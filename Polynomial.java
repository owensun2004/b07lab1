import java.security.cert.PolicyQualifierInfo;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.IntFunction;
import java.util.*;
import java.util.ArrayList;

public class Polynomial {
	double[] coefficients;
	int[] exps;

	public Polynomial() {
		coefficients = new double[] { 0.0 };
		exps = new int[] { 0 };
	}

	public Polynomial(double[] arr, int[] arr2) {
		coefficients = arr;
		exps = arr2;
	}

	public Polynomial(File f) throws IOException {
		Scanner scan = new Scanner(f);
		String in = scan.nextLine();
		char[] split = new char[in.length()];
		for (int i = 0; i < in.length(); i++) {
			split[i] = in.charAt(i);
		}
		System.out.println(Arrays.toString(split));
		ArrayList<Character> mSplit = new ArrayList<>();
		boolean goldenTick = false;
		for (int i = 0; i < split.length; i++) {
			// change x to nxm
			if (i == 0 && split[i] == '-') {
				// -x to -1x
				if (split[i + 1] == 'x') {
					mSplit.add('-');
					mSplit.add('1');
					// negative constant
				} else if (split[i + 1] != 'x') {
					mSplit.add('-');
					mSplit.add(split[i + 1]);
					i++;
				}
			} else if (split[i] == 'x') {
				// case when n=1 or x at the start, change x to 1x
				if ((i - 1 >= 0 && (split[i - 1] == '+' || split[i - 1] == '-')) && i != 1) {
					mSplit.add('1');
				} else if (split[0] == 'x' && goldenTick == false) {
					mSplit.add('1');
					goldenTick = true;
				}
				// case when m=1 or x at the end, change x to x1
				if ((i + 1 < split.length && (split[i + 1] == '+' || split[i + 1] == '-'))
						|| (i == split.length - 1 && split[i] == 'x')) {
					// if (split[i - 1] == '+' || split[i - 1] == '-') {
					// mSplit.add('1');
					// }
					mSplit.add(split[i]);
					mSplit.add('1');
					// case when n and m not equal to 1, then just add x
				} else {
					mSplit.add(split[i]);
				}
			} else if (split[i] == '-') {
				mSplit.add('+');
				mSplit.add(split[i]);
			} else {
				mSplit.add(split[i]);
			}
		}
		System.out.println(Arrays.toString(mSplit.toArray()));
		ArrayList<Double> coeffArr = new ArrayList<>();
		ArrayList<Integer> expArr = new ArrayList<>();
		StringBuilder builder = new StringBuilder(mSplit.size());
		for (Character ch : mSplit) {
			builder.append(ch);
		}
		String nLine = builder.toString();
		String[] slines = nLine.split("[+]");
		coefficients = new double[slines.length];
		exps = new int[slines.length];
		int c = 0;
		int e = 0;
		for (String sl1 : slines) {
			if ((sl1.length() == 1 || sl1.length() == 2) && (sl1 == slines[0] || sl1 == slines[slines.length - 1])) {
				coefficients[c] = Double.parseDouble(sl1);
				c++;
				exps[e] = 0;
				e++;
			} else {
				String[] sl2 = sl1.split("[x]");
				coefficients[c] = Double.parseDouble(sl2[0]);
				c++;
				if (sl2.length == 2) {
					exps[e] = Integer.parseInt(sl2[1]);
					e++;
				} else {
					exps[e] = 0;
					e++;
				}

			}
		}
		/*
		 * int[] expArr = new int[line.length()];
		 * double[] coeffArr = new double[line.length()];
		 * String[] slines = line.split("[+]");
		 * int coeffCount = 0;
		 * int expCount = 0;
		 * boolean NotFFN = false;
		 * 
		 * 
		 * 
		 * for (String sl1 : slines) {
		 * if (sl1.indexOf('-') != -1 && sl1.indexof('x') != -1) {
		 * String[] sl2 = sl1.split("[x]");
		 * for (String sl3 : sl2) {
		 * // negative constant followed by negative coeff
		 * if (sl3.length() == 4) {
		 * String[] sl4 = sl3.split("[-]");
		 * coeffArr[coeffCount] = Integer.parseInt(sl4[0]) * -1;
		 * coeffCount++;
		 * coeffArr[coeffCount] = Integer.parseInt(sl4[1]) * -1;
		 * coeffCount++;
		 * expArr[expCount] = 0;
		 * expCount++;
		 * NotFFN = true;
		 * // positive constant followed by negative coeff
		 * } else if (sl3.length() == 3 && sl3 == sl2[0]) {
		 * String[] sl4 = sl3.split("[-]");
		 * coeffArr[coeffCount] = Integer.parseInt(sl4[0]);
		 * coeffCount++;
		 * expArr[expCount] = 0;
		 * expCount++;
		 * expArr[coeffCount] = Integer.parseInt(sl4[1]) * -1;
		 * coeffCount++;
		 * NotFFN = true;
		 * // positive exponent followed by negative coeff
		 * } else if (sl3.length() == 3) {
		 * String[] sl4 = sl3.split("[-]");
		 * coeffArr[coeffCount] = Integer.parseInt(sl4[1]) * -1;
		 * coeffCount++;
		 * expArr[expCount] = Integer.parseInt(sl4[0]);
		 * expCount++;
		 * NotFFN = true;
		 * } else if (sl3.length() == 1) {
		 * // if previous subline has length 3, then this current subline must be exp
		 * if (NotFFN == true) {
		 * expArr[expCount] = Integer.parseInt(sl3);
		 * expCount++;
		 * NotFFN = false;
		 * // if previous subline ! length 3, then this current subline + coeff
		 * } else {
		 * coeffArr[coeffCount] = Integer.parseInt(sl3);
		 * coeffCount++;
		 * }
		 * }
		 * }
		 * // positive or negative constant with next positive coeff
		 * } else if ((sl1.contains('-') && sl1.length() == 2) || (sl1.length() == 1 &&
		 * sl1 == slines[0])) {
		 * coeffArr[coeffCount] = Integer.parseInt(sl1);
		 * coeffCount++;
		 * expArr[expCount] = 0;
		 * expCount++;
		 * // only positive coefficient x term
		 * } else if (sl1.contains('x') && sl1.length() == 3) {
		 * String[] subline2 = sl1.split("[x]");
		 * coeffArr[coeffCount] = Integer.parseInt(subline2[0]);
		 * coeffCount++;
		 * expArr[expCount] = Integer.parseInt(subline2[1]);
		 * expCount++;
		 * }
		 * }
		 * int cCount = 0;
		 * int eCount = 0;
		 * for (int i = 0; i < coeffArr.length; i++) {
		 * if (coeffArr[i] == 0) {
		 * break;
		 * }
		 * cCount++;
		 * }
		 * for (int i = 0; i < expArr.length; i++) {
		 * if (expArr[i] == 0) {
		 * break;
		 * }
		 * eCount++;
		 * }
		 * int[] ret = new int[eCount];
		 * double[] retd = new double[cCount];
		 * for (int i = 0; i < cCount; i++) {
		 * retd[i] = coeffArr[i];
		 * }
		 * for (int i = 0; i < eCount; i++) {
		 * ret[i] = expArr[i];
		 * }
		 */

	}

	public void saveToFile(String a) throws FileNotFoundException {
		String sum = "";
		for (int i = 0; i < exps.length; i++) {
			if (i == 0) {
				if (exps[i] == 0) {
					if (coefficients[i] == (int) coefficients[i]) {
						sum += (int) coefficients[i];
					} else {
						sum += coefficients[i];
					}
				} else {
					if (coefficients[i] == (int) coefficients[i]) {
						sum += (int) coefficients[i] + "x" + exps[i];
					} else {
						sum += coefficients[i] + "x" + exps[i];
					}

				}
			} else if (coefficients[i] < 0 && exps[i] != 0) {
				if (coefficients[i] == (int) coefficients[i]) {
					sum += (int) coefficients[i] + "x" + exps[i];
				} else {
					sum += coefficients[i] + "x" + exps[i];
				}

			} else if (exps[i] == 0) {
				if (coefficients[i] < 0) {
					if (coefficients[i] == (int) coefficients[i]) {
						sum += (int) coefficients[i];
					} else {
						sum += coefficients[i];
					}
				} else {
					if (coefficients[i] == (int) coefficients[i]) {
						sum += "+" + (int) coefficients[i];
					} else {
						sum += "+" + coefficients[i];
					}
				}
			} else if (coefficients[i] > 0 && exps[i] != 0) {
				if (coefficients[i] == (int) coefficients[i]) {
					sum += "+" + (int) coefficients[i] + "x" + exps[i];
				} else {
					sum += "+" + coefficients[i] + "x" + exps[i];
				}
			}
		}
		PrintStream yes = new PrintStream(a);
		yes.print(sum);
		yes.close();

	}

	public Polynomial add(Polynomial pol) {
		/*
		 * int maxLen = (pol.coefficients.length > coefficients.length) ?
		 * pol.coefficients.length : coefficients.length;
		 * int minLen = (pol.coefficients.length < coefficients.length) ?
		 * pol.coefficients.length : coefficients.length;
		 * double[] retNum = new double[maxLen];
		 * int i = 0;
		 * for (i = 0; i < minLen; i++) {
		 * retNum[i] += pol.coefficients[i];
		 * retNum[i] += coefficients[i];
		 * }
		 * if (pol.coefficients.length > coefficients.length) {
		 * for (int j = i; j < maxLen; j++) {
		 * retNum[j] = pol.coefficients[j];
		 * }
		 * } else {
		 * for (int j = i; j < maxLen; j++) {
		 * retNum[j] = coefficients[j];
		 * }
		 * }
		 */
		// set up key value pairs
		double[][] polDict = new double[pol.exps.length][2];
		double[][] Dict = new double[exps.length][2];
		for (int i = 0; i < pol.exps.length; i++) {
			polDict[i][0] = pol.coefficients[i];
			polDict[i][1] = pol.exps[i];
		}
		for (int i = 0; i < exps.length; i++) {
			Dict[i][0] = coefficients[i];
			Dict[i][1] = exps[i];
		}
		// System.out.println(Arrays.deepToString(Dict));
		// System.out.println(Arrays.deepToString(polDict));
		int count = 0;
		boolean a = true;
		double[] expTest = new double[pol.exps.length];
        ArrayList<Integer> testing = new ArrayList<>();
		// find new array where values are not the same as exps
		for (int i = 0; i < pol.exps.length; i++) {
			for (int j = 0; j < exps.length; j++) {
				if (exps[j] == pol.exps[i]) {
					a = false;
					// add coeff for repeating exps
					Dict[j][0] += pol.coefficients[i];
					polDict[i][0] = Dict[j][0];
                    if(polDict[i][0]==0 && Dict[j][0]==0){
                        polDict[i][1]=-1;
                        Dict[j][1]=-1;
                    }
				}
			}
			if (a == true) {
				expTest[count] = pol.exps[i];
				count++;
			}
			a = true;
		}
		// System.out.println(Arrays.toString(expTest));
		// add expTest and exps together to new array
		//int[] retExps = new int[exps.length + count];
		// System.out.println(exps.length);
		for (int i = 0; i < exps.length; i++) {
            if(Dict[i][1]!=-1) {
				testing.add((int)Dict[i][1]);
			}
		}
		for (int j = 0; j < count; j++) {
			testing.add((int)expTest[j]);
		}
		int[] retExps=new int[testing.size()];
		for(int i=0; i<retExps.length;i++){
			retExps[i]=testing.get(i);
		}
		// System.out.println(Arrays.toString(retExps));
		// bubble sort
		bubbleSort(retExps);
		double[] retCoeff = new double[retExps.length];
		for (int k = 0; k < retExps.length; k++) {
			for (int l = 0; l < polDict.length; l++) {
				if (retExps[k] == polDict[l][1]) {
					retCoeff[k] = polDict[l][0];
				}
			}
			for (int m = 0; m < Dict.length; m++) {
				if (retExps[k] == Dict[m][1]) {
					retCoeff[k] = Dict[m][0];
				}
			}
		}
		Polynomial polly = new Polynomial(retCoeff, retExps);
		return polly;
	}

	void bubbleSort(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < n - i - 1; j++)
				if (arr[j] > arr[j + 1]) {
					// swap temp and arr[i]
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
	}

	public double evaluate(double val) {
		double sum = 0.0;
		double temp = 1.0;
		for (int i = 0; i < coefficients.length; i++) {
			for (int j = 0; j < exps[i]; j++) {
				temp *= val;
			}
			sum += coefficients[i] * temp;
			temp = 1.0;
		}
		return sum;
	}

	public boolean hasRoot(double a) {
		if (evaluate(a) == 0.0) {
			return true;
		} else {
			return false;
		}
	}

	public Polynomial multiply(Polynomial pol) {
		Polynomial[] testPols = new Polynomial[pol.coefficients.length];
		for (int i = 0; i < pol.coefficients.length; i++) {
			// double[] testarr1 = arrMult(pol.coefficients[i], coefficients);
			// int[] testarr2 = arrAdd(pol.exps[i], exps);
			double[] testarr1 = new double[coefficients.length];
			int[] testarr2 = new int[exps.length];
			for (int j = 0; j < exps.length; j++) {
				testarr1[j] = coefficients[j] * pol.coefficients[i];
				testarr2[j] = exps[j] + pol.exps[i];
			}
			testPols[i] = new Polynomial(testarr1, testarr2);
		}
		Polynomial retPol = testPols[0];
		for (int i = 0; i < pol.coefficients.length - 1; i++) {
			retPol = retPol.add(testPols[i + 1]);
		}
		return retPol;
	}
	/*
	 * public double[] arrMult(double a, double[] arr) {
	 * double[] newarr = arr;
	 * for (int i = 0; i < newarr.length; i++) {
	 * newarr[i] = newarr[i] * a;
	 * }
	 * return newarr;
	 * }
	 * 
	 * public int[] arrAdd(int a, int[] arr) {
	 * int[] newarr = arr;
	 * for (int i = 0; i < newarr.length; i++) {
	 * newarr[i] = newarr[i] + a;
	 * }
	 * return newarr;
	 * }
	 */
}
