package br.com.meli.desafiospring.dtos;


import br.com.meli.desafiospring.models.Product;

import java.util.Date;

public class ProductDTO {

    private Integer product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

    public ProductDTO() {
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

    public Product toModel(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getProduct_id());
        product.setName(productDTO.getProductName());
        product.setType(productDTO.getType());
        product.setBrand(productDTO.getBrand());
        product.setColor(productDTO.getColor());
        product.setNotes(productDTO.getNotes());
        return product;
    }
}
