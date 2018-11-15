package by.moiseenko.entity;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author moiseenko-s
 *
 */
public class Car extends Thread {
	Semaphore semaphore;
	int carId = 0;

	public Car(Semaphore semaphore, int carId) {
		this.semaphore = semaphore;
		this.carId = carId;
		System.out.printf("Car %d had come.\n", carId);
	}

	public void run() {
		try {
			semaphore.acquire();
			System.out.printf("Car %d parked.\n", carId);
			Thread.sleep((long) (Math.random() * 3000) + 500);
		} catch (InterruptedException e) {
			System.out.printf("Car was anigilated!!!\n");
		}
		semaphore.release();
		System.out.printf("Car %d left parking.\n", carId);
	}

}
