package personagens;

import classes.Guerreiro;
import racas.Humano;
import racas.Monstro;

public class Urukhai extends Guerreiro implements Monstro, Humano {
    private static final int DANO_POR_ENVELHECER = 2;

    public Urukhai() {
        super(8, 6, 3, 45, false);
    }

    @Override
    public String toString() {
        return "U";
    }

    @Override
    public void envelhecer() {
        setConstituicao(this.constituicao - DANO_POR_ENVELHECER);
    }

    @Override
    public void falar() {
        System.out.println("Looks like meat's back on the menu boys!");
    }

    @Override
    public void grunir() {
        System.out.println("Uuurrrrrr");
    }
}
