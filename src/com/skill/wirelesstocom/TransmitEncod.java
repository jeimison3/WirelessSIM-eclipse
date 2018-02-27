package com.skill.wirelesstocom;

import gnu.io.SerialPort;

public class TransmitEncod {
    private String CODING;
    private int STOPBITS;
    private int DATABITS;

    public TransmitEncod(String entrada, int Databits, int Stopbits){
        this.CODING=entrada;
        this.DATABITS=Databits;
        this.STOPBITS=Stopbits;
    }

    final int getDATABITS(){
        return this.DATABITS;
    }

    final int getSTOPBITS(){
        return this.STOPBITS;
    }

    final String getCODING(){
        return this.CODING;
    }

    public static TransmitEncod UTF8_TE(){
        return new TransmitEncod("UTF-8",SerialPort.DATABITS_8,SerialPort.STOPBITS_1);
    }
    public static TransmitEncod ASCII_TE(){
        return new TransmitEncod("ASCII",SerialPort.DATABITS_8,SerialPort.STOPBITS_1);
    }
}
