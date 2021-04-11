package com.testcode.yjp.last.controller.android;

import com.testcode.yjp.last.domain.Board;
import com.testcode.yjp.last.domain.Member;
import com.testcode.yjp.last.domain.dto.BoardListResponseDto;
import com.testcode.yjp.last.domain.dto.android.AndBoardSaveDto;
import com.testcode.yjp.last.domain.dto.android.AndBoardSearchDto;
import com.testcode.yjp.last.domain.dto.android.AndBoardUpdateDto;
import com.testcode.yjp.last.repository.android.AndroidBoardRepository;
import com.testcode.yjp.last.repository.BoardRepository;
import com.testcode.yjp.last.repository.MemberRepository;
import com.testcode.yjp.last.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/android/board")
public class AndroidBoardController {
    private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final MemberRepository memberRepository;
    private final AndroidBoardRepository androidBoardRepository;

    // 게시판 조회
    @PostMapping("/select")
    public ArrayList<BoardListResponseDto> select() {
        log.info("BoardController select 1st Line");

        ArrayList<BoardListResponseDto> allDesc = (ArrayList<BoardListResponseDto>) boardService.findAllDesc();

        log.info("어우 렉걸려"+allDesc.toString());
        return allDesc;
    }

    // 게시판 등록
    @PostMapping("/insert/{member_id}")
    public Board insert(@PathVariable("member_id") Long member_id, @RequestBody AndBoardSaveDto andBoardSaveDto) {
        log.info("BoardController insert 1st Line");

        Member member = memberRepository.findById(member_id).get();
        andBoardSaveDto.setUser_id(member.getUser_id());
        andBoardSaveDto.setMember(member);
        return boardRepository.save(andBoardSaveDto.toEntity());
    }

    // 게시판 수정
    @PutMapping("/update/{board_id}")
    public Board update(@PathVariable("board_id") Long board_id, @RequestBody AndBoardUpdateDto andBoardUpdateDto) {
        log.info("BoardController update 1st Line");

        Board board = boardRepository.findById(board_id).get();
        board.setContent(andBoardUpdateDto.getContent());
        board.setTitle(andBoardUpdateDto.getTitle());

        return boardRepository.save(board);
    }

    // 게시판 삭제
    @DeleteMapping("/delete/{board_id}")
    public Long delete(@PathVariable("board_id") Long board_id) {
        log.info("BoardController delete 1st Line");

        boardService.delete(board_id);
        return board_id;
    }

    // 게시판 검색
    @PostMapping("/search")
    public List<BoardListResponseDto> search(@RequestBody AndBoardSearchDto andBoardSearchDto) {
        log.info("BoardController search 1st Line");

        String head = andBoardSearchDto.getHead();
        String search = andBoardSearchDto.getSearch();
        if (head.equals("t")) {
            return androidBoardRepository.titleSearch(search);
        } else if (head.equals("c")) {
            return androidBoardRepository.contentSearch(search);
        } else if (head.equals("u")) {
            return androidBoardRepository.userSearch(search);
        }
        return boardService.findAllDesc();
    }
}
