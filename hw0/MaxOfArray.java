public class MaxOfArray {
	/** Return the maximum value from m */
	public static int max(int[] m) {
		int maxValue = m[0];
		int i = 0;
		while (i < m.length) {
			if (m[i] > maxValue) {
				maxValue = m[i];
			}
			i += 1;
		}
		return maxValue;
	}

	public static void main(String[] args) {
		int[] number = new int[]{9, 2, 15, 2, 22, 10, 6};
		System.out.printf("%d\n", max(number));
	}
}