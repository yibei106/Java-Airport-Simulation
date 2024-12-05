
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author yibei
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Airport airport = new Airport();
        airport.start();
        Gates gates = new Gates();
        gates.generateName();
        AirplaneGenerator ag = new AirplaneGenerator(airport);
        Statistics stats = airport.getStatistics();
        Thread thag = new Thread(ag);
        thag.start();
        try {
            thag.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (airport.getPlaneCount() < 6) {
                Thread.sleep(1000); // Sleep for 1 second before checking again
            }
            airport.setClosingTime(true);
            airport.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Wait for the executor to complete
        try {
            airport.executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        stats.printStatistics(gates, airport);
    }
}
