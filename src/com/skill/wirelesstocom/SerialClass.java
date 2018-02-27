package com.skill.wirelesstocom;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class SerialClass implements SerialPortEventListener {

    public SerialPort serialPort;

    public static BufferedReader input;
    public static OutputStream output;
    /** Milliseconds to block while waiting for port open */
    public static final int TIME_OUT = 10;
    /** Default bits per second for COM port. */
    public static int DATA_RATE = 2500000;

    public void initialize() {
        CommPortIdentifier portId = Main.portSerial;

        if (portId == null) {
            System.err.println("ERRO: COM não encontrada.");
            return;
        }
        try {
// open serial port, and use class name for the appName.
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    TIME_OUT);

// set port parameters
            serialPort.setSerialPortParams(DATA_RATE,
                    Main.CONECTION_ENCODING.getDATABITS(),
                    Main.CONECTION_ENCODING.getSTOPBITS(),
                    SerialPort.PARITY_NONE);

            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();
            char ch = 1;
            output.write(ch);


            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    public synchronized void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                String inputLine=input.readLine();
                System.out.println("[COM OUT]: "+inputLine);
                SkillForm1.incSerial();
                Main.writeUDPData(inputLine);
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }

    }




}