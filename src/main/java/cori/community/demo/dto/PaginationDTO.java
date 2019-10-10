package cori.community.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 3plus2
 * @data 2019/10/8 17 09
 * @desercription
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTO;
    private Boolean showPrevious;
    private Boolean showFirstPage;
    private Boolean showNext;
    private Boolean showEndPage;
    private Integer page;
    private List<Integer> pages;
    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer page, Integer size) {

        Integer totalPage = 0;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        this.totalPage=totalPage;

        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }


        this.page=page;

        pages =new ArrayList<>();
        pages.add(page);
        for (int i = 1, k = 1; i <= 5; i++) {
            if (page-i>0&&k<5){
                pages.add(0,page-i);
                k++;
            }
            if(page+i<=totalPage&&k<5){
                pages.add(page+i);
                k++;
            }


        }

        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }


    }
}
