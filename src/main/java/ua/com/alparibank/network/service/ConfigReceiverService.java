package ua.com.alparibank.network.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.com.alparibank.network.domain.ConfigReciever;
import ua.com.alparibank.network.repositories.NetworkDeviceRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class ConfigReceiverService {

    private static final String PATTERN = "yyyy-MM-dd-HH-mm-ss";
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT =
            new SimpleDateFormat(PATTERN, new Locale("alp", "ALP"));
    private static ConfigReciever configReciever = new ConfigReciever();
    private static Logger logger = LoggerFactory.getLogger(ConfigReceiverService.class);
    @Autowired
    private NetworkDeviceRepository networkDeviceRepository;

    @Scheduled(cron = "0 0 1/12  * * ?")
    public void saveStartupConfig() {
        String time = SIMPLE_DATE_FORMAT.format(new Date());

        networkDeviceRepository.findAll().parallelStream().forEach(
                device -> {
                    String deviceHostname = device.getHostname();
                    logger.info("Device " + deviceHostname + " saveStartupConfig() " + time);

                    if (!deviceHostname.contains("-fw-")) {
                        configReciever.save(device, String.format("copy start ftp://10.41.8.13/%s/startup/%s-%s.cfg",
                                deviceHostname, deviceHostname, time));
                    } else {
                        configReciever.save(device, String.format("copy start ftp://ciscogit :password\n10.41.8.13\n\n\n%s/startup/%s-%s.cfg\n",
                                deviceHostname, deviceHostname, time));
                    }
                });
    }

    @Scheduled(cron = "0 0 1/12 * * ?")
    public void saveRunningConfig() {
        String time = SIMPLE_DATE_FORMAT.format(new Date());

        networkDeviceRepository.findAll().parallelStream().forEach(
                device -> {
                    String deviceHostname = device.getHostname();
                    logger.info("Device " + device.getHostname() + " saveRunningConfig() " + time);
                    if (!deviceHostname.contains("-fw-")) {
                        configReciever.save(device, String.format("copy running ftp://10.41.8.13/%s/running/%s-%s.cfg",
                                deviceHostname, deviceHostname, time));
                    } else {
                        configReciever.save(device, String.format("copy running ftp://cisco:password\n\n10.41.8.13\n\n\n%s/running/%s-%s.cfg\n",
                                deviceHostname, deviceHostname, time));
                    }
                });
    }
}
