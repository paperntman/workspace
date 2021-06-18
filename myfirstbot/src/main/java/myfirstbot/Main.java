package myfirstbot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class Main {

    public static void main(String[] args) {
        // Insert your bot's token here
        String token = "ODU1MzcxNDA4MTAwMDMyNTMz.YMxgsw.tstyadfMFNOxMAsvgLVuCvoy2tw";

        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();

        // Add a listener which answers with "Pong!" if someone writes "!ping"
        api.addMessageCreateListener(new MessageCreateListener() {
			
			@Override
			public void onMessageCreate(MessageCreateEvent event) {
	            if (event.getMessageContent().equalsIgnoreCase("!ping")) {
	                event.getChannel().sendMessage("Pong!");
	            }
			}
		});

        // Print the invite url of your bot
        System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
    }

}