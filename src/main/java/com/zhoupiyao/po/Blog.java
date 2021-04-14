package com.zhoupiyao.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "t_blogs")
@Table
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;
    //博客图片
    private String pictureAddr;
    //访问数
    private Integer views;
    //文章是（原创，转载或其他）
    private String flag;
    //开启推荐
    private boolean recommend;
    //开启转载声明
    private boolean statement;
    //开启赞赏
    private boolean admire;
    //开启评论
    private boolean comment;
    //是否发布
    private boolean published;
    //博客简短描述
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String description;
    //此注解表示不会添加进数据库的属性
    @Transient
    private String tagIds;

    //此注解表示设置日期格式
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @ManyToOne
    private Type type;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    private User user;

    public Blog() {
    }

    public Blog(Long id, String title, String flag, String content, String description, String pictureAddr, Integer views, boolean recommend, boolean statement, boolean admire, boolean comment, boolean published, Date createTime, Date updateTime, Type type, List<Tag> tags, List<Comment> comments, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.description = description;
        this.pictureAddr = pictureAddr;
        this.flag = flag;
        this.views = views;
        this.recommend = recommend;
        this.statement = statement;
        this.admire = admire;
        this.comment = comment;
        this.published = published;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.type = type;
        this.tags = tags;
        this.comments = comments;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPictureAddr() {
        return pictureAddr;
    }

    public void setPictureAddr(String pictureAddr) {
        this.pictureAddr = pictureAddr;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public boolean isStatement() {
        return statement;
    }

    public void setStatement(boolean statement) {
        this.statement = statement;
    }

    public boolean isAdmire() {
        return admire;
    }

    public void setAdmire(boolean admire) {
        this.admire = admire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", pictureAddr='" + pictureAddr + '\'' +
                ", views=" + views +
                ", flag='" + flag + '\'' +
                ", recommend=" + recommend +
                ", statement=" + statement +
                ", admire=" + admire +
                ", comment=" + comment +
                ", published=" + published +
                ", description='" + description + '\'' +
                ", tagIds='" + tagIds + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", type=" + type +
                ", tags=" + tags +
                ", comments=" + comments +
                ", user=" + user +
                '}';
    }
}
