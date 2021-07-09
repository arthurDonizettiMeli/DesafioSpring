package br.com.meli.desafiospring.dtos;

import br.com.meli.desafiospring.models.Post;
import br.com.meli.desafiospring.models.Product;
import br.com.meli.desafiospring.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostsFromUserDTO {
  private Integer userId;
  private String userName;
  private List<PostFromUserDTO> posts;

  public PostsFromUserDTO() {
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public List<PostFromUserDTO> getPosts() {
    return posts;
  }

  public void setPosts(List<PostFromUserDTO> posts) {
    this.posts = posts;
  }

  public User toUser(){
    User user = new User();
    user.setId(this.userId);
    user.setUsername(this.userName);
    return user;
  }

  public List<Post> toPosts() {
    List<Post> postList = new ArrayList<>();
    this.getPosts().forEach(p -> {
      Post post = new Post();
      post.setId(p.getId_post());
      post.setDate(p.getDate());
      post.setCategory(p.getCategory());
      post.setDiscount(p.getDiscount());
      post.setHasPromo(p.getHasPromo());
      post.setPrice(p.getPrice());

      Product product = new Product();
      product.setId(p.getDetail().getProduct_id());
      product.setName(p.getDetail().getProductName());
      product.setType(p.getDetail().getType());
      product.setBrand(p.getDetail().getBrand());
      product.setColor(p.getDetail().getColor());
      product.setNotes(p.getDetail().getNotes());

      post.setProduct(product);

      postList.add(post);
    });

    return postList;
  }

  public PostsFromUserDTO toModel(User user, List<Post> posts) {
    PostsFromUserDTO dto = new PostsFromUserDTO();
    dto.setUserId(user.getId());
    dto.setUserName(user.getUsername());

    List<PostFromUserDTO> postListDTO = new ArrayList<>();
    posts.forEach(p -> {
      PostFromUserDTO postDTO = new PostFromUserDTO();
      postDTO.setId_post(p.getId());
      postDTO.setDate(p.getDate());
      postDTO.setCategory(p.getCategory());
      postDTO.setPrice(p.getPrice());
      postDTO.setHasPromo(p.getHasPromo());
      postDTO.setDiscount(p.getDiscount());

      ProductFromUserDTO productDTO = new ProductFromUserDTO();
      productDTO.setProduct_id(p.getProduct().getId());
      productDTO.setProductName(p.getProduct().getName());
      productDTO.setType(p.getProduct().getType());
      productDTO.setBrand(p.getProduct().getBrand());
      productDTO.setColor(p.getProduct().getColor());
      productDTO.setNotes(p.getProduct().getNotes());
      postDTO.setDetail(productDTO);

      postListDTO.add(postDTO);
    });
    dto.setPosts(postListDTO);

    return dto;
  }


  private class PostFromUserDTO {
    private Integer id_post;
    private Date date;
    private ProductFromUserDTO detail;
    private Integer category;
    private Double price;
    private Double discount;
    private Boolean hasPromo;

    public PostFromUserDTO() {
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

    public ProductFromUserDTO getDetail() {
      return detail;
    }

    public void setDetail(ProductFromUserDTO detail) {
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

    public Double getDiscount() {
      return discount;
    }

    public void setDiscount(Double discount) {
      this.discount = discount;
    }

    public Boolean getHasPromo() {
      return hasPromo;
    }

    public void setHasPromo(Boolean hasPromo) {
      this.hasPromo = hasPromo;
    }
  }

  private class ProductFromUserDTO {
    private Integer product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

    public ProductFromUserDTO() {
    }

    public Integer getProduct_id() {
      return product_id;
    }

    public void setProduct_id(Integer product_id) {
      this.product_id = product_id;
    }

    public String getProductName() {
      return productName;
    }

    public void setProductName(String productName) {
      this.productName = productName;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getBrand() {
      return brand;
    }

    public void setBrand(String brand) {
      this.brand = brand;
    }

    public String getColor() {
      return color;
    }

    public void setColor(String color) {
      this.color = color;
    }

    public String getNotes() {
      return notes;
    }

    public void setNotes(String notes) {
      this.notes = notes;
    }
  }
}


