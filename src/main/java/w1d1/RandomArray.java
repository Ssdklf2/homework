package w1d1;

import java.util.Arrays;

public class RandomArray {
    private final int[] array = new int[10];
    public int[] getArray() {
        return array;
    }
    @Override
    public String toString() {
        return "RandomArray: " + Arrays.toString(array);
    }
    public void randomNums(int[] array){
        for (int i = 0; i<array.length; i++){
            array[i] = (int) (Math.random() * 0.05);
        }
    }
}
