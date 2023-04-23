import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static ArrayList<String> bank = new ArrayList<>();
    public static Object lockMoney = new Object();

    public static void main(String[] args) {
	// write your code here
        Thread[] add = new Thread[4];
        Thread[] take = new Thread[5];

        for(int i = 0 ; i < add.length; i ++){
            add[i] = new Thread(new AddMoney());
            add[i].setName("Adding " + i);
            add[i].start();
        }

        for(int i = 0 ; i < take.length; i ++){
            take[i] = new Thread(new TakeMoney());
            take[i].setName("Take " + i);
            take[i].start();
        }

        //Queue<String> queue = new LinkedList<>();
    }
}
