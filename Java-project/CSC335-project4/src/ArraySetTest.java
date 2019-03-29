/*
 * 
 */
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * The Class ArraySetTest. This class is to test the functionality of the 
 * ArraySet. 
 */
public class ArraySetTest {

/**
 * Test 1. This test add more than 10 element to test the double space feature
 * of the array.Then it test the iterator of the ArraySet. Test remove before
 * calling of next and after.Which show the specific feature of the remove in
 * the iterator. Print the size and catch the exception. To sum up, it 
 * convinced me that the ArraySet is a workable data structure.
 */
@Test
 void Test1() {
	ArraySet<String> a = new ArraySet<String>();
	a.add("a");
	a.add("b");
	a.add("c");  
	a.add("d");
	a.add("a");
	a.add("e");
	a.add("f");
	a.add("g");
	a.add("h");
	a.add("i");
	a.add("j");
	a.add("k");
	a.add("l");
	a.add("m");
	a.add("n");
	ArraySetIterator<String> iterator = (ArraySetIterator<String>) a.iterator();
	while (iterator.hasNext()) {
		System.out.println(iterator.next());
		 
	}
	iterator.next(); 
	 
	iterator = (ArraySetIterator<String>) a.iterator(); 
	try {
		iterator.remove();
	}catch(IllegalStateException e){ 
		System.out.println("Once per Next!");
	}
	iterator.next();   
	iterator.remove();
	 
	
	while (iterator.hasNext()) {
		System.out.println(iterator.next()+""); 
	}
	System.out.println(a.size());
	a.remove("b");
	a.remove("a"); 
	
}	


}
