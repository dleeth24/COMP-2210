import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author Davis Leeth (dkl0011@auburn.edu)
 */
public class Doublets implements WordLadderGame {

   HashSet<String> lexicon;
      
   /**
    * Instantiates a new instance of Doublets with the lexicon populated with
    * the strings in the provided InputStream. The InputStream can be formatted
    * in different ways as long as the first string on each line is a word to be
    * stored in the lexicon.
    */
   public Doublets(InputStream in) {
      try {
         lexicon = new HashSet<String>();
         Scanner s =
            new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next();
            lexicon.add(str.toUpperCase());
            s.nextLine();
         }
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }


   /**
    * Returns the total number of words in the current lexicon.
    *
    * @return number of words in the lexicon
    */
   public int getWordCount(){
      return lexicon.size();
   }


   /**
    * Checks to see if the given string is a word.
    *
    * @param  str the string to check
    * @return     true if str is a word, false otherwise
    */
   public boolean isWord(String str) {
      str = str.toUpperCase();
      return lexicon.contains(str);
   }


   /**
    * Returns the Hamming distance between two strings, str1 and str2. The
    * Hamming distance between two strings of equal length is defined as the
    * number of positions at which the corresponding symbols are different. The
    * Hamming distance is undefined if the strings have different length, and
    * this method returns -1 in that case. See the following link for
    * reference: https://en.wikipedia.org/wiki/Hamming_distance
    *
    * @param  str1 the first string
    * @param  str2 the second string
    * @return      the Hamming distance between str1 and str2 if they are the
    *                  same length, -1 otherwise
    */
   public int getHammingDistance(String str1, String str2){
      if (str1.length() != str2.length()) {
         return -1;
      }
      
      str1 = str1.toUpperCase();
      str2 = str2.toUpperCase();
      
      int count = 0;
      for (int i = 0; i < str1.length(); i++) {
         if (str1.charAt(i) != str2.charAt(i)) {
            count++;
         }
      }
      return count;
   }


   /**
    * Returns all the words that have a Hamming distance of one relative to the
    * given word.
    *
    * @param  word the given word
    * @return      the neighbors of the given word
    */
   public List<String> getNeighbors(String word) {
      List<String> neighbors = new ArrayList<String>();
      word = word.toUpperCase();
      
      for(int i = 0; i < word.length(); i++) {
         String copy = word;
         char[] charArray = copy.toCharArray();
         
         for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
            charArray[i] = alphabet;
            copy = String.valueOf(charArray);
            
            if (isWord(copy) && !neighbors.contains(copy) && !word.equals(copy)) {
               neighbors.add(copy);
            }
         }
      }
      return neighbors;
   }
   


   /**
    * Checks to see if the given sequence of strings is a valid word ladder.
    *
    * @param  sequence the given sequence of strings
    * @return          true if the given sequence is a valid word ladder,
    *                       false otherwise
    */
   public boolean isWordLadder(List<String> sequence) {
      if (sequence.isEmpty()) {
         return false;
      }
     
      for (int i = 0; i < sequence.size(); i++) {
         if (!isWord(sequence.get(i))) {
            return false;
         }
      }
      
      for (int i = 0; i < sequence.size() - 1; i++) {
         if (getHammingDistance(sequence.get(i), sequence.get(i + 1)) != 1) {
            return false;
         }
      }
      return true;
   }


  /**
   * Returns a minimum-length word ladder from start to end. If multiple
   * minimum-length word ladders exist, no guarantee is made regarding which
   * one is returned. If no word ladder exists, this method returns an empty
   * list.
   *
   * Breadth-first search must be used in all implementing classes.
   *
   * @param  start  the starting word
   * @param  end    the ending word
   * @return        a minimum length word ladder from start to end
   */
   public List<String> getMinLadder(String start, String end) {
      List<String> minLadder = new ArrayList<String>();
      ArrayList<String> list = new ArrayList<String>();
      start = start.toUpperCase();
      end = end.toUpperCase();
     
      if (start.equals(end)) {
         minLadder.add(start.toLowerCase());
         return minLadder;
      }
     
      if (getHammingDistance(start, end) == -1) {
         return minLadder;
      }
      
      if (isWord(start) && isWord(end)) {
         list = bfs(start, end);
      }
      for (int i = list.size() - 1; i >= 0; i--) {
         minLadder.add(list.get(i));
      }
      
      return minLadder;
   }
   
   private ArrayList<String> bfs(String start, String end) {
      Deque<Node> q = new ArrayDeque<>();
      HashSet<String> visited = new HashSet<String>();
      visited.add(start);
      q.addLast(new Node(start, null));
      Node endNode = new Node(end, null);
      
      while (!q.isEmpty()) {
         Node n = q.removeFirst();
         String word = n.word;
         List<String> neighbors = getNeighbors(word);
         
         for (String prox : neighbors) {
            if (!visited.contains(prox)) {
               visited.add(prox);
               q.addLast(new Node(prox, n));
               
               if (prox.equals(end)) {
                  endNode.before = n;
               }
            }
         }
      }
      ArrayList<String> list = new ArrayList<String>();
      
      if (endNode.before == null) {
         return list;
      }
      Node e = endNode;
      while (e != null) {
         list.add(e.word);
         e = e.before;
      }
      return list;
   }
   
   
   private class Node {
      String word;
      Node before;
   
      public Node(String w, Node bf) {
         word = w;
         before = bf;
      }
   }

   

}
