import processing.core.PApplet;
public class Cell extends Button{
    public boolean isAlive;
    public int rowIndex, colIndex;
    public int cellAge = 0;

    public Cell() {}
    public Cell(int xPos, int yPos, int wSize, int hSize, int rowIndex, int colIndex) {
        super(xPos, yPos, wSize, hSize);
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        isAlive = false;
    }

    public void display(PApplet pa){
        if (isAlive) {
            // turns blue if cell is older
            pa.fill(0, 0, cellAge + 50);
            cellAge++;
        } else {
            pa.fill(255,255,255);
        }
        pa.stroke(0,0,0);
        pa.rect(xPos, yPos, wSize, hSize);
    }
}