package com.zhoupiyao.controller.admin;

import com.zhoupiyao.po.Comment;
import com.zhoupiyao.po.User;
import com.zhoupiyao.service.BlogService;
import com.zhoupiyao.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentsController {
    @Value("${comment.avatar}")
    private String avatar;
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/comments/{blogId}")
    public String comment(@PathVariable Long blogId, Model model) {
        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
        return "blog :: commentsList";
    }

    @PostMapping("/commentpost")
    public String commentpost(Comment comment, HttpSession session) {
        comment.setBlog(blogService.getBlogById(comment.getBlog().getId()));
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAdmin(true);
            comment.setAvatar(user.getAvatar());
//            comment.setNickname(user.getNickname());
        } else {
            comment.setAdmin(false);
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + comment.getBlog().getId();
    }
}
