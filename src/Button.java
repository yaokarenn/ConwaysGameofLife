import processing.core.PApplet;
public class Button {
    public int wSize; // width of button
    public int hSize; // height of button
    public int xPos; // x position of button on the window
    public int yPos; // y position of button on the window
    public Button() {
    }
    public Button(int xPos, int yPos, int wSize, int hSize) {
        this.wSize = wSize;
        this.hSize = hSize;
        this.xPos = xPos;
        this.yPos = yPos;
    }
    public boolean isOverButton(PApplet pa){
        if (pa.mouseX <= xPos + wSize &&
                pa.mouseX >= xPos  &&
                pa.mouseY <= yPos + hSize &&
                pa.mouseY >= yPos ){
            return true;
        }
        return false;
    }
    public void display(PApplet pa){
        pa.rectMode(pa.CENTER);
        pa.rect(xPos,yPos,wSize,hSize);
    }
}




