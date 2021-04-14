package com.zhoupiyao.dao;

import com.zhoupiyao.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentByBlogId(Long blogId, Sort sort);

    List<Comment> findCommentByBlogIdAndParentCommentNull(Long blogId, Sort sort);

    void deleteByBlogId(Long blogId);
}
