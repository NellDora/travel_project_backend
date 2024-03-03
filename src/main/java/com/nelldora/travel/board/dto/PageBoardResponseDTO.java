package com.nelldora.travel.board.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageBoardResponseDTO<E> {

    private List<E> dtoList;

    private List<Integer> pageNumList;

    private PageBoardRequestDTO pageBoardRequestDTO;

    private boolean prev,next;

    private int totalCount, prevPage, nextPage, totalPage, current;

    @Builder(builderMethodName = "withAll")
    public PageBoardResponseDTO(List<E> dtoList, PageBoardRequestDTO pageBoardRequestDTO, long total){

        this.dtoList = dtoList;
        this.pageBoardRequestDTO = pageBoardRequestDTO;
        this.totalCount = (int)total;
        this.current = pageBoardRequestDTO.getPage();

        int end =(int)(Math.ceil(pageBoardRequestDTO.getPage()/10.0))*10;

        int start = end-9;

        int last = (int)(Math.ceil(totalCount/(double) pageBoardRequestDTO.getSize()));

        end = end>last ? last : end;

        this.prev = start >1;
        this.next = totalCount >end * pageBoardRequestDTO.getSize();

        this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

        this.prevPage =prev ? start-1 :0;
        this.nextPage = next ? end +1:0;
    }
}
