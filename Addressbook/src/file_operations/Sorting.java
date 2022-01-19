package file_operations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Sorting{
	static Scanner sc=new Scanner(System.in);
	
	
	public static ArrayList<String> sortBy(int parameter,String file_name) {
		
		String parameter_name=""; //optional variable to print instructions
		if(parameter==0)
			parameter_name="First Name";
		else if(parameter==1)
			parameter_name="Last Name";
		else
			parameter_name="Email Address";
		
		File sort_file=new File('Path');
		
		//comparator to sort parameters
		Comparator<String> cmp = new Comparator<String>() {
			public int compare(String op1, String op2) {
				return op1.compareTo(op2);
			}
		};
		
		ArrayList<String> records=new ArrayList<String>();//to store records from file
		
		try {
			Scanner dataReader=new Scanner(sort_file);
			while(dataReader.hasNextLine()) {
				String Line=dataReader.nextLine();
				records.add(Line);
			}
			dataReader.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("Something went wrong in sorting!!");
			e.printStackTrace();
		}
		
		String[] records_splitted=new String[records.size()];//to store splitted string
		
		ArrayList<String> sort_parameter=new ArrayList<String>();//to store sorted parameters
		
		ArrayList<String> sorted_records=new ArrayList<String>();//to store sorted records
		
		for(int i=0;i<records.size();i++) {
			records_splitted=records.get(i).split(",");
			sort_parameter.add(records_splitted[parameter]);
		}
		
		//sorting by chosen parameter
		Collections.sort(sort_parameter,cmp);
		
		String temp="";
		
		//Storing sorted records by sorted parameters
		for(int i=0;i<sort_parameter.size();i++) {
			
			for(int j=0;j<records.size();j++) {
				
				temp=records.get(j).split(",")[parameter];
				
				try{
					if(sort_parameter.get(i).equals(temp)) {
						sorted_records.add(records.get(j));
						records.remove(j);
						break;
					}
				}
				catch(Exception e) {
					System.out.println("Error encountered in Sorting");
					return sorted_records;
				}
			}
			
		}
		System.out.println("Records sorted by "+parameter_name);
		for(int i=0;i<sorted_records.size();i++)
			System.out.println(sorted_records.get(i));
		
		return sorted_records;
	}
	
	public static void sortingMain() {
		
		System.out.println("Choose the file where sorting has to be performed!");
		String [] list_of_files=AddOperations.displayAddBooks();
		System.out.print(">");
		int ch_sort_file=sc.nextInt();
		
		if(ch_sort_file>list_of_files.length || ch_sort_file<0) {
			System.out.println("Invalid Choice");
			return;
		}
		
		System.out.print("\nSort by----?");
		System.out.print("\n1.First Name\n2.Last Name\n3.Email Address\n>");
		
		int ch_sort_parameter=sc.nextInt();
		
		ArrayList<String>sorted_records=new ArrayList<String>();
		
		if(ch_sort_parameter==3)
			sorted_records=sortBy(ch_sort_parameter,list_of_files[ch_sort_file-1]);
		else
			sorted_records=sortBy(ch_sort_parameter-1,list_of_files[ch_sort_file-1]);
		
		System.out.print("\nDo you wish to write the changes to the file? (y/n)\n>");
		String ch=sc.next().toLowerCase();
		if(ch.equals("y")) {
			try {
				//removing existing records from file
				new FileOutputStream('Path').close();
				
				//adding sorted records to the file
				FileWriter myWriter = new FileWriter('Path',true);
				for(int i=0;i<sorted_records.size();i++) {
					myWriter.write(sorted_records.get(i).split(",")[0]+","+
							sorted_records.get(i).split(",")[1]+","+
							sorted_records.get(i).split(",")[2]+","+
							sorted_records.get(i).split(",")[3]+","+
							sorted_records.get(i).split(",")[4]+"\n");					
				}
				myWriter.close();
			} 
			catch (Exception e) {
				System.out.println("Something went wrong in Sorting_Writing!!");
				e.printStackTrace();
			}
			
			System.out.print("\nSorted records added to file successfully!");
		}
		else if(ch.equals("n"))
			return;
		else {
			System.out.println("Invalid Choice");
			return;
		}
	}
}