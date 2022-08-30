package classes;

import exceptions.PersonagemJaEstaNoMapaException;
import exceptions.PosicaoOcupadaException;
import mapa.Mapa;
import personagens.Personagem;

public abstract class Arqueiro extends Personagem {

    public Arqueiro(int forca, int agilidade, int inteligencia, int constituicao, boolean sociedadeDoAnel) {
        super(forca, agilidade, inteligencia, constituicao, sociedadeDoAnel);
    }

    public void atacar(Mapa mapa, int posicaoAtual) {

        int distancia = 1;
        Personagem personagemInimigo = null;

        if (isSociedadeDoAnel()) {
            if (posicaoAtual < 9) {

                for (int i = 1; i <= 3 || (posicaoAtual + i > 9); i++) {
                    personagemInimigo = mapa.buscarCasa(posicaoAtual + i);

                    if ((personagemInimigo != null) && (this.isSociedadeDoAnel() != personagemInimigo.isSociedadeDoAnel()))
                        distancia = i;
                }
                personagemInimigo = mapa.buscarCasa(posicaoAtual + distancia);
            }

        } else {
            if (posicaoAtual > 0) {

                for (int i = 1; i <= 3 || (posicaoAtual - i < 0); i++) {
                    personagemInimigo = mapa.buscarCasa(posicaoAtual - i);

                    if ((personagemInimigo != null) && (this.isSociedadeDoAnel() != personagemInimigo.isSociedadeDoAnel()))
                        distancia = i;
                }
                personagemInimigo = mapa.buscarCasa(posicaoAtual - distancia);
            }
        }

        int dano = distancia * this.agilidade;
        if ((personagemInimigo != null) && (this.isSociedadeDoAnel() != personagemInimigo.isSociedadeDoAnel()))
            sofrerDano(personagemInimigo, dano);
    }

    public void movimentacao(Mapa mapa, int posicaoAtual) throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException {

        int posicaoNova = posicaoAtual;
        Personagem personagem = mapa.buscarCasa(posicaoAtual);

        for (int i = 0; i <= 1; i++) {

            if (this.isSociedadeDoAnel()) {
                posicaoNova += 1;
            } else {
                posicaoNova -= 1;
            }

            if ((posicaoNova < 10) && (mapa.buscarCasa(posicaoNova) == null)) {
                mapa.remover(posicaoAtual);
                mapa.inserir(posicaoNova, personagem);
                posicaoAtual = posicaoNova;
            } else {
                break;
            }
        }
    }
}
