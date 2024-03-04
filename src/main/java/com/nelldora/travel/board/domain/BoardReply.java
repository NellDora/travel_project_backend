package com.nelldora.travel.board.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class BoardReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brno;

    @ManyToOne
    @JoinColumn(name = "board")
    private Board board;

    private String content;

    private Timestamp regDate;

    private Timestamp updateDate;

    private boolean delFlag;

    private boolean reportFlag;

    public void addBoard(Board board){
        this.board = board;
    }
}
