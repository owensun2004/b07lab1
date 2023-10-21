package lab3;
import java.util.List;

public abstract class SpecialNumber {
    public abstract SpecialNumber add(SpecialNumber a) throws Lab3Exception;
    public abstract SpecialNumber divideByInt(int a) throws Lab3Exception;
    /**Returns the average of the instances of SpecialNumber in the list a. 
     * The list contains instances of RationalNumber or ComplexNumber, which is
     * defined by a/b and c+id for all a, b in int, c, d in double, respectively.
     * Returns the average by using the add method to add all instances together, then using
     * the divideByInt method to compute the average of the instances.
     * The list a must be non-empy, or a Lab3Exception will be thrown
     * 
     * @param a              the list containing instances of class SpecialNumber, 
     * @return               the average of SpecialNumber from the list a
     * @throws Lab3Exception a String that tells the list must be non-empty
     */
    public static SpecialNumber computeAverage(List<SpecialNumber> a) throws Lab3Exception{
    	if(a==null) {
    		throw new Lab3Exception("List cannot be empty");
    	}
        SpecialNumber sum;
        if(a.get(0) instanceof RationalNumber){
            sum=new RationalNumber(0, 1);
        }
        else{
            sum=new ComplexNumber(0, 0);
        }
        for(int i=0; i<a.size(); i++){
            if(a.get(i)==null){
                throw new Lab3Exception("List cannot be empty");
            }
            sum=sum.add(a.get(i));
        }
        return sum.divideByInt(a.size());
    }
}
