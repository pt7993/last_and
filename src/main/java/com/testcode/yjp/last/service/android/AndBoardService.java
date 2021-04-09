package com.testcode.yjp.last.service.android;

import com.querydsl.core.BooleanBuilder;
import com.testcode.yjp.last.domain.Board;
import com.testcode.yjp.last.domain.dto.BoardDto;
import com.testcode.yjp.last.domain.dto.PageRequestDto;
import com.testcode.yjp.last.domain.dto.PageResultDto;
import com.testcode.yjp.last.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AndBoardService {
    private final BoardRepository boardRepository;

//    public PageResultDto<BoardDto, Board> getList(PageRequestDto requestDto) {
//
//    }
}
