package ua.com.alparibank.network.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.alparibank.network.model.Model;
import ua.com.alparibank.network.repositories.ModelRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    public List<Model> getAllModels() {
        List<Model> locations = new ArrayList<>();
        modelRepository.findAll().forEach(locations::add);
        return locations;
    }

    public Model getModelById(int id) {
        Optional<Model> byId = modelRepository.findById(id);
        if (!byId.isPresent()) {
            throw new EntityNotFoundException("Model Not found");
        }
        return byId.get();
    }

    public Model addModel(Model location) {
        return modelRepository.save(location);
    }

    public Model updateModel(int id, Model location) {
        return modelRepository.save(location);
    }
}
