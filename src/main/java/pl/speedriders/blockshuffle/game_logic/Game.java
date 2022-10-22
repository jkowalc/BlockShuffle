package pl.speedriders.blockshuffle.game_logic;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import static java.lang.Math.abs;

public class Game {
    private final UUID id;
    private final UUID owner;
    private final Set<UUID> players = new HashSet<>();
    private final HashMap<UUID, Integer> lives = new HashMap<>();
    private final HashMap<UUID, Material> materials = new HashMap<>();
    private GameState currGameState = GameState.CREATED;
    private int currRound = 0;
    private LocalDateTime roundStart;
    private GameSettings settings;

    public Game(UUID owner) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.id = UUID.fromString(dateFormat.format(date));
        this.owner = owner;
    }

    public String toString() {
        String ownerName = Bukkit.getOfflinePlayer(owner).getName();
        return "Game " + id + " (owner: " + ownerName + ")";
    }

    public GameState getCurrGameState() {
        return currGameState;
    }

    public int getCurrRound() {
        return currRound;
    }

    public void start() {
        if (currGameState == GameState.CREATED) {
            Bukkit.getLogger().info(this + " is being started");
        } else {
            Bukkit.getLogger().info(this + " is being restarted");
        }
        players.forEach(playerID -> {
            lives.put(playerID, 0);
        });
        nextRound();
        currGameState = GameState.RUNNING;
    }

    public void nextRound() {
        currRound++;
        Bukkit.getLogger().info("Round " + currRound + " of game " + this + "has started");

    }

    public void stop() {

        currGameState = GameState.END;
    }

    public int getCurrRoundTime() throws Exception {
        if (currGameState != GameState.RUNNING) {
            throw new Exception("Cannot get current round time: the game is not running");
        }
        return abs(roundStart.compareTo(LocalDateTime.now()));
    }

    public void addPlayer(UUID playerID) throws GameLogicException {
        if (currGameState != GameState.RUNNING) players.add(playerID);
        else throw new GameLogicException("Cannot add players while the game is running");
    }

    public void removePlayer(UUID playerID) throws GameLogicException {
        if (currGameState != GameState.RUNNING) players.remove(playerID);
        else throw new GameLogicException("Cannot remove players while the game is running");
    }

    public UUID getId() {
        return id;
    }

    enum GameState {
        CREATED,
        RUNNING,
        END
    }
}
