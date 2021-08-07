import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int players = sc.nextInt();
        for (int i = 0; i < players*4 ; i++){
            String[] out = new String[players];
            Arrays.fill(out, "│");
            for(String o : out) System.out.print(o);
            System.out.println("\n");
        }
    }
}
