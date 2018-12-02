package by.moiseenko.service;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import by.moiseenko.entity.Car;
import by.moiseenko.entity.ParkingLot;
import by.moiseenko.entity.ParkingSpace;

/**
 * Class that extends from {@link Car} class, represent car as a thread. It try
 * to take common resource - one {@link ParkingLot} form some that located on
 * {@link ParkingSpace} not longer that <i>maxWaitTime</i>. If it possible,
 * car-thread sleep on this lot for <i>parkedTime</i> and go away. If time of
 * awaiting past, but no any empty lot on parking space, car will go away.
 * 
 * @author moiseenko-s
 * @see Car
 * @see ParkingLot
 * @see ParkingSpace
 */

public class CarService extends Car implements Runnable {
    private static final Logger logger = Logger.getLogger(CarService.class);

    private ParkingSpace parkingSpace;
    private Semaphore semaphore;
    private ReentrantLock locker;

    public CarService(Semaphore semaphore, ReentrantLock locker, ParkingSpace parkingSpace, long maxWaitTime,
	    long parkedTime, int name) {
	super(maxWaitTime, parkedTime, name);
	this.locker = locker;
	this.parkingSpace = parkingSpace;
	this.semaphore = semaphore;

    }

    @Override
    public void run() {
	Thread.currentThread().setName("Car №" + name);
	logger.debug(Thread.currentThread().getName() + " created.");
	ParkingLot lot = tryTakeLot();

	if (lot != null) {
	    logger.debug(Thread.currentThread().getName() + " parked at lot №" + lot.getLotID() + ".");
	    parkingLotUsed();
	    parkedAtLot();
	    leaveLot(lot);
	    logger.debug(Thread.currentThread().getName() + " leave lot №" + lot.getLotID() + ".");
	} else {
	    logger.debug(Thread.currentThread().getName() + " can't wait anymore and drive away.");
	}
    }

    public ParkingLot tryTakeLot() {
	ParkingLot hasEmptyLot = null;
	try {
	    if (semaphore.tryAcquire(maxWaitTime, TimeUnit.MILLISECONDS)) {
		hasEmptyLot = parkingSpace.getParkingLots().poll();
	    }
	} catch (InterruptedException e) {
	    logger.error("Thread was interrupted!!!");
	    Thread.currentThread().interrupt();
	}

	return hasEmptyLot;
    }

    public void parkingLotUsed() {
	locker.lock();
	parkingSpace.parkedCarCounter();
	locker.unlock();
    }

    public void parkedAtLot() {
	try {
	    Thread.sleep(parkedTime);
	} catch (InterruptedException e) {
	    logger.error("Thread was interrupted!!!");
	    Thread.currentThread().interrupt();
	}
    }

    public void leaveLot(ParkingLot lot) {
	parkingSpace.returnLot(lot);
	semaphore.release();
    }

}
