package com.skill.wirelesstocom;

import gnu.io.CommPortIdentifier;

import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashSet;

/*

    BREVE LEIA-ME:

    Seguem as descrições de cada classe:

    DefaultCOMRequests -> Respostas padrões para mensagens da porta COM, coisas que o
    PC pode responder (tempo por exemplo). A variável "FORWARD_INTERCEPTED_MESSAGES" define se
    as mensagens serão realmente enviadas por UDP.

    TransmitEncod -> Padrões de codificação utilizáveis. Existem atualmente UTF8 e ASCII.
    Suas referências estão nas funções.

    SkillForm1 -> Formulário visível durante a execução.

    SerialThread -> Thread principal da leitura Serial (dados da porta COM). Apenas configurações básicas.

    SerialClass -> Contém duas threads com as funções de receber dados UDP por rede e Serial da porta COM.

 */

public class Main {

    static boolean FORWARD_INTERCEPTED_MESSAGES=false;

    static BroadcastServer brdServ;
    static BroadcastClient brdSend;
    public static SkillForm1 form1;
    static CommPortIdentifier portSerial;
    public static SerialThread serialThr;
    public static HashSet<InetAddress> broads;
    public static TransmitEncod CONECTION_ENCODING;



    public static synchronized void writeCOMData(String data) {
        System.out.println("[COM IN]: " + data);
        try {
            if(data.indexOf("[SERVER_CLOSE]")!=-1) {serialThr.interrupt();brdServ.interrupt();}
            Main.serialThr.serial.serialPort.getOutputStream().write(data.getBytes(Main.CONECTION_ENCODING.getCODING()));
        } catch (Exception e) {
            System.out.println("Nao é possível escrever na porta.\nMsg:"+e.toString());
        }
    }

    public static void writeUDPData(String data) {
            //Usar respostas padrão:
        String defReturn= DefaultCOMRequests.getReply(data);
            if(defReturn!=null){
                writeCOMData(defReturn);
            }

        if(((defReturn!=null)&&(FORWARD_INTERCEPTED_MESSAGES))||(defReturn==null))
        System.out.println("[UDP BROADCAST]: " + data);

        try {
            brdSend.enviaBroadcast(data);
        } catch (Exception e) {
            System.out.println("Nao é possível enviar.\nMsg:"+e.toString());
        }
    }



    public static void main(String[] args) throws IOException {
    	
    	System.loadLibrary("rxtxSerial");
    	System.loadLibrary("rxtxParallel");
    	
        broads = Broadcasts.main();
        portSerial=SerialCOMGet.main();
        CONECTION_ENCODING= TransmitEncod.UTF8_TE();
        //CONECTION_ENCODING=TransmitEncod.ASCII_TE();
        
        try
        {
            form1 = new SkillForm1();
            form1.start();
            form1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            brdServ = new BroadcastServer(11888); //RECEBE broadcasts

            brdSend = new BroadcastClient(11889); //ENVIA broadcasts

            if(portSerial!=null) {
                serialThr = new SerialThread(250000);
                serialThr.start();
            }else System.err.println("ERRO: NENHUMA PORTA SERIAL FOI ENCONTRADA.");

        }
        catch(Exception e){}



    }

}
