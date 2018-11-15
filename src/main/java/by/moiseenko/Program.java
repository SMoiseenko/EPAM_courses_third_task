package by.moiseenko;

import java.util.concurrent.Semaphore;

import by.moiseenko.entity.Car;

public class Program {
	public static void main(String[] args) {

		Semaphore semaphore = new Semaphore(2);

		try {
			Car car = null;
			for (int a = 1; a < 10; a++) {
				car = new Car(semaphore, a);
				car.start();
				Thread.sleep(100);
			}
			car.join();
		} catch (InterruptedException e) {
			System.out.println("Park was destroyed");
		}

		System.out.println("No more cars");
	}
}
