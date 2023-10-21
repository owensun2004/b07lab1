package lab3;
/**
 * The `RationalNumber` class represents a rational number with a numerator and denominator.
 * It extends the class `SpecialNumber` and implements `Comparable`, which allows for mathematical operations
 * and comparisons with other `RationalNumber` objects. The class contains methods such as addition,
 * division, comparisons, and simplification of rational numbers. It also includes many helper functions
 * for finding the greatest common divisor (GCD) and reducing rational numbers to their lowest terms.
 * 
 * @author Shengxiang Sun
 * @version 1.0
 * @since 2023-10-21
 */
public class RationalNumber extends SpecialNumber implements Comparable<RationalNumber>{
    int numerator;
    int denominator;
    
    /**
     * Constructor for RationalNumber, assigns numerator and
     * denominator with integer arguments a and b respectively
     * 
     * @param a              an integer representing the numerator
     * @param b              an integer representing the denominator
     * @throws Lab3Exception a String that tells b must be non-zero
     */
    public RationalNumber(int a, int b) throws Lab3Exception {
        numerator=a;
        if(b==0){
            throw new Lab3Exception("Denominator cannot be zero");
        }else {
            denominator=b;
        }
    }
    
    /**
     * Adds two RationalNumber together by first making their denominators
     * the same, which is done by multiplying the calling object's denominator 
     * with a's denominator and the calling object's numerator with a's denominator,
     * and similar process for a's numerator and denominator. Then adds the modified
     * numerators after the denominators are multiplied together.
     * Argument a must be a RationalNumber type
     * 
     * @param a              RationalNumber with fields numerator and denominator
     * @return               a RationalNumber that is the sum of a and this
     * @throws Lab3Exception a String that tells a must be a RationalNumber
     */
    @Override
    public SpecialNumber add(SpecialNumber a) throws Lab3Exception{
        if(a instanceof RationalNumber){
        	RationalNumber bruh = new RationalNumber(0, 1);
            bruh.numerator=((RationalNumber) a).numerator*this.denominator + this.numerator*((RationalNumber) a).denominator;
            bruh.denominator=((RationalNumber) a).denominator*this.denominator;
            //bruh.numerator=red(bruh.numerator, bruh.denominator);
            //bruh.denominator=red(bruh.denominator, bruh.numerator);
            return bruh;
        }else{
            throw new Lab3Exception("Cannot add an incompatible type");
        }
    }
    
    /**
     * Divides the calling object RationalNumber by integer a, which is the same
     * as multiplying the RationalNumber's denominator with a
     * Argument a must be non-zero
     * 
     * @param a              integer that will divide this
     * @return               the new RationalNumber that was divided by a
     * @throws Lab3Exception a String that tells a must be non-zero
     */
    @Override
    public SpecialNumber divideByInt(int a) throws Lab3Exception {
        if(a==0){
            throw new Lab3Exception("Cannot divide by zero");
        }
        this.denominator=this.denominator*a;
        //this.numerator=red(this.numerator, this.denominator);
        //this.denominator=red(this.denominator, this.numerator);
        return this;
    }
    
    /**
     * Compares the calling object RationalNumber's value with n's value,
     * which is computed by dividing its numerator by its denominator
     * 
     * @param n a RationalNumber that is being compared to the calling object RationalNumber
     * @return  1 if the value of the calling object RationalNumber is greater than
     * n's value, -1 if smaller the value of the calling object RationalNumber is smaller than
     * n's value, and 0 if they are equal
     *
     */
    @Override
    public int compareTo(RationalNumber n){
        if(((double)this.numerator/this.denominator) > ((double)n.numerator/n.denominator)){
            return 1;
        }else if (((double)this.numerator/this.denominator) <((double)n.numerator/n.denominator)) {
            return -1;
        }
        else {
            return 0;
        }
    }

    /**
     * Compares the calling object RationalNumber's value with n's value,
     * which is computed with helper function isPowerof
     * 
     * @param n a RationalNumber that is being equated to the calling object RationalNumber
     * @return  true if n is the calling object RationNumber or isPowerof returns true for
     * both numerators and denominators, false if n is not an instance of RationalNumber or
     * isPowerof returns false both numerators or denominators
     */
    @Override
    public boolean equals(Object n){
        if(n==this){
            return true;
        }
        if(!(n instanceof RationalNumber)){
            return false;
        }
        RationalNumber n2 = (RationalNumber)n;
        return isPowerof(numerator, n2.numerator) && isPowerof(denominator, n2.denominator);
    }
    
    /**
     * Finds if integer a is a multiple of integer b or vice versa, by continuing to add
     * the smaller integer by its value until its value is greater or equal to the larger integer
     * 
     * 
     * @param a any integer
     * @param b any integer
     * @return true if integer a is a multiple of integer b or vice versa
     */
    public boolean isPowerof(int a, int b) {
    	int max = Math.max(a, b);
    	int min = Math.min(a, b);
    	while(min<max) {
    		min+=min;
    	}
    	if(min==max) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Returns the hashcode of the calling object RationalNumber using helper function red,
     * which adds the reduced numerator with the reduced denominator
     * 
     * @return hashcode of calling object RationalNumber
     */
    @Override
    public int hashCode(){
        return red(this.numerator, this.denominator) + red(this.denominator, this.numerator);
    }
    
    /**
     * Returns the lowest term of integer x with respect to integer y, using helper function gcd
     * 
     * @param x any integer
     * @param y any integer
     * @return greatest common factor of x and y
     */
    public static int red(int x, int y) 
    { 
        int d = gcd(x, y); 
        x = x / d; 
        return x;
    } 
    
    /**
     * Returns the greatest common divisor of integers a and b by using the common gcd formula
     * 
     * @param a any integer
     * @param b any integer
     * @return  gcd of a and b
     */
    public static int gcd(int a, int b) 
    { 
        if (b == 0) 
            return a; 
        return gcd(b, a % b); 
         
    }
}
