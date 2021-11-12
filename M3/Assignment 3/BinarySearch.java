import java.util.Arrays;
import java.util.Comparator;

/**
 * Binary search.
 */
public class BinarySearch {

   /**
    * Returns the index of the first key in a[] that equals the search key, 
    * or -1 if no such key exists. This method throws a NullPointerException
    * if any parameter is null.
    */
   public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
      if (a == null || key == null || comparator == null) {
         throw new NullPointerException();
      }
      int firstIndex = -1;
      Arrays.sort(a, comparator);
      int front = 0;
      int back = a.length - 1;
      while (front <= back) {
         int middle = (front + back) / 2;
         if (comparator.compare(a[middle], key) > 0) {
            back = middle - 1;
         }
         else if (comparator.compare(a[middle], key) < 0) {
            front = middle + 1;
         }
         else {
            firstIndex = middle;
            back = middle - 1;
         }
      }
      return firstIndex;
   }

   /**
    * Returns the index of the first key in a[] that equals the search key, 
    * or -1 if no such key exists. This method throws a NullPointerException
    * if any parameter is null.
    */
   public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
      if (a == null || key == null || comparator == null) {
         throw new NullPointerException();
      }
      int lastIndex = -1;
      Arrays.sort(a, comparator);
      int front = 0;
      int back = a.length - 1;
      while (front <= back) {
         int middle = (front + back) / 2;
         if (comparator.compare(a[middle], key) == 1) {
            back = middle - 1;
         }
         else if (comparator.compare(a[middle], key) == -1) {
            front = middle + 1;
         }
         else {
            lastIndex = middle;
            front = middle + 1;
         }
      }
      return lastIndex;
   }

}
