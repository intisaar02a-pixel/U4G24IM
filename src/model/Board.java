package model;

import model.Mysteries.AdditivaMetoder;
import model.Mysteries.AvgrundsVrål;
import model.Mysteries.Demagog;
import model.Mysteries.Multiplicitet;
import model.Mysteries.Narcissus;
import model.Mysteries.Tidshopp;
import java.util.Random;
import java.util.ArrayList;

public class Board {

    private final int cols;
    private final int rows;
    private final Piece[][] grid;
    private int activatedMysteries = 0;
    private final int totalMysteries;

    public Board (int rows, int cols, int mysteryCount){
        this.rows = rows;
        this.cols = cols;
        this.grid = new Piece[rows][cols];
        this.totalMysteries = mysteryCount;
        generateBoard();
    }

    private void generateBoard(){
        Random rand = new Random();
        int placed = 0;

        while (placed < totalMysteries){
            int r = rand.nextInt(rows);
            int c = rand.nextInt(cols);

            if(isValidMysteryLocation(r, c)){
                grid[r][c] = createRandomMystery(rand);
                placed++;
            }
        }
    }

    private boolean isValidMysteryLocation(int r, int c){
        if(grid[r][c] != null){
            return false;
        }

        if((r == 0 && c == 0) || (r == 0 && c == cols - 1)
                || (r == rows - 1 && c == 0) || (r == rows - 1 && c == cols - 1)){
            return false;
        }

        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                int nr = r + i;
                int nc = c + j;
                if(isInside(nr, nc)){
                    Piece p = grid[nr][nc];
                    if(p != null && p.isMystery()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private MysteryPiece createRandomMystery(Random rand){
        int type = rand.nextInt(6);

        if(type == 0) return new Tidshopp();
        if(type == 1) return new Narcissus();
        if(type == 2) return new AdditivaMetoder();
        if(type == 3) return new Multiplicitet();
        if(type == 4) return new AvgrundsVrål();
        return new Demagog();
    }

    public MoveResult placePiece(int r, int c, PlayerType player, boolean isFirstMove){
        if(isInside(r, c) == false){
            return MoveResult.invalid("Utanför spelplanen!");
        }

        if(grid[r][c] != null){
            return MoveResult.invalid("Rutan är redan upptagen!");
        }

        if(isFirstMove == false && hasNeighbor(r, c) == false){
            return MoveResult.invalid("Du måste placera intill en annan pjäs!");
        }

        grid[r][c] = new PlayerPiece(player);
        ArrayList<MysteryEffect> effects = new ArrayList<>();

        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(i == 0 && j == 0) continue;
                checkDirection(r, c, i, j, player, effects);
            }
        }
        return MoveResult.ok(effects);
    }

    private boolean hasNeighbor(int r, int c){
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(i == 0 && j == 0) continue;
                int nr = r + i;
                int nc = c + j;
                if(isInside(nr, nc) && grid[nr][nc] != null){
                    return true;
                }
            }
        }
        return false;
    }

    private void checkDirection(int r, int c, int i, int j, PlayerType player, ArrayList<MysteryEffect> effects){
        int rr = r + i;
        int cc = c + j;

        ArrayList<int[]> between = new ArrayList<>();

        while(isInside(rr, cc)){
            Piece p = grid[rr][cc];

            if(p == null){
                return;
            }

            if(p.isMystery() == false && p.getOwner() == player){
                for (int k = 0; k < between.size(); k++){
                    int[] pos = between.get(k);
                    flipAt(pos[0], pos[1], player, effects);
                }
                return;
            }

            between.add(new int[]{rr, cc});
            rr = rr + i;
            cc = cc + j;
        }
    }

    private void flipAt(int r, int c, PlayerType player, ArrayList<MysteryEffect> effects){
        Piece p = grid[r][c];
        if(p == null) return;

        if(p.isMystery()){
            activatedMysteries++;
            MysteryPiece mp = (MysteryPiece) p;
            MysteryEffect effect = mp.executeEffect(this, r, c, player);
            if(effect != null){
                effects.add(effect);
            }
            grid[r][c] = new PlayerPiece(player);
        } else {
            p.setOwner(player);
        }
    }

    public void forceSet(int r, int c, PlayerType owner){
        if(isInside(r, c) == false) return;

        if(grid[r][c] != null && grid[r][c].isMystery()){
            return;
        }

        grid[r][c] = new PlayerPiece(owner);
    }

    public void forceClear(int r, int c){
        if (isInside(r, c) == false) return;

        if(grid[r][c] != null && grid[r][c].isMystery()){
            return;
        }
        grid[r][c] = null;
    }

    public CellState getCellState(int r, int c){
        Piece p = grid[r][c];
        if(p == null) return CellState.EMPTY;
        if(p.isMystery()) return CellState.MYSTERY;

        if(p.getOwner() == PlayerType.PLAYER_1){
            return CellState.P1;
        } else {
            return CellState.P2;
        }
    }

    public int getScore(PlayerType player){
        int score = 0;
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                Piece p = grid[r][c];
                if(p != null && p.isMystery() == false && p.getOwner() == player){
                    score++;
                }
            }
        }
        return score;
    }

    public boolean isFull(){
        for(int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                if(grid[r][c] == null) return false;
            }
        }
        return true;
    }

    public boolean allMysteriesActivated(){
        return activatedMysteries >= totalMysteries;
    }

    public boolean isGameOver(){
        return isFull() || allMysteriesActivated();
    }

    public boolean isInside(int r, int c){
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }
}