package com.zhoupiyao.controller.admin;

import com.zhoupiyao.po.Blog;
import com.zhoupiyao.service.BlogService;
import com.zhoupiyao.service.CommentService;
import com.zhoupiyao.service.TagService;
import com.zhoupiyao.service.TypeService;
import com.zhoupiyao.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminBlogsController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/blogsControl")
    public String blogsControl(@PageableDefault(size = 6, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blogq, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blogq));
        model.addAttribute("types", typeService.listAllType());
        return "admin/blogsControl";
    }

    @PostMapping("/blogsControl/search")
    public String searchBlogs(@PageableDefault(size = 6, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blogq, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blogq));
        return "admin/blogsControl :: blogsList";
    }

    @GetMapping("/blogsPost/{id}/editor")
    public String editorBlogs(@PathVariable Long id, Model model) {
        Blog b = blogService.getBlogById(id);
        b.setTagIds(tagService.listSelectTags(b));
        model.addAttribute("blog", b);
        model.addAttribute("types", typeService.listAllType());
        model.addAttribute("tags", tagService.listAllTag());
        return "admin/blogsPost";
    }

    @GetMapping("/blogsPost/{id}/delete")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        commentService.deleteCommentsByblogId(id);
        blogService.deleteBlogById(id);
        redirectAttributes.addFlashAttribute("message", "恭喜,博客删除成功！");
        return "redirect:/admin/blogsControl";
    }
}
