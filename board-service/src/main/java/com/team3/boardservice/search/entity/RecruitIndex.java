package com.team3.boardservice.search.entity;


import com.team3.boardservice.recruitment.aggregate.RecruitType;
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
@Document(indexName = "recruit_index")
public class RecruitIndex {

    @Id
    private String recruit_id;

    @Field(type = FieldType.Text)
    private RecruitType recruit_type;

    @Field(type = FieldType.Text)
    private String recruit_title;

    @Field(type = FieldType.Text)
    private String recruit_content;

    @Field(type = FieldType.Text)
    private String recruit_expDate;

    @Field(type = FieldType.Text)
    private Integer recruit_mb_cnt;

    @Field(type = FieldType.Text)
    private Long member_id;

    @Field(type = FieldType.Text)
    private int skillId;

    @Field(type = FieldType.Text)
    private int recruitCategoryId;

}
