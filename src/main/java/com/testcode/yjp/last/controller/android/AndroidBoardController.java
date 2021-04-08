package com.testcode.yjp.last.controller.android;

import com.testcode.yjp.last.domain.Board;
import com.testcode.yjp.last.domain.Member;
import com.testcode.yjp.last.domain.dto.android.AndBoardSaveDto;
import com.testcode.yjp.last.domain.dto.android.AndBoardUpdateDto;
import com.testcode.yjp.last.repository.BoardRepository;
import com.testcode.yjp.last.repository.MemberRepository;
import com.testcode.yjp.last.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/android/board")
public class AndroidBoardController {
    private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final MemberRepository memberRepository;

    @GetMapping("/select")
    public List<Board> select() {
        List<Board> all = boardRepository.findAll();

        return all;
    }

    @PostMapping("/insert/{member_id}")
    public Board insert(@PathVariable("member_id") Long member_id, @RequestBody AndBoardSaveDto andBoardSaveDto) {
        Member member = memberRepository.findById(member_id).get();
        andBoardSaveDto.setUser_id(member.getUser_id());
        andBoardSaveDto.setMember(member);
        return boardRepository.save(andBoardSaveDto.toEntity());
    }

    @PutMapping("/update/{board_id}")
    public Board update(@PathVariable("board_id") Long board_id, @RequestBody AndBoardUpdateDto andBoardUpdateDto) {
        Board board = boardRepository.findById(board_id).get();
        board.setContent(andBoardUpdateDto.getContent());
        board.setTitle(andBoardUpdateDto.getTitle());

        return boardRepository.save(board);
    }

    @DeleteMapping("/delete/{board_id}")
    public String delete(@PathVariable("board_id") Long board_id) {

        return "yes";
    }
}
