package br.com.meli.desafiospring.dtos;

public class ErrorDTO {

    private String name;
    private String errorDetail;
    private Integer htttpStatusCode;

    public ErrorDTO() {
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHtttpStatusCode() {
        return htttpStatusCode;
    }

    public void setHtttpStatusCode(Integer htttpStatusCode) {
        this.htttpStatusCode = htttpStatusCode;
    }
}
