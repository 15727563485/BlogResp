package com.zhoupiyao.blog.service;

import com.zhoupiyao.blog.po.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);
    Comment saveComment(Comment comment);
    void deleteCommentsByblogId(Long bogId);
}
