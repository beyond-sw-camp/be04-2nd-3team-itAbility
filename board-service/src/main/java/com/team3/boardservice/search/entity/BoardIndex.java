package com.team3.boardservice.search.entity;


import com.team3.boardservice.feed.dto.ImgDTO;
import com.team3.boardservice.reple.dto.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "board_index")
public class BoardIndex {
    @Id
    private String board_id;

    @Field(type = FieldType.Text)
    private String board_title;

    @Field(type = FieldType.Text)
    private String board_content;

    @Field(type = FieldType.Text)
    private String write_date;

    @Field(type = FieldType.Text)
    private int hits;

    @Field(type = FieldType.Text)
    private int reportCount;

    @Field(type = FieldType.Text)
    private int isActive;

    @Field(type = FieldType.Text)
    private long memberId;

    @Field(type = FieldType.Text)
    private ImgDTO imgId;

    @Field(type = FieldType.Text)
    private List<CommentDTO> comments;
}
