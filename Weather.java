
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yibei
 */
class Weather {
    
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedDateTime = currentDateTime.format(format);
    
    public Weather() {
        
    }
    
    public boolean currentWeather(int i, Airplane airplane) {
        switch(i) {
            case 0: 
                System.out.println("[" + formattedDateTime + "] ATC: Current weather Thunderstorms - Departure " 
                        + airplane.getName() + " will be delay.");
                return false;
            case 1: 
                System.out.println("[" + formattedDateTime + "] ATC: Current weather Severe Turbulence - Departure " 
                        + airplane.getName() + " will be delay.");
                return false;
            case 2: 
                System.out.println("[" + formattedDateTime + "] ATC: Current weather Clear skies.");
                return true;
            case 3: 
                System.out.println("[" + formattedDateTime + "] ATC: Current weather Favourable atmospheric conditions.");
                return true;
        }
        return true;
    }
}
