package ua.com.alparibank.network.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.alparibank.network.model.Model;

public interface ModelRepository extends JpaRepository<Model, Integer> {

}
