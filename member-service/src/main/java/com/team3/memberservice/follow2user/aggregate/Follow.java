package com.team3.memberservice.follow2user.aggregate;

import com.team3.memberservice.member.dto.MemberInfoDTO;
import lombok.*;
import jakarta.persistence.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name="follow")

public class Follow {

        @Id
        @Column(name="follow_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int followId;

        @JoinColumn(name="member_id_following")
        @ManyToOne
        private MemberInfoDTO following;

        @JoinColumn(name="member_id_followed")
        @ManyToOne
        private MemberInfoDTO followed;

}
