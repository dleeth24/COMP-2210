import java.util.Arrays;

/**
 * Autocomplete.
 */
public class Autocomplete {

   private Term[] termArray;

	/**
	 * Initializes a data structure from the given array of terms.
	 * This method throws a NullPointerException if terms is null.
	 */
   public Autocomplete(Term[] terms) {
      if (terms == null) {
         throw new NullPointerException();
      }
      
      Arrays.sort(terms);
      termArray = terms;
   }

	/** 
	 * Returns all terms that start with the given prefix, in descending order of weight. 
	 * This method throws a NullPointerException if prefix is null.
	 */
   public Term[] allMatches(String prefix) {
      if (prefix == null) {
         throw new NullPointerException();
      }
      
      Term t = new Term(prefix, 0);
      int a = BinarySearch.firstIndexOf(termArray, t, Term.byPrefixOrder(prefix.length()));
      int b = BinarySearch.lastIndexOf(termArray, t, Term.byPrefixOrder(prefix.length()));
      if (a == -1 || b == -1) {
         throw new NullPointerException();
      }
      
      int length = prefix.length();
      int firstIndex = BinarySearch.<Term>firstIndexOf(termArray, new Term(prefix, 0),
         Term.byPrefixOrder(prefix.length()));
      int lastIndex = BinarySearch.<Term>lastIndexOf(termArray, new Term(prefix, 0),
         Term.byPrefixOrder(prefix.length()));
      if (firstIndex == -1 && lastIndex == -1) {
         return new Term[0];
      }
      int sameSize = (lastIndex - firstIndex) + 1;
      Term[] allMatches = new Term[sameSize];
      allMatches = Arrays.copyOfRange(termArray, firstIndex, lastIndex + 1);
      Arrays.sort(allMatches, allMatches[0].byDescendingWeightOrder());
      
      return allMatches;
   }
  

}

