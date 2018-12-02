package by.moiseenko;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.moiseenko.util.CarThreadExecutor;

public class TestCarThreadExecutor {
    private static final Logger logger = Logger.getLogger(TestCarThreadExecutor.class);

    @DataProvider
    public static Object[][] carThreadRunnerData() {
	return new Object[][] { { 15, 5, 3000L, 2000L, 10 }, { 15, 1, 1500L, 100L, 15 }, { 10, 3, 100L, 200L, 3 }, };
    }

    @Test(dataProvider = "carThreadRunnerData")
    public void testCarThreadRunner(int qtyOfCar, int qtyOfParkingLot, long maxWaitTime, long parkedTime,
	    int expectedResult) {
	// WHEN
	int acrualResult = new CarThreadExecutor().carThreadRunner(qtyOfCar, qtyOfParkingLot, maxWaitTime, parkedTime);
	// THEN
	logger.debug("Total parked car actual = " + acrualResult);
	logger.debug("Total parked car expected = " + expectedResult);
	Assert.assertEquals(acrualResult, expectedResult);
    }

}
