package classes;

import exceptions.PersonagemJaEstaNoMapaException;
import exceptions.PosicaoOcupadaException;
import mapa.Mapa;
import personagens.Personagem;

public abstract class Mago extends Personagem {

    public Mago(int forca, int agilidade, int inteligencia, int constituicao, boolean sociedadeDoAnel) {
        super(forca, agilidade, inteligencia, constituicao, sociedadeDoAnel);
    }

    public void atacar(Mapa mapa) {

        int dano = this.inteligencia;

        for (int i = 0; i <= 9; i++){
            Personagem personagemInimigo = mapa.buscarCasa(i);

            if (personagemInimigo != null && (this.isSociedadeDoAnel() != personagemInimigo.isSociedadeDoAnel())){
                sofrerDano(personagemInimigo, dano);
            }
        }
    }

    public void movimentacao(Mapa mapa, int posicaoAtual) throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException {

        int posicaoNova = posicaoAtual;
        boolean unicoPersonagem = true;

        if (this.isSociedadeDoAnel()){
            posicaoNova += 1;
        } else {
            posicaoNova -= 1;
        }

        Personagem personagem = mapa.buscarCasa(posicaoAtual);

        for (int i = 0; i <= 9; i++){
            Personagem personagemInimigo = mapa.buscarCasa(i);

            if ((personagemInimigo != null) && (i != posicaoAtual)){
                unicoPersonagem = false;
                break;
            }
        }

        if ((posicaoNova < 10) && unicoPersonagem) {
            mapa.remover(posicaoAtual);
            mapa.inserir(posicaoNova, personagem);
        }
    }
}
