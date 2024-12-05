
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
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
public class AirplaneGenerator implements Runnable {

    Airport airport;
    AtomicInteger atomicInt = new AtomicInteger(1);

    public AirplaneGenerator(Airport airport) {
        this.airport = airport;
    }
        
    public void run() {
        while (atomicInt.get() <= 6 ) {
            Airplane airplane = new Airplane(airport);
            Date currentDate = new Date();
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(format);
            airplane.setInTime(formattedDateTime);
            Thread thairplane = new Thread(airplane);
            airplane.setName("Airplane " + atomicInt.get());
            airplane.setId(atomicInt.get());
            atomicInt.getAndIncrement();
            thairplane.start();
            try {
                Random rand = new Random();
                Thread.sleep(rand.nextInt(3000));
            } catch (InterruptedException ex) {
                Logger.getLogger(AirplaneGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
