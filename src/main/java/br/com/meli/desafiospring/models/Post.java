package br.com.meli.desafiospring.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
  private LocalDate date;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;

  @NotNull
  private Integer category;

  @NotNull
  private Double price;

  @NotNull
  private Boolean hasPromo;

  @NotNull
  private Double discount;

  public Post() {
  }

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

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
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
}
