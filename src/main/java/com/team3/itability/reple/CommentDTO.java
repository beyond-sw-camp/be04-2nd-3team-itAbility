package com.team3.itability.reple;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name="comment_dto")
@Table(name="comment")
public class CommentDTO {

    @Id
    @Column(name="cmt_id")
    private int commentId;
}
