package com.team3.boardservice.search.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "comment_index")
public class CommentIndex {
    @Id
    private String cmt_Id;

    @Field(type = FieldType.Text)
    private String write_date;

    @Field(type = FieldType.Text)
    private int report_count;

    @Field(type = FieldType.Text)
    private long memberId;

    @Field(type = FieldType.Text)
    private String cmt_content;
}
