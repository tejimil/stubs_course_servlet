package calc.logic;

//import java.io.Serializable;

public class calculations{
	
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	//private static final calculations instance = new calculations();
	private double res;
	
	/*public static calculations getInstance() {
		return instance;
	}*/
	public calculations() {
		this.res = 55555;
	}

	public void docalc(double a, double b, char c) {
		
		if (c=='+') 
			res = a+b;
		
		if (c=='*')
			res = a*b;
		
		if (c=='/')
			res = a/b;
		
		if (c=='-')
			res = a-b;
		
	}


	public double getRes() {
		return this.res;
	}
}
