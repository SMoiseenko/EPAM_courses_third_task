package by.moiseenko.entity;

import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

public class Pool {
    private static final Logger logger = Logger.getLogger(Pool.class);

    ReentrantLock lock = new ReentrantLock();
    Parking parking = new Parking(5);

    public Lot getLot(long maxWait) {

	Lot toReturn = null;
	Lot lot = parking.takeLot(maxWait); // пытаемся взять lot 7000 (вернем или lot или null)
	
	if (lot != null) { //если свободен - то вернем место
	    toReturn = lot;
	} else { // иначе умрем где?
	   
	    logger.debug(Thread.currentThread().getName() + " go away");
	}

	return toReturn;
    }
    
    public boolean leaveLot(Lot lot) {
	boolean toReturn = false;
	if (lock.tryLock()) {
	    toReturn = true;
	    lock.unlock();
	}
	
	return toReturn;
    }
    
}
