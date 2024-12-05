/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yibei
 */
class RefuellingTruck{

    static Semaphore TruckSem = new Semaphore(1);

    Airplane airplane;

    public RefuellingTruck(Airplane airplane) {
        this.airplane = airplane;
    }

    public void refuel() {

    try {
        TruckSem.acquire();

        System.out.println("[Refuelling Truck is available!] ");
        System.out.println("Refuelling Truck is refuelling " + airplane.getName());
        TimeUnit.SECONDS.sleep(2);
        System.out.println(airplane.getName() + " DONE REFUELLING");
    } catch (InterruptedException ex) {
        Logger.getLogger(RefuellingTruck.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
            TruckSem.release();
    }
}
}
    
