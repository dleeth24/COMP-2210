import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class WordSearchGameAppTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void getBoardTest() {
      String board = "E E C A\nA L E P\nH N B O\nQ T T Y";
      Assert.assertEquals(board, WordSearchGameApp.getBoard());
   }
}
