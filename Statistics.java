
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Semaphore;
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
public class Statistics {
    
    private long startTime;
    private long endTime;
    ArrayList<Long> totalDurations = new ArrayList<>();
    long totalTime = 0;
    long avgTime = 0;
    int numberPassengers = 0;
    int numberPlane = 0;
    ArrayList<Gates> arrayGates;
    Gates gate;
    Airport airport;

    public Statistics() {

    }

    
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
     
    public long planeDuration() {
        return ((endTime - startTime)/1000000000);
    }
    

    public void totalDuration(long duration) {
        this.totalDurations.add(duration);
    }
    
    public void countPassenger() {
        numberPassengers++;
    }
    
    public void countAirplane() {
        numberPlane++;
    }
    
    
    void printStatistics(Gates gates, Airport airport) {
        System.out.println("\n-----------------------------------------------");
        System.out.println("\t\tAIRPORT STATUS");
        System.out.println("-----------------------------------------------");
        if(airport.checkRunway() == true)
            System.out.println("RUNWAY CLEAR");
        arrayGates = gates.gatesArray();
        for(int i = 0; i < arrayGates.size() ; i++) {
            if(arrayGates.get(i).isGateStatus() == true)
            {
                System.out.println("Gate " + arrayGates.get(i).getGatename() + " CLEAR");
            }
        }
        System.out.println("\n-----------------------------------------------");
        System.out.println("\t\tSTATISTICS");
        System.out.println("-----------------------------------------------");
        System.out.println("NO OF PLANES                    = " + numberPlane);
        System.out.println("NO OF PASSENGERS BOARDED        = " + numberPassengers);
        long min = Collections.min(totalDurations);
        long max = Collections.max(totalDurations);
        System.out.println("MINIMUM TURNAROUND TIME         = " + min + " seconds");
        System.out.println("MAXIMUM TURNAROUND TIME         = " + max + " seconds");
        for(long duration : totalDurations)
            totalTime += duration;
        avgTime = totalTime/totalDurations.size();
        System.out.println("AVERAGE TURNAROUND TIME         = " + avgTime + " seconds");
    }
}
