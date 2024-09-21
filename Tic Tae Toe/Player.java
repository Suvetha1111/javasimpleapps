public class Player {
    private int playerId;
    private char mark;

    public Player(int playerId, char mark) {
        this.playerId = playerId;
        this.mark = mark;
    }

    public int getPlayerId() {
        return playerId;
    }

    public char getMark() {
        return mark;
    }
}
