import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @author Davis Leeth (dkl0011@auburn.edu)
 *
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {

   //////////////////////////////////////////////////////////
   // Do not change the following three fields in any way. //
   //////////////////////////////////////////////////////////

   /** References to the first and last node of the list. */
   Node front;
   Node rear;

   /** The number of nodes in the list. */
   int size;

   /////////////////////////////////////////////////////////
   // Do not change the following constructor in any way. //
   /////////////////////////////////////////////////////////

   /**
    * Instantiates an empty LinkedSet.
    */
   public LinkedSet() {
      front = null;
      rear = null;
      size = 0;
   }


   //////////////////////////////////////////////////
   // Public interface and class-specific methods. //
   //////////////////////////////////////////////////

   ///////////////////////////////////////
   // DO NOT CHANGE THE TOSTRING METHOD //
   ///////////////////////////////////////
   /**
    * Return a string representation of this LinkedSet.
    *
    * @return a string representation of this LinkedSet
    */
   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (T element : this) {
         result.append(element + ", ");
      }
      result.delete(result.length() - 2, result.length());
      result.append("]");
      return result.toString();
   }


   ///////////////////////////////////
   // DO NOT CHANGE THE SIZE METHOD //
   ///////////////////////////////////
   /**
    * Returns the current size of this collection.
    *
    * @return  the number of elements in this collection.
    */
   public int size() {
      return size;
   }

   //////////////////////////////////////
   // DO NOT CHANGE THE ISEMPTY METHOD //
   //////////////////////////////////////
   /**
    * Tests to see if this collection is empty.
    *
    * @return  true if this collection contains no elements, false otherwise.
    */
   public boolean isEmpty() {
      return (size == 0);
   }


   /**
    * Ensures the collection contains the specified element. Neither duplicate
    * nor null values are allowed. This method ensures that the elements in the
    * linked list are maintained in ascending natural order.
    *
    * @param  element  The element whose presence is to be ensured.
    * @return true if collection is changed, false otherwise.
    */
   public boolean add(T element) {
      if (contains(element) || element == null) {
         return false;
      }
      Node temp = front;
      Node n = new Node(element);
      
      if (isEmpty()) {
         front = n;
         rear = n;
         rear.next = null;
         size++;
         return true;
      }
      else if (element.compareTo(rear.element) > 0) {
         rear.next = n;
         n.prev = rear;
         rear = n;
         rear.next = null;
         size++;
         return true;
      }
      else if (element.compareTo(front.element) < 0) {
         n.next = front;
         front.prev = n;
         front = n;
         size++;
         return true;
      }
      else {
         Node a = prevNode(element);
         a.next.prev = n;
         n.next = a.next;
         a.next = n;
         n.prev = a;
         size++;
         return true;
      }
   }

   /**
    * Ensures the collection does not contain the specified element.
    * If the specified element is present, this method removes it
    * from the collection. This method, consistent with add, ensures
    * that the elements in the linked lists are maintained in ascending
    * natural order.
    *
    * @param   element  The element to be removed.
    * @return  true if collection is changed, false otherwise.
    */
   public boolean remove(T element) {
      Node n = find(element);
      if (element == null || isEmpty() || !contains(element)) {
         return false;
      }
      if (size == 1) {
         front = null;
         rear = null;
         size = 0;
         return true;
      }
      if (n == front) {
         front = front.next;
         front.prev = null;
      }
      else {
         if (n.next != null) {
            n.prev.next = n.next;
            n.next.prev = n.prev;
         }
         else {
            n.prev.next = null;
            rear = n.prev;
         }
      }
      size--;
      return true;
   }


   /**
    * Searches for specified element in this collection.
    *
    * @param   element  The element whose presence in this collection is to be tested.
    * @return  true if this collection contains the specified element, false otherwise.
    */
   public boolean contains(T element) {
      Node copy = front;
      while (copy != null) {
         if (copy.element != null && copy.element.equals(element)) {
            return true;
         }
         copy = copy.next;
      }
      return false;
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(Set<T> s) {
      if (size == s.size() && complement(s).size() == 0) {
         return true;
      }
      return false;
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(LinkedSet<T> s) {
      if (size == s.size() && complement(s).size() == 0) {
         return true;
      }
      return false;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(Set<T> s){
      LinkedSet<T> jointSet = new LinkedSet<T>();
      Node n = front;
      while (n != null) {
         jointSet.add(n.element);
         n = n.next;
      }
      if (s == null) {
         return jointSet;
      }
      Iterator<T> i = s.iterator();
      while (i.hasNext()) {
         jointSet.add(i.next());
      }
      return jointSet;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(LinkedSet<T> s){
      LinkedSet<T> jointSet = new LinkedSet<T>();
      Node t = front;
      Node n = s.front;
      if (s.isEmpty()) {
         while (t != null) {
            jointSet.linkedAdd(t.element);
            t = t.next;
         }
         return jointSet;
      }
      if (this.isEmpty()) {
         while (n != null) {
            jointSet.linkedAdd(n.element);
            n = n.next;
         }
         return jointSet;
      }
      while (t != null && n != null) {
         if (t.element.compareTo(n.element) > 0) {
            jointSet.linkedAdd(n.element);
            n = n.next;
         }
         else if (t.element.compareTo(n.element) < 0) {
            jointSet.linkedAdd(t.element);
            t = t.next;
         }
         else if (t.element.compareTo(t.element) == 0) { 
            jointSet.linkedAdd(t.element);
            t = t.next;
            n = n.next;
         }
      }
      if (t == null) {
         while (n != null) {
            jointSet.linkedAdd(n.element);
            n = n.next;
         }
      }
      else if (n == null) {
         while (t != null) {
            jointSet.linkedAdd(t.element);
            t = t.next;
         }
      }
      return jointSet;
   }


   /**
    * Returns a set that is the intersection of this set and the parameter set.
    *
    * @return  a set that contains elements that are in both this set and the parameter set
    */
   public Set<T> intersection(Set<T> s) {
      LinkedSet<T> sameSet = new LinkedSet<T>();
      Node t = front;
      if (s == null) {
         return sameSet;
      }
      while (t != null) {
         if (s.contains((T)t.element)) {
            sameSet.add((T)t.element);
         }
         t = t.next;
      }
      
      return sameSet;
   }

   /**
    * Returns a set that is the intersection of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */
   public Set<T> intersection(LinkedSet<T> s) {
      LinkedSet<T> sameSet = new LinkedSet<T>();
      Node t = front;
      Node n = s.front;
      if (s.isEmpty()) {
         return sameSet;
      }
      if (this.isEmpty()) {
         return sameSet;
      }
      while (t != null && n != null) {
         if (t.element.compareTo(n.element) > 0) {
            n = n.next;
         }
         else if (t.element.compareTo(n.element) < 0) {
            t = t.next;
         }
         else if (t.element.compareTo(n.element) == 0) { 
            sameSet.linkedAdd(t.element);
            t = t.next;
            n = n.next;
         }
      }
      return sameSet;
   }


   /**
    * Returns a set that is the complement of this set and the parameter set.
    *
    * @return  a set that contains elements that are in this set but not the parameter set
    */
   public Set<T> complement(Set<T> s) {
      LinkedSet<T> returnSet = new LinkedSet<T>();
      Node n = front;
      if (s == null || s.isEmpty()) {
         while (n != null) {
            returnSet.linkedAdd(n.element);
            n = n.next;
         }
         return returnSet;
      }
      while (n != null) {
         if (s.contains(n.element)) {
            n = n.next;
         }
         else {
            returnSet.add(n.element);
            n = n.next;
         }
      }
      return returnSet;
   }


   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */
   public Set<T> complement(LinkedSet<T> s) {
      LinkedSet<T> returnSet = new LinkedSet<T>();
      Node t = front;
      Node n = s.front;
      if (s.isEmpty()) {
         while (t != null) {
            returnSet.linkedAdd(t.element);
            t = t.next;
         }
         return returnSet;
      }
      if (this.isEmpty()) {
         return returnSet;
      }
      while (t != null && n != null) {
         if (t.element.compareTo(n.element) > 0) {
            returnSet.linkedAdd(n.element);
            n = n.next;
         }
         else if (t.element.compareTo(n.element) < 0) {
            returnSet.linkedAdd(t.element);
            t = t.next;
         }
         else if (t.element.compareTo(n.element) == 0) { 
            t = t.next;
            n = n.next;
         }
      }
      return returnSet;
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in ascending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> iterator() {
      return 
         new Iterator<T>() {
            Node n = front;
            
            public boolean hasNext() {
               return n != null;
            }
            public T next() {
               T result = n.element;
               n = n.next;
               return result;
            }
         };
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in descending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> descendingIterator() {
      return 
         new Iterator<T>() {
            Node n = rear;
            
            public boolean hasNext() {
               return n != null;
            }
            public T next() {
               T result = n.element;
               n = n.prev;
               return result;
            }
         };
   }


   /**
    * Returns an iterator over the members of the power set
    * of this LinkedSet. No specific order can be assumed.
    *
    * @return  an iterator over members of the power set
    */
   public Iterator<Set<T>> powerSetIterator() {
      return 
         new Iterator<Set<T>>() {
            int current = 0;
            int elementCount = size();
            int setCount = (int) Math.pow(2, elementCount);
            Node n = front;
            
            public boolean hasNext() {
               return current < setCount;
            }
            public Set<T> next() {
               Set<T> newSet = new LinkedSet<T>();
               String binaryString = Integer.toBinaryString(current);
               while (binaryString.length() < elementCount) {
                  binaryString = "0" + binaryString;
               }
               for (int i = 0; i < elementCount; i++) {
                  if (binaryString.charAt(i) == '1') {
                     T element = n.element;
                     newSet.add(element);
                  }
                  n = n.next;
               }
               n = front;
               current++;
               return newSet;
            }
         };
   }



   //////////////////////////////
   // Private utility methods. //
   //////////////////////////////

   // Feel free to add as many private methods as you need.
   
   private Node prevNode(T element){
      Node n = front;
      while (n != null) {
         if (n.element.compareTo(element) > 0) {
            return n.prev;
         }
         n = n.next;
      }
      return n;
   }
   
   private Node find(T element) {
      Node n = front;
      while (n != null) {
         if (n.element.equals(element)) {
            return n;
         }
         n = n.next;
      }
      return null;
   }
   
   private boolean linkedAdd(T element) {
      if (element == null) {
         return false;
      }
      Node temp = front;
      Node n = new Node(element);
      
      if (isEmpty()) {
         front = n;
         rear = n;
         rear.next = null;
         size++;
         return true;
      }
      else if (element.compareTo(rear.element) > 0) {
         rear.next = n;
         n.prev = rear;
         rear = n;
         rear.next = null;
         size++;
         return true;
      }
      else if (element.compareTo(front.element) < 0) {
         n.next = front;
         front.prev = n;
         front = n;
         size++;
         return true;
      }
      return false;
   }


   ////////////////////
   // Nested classes //
   ////////////////////

   //////////////////////////////////////////////
   // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
   //////////////////////////////////////////////

   /**
    * Defines a node class for a doubly-linked list.
    */
   class Node {
      /** the value stored in this node. */
      T element;
      /** a reference to the node after this node. */
      Node next;
      /** a reference to the node before this node. */
      Node prev;
   
      /**
       * Instantiate an empty node.
       */
      public Node() {
         element = null;
         next = null;
         prev = null;
      }
   
      /**
       * Instantiate a node that containts element
       * and with no node before or after it.
       */
      public Node(T e) {
         element = e;
         next = null;
         prev = null;
      }
   }

}
