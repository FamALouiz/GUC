
public class LinearArray {
	// a counter that keeps track of the number of elements in the array
		int itemCount;
		int[] array;

		// constructor
		public LinearArray(int maxSize) {
			itemCount = 0;
			array = new int[maxSize];
		}

		// method to insert elements at the end of an unordered array
		// Time complexity: O(1)
		public void insertLast(int x) {
			if (itemCount < array.length)
				array[itemCount++] = x;
			else
				System.out.print("Array is Full");
		}


		public void displayArray() {
			// print the array
			System.out.print("Array: ");
			for (int i = 0; i < itemCount; i++)
				System.out.print(array[i] + " ");
			System.out.println();
		}
}
