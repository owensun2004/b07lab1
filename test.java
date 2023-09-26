import java.util.ArrayList;
import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        String line = "4-x-4x5-x2-5+6-3x-x-x+5";
        char[] split = new char[line.length()];
        for (int i = 0; i < line.length(); i++) {
            split[i] = line.charAt(i);
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
        for (String sl1 : slines) {
            if ((sl1.length() == 1 || sl1.length() == 2) && (sl1 == slines[0] || sl1 == slines[slines.length - 1])) {
                coeffArr.add(Double.parseDouble(sl1));
            } else {
                String[] sl2 = sl1.split("[x]");
                coeffArr.add(Double.parseDouble(sl2[0]));
                if (sl2.length == 2) {
                    expArr.add(Integer.parseInt(sl2[1]));
                }

            }
        }
        System.out.println(Arrays.toString(coeffArr.toArray()));
        System.out.println(Arrays.toString(expArr.toArray()));
    }
}