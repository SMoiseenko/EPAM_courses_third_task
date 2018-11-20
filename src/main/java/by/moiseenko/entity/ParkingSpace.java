package by.moiseenko.entity;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * 
 * @author moiseenko-s
 *
 */
public class ParkingSpace {
    private static final Logger logger = Logger.getLogger(ParkingSpace.class);

    private Queue<ParkingLot> parkingLots;
    private Semaphore semaphore;

    public ParkingSpace(int parkingLotQty) {
	this.semaphore = new Semaphore(parkingLotQty, true);
	this.parkingLots = new ConcurrentLinkedQueue<ParkingLot>();
	for (int i = 0; i < parkingLotQty; i++) 
		   parkingLots.add(new ParkingLot(i));
	
    }

    public void setParkingLots(Queue<ParkingLot> parkingLots) {
	this.parkingLots = parkingLots;
    }

    public ParkingLot tryTakeLot(long maxWaitTime) {
	ParkingLot hasEmptyLot = null;
	try {
	    if (semaphore.tryAcquire(3000L, TimeUnit.MILLISECONDS)) {
		hasEmptyLot = parkingLots.poll();
	    }
	} catch (InterruptedException e) {
	    logger.error("Thread was interrupted!!!");
	}

	return hasEmptyLot;
    }

    public void leaveLot(ParkingLot lot) {
	semaphore.release();
	parkingLots.add(lot);
    }
}
