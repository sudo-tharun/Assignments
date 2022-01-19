package file_operations;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import add_operations.*;
public class Modify{
	
	//function to modify existing records
	public static void modifyRecords(){
		
		Scanner sc=new Scanner(System.in);
		
		String [] list_of_files=AddOperations.displayAddBooks();
		System.out.println("Choose the addressbook where the modification needs to be done: \n>");
		int ch_modify=sc.nextInt(); //variable to store the address book number
		
		//opening the file to be modified
		File modify_record=new File('Path');
		
		System.out.println("\r\nRecords from "+modify_record.getName()+":");
		
		Scanner dataReader;
		
		ArrayList<String> hs=new ArrayList<String>();

		try {
			dataReader = new Scanner(modify_record);
			int i=0;
			while (dataReader.hasNextLine()) {  
				String Line = dataReader.nextLine();
				hs.add(Line);
				System.out.println((i+1)+". "+Line); 
				i++;
			}
			System.out.println("\r\n");
			dataReader.close();
			
			System.out.print("Enter the line number in the records to modify: \n>");
			String ch_modify_line_str=sc.next(); //storing the line number to modify from the file
			
			int ch_modify_line=Integer.parseInt(ch_modify_line_str);
			
			//System.out.println("Line: "+ch_modify_line);
			
			String[] Data = hs.toArray(new String[hs.size()]);
			
			String [] records=Data[ch_modify_line-1].split(","); //splitting details from the record
			
			String [] parameters={"First Name","Last Name","Contact","Email Address","Address"};
			
			System.out.print("\r\nDetails in the record are as follows: \n");
			
			//printing the records to identify
			for(i=0;i<records.length;i++)
				System.out.println((i+1)+". "+parameters[i]+": "+records[i]);
			
			System.out.print("Choose the parameter to modify from above: \n>");
			int ch_modify_parameter=sc.nextInt(); //to store the parameter in the record to modify
			
			if(ch_modify_parameter>records.length || ch_modify_parameter<1) {
				System.out.println("Invalid Choice!");
				return;
			}
			
			System.out.println("Enter new entry for "+parameters[ch_modify_parameter-1]+": \n>");
			String modification=sc.next(); //storing modified detail
			
			//verifying mobile number if choice is 3
			if(ch_modify_parameter==3) {
				while(!Pattern.matches("\s*[1-9]{10}", modification)) {
					System.out.print("Invalid Contact Number! \nEnter the Contact number to 10 digits only.\n>");
					modification=sc.nextLine();
				}
			}
			
			//verifying email address if choice is 4
			if(ch_modify_parameter==4) {
				boolean e=AddRecord.email_check(modification);
				while(!e) {
					System.out.println("Invalid Email Address!\r\nTry Again!\n>");
					modification=sc.next();
					e=AddRecord.email_check(modification);
				}
			}
			
			//changing record in the array
			records[ch_modify_parameter-1]=modification;
			
			//creating duplicate file to write all the records including modifications
			FileWriter myWriter = new FileWriter('Path');

			dataReader = new Scanner(modify_record);

			while(dataReader.hasNextLine()) {
				String Line = dataReader.nextLine();

				if(Data[ch_modify_line-1].equals(Line)) {
					for(i=0;i<records.length;i++) {
						if(i==(records.length-1)) {
							myWriter.write(records[i]+"");
							break;
						}
						myWriter.write(records[i]+",");
					}
					myWriter.write("\n");
					continue;
				}
				
				myWriter.write(Line+"\n");
			}
			
			dataReader.close();
			myWriter.close();
			
			//renaming file
			Path source = Paths.get('Path');
			String new_name=modify_record.getName();
			
			//deleting existing address book to avoid duplicates
			modify_record.delete();
			
			//renamed successfully
			Files.move(source, source.resolveSibling(new_name));
			
			System.out.println("Record modified successfully!\r\n");
			return;
		}
		catch(IOException e) {
			System.out.println("Oops! Something went wrong in modification!");
			e.printStackTrace();
		}
	}
}