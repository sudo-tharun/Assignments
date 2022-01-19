package file_operations;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import add_operations.AddRecord;

public class AddOperations{
	
	static Scanner sc=new Scanner(System.in);
	
	//function to display existing address books
	public static String [] displayAddBooks(){
		
		//opening directory
		File display= new File('Path');
		String []list_of_files=display.list();
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
		
		File addr=new File('Path');
		
		if(!addr.exists()) {
			try {
				addr.createNewFile();
				System.out.println("\r\nFile created successfully!\r\n");
			} 
			catch (IOException e) {
				System.out.println("Error occurred while creating addressbook!");
				e.printStackTrace();
				return;
			}
		}
		else
			System.out.println("\r\n File already exists!");
		return;
	}
	
	//function to add details to existing address book
	public static void addDetails(String fname,String lname,String ph,String email,String addr) throws IOException {
		
		System.out.print("\r\nChoose one addressbook from the below: ");
		String list_of_files[]=displayAddBooks();
		System.out.print(">");
		int ch_add=sc.nextInt();
		
		if(ch_add>list_of_files.length||ch_add<-1)
			System.out.println("Invalid Choice.");
		
		ArrayList<String> hs=new ArrayList<String>();
		
		try {
			File f=new File('Path');
			
			Scanner dataReader=new Scanner(f);
			while(dataReader.hasNextLine()) {
				String Line = dataReader.nextLine();
				hs.add(Line);
			}
			
			String[] Data = hs.toArray(new String[hs.size()]);
			
			String [] emails= new String[hs.size()];
			
			for(int i=0;i<=(Data.length-1);i++) {
				String [] records=Data[i].split(",");
				emails[i]=records[3];
			}
			//checking for unique email address
			for(int i=0;i<=(emails.length-1);i++) {
				if(emails[i].equals(email)) {
					System.out.println("Duplicate found for email! Enter complete details again!");
					AddRecord.add_record();
					break;
				}
			}
			
			dataReader.close();
			
			//writing to file
			FileWriter myWriter = new FileWriter('Path',true);
	    	myWriter.write(fname.toLowerCase()+","+lname.toLowerCase()+","+ph+","+email+","+addr+"\n");
	    	myWriter.close();
	    	System.out.print("\r\nDetails successfully added to "+list_of_files[ch_add-1]+" !");
	    	return;
		}
		catch(IOException e){
			System.out.println("Oops! Something went wrong!");
			e.printStackTrace();
			return;
		}
	}
}