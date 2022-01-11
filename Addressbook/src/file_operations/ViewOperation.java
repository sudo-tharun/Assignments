package file_operations;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class ViewOperation{
	
	//function to view the existing records
	public static void viewRecords() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Available Addressbooks are: ");
		
		//getting all the available files
		String [] list_of_files=AddOperations.list_of_files;

		System.out.print("Select one from the above\n>");
		int ch_view=sc.nextInt();
		if(ch_view>list_of_files.length || ch_view<1) {
			System.out.println("Invalid Choice!");
			return;
		}
		File view_record=new File("Path"+list_of_files[ch_view-1]);
		System.out.println("\r\nRecords from "+view_record.getName()+":");
		System.out.println("Format: First Name,Last Name,Contact,Email,Address");
		Scanner dataReader;
		try {
			dataReader = new Scanner(view_record);
			int i=0;
			while (dataReader.hasNextLine()) {  
				String Line = dataReader.nextLine();
				System.out.println((i+1)+". "+Line); 
				i++;
			}
			System.out.println("\r\n");
			dataReader.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
