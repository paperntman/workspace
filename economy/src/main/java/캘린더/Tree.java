package 캘린더;

import java.util.ArrayList;
import java.util.List;

public class Tree{
	Tree Parent;
	List<Tree> Children = new ArrayList<Tree>();
	String name;
	
	public Tree(String n) {
		name = n;
	}
	public Tree(String n, Tree p) {
		name = n;
		Parent = p;
	}
	
	public Tree addChild(Tree t) {
		Children.add(t);
		return t;
	}
	
	public Tree addChildren(Tree...t) {
		for (Tree tree : t) {
			Children.add(tree);
		}
		return this;
	}
	
	public List<Tree> addChildrenAsString(String...t) {
		List<Tree> t1 = new ArrayList<Tree>();
		for (String string : t) {
			t1.add(new Tree(string, this));
		}
		addChildren(t1.toArray(new Tree[t1.size()]));
		return t1;
	}
	
	public void setParent(Tree parent) {
		Parent = parent;
	}
	
	public Tree removeChild(Tree t) {
		Children.remove(t);
		return this;
	}
	
	public List<Tree> getChildren() {
		return Children;
	}
	
	public Tree getParent() {
		return Parent;
	}
	
	public String getName() {
		return name;
	}
	
	public Tree getChildAsString(String s) {
		for (Tree tree : Children) {
			if(tree.name.equalsIgnoreCase(s)) return tree;
		}
		return null;
	}
	
	public Tree getHighestTree() {
		Tree high = Parent;
		while(high.Parent != null) {
			high = high.Parent;
		}
		return high;
	}
	
	public void print() {
		printParent();
		System.out.println();
		this.getChildren().forEach(t -> {t.print();});
	}
	
	public void printParent() {
		if(Parent != null) {
			Parent.printParent();
		}
		System.out.print(this.name + " ");
		if(!Children.isEmpty()) {
			System.out.print("- ");
		}
	}
}
