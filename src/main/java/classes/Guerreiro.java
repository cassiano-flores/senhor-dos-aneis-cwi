package classes;

import exceptions.PersonagemJaEstaNoMapaException;
import exceptions.PosicaoOcupadaException;
import mapa.Mapa;
import personagens.Personagem;

public abstract class Guerreiro extends Personagem {

    public Guerreiro(int forca, int agilidade, int inteligencia, int constituicao, boolean sociedadeDoAnel) {
        super(forca, agilidade, inteligencia, constituicao, sociedadeDoAnel);
    }

    public void atacar(Mapa mapa, int posicaoAtual) {

        int dano = this.forca * 2;

        if (posicaoAtual > 0) {
            Personagem personagemInimigo1 = mapa.buscarCasa(posicaoAtual - 1);

            if ((personagemInimigo1 != null) && (this.isSociedadeDoAnel() != personagemInimigo1.isSociedadeDoAnel()))
                sofrerDano(personagemInimigo1, dano);
        }

        if (posicaoAtual < 9) {
            Personagem personagemInimigo2 = mapa.buscarCasa(posicaoAtual + 1);

            if ((personagemInimigo2 != null) && (this.isSociedadeDoAnel() != personagemInimigo2.isSociedadeDoAnel()))
                sofrerDano(personagemInimigo2, dano);
        }
    }

    public void movimentacao(Mapa mapa, int posicaoAtual) throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException {

        int posicaoNova = posicaoAtual;

        if (this.isSociedadeDoAnel()){
            posicaoNova += 1;
        } else {
            posicaoNova -= 1;
        }

        Personagem personagem = mapa.buscarCasa(posicaoAtual);

        if ((posicaoNova < 10) && (mapa.buscarCasa(posicaoNova) == null)) {
            mapa.remover(posicaoAtual);
            mapa.inserir(posicaoNova, personagem);
        }
    }
}
