package personagens;

import classes.Guerreiro;
import racas.Anao;

public class Gimli extends Guerreiro implements Anao {
    private final int QUANTIDADE_PARA_BEBADO = 3;
    private int quantidadeDeBebidas;

    public Gimli() {
        super(9, 2, 4, 60, true);
    }

    @Override
    public String toString() {
        return "I";
    }

    @Override
    public void beber() {
        quantidadeDeBebidas++;
    }

    @Override
    public void falar() {
        if (quantidadeDeBebidas >= QUANTIDADE_PARA_BEBADO){
            System.out.println("What did I say? He can't hold his liquor!");
        } else {
            System.out.println("Let them come. There is one Dwarf yet in Moria who still draws breath.");
        }
    }
}
