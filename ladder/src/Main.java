import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static final int seed = 2;
    static ArrayList<Integer> data = new ArrayList<Integer>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int players = sc.nextInt();
        for (int i = 0; i < players*10 ; i++){
            String[] out = new String[players];
            Arrays.fill(out, "│");
            Random r = new Random();
            int total = 0;
            for(String s : out){
                if(total >= seed) break;

            }
            System.out.println();
        }
    }

    public int nextRand(){

    }
}
