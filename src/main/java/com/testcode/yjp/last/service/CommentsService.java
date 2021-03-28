package com.testcode.yjp.last.service;

import com.testcode.yjp.last.domain.Board;
import com.testcode.yjp.last.domain.Comments;
import com.testcode.yjp.last.domain.dto.BoardUpdateRequestDto;
import com.testcode.yjp.last.domain.dto.CommentsListResponseDto;
import com.testcode.yjp.last.domain.dto.CommentsUpdateRequestDto;
import com.testcode.yjp.last.repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentsService {

    private final CommentsRepository commentsRepository;

    @Transactional(readOnly = true)
    public List<CommentsListResponseDto> findAllDesc() {
        return commentsRepository.findAllDesc().stream()
                .map(CommentsListResponseDto::new)
                .collect(Collectors.toList());
    }


    public Comments findByComments(Long ParentNum) {
        return commentsRepository.findByParentNum(ParentNum);
    }


    public Long update(Long id, CommentsUpdateRequestDto commentsUpdateRequestDto) {
        log.info("comments update post service");
        Comments comments = commentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + id));
        comments.update(commentsUpdateRequestDto.getComments());
        System.out.println(commentsUpdateRequestDto.getComments());
        commentsRepository.save(comments);
        return id;
    }

    public void delete(Long id) {
        Comments comments = commentsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다=id" + id));
        commentsRepository.delete(comments);
    }
}
