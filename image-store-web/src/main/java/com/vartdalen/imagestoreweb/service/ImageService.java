package com.vartdalen.imagestoreweb.service;
import com.vartdalen.imagestoreweb.factory.HttpEntityFactory;
import com.vartdalen.imagestoreweb.model.Image;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ImageService {

    @Value("http://${database.address}:${database.port}/images/")
    private String BASE_URL;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Image> get() {
        return Arrays
            .stream(Objects.requireNonNull(restTemplate.getForObject(BASE_URL, Image[].class)))
            .collect(Collectors.toList());
    }

    public Image get(long id) {
        return restTemplate.getForObject(BASE_URL+id, Image.class);
    }

    public Image post(Image image) {
        return restTemplate
            .postForEntity(BASE_URL, HttpEntityFactory.createPost(image), Image.class)
            .getBody();
    }

    public void put(long id, Image image) { restTemplate.put(BASE_URL+id, image); }

    public void delete(long id) {
        restTemplate.delete(BASE_URL+id);
    }
}
