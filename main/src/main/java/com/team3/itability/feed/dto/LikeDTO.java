package com.team3.itability.feed.dto;

import com.team3.itability.member.dto.MemberInfoDTO;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name="like_dto")
@Table(name="board_like")
public class LikeDTO {

    @Id
    @Column(name = "like_id")
    private int likeId;

    @JoinColumn(name = "board_id")
    @ManyToOne
    private FeedDTO boardId;

    @JoinColumn(name = "member_id")
    @ManyToOne
    private MemberInfoDTO memberId;
}
