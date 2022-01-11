package add_operations;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import file_operations.*;
public class AddRecord{
	
	//function to check format of email
	private static boolean email_check(String email) {
		
		//regular expression for email
		String email_rex="^\s*([a-zA-Z0-9._]+)[@]([a-zA-Z]+)(.*)[(a-zA-Z)]+$";
		if(Pattern.matches(email_rex, email))
			return true;
		else
			return false;
	}
	
	//function to add record
	public static void add_record() throws IOException{
		
		String first_name="",last_name="";
		String phone="",email="";
		String address="";
		
		Scanner sc=new Scanner(System.in);
		
		//taking inputs for necessary details
		System.out.println("Record Entry in progress: ");
		
		System.out.print("Enter First Name: \n>");
		first_name=sc.nextLine();
		
		System.out.print("Enter Last Name: \n>");
		last_name=sc.nextLine();
		
		System.out.print("Enter Contact number (without country code): \n>");
		phone=sc.nextLine();
		if(!Pattern.matches("\s*[0-9]{10}",phone) {
			System.out.print("Invalid Contact Number! \nEnter the Contact number to 10 digits only.\n>");
			phone=sc.nextLine();
		}
		
		System.out.print("Enter Email Address: \n>");
		email=sc.nextLine();
		boolean e=email_check(email);
		while(!e) {
			System.out.print("Invalid Email Address!\r\nTry Again!\n>");
			email=sc.nextLine();
			e=email_check(email);
		}
		
		System.out.print("Enter Address: \n>");
		address=sc.nextLine();
		
		//writing to file
		AddOperations.addDetails(first_name,last_name,phone,email,address);
		return;
	}
}
