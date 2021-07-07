package br.com.meli.desafiospring.dtos;

public class PrductCountPromoDTO {

    private Integer userId;
    private String username;
    private Integer promoProductsCount;

    public PrductCountPromoDTO() {
    }

    public PrductCountPromoDTO(Integer userId, String username, Integer promoProductsCount) {
        this.userId = userId;
        this.username = username;
        this.promoProductsCount = promoProductsCount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPromoProductsCount() {
        return promoProductsCount;
    }

    public void setPromoProductsCount(Integer promoProductsCount) {
        this.promoProductsCount = promoProductsCount;
    }
}
