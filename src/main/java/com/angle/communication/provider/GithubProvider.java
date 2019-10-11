package com.angle.communication.provider;

import com.alibaba.fastjson.JSON;
import com.angle.communication.dto.AccessTokenDTO;
import com.angle.communication.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * created by codeteinker on 2019/9/20
 */
@Component
//把当前的类初始化spring容器的上下文 不需要实列化他的对象
// （main方法 下new一个对象）
public class GithubProvider {
    public String getAcccessToken(AccessTokenDTO accessTokenDTO) {
        //调用access_token 传入accesstokendto//code参数  获取access_token的过程
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {

            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            //access_token=0f557625155fdff0f696ff33d3c7128cc4547545&scope=user&token_type=bearer
            //按 &分割得到第0位在根据=分割得到第一位获得access_token的码
            return token;
            //System.out.println(string);
            //返回的是 access_token字符串
            //return string;
        } catch (Exception e) {
            e.printStackTrace();
        }
    return null;
    }
    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+ accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            //把String的json的对象自动转换成java的类对象
            return githubUser;
        } catch (IOException e) {
        }
        return null;
    }



}