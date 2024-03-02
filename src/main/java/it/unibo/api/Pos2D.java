package main.java.it.unibo.api;

public class Pos2D {
    private int x;
    private int y;

    public Pos2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * ci da la nuova posizione in base a una direzione
     * e' una funzione stupida, sta all'oggetto gestire se il risultato e' giusto o
     * sbagliato
     */
    public static Pos2D transform(Pos2D p, Direction d) {
        return new Pos2D(p.getX() + d.getHorizontalVelocity(), p.getY() + d.GetVerticalVelocity());
    }
}
