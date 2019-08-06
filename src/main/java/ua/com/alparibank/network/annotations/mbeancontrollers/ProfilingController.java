package ua.com.alparibank.network.annotations.mbeancontrollers;

import org.springframework.stereotype.Service;

@Service
public class ProfilingController implements ProfilingControllerMBean {

    private boolean enabled;

    public ProfilingController() {
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
