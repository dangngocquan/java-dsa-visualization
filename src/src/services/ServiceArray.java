package src.services;

public class ServiceArray extends Service {
    public static void shuffleArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int j = (int) Math.floor(Math.random() * array.length);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
