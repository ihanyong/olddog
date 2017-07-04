package com.monkey.olddog.network;/**
 * Created by hanyong on 2017/6/5.
 */

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * @author hanyong
 * @Date 2017/6/5
 */
public class NetworkTest {


    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());

        }
    }


    public static void testInetAddress(String[] args) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName("baidu.com");

//        for (InetAddress inetAddress : inetAddresses) {

            System.out.println(inetAddress);
            System.out.println(inetAddress.getAddress());
            System.out.println(inetAddress.getCanonicalHostName());
            System.out.println(inetAddress.getHostAddress());
            System.out.println(inetAddress.getHostName());
//        }
    }
}
