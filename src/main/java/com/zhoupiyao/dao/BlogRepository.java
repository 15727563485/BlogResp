package com.zhoupiyao.dao;


import com.zhoupiyao.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

    @Query("select b from t_blogs b where b.recommend=true and b.published=true")
    List<Blog> findTop(Pageable pageable);

    @Query("select b from t_blogs b where b.published=true")
    List<Blog> findNew(Pageable pageable);

    @Query("select b from t_blogs b where b.published=true")
    Page<Blog> findPublished(Pageable pageable);

    @Query("select b from t_blogs b where b.title like ?1 or b.content like ?1 " +
            "or b.description like ?1 and b.published=true")
    Page<Blog> findQueryed(String searchContent, Pageable pageable);

    @Query("select function('date_format',b.createTime,'%Y') as year from t_blogs b")
    List<String> findGroupYear();

    @Query("select b from t_blogs b where function('date_format',b.createTime,'%Y') = ?1")
    List<Blog> findByYear(String year);

    @Modifying
    @Query("update t_blogs set views=views+1 where id=?1")
    int updateView(Long blogId);

    @Query(value = "select * from t_blogs_tags where tags_id=?1", nativeQuery = true)
    List findUseedgBytagId(Long tagId);

    @Query("select b from t_blogs b where b.type.id=?1")
    List<Blog> findBlogBytypeId(Long typeId);
}
