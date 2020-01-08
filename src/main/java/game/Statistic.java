package game;

import java.io.*;

public class Statistic {
    private int score;
    private int level;
    private int line;
    private int highScore;
    private int maxLvl;

    public Statistic() {

    }

    public void initParameters() {
        this.score = 0;
        this.level = 0;
        this.line = 0;
        this.highScore = 0;
        maxLvl = 10;
        try {
            highScore = getRecordInFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addLineCount(int count) {
        switch (count) {
            case 1:
                addScore(100);
                break;
            case 2:
                addScore(300);
                break;
            case 3:
                addScore(700);
                break;
            case 4:
                addScore(1500);
                break;
            default:
                break;
        }
        line += count;
        if (level < maxLvl)
            level = line / 5;
    }

    public void addScore(int score) {
        this.score += score;
    }


    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public int getLine() {
        return line;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void writeRecordsInFile(int record) {
        BufferedWriter bufferedWriter;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/high_score.txt"));
            bufferedWriter.write(String.valueOf(record));
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private int getRecordInFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/high_score.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            return Integer.valueOf(line);
        }
        return 0;
    }
}
