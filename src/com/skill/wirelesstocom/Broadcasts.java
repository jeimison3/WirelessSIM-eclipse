package com.skill.wirelesstocom;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;

public class Broadcasts
{
    public static HashSet<InetAddress> main()
    {
        HashSet<InetAddress> listOfBroadcasts = new HashSet<InetAddress>();
        Enumeration list;
        try {
            list = NetworkInterface.getNetworkInterfaces();

            while(list.hasMoreElements()) {
                NetworkInterface iface = (NetworkInterface) list.nextElement();

                if(iface == null) continue;

                if(!iface.isLoopback() && iface.isUp()) {
                    //System.out.println("Found non-loopback, up interface:" + iface);

                    Iterator it = iface.getInterfaceAddresses().iterator();
                    while (it.hasNext()) {
                        InterfaceAddress address = (InterfaceAddress) it.next();
                        //System.out.println("Found address: " + address);
                        if(address == null) continue;
                        InetAddress broadcast = address.getBroadcast();
                        if(broadcast != null)
                        {
                            System.out.println("Broadcast encontrado: " + broadcast);
                            listOfBroadcasts.add(broadcast);
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            System.err.println("Erro capturando interfaces de rede.\nMsg:"+ex.toString());
            ex.printStackTrace();
        }

        return listOfBroadcasts;
    }
}
