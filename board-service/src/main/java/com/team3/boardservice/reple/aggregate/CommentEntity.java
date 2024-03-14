package com.team3.boardservice.reple.aggregate;



import com.team3.boardservice.feed.dto.FeedDTO;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "boardId")
@Entity(name = "comment_dto")
@Table(name = "comment")
public class CommentEntity {

    @Id
    @Column(name = "cmt_id")
    private int cmtId;

    @Column(name = "write_date")
    private String writeDate;

    @Column(name = "report_count")
    private int reportCount;

    @Column(name = "member_id")
    private long memberId;

    @Column(name = "cmt_content")
    private String cmtContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private FeedDTO boardId;

//    public String getNickname() {
//        return memberId != null ? memberId.getName() : null;
//    }
}
