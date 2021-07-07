package br.com.meli.desafiospring.dtos;

import br.com.meli.desafiospring.models.Post;

import java.util.Date;

public class PostDTO {

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

    public Post toModel (PostDTO postDTO){
        Post post = new Post();
        post.setUserId(postDTO.getUserID());
        post.setId(postDTO.getId_post());
        post.setDate(postDTO.getDate());
        post.setProduct(postDTO.getDetail().toModel(postDTO.getDetail()));
        post.setCategory(postDTO.getCategory());
        post.setPrice(postDTO.getPrice());
        post.setHasPromo(postDTO.getHasPromo());
        post.setDiscount(postDTO.getDiscount());
        return post;
    }
}
