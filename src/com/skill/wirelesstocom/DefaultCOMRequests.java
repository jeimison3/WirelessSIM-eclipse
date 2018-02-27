package com.skill.wirelesstocom;

public class DefaultCOMRequests {

    public static String SysComunicate(String Assunto, String conteudo){
        return Assunto.replace(";","")+";"+conteudo.replace(";","")+";";
    }

    public static String getReply(String request){
        switch (request){
            case "millis();":
                return SysComunicate("millis()",String.valueOf(System.currentTimeMillis()));
            default:
                return null;
        }
    }

}
