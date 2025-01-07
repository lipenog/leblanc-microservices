package com.example.elasticsearchservice.web.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "posts", createIndex = false)
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@ToString @Builder
public class Posts {
    @Id
    @Field(type = FieldType.Keyword, name = "id")
    private String id;
    @Field(type = FieldType.Text, name = "content")
    private String content;
    @Field(type = FieldType.Text, name = "media_content")
    private String mediaContent;
}
