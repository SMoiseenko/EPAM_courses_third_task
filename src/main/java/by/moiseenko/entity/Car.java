package by.moiseenko.entity;

import org.apache.log4j.Logger;

/**
 * @author moiseenko-s
 *
 */
public class Car extends Thread {
    private static final Logger logger = Logger.getLogger(Car.class);

    private Pool carPool;

    public Car(Pool carPool, String name) {
	this.carPool = carPool;
	this.setName(name);
	logger.debug(this.getName() + " created.");
    }

    public void run() {

	logger.debug(this.getName() + " waiting for parking.");
	Lot lot = carPool.getLot(7000);//

	if (lot != null) {
	    logger.debug(this.getName() + " parked at lot № " + lot.getLotID() + ".");
	    lot.parkedAtLot();
	    carPool.leaveLot(lot);
	    logger.debug(this.getName() + " leave lot № " + lot.getLotID() + ".");
	} else {
	    logger.debug(this.getName() + " can't wait anymore and drive away.");
	}
    }

}
