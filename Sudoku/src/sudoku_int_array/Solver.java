/*
 * Java Program to solve the Sudoku problem
 * @author: Tharun Buddigina
 */
package sudoku_int_array;
public class Solver{
	
	//checking validity of placed number
	static boolean checkValidity(int [][] board,int row, int col,int num) {
		//checking for row
		for(int r=0;r<board.length;r++) {
			if(board[row][r]==num)
				return false;
		}
		//checking for column
		for(int c=0;c<board.length;c++) {
			if(board[c][col]==num)
				return false;
		}
		//checking for sub-square matrix
		int x=(int)Math.sqrt(board.length);
		
		int rowStart=row-(row%x); //start row index for sub-square
		int colStart=col-(col%x); //start column index for sub-square
		
		int row_end=rowStart+x; //end row index for sub-square
		int col_end=colStart+x; //end column index for sub-square
		
		for(int j=rowStart;j<row_end;j++) {
			
			for(int k=colStart;k<col_end;k++) {
				if(board[j][k]==num)
					return false;
			}
		}
		return true;
	}
	static boolean Sudokusolve(int [][] board,int n) {
		int row=0,col=0;
		boolean empty=true;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				//Checking empty spaces
				if(board[i][j]==0) {
					row=i;
					col=j;
					
					//breaking loop for column
					empty=false;
					break;
				}	
			}
		//breaking loop of row
		if(!empty)
			break;
		}
		
		//no empty spaces left
		if(empty)
			return true;
		
		for(int num=1;num<=n;num++) {
			if(checkValidity(board, row, col, num)) {
				board[row][col]=num;
				
				if(Sudokusolve(board, n)) {
					return true;
				}
				//backtracking
				else
					board[row][col]=0;
			}
		}
		return false;
	}
}