package ua.com.alparibank.network.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alparibank.network.model.NetworkDevice;
import ua.com.alparibank.network.service.NetworkDeviceService;

import java.util.List;

@RestController
public class NetworkDeviceController {

    @Autowired
    private NetworkDeviceService networkDeviceService;

    @RequestMapping(method = RequestMethod.GET, value = "/devices")
    public List<NetworkDevice> getAllNetworkDevices() {
        return networkDeviceService.getAllNetworkDevices();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/devices/{id}")
    public NetworkDevice getNetworkDevice(@PathVariable int id) {
        return networkDeviceService.getDeviceById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/branches/{branchId}/devices")
    public List<NetworkDevice> getAllNetworkDevices(@PathVariable int branchId) {
        return networkDeviceService.getNetworkDevicesByBranch(branchId);
    }


    //

//

//
//    @RequestMapping(method = RequestMethod.POST, value = "/branches/{branchId}/devices")
//    public NetworkDevice addNetworkDevice(@RequestBody NetworkDevice device, @PathVariable int branchId){
//        return networkDeviceService.addDevice(device);
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, value = "branches/{brancheId}/devices/{id}")
//    public NetworkDevice findByBranchId(@PathVariable int brancheId, @RequestBody NetworkDevice device) {
//        return networkDeviceService.updateDevice(brancheId, device);
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/models/{modelId}/devices")
//    public List<NetworkDevice> getNetworkDevicesByModelId(@PathVariable int modelId){
//        return networkDeviceService.getByModelId(modelId);
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/branches/{branchId}/devices")
//    public List<NetworkDevice> getNetworkDevicesByBranchId(@PathVariable int branchId){
//        return networkDeviceService.getByBranchId(branchId);
//    }

    //
//    @RequestMapping(method = RequestMethod.GET, value = "/models/{modelId}/devices")
//    public List<NetworkDevice> getNetworkDevicesByModelId(@PathVariable int modelId){
//        return networkDeviceService.getByBranchId(modelId);
//    }

}
