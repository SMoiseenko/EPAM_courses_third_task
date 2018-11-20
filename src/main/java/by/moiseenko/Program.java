package by.moiseenko;

import by.moiseenko.entity.Car;
import by.moiseenko.entity.ParkingSpace;

/**
 * 
 * @author s-moiseenko
 *
 */
public class Program {
    public static void main(String[] args) {

	ParkingSpace newParking = new ParkingSpace(5);
	for (int i = 0; i < 15; i++) {
	    new Car(newParking, i).start();
	}
    }
}
