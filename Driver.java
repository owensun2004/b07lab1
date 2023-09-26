import java.util.Arrays;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) throws IOException {

		double[] c1 = { 1, 2 };
		int[] e1 = { 1, 0};
		Polynomial p1 = new Polynomial(c1, e1);
		double[] c2 = { -3, 1 };
		int[] e2 = { 0, 1};
		Polynomial p2 = new Polynomial(c2, e2);
		File filePath = new File("TestCases.txt");
		Polynomial p3 = p2.multiply(p1);
		System.out.println(Arrays.toString(p3.coefficients));
		System.out.println(Arrays.toString(p3.exps));
		p3.saveToFile("Output.txt");
		/*
		 * double[] c1 = { 1, 2 };
		 * int[] e1 = { 0, 1 };
		 * Polynomial p1 = new Polynomial(c1, e1);
		 * double[] c2 = { 4, 5 };
		 * int[] e2 = { 2, 3 };
		 * Polynomial p2 = new Polynomial(c2, e2);
		 */
		// Polynomial s = p2.multiply(p2);
		// System.out.println("Multiplying" + Arrays.toString(s.coefficients));
		// System.out.println(Arrays.toString(s.exps));
		/*
		 * System.out.println("s.evaluate(-0.2)=" + s.evaluate(-0.2));
		 * System.out.println(s.exps[0]);
		 * System.out.println(s.exps[1]);
		 * System.out.println(s.exps[2]);
		 * System.out.println(s.exps[3]);
		 * System.out.println(s.exps[4]);
		 * System.out.println(s.coefficients[0]);
		 * System.out.println(s.coefficients[1]);
		 * System.out.println(s.coefficients[2]);
		 * System.out.println(s.coefficients[3]);
		 * System.out.println(s.coefficients[4]);
		 * // for (int i = 0; i < s.coefficients.length; i++) {
		 * // System.out.println(s.coefficents[i]);
		 * // }
		 * // for (int i = 0; i < s.coefficients.length; i++) {
		 * // System.out.println(s.exps[i]);
		 * // }
		 */
	}
}