package com.testcode.yjp.last.service;

import com.testcode.yjp.last.domain.Board;
import com.testcode.yjp.last.domain.Member;
import com.testcode.yjp.last.domain.dto.BoardListResponseDto;
import com.testcode.yjp.last.domain.dto.BoardResponseDto;
import com.testcode.yjp.last.domain.dto.BoardSaveRequestDto;
import com.testcode.yjp.last.domain.dto.BoardUpdateRequestDto;
import com.testcode.yjp.last.repository.BoardRepository;
import com.testcode.yjp.last.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    // Board save 연관관계 매핑 처리부분
    public Long save(Long member_id,BoardSaveRequestDto boardSaveRequestDto){

        Optional<Member> memberId = memberRepository.findById(member_id);
        log.info("save post service");
        System.out.println(member_id);
        System.out.println(memberId);

        return boardRepository.save(boardSaveRequestDto.toEntity()).getId();
    }

    // 게시판 전체조회
    @Transactional(readOnly = true)
    public List<BoardListResponseDto> findAllDesc(){
        return boardRepository.findAllDesc().stream()
                .map(BoardListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 게시판 findById
    public BoardResponseDto findById(Long id) {
        Board entity = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다=id=" + id));
        return new BoardResponseDto(entity);
    }

    // 게시판 수정
    public Long update(Long id, BoardUpdateRequestDto boardUpdateRequestDto) {
        log.info("update post controller");
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        board.update(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent());
        System.out.println(boardUpdateRequestDto.getTitle());
        System.out.println(boardUpdateRequestDto.getContent());
        boardRepository.save(board);
        return id;
    }

    // 게시판 삭제
    public void delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        boardRepository.delete(board);
    }


    @Transactional
    public int updateView(Long id) {
        log.info("조회수 증가 서비스");
        return boardRepository.updateView(id);
    }
}
