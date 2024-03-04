package com.team3.itability.report.dto;

import com.team3.itability.feed.dto.CommentDTO;
import com.team3.itability.feed.dto.FeedDTO;
import com.team3.itability.member.dto.MemberInfoDTO;
import com.team3.itability.recruitment.dto.RecruitDTO;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity(name="report_dto")
@Table(name="report")
public class ReportDTO {

    @Id
    @Column(name = "report_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reportId;

    @Column(name="report_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date reportDate;

    @ManyToOne
    @JoinColumn(name="board_id")
    private FeedDTO boardId;

    @ManyToOne
    @JoinColumn(name="member_id")
    private MemberInfoDTO memberId;

    @Column(name="report_category_id")
    private int reportCategoryId;

    @ManyToOne
    @JoinColumn(name="cmt_id")
    private CommentDTO commentId;


    @ManyToOne
    @JoinColumn(name="recruit_id")
    private RecruitDTO recruitId;
    public String getBoardTitle() {
        return boardId != null ? boardId.getBoardTitile() : null;
    }

    public String getMemberName() {
        return memberId != null ? memberId.getName() : null;
    }
    public String getCommentContent() {
        return commentId != null ? commentId.getCmtContent() : null;
    }
}
