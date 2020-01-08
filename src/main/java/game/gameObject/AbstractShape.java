package game.gameObject;

import game.gameObject.interfaces.Drawing;
import main.Main;

import java.awt.*;
import java.util.ArrayList;

public abstract class AbstractShape implements Drawing, Cloneable {

    protected int x;
    protected int y;
    protected ArrayList<DetailShape> detailFigures;
    protected Color color;

    public AbstractShape() {
        detailFigures = new ArrayList<>(4);
        this.x = Main.countCellWidth + 3;
        this.y = 7 * Main.countCellHeight / 8;
    }

    public void setStartingPosition() {
        x = 5;
        y = -1;
        notifyObservers();
    }

    public void draw(Graphics g) {
        for (DetailShape detailShape : detailFigures) {
            detailShape.draw(g);
        }
    }

    public void moveVertical(double step) {
        y += step;
        notifyObservers();
    }

    public void stepLeft() {
        x--;
        notifyObservers();
    }

    public void stepRight() {
        x++;
        notifyObservers();
    }

    public void rotate() {
        for (DetailShape detailShape : detailFigures) {
            detailShape.rotateRight();
            detailShape.update(x, y);
        }
    }

    public void notifyObservers() {
        for (DetailShape detailShape : detailFigures) {
            detailShape.update(x, y);
        }
    }

    public ArrayList<DetailShape> getDetailFigures() {
        return detailFigures;
    }

    public void setDetailFigures(ArrayList<DetailShape> detailFigures) {
        this.detailFigures = detailFigures;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        AbstractShape shape = (AbstractShape) super.clone();
        ArrayList<DetailShape> cloneDetails = new ArrayList<>();
        for (DetailShape detailShape : detailFigures) {
            cloneDetails.add(detailShape.clone());
        }
        shape.setDetailFigures(cloneDetails);
        return shape;
    }
}

