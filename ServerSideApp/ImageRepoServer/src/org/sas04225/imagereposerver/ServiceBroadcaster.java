/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sas04225.imagereposerver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

/**
 *
 * @author sriram
 */
public class ServiceBroadcaster implements Runnable {

    public static String HTTP_TCP_LOCAL = "_http._tcp.local";

    public static InetAddress getFirstNonLocalAddress() throws SocketException {
        InetAddress addr = null;
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface net_inf : Collections.list(interfaces)) {
            for (InetAddress address : Collections.list(net_inf.getInetAddresses())) {
                if (!(address.isLinkLocalAddress() || address.isLoopbackAddress() || (address instanceof java.net.Inet6Address))) {
                    addr = address;
                }
            }
        }
        return addr;
    }
    private List<ServiceInfo> services;
    private JmDNS jmDNS;
    private InetAddress bindAddress;

    public ServiceBroadcaster(String name, String type, int port, String description, InetAddress bindAddress) {
        services = new ArrayList<>();
        services.add(ServiceInfo.create(type, name, port, 0, 0, true, description));
        this.bindAddress = bindAddress;
    }

    public ServiceBroadcaster(String name, String type, int port, Map<java.lang.String, ?> properties, InetAddress bindAddress) {
        services = new ArrayList<>();
        services.add(ServiceInfo.create(type, name, port, 0, 0, properties));
        this.bindAddress = bindAddress;
    }

    @Override
    public void run() {
        Logger.getLogger("ServiceBroadcaster").log(Level.INFO, "ServiceBroadcaster at thread {0} starting", Thread.currentThread().getName());
        try {
            jmDNS = JmDNS.create(bindAddress, bindAddress.getHostName());
            jmDNS.registerService(services.get(0));
            Thread.sleep(Long.MAX_VALUE);
        } catch (IOException e1) {
            Logger.getLogger("ServiceBroadcaster").log(Level.SEVERE, "ServiceBroadcaster at thread " + Thread.currentThread().getName() + " stopping", e1);
            jmDNS.unregisterAllServices();
        } catch (InterruptedException e2) {
            Logger.getLogger("ServiceBroadcaster").log(Level.INFO, "ServiceBroadcaster at thread {0} shutting down", Thread.currentThread().getName());
            jmDNS.unregisterAllServices();
        }
        try {
            jmDNS.close();
        } catch (IOException ex) {
            Logger.getLogger(ServiceBroadcaster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addService(ServiceInfo info) throws IOException {
        jmDNS.registerService(info);
    }
}
