package uebung;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class BackDiningBoys extends Thread{
    /*
    Using Threads, create a simple simulation for the Dining Philosophers Problem:

    For each Philosopher, create a Thread within this Philosopher dines. The implementation should just create some outputs like:

    Philosopher 1 takes his right fork.
    Philosopher 1 takes his left fork.
    Philosopher 1 eats.
            Philosopher 1 puts down his right fork.
            Philosopher 1 puts down his left fork.
               ...or...
    Philosopher 1 takes his right fork.
    Philosopher 1 waits for left fork.

    Test your implementation to find out whether it could lead to a deadlock.
    Provide more implementations with a sensible way to switch between them; at least one creating a deadlock and one using a probabilistic solution to avoid the deadlock.
    */
    int banana = 0;
    int i = 0;

    Random r = new Random();
    Thread t;

    boolean finished = false;
    boolean left = false;
    boolean right = false;
    boolean ate = false;

    public BackDiningBoys(int n){
        this.banana = n;
        this.t = new Thread(this);
        t.start();
    }

    public static void main(String[] args){
        new BackDiningBoys(1);
        new BackDiningBoys(2);
        new BackDiningBoys(3);
        new BackDiningBoys(4);
        new BackDiningBoys(5);
    }

    public void run(){
        try{
            while(!finished && i<800){
                Lock l = new Lock() {
                    @Override
                    public void lock() {

                    }

                    @Override
                    public void lockInterruptibly() throws InterruptedException {

                    }

                    @Override
                    public boolean tryLock() {
                        return false;
                    }

                    @Override
                    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
                        return false;
                    }

                    @Override
                    public void unlock() {

                    }

                    @Override
                    public Condition newCondition() {
                        return null;
                    }
                };
                l.lock();
                methode();
                l.unlock();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String philosopher(){
        String philosopher = "philosopher ";
        return philosopher + banana;
    }

    public String action() {
        int entscheidung = r.nextInt(2);
        if(entscheidung == 0 && !left && !ate) {
            if (!left) {
                if (Forks.array[banana % Forks.array.length]) {
                    Forks.array[banana % Forks.array.length] = false;
                    left = true;
                    return "takes his left fork";
                }
            }
        }

        if((entscheidung == 1 || left) && !ate) {
            if (!right) {
                if (Forks.array[banana - 1]) {
                    Forks.array[banana - 1] = false;
                    right = true;
                    return "takes his right fork";
                }
            }
        }

        if(left && right && !ate){
            ate = true;
            return "eats";
        }
        if (ate && right){
            right = false;
            Forks.array[banana % Forks.array.length] = true;
            return "puts down his right fork";
        }
        if (ate){
            left = false;
            Forks.array[banana-1] = true;
            finished = true;
            return "puts down his left fork";
        }

        /*if(finished){
            left = false;
            right = false;
            return "put down his left";
        }*/


        i++;
        return "w8s";
    }

    public void methode(){

        System.out.println( philosopher() + " " + action() + ".");

    }

}
