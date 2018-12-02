package by.moiseenko.entity;

/**
 * Class that emulate car with next parameters <i>name</i>, <i>parkedTime</i>
 * and <i>maxWaitTime</i>
 * 
 * @author s-moiseenko
 */
public abstract class Car {

    protected long parkedTime;
    protected long maxWaitTime;
    protected int name;

    public Car(long maxWaitTime, long parkedTime, int name) {

	this.parkedTime = parkedTime;
	this.maxWaitTime = maxWaitTime;
	this.name = name;
    }

}
