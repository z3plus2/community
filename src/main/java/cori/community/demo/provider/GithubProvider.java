package cori.community.demo.provider;

import com.alibaba.fastjson.JSON;
import cori.community.demo.dto.AccessToken;
import cori.community.demo.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 3plus2
 * @data 2019/10/2 13 10
 * @desercription
 */
@Component
public class GithubProvider {
    public String getAccessToken(AccessToken accessToken) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        String url = "https://github.com/login/oauth/access_token";
        String json = JSON.toJSONString(accessToken);
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            String[] split=string.split("&");
            String tokenstring= split[0].split("=")[1];
            return tokenstring;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getGithubUser(String token) {
        OkHttpClient client = new OkHttpClient();

        String url = "https://api.github.com/user?access_token=" + token;

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser gitUser = JSON.parseObject(string, GithubUser.class);
            return gitUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }

}
