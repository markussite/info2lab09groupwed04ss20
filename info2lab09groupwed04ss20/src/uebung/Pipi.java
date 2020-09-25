package uebung;

import java.util.Random;

public class Pipi {
    private int[] punktx;       //X-Koordinate
    private int[] punkty;       //Y-Koordinate
    private int a;              //Koordinaten Rechteck
    private int r;              //radius Kreis



    public Pipi(int n){
        /* Aq = a²          Aq = Square
           Ac = pi * (a/2)² Ac = Circle
           pi = 4 * Ac/Aq
         */
        a = n;
        r = a/2;
    }

    public static void main(String[] args){

        Pipi pi = new Pipi(Integer.parseInt(args[0]));
        double medianPi = 0.0;
        for(int i=0; i<1000;i++){
            medianPi += pi.computePi(Integer.parseInt(args[0]));
        }
        medianPi = medianPi /1000;
        System.out.println(medianPi);

    }
    public double computePi(int n){
        punkteAnlegen(n);
        for(int i=0; i<n ;i++){
            randomPointS(i);
        }
        int punkteImKreis = istImKreisOderNicht(n);
        //System.out.println("Anzahl der Punkte im Kreis: "+punkteImKreis);
        double pi = 4*((double)punkteImKreis/(double)n);
        return pi;

    }
    public void randomPointS(int n){
        Random rndm = new Random();

        int x = rndm.nextInt(a);
        int y = rndm.nextInt(a);
        punktx[n] = x;
        punkty[n] = y;
        //System.out.println(x + " " + y);
    }

    public void punkteAnlegen(int n){
        punktx = new int[n];
        punkty = new int[n];
    }
    public int istImKreisOderNicht(int n){
        int counter = 0;
        for(int i = 0; i < n;i++) {
            if (Math.pow(r, 2) >= Math.pow(punktx[i]-r, 2) + Math.pow(punkty[i]-r, 2)) {
                counter++;
            }
        }
        return counter;
    }
}
