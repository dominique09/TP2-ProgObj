package ca.csf.minesweeper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Minesweeper {
	private Cell[][] cellArray;
	private int sizeX;
	private int sizeY;
	private int nbMines;
	private int[] minesPositions;
	private boolean playerIsDead;
	
	public static enum Difficulty {
		EASY(10, 9, 9), MEDIUM(40, 16, 16), HARD(99, 30, 16);

		private int nbMines;
		private int sizeX;
		private int sizeY;

		private Difficulty(int nbMines, int sizeX, int sizeY) {
			this.nbMines = nbMines;
			this.sizeX = sizeX;
			this.sizeY = sizeY;
		}

		public int getNbMines() {
			return this.nbMines;
		}

		public int getSizeX() {
			return this.sizeX;
		}

		public int getSizeY() {
			return this.sizeY;
		}
	}

	public void newGame(Difficulty difficulty) {
		newGame(difficulty.getNbMines(), difficulty.getSizeX(), difficulty.getSizeY());
	}

	public void newGame(int nbMines, int sizeX, int sizeY) {
		
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.nbMines = nbMines;
		
		playerIsDead = false;
		
		cellArray = new Cell[sizeX][sizeY];
	// Generate random mines positions
		ArrayList<Integer> randomNumbers = new ArrayList<Integer>(sizeX*sizeY);
		for (int i = 0; i < sizeX*sizeY; ++i){
			randomNumbers.add(i, i);
		}
		Collections.shuffle(randomNumbers);
		
		// Push the first mines in minesPositions
		
		minesPositions = new int[nbMines];
		
		for (int i = 0; i < nbMines; ++i){
			minesPositions[i] = (int) randomNumbers.get(i);
		}
		initializeCellArray();
	}
	
	void initializeCellArray() {
		for (int i = 0; i < cellArray.length; i++){
			for (int j = 0; j < cellArray[i].length; ++j){
				cellArray[i][j] = new Cell(Cell.CellType.EMPTY, true);
			}
        }
		
		for (int element : minesPositions) {
			(this.cellArray[(element % sizeX)][(element / sizeY)]).type = Cell.CellType.MINE;
		}
		
	}
	
	public void activate(int coordX, int coordY){
		
		if (cellArray[coordX-1][coordY-1].type == Cell.CellType.MINE){
			playerIsDead = true;
			
			// Show all mines
			
			for (Cell[] row : cellArray){
				for (Cell cell : row){
					if (cell.type == Cell.CellType.MINE){
						cell.isHidden = false;
					}
				}
			}
		}
		
		
	}
	
	public void displayCellArray(){
		for (Cell[] row : cellArray){
			for (Cell element : row){
				switch (element.type.toString()){
				case "MINE":
					System.out.print("*");
					break;
				case "EMPTY":
					System.out.print("-");
					break;
				}
			}
			System.out.println();
		}
		System.out.println("------- END ------");
	}
}
