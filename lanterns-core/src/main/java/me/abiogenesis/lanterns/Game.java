package me.abiogenesis.lanterns;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {

    private final Map<Card, Integer> cards;
    private final Board board;
    private final Stack<Tile> deck = new Stack<>();
    private int favorTokenSupply;
    private final List<Player> players;

    public Game(int numberOfPlayers) {
        this(numberOfPlayers, TileSet.getStock(), Settings.getDefaults());
    }

    public Game(int numberOfPlayers, TileSet tileSet, Settings settings) {
        this.players = IntStream.rangeClosed(1, numberOfPlayers)
            .mapToObj(position -> String.format("Player%d", position))
            .map(Player::new)
            .collect(Collectors.toList());
        this.favorTokenSupply = settings.numberOfFavorTokens;
        prepareDeck(tileSet.getTiles());
        Tile startTile = tileSet.getStartTile();
        startTile.clear();
        this.board = new Board(startTile);
        cards = tileSet.getLanternTypes().stream()
            .collect(Collectors.toMap(
                Card::new,
                lantern -> settings.numberOfCardsPerLantern,
                (a, b) -> a, // won't be called due to Set enforcing unique elements
                LinkedHashMap::new));
    }

    private void prepareDeck(Collection<Tile> tiles) {
        // shuffle tiles
        List<Tile> pile = new ArrayList<>(tiles);
        Collections.shuffle(pile);
        // remove existing edges
        pile.forEach(Tile::clear);
        // add to deck
        deck.addAll(tiles);
    }
}
