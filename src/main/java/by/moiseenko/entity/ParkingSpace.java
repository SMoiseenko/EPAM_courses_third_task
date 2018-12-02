package by.moiseenko.entity;

import java.util.Queue;

/**
 * Class that emulate parking space with list of parking lots and this lots
 * using counter ;
 * 
 * @author moiseenko-s
 */

public class ParkingSpace {
    private int counter;
    private Queue<ParkingLot> parkingLots;

    public ParkingSpace(Queue<ParkingLot> parkingLots) {
	super();
	this.parkingLots = parkingLots;
    }

    public Queue<ParkingLot> getParkingLots() {
	return parkingLots;
    }

    public void returnLot(ParkingLot lot) {
	parkingLots.add(lot);
    }

    public int getCounter() {
	return counter;
    }

    public void parkedCarCounter() {
	counter++;
    }
}
