package by.moiseenko.entity;

import org.apache.log4j.Logger;

/**
 * @author moiseenko-s
 *
 */
public class Car extends Thread {
    private static final Logger logger = Logger.getLogger(Car.class);

    private boolean parkedAtLot;
    private Pool carPool;

    public Car(Pool carPool, String name) {
	this.carPool = carPool;
	this.setName(name);
	logger.debug(this.getName() + " created.");
    }

    public void run() {
	Lot lot = null;
	while (lot == null) { // нет парковки, ждем 7000

	    logger.debug(this.getName() + " waiting for parking");

	    lot = carPool.getLot(7000);//
	}

	logger.debug(this.getName() + " parked at lot № " + lot.getLotID());

	parkedAtLot = true;
	lot.parkedAtLot();
	parkedAtLot = false;
	carPool.leaveLot(lot);
	logger.debug(this.getName() + " leave lot № " + lot.getLotID());
	
/*
	while (true) {
	    if (carPool.leaveLot(lot)) {
		logger.debug(this.getName() + " leave lot № " + lot.getLotID());
		break;
	    }
	}*/

    }

}
