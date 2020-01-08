package game.gameObject;

import main.Main;

import java.awt.*;


public class DetailShape implements Cloneable {
    private int x;
    private int y;
    private int differenceX, differenceY;
    private Color color;

    public DetailShape(int x, int y, int differenceX, int differenceY, Color color) {
        this.x = x + differenceX;
        this.y = y + differenceY;
        this.differenceX = differenceX;
        this.differenceY = differenceY;
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x * Main.sizeCell + Main.indent, (y * Main.sizeCell), Main.sizeCell, Main.sizeCell);
        g.setColor(Color.black);
        g.drawRect(x * Main.sizeCell + Main.indent, (y * Main.sizeCell), Main.sizeCell, Main.sizeCell);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void rotateRight() {
        int difference = differenceX;
        differenceX = -1 * differenceY;
        differenceY = difference;
    }

//    public void rotateLeft() {
//        int difference = differenceX;
//        differenceX = differenceY;
//        differenceY = -1 * difference;
//    }

    public void moveVertical(int step) {
        y += step;
    }

    public void update(int x, int y) {
        this.x = x + differenceX;
        this.y = y + differenceY;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public DetailShape clone() throws CloneNotSupportedException{
        DetailShape detailShape = (DetailShape) super.clone();
        detailShape.setColor(Color.BLACK);
        return detailShape;
    }
}
