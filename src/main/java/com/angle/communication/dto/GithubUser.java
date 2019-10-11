package com.angle.communication.dto;

import lombok.Data;

/**
 * Created by codedrinker on 2019/9/20
 */
@Data
public class GithubUser {
    private  String name;
    private Long id;
    private  String bio;
    private String avatar_url;

    @Override
    public String toString() {
        return "GithubUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }}


