package com.example.telegram.user;

import jakarta.validation.constraints.NotNull;

public class TelegramUser {
    @NotNull
    private Integer uchochik;

    private String chatId;
    private String step;
    private String msg;

    private String Tarjima;

    private Boolean KLTarjima;
    private Boolean LKTarjima;

    public String getTarjima() {
        return Tarjima;
    }

    public Integer getUchochik() {
        return uchochik;
    }

    public void setUchochik(Integer uchochik) {
        this.uchochik = uchochik;
    }

    public Boolean getKLTarjima() {
        return KLTarjima;
    }

    public void setKLTarjima(Boolean KLTarjima) {
        this.KLTarjima = KLTarjima;
    }

    public Boolean getLKTarjima() {
        return LKTarjima;
    }

    public void setLKTarjima(Boolean LKTarjima) {
        this.LKTarjima = LKTarjima;
    }

    public void setTarjima(String tarjima) {
        Tarjima = tarjima;
    }
    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
