package ua.com.alparibank.network.domain;

import ua.com.alparibank.network.model.NetworkDevice;

public interface IConfigReceiver {
    int save(NetworkDevice device, String copyCommand);
}
