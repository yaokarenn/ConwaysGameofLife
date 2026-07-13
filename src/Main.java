// karen yao pd 2
import processing.core.PApplet;
import java.util.ArrayList;
public class Main extends PApplet {
    Grid mainWorld;
    boolean isPaused = true;
    public static void main(String[] args) {
        PApplet.main("Main", args);
    }

    public void setup() {
        frameRate(10); // speed
    }

    public void settings() {
        size(1000,600);
        mainWorld = new Grid(500,500,20,20); // grid size
    }
    public void draw(){
        background(255,255,255);
        mainWorld.displayGrid(this);
        //continues only if not paused
        if (!isPaused) {
            mainWorld.iterate();
        }
        //displays
        fill(0,0,0);
        text("press 'p' to pause", 700,100);
        text("press 'r' to randomize", 700, 150);
    }
    public void keyPressed() {
        if (key == 'p') {
            isPaused = !isPaused;
        }
        if (key == 'r'){
            mainWorld.randomize();
        }

    }
    public void mouseReleased(){
        for (ArrayList<Cell> thisRow: mainWorld.gridWorld){
            for (Cell thisCell: thisRow){
                if (thisCell.isOverButton(this)){
                    thisCell.isAlive = !thisCell.isAlive;
                }
            }
        }
    }
}