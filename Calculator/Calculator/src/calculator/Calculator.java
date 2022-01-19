package calculator;
import java.util.Scanner;
import java.util.regex.*;

class Calculator{
	private static Scanner sc=new Scanner(System.in);
	
	static Double a=0.0,b=0.0;
	static String[] arrOfStr= {};
	static String op="";
	static double result=0.0;
	
	//calculating trigonometric values
	private static double trigonoTrigger(String eq) {
		String rex_trig="^\s*([a-zA-Z]+)+\s*([0-9]*)+$";
		
		if(Pattern.matches(rex_trig, eq)) {
			arrOfStr=eq.split("\s*([0-9])+");
			op=eq.replaceAll("[^0-9]","");
			result=Operations.trigonometric(Double.parseDouble(op), arrOfStr[0]);
			System.out.println(eq+"= "+result);
			return result;
		}
		return 0;
	}

	public void Driver(String eq) {
		//matching for standard equations
		//String rex= "^\\s*([-+]?)(\\d+)(?:\s*([-+*%^/])\s*((?:\\s[-+])?\\d+)\\s*)+$";
		String rex="[0-9.)([+\\-*/%^]]+";
		 //matching for negation
		String rex_negation="^\s*([-+])\s*([-+])\s*([\\d+])+$";
		
		String rex_split="\\W+";
		
		//checking for standard equations
		if(Pattern.matches(rex, eq)) {
//			arrOfStr = eq.split(rex_split);
//			op=eq.replaceAll("\\w+","");//extracting operator
//			
//			//extracting numeric values
//			a=Double.parseDouble(arrOfStr[0]); 
//			b=Double.parseDouble(arrOfStr[1]);
//			
//			result=Operations.getOperations(a,b,op);
			int result=CalMulti.calc(eq);
			
			System.out.println(result);
		}
		
		//checking for negation statements
		else if(Pattern.matches(rex_negation, eq)) {
				arrOfStr = eq.split(rex_split);
				op=eq.replaceAll("\\w+",""); //extracting operator
				a=Double.parseDouble(arrOfStr[1]); //extracting numeric value
				result=Operations.getOperations(a,op);
			}
		
		//checking for trigonometric equations
		else if(Pattern.matches("^\s*([a-zA-Z]+)+\s*([0-9]*)+$", eq)){
			double trig=0;
			trig=trigonoTrigger(eq);
			if(trig==0) {				
				arrOfStr[0]=eq.replaceAll("[^0-9]","");
				result=Operations.misc(Double.parseDouble(arrOfStr[0]), "log");
			}
			else
				result=trig;
		}
		
		//checking for factorial equation
		else if(Pattern.matches("^([0-9]+)\\s*([!])$", eq)) {
						op=eq.replaceAll("\\W+",""); //extracting numeric value
						result=Operations.factorial(Integer.parseInt(op));
						System.out.println("Factorial of "+Integer.parseInt(op)+" is: "+result);
			}
		else if(Pattern.matches("^[0-9]+",eq)) {
			System.out.println("Binary value of the number is: "+Integer.toBinaryString(Integer.parseInt(eq)));
		}
		else {
			System.out.println("Please recheck the equation and try again!");
		}

	}
}