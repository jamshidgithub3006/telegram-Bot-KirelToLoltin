package com.example.telegram.entity;


import com.example.telegram.atrebut.SetText;
import com.example.telegram.token.Token;
import com.example.telegram.transt.TransliteratorHelper;
import com.example.telegram.user.TelegramUser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class K_L_Bot extends TelegramLongPollingBot {
    private final Token token = new Token();
    private Integer uchochik = 0;
    List<TelegramUser> users = new ArrayList<>();

    private final TransliteratorHelper transliteratorHelper = new TransliteratorHelper();

    @Override
    public String getBotUsername() {
        return token.getBotName();
    }

    @Override
    public String getBotToken() {
        return token.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        String text = update.getMessage().getText();
        String chatId = update.getMessage().getChatId().toString();
        Message message = update.getMessage();
        String firstName = message.getChat().getFirstName();
        if (update.hasMessage()) {
            if (message.hasText()) {
                TelegramUser user = sendUser(chatId);
                System.out.println(firstName + ": " + chatId + ":---->  " + text);
                user.setStep(text.toUpperCase());
                user.setTarjima(text.toUpperCase());
                if (user.getStep().equals(SetText.START)) {
                    Start("Asslomu alaaykum xurmatli " + firstName + " botmizga xush kelibsiz !" +
                            "\n Bu bot  JAVA dasturlash-tilida yozilgan", chatId);
                    user.setTarjima("JUMA");
                    user.setLKTarjima(SetText.FALSE);
                    user.setKLTarjima(SetText.FALSE);
                } else if (user.getStep().equals(SetText.ADMIN)) {
                    sendMessage("Admin" +"\n"+
                            " \uD83D\uDC69\uD83C\uDFFB\u200D\uD83D\uDCBB:: @medinaISmoilova " +
                            "\n \uD83D\uDC68\uD83C\uDFFB\u200D\uD83D\uDCBB:: @QwerSanjartyu", chatId);
                    user.setTarjima("JUMA");
                    user.setLKTarjima(SetText.FALSE);
                    user.setKLTarjima(SetText.FALSE);
                }
                if (user.getTarjima().equals(SetText.KL) || user.getTarjima().equals("KIRIL-LOTIN ✅")) {
                    KLButton(text, chatId);
                    user.setKLTarjima(SetText.TRUE);
                    user.setLKTarjima(SetText.FALSE);
                }
                else if (user.getTarjima().equals(SetText.LK) || user.getTarjima().equals("LOTIN-KIRIL ✅")) {
                    LKButton(text, chatId);
                    user.setLKTarjima(SetText.TRUE);
                    user.setKLTarjima(SetText.FALSE);
                }
                if (user.getKLTarjima()) {
                    String tarjima = KLTarjima(text);
                    sendMessage(tarjima, chatId);
                }
                else if (user.getLKTarjima()) {
                    String tarjima = LKTarjima(text);
                    sendMessage(tarjima, chatId);
                }
                Integer akdivlar = Akdivlar(chatId);
                if (text.equals("jamshidgithub3006")) {
                    sendMessage("obunachilar soni: " + String.valueOf(akdivlar), "1872620292");
                }

//                    if(text.equals("Kiril-Lotin"))
//                    user.setTJ(SetText.KIRIL_LOTIN);
//                     } else if (text.equals("Lotin-Kiril")) {
//                    user.setTJ(SetText.LOTIN_KIRIL);
//                 else if (user.getStep().equals(SetText.ADMIN)) {
//                    sendMessage("Admining linkiga xabaringizni yozing \uD83D\uDC69\uD83C\uDFFB\u200D\uD83D\uDCBB :: @medinaISmoilova  " +
//                            "\n‼️ Iltimos ortiqcha gap shart emas ‼️", chatId);
//                }


            }


        }
    }

    public Integer Akdivlar(String chatId) {
        for (TelegramUser user : users) {
            if (user.getChatId().equals(chatId)) {  uchochik = uchochik;     }
            else {    uchochik++;      }
        }
        return uchochik;
    }

    private TelegramUser sendUser(String chatId) {
        for (TelegramUser user : users) {
            if (user.getChatId().equals(chatId)) {
                return user;
            }
        }
        TelegramUser user = new TelegramUser();
        user.setChatId(chatId);
        users.add(user);
        return user;
    }

    public void sendMessage(String txt, String ChatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(txt);
        sendMessage.setChatId(ChatId);
        try {
            execute(sendMessage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Start(String text, String chatId) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        SendMessage sendMessage = new SendMessage();
        replyKeyboardMarkup.setResizeKeyboard(true);
        KeyboardButton uz = new KeyboardButton("Kiril-Lotin");
        KeyboardButton rus = new KeyboardButton("Lotin-Kiril");
        KeyboardRow rowUzRus = new KeyboardRow();
        rowUzRus.add(uz);
        rowUzRus.add(rus);
        List<KeyboardRow> lists = new ArrayList<>();
        lists.add(rowUzRus);
        replyKeyboardMarkup.setKeyboard(lists);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        sendMessage.setText(text);
        sendMessage.setChatId(chatId);
        try {
            execute(sendMessage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void KLButton(String text, String chatId) {
        SendMessage sendMessage = new SendMessage();
        ReplyKeyboardMarkup replyKeyboardMarkup1 = new ReplyKeyboardMarkup();
        replyKeyboardMarkup1.setResizeKeyboard(true);
        KeyboardButton uz = new KeyboardButton("Kiril-Lotin ✅");
        KeyboardButton rus = new KeyboardButton("Lotin-Kiril");
        KeyboardRow rowUzRus = new KeyboardRow();
        rowUzRus.add(uz);
        rowUzRus.add(rus);
        List<KeyboardRow> lists = new ArrayList<>();
        lists.add(rowUzRus);
        replyKeyboardMarkup1.setKeyboard(lists);
        sendMessage.setReplyMarkup(replyKeyboardMarkup1);
        sendMessage.setText(text);
        sendMessage.setChatId(chatId);
        try {
            execute(sendMessage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String KLTarjima(String text) {
        return transliteratorHelper.transliterateToLatin(text);
    }

    public void LKButton(String text, String chatId) {
        SendMessage sendMessage = new SendMessage();
        ReplyKeyboardMarkup replyKeyboardMarkup1 = new ReplyKeyboardMarkup();
        replyKeyboardMarkup1.setResizeKeyboard(true);
        KeyboardButton uz = new KeyboardButton("Kiril-Lotin ");
        KeyboardButton rus = new KeyboardButton("Lotin-Kiril ✅");
        KeyboardRow rowUzRus = new KeyboardRow();
        rowUzRus.add(uz);
        rowUzRus.add(rus);
        List<KeyboardRow> lists = new ArrayList<>();
        lists.add(rowUzRus);
        replyKeyboardMarkup1.setKeyboard(lists);
        sendMessage.setReplyMarkup(replyKeyboardMarkup1);
        sendMessage.setText(text);
        sendMessage.setChatId(chatId);
        try {
            execute(sendMessage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String LKTarjima(String text) {
        return transliteratorHelper.transliterateToCyrillic(text);
    }


}
