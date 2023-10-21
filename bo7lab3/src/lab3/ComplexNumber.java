package lab3;
import java.lang.Math;
public class ComplexNumber extends SpecialNumber implements Comparable<ComplexNumber>{
    double real;
    double imaginary;
    public ComplexNumber(double a, double b){
        real=a;
        imaginary=b;
    }
    public SpecialNumber add(SpecialNumber a) throws Lab3Exception{
        if(a instanceof ComplexNumber){
            this.real+=((ComplexNumber) a).real;
            this.imaginary+=((ComplexNumber) a).imaginary;
            return this;
        }else{
            throw new Lab3Exception("Cannot add an incompatible type");
        }
    }
    public SpecialNumber divideByInt(int a) throws Lab3Exception {
        if(a==0){
            throw new Lab3Exception("Cannot divide by zero");
        }
        this.real=this.real/a;
        this.imaginary=this.imaginary/a;
        return this;
    }
    @Override
    public int compareTo(ComplexNumber n){
        if(Math.sqrt(Math.pow(real, 2) + Math.pow(imaginary, 2)) > Math.sqrt(Math.pow(n.real, 2) + Math.pow(n.imaginary, 2))){
            return 1;
        }else if (Math.sqrt(Math.pow(real, 2) + Math.pow(imaginary, 2)) < Math.sqrt(Math.pow(n.real, 2) + Math.pow(n.imaginary, 2))) {
            return -1;
        }
        else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object n){
        if(n==this){
            return true;
        }
        if(!(n instanceof ComplexNumber)){
            return false;
        }
        ComplexNumber n2 = (ComplexNumber) n;
        return real == n2.real && imaginary == n2.imaginary;
    }

    @Override
    public int hashCode(){
        return (int)(this.real + this.imaginary);
    }
}
