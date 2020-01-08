package game.gameObject.figures;

import game.gameObject.AbstractShape;
import game.gameObject.DetailShape;

import java.awt.*;
import java.util.ArrayList;

public class OrangeRicky extends AbstractShape {

    public OrangeRicky() {
        super();
        color = Color.ORANGE;
        detailFigures.add(new DetailShape(x, y, 0, 0, color));
        detailFigures.add(new DetailShape(x, y, 1, 0, color));
        detailFigures.add(new DetailShape(x, y, -1, 0, color));
        detailFigures.add(new DetailShape(x, y, 1, -1, color));
    }


}
