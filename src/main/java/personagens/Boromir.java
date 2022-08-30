package personagens;

import classes.Guerreiro;
import racas.Humano;

public class Boromir extends Guerreiro implements Humano {
    private static final int DANO_POR_ENVELHECER = 2;

    public Boromir() {
        super(7, 6, 3, 40, true);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public void falar() {
        System.out.println("One does not simply walk into Mordor.");
    }

    @Override
    public void envelhecer() {
        setConstituicao(this.constituicao - DANO_POR_ENVELHECER);
    }
}
