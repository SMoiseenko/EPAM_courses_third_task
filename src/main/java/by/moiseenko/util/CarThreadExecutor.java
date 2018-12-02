package by.moiseenko.util;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import by.moiseenko.entity.ParkingLot;
import by.moiseenko.entity.ParkingSpace;
import by.moiseenko.service.CarService;

/**
 * Utility class that create all necessary instances and run car-threads.
 * Receive next parameters:<br>
 * <b>int</b> <i>qtyOfCar</i> - quantity of the car(s) that needed to run;<br>
 * <b>int</b> <i>qtyOfParkingLot</i> - quantity of the Lot(s) at the Parking
 * Space<br>
 * <b>long</b> <i>maxWaitTime</i> - time in milliseconds, that car can maximum
 * wait;<br>
 * <b>long</b> <i>parkedTime</i> - time in milliseconds, that car will stay on
 * the lot.
 * 
 * @author s-moiseenko
 * @see ParkingLot
 * @see ParkingSpace
 * @see CarService
 */
public class CarThreadExecutor {
    private static final Logger logger = Logger.getLogger(CarThreadExecutor.class);

    public int carThreadRunner(int qtyOfCar, int qtyOfParkingLot, long maxWaitTime, long parkedTime) {

	ReentrantLock locker = new ReentrantLock();
	Semaphore semaphore = new Semaphore(qtyOfParkingLot, true);
	ExecutorService es = Executors.newCachedThreadPool();

	Queue<ParkingLot> parkingLots = new ConcurrentLinkedQueue<>();
	for (int i = 0; i < qtyOfParkingLot; i++)
	    parkingLots.add(new ParkingLot(i));

	ParkingSpace newParking = new ParkingSpace(parkingLots);

	for (int i = 0; i < qtyOfCar; i++) {
	    es.execute(new CarService(semaphore, locker, newParking, maxWaitTime, parkedTime, i));
	}
	es.shutdown();
	try {
	    es.awaitTermination(1L, TimeUnit.MINUTES);
	} catch (InterruptedException e) {
	    logger.error("Thread was interrupted!!!");
	    Thread.currentThread().interrupt();
	}
	return newParking.getCounter();
    }
}
