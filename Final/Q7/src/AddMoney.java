import java.util.Random;

public class AddMoney implements Runnable{
    @Override
    public void run() {
        Random rand = new Random();
        int counter = 1;

        while(true){
            synchronized (Main.lockMoney){
                Main.bank.add(Thread.currentThread().getName() + " " + counter + " dollar");
            }

            counter ++;

            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
