package com.example.myprogram;

import java.util.Date;

public class ChatMesagge {
    private String mesaggetext;
    private String mesaggeuser;
    private String mesaggetime;

    public ChatMesagge(String mesaggeuser, String mesaggetext){
        this.mesaggetext = mesaggetext;
        this.mesaggeuser = mesaggeuser;
        Date date = new Date();
        String temp = date.toString().substring(10, 16);
        mesaggetime = temp;
    }

    String getMesaggeText(){return mesaggetext;}
    String getMesaggeUser(){return mesaggeuser;}
    String getMesaggeTime(){return mesaggetime;}
}
