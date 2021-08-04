package completer;

import java.util.ArrayList;
import java.util.List;

import 캘린더.Tree;

public class TabCompleter {
	static int i = 0;
	static ArrayList<Tree> t = new ArrayList<Tree>();
	static ArrayList<Tree> queue = new ArrayList<Tree>();
	public static List<String> get(String[] args, Tree tree){
		t.add(tree);
		for(i = 0; i < args.length; i++) {
			t.forEach(tr -> {tr.getChildren().forEach(t1 -> {
				if(t1.getName().toLowerCase().startsWith(args[i].toLowerCase())) {
					queue.add(t1);
				}
			});});
			t.clear();
			t.addAll(queue);
		}
		t.forEach(tr -> tr.print());
		return null;
	}
	
	public static void main(String[] args) {
		String[] arg = {"paper", "n"};
 		Tree t = new Tree("main");
		t.addChildrenAsString("paper", "notch", "ppap").forEach(t1 -> {
			t1.addChildrenAsString("nickname");
		});
		get(arg, t);
		
	}
}

/*
*/