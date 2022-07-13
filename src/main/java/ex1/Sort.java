package ex1;

import java.util.Random;

public class Sort{
    public void quickSort(int[] array, int iStart, int iEnd){
        if (iStart >= iEnd){
            return;
        }
        int iPivot = new Random().nextInt(iEnd - iStart) + iStart;
        int pivot = array[iPivot];
        swap(array, iPivot, iEnd);

        int leftP = iStart;
        int rightP = iEnd;

        while(leftP < rightP) {
            while(array[leftP] <= pivot && leftP < rightP){
                leftP++;
            }
            while(array[rightP] >= pivot && leftP < rightP){
                rightP--;
            }
            swap(array, leftP, rightP);
        }
        swap(array, leftP, iEnd);
        quickSort(array, iStart, leftP - 1);
        quickSort(array, leftP + 1, iEnd);
    }
    private static void swap(int[] array, int ind1, int ind2) {
        int x = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = x;
    }
}