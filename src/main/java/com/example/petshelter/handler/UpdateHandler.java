package com.example.petshelter.handler;

import com.pengrad.telegrambot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateHandler {

    private final CommandHandler commandHandler;
    private final CallbackQueryHandler callbackQueryHandler;

    @Autowired
    public UpdateHandler(final CommandHandler commandHandler,
                         final CallbackQueryHandler callbackQueryHandler) {
        this.commandHandler = commandHandler;
        this.callbackQueryHandler = callbackQueryHandler;
    }

    public void handle(Update update) {
        if (update.message() != null) {
            Message message = update.message();
            User user = message.from();
            Chat chat = message.chat();
            String text = message.text();
            commandHandler.handle(user, chat, text);
        } else if (update.callbackQuery() != null) {
            CallbackQuery query = update.callbackQuery();
            User user = query.from();
            Chat chat = query.message().chat();
            String data = query.data();
            callbackQueryHandler.handle(user, chat, data);
        }
    }

}
