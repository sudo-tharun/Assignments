package file_operations;
import java.io.*;
import java.util.Scanner;

public class AddOperations{
	
	static String [] list_of_files= {};
	static Scanner sc=new Scanner(System.in);
	
	//function to display existing address books
	public static String [] displayAddBooks(){
		File display= new File("C:\\Users\\Kapture\\eclipse-workspace\\Addressbook\\address_books");
		list_of_files=display.list();
		System.out.println("\r\nAvailable addressbooks are:");
		for(int i=0;i<list_of_files.length;i++)
			System.out.println((i+1)+". "+list_of_files[i]);
		return list_of_files;
	}
	
	//function to add address book	
	public static void addAddrBook() {
		System.out.println("\rEnter the name of the addressbook: \n>");
		String addr_book=sc.nextLine();
		File addr=new File("C:/Users/Kapture/eclipse-workspace/Addressbook/address_books/"+addr_book+".txt");
		if(!addr.exists()) {
			try {
				addr.createNewFile();
				System.out.println("\r\nFile created successfully!\r\n");
			} 
			catch (IOException e) {
				System.out.println("Error occurred!");
				e.printStackTrace();
			}
		}
		else
			System.out.println("\r\n File already exists!");
		return;
	}
	
	//function to add details to existing address book
	public static void addDetails(String fname,String lname,String ph,String email,String addr) throws IOException {
		
		System.out.print("\r\nChoose one addressbook from the below: ");
		displayAddBooks();
		System.out.print("\n>");
		int ch_add=sc.nextInt();
		
		try {
			FileWriter myWriter = new FileWriter("C:/Users/Kapture/eclipse-workspace/Addressbook/address_books/"+list_of_files[ch_add-1],true);
	    	myWriter.write(fname+","+lname+","+ph+","+email+","+addr+".\n");
	    	myWriter.close();
	    	System.out.print("\r\nDetails successfully added to "+list_of_files[ch_add-1]+" !");
	    	return;
		}
		catch(IOException e){
			System.out.println("Oops! Something went wrong!");
			e.printStackTrace();
		}
	}
}