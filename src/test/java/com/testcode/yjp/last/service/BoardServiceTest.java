package com.testcode.yjp.last.service;

import com.testcode.yjp.last.domain.Board;
import com.testcode.yjp.last.domain.dto.BoardDto;
import com.testcode.yjp.last.domain.dto.PageRequestDto;
import com.testcode.yjp.last.domain.dto.PageResultDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTest {

    @Autowired
    private BoardService service;

    @Test
    public void testSearch() {
        PageRequestDto pageRequestDto = PageRequestDto.builder()
                .page(1)
                .size(10)
                .type("t") // 검색 조건
                .keyword("제목200") // 검색키워드
                .build();
        PageResultDto<BoardDto, Board> resultDto = service.getList(pageRequestDto);

        System.out.println("이전" + resultDto.isPrev());
        System.out.println("다음" + resultDto.isNext());
        System.out.println("총합" + resultDto.getTotalPage());

        System.out.println("--------------------------");

        for (BoardDto boardDto : resultDto.getDtoList()) {
            System.out.println(boardDto);
        }
        System.out.println("-------------------------");
        resultDto.getPageList().forEach(i -> System.out.println(i));
    }
}
