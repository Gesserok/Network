package ua.com.alparibank.network.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.alparibank.network.model.NetworkDevice;

import java.util.List;

public interface NetworkDeviceRepository extends JpaRepository<NetworkDevice, Integer> {
    List<NetworkDevice> findNetworkDevicesByBranchId(int branchId);


    //    List<NetworkDevice> findByModelId(int modelId);
}
