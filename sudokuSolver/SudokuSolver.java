package sudokuSolver;
import java.util.*;

public class SudokuSolver {
	
	private int[][] grid;
	
	public SudokuSolver(int[][] initialGrid) {
	    this.grid = initialGrid;
	}
	public SudokuSolver() {
	    this.grid = new int[9][9]; // Creates a 9x9 grid filled with 0s
	}
	
	public boolean rowPlaced(int n, int[] cell) {
		for(int i=0; i<9; i++) {
			if(grid[cell[0]][i] == n) {
				return false;
			}
		}
		return true;
	}
	public boolean columnPlaced(int n, int[] cell) {
		for(int i=0; i<9; i++) {
			if(grid[i][cell[1]] == n) {
				return false;
			}
		}
		return true;
	}
	public boolean boxPlaced(int n, int[] cell) {
		for(int i=((cell[0]/3)*3); i<(((cell[0]/3) + 1)*3); i++) {
			for(int j=((cell[1]/3)*3); j<(((cell[1]/3) + 1)*3); j++) {
				if(grid[i][j] == n) {
					return false;
				}
			}
		}
		return true;
	}
	public boolean isValidPlacement(int n, int[] cell) {
		return rowPlaced(n, cell) && columnPlaced(n, cell) && boxPlaced(n, cell);
	}

	public boolean placing() {
		int[] pos = emptyCell();
		if (emptyCell()[0] == -1 && emptyCell()[1] == -1) {
			 return true;
			 }
		else {
			for (int k = 1; k <= 9; k++) {
				if(isValidPlacement(k, new int[] {pos[0], pos[1]})) {
					grid[pos[0]][pos[1]] = k;
					if(placing()) {
						return true;
					}
					grid[pos[0]][pos[1]] = 0;
					}
				}
			return false;
		}
	}
	public boolean solve() {
        return placing();
    }
	public int[] emptyCell() {
		int[] pos = {-1, -1};
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (grid[i][j] == 0) {
					pos[0] = i;
					pos[1] = j;
					return pos;
				}
			}
		}
		return pos;
	}
	
	public void show() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(grid[i][j] + " ");
				if(j == 2 || j == 5) {
					System.out.print("| ");
				}
			}
			System.out.println();
			if(i == 2 || i == 5) {
				System.out.println("----------------------");
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner console = new Scanner(System.in);
		int[][] puzzle = { 
				  {0,0,0,0,0,0,1,0,2}, 
				  {0,2,0,1,0,3,0,0,9}, 
				  {0,0,0,0,0,0,0,4,0},
				  {0,0,2,0,0,0,5,0,0},
				  {4,0,0,0,0,8,0,0,0},
				  {0,1,9,0,0,0,0,3,6},
				  {5,0,0,0,0,0,6,0,7},
				  {0,9,0,0,0,4,2,0,0},
				  {0,0,1,6,0,9,0,0,0},
				};
		
		SudokuSolver sudoku = new SudokuSolver(puzzle);
		
		while(true) {
			System.out.println("Enter the cell you want to place in");
			System.out.println("Enter row (0-8): ");
			int i = console.nextInt();
			if(!(i>=0 && i<9)) {
				System.out.println("wrong input");
				continue;
			}
			System.out.println("Enter column (0-8): ");
			int j = console.nextInt();
			if(!(j>=0 && j<9)) {
				System.out.println("wrong input");
				continue;
			}
			System.out.println("Enter number (1-9): ");
			int n = console.nextInt();
			if(!(n>=1 && n<=9)) {
				System.out.println("wrong input");
				continue;
			}
			console.nextLine();
			if(sudoku.isValidPlacement(n, new int[] {i, j}) && puzzle[i][j] == 0) {
				puzzle[i][j] = n;
				sudoku.show();
			}
			else {
				System.out.println("the number " + n + " can't be place at the given positin");
				continue;
			}
			System.out.println("do you want to place again (y/n): ");
			String placeAgain = console.nextLine();
			if(!(placeAgain.equals("y") || placeAgain.equals("n"))) {
				System.out.println("wrong input");
				continue;
			}
			if(placeAgain.equals("n")) {
				break;
			}
			else if(placeAgain.equals("y")) {
				continue;
			}
			
		}
		
		sudoku = new SudokuSolver(puzzle);
		
		if (sudoku.solve()) {
			sudoku.show();
        } else {
            System.out.println("No solution exists.");
        }

	}

}
