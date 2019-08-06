package ua.com.alparibank.network.domain;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.com.alparibank.network.annotations.Profiling;
import ua.com.alparibank.network.model.NetworkDevice;
import ua.com.alparibank.network.service.ConfigReceiverService;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;


@Profiling
@Service ()
public class ConfigReciever implements IConfigReceiver{

    private static Logger logger = LoggerFactory.getLogger(ConfigReceiverService.class);

    public ConfigReciever(){
        System.out.println("CONSTRUCKTOR DONE");
    }
    @PostConstruct
    public void init(){
        System.out.println("INIT METHOD DONE");
    }

    @Override
    public int save(NetworkDevice device, String copyCommand) {
        logger.trace(copyCommand);

        int status = 0;
        try {

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            // Create a JSch session to connect to the server
            Session session = jsch.getSession("login", device.getIp(), 22);
            session.setPassword("password");
            session.setConfig(config);
            // Establish the connection
            session.connect();

            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(copyCommand);

            OutputStream out = channel.getOutputStream();
            channel.connect();

            if (!device.getHostname().contains("-fw-")) {
                out.write(("\n").getBytes());
                Thread.sleep(500);
                out.write(("\n").getBytes());
                Thread.sleep(500);
            }

            out.write("\n".getBytes());
            out.flush();
            channel.connect();
            InputStream in = channel.getInputStream();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    logger.info("Exit Status: "
                            + channel.getExitStatus());
                    status = channel.getExitStatus();
                    break;
                }
                Thread.sleep(1000);
            }
            channel.disconnect();
            session.disconnect();
        } catch (JSchException e) {
            logger.trace("ERROR host = " + device.getHostname() + " ip = " + device.getIp() + "\n" + e);
        }catch (Exception e) {
            logger.trace("ERROR host = " + device.getHostname() + " ip = " + device.getIp() + "\n" + e);
        }
        logger.info("DONE\n host = " + device.getHostname() + " ip = " + device.getIp() + "\n");
        return status;
    }
}
