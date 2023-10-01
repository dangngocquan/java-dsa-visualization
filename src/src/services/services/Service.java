package src.services.services;

import javax.swing.*;
import java.awt.*;

public class Service {
    public static JFrame getFrame(Component theComponent) {
        Component currParent = theComponent;
        JFrame theFrame = null;
        while (currParent != null) {
            if (currParent instanceof JFrame) {
                theFrame = (JFrame) currParent;
                break;
            }
            currParent = currParent.getParent();
        }
        return theFrame;
    }

    public static int createRandomNumber(int from, int to) {
        int range = to - from;
        int randomNum = (int) (Math.floor(Math.random() * range)) + from;
        return randomNum;
    }

    public static int[] createRandomArray(int length, int from, int to) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) array[i] = createRandomNumber(from, to);
        return array;
    }

    public static void shuffleArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int j = (int) Math.floor(Math.random() * array.length);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
