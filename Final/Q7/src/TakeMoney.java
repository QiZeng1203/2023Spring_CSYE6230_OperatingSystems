import java.util.Random;

public class TakeMoney implements Runnable{
    @Override
    public void run() {
        Random rand = new Random();
        while(true){
            synchronized (Main.lockMoney){
                if(Main.bank.size() > 0){
                    String takeAmount = Main.bank.remove(0);
                    System.out.println(Thread.currentThread().getName() + " take " + takeAmount);
                }

            }

            try {
                Thread.sleep(rand.nextInt(20));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
