package personagens;

import classes.Mago;
import racas.Maia;

public class Saruman extends Mago implements Maia {

    public Saruman() {
        super(2, 2, 9, 70, false);
    }

    @Override
    public String toString() {
        return "S";
    }

    @Override
    public void ressucitar() {
        //return null;
    }

    @Override
    public void falar() {
        System.out.println("Against the power of Mordor there can be no victory.");
    }
}
