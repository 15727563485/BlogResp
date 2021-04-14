package com.zhoupiyao.service;

import com.zhoupiyao.po.Blog;
import com.zhoupiyao.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Blog saveBlog(Blog blog);

    void deleteBlogById(Long id);

    Blog updateBlog(Long id, Blog blog);

    Blog getBlogById(Long id);

    Blog getAndConvert(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(Pageable pageable, Long tagId);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listPublishedBlog(Integer size, Integer page);

    List<Blog> listRecommendTop(Integer size);

    List<Blog> listnewBlogs(Integer size);

    Page<Blog> listQueryBlog(Pageable pageable, String searchContent);

    int updateViews(Long blogId);

    Map<String, List<Blog>> archiveBlog();

    Long blogCount();

    List<Blog> listBlogBytypeId(Long typeId);

    List hasUsedBytagId(Long tagId);
}
