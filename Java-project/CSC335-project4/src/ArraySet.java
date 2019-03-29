/*This class is the ArraySet class.It is the children class of the AbstractSet
 * class. The main purpose of this class is to store the data and intimate the
 * feature of the Set structure. The one important feature of the set is that,
 * it should not contain two same object in the data structure. The design of 
 * the class is using the array as the primary data structure. By giving a
 * generic type, it can take any kinds of parameter and work. One thing worth
 * mentioned is the storage. The default storage is 10, if there is no more
 * space to store the data, the storage will be doubled.
 * 
 */
import java.lang.reflect.Array;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * The Class ArraySet.
 *
 * @param <E> the generic type
 */
public class ArraySet<E> extends AbstractSet<E>{
	
	
	//default capacity of the list.
	private static int DEFAULT_CAPACITY=10;
	private  E [] list;
	
	private int size=0;
	private int capacity=10;
	/**
	 * This is the constructor of the ArraySet. To the default setting, it 
	 * will create a 10 capacity list. Which is generic type, means you can 
	 * take whatever parameter you want.
	 */
	@SuppressWarnings("unchecked")
	public ArraySet() {
		 
		list = (E[]) new Object[DEFAULT_CAPACITY];
		
	}
	
	
	 
	/* This is the add method of the ArraySet. It is to add a object to the 
	 * list. The important thing is, it can only add a same type object. To be
	 * specific, if the list contain string object, then it will be unacceptable
	 * to add an integer. The other feature is it will double the storage if
	 * there is no more space. Else, one rule is same with the set is that, no
	 * same object in the list.
	 * @param <E> element the generic type
	 * @return boolean :whether the add is success or not.
	 */
	@Override
	public boolean add(E element) {
		if (size!=list.length) {
			for (int i=0;i<size;i++) {
				if (list[i].equals(element)) {
					return false;
				}
			}
			list[size]=element;
			size=size+1;
		}else {
			//double the space and set the max value of the space.
			if (capacity*2<Integer.MAX_VALUE-8) {
				capacity=capacity*2;
			}
			
			
			list=Arrays.copyOf(list,capacity);
			
		}
		return false;
		
			
	}
	
	/* The main idea of this method is the same with the remove method in 
	 * the upper class.Removes the first occurrence of the specified element 
	 * from this list, if it is present.If the list does not contain the 
	 * element, it is unchanged and return false.The implementation is to copy
	 * part of the old list and paste it into a new list. Set the last postion
	 * to be null. If success, return true. Vice Versa.
	 * @parameter Object elem the object to remove
	 * @return boolean whether the remove is success or not.
	 */
	@Override
	public boolean remove(Object elem) {
		for (int index=0;index<size;index++) {
			if (elem.equals(list[index])) {
				int newNum=size-index-1;
				if (newNum>0) {
					System.arraycopy(list, index+1, list, index, newNum);
					//copy the old list to the new one.
					
				}
				list[--size]=null;
				return true;
			}
			
		}
		return false;
	}
	
	/* This method is to provide an iterator to walk through the collection. The
	 * definition in the upper class is An iterator is an object that has
	 *  methods that allow you to proccess a collection of items one at a time.
	 *  It did the same thing with that. One feature is , the parameter of the
	 *  new iterator is the data structure that used in the ArraySet class. An 
	 *  array.
	 *  @return new ArraySetIterator iterator
	 */
	@Override
	public Iterator iterator() {
		
		return new ArraySetIterator(list);
	}

	/* Returns the number of elements in this collection. There is not much
	 * to explain in this class.
	 * 
	 */
	@Override
	public int size() {
		
		return size;
	}



	

}
/*This class is the special iterator class to the special data structure 
 * ArraySet. It implement the Iterator<T> interface. It shows that the parameter
 * of this class is generic. It can take what the user want to it. The class 
 * is not much different with other iterator. The feature of the class is
 * it take a parameter from the ArraySet which is the data structure array .
 * All the operation is based on the array.
 * 
 */
class ArraySetIterator<T> implements Iterator<T>{
	private int index;
	private  T [] list;
	private int size;
	int ifNext=0;
	//Whether the class call the next.
	/*
	 * The constructor of the class. It does a very important job which is
	 * set up the class. Including initialize important value such as list, 
	 * size. It judge the list size by looking for the postion which value is 
	 * null.
	 * @parameter T[] list
	 */
	public <E> ArraySetIterator(T[] list) {
		 index =0; 
		 this.list=list;
		 size=list.length;
		 for (int i=0;i<list.length;i++) {
				if (list[i]==null) {
					 size = i;
					 break;
				}
			} 
		
	}
	/*
	 * Judge whether there is next element to show up by looking for the size
	 * and the index. If they are equal, means the iterator is at the end of
	 * the list.
	 * @return boolean whether there is next element. 
	 */
	@Override
	public boolean hasNext() {
	
		
		
		return !(size==index);
	
	}

	/**
	 * Return the next element in the list. The implementation is judge whether
	 * there is next element in the list. It is like a boundary checking. Then
	 * It give 1 to the ifNext to show that we have already run the next method
	 * 
	 *
	 * @return T the element
	 */
	@Override
	public T next() {
	
			ifNext=1;
			if (hasNext()) {
				return list[index++]; 
			}else {
				return null;
			}
			  
		
		
		
		
	}
	/* The main idea of this method is the same with the remove method in 
	 * the upper class.The main difference between this one and others
	 * is it will judge whether there is a call of the next methods
	 * it will not run if the next method is not being called or
	 * there are more than once call of the remove when there is only
	 * one next call.In that situation, it will throw an exception to complain 
	 * . Removes the first occurrence of the specified element 
	 * from this list, if it is present.If the list does not contain the 
	 * element, it is unchanged and return false.The implementation is to copy
	 * part of the old list and paste it into a new list. Set the last postion
	 * to be null. If success, return true. Vice Versa. 
	 * @parameter
	 * @return boolean whether the remove is success or not.
	 * @throw IllegalStateException
	 */
	@Override
	public
	void remove() {
		if (ifNext==1) {
			ifNext=0;
			for (int k=0;k<size;k++) {
				
				if (list[--index].equals(list[k])) { 
					
					int newNum=size-k-1; 
					if (newNum>0) {
						System.arraycopy(list, k+1, list, k, newNum);
						
						  
					} 		
					list[--size]=null;
					return; 
				}
				 
			}
			 
			
		
			
		
		
		
	}
		throw new IllegalStateException();
}}
