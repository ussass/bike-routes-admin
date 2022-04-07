package ru.trofimov.bikeroutesadmin.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.trofimov.bikeroutesadmin.exception.InvalidAuthorizationException;
import ru.trofimov.bikeroutesadmin.exception.InvalidConnectionException;
import ru.trofimov.bikeroutesadmin.model.Admin;
import ru.trofimov.bikeroutesadmin.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ConnectionServiceImpl implements ConnectionService {

    @Value("${url}")
    private String url;

    private String token;

    private final HttpClient httpClient;

    public ConnectionServiceImpl() {
        httpClient = HttpClientBuilder.create().build();
    }

    @Override
    public void authorization(Admin admin) {
        String responseString = null;
        try {
            HttpPost request = new HttpPost(url + "/login");
            StringEntity params = new StringEntity("{\"login\": \"" + admin.getLogin() + "\",\"password\":\"" + admin.getPassword() + "\"} ");
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

            HttpEntity entity = response.getEntity();
            responseString = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            throw new InvalidConnectionException("No connection to server");
        }

        if (responseString.contains("message")) {
            String[] split = cutFirstAndLast(responseString).split(",");
            String message = Arrays.stream(split).filter(s -> s.contains("message")).findFirst().get();
            throw new InvalidAuthorizationException(getValue(message));
        }

        token = getValue(cutFirstAndLast(responseString));
    }

    @Override
    public List<User> getUserList() {

        if (token == null) {
            throw new InvalidAuthorizationException("Invalid Token");
        }

        String responseString = null;

        try {
            HttpGet request = new HttpGet(url);
            request.addHeader("Authorization", "Bearer_" + token);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            responseString = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            throw new InvalidConnectionException("No connection to server");
        }

        List<User> userList = new ArrayList<>();
        String[] split = cutFirstAndLast(cutFirstAndLast(responseString)).split("},\\{");

        for (String element : split) {
            userList.add(new User(element));
        }
        return userList;
    }

    private String getValue(String string) {
        return cutFirstAndLast(string.split(":")[1]);
    }

    private String cutFirstAndLast(String string) {
        return string.substring(1, string.length() - 1);
    }
}
