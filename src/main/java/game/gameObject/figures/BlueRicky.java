package game.gameObject.figures;

import game.gameObject.AbstractShape;
import game.gameObject.DetailShape;

import java.awt.*;
import java.util.ArrayList;

public class BlueRicky extends AbstractShape {

    public BlueRicky() {
        super();
        color = new Color(0x31F3FF);
        detailFigures.add(new DetailShape(x, y, 0, 0, color));
        detailFigures.add(new DetailShape(x, y, 1, 0, color));
        detailFigures.add(new DetailShape(x, y, -1, 0, color));
        detailFigures.add(new DetailShape(x, y, -1, -1, color));
    }

}
