package game;

import game.gameObject.AbstractShape;
import game.gameObject.Background;
import game.gameObject.DetailShape;
import game.gameObject.figures.*;
import main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.stream.Collectors;

public class Game extends JComponent implements Runnable, KeyListener {

    private int speedClone;
    private int speed;
    private int maxSpeed;
    private int time;
    private ArrayList<DetailShape> shapesOnBottom;
    private AbstractShape selectedShape;
    private AbstractShape nextShape;
    private Background background;
    private final CreateShapeFactory createShapeFactory;
    private final JFrame frame;
    private BufferedImage img;
    private Graphics2D g2;
    private Statistic statistic;
    private boolean pause;
    private boolean gameOver;


    public Game(JFrame frame) {
        this.frame = frame;
        createShapeFactory = new CreateShapeFactory();
        shapesOnBottom = new ArrayList<>();
        img = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_INT_RGB);
        g2 = (Graphics2D) img.getGraphics();
        statistic = new Statistic();
        background = new Background(statistic);
        initParameters();
    }

    public void run() {
        frame.addKeyListener(this);
        while (true) {
            if (!pause) {
                change();
                draw();

            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void initParameters() {
        time = 0;
        maxSpeed = 3;
        statistic.initParameters();
        speed = 55 - statistic.getLevel() * 5;
        speedClone = speed;
        shapesOnBottom.clear();
        selectedShape = createShapeFactory.createShape();
        selectedShape.setStartingPosition();
        nextShape = createShapeFactory.createShape();
        gameOver = false;
        pause = false;
    }

    private void draw() {
        background.draw(g2);
        selectedShape.draw(g2);
        nextShape.draw(g2);
        for (DetailShape details : shapesOnBottom) {
            details.draw(g2);
        }
        background.drawStatistic(g2);
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(img, 0, 0, Main.WIDTH, Main.HEIGHT, null);
    }

    private void change() {
        time++;
        if (time < speed) return;
        time = 0;
        if (checkShapesBottom()) {
            nextShape.setStartingPosition();
            shapesOnBottom.addAll(selectedShape.getDetailFigures());
            selectedShape = nextShape;
            nextShape = createShapeFactory.createShape();
            horizontalFillCheck(shapesOnBottom);
        }
        selectedShape.moveVertical(1);
    }

    private void horizontalFillCheck(ArrayList<DetailShape> list) {
        int line = 0;
        int min = list.stream().min(Comparator.comparing(DetailShape::getY)).get().getY();
        if (min <= 0) {
            gameOver = true;
            pause = true;
            background.drawPause(g2);
            repaint();
            if (statistic.getScore() > statistic.getHighScore()) {
                statistic.writeRecordsInFile(statistic.getScore());
            }
            return;
        }
        ArrayList<DetailShape> sortList = list.stream().sorted(Comparator.comparing(DetailShape::getY).reversed()).collect(Collectors.toCollection(ArrayList::new));
        for (int i = Main.countCellHeight - 1; i >= min; i--) {
            int finalI = i;
            ArrayList<DetailShape> filterList = sortList.stream().limit(10).filter(element -> element.getY() == finalI).collect(Collectors.toCollection(ArrayList::new));
            if (filterList.size() == Main.countCellWidth) {
                shapesOnBottom.removeAll(filterList);
                moveOnBottomShapes(i);
                ++i;
                line++;
            }
            sortList.removeAll(filterList);
        }
        if (line > 0) {
            statistic.addLineCount(line);
            speed = 50 - statistic.getLevel() * 5;
            speedClone = speed;
        }
    }

    private void moveOnBottomShapes(int line) {
        for (DetailShape detailShape : shapesOnBottom) {
            if (detailShape.getY() < line) {
                detailShape.moveVertical(1);
            }
        }
    }


    private boolean checkShapesBottom() {
        for (DetailShape detail : selectedShape.getDetailFigures()) {
            if (detail.getY() >= Main.countCellHeight - 1) {
                return true;
            }
        }
        return checkCollisionShape(selectedShape, 0, 1);
    }

    private boolean checkCollisionShape(AbstractShape shape, int deviationX, int deviationY) {
        for (DetailShape detailShape : shape.getDetailFigures()) {
            for (DetailShape shapeOnBottom : shapesOnBottom) {
                if ((detailShape.getY() + deviationY) == shapeOnBottom.getY() && (detailShape.getX() + deviationX) == shapeOnBottom.getX()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkStepLeft() {
        if (checkCollisionShape(selectedShape, -1, 0)) return false;
        for (DetailShape detail : selectedShape.getDetailFigures()) {
            if (detail.getX() < 1) {
                return false;
            }
        }
        return true;
    }

    private boolean checkStepRight() {
        if (checkCollisionShape(selectedShape, 1, 0)) return false;
        for (DetailShape detail : selectedShape.getDetailFigures()) {
            if (detail.getX() > Main.countCellWidth - 2) {
                return false;
            }
        }
        return true;
    }

    private void checkRotate(AbstractShape shape) {
        for (DetailShape detail : shape.getDetailFigures()) {
            if (detail.getX() < 0) {
                shape.stepRight();
            }
            if (detail.getX() > Main.countCellWidth - 1) {
                shape.stepLeft();
            }
            if (detail.getY() > Main.countCellHeight - 2) {
                shape.moveVertical(-1);
            }
        }
    }

    private boolean rotateClone() {
        AbstractShape shape;
        try {
            shape = (AbstractShape) selectedShape.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return false;
        }
        shape.rotate();
        checkRotate(shape);
        return (checkCollisionShape(shape, 0, 0));
    }

    public void keyTyped(KeyEvent keyEvent) {
    }

    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN && !pause) {
            speed = maxSpeed;
        }
    }

    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT && !pause) {
            if (checkStepLeft())
                selectedShape.stepLeft();
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT && !pause) {
            if (checkStepRight())
                selectedShape.stepRight();
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE && !pause) {
            if (!rotateClone()) {
                selectedShape.rotate();
                checkRotate(selectedShape);
            }
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN && !pause) {
            speed = speedClone;
        }
        if (keyEvent.getKeyChar() == 'p' && !gameOver) {
            pause = !pause;
            background.drawPause(g2);
            repaint();
        }
        if (keyEvent.getKeyChar() == 'r' && pause) {
            initParameters();
        }
    }
}
