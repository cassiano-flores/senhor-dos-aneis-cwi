package personagens;

import classes.Arqueiro;
import racas.Elfo;

public class Legolas extends Arqueiro implements Elfo {

    public Legolas() {
        super(5, 10, 6, 80, true);
    }

    @Override
    public String toString() {
        return "L";
    }

    @Override
    public void falarElfico() {
        System.out.println("I amar prestar aen, han mathon ne nem, han mathon ne chae, a han noston ned.");
    }

    @Override
    public void falar() {
        System.out.println("They're taking the Hobbits to Isengard!");
    }
}
