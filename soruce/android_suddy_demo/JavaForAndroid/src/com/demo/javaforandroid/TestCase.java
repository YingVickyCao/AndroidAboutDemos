package com.demo.javaforandroid;

import android.test.AndroidTestCase;

public class TestCase extends AndroidTestCase {

	
	
	class A {
	}

	private static String TAG = Messages.getString("TestCase.0"); //$NON-NLS-1$

	// 测试instanceof和getClass()区别？
	public void testInstance() {

		class B extends A {
		}

		Object o1 = new A();
		A a1 = new A();
		Object o2 = new B();
		B b = new B();
		A a2 = new B();

		/**
		 * <pre>
		 * 	08-11 09:06:32.465: I/System.out(4841): true
		 * 08-11 09:06:32.466: I/System.out(4841): false
		 * 08-11 09:06:32.466: I/System.out(4841): true
		 * 08-11 09:06:32.466: I/System.out(4841): true
		 * 08-11 09:06:32.466: I/System.out(4841): true
		 * 08-11 09:06:32.466: I/System.out(4841): true
		 * 08-11 09:06:32.466: I/System.out(4841): true
		 * 08-11 09:06:32.466: I/System.out(4841): ------------------
		 * 08-11 09:06:32.466: I/System.out(4841): class com.demo.javaforandroid.C
		 * 08-11 09:06:32.466: I/System.out(4841): class com.demo.javaforandroid.TestCase$A
		 * 08-11 09:06:32.467: I/System.out(4841): class com.demo.javaforandroid.TestCase$1B
		 * 08-11 09:06:32.467: I/System.out(4841): class java.lang.Object
		 * 08-11 09:06:32.467: I/System.out(4841): ------------------
		 * 08-11 09:06:32.467: I/System.out(4841): true
		 * 08-11 09:06:32.467: I/System.out(4841): false
		 * 08-11 09:06:32.467: I/System.out(4841): false
		 * 08-11 09:06:32.467: I/System.out(4841): true
		 * 08-11 09:06:32.467: I/System.out(4841): true
		 * 08-11 09:06:32.467: I/System.out(4841): false
		 * 08-11 09:06:32.467: I/System.out(4841): false
		 * 
		 * </pre>
		 */
		System.out.println(o1 instanceof A);// true
		System.out.println(o1 instanceof B);// false
		System.out.println(o2 instanceof A);// true
		System.out.println(o2 instanceof B);// true
		System.out.println(a1 instanceof A);// true
		System.out.println(a2 instanceof A);// true
		System.out.println(b instanceof A);// true

		System.out.println(Messages.getString("TestCase.1")); //$NON-NLS-1$

		// C为单独文件的外部类
		System.out.println(C.class);
		// A为本文件的外部类
		System.out.println(A.class);
		// B为本文件的内部类
		System.out.println(B.class);
		System.out.println(Object.class);
		System.out.println(Messages.getString("TestCase.2")); //$NON-NLS-1$

		System.out.println(o1.getClass().equals(A.class));// true
		System.out.println(o1.getClass().equals(B.class));// false
		System.out.println(o2.getClass().equals(A.class));// false
		System.out.println(o2.getClass().equals(B.class));// true
		System.out.println(a1.getClass().equals(A.class));// true
		System.out.println(a2.getClass().equals(A.class));// false
		System.out.println(b.getClass().equals(A.class));// false
	}
}
