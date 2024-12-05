
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author yibei
 */
class Gates {

    Gates gate;
    private String gateName;
    private boolean gateStatus;
    public static ArrayList<Gates> arrayGates = new ArrayList<>();  //add static so can share 1 array

    public Gates(String gateName, boolean gateStatus) {
        this.gateName = gateName;
        this.gateStatus = gateStatus;
    }

    public Gates() {   //to access the methods
    }

    public Gates getGates() {
        return gate;
    }

    public boolean isGateStatus() {
        return gateStatus;
    }

    public String getGatename() {
        return gateName;
    }

    public void setGateStatus(boolean gateStatus) {
        this.gateStatus = gateStatus;
    }

    public ArrayList<Gates> gatesArray() {
        return arrayGates;
    }

    public void generateName() {

        for (char i = 'A'; i <= 'C'; i++) {
            Gates gate = new Gates(String.valueOf(i), true);
            arrayGates.add(gate);
        }
    }

    public synchronized Gates checkGates() throws InterruptedException {
        for (int i = 0; i < arrayGates.size(); i++) {
            if (arrayGates.get(i).isGateStatus()) {
                arrayGates.get(i).setGateStatus(false);
                return arrayGates.get(i);
            }
        }
        return null;
    }

    public synchronized void leaveGate(String gateName, Airplane airplane, Statistics stats) {
        for (int i = 0; i < arrayGates.size(); i++) {
            if (arrayGates.get(i).getGatename().equals(gateName)) {
                arrayGates.get(i).setGateStatus(true);
            }
        }
    }

}
