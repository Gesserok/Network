package ua.com.alparibank.network.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.alparibank.network.model.Branch;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Integer> {

    List<Branch> findByLocationId(int locationAddrId);

}
