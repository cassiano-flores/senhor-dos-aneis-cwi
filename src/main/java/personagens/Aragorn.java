package personagens;

import classes.Guerreiro;
import racas.Humano;

public class Aragorn extends Guerreiro implements Humano {
    private static final int DANO_POR_ENVELHECER = 1;

    public Aragorn() {
        super(10, 7, 6, 60, true);
    }

    @Override
    public String toString() {
        return "A";
    }

    @Override
    public void envelhecer() {
        setConstituicao(this.constituicao - DANO_POR_ENVELHECER);
    }

    @Override
    public void falar() {
        System.out.println("A day may come when the courage of men fails… but it is not THIS day.");
    }
}
