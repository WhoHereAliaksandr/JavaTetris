package game.gameObject;

import game.Statistic;
import game.gameObject.interfaces.Drawing;
import main.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background implements Drawing {

    static {
        try {
            image = ImageIO.read(new File("src/main/resources/background.jpg"));
            pause = ImageIO.read(new File("src/main/resources/pause.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage image;
    private static BufferedImage pause;
    private Statistic statistic;

    public Background(Statistic statistic) {
        this.statistic = statistic;
    }

    public void draw(Graphics g) {
        g.drawImage(image, 0, 0, Main.WIDTH, Main.HEIGHT, null);
    }

    public void drawStatistic(Graphics g) {
        g.setColor(new Color(0xF8FFFD));
        Graphics2D g2 = (Graphics2D) g;
        Stroke oldStroke = g2.getStroke();
        g2.setStroke(new BasicStroke(5, BasicStroke.JOIN_ROUND, BasicStroke.CAP_SQUARE));
        g.drawRect(-3 + Main.indent, 0, Main.WIDTH - Main.sizeCell * 6 + 6, Main.HEIGHT - 42);
        g2.setStroke(oldStroke);
        g2.setFont(new Font("Serif", Font.PLAIN, Main.sizeCell));

        g2.drawString("Score", Main.WIDTH - getStringWidth(g, "Score"), Main.countCellHeight * Main.sizeCell / 14);
        g2.drawString("Level", Main.WIDTH - getStringWidth(g, "Level"), Main.countCellHeight * Main.sizeCell / 4);
        g2.drawString("Line", Main.WIDTH - getStringWidth(g, "Line"), Main.countCellHeight * 3 * Main.sizeCell / 7);
        g2.drawString("Next", Main.countCellWidth * Main.sizeCell + Main.sizeCell * 2, Main.countCellHeight * Main.sizeCell * 13 / 17);


        String score = String.valueOf(statistic.getScore());
        String level = String.valueOf(statistic.getLevel());
        String line = String.valueOf(statistic.getLine());
        String highScore = String.valueOf(statistic.getHighScore());
        g2.setFont(new Font("Serif", Font.PLAIN, 3 * Main.sizeCell / 4));
        g2.drawString(score, Main.WIDTH - getStringWidth(g, score), Main.countCellHeight * Main.sizeCell / 8);
        g2.drawString(level, Main.WIDTH - getStringWidth(g, level), Main.countCellHeight * 4 * Main.sizeCell / 13);
        g2.drawString(line, Main.WIDTH - getStringWidth(g, line), Main.countCellHeight * Main.sizeCell / 2 - Main.sizeCell / 3);
        g.setColor(new Color(0xF5FF7F));
        g2.drawString("High Score", Main.WIDTH - getStringWidth(g, "High Score") + Main.sizeCell / 4, Main.countCellHeight * 5 * Main.sizeCell / 9);
        g2.drawString(highScore, Main.WIDTH - getStringWidth(g, highScore), Main.countCellHeight * 7 * Main.sizeCell / 11);
    }

    public void drawPause(Graphics2D g) {
        g.drawImage(pause, Main.WIDTH / 2 - Main.sizeCell, Main.HEIGHT / 2 - Main.sizeCell * 3, Main.sizeCell * 2, Main.sizeCell * 2, null);
    }

    private int getStringWidth(Graphics g, String text) {
        int indent = Main.sizeCell / 4;
        return g.getFontMetrics().stringWidth(text) + indent;
    }
}
