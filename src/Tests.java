import java.lang.reflect.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Arrays;


public class Tests{
	public static void main(String[] args) {
		if(AVLTests()){
			System.out.println("\nAll Unit Tests passed.");
	        System.out.println("If you reach this point, your submission will be assessed.");
	    } else{
	        System.out.println("\nUnit tests failed on one or more tests.");
	        System.out.println("Please note that all tests must pass for your submission to be assessed.");
	    }
	}

	public static boolean AVLTests(){
        if(!UnitTestClass()){
        	System.out.println("\t\tDefault constructor threw an error. Further testing cannot be conducted.");
        	return false;
        } if(!UnitTestBasicInsert()){
            System.out.println("AVLTree Unit Tests indicated error, further testing cannot be conducted.");
            return false;
        } if(!UnitTestFindMin()){
            System.out.println("AVLTree Unit Tests indicated error, further testing cannot be conducted.");
            return false;
        } if(!UnitTestFindMax()){
            System.out.println("AVLTree Unit Tests indicated error, further testing cannot be conducted.");
            return false;
        } if(!UnitTestFind()){
            System.out.println("AVLTree Unit Tests indicated error, further testing cannot be conducted.");
            return false;
        } if(!UnitTestGetTreeHeight()){
            System.out.println("AVLTree Unit Tests indicated error, further testing cannot be conducted.");
            return false;
        } if(!UnitTestPreOrderWalk()){
            System.out.println("AVLTree Unit Tests indicated error, further testing cannot be conducted.");
            return false;
        } if(!UnitTestInOrderWalk()){
            System.out.println("AVLTree Unit Tests indicated error, further testing cannot be conducted.");
            return false;
        } if(!UnitTestPostOrderWalk()){
            System.out.println("AVLTree Unit Tests indicated error, further testing cannot be conducted.");
            return false;
        } if(!UnitTestRemove()){
            System.out.println("AVLTree Unit Tests indicated error, further testing cannot be conducted.");
            return false;
        } if(!UnitTestThrow()){
            System.out.println("AVLTree Unit Tests indicated error, further testing cannot be conducted.");
            return false;
        } if(!UnitTestStressTest()){
            System.out.println("AVLTree Unit Tests indicated error, further testing cannot be conducted.");
            return false;
        }
        
        System.out.println("\nAVLTree Unit Tests end.");
        return true;
	}

	public static boolean UnitTestClass(){
		try{
			Class<?> c = Class.forName("AVL.AVL");
			Object obj = c.getDeclaredConstructor().newInstance();
			return true;
		} catch(InstantiationException e){
			System.err.println("Could not instantiate an object of the AVL class.");
			return false;
		} catch(IllegalAccessException e){
			System.err.println("Could not access functions when instantiating a AVL object.");
			return false;
		} catch (ClassNotFoundException e){
			System.err.println("Class AVL not found in package AVL. Is it named correctly and in the package AVL?");
			return false;
		} catch(InvocationTargetException e){
			System.err.println("Could not get declared constructor of the AVL class.");
			return false;
		} catch(NoSuchMethodException e){
			System.err.println("There is no default constructor declared in AVL.");
			return false;
		}
	}

	public static boolean UnitTestBasicInsert(){
		String name = "insert";
		Class<?> args = java.lang.Comparable.class;
		Class<?> ret = Void.TYPE;
		boolean correct = false;

		System.out.println("\n\tinsert test begin.");
    	System.out.println("\tPlease note that this is a basic test that does not guarantee correct ordering of nodes.");

		try{
			Class<?> c = Class.forName("AVL.AVL");
			Method m = c.getDeclaredMethod(name, args);
			if(m.getReturnType() != ret)
				throw new NoSuchMethodException("Return type is wrong.");
			correct = true;
		} catch(ClassNotFoundException e){
			System.err.println("Class AVL not found in package AVL. Is it named correctly and in the package AVL?");
		} catch(NoSuchMethodException e){
			System.err.println(name + " is not declared correctly.\nError: " + e.getMessage());
		}

		if(correct){
			int i = 0;
			try{
				Class<?> c = Class.forName("AVL.AVL");
				Object obj = c.getDeclaredConstructor().newInstance();
				Method m = c.getDeclaredMethod(name, args);
				System.out.println("\t\tTesting insertion of 1'000 elements.");
			
				Random random = new Random();
				for(i = 0; i < 1000; ++i)
					m.invoke(obj, random.nextInt(1000000));
				System.out.println("\t\t1'000 elements inserted.");

			} catch(ClassNotFoundException e){
				System.err.println("Class AVL not found in package AVL. Is it named correctly and in the package AVL?");
				return false;
			} catch(InstantiationException e){
				System.err.println("Could not instantiate an object of the AVL class.");
				return false;
			} catch(InvocationTargetException e){
				System.err.println("Could not invoke the method " + name + ".");
				return false;
			} catch(Exception e){
				System.err.println("\t\tException thrown during insertion of 1'000 elements: " + e.getMessage());
				System.err.println("\tinsert test end.");
				return false;
			}
			System.out.println("\tinsert test end.");
			return true;
		}
		return false;
	}

	public static boolean UnitTestFindMin(){
		String name = "getMin";
		Class<?> ret = java.lang.Comparable.class;
		boolean correct = false;

		System.out.println("\n\tgetMin test begin.");

		try{
			Class<?> c = Class.forName("AVL.AVL");
			Method m = c.getDeclaredMethod(name);
			if(m.getReturnType() != ret)
				throw new NoSuchMethodException("Return type is wrong.");
			correct = true;
		} catch(ClassNotFoundException e){
			System.err.println("Class AVL not found in package AVL. Is it named correctly and in the package AVL?");
		} catch(NoSuchMethodException e){
			System.err.println(name + " is not declared correctly.\nError: " + e.getMessage());
		}

		if(correct){
			int i = 0;
			try{
				Class<?> c = Class.forName("AVL.AVL");
				Object obj = c.getDeclaredConstructor().newInstance();
				Method m_getMin = c.getDeclaredMethod(name);
				Method m_insert = c.getDeclaredMethod("insert", java.lang.Comparable.class);
				System.out.println("\t\tThis test will insert a set into the AVLTree and expects getMin to return the minimum element inserted.");
				
				int testData[] = {0, 5, 3, 41, 61, -6, -1, -71, -12, -51, 1, 51, -15};			

				System.out.println("\t\t\tInserting the set: {0, 5, 3, 41, 61, -6, -1, -71, -12, -51, 1, 51, -15} into the AVLTree.");
				for(i = 0; i < 13; ++i)
					m_insert.invoke(obj, testData[i]);
		        System.out.print("\t\t\tInsertion done. Calling getMin.");
				System.out.print("\n\t\t\t\tExpected result: -71");
				System.out.println("\n\t\t\t\tReturned result: " + m_getMin.invoke(obj));

		        if(((Integer)m_getMin.invoke(obj)).compareTo(-71) != 0){
		        	System.out.println("\t\t\tgetMin did not return expected element. Returned: " + m_getMin.invoke(obj));
			        System.out.println("\t\tgetMin not working as expected.");
			        System.out.println("\tgetMin test end.");
			        return false;
			    }
			    
			    System.out.println("\t\t\tgetMin returned expected element.");
			    System.out.println("\t\tgetMin working as expected.");
			    System.out.println("\tgetMin test end.");
			    return true;

			} catch(ClassNotFoundException e){
				System.err.println("Class AVL not found in package AVL. Is it named correctly and in the package AVL?");
				return false;
			} catch(InstantiationException e){
				System.err.println("Could not instantiate an object of the AVL class.");
				return false;
			} catch(InvocationTargetException e){
				System.err.println("Could not invoke the method " + name + ".");
				return false;
			} catch(Exception e){
				System.err.println("\t\tException thrown during insertion of 1'000 elements: " + e.getMessage());
				System.err.println("\tgetMin test end.");
				return false;
			}
		}
		return false;
	}

	public static boolean UnitTestFindMax(){
		String name = "getMax";
		Class<?> ret = java.lang.Comparable.class;
		boolean correct = false;

		System.out.println("\n\tgetMax test begin.");

		try{
			Class<?> c = Class.forName("AVL.AVL");
			Method m = c.getDeclaredMethod(name);
			if(m.getReturnType() != ret)
				throw new NoSuchMethodException("Return type is wrong.");
			correct = true;
		} catch(ClassNotFoundException e){
			System.err.println("Class AVL not found in package AVL. Is it named correctly and in the package AVL?");
		} catch(NoSuchMethodException e){
			System.err.println(name + " is not declared correctly.\nError: " + e.getMessage());
		}

		if(correct){
			int i = 0;
			try{
				Class<?> c = Class.forName("AVL.AVL");
				Object obj = c.getDeclaredConstructor().newInstance();
				Method m_getMax = c.getDeclaredMethod(name);
				Method m_insert = c.getDeclaredMethod("insert", java.lang.Comparable.class);
				System.out.println("\t\tThis test will insert a set into the AVLTree and expects getMax to return the maximum element inserted.");
				
				int testData[] = {0, 5, 3, 41, 61, -6, -1, -71, -12, -51, 1, 51, -15};			

				System.out.println("\t\t\tInserting the set: {0, 5, 3, 41, 61, -6, -1, -71, -12, -51, 1, 51, -15} into the AVLTree.");
				for(i = 0; i < 13; ++i)
					m_insert.invoke(obj, testData[i]);
		        System.out.print("\t\t\tInsertion done. Calling AVLTree getMax.");
				System.out.print("\n\t\t\t\tExpected result: 61");
				System.out.println("\n\t\t\t\tReturned result: " + m_getMax.invoke(obj));

		        if(((Integer)m_getMax.invoke(obj)).compareTo(61) != 0){
		        	System.out.println("\t\t\tgetMax did not return expected element. Returned: " + m_getMax.invoke(obj));
			        System.out.println("\t\tgetMax not working as expected.");
			        System.out.println("\tgetMax test end.");
			        return false;
			    }
			    
			    System.out.println("\t\t\tgetMax returned expected element.");
			    System.out.println("\t\tgetMax working as expected.");
			    System.out.println("\tgetMax test end.");
			    return true;

			} catch(ClassNotFoundException e){
				System.err.println("Class AVL not found in package AVL. Is it named correctly and in the package AVL?");
				return false;
			} catch(InstantiationException e){
				System.err.println("Could not instantiate an object of the AVL class.");
				return false;
			} catch(InvocationTargetException e){
				System.err.println("Could not invoke the method " + name + ".");
				return false;
			} catch(Exception e){
				System.err.println("\t\tException thrown during insertion of 1'000 elements: " + e.getMessage());
				System.err.println("\tgetMax test end.");
				return false;
			}
		}
		return false;
	}

	public static boolean UnitTestFind(){
		String name = "find";
		Class<?> args = java.lang.Comparable.class;
		Class<?> ret = boolean.class;
		boolean correct = false;

		System.out.println("\n\tfind test begin.");

		try{
			Class<?> c = Class.forName("AVL.AVL");
			Method m = c.getDeclaredMethod(name, args);
			if(m.getReturnType() != ret)
				throw new NoSuchMethodException("Return type is wrong.");
			correct = true;
		} catch(ClassNotFoundException e){
			System.err.println("Class AVL not found in package AVL. Is it named correctly and in the package AVL?");
		} catch(NoSuchMethodException e){
			System.err.println(name + " is not declared correctly.\nError: " + e.getMessage());
		}

		if(correct){
			int i = 0;
			try{
				Class<?> c = Class.forName("AVL.AVL");
				Object obj = c.getDeclaredConstructor().newInstance();
				Method m_find = c.getDeclaredMethod(name, args);
				Method m_insert = c.getDeclaredMethod("insert", java.lang.Comparable.class);
				System.out.println("\t\tThis test will insert a set into the AVLTree and expects getMax to return the maximum element inserted.");
				
				int testData[] = {0, 5, 3, 41, 61, -6, -1, -71, -12, -51, 1, 51, -15};			

				System.out.println("\t\t\tInserting the set: {0, 5, 3, 41, 61, -6, -1, -71, -12, -51, 1, 51, -15} into the AVLTree.");
				for(i = 0; i < 13; ++i)
					m_insert.invoke(obj, testData[i]);
		        System.out.print("\t\t\tInsertion done. Calling find, searching for element 5.");
				System.out.print("\n\t\t\t\tExpected result: true");
				System.out.println("\n\t\t\t\tReturned result: " + m_find.invoke(obj, 5));

		        if(((boolean)m_find.invoke(obj, 5)) == false){
		        	System.out.println("\t\t\tfind did not return expected element. Returned: " + false);
			        System.out.println("\t\tfind not working as expected.");
			        System.out.println("\tfind test end.");
			        return false;
			    }
			    
			    System.out.println("\t\t\tfind returned expected element.");
			    
			    System.out.print("\t\t\tCalling find, searching for element -72.");
				System.out.print("\n\t\t\t\tExpected result: false");
				System.out.println("\n\t\t\t\tReturned result: " + m_find.invoke(obj, -72));

		        if(((boolean)m_find.invoke(obj, -72)) == true){
		        	System.out.println("\t\t\tfind did not return expected element. Returned: " + true);
			        System.out.println("\t\tfind not working as expected.");
			        System.out.println("\tfind test end.");
			        return false;
			    }

			    System.out.println("\t\t\tfind(-72) returned expected element.");
		        System.out.println("\t\tfind working as expected.");
		        System.out.println("\tfind test end.");
			    return true;

			} catch(ClassNotFoundException e){
				System.err.println("Class AVL not found in package AVL. Is it named correctly and in the package AVL?");
				return false;
			} catch(InstantiationException e){
				System.err.println("Could not instantiate an object of the AVL class.");
				return false;
			} catch(InvocationTargetException e){
				System.err.println("Could not invoke the method " + name + ".");
				return false;
			} catch(Exception e){
				System.err.println("\t\tException thrown during insertion of 1'000 elements: " + e);
				System.err.println("\tfind test end.");
				return false;
			}
		}
		return false;
	}

	public static boolean UnitTestGetTreeHeight(){
		String name = "getTreeHeight";
		Class<?> ret = int.class;
		boolean correct = false;

		System.out.println("\n\tgetTreeHeight test begin.");

		try{
			Class<?> c = Class.forName("AVL.AVL");
			Method m = c.getDeclaredMethod(name);
			if(m.getReturnType() != ret)
				throw new NoSuchMethodException("Return type is wrong.");
			correct = true;
		} catch(ClassNotFoundException e){
			System.err.println("Class AVL not found in package AVL. Is it named correctly and in the package AVL?");
		} catch(NoSuchMethodException e){
			System.err.println(name + " is not declared correctly.\nError: " + e.getMessage());
		}

		if(correct){
			int i = 0;
			try{
				Class<?> c = Class.forName("AVL.AVL");
				Object obj = c.getDeclaredConstructor().newInstance();
				Method m_getTreeHeight = c.getDeclaredMethod(name);
				Method m_insert = c.getDeclaredMethod("insert", java.lang.Comparable.class);
				
				System.out.println("\t\tThis test will try to get the height of an empty tree.\n\t\t\tExpected value: -1");
		        int height = (int)m_getTreeHeight.invoke(obj);
		        System.out.println("\t\t\tReturned value: " + height);
		        if(height != -1)
		        {
		            System.out.println("\t\t\tgetTreeHeight did not return expected value.");
		            System.out.println("\t\tgetTreeHeight not working as expected.");
		            System.out.println("\tgetTreeHeight test end.");
		            return false;
		        }
		        System.out.println("\t\tgetTreeHeight returned expected value on empty tree.");
		        System.out.println("\t\tThis test will insert the set {3,2,1,4,5,6,7} before calling getTreeHeight() on the tree.");
		        int range[] = {3, 2, 1, 4, 5, 6, 7};
		        for(i = 0; i < 7; ++i)
		            m_insert.invoke(obj, range[i]);

		        System.out.println("\t\t\tSet inserted into the AVLTree. This set should have resulted in multiple rotations. Calling getTreeHeight.");
		        System.out.println("\t\t\t\tExpected value: 2");
		        height = (int)m_getTreeHeight.invoke(obj);
		        System.out.println("\t\t\t\tReturned value: " + height);
		        if(height != 2)
		        {
		            System.out.println("\t\t\tgetTreeHeight did not return expected value, see page 165-167 for example.");
		            System.out.println("\t\tgetTreeHeight not working as expected.");
		            System.out.println("\tgetTreeHeight test end.");
		            return false;
		        }
		        System.out.println("\t\t\tgetTreeHeight() returned expected value on the set. Continuing with insertion of the second set {16, 15, 14, 13, 12, 11, 10, 8, 9}.");

		        range = new int[]{16, 15, 14, 13, 12, 11, 10, 8, 9};
		        for(i = 0; i < 9; ++i)
		            m_insert.invoke(obj, range[i]);

		        System.out.println("\t\t\tSet inserted into the AVLTree. This set should have resulted in multiple double rotations. Calling getTreeHeight.");
		        System.out.println("\t\t\t\tExpected value: 4");
		        height = (int)m_getTreeHeight.invoke(obj);

		        System.out.println("\t\t\t\tReturned value: " + height);
		        if(height != 4)
		        {
		            System.out.println("\t\t\tgetTreeHeight did not return expected value, see page 167-171 for example.");
		            System.out.println("\t\tgetTreeHeight not working as expected.");
		            System.out.println("\tgetTreeHeight test end.");
		            return false;
		        }
		        System.out.println("\t\t\tgetTreeHeight returned expected value on the set.");
		        System.out.println("\t\tgetTreeHeight working as expected.");
		        System.out.println("\tgetTreeHeight test end.");
		        return true;

			} catch(ClassNotFoundException e){
				System.err.println("Class AVL not found in package AVL. Is it named correctly and in the package AVL?");
				return false;
			} catch(InstantiationException e){
				System.err.println("Could not instantiate an object of the AVL class.");
				return false;
			} catch(InvocationTargetException e){
				System.err.println("Could not invoke the method " + name + ".");
				return false;
			} catch(Exception e){
				System.err.println("\t\tException thrown during insertion of 1'000 elements: " + e);
				System.err.println("\tgetTreeHeight test end.");
				return false;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static boolean UnitTestPreOrderWalk(){
		String name = "preOrderWalk";
		Class<?> ret = ArrayList.class;
		boolean correct = false;

		System.out.println("\n\tpreOrderWalk test begin.");

		try{
			Class<?> c = Class.forName("AVL.AVL");
			Method m = c.getDeclaredMethod(name);
			if(m.getReturnType() != ret)
				throw new NoSuchMethodException("Return type is wrong.");
			correct = true;
		} catch(ClassNotFoundException e){
			System.err.println("Class AVL not found in package AVL. Is it named correctly and in the package AVL?");
		} catch(NoSuchMethodException e){
			System.err.println(name + " is not declared correctly.\nError: " + e.getMessage());
		}

		if(correct){
			int i = 0;
			try{
				Class<?> c = Class.forName("AVL.AVL");
				Object obj = c.getDeclaredConstructor().newInstance();
				Method m_preOrderWalk = c.getDeclaredMethod(name);
				Method m_insert = c.getDeclaredMethod("insert", java.lang.Comparable.class);
				
				System.out.println("\t\tThis test will try to get the preOrderWalk of an empty tree.");
		        ArrayList<Integer> walkResult;
		        Object test;

		        test = m_preOrderWalk.invoke(obj);
		        if(test instanceof ArrayList)		        	
		        	walkResult = (ArrayList<Integer>)test;
		        else{
		        	System.out.println("preOrderWalk doesn't return an ArrayList.");
		        	System.out.println("preOrderWalk test end.");
		        	return false;
		        }

		        if(walkResult.size() != 0)
		        {
		            System.out.println("\t\t\tpreOrderWalk did not return as expected. size() indicates that elements are placed in the return vector when non should be.");
		            System.out.println("\t\tpreOrderWalk not working as expected.");
		            System.out.println("\tpreOrderWalk test end.");
		            return false;
		        }
		        System.out.println("\t\tpreOrderWalk returned expected value on empty tree.");
		        System.out.println("\t\tThis test will insert the set {3,2,1,4,5,6,7} before calling preOrderWalk() on the tree.");
		        int range[] = new int[]{3, 2, 1, 4, 5, 6, 7};
		        for(i = 0; i < 7; ++i)
		            m_insert.invoke(obj, range[i]);

		        System.out.println("\t\t\tSet inserted into the AVLTree. This set should have resulted in multiple rotations. Calling preOrderWalk.");
		        range = new int[]{4, 2, 1, 3, 6, 5, 7};
		        System.out.println("\t\t\t\tExpected value: {4, 2, 1, 3, 6, 5, 7}");
		        
		        test = m_preOrderWalk.invoke(obj);
		        if(test instanceof ArrayList)		        	
		        	walkResult = (ArrayList<Integer>)test;
		        else{
		        	System.out.println("preOrderWalk doesn't return an ArrayList.");
		        	System.out.println("preOrderWalk test end.");
		        	return false;
		        }

		        if(walkResult.size() == 0)
		        {
		            System.out.println("\t\t\tpreOrderWalk did not return any elements.");
		            System.out.println("\t\tpreOrderWalk not working as expected.");
		            System.out.println("\tpreOrderWalk test end.");
		            return false;
		        }

		        System.out.print("\t\t\t\tReturned value: {");
		        for(i = 0; i < walkResult.size()-1; ++i)
		            System.out.print(walkResult.get(i) + ", ");
		        System.out.println(walkResult.get(walkResult.size() - 1) + "}");

		        ArrayList<Integer> rangeList = new ArrayList();
		        for(i = 0; i < 7; ++i)
		        	rangeList.add(range[i]);

		        if(rangeList.equals(walkResult) == false)
		        {
		            System.out.println("\t\t\tpreOrderWalk did not return expected value, see page 165-167 for example.");
		            System.out.println("\t\tpreOrderWalk not working as expected.");
		            System.out.println("\tpreOrderWalk test end.");
		            return false;
		        }
		        System.out.println("\t\t\tpreOrderWalk() returned expected value on the set. Continuing with insertion of the second set {16, 15, 14, 13, 12, 11, 10, 8, 9}.");
		        
		        range = new int[] {16, 15, 14, 13, 12, 11, 10, 8, 9};
		        for(i = 0; i < 9; ++i)
		            m_insert.invoke(obj, range[i]);

		        System.out.println("\t\t\tSet inserted into the AVLTree. This set should have resulted in multiple double rotations. Calling preOrderWalk.");
		        System.out.println("\t\t\t\tExpected value: {7, 4, 2, 1, 3, 6, 5, 13, 11, 9, 8, 10, 12, 15, 14, 16}");
		        
		        walkResult = new ArrayList<Integer>();
		        test = m_preOrderWalk.invoke(obj);
		        if(test instanceof ArrayList)		        	
		        	walkResult = (ArrayList<Integer>)test;
		        else{
		        	System.out.println("preOrderWalk doesn't return an ArrayList.");
		        	System.out.println("preOrderWalk test end.");
		        	return false;
		        }

		        if(walkResult.size() == 0)
		        {
		            System.out.println("\t\t\tpreOrderWalk did not return any elements.");
		            System.out.println("\t\tpreOrderWalk not working as expected.");
		            System.out.println("\tpreOrderWalk test end.");
		            return false;
		        }

		        System.out.print("\t\t\t\tReturned value: {");
		        for(i = 0; i < walkResult.size()-1; ++i)
		            System.out.print(walkResult.get(i) + ", ");
		        System.out.println(walkResult.get(walkResult.size() - 1) + "}");

		        int values[] = new int[]{7, 4, 2, 1, 3, 6, 5, 13, 11, 9, 8, 10, 12, 15, 14, 16};
		        rangeList = new ArrayList();
		        for(i = 0; i < 16; ++i)
		        	rangeList.add(values[i]);

		        if(walkResult.equals(rangeList) == false)
		        {
		            System.out.println("\t\t\tpreOrderWalk did not return expected value, see page 165-167 for example.");
		            System.out.println("\t\tpreOrderWalk not working as expected.");
		            System.out.println("\tpreOrderWalk test end.");
		            return false;
		        }
		        System.out.println("\t\t\tpreOrderWalk returned expected value on the set.");
		        System.out.println("\t\tpreOrderWalk working as expected.");
		        System.out.println("\tpreOrderWalk test end.");

		        return true;

			} catch(ClassNotFoundException e){
				System.err.println("Class AVL not found in package AVL. Is it named correctly and in the package AVL?");
				return false;
			} catch(InstantiationException e){
				System.err.println("Could not instantiate an object of the AVL class.");
				return false;
			} catch(InvocationTargetException e){
				System.err.println("Could not invoke the method " + name + ". Exception: " + e.getCause());
				return false;
			} catch(Exception e){
				System.err.println("\t\tException thrown during insertion of 1'000 elements: " + e);
				System.err.println("\tpreOrderWalk test end.");
				return false;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static boolean UnitTestInOrderWalk(){
		String name = "inOrderWalk";
		Class<?> ret = ArrayList.class;
		boolean correct = false;

		System.out.println("\n\tinOrderWalk test begin.");

		try{
			Class<?> c = Class.forName("AVL.AVL");
			Method m = c.getDeclaredMethod(name);
			if(m.getReturnType() != ret)
				throw new NoSuchMethodException("Return type is wrong.");
			correct = true;
		} catch(ClassNotFoundException e){
			System.err.println("Class AVL not found in package AVL. Is it named correctly and in the package AVL?");
		} catch(NoSuchMethodException e){
			System.err.println(name + " is not declared correctly.\nError: " + e.getMessage());
		}

		if(correct){
			int i = 0;
			try{
				Class<?> c = Class.forName("AVL.AVL");
				Object obj = c.getDeclaredConstructor().newInstance();
				Method m_inOrderWalk = c.getDeclaredMethod(name);
				Method m_insert = c.getDeclaredMethod("insert", java.lang.Comparable.class);
				
				System.out.println("\t\tThis test will try to get the inOrderWalk of an empty tree.");
		        ArrayList<Integer> walkResult;
		        Object test;

		        test = m_inOrderWalk.invoke(obj);
		        if(test instanceof ArrayList)		        	
		        	walkResult = (ArrayList<Integer>)test;
		        else{
		        	System.out.println("inOrderWalk doesn't return an ArrayList.");
		        	System.out.println("inOrderWalk test end.");
		        	return false;
		        }

		        if(walkResult.size() != 0)
		        {
		            System.out.println("\t\t\tinOrderWalk did not return as expected. size() indicates that elements are placed in the return vector when non should be.");
		            System.out.println("\t\tinOrderWalk not working as expected.");
		            System.out.println("\tinOrderWalk test end.");
		            return false;
		        }
		        System.out.println("\t\tinOrderWalk returned expected value on empty tree.");
		        System.out.println("\t\tThis test will insert the set {3,2,1,4,5,6,7} before calling inOrderWalk() on the tree.");
		        int range[] = new int[]{3, 2, 1, 4, 5, 6, 7};
		        for(i = 0; i < 7; ++i)
		            m_insert.invoke(obj, range[i]);

		        System.out.println("\t\t\tSet inserted into the AVLTree. This set should have resulted in multiple rotations. Calling inOrderWalk.");
		        range = new int[]{1, 2, 3, 4, 5, 6, 7};
		        System.out.println("\t\t\t\tExpected value: {1, 2, 3, 4, 5, 6, 7}");
		        
		        test = m_inOrderWalk.invoke(obj);
		        if(test instanceof ArrayList)		        	
		        	walkResult = (ArrayList<Integer>)test;
		        else{
		        	System.out.println("inOrderWalk doesn't return an ArrayList.");
		        	System.out.println("inOrderWalk test end.");
		        	return false;
		        }

		        if(walkResult.size() == 0)
		        {
		            System.out.println("\t\t\tinOrderWalk did not return any elements.");
		            System.out.println("\t\tinOrderWalk not working as expected.");
		            System.out.println("\tinOrderWalk test end.");
		            return false;
		        }

		        System.out.print("\t\t\t\tReturned value: {");
		        for(i = 0; i < walkResult.size()-1; ++i)
		            System.out.print(walkResult.get(i) + ", ");
		        System.out.println(walkResult.get(walkResult.size() - 1) + "}");

		        ArrayList<Integer> rangeList = new ArrayList();
		        for(i = 0; i < 7; ++i)
		        	rangeList.add(range[i]);

		        if(rangeList.equals(walkResult) == false)
		        {
		            System.out.println("\t\t\tinOrderWalk did not return expected value, see page 165-167 for example.");
		            System.out.println("\t\tinOrderWalk not working as expected.");
		            System.out.println("\tinOrderWalk test end.");
		            return false;
		        }
		        System.out.println("\t\t\tinOrderWalk() returned expected value on the set. Continuing with insertion of the second set {16, 15, 14, 13, 12, 11, 10, 8, 9}.");
		        
		        range = new int[] {16, 15, 14, 13, 12, 11, 10, 8, 9};
		        for(i = 0; i < 9; ++i)
		            m_insert.invoke(obj, range[i]);

		        System.out.println("\t\t\tSet inserted into the AVLTree. This set should have resulted in multiple double rotations. Calling inOrderWalk.");
		        System.out.println("\t\t\t\tExpected value: {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}");
		        
		        walkResult = new ArrayList<Integer>();
		        test = m_inOrderWalk.invoke(obj);
		        if(test instanceof ArrayList)		        	
		        	walkResult = (ArrayList<Integer>)test;
		        else{
		        	System.out.println("inOrderWalk doesn't return an ArrayList.");
		        	System.out.println("inOrderWalk test end.");
		        	return false;
		        }

		        if(walkResult.size() == 0)
		        {
		            System.out.println("\t\t\tinOrderWalk did not return any elements.");
		            System.out.println("\t\tinOrderWalk not working as expected.");
		            System.out.println("\tinOrderWalk test end.");
		            return false;
		        }

		        System.out.print("\t\t\t\tReturned value: {");
		        for(i = 0; i < walkResult.size()-1; ++i)
		            System.out.print(walkResult.get(i) + ", ");
		        System.out.println(walkResult.get(walkResult.size() - 1) + "}");

		        int values[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
		        rangeList = new ArrayList();
		        for(i = 0; i < 16; ++i)
		        	rangeList.add(values[i]);

		        if(walkResult.equals(rangeList) == false)
		        {
		            System.out.println("\t\t\tinOrderWalk did not return expected value, see page 165-167 for example.");
		            System.out.println("\t\tinOrderWalk not working as expected.");
		            System.out.println("\tinOrderWalk test end.");
		            return false;
		        }
		        System.out.println("\t\t\tinOrderWalk returned expected value on the set.");
		        System.out.println("\t\tinOrderWalk working as expected.");
		        System.out.println("\tinOrderWalk test end.");

		        return true;

			} catch(ClassNotFoundException e){
				System.err.println("Class AVL not found in package AVL. Is it named correctly and in the package AVL?");
				return false;
			} catch(InstantiationException e){
				System.err.println("Could not instantiate an object of the AVL class.");
				return false;
			} catch(InvocationTargetException e){
				System.err.println("Could not invoke the method " + name + ". Exception: " + e.getCause());
				return false;
			} catch(Exception e){
				System.err.println("\t\tException thrown during insertion of 1'000 elements: " + e);
				System.err.println("\tinOrderWalk test end.");
				return false;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static boolean UnitTestPostOrderWalk(){
		String name = "postOrderWalk";
		Class<?> ret = ArrayList.class;
		boolean correct = false;

		System.out.println("\n\tpostOrderWalk test begin.");

		try{
			Class<?> c = Class.forName("AVL.AVL");
			Method m = c.getDeclaredMethod(name);
			if(m.getReturnType() != ret)
				throw new NoSuchMethodException("Return type is wrong.");
			correct = true;
		} catch(ClassNotFoundException e){
			System.err.println("Class AVL not found in package AVL. Is it named correctly and in the package AVL?");
		} catch(NoSuchMethodException e){
			System.err.println(name + " is not declared correctly.\nError: " + e.getMessage());
		}

		if(correct){
			int i = 0;
			try{
				Class<?> c = Class.forName("AVL.AVL");
				Object obj = c.getDeclaredConstructor().newInstance();
				Method m_postOrderWalk = c.getDeclaredMethod(name);
				Method m_insert = c.getDeclaredMethod("insert", java.lang.Comparable.class);
				
				System.out.println("\t\tThis test will try to get the postOrderWalk of an empty tree.");
		        ArrayList<Integer> walkResult = new ArrayList<Integer>();
		        Object test;

		        test = m_postOrderWalk.invoke(obj);
		        if(test instanceof ArrayList)		        	
		        	walkResult = (ArrayList<Integer>)test;
		        else{
		        	System.out.println("postOrderWalk doesn't return an ArrayList.");
		        	System.out.println("postOrderWalk test end.");
		        	return false;
		        }

		        if(walkResult.size() != 0)
		        {
		            System.out.println("\t\t\tpostOrderWalk did not return as expected. size() indicates that elements are placed in the return vector when non should be.");
		            System.out.println("\t\tpostOrderWalk not working as expected.");
		            System.out.println("\tpostOrderWalk test end.");
		            return false;
		        }
		        System.out.println("\t\tpostOrderWalk returned expected value on empty tree.");
		        System.out.println("\t\tThis test will insert the set {3,2,1,4,5,6,7} before calling postOrderWalk() on the tree.");
		        int range[] = new int[]{3, 2, 1, 4, 5, 6, 7};
		        for(i = 0; i < 7; ++i)
		            m_insert.invoke(obj, range[i]);

		        System.out.println("\t\t\tSet inserted into the AVLTree. This set should have resulted in multiple rotations. Calling postOrderWalk.");
		        range = new int[]{1, 3, 2, 5, 7, 6, 4};
		        System.out.println("\t\t\t\tExpected value: {1, 3, 2, 5, 7, 6, 4}");
		        
		        test = m_postOrderWalk.invoke(obj);
		        if(test instanceof ArrayList)		        	
		        	walkResult = (ArrayList<Integer>)test;
		        else{
		        	System.out.println("postOrderWalk doesn't return an ArrayList.");
		        	System.out.println("postOrderWalk test end.");
		        	return false;
		        }

		        if(walkResult.size() == 0)
		        {
		            System.out.println("\t\t\tpostOrderWalk did not return any elements.");
		            System.out.println("\t\tpostOrderWalk not working as expected.");
		            System.out.println("\tpostOrderWalk test end.");
		            return false;
		        }

		        System.out.print("\t\t\t\tReturned value: {");
		        for(i = 0; i < walkResult.size()-1; ++i)
		            System.out.print(walkResult.get(i) + ", ");
		        System.out.println(walkResult.get(walkResult.size() - 1) + "}");

		        ArrayList<Integer> rangeList = new ArrayList();
		        for(i = 0; i < 7; ++i)
		        	rangeList.add(range[i]);

		        if(rangeList.equals(walkResult) == false)
		        {
		            System.out.println("\t\t\tpostOrderWalk did not return expected value, see page 165-167 for example.");
		            System.out.println("\t\tpostOrderWalk not working as expected.");
		            System.out.println("\tpostOrderWalk test end.");
		            return false;
		        }
		        System.out.println("\t\t\tpostOrderWalk() returned expected value on the set. Continuing with insertion of the second set {16, 15, 14, 13, 12, 11, 10, 8, 9}.");
		        
		        range = new int[] {16, 15, 14, 13, 12, 11, 10, 8, 9};
		        for(i = 0; i < 9; ++i)
		            m_insert.invoke(obj, range[i]);

		        System.out.println("\t\t\tSet inserted into the AVLTree. This set should have resulted in multiple double rotations. Calling postOrderWalk.");
		        System.out.println("\t\t\t\tExpected value: {1, 3, 2, 5, 6, 4, 8, 10, 9, 12, 11, 14, 16, 15, 13, 7}");
		        
		        walkResult = new ArrayList<Integer>();
		        test = m_postOrderWalk.invoke(obj);
		        if(test instanceof ArrayList)		        	
		        	walkResult = (ArrayList<Integer>)test;
		        else{
		        	System.out.println("postOrderWalk doesn't return an ArrayList.");
		        	System.out.println("postOrderWalk test end.");
		        	return false;
		        }

		        if(walkResult.size() == 0)
		        {
		            System.out.println("\t\t\tpostOrderWalk did not return any elements.");
		            System.out.println("\t\tpostOrderWalk not working as expected.");
		            System.out.println("\tpostOrderWalk test end.");
		            return false;
		        }

		        System.out.print("\t\t\t\tReturned value: {");
		        for(i = 0; i < walkResult.size()-1; ++i)
		            System.out.print(walkResult.get(i) + ", ");
		        System.out.println(walkResult.get(walkResult.size() - 1) + "}");

		        int values[] = new int[]{1, 3, 2, 5, 6, 4, 8, 10, 9, 12, 11, 14, 16, 15, 13, 7};
		        rangeList = new ArrayList();
		        for(i = 0; i < 16; ++i)
		        	rangeList.add(values[i]);

		        if(walkResult.equals(rangeList) == false)
		        {
		            System.out.println("\t\t\tpostOrderWalk did not return expected value, see page 165-167 for example.");
		            System.out.println("\t\tpostOrderWalk not working as expected.");
		            System.out.println("\tpostOrderWalk test end.");
		            return false;
		        }
		        System.out.println("\t\t\tpostOrderWalk returned expected value on the set.");
		        System.out.println("\t\tpostOrderWalk working as expected.");
		        System.out.println("\tpostOrderWalk test end.");

		        return true;

			} catch(ClassNotFoundException e){
				System.err.println("Class AVL not found in package AVL. Is it named correctly and in the package AVL?");
				return false;
			} catch(InstantiationException e){
				System.err.println("Could not instantiate an object of the AVL class.");
				return false;
			} catch(InvocationTargetException e){
				System.err.println("Could not invoke the method " + name + ". Exception: " + e.getCause());
				return false;
			} catch(Exception e){
				System.err.println("\t\tException thrown during insertion of 1'000 elements: " + e);
				System.err.println("\tpostOrderWalk test end.");
				return false;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static boolean UnitTestRemove(){
		String name = "remove";
		Class<?> args = java.lang.Comparable.class;
		Class<?> ret = Void.TYPE;
		boolean correct = false;

		System.out.println("\n\tremove test begin.");

		try{
			Class<?> c = Class.forName("AVL.AVL");
			Method m = c.getDeclaredMethod(name, args);
			if(m.getReturnType() != ret)
				throw new NoSuchMethodException("Return type is wrong.");
			correct = true;
		} catch(ClassNotFoundException e){
			System.err.println("Class AVL not found in package AVL. Is it named correctly and in the package AVL?");
		} catch(NoSuchMethodException e){
			System.err.println(name + " is not declared correctly.\nError: " + e.getMessage());
		}

		if(correct){
			int i = 0;
			try{
				Class<?> c = Class.forName("AVL.AVL");
				Object obj = c.getDeclaredConstructor().newInstance();
				Method m_remove = c.getDeclaredMethod(name, args);
				Method m_preOrderWalk = c.getDeclaredMethod("preOrderWalk");
				Method m_postOrderWalk = c.getDeclaredMethod("postOrderWalk");
				Method m_insert = c.getDeclaredMethod("insert", java.lang.Comparable.class);
				Method m_getTreeHeight = c.getDeclaredMethod("getTreeHeight");
				
				System.out.println("\t\tThis test will try to get the remove of an empty tree.");
		        ArrayList<Integer> walkResult = new ArrayList<Integer>();
		        Object test;
		        
		        /*TODO: Insert tests here*/
		        
		        System.out.println("\t\tThis test will insert the set {3,2,1,4,5,6,7, 16, 15, 14, 13, 12, 11, 10, 8, 9} before removing various nodes on the tree.");
		        int range[] = new int[]{3, 2, 1, 4, 5, 6, 7, 16, 15, 14, 13, 12, 11, 10, 8, 9};
		        for(i = 0; i < 16; ++i)
		            m_insert.invoke(obj, range[i]);

		        System.out.println("\t\t\tSet inserted into the AVLTree. This set should have resulted in multiple rotations and double rotations.");
		        
				System.out.println("\t\t\tCalling remove(12)");
		        m_remove.invoke(obj, 12);
		        System.out.println("\t\t\t\tRemove finished. Testing correctness with call to postOrderWal and preOrderWalk");
		        System.out.println("\t\t\t\tExpected value (preOrderWalk): {7, 4, 2, 1, 3, 6, 5, 13, 9, 8, 11, 10, 15, 14, 16}");

				test = m_preOrderWalk.invoke(obj);
		        if(test instanceof ArrayList)		        	
		        	walkResult = (ArrayList<Integer>)test;
		        else{
		        	System.out.println("preOrder doesn't return an ArrayList.");
		        	System.out.println("remove test end.");
		        	return false;
		        }
		        System.out.print("\t\t\t\tReturned value (preOrderWalk): {");
		        for(i = 0; i < walkResult.size() - 1; ++i)
		            System.out.print(walkResult.get(i) + ", ");
		        System.out.println(walkResult.get(walkResult.size() - 1) + "}");

		        range = new int[]{7, 4, 2, 1, 3, 6, 5, 13, 9, 8, 11, 10, 15, 14, 16};
		        ArrayList<Integer> rangeList = new ArrayList();
		        for(i = 0; i < 15; ++i)
		        	rangeList.add(range[i]);

		        if(walkResult.equals(rangeList) == false)
		        {
		            System.out.println("\t\t\tpreOrderWalk did not return expected value after call to remove.");
		            System.out.println("\t\tremove not working as expected.");
		            System.out.println("\tremove test end.");
		            return false;
		        }
		        
		        walkResult = new ArrayList<Integer>();
				test = m_postOrderWalk.invoke(obj);
		        System.out.println("\t\t\t\tExpected value (postOrderWalk): {1, 3, 2, 5, 6, 4, 8, 10, 11, 9, 14, 16, 15, 13, 7}");

		        if(test instanceof ArrayList)		        	
		        	walkResult = (ArrayList<Integer>)test;
		        else{
		        	System.out.println("postOrder doesn't return an ArrayList.");
		        	System.out.println("remove test end.");
		        	return false;
		        }
		        System.out.print("\t\t\t\tReturned value (postOrderWalk): {");
		        for(i = 0; i != walkResult.size() - 1; ++i)
		            System.out.print(walkResult.get(i) + ", ");
		        System.out.println(walkResult.get(walkResult.size() - 1) + "}");

		        range = new int[]{1, 3, 2, 5, 6, 4, 8, 10, 11, 9, 14, 16, 15, 13, 7};
		        rangeList = new ArrayList();
		        for(i = 0; i < 15; ++i)
		        	rangeList.add(range[i]);

		        if(walkResult.equals(rangeList) == false) {
		            System.out.println("\t\t\tpreOrderWalk did not return expected value after call to remove.");
		            System.out.println("\t\tremove not working as expected.");
		            System.out.println("\tremove test end.");
		            return false;
		        }



		        System.out.println("\t\t\tCalling remove(8)");
		        m_remove.invoke(obj, 8);
		        System.out.println("\t\t\t\tRemove finished. Testing correctness with call to postOrderWal and preOrderWalk");
		        System.out.println("\t\t\t\tExpected value (preOrderWalk): {7, 4, 2, 1, 3, 6, 5, 13, 10, 9, 11, 15, 14, 16}");
		        
		        walkResult = new ArrayList<Integer>();
		        test = m_preOrderWalk.invoke(obj);
		        if(test instanceof ArrayList)		        	
		        	walkResult = (ArrayList<Integer>)test;
		        else{
		        	System.out.println("preOrder doesn't return an ArrayList.");
		        	System.out.println("remove test end.");
		        	return false;
		        }

		        System.out.print("\t\t\t\tReturned value (preOrderWalk): {");
		        for(i = 0; i < walkResult.size() - 1; ++i)
		            System.out.print(walkResult.get(i) + ", ");
		        System.out.println(walkResult.get(walkResult.size() - 1) + "}");

		        range = new int[]{7, 4, 2, 1, 3, 6, 5, 13, 10, 9, 11, 15, 14, 16};
		        rangeList = new ArrayList();
		        for(i = 0; i < 14; ++i)
		        	rangeList.add(range[i]);

		        if(walkResult.equals(rangeList) == false)
		        {
		            System.out.println("\t\t\tpreOrderWalk did not return expected value after call to remove.");
		            System.out.println("\t\tremove not working as expected.");
		            System.out.println("\tremove test end.");
		            return false;
		        }
		        
		        walkResult = new ArrayList<Integer>();
				test = m_postOrderWalk.invoke(obj);
		        System.out.println("\t\t\t\tExpected value (postOrderWalk): {1, 3, 2, 5, 6, 4, 9, 11, 10, 14, 16, 15, 13, 7}");

		        if(test instanceof ArrayList)		        	
		        	walkResult = (ArrayList<Integer>)test;
		        else{
		        	System.out.println("postOrder doesn't return an ArrayList.");
		        	System.out.println("remove test end.");
		        	return false;
		        }

		        System.out.print("\t\t\t\tReturned value (postOrderWalk): {");
		        for(i = 0; i != walkResult.size() - 1; ++i)
		            System.out.print(walkResult.get(i) + ", ");
		        System.out.println(walkResult.get(walkResult.size() - 1) + "}");

		        range = new int[]{1, 3, 2, 5, 6, 4, 9, 11, 10, 14, 16, 15, 13, 7};
		        rangeList = new ArrayList();
		        for(i = 0; i < 14; ++i)
		        	rangeList.add(range[i]);

		        if(walkResult.equals(rangeList) == false) {
		            System.out.println("\t\t\tpreOrderWalk did not return expected value after call to remove.");
		            System.out.println("\t\tremove not working as expected.");
		            System.out.println("\tremove test end.");
		            return false;
		        }


		        int deletes[] = new int[]{1, 3, 2, 5, 6, 4, 9, 11, 10, 14, 16, 15, 13, 7};
		        System.out.println("\t\t\tCalling remove on remaining elements in the order {1, 3, 2, 5, 6, 4, 9, 11, 10, 14, 16, 15, 13, 7}");
        		System.out.print("\t\t\t\tRemoving element: ");
        		for(i = 0; i < 14; i++){
        			System.out.print(deletes[i] + " ");
        			m_remove.invoke(obj, deletes[i]);
        		}
        		System.out.println();

        		System.out.println("\t\t\tControlling that all elements have been properly removed.");

        		int height = (int)m_getTreeHeight.invoke(obj);
        		if(height != -1){
        			System.out.println("\t\t\tgetTreeHeight did not return 0 on tree that is expected to be empty.");
        			walkResult = new ArrayList<Integer>();
					test = m_postOrderWalk.invoke(obj);
			        System.out.println("\t\t\t\tExpected value (postOrderWalk): {1, 3, 2, 5, 6, 4, 9, 11, 10, 14, 16, 15, 13, 7}");

			        if(test instanceof ArrayList)		        	
			        	walkResult = (ArrayList<Integer>)test;
			        else{
			        	System.out.println("postOrder doesn't return an ArrayList.");
			        	System.out.println("remove test end.");
			        	return false;
			        }

			        if(walkResult.size() > 0){
			        	System.out.print("Elements remaining: {");
			        	for(i = 0; i < walkResult.size()-1; ++i)
			        		System.out.print(walkResult.get(i) + ", ");
			        	System.out.println(walkResult.get(walkResult.size()-1) + "}");
			        } else{
			        	System.out.println("\t\t\tNo elements returned from call to inOrderWalk. This error should not be seen, since it indicates that the getTreeHeight test passed wrongly.");
			        	System.out.println("\t\t\tPlease contact Christian, since this is a corner case unexpected to be seen.");
			        }
        		}
        		System.out.println("\t\tremove working as expected.");
        		System.out.println("\tremove test end.");

		        return true;

			} catch(ClassNotFoundException e){
				System.err.println("Class AVL not found in package AVL. Is it named correctly and in the package AVL?");
				return false;
			} catch(InstantiationException e){
				System.err.println("Could not instantiate an object of the AVL class.");
				return false;
			} catch(InvocationTargetException e){
				System.err.println("Could not invoke the method " + name + ". Exception: " + e.getCause());
				return false;
			} catch(Exception e){
				System.err.println("\t\tException thrown during insertion of 1'000 elements: " + e);
				System.err.println("\tRemove test end.");
				return false;
			}
		}
		return false;
	}

	public static boolean UnitTestThrow(){

		System.out.println("\n\tTesting exceptions on the AVL tree.");
		int i = 0;
		try{
			Class<?> c = Class.forName("AVL.AVL");
			Object obj = c.getDeclaredConstructor().newInstance();
			Method m_getMin = c.getDeclaredMethod("getMin");
			Method m_getMax = c.getDeclaredMethod("getMax");
			
			System.out.println("\t\tCalling getMin on an empty AVL tree.");

			try{
				m_getMin.invoke(obj);
				System.out.println("\t\tgetMin not throwing exception as expected.");
				System.out.println("\tException tests end.");
				return false;
			} catch(InvocationTargetException e){
				if(e.getCause().toString().contains("NoSuchElement"))
					System.out.println("\t\tCaught error: " + e.getCause());
				else {
					System.out.println("\t\t\tException handling not done correctly for newly-initalized AVL tree. Should throw a NoSuchElement exception. Exception caught: \n\t\t\t\t" + e.getCause());
					return false;
				}
			}
			System.out.println("\t\tCalling getMax on an empty AVL tree.");
			try{
				m_getMax.invoke(obj);
				System.out.println("\t\tgetMax not throwing exception as expected.");
				System.out.println("\tException tests end.");
				return false;
			} catch(InvocationTargetException e){
				if(e.getCause().toString().contains("NoSuchElement"))
					System.out.println("\t\tCaught error: " + e.getCause());
				else {
					System.out.println("\t\t\tException handling not done correctly for newly-initalized AVL tree. Should throw a NoSuchElement exception. Exception caught: \n\t\t\t\t" + e.getCause());
					return false;
				}
			}

			System.out.println("\t\tExceptions thrown as expected on the implemented AVL tree.");
			System.out.println("\tException tests end.");
	        return true;

		} catch(ClassNotFoundException e){
			System.err.println("Class AVL not found in package AVL. Is it named correctly and in the package AVL?");
			return false;
		} catch(InstantiationException e){
			System.err.println("Could not instantiate an object of the AVL class.");
			return false;
		} catch(InvocationTargetException e){
			System.err.println("Could not invoke the method getMin/getMax. Exception: " + e.getCause());
			return false;
		} catch(Exception e){
			System.err.println("\t\tException thrown during insertion of 1'000 elements: " + e);
			System.err.println("\tException test end.");
			return false;
		}
	}

	public static boolean UnitTestStressTest(){
		System.out.println("\n\tStarting stress test.");
		int i = 0;
		try{
			System.out.println("\t\tThe stress test will first add 1'000 randomized elements.");
			Class<?> c = Class.forName("AVL.AVL");
			Object obj = c.getDeclaredConstructor().newInstance();
			Method m_insert = c.getDeclaredMethod("insert", java.lang.Comparable.class);
			Method m_remove = c.getDeclaredMethod("remove", java.lang.Comparable.class);
			Method m_getTreeHeight = c.getDeclaredMethod("getTreeHeight");
			
			ArrayList<Integer> randomNumbs = new ArrayList<Integer>(1000);
			System.out.println("\t\t\tRandomizing elements.");
			Random random = new Random();
			for(i = 0; i < 1000; ++i){
				int randomNumb = random.nextInt(1000000);
				if(randomNumbs.contains(randomNumb) == true){
					i--;
					continue;
				}
				randomNumbs.add(randomNumb);
			}
			System.out.println("\t\t\t1'000 elements created. Inserting into the tree.");

			for(i = 0; i < 1000; ++i)
				m_insert.invoke(obj, randomNumbs.get(i));

			System.out.println("\t\t\tSecondly, elements will be added and removed in such a way that 250 elements are removed before 100 new are inserted.");
			System.out.println("\t\t\tThis will continue until the tree is empty.");

			int counter = 0;
			while((int)m_getTreeHeight.invoke(obj) != 0){
				m_remove.invoke(obj, randomNumbs.get(0));
				randomNumbs.remove(0);
				counter++;

				if(counter == 250){
					for(i = 0; i < 100; ++i){
						int randomNumb = random.nextInt(1000000);
						if(randomNumbs.contains(randomNumb) == true){
							i--;
							continue;
						}
						randomNumbs.add(randomNumb);
					}
					counter = 0;
				}
			}

			System.out.println("\t\t\tTree empty");
			System.out.println("\t\tStress test passed.");
			System.out.println("\tStress test end.");


		} catch(ClassNotFoundException e){
			System.err.println("Class AVL not found in package AVL. Is it named correctly and in the package AVL?");
			return false;
		} catch(InstantiationException e){
			System.err.println("Could not instantiate an object of the AVL class.");
			return false;
		} catch(InvocationTargetException e){
			System.err.println("Could not invoke the method insert/remove/getTreeHeight.");
			return false;
		} catch(Exception e){
			System.err.println("\t\tException thrown during insertion of 1'000 elements: " + e.getMessage());
			System.err.println("\tStress test end.");
			return false;
		}
		return true;

	}
}
