package calculator;
import java.lang.Math;

class Operations{
	
	//function to calculate trigonometric operations
	public static double trigonometric(double a,String op) {
		a=Math.toRadians(a);
		if(op.equals("cos") || op.equals("cosine"))
			return (Math.cos(a));
		
		
		else if(op.equals("sin") || op.equals("sine")) 
			return Math.sin(a);
		
		else if(op.equals("tan") || op.equals("tangent"))
			return Math.tan(a);
		
		else
			return 0;
	}
	
	//recursive function to calculate factorial
	public static int factorial(int a) {
		if(a==0)
			return 1;
		else
			return (a*factorial(a-1));
	}
	
	//function to calculate log values
	public static double misc(double a, String op) {
		
		if (op.equals("log")) {
			return Math.log(a);
		}
		return 0;
	}
	
	private static double negationOperation(double a,String op) {
		if(op.equals("+-") || op.equals("-+")) {
			System.out.println(op+a+"= "+(int)(-a));
			return -a;
		}
		else if(op.equals("--") || op.equals("++")) {
			System.out.println(op+a+"= "+(int)(a));
			return a;
		}
		return 0;
	}

	
	//Overwriting function to calculate negation operations
	public static double getOperations(double a,String op) {
		return negationOperation(a, op);
	}
}