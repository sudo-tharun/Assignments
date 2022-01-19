package file_operations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Search{
	
	static File get_AllRecords=new File('Path');
	
	static String [] list_of_files=get_AllRecords.list();
	
	static Scanner sc=new Scanner(System.in);
	
	@SuppressWarnings("resource")
	//function to get the records from all addressbooks
	public static HashMap<String, String> getAllRecords() {
		
		HashMap<String,String> records=new HashMap<String,String>();//to store records in address books
		
		for(int i=0;i<list_of_files.length;i++) {
			int j=1;
			File temp=new File('Path');
			
			try {
				Scanner dataReader=new Scanner(temp);
				while(dataReader.hasNextLine()) {
					String Line = dataReader.nextLine();
					records.put(list_of_files[i]+"_"+j, Line);
					j++;	
				}	
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} 
		
		return records;
	}
	
	public static HashMap<String, String> searchParameters(int key) {
		
		//key is the parameter by which searching is performed
		
		System.out.print("\nEnter search String: \n>");
		
		String ch_search=sc.next();
		
		ch_search=ch_search.toLowerCase();
		
		System.out.print("\nFetching records---->\n");
		
		
		HashMap<String,String> search=new HashMap<String,String>(); //to store all the available records
		
		HashMap<String,String> results=new HashMap<String,String>();//to store the search results
		
		search=getAllRecords();
		
		String[] records=new String[search.size()];
	
		int c=1;
		
		//printing matched search results
		for(HashMap.Entry<String, String> i : search.entrySet()) {
			records=i.getValue().split(",");
			if(records[key-1].contains(ch_search)){
				System.out.println(c+". "+i.getValue()+" -- from -->"+i.getKey().split("_")[0]);
				results.put(i.getKey(),i.getValue());
				c++;
			}
		}
		
		if(results.size()==0) {
			System.out.println("No related results found!!");
			System.out.print("Would you like to try searching again? (y/n)\n>");
			
			String ch=sc.next();
			
			if(ch.equals("y"))
				search();
			else if(ch.equals("n"))
				return results;
			else {
				System.out.println("Invalid Choice");
			}
		}

		
		return results;
	}
	
	public static void search() {
		System.out.println("Search is performed on the whole database!!");
		
		System.out.print("Search by---> ");
		System.out.println("\n1.First Name\n2.Last Name\n3.Contact Number\n4.Email Address\n");
		System.out.print("Enter -1 to return to main menu\n>");
		
		int ch_search_by=sc.nextInt();
		
		HashMap<String,String> results=new HashMap<String,String>();
		if(ch_search_by==-1)
			return;
		
		results=searchParameters(ch_search_by);
		
		if(results.size()==0)
			return;
		
		Object [] keySet=results.keySet().toArray();
		String [] valueSet=new String[results.size()];
		String [] temp=new String[results.size()];
		String tem="";
		
		//function to get records string as a whole from results 
		for(int i=0;i<results.size();i++) {
			temp=((String)(results.values()).toArray()[i]).split(",");
			for(int j=0;j<temp.length;j++) {
				if(j==temp.length-1) {
					tem=tem+temp[j];
					break;
				}
				tem=tem+temp[j]+",";
			}
			valueSet[i]=tem;
		}
		
		System.out.println("\nWhat operation would you like to perform further? \n");
		System.out.print("1.Delete record\n2.Return to main menu\n>");

		int ch_further=sc.nextInt();
		
		if(ch_further==2)
			return;
		
		System.out.println("Select a record from the search results!!\n>");

		int ch_record_select=sc.nextInt();
		
		if(ch_record_select>results.size() || ch_record_select<1) {
			System.out.println("Invalid Choice!!");
			return;
		}

		if(ch_further==1) {
			//deleting matched search
			DeleteOperations.deleteRecord(((String)keySet[ch_record_select-1]).split("_")[0], ch_record_select,valueSet);
			System.out.println("Deletion Successful!");
			return;
		}
	}
}