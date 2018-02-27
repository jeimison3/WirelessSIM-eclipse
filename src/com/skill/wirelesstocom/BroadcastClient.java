package com.skill.wirelesstocom;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BroadcastClient
{

    private static int PORT = 11888;

    public BroadcastClient(int porta){
        this.PORT=porta;
    }

    public void enviaBroadcast(String mensagem) throws IOException {
        for(InetAddress HostBroad: Main.broads) {
            DatagramSocket dsock = new DatagramSocket();
            byte[] send = mensagem.getBytes(Main.CONECTION_ENCODING.getCODING());
            DatagramPacket data = new DatagramPacket(send, send.length, HostBroad, this.PORT);
            dsock.send(data);
        }//Fim do loop foreach
        System.out.println("> "+ Main.broads.size()+" broadcasts feitos.");
    }
}

class BroadcastServer extends Thread {
    private final int port;
    public String msgRead="";

    public BroadcastServer( int porta )
    {
        this.port = porta;
        this.setPriority(6);
        this.start();
    }

    @Override
    public void run()
    {
        try {
            DatagramSocket dsock = new DatagramSocket( port );
            DatagramPacket data;
            while(!this.isInterrupted()) {
                data = new DatagramPacket( new byte[2048], 2048 );
                dsock.receive(data);
                SkillForm1.incUDP();

                msgRead=(new String( data.getData(), Main.CONECTION_ENCODING.getCODING() )).trim();
                System.out.println("[UDP RECEIVE] " + msgRead );
                Main.writeCOMData(msgRead);
            }
            System.out.println("UDP FINALIZADO");
        } catch( SocketException ex ) {
            Logger.getLogger( BroadcastServer.class.getName() ).
                    log( Level.SEVERE, null, ex );
        } catch( IOException ex ) {
            Logger.getLogger( BroadcastServer.class.getName() ).
                    log( Level.SEVERE, null, ex );
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}