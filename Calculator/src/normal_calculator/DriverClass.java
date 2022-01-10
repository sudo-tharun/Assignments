package normal_calculator;
import java.util.Scanner;

public class DriverClass{
	
	//help menu
	private static void helperFunction() {
		System.out.println("Welcome to the Helper Menu!\r\n");
		System.out.println("Available Operations:");
		System.out.println("Standard: A op B \r\n");
		System.out.println("Negation: [+/-] [+/-] A \r\n");
		System.out.println("Trigonometric: sin/cos/tan \r\n");
		System.out.println("Factorial: A ! \r\n");
		System.out.println("Miscellaneous: log A \r\n");
		System.out.println("Enter 'quit' or 'q' to terminate application!");
		System.out.println("A and B are ONLY Integers! 'op' is the operator");
	}
	
	//driver function
	public static void main(String [] args) {
		Scanner sc=new Scanner(System.in);
		String input="";
		int q=0;
		Calculator c=new Calculator();
		System.out.print(">");
		input=sc.nextLine();
		while(true) {
			if(input.equals("q") || input.equals("quit"))
				break;
			else if(input.equals("help"))
				helperFunction();
			else {
				c.Driver(input);
			}
			System.out.println(">");
			input=sc.nextLine();
		}
		System.out.println("Application Terminated!");
	}
	
}