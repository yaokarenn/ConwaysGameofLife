import processing.core.PApplet;
import java.util.ArrayList;
public class Grid extends Cell {
    public int hPixelSize; // pixel dimension
    public int vPixelSize;
    public int hCellCount; // cell count
    public int vCellCount;
    public int startingPointRow = 40; // starting pixels
    public int startingPointCol = 40;
    public ArrayList<ArrayList<Cell>>gridWorld;
    public int cellSize;

    public Grid() {}
    public Grid(int hPixelSize, int vPixelSize, int hCellCount, int vCellCount) {
        gridWorld = new ArrayList<ArrayList<Cell>>();
        this.hPixelSize = hPixelSize;
        this.vPixelSize = vPixelSize;
        this.hCellCount = hCellCount;
        this.vCellCount = vCellCount;
        cellSize = Math.min(hPixelSize / hCellCount, vPixelSize / vCellCount);
        initializeGrid();
    }

    public void initializeGrid(){
        for (int r = 0; r < vCellCount; r++) {
            ArrayList<Cell> thisRow = new ArrayList<Cell>();
            for (int c = 0; c < hCellCount; c++) {
                thisRow.add(new Cell(startingPointCol + c * cellSize, startingPointRow + r * cellSize, cellSize, cellSize, r, c));
            }
            gridWorld.add(thisRow);
        }

    }

    public void displayGrid(PApplet pa){
        for (ArrayList<Cell> row : gridWorld) {
            for (Cell cell : row) {
                cell.display(pa);
            }
        }

    }
    public int countNeighbors(int r, int c){ // count the live neighbors, 8 spots around
        int count = 0;
        int [] dR = {0,-1,-1,-1,0,1,1,1};
        int [] dC = {1,1,0,-1,-1,-1,0,1};
        int targetR = 0, targetC = 0;
        for (int i = 0; i < 8; i++){
            targetR = r + dR[i];
            targetC = c + dC[i];
            if (targetR < 0){
                targetR = vCellCount-1;
            } else if (targetR > vCellCount-1){
                targetR = 0;
            }
            if (targetC < 0){
                targetC = hCellCount-1;
            } else if (targetC > hCellCount-1){
                targetC = 0;
            }
            if (gridWorld.get(targetR).get(targetC).isAlive){
                count++;
            }
        }
        return count;
    }

    public void iterate () {
        // temp grid as next generation
        ArrayList<ArrayList<Cell>> tempGrid = new ArrayList<ArrayList<Cell>>();
        // initialize every cell in temp grid with new cell
        for (int r = 0; r < vCellCount; r++) {
            ArrayList<Cell> tempRow = new ArrayList<Cell>();
            for (int c = 0; c < hCellCount; c++) {
                Cell newCell = new Cell(startingPointCol + c * cellSize, startingPointRow + r * cellSize, cellSize, cellSize, r, c);
                if (gridWorld.get(r).get(c).isAlive) {
                    if (countNeighbors(r, c) < 2 || countNeighbors(r, c) > 3) {
                        newCell.isAlive = false;
                    } else {
                        newCell.isAlive = true;
                    }
                } else {
                    if (countNeighbors(r, c) == 3) {
                        newCell.isAlive = true;
                    }
                }
                tempRow.add(newCell);
            }
            tempGrid.add(tempRow);
        }
        gridWorld=tempGrid;
    }

    public void randomize() {
        for (ArrayList<Cell> thisRow : gridWorld) {
            for (Cell thisCell : thisRow) {
                thisCell.isAlive = Math.random() < 0.5;
            }
        }
    }

}