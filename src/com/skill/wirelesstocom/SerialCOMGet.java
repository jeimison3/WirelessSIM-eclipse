package com.skill.wirelesstocom;

import gnu.io.CommPortIdentifier;

import java.util.Enumeration;

public class SerialCOMGet {

    static String PortaIDToStr(int PortaTyp){
        switch(PortaTyp) {
            case 1: return "SERIAL";
            case 2: return "PARALLEL";
            case 3: return "I2C";
            case 4: return "RS485";
            case 5: return "RAW";
            default: return null;
        }

    }

    public static CommPortIdentifier main() {
        Enumeration portList = CommPortIdentifier.getPortIdentifiers();
        if(!portList.hasMoreElements()){
            return null;
        }else {
            CommPortIdentifier PortaFinal=null;
            while (portList.hasMoreElements()) {
                CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();
                if(portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                    PortaFinal = portId;
                }
                System.out.println(portId.getName() + " | porta tipo: " + PortaIDToStr(portId.getPortType()));
            }
            return PortaFinal;
        }
    }
}
