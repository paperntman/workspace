package dobak;

import java.util.Random;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("dobak")) {
			if(args.length==0) {
				sender.sendMessage("숫자를 입력해주세요!");
				return true;
			}
			Random r = new Random();
			int number = r.nextInt(51);
			int input = Integer.parseInt(args[0]);
			if(input < 0 || 50 < input) {
				sender.sendMessage("0 ~ 50 사이의 숫자를 입력해주세요!");
				return true;
			}
			if(number == input) {
				sender.sendMessage("성공!");
			}else {
				sender.sendMessage("ㅋㅋㄹㅃㅃ");
			}
		}
		return true;
	}
}
