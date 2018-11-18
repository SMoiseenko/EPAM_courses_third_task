package by.moiseenko.entity;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * @author moiseenko-s
 *
 */
public class Parking {
    private static final Logger logger = Logger.getLogger(Parking.class);

    //private int parkingLotQty;
    private Queue<Lot> parkingLots;
    private Semaphore semaphore;

    public Parking(int parkingLotQty) {
	//this.parkingLotQty = parkingLotQty;
	this.semaphore = new Semaphore(parkingLotQty, true);
	this.parkingLots = new ConcurrentLinkedQueue<>();
	for (int i = 0; i < parkingLotQty; i++) {
	    parkingLots.add(new Lot(i));
	}
	
    }

    public Lot takeLot(long maxWaitTime) {
	Lot toReturn = null;
	try {
	    if (semaphore.tryAcquire(maxWaitTime, TimeUnit.MILLISECONDS)) {
		Lot lot = parkingLots.poll();
		toReturn = lot;
	    }
	} catch (InterruptedException e) {
	    logger.error("Thread was interrupted!!!");
	}

	return toReturn;
    }

    public boolean leavelot(Lot lot) {
	return parkingLots.add(lot);
    }
}
