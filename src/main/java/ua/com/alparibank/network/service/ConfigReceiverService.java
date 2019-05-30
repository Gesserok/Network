package ua.com.alparibank.network.service;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.log4j2.Log4J2LoggingSystem;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.com.alparibank.network.domain.ConfigReciever;
import ua.com.alparibank.network.model.NetworkDevice;
import ua.com.alparibank.network.repositories.NetworkDeviceRepository;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

@Service
public class ConfigReceiverService {

    @Autowired
    private NetworkDeviceRepository networkDeviceRepository;
    private static ConfigReciever configReciever = new ConfigReciever();
    private static final String PATTERN = "yyyy-MM-dd-HH-mm-ss";
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT =
            new SimpleDateFormat(PATTERN, new Locale("alp", "ALP"));
    private static Logger logger = LoggerFactory.getLogger(ConfigReceiverService.class);


    @Scheduled(cron = "0 05 23 * * ?")
    public void saveStartupConfig(){

        networkDeviceRepository.findAll().parallelStream().forEach(
                device -> configReciever.save(device, String.format("copy start ftp://10.41.8.13/%s/startup/%s-%s.cfg",
                device.getHostname(), device.getHostname(), SIMPLE_DATE_FORMAT.format(new Date()))));
    }

    @Scheduled(cron = "0 0 23 * * ?")
    public void saveRunningConfig(){

        networkDeviceRepository.findAll().parallelStream().forEach(
                device -> configReciever.save(device, String.format("copy running ftp://10.41.8.13/%s/running/%s-%s.cfg",
                        device.getHostname(), device.getHostname(), SIMPLE_DATE_FORMAT.format(new Date()))));
    }
}
