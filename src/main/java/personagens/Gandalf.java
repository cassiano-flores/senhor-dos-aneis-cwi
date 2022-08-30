package personagens;

import classes.Mago;
import racas.Maia;

public class Gandalf extends Mago implements Maia {

    public Gandalf() {
        super(2, 3, 10, 80, true);
    }

    @Override
    public String toString() {
        return "G";
    }

    @Override
    public void ressucitar() {
        if (this.constituicao == 0)
            new Gandalf();
    }

    @Override
    public void falar() {
        System.out.println("A Wizard is never late, nor is he early. He arrives precisely when he means to.");
    }
}
