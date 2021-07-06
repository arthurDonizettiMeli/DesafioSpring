package br.com.meli.desafiospring.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

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

  private List<Product> detail;

  @OneToMany(targetEntity=Product.class, mappedBy="id",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
  public List<Product> getDetail() {
    return detail;
  }

  public void setDetail(List<Product> detail) {
    this.detail = detail;
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
}
