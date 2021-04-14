package com.zhoupiyao.controller.admin;

import com.zhoupiyao.po.Type;
import com.zhoupiyao.service.BlogService;
import com.zhoupiyao.service.TypeService;
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
public class AdminTypesController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    @GetMapping("/typesControl")
    public String typePage(@PageableDefault(size = 5, sort = {"id"},
            direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/typesControl/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @GetMapping("/typesControl/{id}/input")
    public String editorPage(Model model, @PathVariable Long id) {
        model.addAttribute("type", typeService.getTypeById(id));
        return "admin/types-input";
    }

    @PostMapping("/typesAdd")
    public String post(Type type, RedirectAttributes redirectAttributes) {
        if (typeService.getTypeByName(type.getName()) == null) {
            Type t = typeService.saveType(type);
            if (t == null) {
                redirectAttributes.addFlashAttribute("message", "抱歉，添加失败！");
            } else {
                redirectAttributes.addFlashAttribute("message", "恭喜，添加成功!");
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "抱歉，添加失败！该分类名称已存在！");
        }
        return "redirect:/admin/typesControl";
    }

    @GetMapping("/typesControl/delete")
    public String delete(Long id, RedirectAttributes redirectAttributes) {
        if (blogService.listBlogBytypeId(id) == null) {
            typeService.deleteType(id);
            redirectAttributes.addFlashAttribute("message", "恭喜，删除成功!");
        }
        redirectAttributes.addFlashAttribute("message", "该分类已被使用，无法删除!");
        return "redirect:/admin/typesControl";
    }

    @PostMapping("/typesControl/{id}/typesUpadte")
    public String editorType(Type type, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (typeService.getTypeByName(type.getName()) == null) {
            typeService.upadteType(id, type);
            redirectAttributes.addFlashAttribute("message", "恭喜，修改成功!");
        } else {
            redirectAttributes.addFlashAttribute("message", "抱歉，修改失败！该分类名称已存在！");
        }
        return "redirect:/admin/typesControl";
    }


}
