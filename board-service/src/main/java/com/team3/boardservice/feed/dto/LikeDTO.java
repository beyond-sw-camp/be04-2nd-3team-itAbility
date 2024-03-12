package com.team3.boardservice.feed.dto;

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

    @Column(name = "member_id")
    private long memberId;
}
