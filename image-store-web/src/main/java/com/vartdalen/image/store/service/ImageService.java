package com.vartdalen.image.store.service;
import com.vartdalen.image.store.model.Image;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ImageService {

    @Value("http://${server.address}:${server.port}/database") String BASE_URL;
    private final RestTemplate restTemplate = new RestTemplate();

    public String helloWorld() {
        return "Hello, World";
    }

    public List<Image> get() {
        return Arrays
                .stream(Objects.requireNonNull(restTemplate.getForObject(BASE_URL, Image[].class)))
                .collect(Collectors.toList());
    }

    public Image get(long id) {
        return restTemplate.getForObject(BASE_URL+"/"+id, Image.class);
    }

    public Image post(Image image) {
        ResponseEntity<Image> response;
        response = restTemplate.postForEntity(BASE_URL, image, Image.class);
        return response.getBody();
    }

    public void put(long id, Image image) { restTemplate.put(BASE_URL+"/"+id, image); }

    public void delete(long id) {
//        cascading (otherModelTemplate)
//        List<OtherModelTemplate> otherModelTemplateList = otherModelTemplateService.getAllOtherModelTemplates();
//        for(OtherModelTemplate otherModelTemplate: otherModelTemplateList) {
//            if (otherModelTemplate.getIdModelTemplate() == id) {
//                otherModelTemplateService.deleteOtherModelTemplateById(otherModelTemplate.getId());
//            }
//        }
        restTemplate.delete(BASE_URL+"/"+id);
    }
}
