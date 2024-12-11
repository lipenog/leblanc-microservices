package com.example.elasticsearchservice;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "posts")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@ToString
public class Posts {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Text)
    private String content;
    @Field(type = FieldType.Text, name = "media_content")
    private String mediaContent;
}
