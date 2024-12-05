
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
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
class Crew {

    Airplane airplane;
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedDateTime = currentDateTime.format(format);

    public Crew(Airplane airplane) {
        this.airplane = airplane;
    }

    public void clean() {
        System.out.println(airplane.getName() + " is getting to clean!");
        for (int i = 1; i <= 4; i++) {
            switch (i) {
                case 1:
                    System.out.println(airplane.getName() + " CLEANING -- 25%");
                    break;
                case 2:
                    System.out.println(airplane.getName() + " CLEANING -- 50%");
                    break;
                case 3:
                    System.out.println(airplane.getName() + " CLEANING -- 75%");
                    break;
                case 4:
                    System.out.println(airplane.getName() + " CLEANING -- 100%");
                    break;
            }
            try {
                TimeUnit.SECONDS.sleep(1/2*1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Crew.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(airplane.getName() + " refilling supplies!");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Crew.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("[" + formattedDateTime + "]" + "ATC: " + airplane.getName() + " READY FOR BOARDING TIME");
    }
}
