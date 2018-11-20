package by.moiseenko.entity;

import org.apache.log4j.Logger;

/**
 * @author moiseenko-s
 *
 */
public class Car extends Thread {
    private static final Logger logger = Logger.getLogger(Car.class);

    private ParkingSpace carPatking;

    public Car(ParkingSpace carPatking, int name) {
	this.carPatking = carPatking;
	this.setName("Car №" + name);
	logger.debug(this.getName() + " created.");
    }
@Override
    public void run() {

	//logger.debug(this.getName() + " waiting for parking.");
	ParkingLot lot = carPatking.tryTakeLot(3000L);//

	if (lot != null) {
	    logger.debug(this.getName() + " parked at lot №" + lot.getLotID() + ".");
	    lot.parkedAtLot();
	    carPatking.leaveLot(lot);
	    logger.debug(this.getName() + " leave lot №" + lot.getLotID() + ".");
	} else {
	    logger.debug(this.getName() + " can't wait anymore and drive away.");
	}
    }

}
