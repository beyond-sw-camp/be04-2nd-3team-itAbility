package com.team3.boardservice.feed.vo;
import com.team3.boardservice.feed.dto.FeedDTO;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LikeVO {
    private FeedDTO boardId;
    private long memberId;
}

