import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class HighScoresManager {
    private static List<Score> highScores = new ArrayList<>();

    public static void addScore(String playerName, int score, int levelPlayed, int tries) {
    highScores.add(new Score(playerName, score, levelPlayed, tries)); // Update Score constructor
    highScores.sort(Comparator.comparingInt(Score::getScore).reversed());
    if (highScores.size() > 10) {
        highScores = highScores.subList(0, 10); // Keep only top 10 scores
      }
    }


    public static String[][] getHighScoresData() {
        String[][] data = new String[highScores.size()][4];
        for (int i = 0; i < highScores.size(); i++) {
            Score score = highScores.get(i);
            data[i][0] = score.getPlayerName();
            data[i][1] = getLevelName(score.getLevelPlayed());
            data[i][2] = String.valueOf(score.getTries());
	    data[i][3] = String.valueOf(score.getScore()); 
        }
        return data;
    }

    private static String getLevelName(int levelPlayed) {
        switch (levelPlayed) {
            case 4:
                return "Easy";
            case 8:
                return "Medium";
            case 18:
                return "Difficult";
            default:
                return "Unknown";
        }
    }

    private static class Score {
        private String playerName;
        private int score;
        private int levelPlayed;
	private int tries;

        public Score(String playerName, int score, int levelPlayed, int tries) {
            this.playerName = playerName;
            this.score = score;
            this.levelPlayed = levelPlayed;
	    this.tries = tries;
        }

        public String getPlayerName() {
            return playerName;
        }

        public int getScore() {
            return score;
        }

        public int getLevelPlayed() {
            return levelPlayed;
        }
	
	public int getTries() {
        return tries;
    	}

    }
}
