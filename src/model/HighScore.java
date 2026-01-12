package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hanterar highscore-listan och sparar/laddar den från en textfil.
 *
 * @author Intisaar & Maya
 */
public class HighScore {

    private String fileName;
    private ArrayList<ScoreEntry> entries;


    /**
     * Skapar en ny HighScore och laddar befintliga poster från fil.
     */
    public HighScore(){
        this.fileName = "highscore.txt";
        this.entries = new ArrayList<>();
        loadFromFile();
    }


    /**
     * Lägger till en ny highscore-post och sparar till fil.
     *
     * @param name spelarens namn
     * @param score poängen
     */
    public void addScore(String name, int score){
        entries.add(new ScoreEntry(name, score));
        sortEntries();
        saveToFile();
    }


    /**
     * Hämtar de högsta poängen sorterade i fallande ordning.
     *
     * @param n antal poster att hämta
     * @return lista med de n högsta poängen
     */
    public ArrayList<ScoreEntry> getTopScores(int n){
        ArrayList<ScoreEntry> topScores = new ArrayList<>();
        for(int i = 0; i < n && i < entries.size(); i++){
            topScores.add(entries.get(i));
        }
        return topScores;
    }


    /**
     * Formaterar highscore-listan som en sträng.
     *
     * @param n antal poster att visa
     * @return formaterad highscore-lista
     */
    public String getTopScoresText(int n){
        String result = "=== HIGHSCORE ===\n";
        ArrayList<ScoreEntry> topScores = getTopScores(n);

        if (topScores.size() == 0){
            result = result + "(ingen data än)\n";
            return result;
        }

        for(int i = 0; i < topScores.size(); i++){
            ScoreEntry e = topScores.get(i);
            result = result + (i + 1) + ". " + e.getName() +
                    " - " + e.getScore() + "\n";
        }
        return result;
    }


    /**
     * Laddar highscore från textfil.
     */
    private void loadFromFile(){
        entries.clear();
        File file = new File(fileName);

        if(file.exists() == false){
            return;
        }

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if(parts.length == 2){
                    String name = parts[0].trim();
                    int score = Integer.parseInt(parts[1].trim());
                    entries.add(new ScoreEntry(name, score));
                }
            }
            scanner.close();
            sortEntries();
        } catch (IOException e){
            System.out.println("Kunde inte läsa highscore-filen.");
        }
    }


    /**
     * Sparar highscore till textfil.
     */
    private void saveToFile(){
        try {
            FileWriter writer = new FileWriter(fileName);
            for(int i = 0; i < entries.size(); i++){
                ScoreEntry e = entries.get(i);
                writer.write(e.getName() + "," + e.getScore() + "\n");
            }
            writer.close();
        } catch (IOException e){
            System.out.println("Kunde inte spara highscore-filen.");
        }
    }


    /**
     * Sorterar entries i fallande ordning baserat på poäng.
     */
    private void sortEntries(){
        for(int i = 0; i < entries.size(); i++){
            for(int j = i + 1; j < entries.size(); j++){
                if(entries.get(j).getScore() > entries.get(i).getScore()){
                    ScoreEntry temp = entries.get(i);
                    entries.set(i, entries.get(j));
                    entries.set(j, temp);
                }
            }
        }
    }


    /**
     * Inre klass som representerar en highscore-post
     */
    public class ScoreEntry{
        private String name;
        private int score;


        /**
         * Skapar en ny ScoreEntry.
         *
         * @param name spelarens namn
         * @param score poängen
         */
        public ScoreEntry(String name, int score){
            this.name = name;
            this.score = score;
        }


        /**
         * Hämtar spelarens namn.
         *
         * @return namnet
         */
        public String getName(){
            return name;
        }


        /**
         * Hämtar poängen.
         *
         * @return poängen
         */
        public int getScore(){
            return score;
        }
    }
}