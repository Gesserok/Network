package ua.com.alparibank.network.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.alparibank.network.model.NetworkDevice;
import ua.com.alparibank.network.repositories.NetworkDeviceRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NetworkDeviceService {

    @Autowired
    private NetworkDeviceRepository networkDeviceRepository;

    public List<NetworkDevice> getAllNetworkDevices() {
        return new ArrayList<>(networkDeviceRepository.findAll());
    }

    public NetworkDevice getDeviceById(int id) {

        Optional<NetworkDevice> byId = networkDeviceRepository.findById(id);
        if (!byId.isPresent()) {
            throw new EntityNotFoundException("NetworkDevice Not found");
        }
        return byId.get();
    }

    public List<NetworkDevice> getNetworkDevicesByBranch(int branchId) {
        return networkDeviceRepository.findNetworkDevicesByBranchId(branchId);
    }
//
//    public NetworkDevice addDevice(NetworkDevice location) {
//        return networkDeviceRepository.saveStartupConfig(location);
//    }
//
//    public NetworkDevice updateDevice(int id, NetworkDevice location) {
//        return networkDeviceRepository.saveStartupConfig(location);
//    }
//
//    public List<NetworkDevice> getByModelId(int modelId) {
//        return new ArrayList<>(networkDeviceRepository.findByModelId(modelId));
//    }
//
//    public List<NetworkDevice> getByBranchId(int branchId) {
//        return new ArrayList<NetworkDevice>(networkDeviceRepository.findById(branchId));
//    }
//
//    public List<NetworkDevice> getByBranchId(int brancheId) {
//        return networkDeviceRepository.findByBranchId(brancheId);
//    }
//
//    public List<NetworkDevice> findByModelId(int id, int modelId) {
//        return networkDeviceRepository.findByModelId(modelId);
//    }

}
