package game.gameObject.figures;

import game.gameObject.AbstractShape;
import game.gameObject.DetailShape;

import java.awt.*;
import java.util.ArrayList;

public class Hero extends AbstractShape {

    public Hero() {
        super();
        color = Color.BLUE;
        detailFigures.add(new DetailShape(x, y, 0, 0, color));
        detailFigures.add(new DetailShape(x, y, 1, 0, color));
        detailFigures.add(new DetailShape(x, y, -1, 0, color));
        detailFigures.add(new DetailShape(x, y, -2, 0, color));
    }


}
