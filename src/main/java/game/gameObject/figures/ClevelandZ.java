package game.gameObject.figures;

import game.gameObject.AbstractShape;
import game.gameObject.DetailShape;

import java.awt.*;
import java.util.ArrayList;

public class ClevelandZ extends AbstractShape {
    public ClevelandZ() {
        super();
        color = Color.red;
        detailFigures.add(new DetailShape(x, y, 0, 0, color));
        detailFigures.add(new DetailShape(x, y, -1, 0, color));
        detailFigures.add(new DetailShape(x, y, 0, 1, color));
        detailFigures.add(new DetailShape(x, y, 1, 1, color));
    }

}
