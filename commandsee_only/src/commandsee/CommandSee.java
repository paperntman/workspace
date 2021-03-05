package commandsee;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSee implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		ArrayList<World> world = new ArrayList<>();
		ArrayList<Ppcmd> cmd = new ArrayList<>();
		ArrayList<String> text = new ArrayList<>();
		boolean help = false;
		int range = -1;
		if(args.length > 0)
		for(int i = 0; i < args.length-1; i+=2) {
			switch (args[i]) {
			case "-w":
				world.add(Bukkit.getWorld(args[i+1]));
				break;
			case "-s":
				cmd.add(Ppcmd.valueOf(args[i+1]));
				break;
			case "-c":
				text.add(args[i+1]);
				break;
			case "-r":
				if(!(sender instanceof Player)) return true;
				range = Integer.parseInt(args[i+1]);
				break;
			case "help":
				if(args[i+1] == null) {
					sender.sendMessage("-w <월드 이름> 으로 특정 월드들에서 실행되는 커맨드들을 검색합니다.");	
					sender.sendMessage("-s <타입> 으로 특정 타입의 커맨드 실행자가 실행하는 커맨드들을 검색합니다. (목록 : 임펄스, 체인, 리핏, 서버, 커맨드블록 마인카트)");
					sender.sendMessage("-c <커맨드> 로 이 텍스트를 포함하는 커맨드들을 검색합니다.");
				}else {
					switch(args[i+1]) {
					case "-w":{
						sender.sendMessage("-w <월드 이름> 으로 특정 월드들에서 실행되는 커맨드들을 검색합니다.");
						break;
					}
					case "-s":{
						sender.sendMessage("-s <타입> 으로 특정 타입의 커맨드 실행자가 실행하는 커맨드들을 검색합니다.\n(목록 : 임펄스, 체인, 리핏, 서버, 커맨드블록 마인카트)");
						break;
					}
					case "-c":{
						sender.sendMessage("-c <커맨드> 로 이 텍스트를 포함하는 커맨드들을 검색합니다.");
						break;
					}
					case "-r":{
						sender.sendMessage("-r <범위> 로 특정 범위 안의 커맨드들을 탐색할 수 있습니다.");
					}
					default:
						sender.sendMessage("-w <월드 이름> 으로 특정 월드들에서 실행되는 커맨드들을 검색합니다.");	
						sender.sendMessage("-s <타입> 으로 특정 타입의 커맨드 실행자가 실행하는 커맨드들을 검색합니다. (목록 : 임펄스, 체인, 리핏, 서버, 커맨드블록 마인카트)");
						sender.sendMessage("-c <커맨드> 로 이 텍스트를 포함하는 커맨드들을 검색합니다.");
						sender.sendMessage("-r <범위> 로 특정 범위 안의 커맨드들을 탐색할 수 있습니다.");
						break;
					}
				}
				help = true;
				return true;
			default:
				return true;
			}
		}
		if(help) return true;
		Quest q = null;
		q = new Quest(sender, cmd, world, text, range);
		Main.quests.add(q);
		return true;
	}

}
