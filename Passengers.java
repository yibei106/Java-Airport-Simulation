
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author yibei
 */
class Passengers {

    Airplane airplane;
    Random rand = new Random();

    public Passengers(Airplane airplane) {
        this.airplane = airplane;
    }

    public void disembark() {
        try {
            System.out.println(airplane.getName() + " is disembarking!");
            for (int i = 1; i <= 15; i++) {
                System.out.println(airplane.getName() + " : Disembarking Passenger " + i);
                Thread.sleep(500);
                if (i == rand.nextInt(15)) {
                    System.out.println(">>>>> " + airplane.getName() +" : BAGGAGE HANDLING >>>>>");
                    Thread.sleep(500);
                }
            };
        } catch (InterruptedException ex) {
            Logger.getLogger(Passengers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void embark(Statistics stats) throws InterruptedException {
        System.out.println(airplane.getName() + " is embarking!");
        for (int i = 1; i <= 20; i++) {
            try {
                synchronized(this) {
                stats.countPassenger();}
                System.out.println(airplane.getName() + " : Embarking Passenger " + i);
                Thread.sleep(500);
                if (i == rand.nextInt(15)) {
                    System.out.println("<<<<< " + airplane.getName() +" : BAGGAGE HANDLING <<<<<");
                    Thread.sleep(500);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Passengers.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}


