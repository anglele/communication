package com.angle.communication.dto;

import lombok.Data;

/**
 * creates by codedrinker on 2019/9/20
 */
@Data
public class AccessTokenDTO
{
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    public String state;


//调用access_token 传入这些参数  获取access_token的过程
}
