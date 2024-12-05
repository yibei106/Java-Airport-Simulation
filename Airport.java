/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yibei
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

class Airport extends Thread {

    static Lock lock = new ReentrantLock();
    LinkedBlockingDeque<Airplane> landingQueue;
    private static boolean runway = true;
    static ExecutorService executor = Executors.newFixedThreadPool(3);
    Statistics stats = new Statistics();
    private volatile boolean ending = false;
    private int planeCount = 0;
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedDateTime = currentDateTime.format(format);
    Random rand = new Random();

    public Airport() {
        landingQueue = new LinkedBlockingDeque<Airplane>();
    }

    public int getPlaneCount() {
        return planeCount;
    }

    public Statistics getStatistics() {
        return stats;
    }

    public void setClosingTime(boolean ending) {
        this.ending = ending;
    }

    public boolean checkRunway() {
        return runway;
    }

    public void run() {
        while (planeCount <= 6) {
            try {
                landing();
                if (ending || planeCount >= 6) {
                    break;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Airport.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        executor.shutdown();
    }

    public void landing() throws InterruptedException {
        Airplane airplane;
        Gates gate;
        final Gates gates = new Gates();  //because if inside the synchronized, it will not sharing the same resources.
        synchronized (landingQueue) {
            while (landingQueue.isEmpty() && !ending) {
                landingQueue.wait();
            }
            if (ending) {
                return;
            }
        }
        gate = gates.checkGates();
        while (gate == null) {
            Thread.sleep(500);
            gate = gates.checkGates();
        }
        synchronized (this) {
            while (!runway) {
                Thread.sleep(500);
            }
        }
        airplane = landingQueue.poll();
        System.out.println("[" + airplane.getInTime() + "]" + " ATC: " + airplane.getName() + " can use the runway.");
        usingRunway(airplane, lock);
        System.out.println("[" + formattedDateTime + "]" + " ATC: " + airplane.getName() + " can dock to " + gate.getGatename());
        System.out.println("ATC: " + airplane.getName() + " - COASTED TO Gate " + gate.getGatename());
        final Gates gateFinal = gate;
        final Airplane airFinal = airplane;
        executor.execute(() -> {
            activities(airFinal, gateFinal);
            stats.countAirplane();
        });
        
        planeCount++;
    }

    public void usingRunway(Airplane airplane, Lock lock) throws InterruptedException {
        lock.lock();
        try {
            System.out.println("ATC: " + airplane.getName() + " - USING RUNWAY.");
            runway = false;
            TimeUnit.SECONDS.sleep(1);
            System.out.println("ATC: " + airplane.getName() + " - LEFT RUNWAY.");
            runway = true;
        } finally {
            lock.unlock();
        }
    }

    public void activities(Airplane airplane, Gates gate) {
        Gates gates = new Gates();
        Passengers passenger = new Passengers(airplane);
        RefuellingTruck truck = new RefuellingTruck(airplane);
        Crew crew = new Crew(airplane);
        Weather weather = new Weather();
        passenger.disembark();
        truck.refuel();
        crew.clean();
        boolean delay = weather.currentWeather(rand.nextInt(4), airplane);
        if(delay == false)
            try {
                Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Airport.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            passenger.embark(stats);
        } catch (InterruptedException ex) {
            Logger.getLogger(Airport.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            synchronized (this) {
                while (!runway) {
                    Thread.sleep(500);
                }
            }
            usingRunway(airplane, lock);
            gates.leaveGate(gate.getGatename(), airplane, stats);
            System.out.println(airplane.getName() + " SUCCESSFULLY DEPART :)");
            stats.setEndTime(System.nanoTime());
            long duration = stats.planeDuration();
            stats.totalDuration(duration);
            System.out.println("==================================");
            System.out.println(airplane.getName() + " USED TIME = " + duration + " seconds");
            System.out.println("==================================");
        } catch (InterruptedException ex) {
            Logger.getLogger(Airport.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void add(Airplane airplane) throws InterruptedException {
        synchronized (landingQueue) {
            stats.setStartTime(System.nanoTime());
            if (planeCount >= 6) {
                return;
            }
            if (airplane.getId() == 6) {
                landingQueue.offerFirst(airplane);
                System.out.println("[" + airplane.getInTime() + "] " + airplane.getName() + ": REQUEST FOR EMERGENCY LANDING");
            } else {
                if (airplane.getId() > 3) {
                    landingQueue.offerLast(airplane);
                    System.out.println("[" + airplane.getInTime() + "] " + airplane.getName() + " REQUEST FOR LANDING.");
                } else {
                    ((LinkedBlockingDeque<Airplane>) landingQueue).offer(airplane);
                    System.out.println("[" + airplane.getInTime() + "] " + airplane.getName() + ": REQUEST FOR LANDING!");
                }
            }
            if (landingQueue.size() == 1) {
                landingQueue.notify();
            }
        }
    }
}
