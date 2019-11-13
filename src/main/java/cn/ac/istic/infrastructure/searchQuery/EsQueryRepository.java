package cn.ac.istic.infrastructure.searchQuery;

import cn.ac.istic.infrastructure.searchQuery.exception.ThrowOutMessageAndDataException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface EsQueryRepository<T,ID extends Serializable> extends ElasticsearchRepository<T,ID> {
    Page<T> searchByQuery(String query, Pageable pageable) throws ThrowOutMessageAndDataException;
}
