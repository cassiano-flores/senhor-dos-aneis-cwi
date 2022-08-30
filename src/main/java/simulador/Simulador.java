package simulador;

import exceptions.PersonagemJaEstaNoMapaException;
import exceptions.PersonagemNaoEncontradoNoMapaException;
import exceptions.PosicaoOcupadaException;
import exceptions.SauronDominaOMundoException;
import mapa.Mapa;
import personagens.*;

public class Simulador {

    Mapa mapa;

    public Simulador(Mapa mapa) {
        this.mapa = mapa;
    }

    public void simular() throws PersonagemNaoEncontradoNoMapaException, PosicaoOcupadaException, PersonagemJaEstaNoMapaException, SauronDominaOMundoException {
        boolean vitoriaSociedadeDoAnel = false;
        boolean vitoriaParaSauron = false;
        boolean repetidoPodeJogar = false;
        Personagem ultimoPersonagemJogado = null;

        while (!vitoriaSociedadeDoAnel && !vitoriaParaSauron){
            for (int i = 0; i <= 9; i++){

                //System.out.println(mapa.exibir());
                Personagem personagem = mapa.buscarCasa(i);

                if ((ultimoPersonagemJogado != personagem) || repetidoPodeJogar) {

                    if (personagem instanceof Aragorn) {
                        ((Aragorn) personagem).atacar(mapa, mapa.buscarPosicao(personagem));
                        mapa.removerEliminados();
                        ((Aragorn) personagem).movimentacao(mapa, mapa.buscarPosicao(personagem));
                    }
                    if (personagem instanceof Boromir) {
                        ((Boromir) personagem).atacar(mapa, mapa.buscarPosicao(personagem));
                        mapa.removerEliminados();
                        ((Boromir) personagem).movimentacao(mapa, mapa.buscarPosicao(personagem));
                    }
                    if (personagem instanceof Gandalf) {
                        ((Gandalf) personagem).atacar(mapa);
                        mapa.removerEliminados();
                        ((Gandalf) personagem).movimentacao(mapa, mapa.buscarPosicao(personagem));
                    }
                    if (personagem instanceof Gimli) {
                        ((Gimli) personagem).atacar(mapa, mapa.buscarPosicao(personagem));
                        mapa.removerEliminados();
                        ((Gimli) personagem).movimentacao(mapa, mapa.buscarPosicao(personagem));
                    }
                    if (personagem instanceof Goblim) {
                        ((Goblim) personagem).atacar(mapa, mapa.buscarPosicao(personagem));
                        mapa.removerEliminados();
                        ((Goblim) personagem).movimentacao(mapa, mapa.buscarPosicao(personagem));
                    }
                    if (personagem instanceof Legolas) {
                        ((Legolas) personagem).atacar(mapa, mapa.buscarPosicao(personagem));
                        mapa.removerEliminados();
                        ((Legolas) personagem).movimentacao(mapa, mapa.buscarPosicao(personagem));
                    }
                    if (personagem instanceof Orc) {
                        ((Orc) personagem).atacar(mapa, mapa.buscarPosicao(personagem));
                        mapa.removerEliminados();
                        ((Orc) personagem).movimentacao(mapa, mapa.buscarPosicao(personagem));
                    }
                    if (personagem instanceof Saruman) {
                        ((Saruman) personagem).atacar(mapa);
                        mapa.removerEliminados();
                        ((Saruman) personagem).movimentacao(mapa, mapa.buscarPosicao(personagem));
                    }
                    if (personagem instanceof Urukhai) {
                        ((Urukhai) personagem).atacar(mapa, mapa.buscarPosicao(personagem));
                        mapa.removerEliminados();
                        ((Urukhai) personagem).movimentacao(mapa, mapa.buscarPosicao(personagem));
                    }
                }

                int count = 0;
                for (int j = 0; j <= 9; j++){
                    Personagem personagemRestante = mapa.buscarCasa(j);

                    if (personagemRestante != null){
                        count++;
                    }
                }

                if (count == 1)
                    repetidoPodeJogar = true;

                if (personagem != null)
                    ultimoPersonagemJogado = personagem;

                mapa.exibir();
            }
            //testa vitoria sociedade
            Personagem personagemUltimaPosicao = mapa.buscarCasa(9);
            if ((personagemUltimaPosicao != null) && (personagemUltimaPosicao.isSociedadeDoAnel()))
                vitoriaSociedadeDoAnel = true;

            //testa vitoria sauron
            for (int i = 0; i <= 9; i++){

                Personagem personagem = mapa.buscarCasa(i);

                if ((personagem != null) && (personagem.isSociedadeDoAnel())){
                    vitoriaParaSauron = false;
                    break;

                } else {
                    vitoriaParaSauron = true;
                }
            }
        }
        if (vitoriaParaSauron)
            throw new SauronDominaOMundoException("A humanidade sofre perante a tirania de Sauron.");
    }
}
