import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Davis Leeth (dkl0011@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  8/31/21
*
*/
public final class Selector {

    /**
     * Can't instantiate this class.
     *
     * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
     *
     */
   private Selector() { }


    /**
     * Selects the minimum value from the array a. This method
     * throws IllegalArgumentException if a is null or has zero
     * length. The array a is not changed by this method.
     */
   public static int min(int[] a) {
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException();
      }
      int min = a[0];
      for (int i = 0; i < a.length; i++) {
         if (a[i] < min) {
            min = a[i];
         }
      }
      return min;
   }


    /**
     * Selects the maximum value from the array a. This method
     * throws IllegalArgumentException if a is null or has zero
     * length. The array a is not changed by this method.
     */
   public static int max(int[] a) {
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException();
      }
      int max = a[0];
      for (int i = 0; i < a.length; i++) {
         if (a[i] > max) {
            max = a[i];
         }
      }
      return max;
   }


    /**
     * Selects the kth minimum value from the array a. This method
     * throws IllegalArgumentException if a is null, has zero length,
     * or if there is no kth minimum value. Note that there is no kth
     * minimum value if k < 1, k > a.length, or if k is larger than
     * the number of distinct values in the array. The array a is not
     * changed by this method.
     */
   public static int kmin(int[] a, int k) {
      if ((a == null) || (a.length == 0) || (k < 1) || (k > a.length)) {
         throw new IllegalArgumentException();
      }
      int[] copy = Arrays.copyOf(a, a.length);
      Arrays.sort(copy);
      int same = 0;
      for (int i = 0; i < copy.length - 1; i++) {
         if (copy[i] == copy[i+1]) {
            same++;
         }
      }
      if (k > a.length - same) {
         throw new IllegalArgumentException();
      }
      for (int i = 0; i < k - 1; i++) {
         if (copy[i] == copy[i + 1]) {
            k++;
         }
      }
      return copy[k - 1];
   }


    /**
     * Selects the kth maximum value from the array a. This method
     * throws IllegalArgumentException if a is null, has zero length,
     * or if there is no kth maximum value. Note that there is no kth
     * maximum value if k < 1, k > a.length, or if k is larger than
     * the number of distinct values in the array. The array a is not
     * changed by this method.
     */
   public static int kmax(int[] a, int k) {
      if ((a == null) || (a.length == 0) || (k < 1) || (k > a.length)) {
         throw new IllegalArgumentException();
      }
      int[] copy = Arrays.copyOf(a, a.length);
      Arrays.sort(copy);
      int same = 0;
      for (int i = 0; i < copy.length - 1; i++) {
         if (copy[i] == copy[i+1]) {
            same++;
         }
      }
      if (k > a.length - same) {
         throw new IllegalArgumentException();
      }
      for (int i = 0; i < a.length; i++) {
         copy[i] = -1 * copy[i];
      }
      Arrays.sort(copy);
      for (int i = 0; i < a.length; i++) {
         copy[i] = -1 * copy[i];
      }
      for (int i = 0; i < k - 1; i++) {
         if (copy[i] == copy[i + 1]) {
            k++;
         }
      }         
      return copy[k - 1];
   }


    /**
     * Returns an array containing all the values in a in the
     * range [low..high]; that is, all the values that are greater
     * than or equal to low and less than or equal to high,
     * including duplicate values. The length of the returned array
     * is the same as the number of values in the range [low..high].
     * If there are no qualifying values, this method returns a
     * zero-length array. Note that low and high do not have
     * to be actual values in a. This method throws an
     * IllegalArgumentException if a is null or has zero length.
     * The array a is not changed by this method.
     */
   public static int[] range(int[] a, int low, int high) {
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException();
      }
      int count = 0;
      for (int i = 0; i < a.length; i++) {
         if ((a[i] >= low) && (a[i] <= high)) {
            count++;
         }
      }
      int c = 0;
      int[] copy = new int[count];
      for (int i = 0; i < a.length; i++) {
         if ((a[i] >= low) && (a[i] <= high)) {
            copy[c] = a[i];
            c++;
         }
      }
      return copy;
   }


    /**
     * Returns the smallest value in a that is greater than or equal to
     * the given key. This method throws an IllegalArgumentException if
     * a is null or has zero length, or if there is no qualifying
     * value. Note that key does not have to be an actual value in a.
     * The array a is not changed by this method.
     */
   public static int ceiling(int[] a, int key) {
      if ((a == null) || (a.length == 0) || (key > max(a))) {
         throw new IllegalArgumentException();
      }
      int count = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] == key) {
            return key;
         }
         if (a[i] > key) {
            count++;
         }
      }
      int u = 0;
      int[] possible = new int[count];
      for (int i = 0; i < a.length; i++) {
         if (a[i] > key) {
            possible[u] = a[i];
            u++;
         }
      }
      return min(possible);
   }


    /**
     * Returns the largest value in a that is less than or equal to
     * the given key. This method throws an IllegalArgumentException if
     * a is null or has zero length, or if there is no qualifying
     * value. Note that key does not have to be an actual value in a.
     * The array a is not changed by this method.
     */
   public static int floor(int[] a, int key) {
      if ((a == null) || (a.length == 0) || (key < min(a))) {
         throw new IllegalArgumentException();
      }
      int count = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] == key) {
            return key;
         }
         if (a[i] < key) {
            count++;
         }
      }
      int u = 0;
      int[] possible = new int[count];
      for (int i = 0; i < a.length; i++) {
         if (a[i] < key) {
            possible[u] = a[i];
            u++;
         }
      }
      return max(possible);
   }

}
