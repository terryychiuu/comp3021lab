package lab9;

/**
 * 
 * COMP 3021
 * 
 * This is a class that prints the maximum value of a given array of 90 elements
 * 
 * This is a single threaded version.
 * 
 * Create a multi-thread version with 3 threads:
 * 
 * one thread finds the max among the cells [0,29] another thread the max among
 * the cells [30,59] another thread the max among the cells [60,89]
 * 
 * Compare the results of the three threads and print at console the max value.
 * 
 * 
 * @author valerio
 *
 */
public class FindMax {
	// this is an array of 90 elements
	// the max value of this array is 9999
	static int[] array = { 1, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2, 3, 4543,
			234, 3, 454, 1, 2, 3, 1, 9999, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3, 1, 34, 5, 6, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3 };

	public class Foo implements Runnable {
		private int value;
		private int begin;
		private int end;

		public Foo(int a, int b) {
			this.begin = a;
			this.end = b;
		}

		@Override
		public void run() {
			synchronized (Foo.class) {
				value = findMax(begin, end);
			}
		}

		public int getValue() {
			return value;
		}
	}

	public static void main(String[] args) {
		new FindMax().printMax();
	}

	public void printMax() {
		// this is a single threaded version
		Foo f1 = new Foo(0, 29);
		Thread t1 = new Thread(f1);
		Foo f2 = new Foo(30, 59);
		Thread t2 = new Thread(f2);
		Foo f3 = new Foo(60, 89);
		Thread t3 = new Thread(f3);

		t1.start();
		t2.start();
		t3.start();

		System.out.println(f1.getValue());
		System.out.println(f2.getValue());
		System.out.println(f3.getValue());

		int finalMax = Math.max(f1.getValue(), Math.max(f2.getValue(), f3.getValue()));

		System.out.println("the max value is " + finalMax);
	}

	/**
	 * returns the max value in the array within a give range [begin,range]
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	private int findMax(int begin, int end) {
		// you should NOT change this function
		int max = array[begin];
		for (int i = begin + 1; i <= end; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}
}