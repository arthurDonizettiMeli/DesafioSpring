package br.com.meli.desafiospring.dtos;


import br.com.meli.desafiospring.models.Product;

import javax.validation.constraints.NotNull;

public class ProductDTO {

    private Integer product_id;

    @NotNull
    private String productName;

    @NotNull
    private String type;

    @NotNull
    private String brand;

    @NotNull
    private String color;

    @NotNull
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

    public Product toModel() {
        Product product = new Product();
        product.setId(getProduct_id());
        product.setName(getProductName());
        product.setType(getType());
        product.setBrand(getBrand());
        product.setColor(getColor());
        product.setNotes(getNotes());
        return product;
    }
}
