package com.example.bricoli.models;

public class Notification {

    private String notif_state;
    private String notif_text;

    public Notification(String notif_state, String notif_text) {
        this.notif_state = notif_state;
        this.notif_text = notif_text;
    }

    public String getNotif_state() {
        return notif_state;
    }

    public String getNotif_text() {
        return notif_text;
    }

    public void setNotif_state(String notif_state) {
        this.notif_state = notif_state;
    }

    public void setNotif_text(String notif_text) {
        this.notif_text = notif_text;
    }
}
