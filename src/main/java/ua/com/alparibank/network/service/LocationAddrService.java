package ua.com.alparibank.network.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.alparibank.network.model.LocationAddr;
import ua.com.alparibank.network.repositories.LocationAddrRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationAddrService {

    @Autowired
    private LocationAddrRepository locationAddrRepository;

    public List<LocationAddr> getAllLocationAddrs() {
        return new ArrayList<>(locationAddrRepository.findAll());
    }

    public LocationAddr getLocationAddrById(int id) {
        Optional<LocationAddr> byId = locationAddrRepository.findById(id);
        if (!byId.isPresent()) {
            throw new EntityNotFoundException("LocationAddr Not found");
        }
        return byId.get();
    }

    public LocationAddr addLocationAddr(LocationAddr location) {
        return locationAddrRepository.save(location);
    }

    public LocationAddr updateLocationAddr(int id, LocationAddr location) {
        return locationAddrRepository.save(location);
    }
}
