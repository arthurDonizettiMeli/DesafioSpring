package br.com.meli.desafiospring.dtos;

public class UserFollowerDTO implements Comparable<UserFollowerDTO> {

    private Integer userId;
    private String username;

    public UserFollowerDTO() {
    }

    public UserFollowerDTO(Integer userId, String username) {
        this.userId = userId;
        this.username = username;
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

    @Override
    public int compareTo(UserFollowerDTO o) {
        return getUsername().compareTo(o.getUsername());
    }
}
