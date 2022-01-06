/*
 * Java Program to solve the Sudoku problem
 * @author: Tharun Buddigina
 */
package sudoku_int_array;
import java.util.HashSet;
import java.util.Scanner;
class SudokuSolver{
	static Scanner sc=new Scanner(System.in);
	int[][] hard_code = {
			{ 8, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 3, 6, 0, 0, 0, 0, 0 },
			{ 0, 7, 0, 0, 9, 0, 2, 0, 0 },
			{ 0, 5, 0, 0, 0, 7, 0, 0, 0 },
			{ 0, 0, 0, 0, 4, 5, 7, 0, 0 },
			{ 0, 0, 0, 1, 0, 0, 0, 3, 0 },
			{ 0, 0, 1, 0, 0, 0, 0, 6, 8 },
			{ 0, 0, 8, 5, 0, 0, 0, 1, 0 },
			{ 0, 9, 0, 0, 0, 0, 4, 0, 0 } 
	};
	
	//Validating User Input
    private boolean isValidSudoku(int[][] board) {
        
        for(int i=0; i < 9; i++){
            HashSet<Integer> row = new HashSet();
            HashSet<Integer> col = new HashSet();
            HashSet<Integer> Box = new HashSet();

            for(int j=0; j < 9; j++){
                if(board[i][j] != 0 && !row.add(board[i][j]))
                    return false;
                if(board[j][i] != 0 &&!col.add(board[j][i]))
                    return false;
                int box_row = 3 * (i/3) + j/3;
                int box_col = 3 * (i%3) + j%3;
                if(board[box_row][box_col] != 0 && !Box.add(board[box_row][box_col]))
                    return false;
            }
        }
        
        return true;
    } 
    
	private int choiceofTesting() {
		System.out.println("How do you wish to test the code?");
		System.out.println("1. Manual Input	2. Program Default");
		int c=sc.nextInt();
		if(c==1)
			return 1;
		else if(c==2)
			return 2;
		else {
			System.out.println("Invalid Input! Try again!");
			choiceofTesting();
		}
		return 2;
	}
	
	private int[][]manualInput(){
		System.out.println("Enter '0' to indicate empty spaces!");
		int[][] board= new int[9][9];
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				System.out.println("You are currently at row="+(i+1)+" and column="+(j+1));
				board[i][j]=sc.nextInt();
				if(board[i][j]>9) {
					System.out.println("The entered value is >9. Enter again!");
					board[i][j]=sc.nextInt();
				}
			}
		}
		return board;
	}
	//function to print board
	private void print(int [][]input){
        if(input == null) {
            System.out.println("\n Invalid input.");
            return;
        }
        for(int i = 0, k = 1; i < 9; i++, k++){
            for(int j=0; j < 9; j++){
                if((j%3 == 0)){
                    System.out.print(" | " + input[i][j]);
                }
                else
                    System.out.print(" " + input[i][j]);

                if(j == 8)
                    System.out.print(" |");
            }
            if((k%3) == 0) System.out.print("\n -------------------------");
            System.out.println();
        }
    }
	
	public void main_method() {
		
		int [][] board = new int[9][9];
		int user_choice=choiceofTesting();
		System.out.println("For hard code: "+isValidSudoku(hard_code));
		switch(user_choice) {
		case 1:{
			board=manualInput();
			if(!isValidSudoku(board)) {
				System.out.println("Invalid Input!\nProgram Terminated!");
				return;	
			}
			System.out.println("Validity Check Passed!");
			System.out.println("Provided board:");
			print(board);
			break;
			}
		case 2:{
			board=hard_code;
			System.out.println("System board:");
			print(board);
			break;
			}
		}
		//zero indicates empty space
		int size=board.length;
		
		//Checking for time taken to execute the program
		long startTime = System.currentTimeMillis();
		if(Solver.Sudokusolve(board,size)) {
			System.out.println("Solution: ");
			print(board);
		}
		else
			System.out.println("Solution doesn't exist!");
	    long endTime = System.currentTimeMillis();
	    System.out.println("Implementation took " + (double)(endTime - startTime) + " ms");

	}
}

public class Sudoku{
	public static void main(String [] args) {
		SudokuSolver obj=new SudokuSolver();
		obj.main_method();
	}
}