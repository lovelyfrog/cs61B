public class Triangle {
	public static void main(String[] args) {
		int lines = Integer.parseInt(args[0]);
		String stars = "*";
		for (int i=0; i<=lines; i++) {
			System.out.printf("%s", stars);
			System.out.println();
			stars = stars + "*";
		}
	}
}