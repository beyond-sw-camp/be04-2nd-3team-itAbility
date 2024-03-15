package com.team3.boardservice.feed.dto;



import com.team3.boardservice.reple.aggregate.CommentEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "comments")
@Entity(name = "feed_dto")
@Table(name = "board")
public class FeedDTO {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardId;

    @Column(name = "board_title")
    private String boardTitle;

    @Column(name = "board_content")
    private String boardContent;

    @Column(name = "write_date")
    private String writeDate;

    @Column(name = "hits")
    private int hits;

    @Column(name = "report_count")
    private int reportCount;

    @Column(name = "is_active")
    private int isActive;

    @Column(name = "member_id")
    private long memberId;

//    @JoinColumn(name = "img_id")
    @OneToMany(mappedBy = "imgId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ImgDTO> imgId;

    @OneToMany(mappedBy = "cmtId", fetch = FetchType.LAZY)
    private List<CommentEntity> comments;

}
