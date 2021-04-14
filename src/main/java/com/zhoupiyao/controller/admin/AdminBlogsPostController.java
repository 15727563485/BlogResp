package com.zhoupiyao.controller.admin;

import com.zhoupiyao.po.Blog;
import com.zhoupiyao.po.User;
import com.zhoupiyao.service.BlogService;
import com.zhoupiyao.service.TagService;
import com.zhoupiyao.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminBlogsPostController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/blogsPost")
    public String blogsPostPage(Model model) {
        model.addAttribute("types", typeService.listAllType());
        model.addAttribute("tags", tagService.listAllTag());
        model.addAttribute("blog", new Blog());
        return "admin/blogsPost";
    }

    @PostMapping("/blogsPost/post")
    public String blogsPost(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getTypeById(blog.getType().getId()));
        blog.setTags(tagService.listSelectTags(blog.getTagIds()));
        Blog b = blogService.saveBlog(blog);
        if (b == null) {
            attributes.addFlashAttribute("message", "抱歉,博客发表失败！");
        } else {
            attributes.addFlashAttribute("message", "恭喜,博客发表成功！");
        }
        return "redirect:/admin/blogsControl";
    }

    @PostMapping("/blogsPost/updateBlog")
    public String updateBlogs(Long id, Blog blog, HttpSession session, RedirectAttributes attributes) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setTags(tagService.listSelectTags(blog.getTagIds()));
        Blog b = blogService.updateBlog(blog.getId(), blog);
        if (b != null) {
            attributes.addFlashAttribute("message", "博客修改成功！");
        } else {
            attributes.addFlashAttribute("message", "博客修改失败！");
        }
        return "redirect:/admin/blogsControl";
    }
}
