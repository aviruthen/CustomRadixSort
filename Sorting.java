package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Sorting {

    /** Runner for comparing 4 sorting algorithms
     *
     * 1. Arrays.sort() implemented with TimSort
     * 2. aviSort() implemented with custom algorithm
     * 3. aviSort2() optimized version of aviSort()
     * 4. radix_array_sort() implementation of radix sort
     * Code found from educba.com: https://www.educba.com/radix-sort-java/ 
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        int numVals= 10;
        int stepSize= 1;

        int[] b= new int[numVals];

        ArrayList<Integer> list= new ArrayList<>(numVals);
        for (int i= 1; i <= numVals; i++ ) {
            list.add(i - 1);
        }

        Random rand= new Random();
        int k= 0;
        while (list.size() > 0) {
            int index= rand.nextInt(list.size());
            b[k]= list.remove(index);
            k++ ;
        }

        double time= 0;
        HashMap<Integer, Integer> d;

        time= System.nanoTime();
        Arrays.sort(b);
        System.out.println(System.nanoTime() - time);

        time= System.nanoTime();
        d= aviSort(b, stepSize);
        System.out.println(System.nanoTime() - time);

        time= System.nanoTime();
        d= aviSort2(b, stepSize);
        System.out.println(System.nanoTime() - time);

        time= System.nanoTime();
        radix_array_sort(b, b.length);
        System.out.println(System.nanoTime() - time);

    }

    /** Sorts array b inside of the HashMap ADT
     *
     * @param b        the integer array to be sorted
     * @param stepSize the bin increment size
     * @return a sorted version of array b stored in a HashMap The HashMap maps each integer value
     *         to its sorted place in the array */
    public static HashMap<Integer, Integer> aviSort(int[] b, int stepSize) {
        double time= System.nanoTime();
        HashSet<Integer> c= prepSort(b);
        int m= minValue(b);

        HashMap<Integer, Integer> d= new HashMap<>();

        int i= m;
        int valsUsed= 0;
        int size= c.size();

        while (valsUsed < size) { // !c.isEmpty()) {
            if (c.remove(i)) {
                d.put(i, d.size());
            }
            i+= stepSize;
            valsUsed++ ;
        }

        return d;

    }

    /** Puts each value of the integer array b in a HashSet
     *
     * Only used in the first version of "aviSort", later deemed unnecessary in more optimized
     * version
     *
     * @param b the integer array to be sorted
     * @return a HashSet containing all the values in b */
    public static HashSet<Integer> prepSort(int[] b) {
        HashSet<Integer> c= new HashSet<>();

        for (int elm : b)
            c.add(elm);

        return c;

    }

    /** Finds the minimum value in array b in O(n) time
     *
     * @param b the integer array
     * @return the minimum value in b */
    public static int minValue(int[] b) {
        int minVal= b[0];
        for (int elm : b)
            if (elm < minVal)
                minVal= elm;
        return minVal;
    }

    /** Sorts array b inside of the HashMap ADT
     *
     * @param b        the integer array
     * @param stepSize the amount to increment each bin size
     * @return a sorted version of array b stored in a HashMap The HashMap maps each integer value
     *         to its sorted place in the array */
    public static HashMap<Integer, Integer> aviSort2(int[] b, int stepSize) {
        int m= minValue(b);

        HashMap<Integer, Integer> d= new HashMap<>();

        for (int elm : b)
            d.put(elm, null);

        int i= m;
        int valsUsed= 0;
        int size= d.size();

        while (valsUsed < size) {
            d.replace(i, valsUsed);
            i+= stepSize;
            valsUsed++ ;
        }

        return d;

    }

    /** Method to obtain maximum value in radixArr[] (used in countSorting)
     *
     *
     * Code found from educba.com: https://www.educba.com/radix-sort-java/
     *
     * Using their implementation to compare */
    static int get_maxVal(int radixArr[], int arrLen) {
        int maxVal= radixArr[0];
        for (int i= 1; i < arrLen; i++ )
            if (radixArr[i] > maxVal)
                maxVal= radixArr[i];
        return maxVal;
    }

    /** Performs count sorting on radixArr
     *
     *
     * Code found from educba.com: https://www.educba.com/radix-sort-java/
     *
     * Using their implementation to compare to mine */
    static void countSorting(int radixArr[], int arrLen, int exp) {
        int resultArray[]= new int[arrLen];
        int i;
        int countVal[]= new int[10];
        Arrays.fill(countVal, 0);
        for (i= 0; i < arrLen; i++ )
            countVal[radixArr[i] / exp % 10]++ ;
        for (i= 1; i < 10; i++ )
            countVal[i]+= countVal[i - 1];
        for (i= arrLen - 1; i >= 0; i-- ) {
            resultArray[countVal[radixArr[i] / exp % 10] - 1]= radixArr[i];
            countVal[radixArr[i] / exp % 10]-- ;
        }
        for (i= 0; i < arrLen; i++ )
            radixArr[i]= resultArray[i];
    }

    /** Organize radix sort using count sorting
     *
     * Code found from educba.com: https://www.educba.com/radix-sort-java/
     *
     * Using their implementation to compare to mine */
    static void radix_array_sort(int radixArr[], int arrLen) {
        int m= get_maxVal(radixArr, arrLen);
        for (int exp= 1; m / exp > 0; exp*= 10)
            countSorting(radixArr, arrLen, exp);
    }

}
