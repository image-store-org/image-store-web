package com.vartdalen.image.store.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.vartdalen.image.store.model.ModelTemplate;
import com.vartdalen.image.store.service.ModelTemplateService;

import java.util.List;

@RestController
@RequestMapping("/modelTemplates")
public class ModelTemplateController {

    private final ModelTemplateService modelTemplateService;

    @Autowired
    public ModelTemplateController(ModelTemplateService modelTemplateService) {
        this.modelTemplateService = modelTemplateService;
    }

    @RequestMapping("/helloWorld")
    public @ResponseBody String helloWorld() {
        return modelTemplateService.helloWorld();
    }

    @GetMapping("/get")
    public @ResponseBody List<ModelTemplate> getModelTemplates() {
        return modelTemplateService.getModelTemplates();
    }

    @PostMapping("/post")
    public String post(@ModelAttribute("modelTemplate") ModelTemplate modelTemplate) {
        modelTemplateService.postModelTemplate(modelTemplate);
        return "redirect:/";
    }

    @PutMapping("/put")
    public String put(@ModelAttribute("modelTemplate") ModelTemplate modelTemplate) {
        modelTemplateService.putModelTemplate(modelTemplate.getId(), modelTemplate);
        return "redirect:/";
    }

    @DeleteMapping("/delete/{idModelTemplate}")
    public String delete(@PathVariable("idModelTemplate") String idModelTemplate) {
            modelTemplateService.deleteModelTemplate(Long.parseLong(idModelTemplate));
        return "redirect:/";
    }
}
