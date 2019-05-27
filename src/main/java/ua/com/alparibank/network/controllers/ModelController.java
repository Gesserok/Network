package ua.com.alparibank.network.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.alparibank.network.model.Model;
import ua.com.alparibank.network.service.ModelService;

import java.util.List;

@RestController
public class ModelController {

    @Autowired
    private ModelService modelService;

    @RequestMapping(method = RequestMethod.GET, value = "/models")
    public List<Model> getAllModels() {
        return modelService.getAllModels();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/models/{id}")
    public Model getModelById(@PathVariable int id) {
        return modelService.getModelById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/models")
    public Model addModel(@RequestBody Model location) {
        return modelService.addModel(location);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/models/{id}")
    public Model updateModel(@PathVariable int id, @RequestBody Model location) {
        return modelService.updateModel(id, location);
    }
}
