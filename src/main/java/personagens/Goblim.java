package personagens;

import classes.Arqueiro;
import racas.Monstro;

public class Goblim extends Arqueiro implements Monstro {

    public Goblim() {
        super(3, 6, 1, 20, false);
    }

    @Override
    public String toString() {
        return "M";
    }

    @Override
    public void grunir() {
        System.out.println("Iiisshhhh");
    }
}
