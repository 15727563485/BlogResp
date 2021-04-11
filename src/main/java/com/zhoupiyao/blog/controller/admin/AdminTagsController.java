package com.zhoupiyao.blog.controller.admin;

import com.zhoupiyao.blog.po.Tag;
import com.zhoupiyao.blog.service.BlogService;
import com.zhoupiyao.blog.service.TagService;
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
public class AdminTagsController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;
    @GetMapping("/tagsControl")
    public String tagPage(@PageableDefault(size = 4,sort={"id"},
            direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }
    @GetMapping("/tagsControl/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }
    @GetMapping("/tagsControl/{id}/input")
    public String editorPage(Model model,@PathVariable Long id){
        model.addAttribute("tag",tagService.getTagById(id));
        return "admin/tags-input";
    }
    @PostMapping("/tagsAdd")
    public String post(Tag tag, RedirectAttributes redirectAttributes){
        if (tagService.getTagByName(tag.getName()) == null){
            Tag t=tagService.saveTag(tag);
            if(t == null){
                redirectAttributes.addFlashAttribute("message","抱歉，添加失败！");
            }else{
                redirectAttributes.addFlashAttribute("message","恭喜，添加成功!");
            }
        }else {
            redirectAttributes.addFlashAttribute("message","抱歉，添加失败！该标签名称已存在！");
        }
        return "redirect:/admin/tagsControl";
    }
    @GetMapping("/tagsControl/delete")
    public String delete(Long id,RedirectAttributes redirectAttributes){
        if(blogService.hasUsedBytagId(id) == null){
            tagService.deleteTag(id);
            redirectAttributes.addFlashAttribute("message","恭喜，删除成功!");
        }
        redirectAttributes.addFlashAttribute("message","该标签已被使用，无法删除!");
        return "redirect:/admin/tagsControl";
    }

    @PostMapping("/tagsControl/{id}/tagsUpadte")
    public String editorTag(Tag tag,@PathVariable Long id,RedirectAttributes redirectAttributes){
        if (tagService.getTagByName(tag.getName()) == null){
            tagService.upadteTag(id,tag);
            redirectAttributes.addFlashAttribute("message","恭喜，修改成功!");
        }else {
            redirectAttributes.addFlashAttribute("message","抱歉，修改失败！该标签名称已存在！");
        }

        return "redirect:/admin/tagsControl";
    }
}
