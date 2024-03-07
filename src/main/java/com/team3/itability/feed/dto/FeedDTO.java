package com.team3.itability.feed.dto;


import com.team3.itability.member.dto.MemberInfoDTO;
import com.team3.itability.reple.aggregate.CommentEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "feed_dto")
@Table(name = "board")
public class FeedDTO {



    @Id
    @Column(name = "board_id")
    private int boardId;

    @Column(name = "board_title")
    private String boardTitile;

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

    @JoinColumn(name = "member_id")
    @ManyToOne
    private MemberInfoDTO memberId;

    @JoinColumn(name = "img_id")
    @ManyToOne
    private ImgDTO imgId;

    @OneToMany(mappedBy = "cmtId", fetch = FetchType.LAZY)
    private List<CommentEntity> comments;

}
