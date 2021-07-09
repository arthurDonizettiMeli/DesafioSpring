package br.com.meli.desafiospring.models;

import br.com.meli.desafiospring.dtos.PostDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer userId;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    public Post() {
    }

    @NotNull
    private Integer category;

    @NotNull
    private Double price;

    @NotNull
    private Boolean hasPromo;

    @NotNull
    private Double discount;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product detail) {
        this.product = detail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public PostDTO toDTO() {
        PostDTO postDTO = new PostDTO();
        postDTO.setUserID(userId);
        postDTO.setId_post(getId());
        postDTO.setDate(getDate());
        postDTO.setDetail(getProduct().toDTO());
        postDTO.setCategory(getCategory());
        postDTO.setPrice(getPrice());
        postDTO.setHasPromo(getHasPromo());
        postDTO.setDiscount(getDiscount());
        return postDTO;
    }

}
