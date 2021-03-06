package file_operations;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DeleteOperations{
	static Scanner sc=new Scanner(System.in);
	static String [] list_of_files= {};

	//function to delete a specific record
	public static void deleteRecord() {
		
		System.out.println("You sure about deleting the record? (y/n)\n>");
		String ch=sc.next().toLowerCase();
		if(ch.equals("n"))
			return;
		System.out.print("Choose the addressbook from which you wish to delete:");
		
		//getting list of the all the available address books
		list_of_files=AddOperations.displayAddBooks();
		
		System.out.print("\n>");
		int ch_del=sc.nextInt();
		
		File delete_record=new File('Path');
		Scanner dataReader;
		
		//creating ArrayList to store records
		ArrayList<String> hs=new ArrayList<String>();        
        System.out.println("Available records: \n");
        
		try {
			dataReader = new Scanner(delete_record);
			int i=0;
			while (dataReader.hasNextLine()) {  
				String Line = dataReader.nextLine();
				hs.add(Line); //adding records to ArrayList
				System.out.println((i+1)+". "+Line); 
				i++;
			}
			dataReader.close();
			
			//deleting with respect to line number of the record
			System.out.print("\nEnter the line number in the records to delete: \n>");
			int ch_del_line=sc.nextInt();
			
			//converting ArrayList to Array to get a record of specific index
			String[] Data = hs.toArray(new String[hs.size()]);
			
			//duplicate file to which records are written
			FileWriter myWriter = new FileWriter('Path');
			
			dataReader = new Scanner(delete_record);
			while(dataReader.hasNextLine()) {
				String Line = dataReader.nextLine();
				
				//writing records to a duplicate file
				//excluding the record to be deleted
				if(Data[ch_del_line-1].equals(Line))
					continue;
				
				myWriter.write(Line+"\n");
			}			
			dataReader.close();
			myWriter.close();
			
			//renaming duplicate file to the name of the address book
			//this file contains all the records from the address book excluding the one to be deleted
			Path source = Paths.get('Path');
			String new_name=delete_record.getName();
			
			//deleting existing address book to avoid duplicates
			delete_record.delete();
			
			//renamed successfully
			Files.move(source, source.resolveSibling(new_name));
			
			System.out.println("Record successfully deleted!\r\n");
			return;
		} 
		catch (IOException e) {
			System.out.println("Oops! Error occurred while deleting record!-- Deletion");
			e.printStackTrace();
			return;
		}  
	}
	
	//functional corresponding to modifying records
	public static void deleteRecord(String fname,int no,String [] Data) {
		
		//Data variable is the complete record to be deleted
		
		Scanner dataReader;
        
		File f=new File('Path');
		
		try {
			
			//duplicate file to which records are written
			FileWriter myWriter = new FileWriter('Path');
			
			dataReader = new Scanner(f);
			while(dataReader.hasNextLine()) {
				String Line = dataReader.nextLine();
				
				//writing records to a duplicate file
				//excluding the record to be moved
				if(Data[no-1].equals(Line)) {
					continue;
				}
				
				myWriter.write(Line+"\n");
			}			
			dataReader.close();
			myWriter.close();
			
			//renaming duplicate file to the name of the address book
			//this file contains all the records from the address book excluding the one to be moved
			Path source = Paths.get('Path');
			String new_name=f.getName();
			
			//deleting existing address book to avoid duplicates
			
			System.gc();
			f.delete();
			
			//renamed successfully
			try {
				Files.move(source, source.resolveSibling(new_name));
			} 
			
			catch (IOException e1) {
				System.out.println("Something went wrong with renaming!");
				e1.printStackTrace();
				return;
			}
			
			return;
		} 
		catch (IOException e) {
			System.out.println("Oops! There is an error while deleting!");
			e.printStackTrace();
			return;
		}  
	}
	
	//function to delete address book
	public static void deleteAddBook(){
		
		System.out.println("You sure about deleting the Addressbook? (y/n)\n>");
		String ch_addbook=sc.next().toLowerCase();
		if(ch_addbook.equals("n"))
			return;
		
		System.out.println("Choose the addressbook you wish to delete: \n>");
		list_of_files=AddOperations.displayAddBooks();
		int ch=sc.nextInt();
		
		File delete_adbook=new File('Path');
		
		
		if(delete_adbook.exists()) { //checking for existence of file
			delete_adbook.delete();
			System.out.println("\r\nFile deleted successfully!\r\n");
			return;
		}
		else {
			System.out.println("File not found! Check the name and try again!\r\n>");
			return;
		}
	}
}