package com.team3.boardservice.search.entity;

import jakarta.persistence.Column;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "searchitem")
public class SearchItem {
    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String position_name;

    @Field(type = FieldType.Text)
    private String company_name;

    @Field(type = FieldType.Text)
    private String img_src;

    @Field(type = FieldType.Text)
    private String href;
}