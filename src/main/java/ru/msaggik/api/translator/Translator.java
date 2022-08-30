package ru.msaggik.api.translator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Translator {
    public static void main(String[] args) throws JsonProcessingException {
        // получение с консоли текста на русском языке и перевод его на английский язык с помощью REST API
        System.out.println("Для перевода на английский язык введите предложение на русском языке: ");
        // сканирование введённого в консоль текста
        Scanner scanner = new Scanner(System.in);
        String sentenceToTranslate = scanner.nextLine();

        // объект для запросов к удалённому сервису яндекс переводчика
        RestTemplate restTemplate = new RestTemplate();

        // задание url стороннего сервиса для POST запроса
        String urlPost = "https://translate.api.cloud.yandex.net/translate/v2/translate";

        // заголовки для POST запроса
        HttpHeaders headers = new HttpHeaders();
        // указание передачи JSON объекта
        headers.setContentType(MediaType.APPLICATION_JSON);
        // авторизация (токен получается при регистрации в яндексе)
        headers.add("Authorization", "Bearer " + "token");

        // данные для POST запроса
        Map<String, String> jsonData = new HashMap<>();
        jsonData.put("folderId", "key"); // ключ folderId получается при регистрации в яндексе
        jsonData.put("targetLanguageCode", "en"); // указание языка для перевода
        jsonData.put("text", "[" + sentenceToTranslate + "]"); // указание текста для перевода
        // упаковка данных для POST запроса в формат JSON
        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonData, headers);
//        // POST запрос на сторонний сервис
//        String responsePost = restTemplate.postForObject(urlPost, request, String.class);
//
//        System.out.println(responsePost); // вывод в консоль JSON объекта
//
//        // парсинг полученного JSON объекта с помощью библиотеки Jackson
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode obj = mapper.readTree(responsePost);
//
//        // вывод в консоль удобного перевода
//        System.out.println("Перевод: " + obj.get("translations").get(0).get("text"));

        // POST запрос на сторонний сервис и парсинг полученного JSON объекта с помощью библиотеки Jackson
        YandexResponse responsePost = restTemplate.postForObject(urlPost, request, YandexResponse.class);

        // вывод в консоль удобного перевода
        System.out.println("Перевод: " + responsePost.getTranslations().get(0).getText());
    }
}
