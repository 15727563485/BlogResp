package com.zhoupiyao.service;

import com.zhoupiyao.dao.TagRepository;
import com.zhoupiyao.po.Blog;
import com.zhoupiyao.po.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TagServiceImp implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void deleteTag(Long id) {
        tagRepository.delete(id);
    }

    @Override
    public Tag upadteTag(Long id, Tag tag) {
        Tag t = tagRepository.findOne(id);
        BeanUtils.copyProperties(tag, t);
        return tagRepository.save(t);
    }

    @Override
    public Tag getTagById(Long id) {
        return tagRepository.findOne(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public List<Tag> listAllTag() {
        return tagRepository.findAll();
    }

    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = new PageRequest(0, size, sort);
        return tagRepository.findtagTop(pageable);
    }

    @Override
    public List<Tag> listSelectTags(String ids) {
        return tagRepository.findAll(convertToList(ids));
    }

    @Override
    public String listSelectTags(Blog blog) {
        return convertToString(blog);
    }

    private List<Long> convertToList(String ids) {
        List<Long> tagsList = new ArrayList<>();
        String[] tagsArray = ids.split(",");
        for (String tag : tagsArray) {
            tagsList.add(new Long(tag));
        }
        return tagsList;
    }

    private String convertToString(Blog blog) {
        StringBuffer ids = new StringBuffer();
        boolean flag = false;
        if (!blog.getTags().isEmpty()) {
            for (Tag tag : blog.getTags()) {
                if (flag) {
                    ids.append(',');
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return blog.getTagIds();
        }

    }
}
