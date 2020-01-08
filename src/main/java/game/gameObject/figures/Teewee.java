package game.gameObject.figures;

import game.gameObject.AbstractShape;
import game.gameObject.DetailShape;

import java.awt.*;

public class Teewee extends AbstractShape {
    public Teewee() {
        super();
        color = new Color(0xFF00DC);
        detailFigures.add(new DetailShape(x, y, 0, 0, color));
        detailFigures.add(new DetailShape(x, y, -1, 0, color));
        detailFigures.add(new DetailShape(x, y, 1, 0, color));
        detailFigures.add(new DetailShape(x, y, 0, -1, color));
    }

}
