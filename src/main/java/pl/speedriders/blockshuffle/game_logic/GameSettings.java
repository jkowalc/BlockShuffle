package pl.speedriders.blockshuffle.game_logic;

public class GameSettings {
    public int roundTime;
    public int livesCount;
    public GameSettings() {
        this.roundTime = 5*60;
        this.livesCount = 2;
    }
    public GameSettings(int roundTime, int roundDelayTime, int livesCount) {
        this.roundTime = roundTime;
        this.livesCount = livesCount;
    }
}

