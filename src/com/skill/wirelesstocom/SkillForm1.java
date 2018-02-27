package com.skill.wirelesstocom;

import javax.swing.*;
import java.awt.*;

public class SkillForm1 extends JFrame
{
    static JLabel udplabel=new JLabel();
    static JLabel seriallabel=new JLabel();
    private Container container;
    private static int cont_UDP=0,cont_SERIAL=0;

    public static void incUDP(){
     udplabel.setText(++cont_UDP + " mensagens UDP");
    }
    public static void incSerial(){
     seriallabel.setText(++cont_SERIAL + " mensagens Serial");
    }

    public SkillForm1() {

        super("WirelesSIM - Packet View Interface");
        container = getContentPane();
        setLayout(new FlowLayout(FlowLayout.LEFT,30,20));

        udplabel.setText("0 mensagens UDP");
        seriallabel.setText("0 mensagens Serial");

        add(seriallabel);
        add(udplabel);

        //seriallabel.setLocation(2,2);


    }

    public void start()
    {

        setSize(400,400);
        setVisible(true);
    }
    public static void main(String args[])
    {

    }
}