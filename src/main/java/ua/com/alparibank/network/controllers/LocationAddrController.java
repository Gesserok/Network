package ua.com.alparibank.network.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.alparibank.network.model.LocationAddr;
import ua.com.alparibank.network.service.LocationAddrService;

import java.util.List;

@RestController
public class LocationAddrController {

    @Autowired
    private LocationAddrService locationAddrService;

    @RequestMapping(method = RequestMethod.GET, value = "/locations")
    public List<LocationAddr> getAllLocationAddrs() {
        return locationAddrService.getAllLocationAddrs();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/locations/{id}")
    public LocationAddr getLocationAddrById(@PathVariable int id) {
        return locationAddrService.getLocationAddrById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/locations")
    public LocationAddr addLocationAddr(@RequestBody LocationAddr location) {
        return locationAddrService.addLocationAddr(location);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/locations/{id}")
    public LocationAddr updateLocationAddr(@PathVariable int id, @RequestBody LocationAddr location) {
        return locationAddrService.updateLocationAddr(id, location);
    }
}
