package executor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import 캘린더.Main;

public class CalenderExecutor implements CommandExecutor {
	long origin = 0;
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 0) {
			sender.sendMessage("사용법 : calender get/set\n어떻게 args를 아무것도 붙이지 않는 후닝같은 짓을 할 수가 있죠? 정말 한심하네요.");
			return true;
		}
		switch (args[0]) {
		case "set": {
			if(args.length != 5) {
				sender.sendMessage("명령어를 제대로 입력하여 주세요.\n/calender set [AC/BC] [년] [달] [일] 순입니다.");
				return true;
			}
			if(!sender.hasPermission("tools.chronos.settime")) {
				sender.sendMessage("권한이 없습니다.");
				return true;
			}
			String[] code = {args[1], args[2], args[3], args[4]}; //AC 2020 06 27
			for(int i = 1; i <= 3; i++) {
				if(Integer.parseInt(code[i])==0) {
					sender.sendMessage("날짜에 0을 입력할 수 없습니다.");
					return true;
				}
			}
			long year = Long.parseLong(code[1]);
			int month = Integer.parseInt(code[2]);
			long day = Long.parseLong(code[3]);
			if(month > 12) {
				sender.sendMessage("12월 다음은 1월입니다.");
				return true;
			}

            if(Arrays.asList(1, 3, 5, 7, 8, 10 ,12).contains(month)) {
            	if(day > 31) {
            		sender.sendMessage(month+"월에는 31일까지밖에 없습니다.");
            		return true;
            	}
            }else if(Arrays.asList(4, 6, 9, 11).contains(month)){
        		sender.sendMessage(month+"월에는 30일까지밖에 없습니다.");
        		return true;
            }else{
                if(year % 4 == 0){
                    if(year % 100 == 0){
                        if(year % 400 == 0) 
                    		{sender.sendMessage(month+"월에는 29일까지밖에 없습니다.");
                		return true;}
                    }else 
                		{sender.sendMessage(month+"월에는 29일까지밖에 없습니다.");
            		return true;}
                }else 
            		{sender.sendMessage(month+"월에는 28일까지밖에 없습니다.");
        		return true;}
            }
            
			if(!code[0].equalsIgnoreCase("AC") && !code[0].equalsIgnoreCase("BC")) {
        		sender.sendMessage("AC 또는 BC 중 하나를 입력하여 주세요.");
        		return true;
			}
			origin = getTime(code, false) - 9528000;
			try {
				save();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sender.sendMessage("변경했습니다.");
			break;
		}
		case "get":{
			if(sender instanceof Player) {
				Player p = (Player) sender;
				World w = p.getWorld();
				sender.sendMessage(getNow(w.getFullTime()));
				
			}else {
				for(World w : Bukkit.getWorlds()) {
					sender.sendMessage("["+w.getName() +"] "+getNow(w.getFullTime()));
				}
			}
			break;
		}
		default:{
			return true;
		}
	}
		return true;
	}
	
	public String getNow(long tick) {
		tick -= origin;
        boolean ac = tick > 0;
        if(!ac) tick *= -1;
        long day = ((tick+6000) / 24000)+1;
        long year = 1;
        while(day > 365){
            day -= 365;
            year++;
        }
        int month = 1;
        for(int i = 1; i <= 12; i++){
            if(Arrays.asList(1, 3, 5, 7, 8, 10 ,12).contains(i)){
                if(day > 31){
                    day -= 31;
                    month++;
                }else break;
            }else if(Arrays.asList(4, 6, 9, 11).contains(i)){
                if(day > 30){
                    day -= 30;
                    month++;
                }else break;
            }else{
                if(year % 4 == 0){
                    if(year % 100 == 0){
                        if(year % 400 == 0){
                            if(day > 29){
                                day -= 29;
                                month++;
                                continue;
                            }else break;
                        }
                    }else{
                        if(day > 29){
                            day -= 29;
                            month++;
                            continue;
                        }else break;
                    }
                }else if(day > 28){
                    day -= 28;
                    month++;
                }else break;
                
            }
        }
        if(ac){
        	return "AC "+year+" "+month+" "+day;
        }else{
        	return "AC "+year+" "+month+" "+day;
        }
		
	}
	
	public long getTime(String[] code, boolean calc) {
		boolean ac = code[0].equalsIgnoreCase("AC") ? true : false;
		long year = Integer.valueOf(code[1]);
		long month = Integer.valueOf(code[2]);
		long day = Integer.valueOf(code[3]);
		day += year*365;
		for(int i = 1; i<=month; i++) {
            if(Arrays.asList(1, 3, 5, 7, 8, 10 ,12).contains(i)){
            	day += 31;
            }else if(Arrays.asList(4, 6, 9, 11).contains(i)){
            	day += 30;
            }else{
                if(year % 4 == 0){
                    if(year % 100 == 0){
                        if(year % 400 == 0) day += 29;
                    }else day += 29;
                }else day += 28;
            }
		}
		if(!ac) day *= -1;
		if(calc)
		return day*24000 - origin;
		else return day * 24000;
	}

	public void save() throws IOException {
		Main.getDataFolder.mkdir();
		File f = new File(Main.getDataFolder+File.separator+"save.txt");
		FileWriter fw = new FileWriter(f);
		fw.write(origin+"");
		fw.close();
	}

}
