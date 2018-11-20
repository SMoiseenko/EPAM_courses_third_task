package by.moiseenko.entity;

import org.apache.log4j.Logger;

/**
 * Class that emulate parking lot occupied by car for 2000ms;
 * @author moiseenko-s
 *
 */
public class ParkingLot {
    private static final Logger logger = Logger.getLogger(ParkingLot.class);
    private int lotID;

    public ParkingLot(int lotID) {
	this.lotID = lotID;
    }

    public int getLotID() {
	return lotID;
    }

    public void parkedAtLot() {
	try {
	    Thread.sleep(2000);
	} catch (InterruptedException e) {
	    logger.error("Thread was interrupted!!!");
	}
    }

}
