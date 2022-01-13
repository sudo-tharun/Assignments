package file_operations;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import add_operations.AddRecord;

public class AddOperations{
	
	static String [] list_of_files= {};
	static Scanner sc=new Scanner(System.in);
	
	//function to display existing address books
	public static String [] displayAddBooks(){
		
		//opening directory
		File display= new File("path");
		list_of_files=display.list();
		System.out.println("\r\nAvailable addressbooks are:");
		
		for(int i=0;i<list_of_files.length;i++)
			System.out.println((i+1)+". "+list_of_files[i]);
		return list_of_files;
	}
	
	//function to add address book	
	public static void addAddrBook() {
		
		System.out.print("Enter '0' to return to main menu and '-1' to goto previous menu!\n");
		System.out.print("\rEnter the name of the addressbook you wish to add: \n>");
		String addr_book=sc.next();
		
		if(addr_book.equals("0")) {
			System.out.println("Returning to main menu...");
			return;
		}
		
		if(addr_book.equals("-1"))
			return;
		
		File addr=new File("path");
		
		if(!addr.exists()) {
			try {
				addr.createNewFile();
				System.out.println("\r\nFile created successfully!\r\n");
			} 
			catch (IOException e) {
				System.out.println("Error occurred while creating addressbook!");
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
		System.out.print("Enter '-1' if the preferred addressbook is not present above!");
		System.out.print("\n>");
		int ch_add=sc.nextInt();
		
		if(ch_add==-1) {
			addAddrBook();
			System.out.print("\r\nChoose one addressbook from the below: ");
			displayAddBooks();
			System.out.print("\n>");
			ch_add=sc.nextInt();
		}
		
		
		ArrayList<String> hs=new ArrayList<String>();
		
		try {
			File f=new File("path");
			
			Scanner dataReader=new Scanner(f);
			while(dataReader.hasNextLine()) {
				String Line = dataReader.nextLine();
				hs.add(Line);
			}
			
			String[] Data = hs.toArray(new String[hs.size()]);
			
			String[] records= new String[Data.length];
			
			String [] emails= new String[records.length];
			
			for(int i=0;i<Data.length;i++) {
				records=Data[i].split(",");
				emails[i]=records[3];
			}
			
			//checking for unique email address
			for(int i=0;i<emails.length;i++) {
				if(emails[i].equals(email)) {
					System.out.println("Duplicate found! Enter complete details again!");
					AddRecord.add_record();
					break;
				}
			}
			
			dataReader.close();
			
			//writing to file
			FileWriter myWriter = new FileWriter("filename/path");
	    	myWriter.write("\n"+fname+","+lname+","+ph+","+email+","+addr+"\n");
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