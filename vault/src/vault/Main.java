package vault;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	/*
	 * /money : 나의 잔액
	/money send [player] x: 자신이 그 player에게 x만큼을 송금
	/money [player] : 그 player의 잔액  [permission: tools.money.player]
	/money set [player] x: 그 player의 잔액을 x만큼 세팅  [permission: tools.money.set]
	/money reset [player]: 그 player의 잔액을 초기화함  [permission: tools.money.reset]
	/money take [player] x: 그 player의 잔액을 x만큼 차감  [permission: tools.money.take]
	/money give [player] x: 그 player의 잔액을 x만큼 추가  [permission: tools.money.give]
	/baltop : 돈 순위  [permission: tools.money.baltop]
	 * UUID로 작동, Vault 플러그인과 동기화
	 * 
	 */
	
	// Filename = Player.uuid
	// String = value
	File f;
	
	@Override
	public void onEnable() {
		f = new File(getDataFolder().getPath()+File.separator);
		getDataFolder().mkdir();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("money")) {
			if(args.length == 0) { //money : 나의 잔액
				if(sender instanceof Player) {
					
				}else {
					sender.sendMessage("당신은 플레이어가 아닙니다! /money [uuid] 를 사용해주세요.");
				}
			}
			switch (args[0]) {
			case "send":{ //money send [player] x: 자신이 그 player에게 x만큼을 송금
				
				break;
			}
			case "set":{ //money set [player] x: 그 player의 잔액을 x만큼 세팅  [permission: tools.money.set]
				
				break;
			}
			case "reset":{ //money reset [player]: 그 player의 잔액을 초기화함  [permission: tools.money.reset]
				
				break;
			}
			case "take":{ //money take [player] x: 그 player의 잔액을 x만큼 차감  [permission: tools.money.take]
				
				break;
			}
			case "give":{ //money give [player] x: 그 player의 잔액을 x만큼 추가  [permission: tools.money.give]
				
				break;
			}
			

			default:{ //money [player] : 그 player의 잔액  [permission: tools.money.player]
				break;
			}
			}
		}else if(command.getName().equalsIgnoreCase("baltop")) {
			
		}
		return true;
	}
	public Hoon getPlayer(String s) {
		return null;
	}
}