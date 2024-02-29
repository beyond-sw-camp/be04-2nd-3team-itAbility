package com.team3.itability.feed;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString


@Entity(name="feed_dto")
@Table(name="board")
public class FeedDTO {

    @Id
    @Column(name="board_id")
    private int boardId;
}
