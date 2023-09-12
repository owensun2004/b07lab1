public class Polynomial{
	double[] coefficients;
	public Polynomial(){
		coefficients = new double[]{0.0};
	}
	public Polynomial(double[] arr){
		coefficients = new double[arr.length];
		for(int i=0; i<arr.length; i++){
			coefficients[i] = arr[i];
		}
	}
	public Polynomial add(Polynomial pol){
		int maxLen = (pol.coefficients.length > coefficients.length) ? pol.coefficients.length : coefficients.length;
		int minLen = (pol.coefficients.length < coefficients.length) ? pol.coefficients.length : coefficients.length;
		double[] retNum = new double[maxLen];
		int i = 0;
		for(i=0; i<minLen; i++){
			retNum[i] += pol.coefficients[i];
			retNum[i] += coefficients[i];
		}
		if(pol.coefficients.length>coefficients.length){
			for(int j=i; j<maxLen; j++){
				retNum[j] = pol.coefficients[j];
			}
		}else{
			for(int j=i; j<maxLen; j++){
				retNum[j] = coefficients[j];
			}
		}
		Polynomial polly = new Polynomial(retNum);
		return polly;
	}
	public double evaluate(double val){
		double sum=0.0;
		double temp=1.0;
		for(int i=0; i<coefficients.length; i++){
			for(int j=0; j<i; j++){
				temp*=val;
			}
			sum += coefficients[i]*temp;
			temp=1.0;
		}
		return sum;
	}
	public boolean hasRoot(double a){
		if(evaluate(a)==0.0){
			return true;
		}else{
			return false;
		}
	}
}
			
		