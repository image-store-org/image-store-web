package com.vartdalen.image.store.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.vartdalen.image.store.model.Image;
import com.vartdalen.image.store.service.ImageService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(
            value = "/helloWorld",
            method = RequestMethod.GET
    )
    public @ResponseBody String helloWorld() {
        return imageService.helloWorld();
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.GET
    )
    public @ResponseBody List<Image> getImages() {
        return imageService.get();
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET
    )
    public @ResponseBody Image getImage(@PathVariable("id") String id) {
        return imageService.get(Long.parseLong(id));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @RequestMapping(
            value = "/latest",
            method = RequestMethod.GET
    )
    public @ResponseBody Image getImage() {
        return imageService
                .get()
                .stream()
                .min(Comparator.comparing(Image::getCreated))
                .get();
    }

    @PostMapping("/")
    public String post(@ModelAttribute("image") Image image) {
        imageService.post(image);
        return "redirect:/";
    }

    @PutMapping("/")
    public String put(@ModelAttribute("image") Image image) {
        imageService.put(image.getId(), image);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String id) {
            imageService.delete(Long.parseLong(id));
        return "redirect:/";
    }
}
