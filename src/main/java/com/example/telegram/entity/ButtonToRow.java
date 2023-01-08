package com.example.telegram.entity;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Arrays;

public class ButtonToRow {
    public static KeyboardButton button1(String text) {
        return new KeyboardButton(text);
    }

    public static KeyboardRow rows(KeyboardButton... buttons) {
        KeyboardRow row = new KeyboardRow();
        row.addAll(Arrays.asList(buttons));
        return row;
    }
}
