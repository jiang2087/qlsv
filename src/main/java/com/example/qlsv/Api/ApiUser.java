package com.example.qlsv.Api;

import com.example.qlsv.model.User;

import java.net.URI;
import java.util.List;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiUser {

    private static final String API_URL = "http://localhost:8080/api/v1";

    public List<User> getSinhVienList() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + "/users"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();

        ObjectMapper objectMapper = new ObjectMapper();
        // Chuyển đổi JSON thành danh sách sinh viên
        return objectMapper.readValue(responseBody, objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
    }
}
