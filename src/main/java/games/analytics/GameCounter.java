package games.analytics;

public class GameCounter {
    public long winCounter;
    public long loseCounter;
    public long drawCounter;
    public long winningStreak;
    public long losingStreak;
    public long drawStreak;

    public double highestBalance;
    public double lowestBalance;
    public double finalBalance;

    @Override
    public String toString() {
        return "GameCounter{" +
                "\nfinalBalance=" + finalBalance +
                ", highestBalance=" + highestBalance +
                ", lowestBalance=" + lowestBalance +
                ",\nwinCounter=" + winCounter +
                ", loseCounter=" + loseCounter +
                ", drawCounter=" + drawCounter +
                ",\nwinningStreak=" + winningStreak +
                ", losingStreak=" + losingStreak +
                ", drawStreak=" + drawStreak +
                '}';
    }
}
