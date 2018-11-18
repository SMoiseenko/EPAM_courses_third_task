package by.moiseenko.service;

public class RandomTime {
    private RandomTime() {
    }

    public static int randomTime(int min, int max) {
	max -= min;
	return (int) (Math.random() * ++max) + min;
    }

}
