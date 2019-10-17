package me.legitzx.bot.core;

import me.legitzx.bot.events.Events;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    private static String TOKEN = "TOKEN_HERE";

    public static void main(String[] args) throws LoginException {
        new JDABuilder(TOKEN)
                .addEventListeners(new Events())
                .setActivity(Activity.playing("iCAPTCHA"))
                .build();
    }
}