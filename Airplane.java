/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yibei
 */
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

class Airplane implements Runnable {

    String name;
    String inTime;
    boolean emergency;
    int id;

    Airport airport;

    public Airplane(Airport airport) {
        this.airport = airport;
    }

    public String getName() {
        return name;
    }

    public String getInTime() {
        return inTime;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void run() {
        addIntoLandingQueue();
    }

    private synchronized void addIntoLandingQueue() {
            try {
                airport.add(this);
            } catch (InterruptedException ex) {
                Logger.getLogger(Airplane.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
