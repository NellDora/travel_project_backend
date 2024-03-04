package com.nelldora.travel.board.dto;

import com.nelldora.travel.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BoardReplyDTO {

    private Long brno;

    private Board board;

    private Long boardNum;

    private String content;

    private Timestamp regDate;

    private Timestamp updateDate;

    private boolean delFlag;

    private boolean reportFlag;
}
