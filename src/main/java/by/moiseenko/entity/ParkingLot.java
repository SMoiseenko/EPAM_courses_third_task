package by.moiseenko.entity;

/**
 * Class that emulate single parking lot with their id;
 * 
 * @author moiseenko-s
 *
 */
public class ParkingLot {

    private int lotID;

    public ParkingLot(int lotID) {
	super();
	this.lotID = lotID;
    }

    public int getLotID() {
	return lotID;
    }

}