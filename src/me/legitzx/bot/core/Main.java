package me.legitzx.bot.core;

import me.legitzx.bot.events.Events;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    private static String TOKEN = "NjM0MjIwNTU0ODU2OTU1OTM1.XafYsw.auDnAgdYKEgrvm1Tjh_DsGQj_oY";

    public static void main(String[] args) throws LoginException {
        new JDABuilder(TOKEN)
                .addEventListeners(new Events())
                .setActivity(Activity.playing("Creating Collages"))
                .build();
    }
}