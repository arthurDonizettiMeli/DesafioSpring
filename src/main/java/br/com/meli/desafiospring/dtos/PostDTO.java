package br.com.meli.desafiospring.dtos;

import br.com.meli.desafiospring.models.Post;

import java.util.Date;


public class PostDTO implements Comparable<PostDTO>{

    private Integer userID;
    private Integer id_post;
    private Date date;
    private ProductDTO detail;
    private Integer category;
    private Double price;
    private Boolean hasPromo;
    private Double discount;

    public PostDTO() {
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getId_post() {
        return id_post;
    }

    public void setId_post(Integer id_post) {
        this.id_post = id_post;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ProductDTO getDetail() {
        return detail;
    }

    public void setDetail(ProductDTO detail) {
        this.detail = detail;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getHasPromo() {
        return hasPromo;
    }

    public void setHasPromo(Boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Post toModel (){
        Post post = new Post();
        post.setUserId(getUserID());
        post.setId(getId_post());
        post.setDate(getDate());
        post.setProduct(getDetail().toModel());
        post.setCategory(getCategory());
        post.setPrice(getPrice());
        post.setHasPromo(getHasPromo());
        post.setDiscount(getDiscount());
        return post;
    }

    @Override
    public int compareTo(PostDTO o) {
        return getDate().compareTo(o.getDate());
    }
}
