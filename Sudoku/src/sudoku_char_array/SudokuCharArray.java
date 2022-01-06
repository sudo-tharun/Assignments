/*
 * Java Program to solve the Sudoku problem
 * @author: Tharun Buddigina
 */
package sudoku_char_array;

class Solver_char_array {
    char[][] input={{'.', '2', '6', '5', '.', '.', '.', '9', '.'},
            {'5', '.', '.', '.', '7', '9', '.', '.', '4'},
            {'3', '.', '.', '.', '1', '.', '.', '.', '.'},
            {'6', '.', '.', '.', '.', '.', '8', '.', '7'},
            {'.', '7', '5', '.', '2', '.', '.', '1', '.'},
            {'.', '1', '.', '.', '.', '.', '4', '.', '.'},
            {'.', '.', '.', '3', '.', '8', '9', '.', '2'},
            {'7', '.', '.', '.', '6', '.', '.', '4', '.'},
            {'.', '3', '.', '2', '.', '.', '1', '.', '.'}};
    static void print(char [][]input){
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
    private boolean Solve(){
        int row, col;
        row = col = -1;
        boolean isEmpty = true;
        
        for(int i=0; i < 9; i++){
            for(int j=0; j < 9; j++){
                if(this.input[i][j] == '.'){
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if(!isEmpty)
                break;
        }
        
        if(isEmpty) return true;
        
        for(int i=1; i <= 9; i++){
            if(helperSolve(row, col, i)){
                this.input[row][col] = (char) (i + '0');
                if(Solve())
                    return true;
                else
                    this.input[row][col] = '.';
            }
        }
        
        return false;
    }
    
    private boolean helperSolve(int row, int col, int num){
        
    	//checking row
        char n = (char) (num + '0');
        for(int i=0; i < 9; i++)
            if(this.input[row][i] == n) return false;
        
        // checking column
        for(int i=0; i < 9; i++)
            if(this.input[i][col] == n) return false;
        
        // check sub-squares
        int startCol = col - (col % 3);
        int startRow = row - (row % 3);
        
        int endCol = startCol + 3;
        int endRow = startRow + 3;
        
        for(int i = startRow; i < endRow; i++){
            for(int j = startCol ; j < endCol; j++){
                if(this.input[i][j] == n) return false;
            }
        }
            
        return true;
    }
    public void solveSudoku() {
    	System.out.println("Initial Character Array: ");
    	print(this.input);
    	Solve();
    	System.out.println("Solved Character Array: ");
    	print(this.input);
    	// 2 helper function
    }
}
class SudokuCharArray{
	
	public static void main(String [] args) {
		Solver_char_array obj=new Solver_char_array();
		obj.solveSudoku();
	}
}
