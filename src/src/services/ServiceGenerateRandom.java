package src.services;

public class ServiceGenerateRandom extends Service {
    public static int createRandomNumber(int from, int to) {
        int range = to - from;
        return (int) (Math.floor(Math.random() * range)) + from;
    }

    public static int[] createRandomArray(int length, int from, int to) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) array[i] = createRandomNumber(from, to);
        return array;
    }
}
