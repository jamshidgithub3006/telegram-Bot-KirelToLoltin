package com.example.telegram;

import com.example.telegram.entity.K_L_Bot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class TelegramApplication {

    public static void main(String[] args) {

        try {
            TelegramBotsApi telegramBotsApi =new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new K_L_Bot());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        SpringApplication.run(TelegramApplication.class, args);
    }

}
