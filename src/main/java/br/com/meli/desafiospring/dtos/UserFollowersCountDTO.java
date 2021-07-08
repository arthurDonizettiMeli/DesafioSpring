package br.com.meli.desafiospring.dtos;

public class UserFollowersCountDTO implements Comparable<UserFollowersCountDTO> {

    private Integer userId;
    private String userName;
    private Integer followers_count;

    public UserFollowersCountDTO() {
    }

    public UserFollowersCountDTO(Integer userId, String userName, Integer followers_count) {
        this.userId = userId;
        this.userName = userName;
        this.followers_count = followers_count;
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

    public Integer getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(Integer followers_count) {
        this.followers_count = followers_count;
    }

    @Override
    public int compareTo(UserFollowersCountDTO u) {
        return this.followers_count.compareTo(u.followers_count);
    }
}
