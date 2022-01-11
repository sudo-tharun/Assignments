import java.io.IOException;
import java.util.Scanner;
import add_operations.*;
import file_operations.AddOperations;
import file_operations.DeleteOperations;
import file_operations.ViewOperation;
public class Driver{
	
	private static int menu() throws IOException {
		int ch_main=0;
		
		Scanner sc= new Scanner(System.in);
		System.out.println("Available Features: ");
		System.out.println("1. Add Entry\n2. Add Addressbook");
		System.out.println("3. Delete Entry\n4. Delete Addressbook");
		System.out.println("5. View Addressbook(s)");
		System.out.println("6. Quit\r\n");
		
		System.out.print("Choose what to do with the Application!\r\n>");
		
		ch_main=sc.nextInt();
		
		switch(ch_main) {
		
		case 1:{
			System.out.println("\r\nAdd Entry");
			AddRecord.add_record();
			menu();
			break;
		}
		
		case 2:{
			System.out.println("\r\nAdd Addressbook");
			AddOperations.addAddrBook();
			menu();
			break;
		}
		
		case 3:{
			System.out.println("Delete Record\r\n");
			DeleteOperations.deleteRecord();
			menu();
			break;
		}
		
		case 4:{
			System.out.println("Delete Addressbook\r\n");
			DeleteOperations.deleteAddBook();
			menu();
			break;
		}
		
		case 5:{
			System.out.println("\r\nView Addressbook(s) details\r\n");
			ViewOperation.viewRecords();
			menu();
			break;
		}
		
		case 6:{
			System.out.println("Application Terminated!");
			break;
		}
		
		default:{
			System.out.println("Invalid Entry! Try again!!");
			menu();
			break;
		}
		
		}
		sc.close();
		return 0;
	}
	
	public static void main(String [] args) throws IOException{
		System.out.println("Welcome to Addressbook Manager!\r\n");
		
		menu();

	}
}