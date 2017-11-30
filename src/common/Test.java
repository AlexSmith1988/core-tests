package common;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Proxy;

/**
 * Created by AIKuznetsov on 21.11.2017.
 */
public class Test implements TestMBean, TestProxy {

    static String message;

    static Test test = new Test();

    @Override
    public void setMessage(String message) {
        Test.message = message;
    }

    @Override
    public void printMe() {
        System.out.println("Message: " + message);
    }

    public static void main(String[] args) throws Exception {
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        platformMBeanServer.registerMBean(test, new ObjectName("testMBean", "name42", "MyTestMBean"));

        TestProxy test = (TestProxy) Proxy.newProxyInstance(Test.class.getClassLoader(), Test.class.getInterfaces(), (proxy, method, args1) -> {
            Object invoke = method.invoke(Test.test, args1);
            System.out.println("Proxy works, haha");
            return invoke;
        });

        while (true) {
            test.printMe();
            Thread.sleep(1000);
        }
    }
}
