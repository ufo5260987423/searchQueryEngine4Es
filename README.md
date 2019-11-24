# Search Query Engine for Spring Data ElasticSearch

> An s-expression parser for Spring Data Elasticsearch.

It parses an s-expression like this:
```
(and (between pub_time "2019-10-01" "1992-08-29") (like title "squid"))
```

And turns it into a boolQuery like this:

```json
{
	"from": 0,
	"size": 1,
	"query": {
		"bool": {
			"must": [{
					"range": {
						"pub_date": {
							"from": "1992-08-29T00:00:00.000Z",
							"to": "2019-10-01T00:00:00.000Z",
							"include_lower": true,
							"include_upper": true,
							"boost": 1.0
						}
					}
				},
				{
					"match": {
						"title": {
							"query": "squid",
							"operator": "OR",
							"prefix_length": 0,
							"max_expansions": 50,
							"fuzzy_transpositions": true,
							"lenient": false,
							"zero_terms_query": "NONE",
							"boost": 1.0
						}
					}
				}
			],
			"disable_coord": false,
			"adjust_pure_negative": true,
			"boost": 1.0
		}
	},
	"version": true
}
```

## TODO
* Self-defined macro;
* Add some macro supported keywords.

## Installation

```shell
git clone https://gitee.com/istic/searchQueryEngine4Es.git
cd searchQueryEngine4Es
mvn install
```
```xml
<dependency>
    <groupId>cn.ac.istic.infrastructure</groupId>
    <artifactId>searchQueryEngine4Es</artifactId>
    <version>1.0</version>
</dependency>
```
This project now is tested with Spring Cloud version as:
```xml
<parent>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-parent</artifactId>
    <version>Finchley.SR1</version>
</parent>
```

## Usage
Add `AbstractEsQueryRepositoryBase.class` to your repsitory base class like:
```java
@SpringBootApplication(scanBasePackages = "cn.ac.istic")
@EnableElasticsearchRepositories(basePackages = "cn.ac.istic",
    repositoryBaseClass = AbstractEsQueryRepositoryBase.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```
Then you can create your elasticsearch repostitory as following:
```java
public interface EsPolicyRepository extends EsQueryRepository<EsPolicy, Integer> {}
```
With autowired property
```java
@Autowired 
private EspolicyRepository espolicyRepository;
```

you could excute your query and get result:
```java
@Transactional
public Page<EsPolicy> fuzzySearch(String query, Pageable pageable) throws ThrowOutMessageAndDataException {
    return esPolicyRepository.searchByQuery(query, pageable);
}
```

## Query
Following keywords are supported now:
* `eq`, accepts 2 parameters like `[FieldName, Integer/Double/String]`,  exact matching;
* `like`, accepts 2 parameters like `[FieldName, String]`, fuzzy searching;
* `startWith`, accepts 2 parameters like `[FieldName, String]`,  forward matching;
* `between`, accepts 3 parameters like `[FieldName, time-String, time-String]`, time-range matching;
* `after`, accepts 2 parameters like `[FieldName, time-String]`;
* `before`, accepts 2 parameters like `[FieldName, String]`;
* `and`, accepts multi-parameters like `[query...]`, all conditions are met;
* `or`, accepts multi-parameters like `[query...]`, more than one condition is met;
* `not`, accepts multi-parameters like `[query...]`, none of listed conditions is met;

Any keywords you wanted might be supported by pulling requests.

## License

The MIT License (MIT)

Copyright (c) 2014

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
