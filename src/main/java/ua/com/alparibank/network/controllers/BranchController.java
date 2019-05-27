package ua.com.alparibank.network.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.alparibank.network.model.Branch;
import ua.com.alparibank.network.service.BranchService;

import java.util.List;

@RestController
public class BranchController {

    @Autowired
    private BranchService branchService;

    @RequestMapping(method = RequestMethod.GET, value = "/branches")
    public List<Branch> getAllBranches(){
        return branchService.getAllBranchs();
    }

    @RequestMapping(method = RequestMethod.GET, value =  "/branches/{id}")
    public Branch getBranchById(@PathVariable int id){
        return branchService.getBranchById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/branches")
    public Branch addBranch(@RequestBody Branch location){
        return branchService.addBranch(location);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/branches/{id}")
    public Branch updateBranch(@PathVariable int id, @RequestBody Branch location) {
        return branchService.updateBranch(id, location);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/locations/{locationId}/branches")
    public List<Branch> getBranchesByLocationId(@PathVariable int locationId){
        return branchService.getAllBranchesByLocationId(locationId);
    }
}
