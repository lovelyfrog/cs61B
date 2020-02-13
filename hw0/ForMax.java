public class ForMax {
	/** Returns the maximum value from m using a for loop */
	public static int forMax(int[] m) {
		int maxValue = m[0];
		for (int i=0; i<m.length; i++) {
			if (m[i] > maxValue) {
				maxValue = m[i];
			}
		}
		return maxValue;
	}

	public static void main(String[] args) {
		int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
		System.out.printf("%d\n", forMax(numbers));
	}
}