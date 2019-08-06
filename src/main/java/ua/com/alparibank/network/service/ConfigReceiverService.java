package ua.com.alparibank.network.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.com.alparibank.network.domain.IConfigReceiver;
import ua.com.alparibank.network.repositories.NetworkDeviceRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class ConfigReceiverService {

    private static final String PATTERN = "yyyy-MM-dd-HH-mm-ss";
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT =
            new SimpleDateFormat(PATTERN, new Locale("alp", "ALP"));
    @Autowired
    private IConfigReceiver configReceiver;
    private static Logger logger = LoggerFactory.getLogger(ConfigReceiverService.class);
    @Autowired
    private NetworkDeviceRepository networkDeviceRepository;

    @Scheduled(cron = "0 0 1/12 * * ?")
    public void saveStartupConfig() {
        String time = SIMPLE_DATE_FORMAT.format(new Date());
        AtomicInteger status = new AtomicInteger();
        networkDeviceRepository.findAll().parallelStream().forEach(
                device -> {
                    String deviceHostname = device.getHostname();

                    if (!deviceHostname.contains("-fw-")) {
                        status.set(configReceiver.save(device, String.format("copy start ftp://localhost/%s/startup/%s-%s.cfg",
                                deviceHostname, deviceHostname, time)));
                    } else {
                        if (!deviceHostname.contains("-dc2-")) {
                            status.set(configReceiver.save(device, String.format("copy start ftp://login:password\nlocalhost\n\n\n%s/startup/%s-%s.cfg\n",
                                    deviceHostname, deviceHostname, time)));
                        } else {
                            status.set(configReceiver.save(device, String.format("copy outside start ftp://login:password\nlocalhost\n\n\n%s/startup/%s-%s.cfg\n",
                                    deviceHostname, deviceHostname, time)));
                        }
                    }

                    logger.info("Device " + deviceHostname + " saveStartupConfig() " + time + " with status = " + status);

                });
    }

    @Scheduled(cron = "0 0 1/12 * * ?")
    public void saveRunningConfig() {
        String time = SIMPLE_DATE_FORMAT.format(new Date());
        AtomicInteger status = new AtomicInteger();
        networkDeviceRepository.findAll().parallelStream().forEach(
                device -> {
                    String deviceHostname = device.getHostname();
                    if (!deviceHostname.contains("-fw-")) {
                        status.set(configReceiver.save(device, String.format("copy running ftp://localhost/%s/running/%s-%s.cfg",
                                deviceHostname, deviceHostname, time)));
                    } else {
                        if (!deviceHostname.contains("-dc2-")) {
                            status.set(configReceiver.save(device, String.format("copy run ftp://login:password\n\nlocalhost\n\n\n%s/running/%s-%s.cfg\n",
                                    deviceHostname, deviceHostname, time)));
                        } else {
                            status.set(configReceiver.save(device, String.format("copy outside run ftp://login:password\n\nlocalhost\n\n\n%s/running/%s-%s.cfg\n",
                                    deviceHostname, deviceHostname, time)));
                        }
                    }
                    logger.info("Device " + deviceHostname + " saveStartupConfig() " + time + " with status = " + status);
                });
    }
}
