package com.example.elasticsearchservice.web.repository;


import com.example.elasticsearchservice.web.entity.Posts;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ElasticRepository extends ElasticsearchRepository<Posts, String> {
    @Query("""
        "bool": {
        "must":[
          {
            "match": {
              "content": "#{#content}"
            }
          },
          {
            "match": {
                "media_content": "#{#content}"
            }
          }
        ],
        "should": [
          {
            "match_phrase": {
              "content": "#{#tags}"
            }
          }
        ]
        }
        },
        "highlight": {
        "fragment_size": 500,
        "fields": {
            "content": {
            }
        }
        }
    """)
    List<Posts> getPostsBySearch(String content, String tags);
}
