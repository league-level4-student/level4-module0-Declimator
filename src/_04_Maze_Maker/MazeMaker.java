package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int rows;
	private static int cols;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int r, int c) {
		System.out.println("test");
		rows = r;
		cols = c;
		maze = new Maze(rows, cols);
		int wall = new Random().nextInt(4);
		int z = new Random().nextInt(5);
		int z2 = new Random().nextInt(5);
		if(wall == 0) {
		maze.getCell(z, 0).setWestWall(false);
		maze.getCell(z2, 4).setEastWall(false);
		} else if(wall == 1) {
			maze.getCell(0, z).setNorthWall(false);
			maze.getCell(4, z2).setSouthWall(false);
		} else if(wall == 2) {
			maze.getCell(z, 4).setEastWall(false);
			maze.getCell(z2, 0).setWestWall(false);
		}else {
			maze.getCell(4, z).setSouthWall(false);
			maze.getCell(0, z2).setNorthWall(false);
		}
		// 1. select a random cell to start
		int x = new Random().nextInt(r);
		int y = new Random().nextInt(c);
		// 2. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.getCell(x, y));

		return maze;
	}

	// 3. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		System.out.println("path");
		// A. mark cell as visited
		currentCell.setBeenVisited(true);
		// B. check for unvisited neighbors using the cell
		ArrayList<Cell> neighbors = getUnvisitedNeighbors(currentCell);
		if (neighbors.size() > 0) {
			// C. if has unvisited neighbors,
			// C1. select one at random.
			int d = new Random().nextInt(neighbors.size());
			uncheckedCells.push(neighbors.get(d));
			// C2. push it to the stack

			// C3. remove the wall between the two cells
			removeWalls(currentCell, neighbors.get(d));
			// C4. make the new cell the current cell and mark it as visited
			currentCell = neighbors.get(d);
			currentCell.setBeenVisited(true);
			// C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		// D. if all neighbors are visited
		else {
			// D1. if the stack is not empty
			if (!uncheckedCells.isEmpty()) {
				// D1a. pop a cell from the stack
				currentCell = uncheckedCells.pop();
				// D1b. make that the current cell

				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}
	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (c1.getCol() == c2.getCol()) {
			if (c1.getRow() == c2.getRow() + 1) {
				c2.setSouthWall(false);
				c1.setNorthWall(false);
			} else if (c1.getRow() == c2.getRow() - 1) {
				c2.setNorthWall(false);
				c1.setSouthWall(false);
			}
		} else if (c1.getRow() == c2.getRow()) {
			if (c1.getCol() == c2.getCol() - 1) {
				c1.setEastWall(false);
				c2.setWestWall(false);
			} else if (c1.getCol() == c2.getCol() + 1) {
				c1.setWestWall(false);
				c2.setEastWall(false);
			}
		}
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> unvisited = new ArrayList<Cell>();
		if(c.getCol() > 0 && !maze.getCell(c.getRow(), c.getCol()-1).hasBeenVisited()) {
			unvisited.add(maze.getCell(c.getRow(), c.getCol()-1));
		} 
		if(c.getCol() < 4 && !maze.getCell(c.getRow(), c.getCol()+1).hasBeenVisited()) {
			unvisited.add(maze.getCell(c.getRow(), c.getCol()+1));
		} 
		if(c.getRow() > 0 && !maze.getCell(c.getRow()-1, c.getCol()).hasBeenVisited()) {
			unvisited.add(maze.getCell(c.getRow()-1, c.getCol()));
		} 
		if(c.getRow() < 4 && !maze.getCell(c.getRow()+1, c.getCol()).hasBeenVisited()) {
			unvisited.add(maze.getCell(c.getRow()+1, c.getCol()));
		} 
		return unvisited;
	}
}
