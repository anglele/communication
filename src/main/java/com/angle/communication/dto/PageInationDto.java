package com.angle.communication.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageInationDto {
    private List<QuestionDto> questions;
private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer currentPage;

    private List<Integer> pages;
    private Integer TotalPage;

    public void setPages(Integer totlecount, Integer page, Integer size) {
Integer totalPage=0;
if (totlecount % size == 0){
totalPage = totlecount / size;
}else {
    totalPage = totlecount / size + 1;
}
//判断展示上一页
if (page == 1){
    showPrevious = false;
}else {
    showPrevious = true;
}//判断是否展示有下一页
if ( page == totalPage){
    showEndPage =true;
        }else {
    showEndPage = false;
        }//判断是否展示第一页
        if(pages.contains(1)){
    showFirstPage = false;
        }else {
    showFirstPage = true;
        }//判断是否展示最后一页
        if(pages.contains(totalPage)){
    showEndPage=false;
        }else {
    showEndPage = true;
        }
    }


}

