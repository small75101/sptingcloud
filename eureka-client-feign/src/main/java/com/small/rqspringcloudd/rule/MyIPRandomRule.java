package com.small.rqspringcloudd.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @com.netflix.loadbalancer.RandomRule
 */
@Component
public class MyIPRandomRule extends AbstractLoadBalancerRule {
    Random rand;

    public MyIPRandomRule() {
        rand = new Random();
    }

    /**
     * Randomly choose from all living servers
     */
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }
            List<Server> ser = upList.stream().filter(s -> LocalHost.getInstance().localHost.contains(s.getHost())).collect(Collectors.toList());
            if (ser != null && ser.size() > 0) {
                server = ser.get(0);
            } else {
                int index = rand.nextInt(serverCount);
                server = upList.get(index);
            }
            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        // TODO Auto-generated method stub
    }

    public static void main(String[] args) {
        MyIPRandomRule s = new MyIPRandomRule();
        try {
            List<String> localHost = LocalHost.getInstance().getLocalHost();
            System.out.println(localHost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class LocalHost {
        private static LocalHost ourInstance = new LocalHost();
        List<String> localHost = new ArrayList<>();

        public static LocalHost getInstance() {
            return ourInstance;
        }

        private LocalHost() {
            try {
                localHost.add(getLocalHostName());
                localHost.add(getLocalHostIp());
                //localHost.add("127.0.0.1");
                //localHost.add("localhost");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public List<String> getLocalHost() {
            return localHost;
        }

        //获取本机的IP地址
        public String getLocalHostIp() throws Exception {
            InetAddress addr = InetAddress.getLocalHost();
            String ip = addr.getHostAddress().toString();
            return ip;
        }

        //获取本机的主机名
        public String getLocalHostName() throws Exception {
            InetAddress addr = InetAddress.getLocalHost();
            String name = addr.getHostName().toString();
            return name;
        }
    }

}
