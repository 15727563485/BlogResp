package com.zhoupiyao.blog.service;

import com.zhoupiyao.blog.dao.BlogRepository;
import com.zhoupiyao.blog.po.Blog;
import com.zhoupiyao.blog.po.Type;
import com.zhoupiyao.blog.util.MarkdownUtils;
import com.zhoupiyao.blog.util.MyBeanUtils;
import com.zhoupiyao.blog.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.*;
@Service
@Transactional
public class BlogServiceImp implements BlogService{
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog saveBlog(Blog blog) {
        if(blog.getId() == null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }else{
            blog.setUpdateTime(new Date());
        }
        return blogRepository.save(blog);
    }

    @Override
    public void deleteBlogById(Long id) {
        blogRepository.delete(id);
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b=blogRepository.findOne(id);
        BeanUtils.copyProperties(blog,b, MyBeanUtils.getNullPropertyNames(blog));
        b.setUpdateTime(new Date());
        return blogRepository.save(b);
    }
    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findOne(id);
    }

    @Override
    public Blog getAndConvert(Long id) {
        Blog b=new Blog();
        Blog blog=blogRepository.findOne(id);
        BeanUtils.copyProperties(blog,b);
        String content=blog.getContent();
        content=MarkdownUtils.markdownToHtmlExtensions(content);
        b.setContent(content);
//        System.out.println(content);
        return b;
    }

    //自定义动态查询语句
    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                    predicates.add(cb.like(root.<String>get("title"), "%"+blog.getTitle()+"%"));
                }
                if (blog.getTypeId() != null) {
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }
                if (blog.isRecommend()) {
                    predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, Long tagId) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
               Join join=root.join("tags");
                return cb.equal(join.get("id"),tagId);
            }
        },pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> listPublishedBlog(Integer size,Integer page) {
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable=new PageRequest(page,size,sort);
        return blogRepository.findPublished(pageable);
    }

    @Override
    public List<Blog> listRecommendTop(Integer size) {
        Sort sort=new Sort(Sort.Direction.DESC,"updateTime");
        Pageable pageable=new PageRequest(0,size,sort);
        return blogRepository.findTop(pageable);
    }

    @Override
    public List<Blog> listnewBlogs(Integer size) {
        Sort sort=new Sort(Sort.Direction.DESC,"updateTime");
        Pageable pageable=new PageRequest(0,size,sort);
        return blogRepository.findNew(pageable);
    }
    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogRepository.findGroupYear();
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : years) {
            map.put(year, blogRepository.findByYear(year));
        }
        return map;
    }

    @Override
    public Page<Blog> listQueryBlog(Pageable pageable, String searchContent) {
        return blogRepository.findQueryed(searchContent,pageable);
    }
    @Transactional
    @Override
    public int updateViews(Long blogId) {
        return blogRepository.updateView(blogId);
    }

    @Override
    public Long blogCount(){
        return blogRepository.count();
    }

    @Override
    public List<Blog> listBlogBytypeId(Long typeId) {
        return blogRepository.findBlogBytypeId(typeId);
    }

    @Override
    public List hasUsedBytagId(Long tagId) {
        return blogRepository.findUseedgBytagId(tagId);
    }
}
