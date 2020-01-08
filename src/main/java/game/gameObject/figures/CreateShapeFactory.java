package game.gameObject.figures;

import game.gameObject.AbstractShape;

import java.util.Random;

public class CreateShapeFactory {
    private final Random random = new Random();
    private int lastShape = 0;

    public CreateShapeFactory() {
    }

    public AbstractShape createShape() {
        int rand = getRandom();
        switch (rand) {
            case 1:
                return new SmashBoy();
            case 2:
                return new Hero();
            case 3:
                return new OrangeRicky();
            case 4:
                return new BlueRicky();
            case 5:
                return new ClevelandZ();
            case 6:
                return new RhodeIslandZ();
            case 7:
                return new Teewee();
        }
        return null;
    }

    public int getRandom() {
        int rand = random.nextInt(7) + 1;
        if (rand == lastShape) {
            rand = getRandom();
        }
        lastShape = rand;
        return rand;
    }

}
