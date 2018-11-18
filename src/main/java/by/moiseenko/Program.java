package by.moiseenko;

import org.apache.log4j.Logger;

import by.moiseenko.entity.Car;
import by.moiseenko.entity.Pool;
import by.moiseenko.service.RandomTime;

public class Program {
    private static final Logger logger = Logger.getLogger(Program.class);

    public static void main(String[] args) {

	Pool carPool = new Pool();
	

	for (int i = 0; i < 10; i++) {
	    new Car(carPool, "Car № " + i).start();
	    try {
		Thread.sleep(RandomTime.randomTime(50, 100));
	    } catch (InterruptedException e) {
		logger.error("Thread was interrupted!!!");
	    }
	}

    }
}
