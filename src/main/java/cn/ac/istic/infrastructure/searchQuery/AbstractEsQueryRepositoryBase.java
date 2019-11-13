package cn.ac.istic.infrastructure.searchQuery;

import cn.ac.istic.infrastructure.searchQuery.exception.ThrowOutMessageAndDataException;
import cn.ac.istic.infrastructure.searchQuery.exception.ThrowOutMessageException;
import cn.ac.istic.infrastructure.searchQuery.parser.S_ExpressionLexer;
import cn.ac.istic.infrastructure.searchQuery.parser.S_ExpressionParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.support.AbstractElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;


public class AbstractEsQueryRepositoryBase<T, ID extends Serializable> extends AbstractElasticsearchRepository<T, ID> implements EsQueryRepository<T, ID> {
    private ParseTreeWalker parseTreeWalker = new ParseTreeWalker();

    public AbstractEsQueryRepositoryBase() {
    }

    public AbstractEsQueryRepositoryBase(ElasticsearchOperations elasticsearchOperations) {
        super(elasticsearchOperations);
    }

    public AbstractEsQueryRepositoryBase(ElasticsearchEntityInformation<T, ID> metadata, ElasticsearchOperations elasticsearchOperations) {
        super(metadata, elasticsearchOperations);
    }

    @Override
    public Page<T> searchByQuery(String query, Pageable pageable) throws ThrowOutMessageAndDataException {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(build(query)).withPageable(pageable).build();
        return elasticsearchOperations.queryForPage(searchQuery, getEntityClass());
    }

    private QueryBuilder build(String query) throws ThrowOutMessageAndDataException {
        CharStream input = CharStreams.fromString(query);
        S_ExpressionLexer lexer = new S_ExpressionLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        S_ExpressionParser parser = new S_ExpressionParser(tokens);
        ParseTree tree = parser.s_expression();
        AstConverter astCoverter = new AstConverter();
        parseTreeWalker.walk(astCoverter, tree);
        try {
            return build(astCoverter.getAst());
        } catch (ThrowOutMessageException e) {
            throw new ThrowOutMessageAndDataException(e.getMessage(), query);
        }
    }

    private QueryBuilder build(List sExpression) throws ThrowOutMessageException {
        if (sExpression.isEmpty())
            return null;
        for (int i = 0; i < sExpression.size(); i++)
            if (sExpression.get(i) instanceof List)
                sExpression.set(i, build((List) sExpression.get(i)));
        BoolQueryBuilder boolQueryBuilder = boolQuery();
        if (sExpression.get(0) instanceof AstConverter.Vaiable)
            switch (sExpression.get(0).toString()) {
                case "eq":
                    if (sExpression.get(1) instanceof AstConverter.Vaiable &&
                            (sExpression.get(2) instanceof String
                                    || sExpression.get(2) instanceof Integer
                                    || sExpression.get(2) instanceof Double ))
                        return matchPhraseQuery(sExpression.get(1).toString(), sExpression.get(2));
                    throw new ThrowOutMessageException("err: function accept 2 parameters like [filedName, value]");
                case "startWith":
                    if (sExpression.get(1) instanceof AstConverter.Vaiable && sExpression.get(2) instanceof String)
                        return matchPhrasePrefixQuery(sExpression.get(1).toString(), sExpression.get(2));
                    throw new ThrowOutMessageException("err: function accept 2 parameters like [filedName, value]");
                case "like":
                    if (sExpression.get(1) instanceof AstConverter.Vaiable && sExpression.get(2) instanceof String)
                        return matchQuery(sExpression.get(1).toString(), sExpression.get(2));
                    throw new ThrowOutMessageException("err: function accept 2 parameters like [filedName, value]");
                case "between":
                    if (sExpression.get(1) instanceof AstConverter.Vaiable && sExpression.get(2) instanceof String && sExpression.get(3) instanceof String) {
                        Timestamp a = Timestamp.valueOf((String) sExpression.get(2));
                        Timestamp b = Timestamp.valueOf((String) sExpression.get(3));
                        return rangeQuery(sExpression.get(1).toString()).from(trans(a.before(b) ? a : b)).to(trans(a.after(b) ? a : b));
                    }
                    throw new ThrowOutMessageException("err: function accept 3 parameters like [filedName, time, time], time is like yyyy-mm-dd HH:MM:SS");
                    //TODO: timestamp start from 1900, how to change that ?
                case "after":
                    if (sExpression.get(1) instanceof AstConverter.Vaiable && sExpression.get(2) instanceof String) {
                        ((AstConverter.Vaiable) sExpression.get(0)).setName("between");
                        if (sExpression.size() < 4) {
                            sExpression.add(new Timestamp(System.currentTimeMillis()).toString().replaceAll("\\..+", ""));
                            return build(sExpression);
                        }
                    }
                    throw new ThrowOutMessageException("err: function accept 2 parameters like [filedName, time], time is like yyyy-mm-dd HH:MM:SS");
                    //TODO:这个项目也就算了，将来还是要改的
                case "before":
                    if (sExpression.get(1) instanceof AstConverter.Vaiable && sExpression.get(2) instanceof String) {
                        ((AstConverter.Vaiable) sExpression.get(1)).setName("between");
                        sExpression.set(3, new Timestamp(0).toString().replaceAll("\\..+", ""));
                        return build(sExpression);
                    }
                    throw new ThrowOutMessageException("err: function accept 2 parameters like [filedName, time], time is like yyyy-mm-dd HH:MM:SS");
                case "and":
                    for (int i = 1; i < sExpression.size(); i++)
                        if (sExpression.get(i) instanceof QueryBuilder)
                            boolQueryBuilder.must((QueryBuilder) sExpression.get(i));
                        else
                            throw new ThrowOutMessageException("err: function accept muilti-parameters like [query...]");
                    return boolQueryBuilder;
                case "or":
                    for (int i = 1; i < sExpression.size(); i++)
                        if (sExpression.get(i) instanceof QueryBuilder)
                            boolQueryBuilder.should((QueryBuilder) sExpression.get(i));
                        else
                            throw new ThrowOutMessageException("err: function accept muilti-parameters like [query...]");
                    return boolQueryBuilder;
                case "not":
                    for (int i = 1; i < sExpression.size(); i++)
                        if (sExpression.get(i) instanceof QueryBuilder)
                            boolQueryBuilder.mustNot((QueryBuilder) sExpression.get(i));
                        else
                            throw new ThrowOutMessageException("err: function accept muilti-parameters like [query...]");
                    return boolQueryBuilder;
                default:
                    throw new ThrowOutMessageException("unknown err: convert query to es query");
            }
        throw new ThrowOutMessageException("err: function " + sExpression.get(0).toString() + " is not supported yet.");
    }

    private String trans(Timestamp timestamp) {
        return timestamp.toString().trim().replaceAll(" ", "T") + "00Z";
    }

    @Override
    protected String stringIdRepresentation(ID id) {
        return String.valueOf(id);
    }
}
