import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MinOfThreeTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** Tests min1. **/
   @Test public void min1Test() {
      int a = 1;
      int b = 3;
      int c = 1;
      int expected = 1;
      int actual = MinOfThree.min1(a, b, c);
      assertEquals(actual, expected);
   }
   
   /** Tests min1. **/
   @Test public void min1Test2() {
      int a = 3;
      int b = 1;
      int c = 3;
      int expected = 1;
      int actual = MinOfThree.min1(a, b, c);
      assertEquals(actual, expected);
   }
   
   /** Tests min2. **/
   @Test public void min2Test() {
      int a = 1;
      int b = 2;
      int c = 3;
      int expected = 1;
      int actual = MinOfThree.min2(a, b, c);
      assertEquals(actual, expected);
   }
}
