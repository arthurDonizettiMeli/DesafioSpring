package br.com.meli.desafiospring.dtos;

import java.util.List;

public class UserFollowersListDTO {

    private Integer userId;
    private String userName;
    private List<UserFollowerDTO> followers;

    public UserFollowersListDTO() {
    }

    public UserFollowersListDTO(Integer userId, String userName, List<UserFollowerDTO> followers) {
        this.userId = userId;
        this.userName = userName;
        this.followers = followers;
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

    public List<UserFollowerDTO> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserFollowerDTO> followers) {
        this.followers = followers;
    }
}
