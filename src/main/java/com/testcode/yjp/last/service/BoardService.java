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
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    public Long save(Long id,BoardSaveRequestDto boardSaveRequestDto){

        Optional<Member> memberId = memberRepository.findById(id);
        log.info("save post service");
        System.out.println(id);
        System.out.println(memberId);

        return boardRepository.save(boardSaveRequestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<BoardListResponseDto> findAllDesc(){
        return boardRepository.findAllDesc().stream()
                .map(BoardListResponseDto::new)
                .collect(Collectors.toList());
    }

    public BoardResponseDto findById(Long id) {
        Board entity = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다=id=" + id));
        return new BoardResponseDto(entity);
    }

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

    public void delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        boardRepository.delete(board);
    }
}
