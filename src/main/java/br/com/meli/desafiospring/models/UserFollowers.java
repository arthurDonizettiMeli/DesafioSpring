package br.com.meli.desafiospring.models;

import br.com.meli.desafiospring.dtos.UserFollowerDTO;

import javax.persistence.*;

@Entity
@Table(name = "user_followers")
public class UserFollowers {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer followedId;
    private Integer followerId;

    public UserFollowers() {
    }

    public UserFollowers(Integer followedId, Integer followerId) {
        this.followedId = followedId;
        this.followerId = followerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFollowedId() {
        return followedId;
    }

    public void setFollowedId(Integer followedId) {
        this.followedId = followedId;
    }

    public Integer getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Integer followerId) {
        this.followerId = followerId;
    }
}
