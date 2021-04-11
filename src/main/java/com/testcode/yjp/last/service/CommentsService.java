package com.testcode.yjp.last.service;

import com.querydsl.core.BooleanBuilder;
import com.testcode.yjp.last.domain.Comment;
import com.testcode.yjp.last.domain.dto.*;
import com.testcode.yjp.last.repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
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


//    public Comment findByComments(Long ParentNum) {
//        return commentsRepository.findBy(ParentNum);
//    }


    public Long update(Long id, CommentsUpdateRequestDto commentsUpdateRequestDto) {
        log.info("comments update post service");
        Comment comments = commentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + id));
        comments.update(commentsUpdateRequestDto.getComments());
        System.out.println(commentsUpdateRequestDto.getComments());
        commentsRepository.save(comments);
        return id;
    }

    @Transactional
    public void delete(Long id) {
//        Comment comments = commentsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다=id" + id));
        commentsRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<CommentsListResponseDto> findAllCount(Long hb_num) {
        return commentsRepository.findAllCount(hb_num).stream()
                .map(CommentsListResponseDto::new)
                .collect(Collectors.toList());
    }


    public PageResultDto<CommentDto, Comment> getList(PageRequestDto requestDto) {
        Pageable pageable = requestDto.getPageable(Sort.by("id").descending());
        Page<Comment> result = commentsRepository.findAll(pageable);
        Function<Comment, CommentDto> fn = (entity -> entityToCoDto(entity));
        return new PageResultDto<>(result, fn);
    }



    private CommentDto entityToCoDto(Comment entity) {
        CommentDto dto = CommentDto.builder()
                .id(entity.getId())
                .user_id(entity.getUser_id())
                .like_check(entity.getLike_check())
                .dislike_check(entity.getDislike_check())
                .parentNum(entity.getParentNum())
                .comments(entity.getComments())
                .regDate(entity.getRegDate())
                .modifiedDate(entity.getModDate())
                .build();
        return dto;
    }

}
