package ru.msaggik.api.consumer;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Consumer {
    public static void main(String[] args) {
        // объект для запросов к удалённым сервисам
        RestTemplate restTemplate = new RestTemplate();

        // задание url стороннего сервиса для GET запроса
        String urlGet = "https://reqres.in/api/users/2";
        // GET запрос на сторонний сервис
        String responseGet = restTemplate.getForObject(urlGet, String.class);

        // задание url стороннего сервиса для POST запроса
        String urlPost = "https://reqres.in/api/users";
        // данные для POST запроса
        Map<String, String> jsonToSend = new HashMap<>();
        jsonToSend.put("name", "Test name");
        jsonToSend.put("job", "Test job");
        // упаковка данных для POST запроса в формат JSON
        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonToSend);
        // POST запрос на сторонний сервис
        String responsePost = restTemplate.postForObject(urlPost, request, String.class);

        System.out.println(responseGet);
        System.out.println(responsePost);
    }
}
