package com.zhoupiyao.service;

import com.zhoupiyao.po.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);

    void deleteCommentsByblogId(Long bogId);
}
