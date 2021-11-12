import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

 /**
 * Test cases for LinkedSet.java
 * @author Chase Hopkins (ceh0136@auburn.edu)
 * @version 2021-06-29
 *
 */
public class LinkedSetTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }

   @Test public void ContainTest1() {
      Set<Integer> set = new LinkedSet<Integer>();
      boolean expected = true;
      
      set.add(0);
      set.add(1);
      set.add(2);
      set.add(3);
      set.add(4);
      set.add(5);
      set.add(6);
      set.add(7);
      set.add(8);
      set.add(9);
      set.add(10);
      
      boolean actual = set.contains(4);
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void ContainTest2() {
      Set<Integer> set = new LinkedSet<Integer>();
      boolean expected = false;
      
      set.add(0);
      set.add(1);
      set.add(2);
      set.add(3);
      set.add(4);
      set.add(6);
      set.add(7);
      set.add(8);
      set.add(9);
      set.add(10);
      
      boolean actual = set.contains(5);
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void ContainTest3() {
      Set<Integer> set = new LinkedSet<Integer>();
      boolean expected = false;
      
      set.add(0);
      set.add(1);
      set.add(2);
      set.add(3);
      set.add(4);
      set.add(6);
      set.add(7);
      set.add(8);
      set.add(9);
      set.add(10);
      set.remove(4);
      set.remove(5);
      
      boolean actual = set.contains(5);
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void AddTest1() {
      Set<Integer> set = new LinkedSet<Integer>();
      int expected = 5;
      
      set.add(4);
      set.add(1);
      set.add(5);
      set.add(3);
      set.add(1);
      set.add(2);
      set.add(2);
      set.add(3);
      
      int actual = set.size();
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void AddTest2() {
      Set<Integer> set = new LinkedSet<Integer>();
      int expected = 6;
      
      set.add(2);
      set.add(1);
      set.add(3);
      set.add(6);
      set.add(4);
      set.add(5);
      
      int actual = set.size(); 
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void AddTest3() {
      Set<Integer> set = new LinkedSet<Integer>();
      
      set.add(4);
      set.add(2);
      set.add(5);
      set.add(1);
      set.add(3);
      set.add(10);
      
      LinkedSet<Integer> copy = new LinkedSet<Integer>();
      Set<Integer> expected = new LinkedSet<Integer>();
      
      expected.add(1);
      expected.add(3);
      expected.add(4);
      expected.add(5);
      expected.add(10);
      
      
      Set<Integer> actual = new LinkedSet<Integer>();
      
      actual.add(1);
      actual.add(4);
      actual.add(3);
      actual.add(10);
      actual.add(5);
      
      Assert.assertTrue(expected.equals(actual));
   }
   
   @Test public void EqualsTestWithDifSize() {
      LinkedSet<Integer> set = new LinkedSet<Integer>();
      boolean expected = false;
      
      set.add(2);
      set.add(3);
      set.add(4);
      
      LinkedSet<Integer> set1 = new LinkedSet<Integer>();
      set1.add(1);
      set1.add(2);
      set1.add(3);
      set1.add(4);
      
      boolean actual = set1.equals(set);
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void EqualsTestWithDifSize2() {
      LinkedSet<Integer> set = new LinkedSet<Integer>();
      boolean expected = true;
      
      set.add(2);
      set.add(3);
      set.add(4);
      
      LinkedSet<Integer> set1 = new LinkedSet<Integer>();
      
      set1.add(1);
      set1.add(2);
      set1.add(3);
      set1.add(4);
      set1.remove(1);
      
      boolean actual = set1.equals(set);
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void RemoveTest() {
      Set<Integer> set = new LinkedSet<Integer>();
      int expected = 8;
      
      set.add(0);
      set.add(1);
      set.add(2);
      set.add(3);
      set.add(4);
      set.add(5);
      set.add(6);
      set.add(7);
      set.add(8);
      set.add(9);
      set.add(10);
      
      set.remove(5);
      set.remove(9);
      set.remove(4);
      
      int actual = set.size();
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void RemoveResizeTest() {
      Set<Integer> set = new LinkedSet<Integer>();
      int expected = 2;
      
      set.add(0);
      set.add(1);
      set.add(2);
      set.add(3);
      set.add(4);
      set.add(5);
      set.add(6);
      set.add(7);
      set.add(8);
      set.add(9);
      set.add(10);
      set.add(11);
      
      set.remove(0);
      set.remove(1);
      set.remove(2);
      set.remove(3);
      set.remove(4);
      set.remove(5);
      set.remove(6);
      set.remove(7);
      set.remove(8);
      set.remove(9);
      
      int actual = set.size();
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void UnionTest() {
      LinkedSet<Integer> newSet = new LinkedSet<Integer>();
      LinkedSet<Integer> newSet2 = new LinkedSet<Integer>();
      
      Set<Integer> expected = new LinkedSet<Integer>();
       
      newSet.add(2);
      newSet.add(1);
      newSet.add(3);
      
      newSet2.add(6);
      newSet2.add(4);
      newSet2.add(5);
      
      expected.add(1);
      expected.add(2);
      expected.add(3);
      expected.add(4);
      expected.add(5);
      expected.add(6);
      
      Set<Integer> actual = newSet2.union(newSet);
      String what = actual.toString();
      Assert.assertTrue(expected.equals(actual)); 
   }
   
   @Test public void RemoveTest1() {
      Set<Integer> set = new LinkedSet<Integer>();
      int expected = 8;
      
      set.add(10);
      set.add(1);
      set.add(4);
      set.add(3);
      set.add(2);
      set.add(6);
      set.add(5);
      set.add(8);
      set.add(7);
      set.add(9);
      set.add(0);
      
      set.remove(0);
      set.remove(9);
      set.remove(10);
      
      int actual = set.size();
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void EqualsTestWithSameSizeDifNums() {
      Set<Integer> newSet = new LinkedSet<Integer>();
      boolean expected = false;
      
      newSet.add(2);
      newSet.add(3);
      newSet.add(4);
      
      Set<Integer> set = new LinkedSet<Integer>();
      
      set.add(1);
      set.add(2);
      set.add(3);
      
      boolean actual = set.equals(newSet);
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void EqualsTestWithSameSizeDifNums2() {
   
      Set<Integer> newSet = new LinkedSet<Integer>();
      boolean expected = false;
      
      newSet.add(2);
      newSet.add(3);
      newSet.add(4);
      
      Set<Integer> set = new LinkedSet<Integer>();
      set.add(1);
      set.add(2);
      set.add(3);
      set.remove(1);
      set.remove(3);
      
      boolean actual = set.equals(newSet);
      Assert.assertEquals(expected, actual);
   } 
   
   @Test public void EqualsTestWithSameSizeSameNum() {
   
      Set<Integer> newSet = new LinkedSet<Integer>();
      boolean expected = true;
      
      newSet.add(2);
      newSet.add(3);
      newSet.add(4);
      
      Set<Integer> set = new LinkedSet<Integer>();
      
      set.add(3);
      set.add(4);
      set.add(2);
      
      boolean actual = set.equals(newSet);
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void EqualsTestWithSameSizeDifNums3() {
   
      LinkedSet<Integer> newSet = new LinkedSet<Integer>();
      boolean expected = true;
      
      newSet.add(1);
      newSet.add(2);
      newSet.add(3);
      
      LinkedSet<Integer> set = new LinkedSet<Integer>();
      set.add(3);
      set.add(2);
      set.add(1);
      
      boolean actual = set.equals(newSet);
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void UnionSetTest() {
   
      Set<Integer> newSet = new LinkedSet<Integer>();
      Set<Integer> newSet2 = new LinkedSet<Integer>();
      
      Set<Integer> expected = new LinkedSet<Integer>();
       
      newSet.add(2);
      newSet.add(1);
      newSet.add(3);
      
      newSet2.add(6);
      newSet2.add(4);
      newSet2.add(5);
       
      expected.add(1);
      expected.add(2);
      expected.add(3);
      expected.add(4);
      expected.add(5);
      expected.add(6);
       
      Set<Integer> actual = newSet2.union(newSet);
      
      Assert.assertTrue(expected.equals(actual)); 
   }
   
   @Test public void IntersectonTest() {
   
      LinkedSet<Integer> newSet = new LinkedSet<Integer>();
      LinkedSet<Integer> newSet2 = new LinkedSet<Integer>();
      
      Set<Integer> expected = new LinkedSet<Integer>();
      
      newSet.add(2);
      newSet.add(1);
      newSet.add(3);
      newSet.add(4);
      newSet.add(6);
      newSet.add(5);
      
      newSet2.add(6);
      newSet2.add(4);
      newSet2.add(5);
      
      expected.add(4);
      expected.add(5);
      expected.add(6);
      
      Set<Integer> actual = newSet.intersection(newSet2);
      String what = actual.toString();
      Assert.assertTrue(expected.equals(actual));
   }
   
   @Test public void DescendingIteratorTest() {
   
      LinkedSet<Integer> set = new LinkedSet<Integer>();
      
      set.add(1);
      set.add(2);
      set.add(3);
      set.add(4);
      set.add(5);
      set.add(6);
      
      Iterator<Integer> wait = set.descendingIterator();
      Iterator<Integer> what = set.descendingIterator();
      
      while (what.hasNext()) {
         System.out.print(what.next());
      }
   }
   
   @Test public void PowerSetIteratorTest() {
      LinkedSet<String> set = new LinkedSet<String>();
      
      set.add("A");
      set.add("B");
      set.add("C");
      
      Iterator<Set<String>> wait = set.powerSetIterator();
      Iterator<Set<String>> what = set.powerSetIterator();
      
      while (what.hasNext()) {
         System.out.print(what.next());
      }
   } 
   
   @Test public void ContainsTest() {
   
      Set<Integer> set = new LinkedSet<Integer>();
      boolean expected = false;
      
      set.contains(4);
      set.add(4);
      set.contains(4); 
      set.add(1);
      set.add(5);
      set.add(3);
      set.add(1);
      set.add(2);
      
      set.contains(3);
      set.remove(4);
      set.contains(4);
      
      set.remove(1);
      set.remove(5);
      
      boolean actual = set.contains(5);
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void RemoveTest2() {
   
      Set<Integer> set = new LinkedSet<Integer>();
      
      int expected = 1;
      
      set.add(4);
      set.add(1);
      set.add(5);
      set.add(3);
      set.add(1);
      set.add(2);
      
      set.remove(4);
      set.remove(1);
      set.remove(5);
      set.remove(2);
      
      int actual = set.size();
      
      Assert.assertEquals(expected, actual);
   }
   
}