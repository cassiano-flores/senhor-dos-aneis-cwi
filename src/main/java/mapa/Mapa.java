package mapa;

import exceptions.PersonagemJaEstaNoMapaException;
import exceptions.PersonagemNaoEncontradoNoMapaException;
import exceptions.PosicaoOcupadaException;
import personagens.Personagem;

public class Mapa {

    private Personagem[] mapa;

    public Mapa() {
        this.mapa = new Personagem[10];
    }

    public String exibir(){
        removerEliminados();

        StringBuilder visualizacao = new StringBuilder();

        for (int i = 0; i <= 9; i++){

            if (mapa[i] != null){
                visualizacao.append("|").append(mapa[i]);
            } else {
                visualizacao.append("| ");
            }
        }

        return visualizacao + "|";
    }

    public void inserir(int posicao, Personagem personagem) throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException {

        if (mapa[posicao] != null)
            throw new PosicaoOcupadaException("Já existe um personagem na posição passada.");

        for (int i = 0; i <= 9; i++){
            if (mapa[i] == personagem)
                throw new PersonagemJaEstaNoMapaException("O personagem passado já está no mapa.");
        }

        mapa[posicao] = personagem;
    }

    public void remover(int posicao){
        mapa[posicao] = null;
    }

    public int buscarPosicao(Personagem personagem) throws PersonagemNaoEncontradoNoMapaException {
        int posicao = -1;
        boolean personagemExiste = false;

        for (int i = 0; i <= 9; i++){
            if (mapa[i] == personagem){
                personagemExiste = true;
                posicao = i;
                break;
            }
        }
        if (!personagemExiste)
            throw new PersonagemNaoEncontradoNoMapaException("Personagem informado não existe no mapa.");

        return posicao;
    }

    public Personagem buscarCasa(int posicao) {

        if ((posicao >= 0) && (posicao <= 9)) {
            return mapa[posicao];

        } else {
            return null;
        }
    }

    public void removerEliminados() {
        for (int i = 0; i <= 9; i++){
            if ((mapa[i] != null) && (mapa[i].getConstituicao() <= 0))
                mapa[i] = null;
        }
    }
}
