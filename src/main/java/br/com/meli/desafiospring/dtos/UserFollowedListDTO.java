package br.com.meli.desafiospring.dtos;

import java.util.List;

public class UserFollowedListDTO {

    private Integer userId;
    private String userName;
    private List<UserFollowerDTO> followed;

    public UserFollowedListDTO() {
    }

    public UserFollowedListDTO(Integer userId, String userName, List<UserFollowerDTO> followed) {
        this.userId = userId;
        this.userName = userName;
        this.followed = followed;
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

    public List<UserFollowerDTO> getFollowed() {
        return followed;
    }

    public void setFollowed(List<UserFollowerDTO> followed) {
        this.followed = followed;
    }
}
