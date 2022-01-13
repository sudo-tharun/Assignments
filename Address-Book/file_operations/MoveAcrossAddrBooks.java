package file_operations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class MoveAcrossAddrBooks{
	
	public static void moveRecords(){
		
		Scanner sc=new Scanner(System.in);
		
		File move= new File("path");
		String [] list_of_files=move.list();
		
		System.out.println("\r\nAvailable files are: ");
		for(int i=0;i<list_of_files.length;i++) {
			System.out.println((i+1)+". "+list_of_files[i]);
		}
		
		System.out.println("Enter '0' to return to main menu!");
		System.out.print("\nChoose from which addressbook you wish to move: \n>");
		int ch_move=sc.nextInt();
		if(ch_move==0) {
			System.out.println("Returning to main menu!");
			return;
		}
		
		//move_record--to store address book from which the record has to be moved
		
		File move_record=new File("path");
		
		System.out.println("Records from "+list_of_files[ch_move-1]);
		try {
			int i=0;
			Scanner dataReader=new Scanner(move_record);
			
			ArrayList<String> hs=new ArrayList<String>();
			
			while(dataReader.hasNextLine()) {
				String Line=dataReader.nextLine();
				System.out.println((i+1)+". "+Line);
				hs.add(Line);
				i++;
			}
			
			
			System.out.print("\r\nChoose one record from above to move: \n>");
			int ch_move_record=sc.nextInt(); //to store selected record from address book
			
			String[] Data = hs.toArray(new String[hs.size()]);
			
			dataReader.close();
			
			if(ch_move_record<1 || ch_move_record>Data.length) {
				System.out.println("Invalid Input! Returning to main menu...");
				return;
			}
			
			//First adding record to other address book
			int index=ch_move-1;
			
			String [] records=Data[index].split(",");
			
			//Adding selected records to other address book (chosen later)
			try {
				AddOperations.addDetails(records[0],records[1],records[2],records[3],records[4]);
			} 
			catch (IOException e) {
				System.out.println("Something went wrong in modification!");
				e.printStackTrace();
			}
			
			//Deleting existing record from selected address book
			DeleteOperations.deleteRecord(list_of_files[ch_move-1],ch_move_record,Data);
			
			//renaming duplicate file to the name of the address book
			//this file contains all the records from the address book excluding the one to be moved
			Path source = Paths.get("path");
			String new_name=move_record.getName();
			
			//deleting existing address book to avoid duplicates
			if(move_record.delete())
				System.out.println("Deletion successful!");
			
			//renamed successfully
			try {
				Files.move(source, source.resolveSibling(new_name));
			} 
			
			catch (IOException e1) {
				System.out.println("Something went wrong with renaming-- moving records!");
				e1.printStackTrace();
			}
			
			System.out.println("Records Modified Successfully!");
			return;
		} 
		
		catch (FileNotFoundException e) {
			System.out.println("Oops! Something went wrong while moving!");
			e.printStackTrace();
		}
	}
}