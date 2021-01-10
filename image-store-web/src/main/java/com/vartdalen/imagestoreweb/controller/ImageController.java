package com.vartdalen.imagestoreweb.controller;
import com.vartdalen.imagestoreweb.model.Image;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vartdalen.imagestoreweb.service.ImageService;

import javax.validation.ValidationException;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @ResponseBody
    @GetMapping("")
    public ResponseEntity<List<Image>> get() { return new ResponseEntity<>(imageService.get(), HttpStatus.OK); }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<Image> get(@PathVariable long id) {
        return new ResponseEntity<>(imageService.get(id), HttpStatus.OK);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @ResponseBody
    @GetMapping("/latest")
    public ResponseEntity<Image> getLatest() {
        return new ResponseEntity<>(imageService
                .get()
                .stream()
                .max(Comparator.comparing(Image::getCreated))
                .get(),
                HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("")
    public ResponseEntity<Image> post(@RequestBody Image image) {
        return new ResponseEntity<>(imageService.post(image), HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@PathVariable long id, @RequestBody Image image) {
        if (image.getId() > -1) { throw new ValidationException("Value of field 'id' is invalid. Expected: null"); }
        imageService.put(id, image);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        imageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
