package ua.com.alparibank.network.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.alparibank.network.model.Branch;
import ua.com.alparibank.network.repositories.BranchRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public List<Branch> getAllBranchs() {
        return new ArrayList<>(branchRepository.findAll());
    }

    public Branch getBranchById(int id) {
        Optional<Branch> byId = branchRepository.findById(id);
        if (!byId.isPresent()) {
            throw new EntityNotFoundException("Branch Not found");
        }
        return byId.get();
    }

    public Branch addBranch(Branch location) {
        return branchRepository.save(location);
    }

    public Branch updateBranch(int id, Branch location) {
        return branchRepository.save(location);
    }

    public List<Branch> getAllBranchesByLocationId(int locationId) {
        return branchRepository.findByLocationId(locationId);
    }
}
