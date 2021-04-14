package com.zhoupiyao.service;

import com.zhoupiyao.po.Blog;
import com.zhoupiyao.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    Tag saveTag(Tag tag);

    void deleteTag(Long id);

    Tag upadteTag(Long id, Tag tag);

    Tag getTagById(Long id);

    Tag getTagByName(String name);

    List<Tag> listAllTag();

    List<Tag> listSelectTags(String ids);

    String listSelectTags(Blog blog);

    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTagTop(Integer size);

}
