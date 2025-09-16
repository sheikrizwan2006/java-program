import java.util.Objects;

class Game {
    protected String name;
    protected int maxPlayers;

    public Game(String name, int maxPlayers) {
        this.name = name;
        this.maxPlayers = maxPlayers;
    }

    @Override
    public String toString() {
        return "Game{name='" + name + "', maxPlayers=" + maxPlayers + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Game)) return false;
        Game other = (Game) obj;
        return maxPlayers == other.maxPlayers && Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, maxPlayers);
    }
}

class CardGame extends Game {
    private int numberOfCards;

    public CardGame(String name, int maxPlayers, int numberOfCards) {
        super(name, maxPlayers);
        this.numberOfCards = numberOfCards;
    }

    @Override
    public String toString() {
        return super.toString() + ", CardGame{numberOfCards=" + numberOfCards + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof CardGame)) return false;
        CardGame other = (CardGame) obj;
        return numberOfCards == other.numberOfCards;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfCards);
    }
}

public class HwProblem3 {
    public static void main(String[] args) {
        Game game1 = new Game("Chess", 2);
        Game game2 = new Game("Chess", 2);

        CardGame cardGame1 = new CardGame("Poker", 6, 52);
        CardGame cardGame2 = new CardGame("Poker", 6, 52);
        CardGame cardGame3 = new CardGame("Poker", 6, 54);

        System.out.println(game1);
        System.out.println(cardGame1);

        System.out.println("game1 equals game2? " + game1.equals(game2));
        System.out.println("cardGame1 equals cardGame2? " + cardGame1.equals(cardGame2));
        System.out.println("cardGame1 equals cardGame3? " + cardGame1.equals(cardGame3));
    }
}
