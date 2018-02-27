package com.skill.wirelesstocom;

import gnu.io.SerialPortEvent;

import java.io.BufferedReader;
import java.io.OutputStream;

public class SerialThread extends Thread {
    public SerialClass serial;
    BufferedReader input;
    OutputStream output;

    public SerialThread(int DATA_RT)
    {
        serial.DATA_RATE=DATA_RT;
        serial = new SerialClass();
        input = serial.input;
        output = serial.output;
        this.setPriority(7);
        this.setDaemon(true);
    }

    @Override
    public void run()
    {
        try {
        serial.initialize();
            System.out.println("SERIAL INICIADO");
        //Fica preso em loop para funcionar
        while (!this.isInterrupted()){

        }
        //Finaliza
        serial.close();
            System.out.println("SERIAL FINALIZADO");
        }
        catch(Exception e){

        }

    }
}
