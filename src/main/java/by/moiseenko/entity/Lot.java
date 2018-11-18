package by.moiseenko.entity;

import org.apache.log4j.Logger;

import by.moiseenko.service.RandomTime;

/**
 * @author moiseenko-s
 *
 */
public class Lot {
    private static final Logger logger = Logger.getLogger(Lot.class);
    private int lotID;

    public Lot(int lotID) {
	this.lotID = lotID;
    }

    public int getLotID() {
	return lotID;
    }

    public void parkedAtLot() {
	try {
	    logger.debug("Parking Lot № " + lotID + " using by car " + Thread.currentThread().getName());
	    Thread.sleep(RandomTime.randomTime(300, 500));
	} catch (InterruptedException e) {
	    logger.error("Thread was interrupted!!!");
	}
    }

}
