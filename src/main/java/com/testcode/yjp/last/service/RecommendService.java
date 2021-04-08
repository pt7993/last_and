package com.testcode.yjp.last.service;

import com.testcode.yjp.last.domain.Board;
import com.testcode.yjp.last.domain.Member;
import com.testcode.yjp.last.domain.Recommend;
import com.testcode.yjp.last.domain.dto.BoardListResponseDto;
import com.testcode.yjp.last.repository.BoardRepository;
import com.testcode.yjp.last.repository.LikeRepository;
import com.testcode.yjp.last.repository.MemberRepository;
import com.testcode.yjp.last.repository.RecommendRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RecommendService {
    private final RecommendRepository recommendRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 게시글 번호, 세션 user_id
    public boolean recommend(Long boards_id,String recommendId) {
        log.info("service recommend post");
        // 1. 게시글이 있는지 확인을 해야합니다
        Optional<Board> result = boardRepository.findById(boards_id);

        // 2. board에 id 를 뽑고 member에 user_id를 값을 받는다
        Board board = boardRepository.findById(boards_id).orElse(null);
        Member member = memberRepository.findByUser_id(recommendId);
        System.out.println(board);
        System.out.println(member);
        // 중복 좋아요 방지지
        if (CheckLike(board, member)) {
            return false;
        }
        Recommend like = new Recommend(member,board);
        recommendRepository.save(like);
        return true;
    }

    //     사용자가 이미 좋아요 한 게시물인지 체크
    public boolean CheckLike(Board board, Member member) {
        Recommend byMemberAndBoard = recommendRepository.findByMemberAndBoard(member, board).orElse(null);
        System.out.println("CheckLike="+byMemberAndBoard);
        if (byMemberAndBoard != null) {
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public List<BoardListResponseDto> findAllDesc(){
        return boardRepository.findAllDesc().stream()
                .map(BoardListResponseDto::new)
                .collect(Collectors.toList());
    }

//    public List<LikeListResponseDto> getLikeCount() {
//        return likeRepository.findAllDesc().stream()
//                .map(LikeListResponseDto::new)
//                .collect(Collectors.toList());
//    }

}
