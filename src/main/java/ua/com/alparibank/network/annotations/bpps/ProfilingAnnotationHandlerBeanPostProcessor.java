package ua.com.alparibank.network.annotations.bpps;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;
import ua.com.alparibank.network.annotations.Profiling;
import ua.com.alparibank.network.annotations.mbeancontrollers.ProfilingController;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;


@Service
public class ProfilingAnnotationHandlerBeanPostProcessor implements BeanPostProcessor {

    private Map<String, Class> map = new HashMap<>();
    private ProfilingController controller = new ProfilingController();

    public ProfilingAnnotationHandlerBeanPostProcessor () throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {

        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        mBeanServer.registerMBean(controller, new ObjectName("profiling", "name", "controller"));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Profiling.class)) {
            System.out.println("BEFORE INITIALIZATION DONE");
            map.put(beanName, beanClass);
        }


        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {


        Class beanClass = map.get(beanName);
        if (beanClass != null) {
            System.out.println("AFTER INITIALIZATION DONE");
            return Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (controller.isEnabled()) {
                        System.out.printf("Start profiling method %s from class %s", method.getName(), beanClass.getCanonicalName());

                        long startTime = System.currentTimeMillis();

                        Object retVal = method.invoke(bean, args);

                        long endTime = System.currentTimeMillis();

                        System.out.printf("Method %s from class %s WAS EXECUTING FOR %s nanos", method, beanClass, endTime - startTime);

                        System.out.printf("End profiling method %s from class %s", method.getName(), beanClass.getCanonicalName());
                        return retVal;
                    } else {
                        return method.invoke(bean, args);
                    }
                }
            });
        }
        return bean;
    }
}
