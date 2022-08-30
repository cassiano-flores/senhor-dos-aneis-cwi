package personagens;

import classes.Guerreiro;
import racas.Monstro;

public class Orc extends Guerreiro implements Monstro {

    public Orc() {
        super(7, 4, 1, 30, false);
    }

    @Override
    public String toString() {
        return "O";
    }

    @Override
    public void grunir() {
        System.out.println("Arrrggghhh");
    }
}
