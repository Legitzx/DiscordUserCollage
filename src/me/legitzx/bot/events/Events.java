package me.legitzx.bot.events;

import me.legitzx.bot.processing.ImageProcessor;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Events extends ListenerAdapter {
    private ImageProcessor imageProcessor = new ImageProcessor();

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if(event.getAuthor().getId().equals("272202471479181312")) {
            List<Member> members = new ArrayList<>(); // Stores all the members

            members = event.getGuild().getMembers();

            for(int x = 0; x < members.size(); x++) { // Loops through ever member in the discord and gets their avatar url
                Member member = members.get(x);
                String num = Integer.toString(x);

                if(member.getUser().getAvatarUrl() == null) {
                    System.out.println();
                    imageProcessor.downloadImage(member.getUser().getDefaultAvatarUrl(), num + ".png");
                } else {
                    imageProcessor.downloadImage(member.getUser().getEffectiveAvatarUrl(), num + ".png");
                }
            }

            imageProcessor.process(members.size()); // Final Step -- Creates the final image
        }
    }


}
