import exceptions.*;
import mapa.Mapa;
import org.junit.Assert;
import org.junit.Test;
import personagens.*;
import simulador.Simulador;

public class SimuladorTest {

    @Test
    public void deveInserirEPrintar() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException {
        String resultadoEsperado = "|A|L| | | | | |O| |M|";

        Aragorn aragorn = new Aragorn();
        Legolas legolas = new Legolas();
        Orc orc = new Orc();
        Goblim goblim = new Goblim();
        Mapa mapa = new Mapa();

        mapa.inserir(0, aragorn);
        mapa.inserir(1, legolas);
        mapa.inserir(7, orc);
        mapa.inserir(9, goblim);

        Assert.assertEquals(resultadoEsperado, mapa.exibir());
    }

    @Test
    public void deveBuscarPosicao() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "|A|L| | | | | |O| |M|"

        Aragorn aragorn = new Aragorn();
        Legolas legolas = new Legolas();
        Orc orc = new Orc();
        Goblim goblim = new Goblim();
        Mapa mapa = new Mapa();

        mapa.inserir(0, aragorn);
        mapa.inserir(1, legolas);
        mapa.inserir(7, orc);
        mapa.inserir(9, goblim);

        int resultadoEsperado = 7;

        Assert.assertEquals(resultadoEsperado, mapa.buscarPosicao(orc));
    }

    @Test
    public void deveBuscarCasa() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException {
        // Mapa:  "|A|L| | | | | |O| |M|"

        Aragorn aragorn = new Aragorn();
        Legolas legolas = new Legolas();
        Orc orc = new Orc();
        Goblim goblim = new Goblim();
        Mapa mapa = new Mapa();

        mapa.inserir(0, aragorn);
        mapa.inserir(1, legolas);
        mapa.inserir(7, orc);
        mapa.inserir(9, goblim);

        Assert.assertEquals(legolas, mapa.buscarCasa(1));
    }

    @Test
    public void deveAndarParaDireitaOGuerreiroSociedade() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "|A| |L| | | | |O| |M|"

        Aragorn aragorn = new Aragorn();
        Legolas legolas = new Legolas();
        Orc orc = new Orc();
        Goblim goblim = new Goblim();
        Mapa mapa = new Mapa();

        int resultadoEsperado = 1;

        mapa.inserir(0, aragorn);
        mapa.inserir(2, legolas);
        mapa.inserir(7, orc);
        mapa.inserir(9, goblim);

        aragorn.atacar(mapa, mapa.buscarPosicao(aragorn));
        aragorn.movimentacao(mapa, mapa.buscarPosicao(aragorn));

        Assert.assertEquals(resultadoEsperado, mapa.buscarPosicao(aragorn));
    }

    @Test
    public void deveAndarParaEsquerdaOGuerreiroNaoSociedade() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "|A| |L| | | | |O| |U|"

        Aragorn aragorn = new Aragorn();
        Legolas legolas = new Legolas();
        Orc orc = new Orc();
        Urukhai urukhai = new Urukhai();
        Mapa mapa = new Mapa();

        int resultadoEsperado = 8;

        mapa.inserir(0, aragorn);
        mapa.inserir(2, legolas);
        mapa.inserir(7, orc);
        mapa.inserir(9, urukhai);

        urukhai.atacar(mapa, mapa.buscarPosicao(urukhai));
        urukhai.movimentacao(mapa, mapa.buscarPosicao(urukhai));

        Assert.assertEquals(resultadoEsperado, mapa.buscarPosicao(urukhai));
    }

    @Test
    public void deveManterConstituicaoQuandoTentarDarDanoEmAliado() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "| | | |A|L| | | | | |"

        Aragorn aragorn = new Aragorn();
        Legolas legolas = new Legolas();
        Mapa mapa = new Mapa();

        int resultadoEsperado = 80;

        mapa.inserir(3, aragorn);
        mapa.inserir(4, legolas);

        aragorn.atacar(mapa, mapa.buscarPosicao(aragorn));

        Assert.assertEquals(resultadoEsperado, legolas.getConstituicao());
    }

    @Test
    public void deveDescontarConstituicaoQuandoTentarDarDanoEmInimigo() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "| | | |A|L| | | | | |"

        Aragorn aragorn = new Aragorn();
        Urukhai urukhai = new Urukhai();
        Mapa mapa = new Mapa();

        int resultadoEsperadoUrukhai = 25;
        int resultadoEsperadoAragorn = 44;

        mapa.inserir(3, aragorn);
        mapa.inserir(4, urukhai);

        urukhai.atacar(mapa, mapa.buscarPosicao(urukhai));
        aragorn.atacar(mapa, mapa.buscarPosicao(aragorn));

        Assert.assertEquals(resultadoEsperadoUrukhai, urukhai.getConstituicao());
        Assert.assertEquals(resultadoEsperadoAragorn, aragorn.getConstituicao());
    }

    @Test
    public void deveManterConstituicaoQuandoNaoAlcancar() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "| | |A| |L| | | | | |"

        Aragorn aragorn = new Aragorn();
        Urukhai urukhai = new Urukhai();
        Mapa mapa = new Mapa();

        int resultadoEsperado = 45;

        mapa.inserir(2, aragorn);
        mapa.inserir(4, urukhai);

        aragorn.atacar(mapa, mapa.buscarPosicao(aragorn));

        Assert.assertEquals(resultadoEsperado, urukhai.getConstituicao());
    }

    @Test
    public void deveAndarParaDireitaOMagoSociedadeSeNaoTiverNinguem() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "|G| | | | | | | | | |"

        Gandalf gandalf = new Gandalf();
        Mapa mapa = new Mapa();

        int resultadoEsperado = 1;

        mapa.inserir(0, gandalf);

        gandalf.atacar(mapa);
        gandalf.movimentacao(mapa, mapa.buscarPosicao(gandalf));

        Assert.assertEquals(resultadoEsperado, mapa.buscarPosicao(gandalf));
    }

    @Test
    public void deveAndarParaEsquerdaOMagoNaoSociedadeSeNaoTiverNinguem() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "| | | | | | | | | |S|"

        Saruman saruman = new Saruman();
        Mapa mapa = new Mapa();

        int resultadoEsperado = 8;

        mapa.inserir(9, saruman);

        saruman.atacar(mapa);
        saruman.movimentacao(mapa, mapa.buscarPosicao(saruman));

        Assert.assertEquals(resultadoEsperado, mapa.buscarPosicao(saruman));
    }

    @Test
    public void deveManterPosicaoOMagoSeTiverPersonagemNoMapa() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "|A| | | | | | | | |S|"

        Saruman saruman = new Saruman();
        Aragorn aragorn = new Aragorn();
        Mapa mapa = new Mapa();

        int resultadoEsperado = 9;

        mapa.inserir(0, aragorn);
        mapa.inserir(9, saruman);

        saruman.atacar(mapa);
        saruman.movimentacao(mapa, mapa.buscarPosicao(saruman));

        Assert.assertEquals(resultadoEsperado, mapa.buscarPosicao(saruman));
    }

    @Test
    public void deveDarDanoOMagoEmInimigo() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "|A| | | | | | | | |S|"

        Saruman saruman = new Saruman();
        Aragorn aragorn = new Aragorn();
        Mapa mapa = new Mapa();

        int resultadoEsperado = 51;

        mapa.inserir(0, aragorn);
        mapa.inserir(9, saruman);

        saruman.atacar(mapa);
        saruman.movimentacao(mapa, mapa.buscarPosicao(saruman));

        Assert.assertEquals(resultadoEsperado, aragorn.getConstituicao());
    }

    @Test
    public void deveDarDanoOMagoEmTodosOsInimigos() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "|A|G|I| | | | | | |S|"

        Saruman saruman = new Saruman();
        Aragorn aragorn = new Aragorn();
        Gandalf gandalf = new Gandalf();
        Gimli gimli = new Gimli();
        Mapa mapa = new Mapa();

        int resultadoEsperadoAragorn = 51;
        int resultadoEsperadoGandalf = 71;
        int resultadoEsperadoGimli = 51;

        mapa.inserir(0, aragorn);
        mapa.inserir(1, gandalf);
        mapa.inserir(2, gimli);
        mapa.inserir(9, saruman);

        saruman.atacar(mapa);
        saruman.movimentacao(mapa, mapa.buscarPosicao(saruman));

        Assert.assertEquals(resultadoEsperadoAragorn, aragorn.getConstituicao());
        Assert.assertEquals(resultadoEsperadoGandalf, gandalf.getConstituicao());
        Assert.assertEquals(resultadoEsperadoGimli, gimli.getConstituicao());
    }

    @Test
    public void deveManterConstituicaoDosAliadosQuandoMagoAtacar() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "|A| | | | |G| | | | |"

        Aragorn aragorn = new Aragorn();
        Gandalf gandalf = new Gandalf();
        Mapa mapa = new Mapa();

        int resultadoEsperado = 60;

        mapa.inserir(0, aragorn);
        mapa.inserir(5, gandalf);

        gandalf.atacar(mapa);
        gandalf.movimentacao(mapa, mapa.buscarPosicao(gandalf));

        Assert.assertEquals(resultadoEsperado, aragorn.getConstituicao());
    }

    @Test
    public void deveDescontarConstituicaoDoInimigoQuandoArqueiroSociedadeAtacar3Posicao() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "|L| | |S| | | | | | |"

        Legolas legolas = new Legolas();
        Saruman saruman = new Saruman();
        Mapa mapa = new Mapa();

        int resultadoEsperado = 40;

        mapa.inserir(0, legolas);
        mapa.inserir(3, saruman);

        legolas.atacar(mapa, mapa.buscarPosicao(legolas));
        legolas.movimentacao(mapa, mapa.buscarPosicao(legolas));

        Assert.assertEquals(resultadoEsperado, saruman.getConstituicao());
    }

    @Test
    public void deveDescontarConstituicaoDoInimigoQuandoArqueiroSociedadeAtacar2Posicao() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "|L| |S| | | | | | | |"

        Legolas legolas = new Legolas();
        Saruman saruman = new Saruman();
        Mapa mapa = new Mapa();

        int resultadoEsperado = 50;

        mapa.inserir(0, legolas);
        mapa.inserir(2, saruman);

        legolas.atacar(mapa, mapa.buscarPosicao(legolas));
        legolas.movimentacao(mapa, mapa.buscarPosicao(legolas));

        Assert.assertEquals(resultadoEsperado, saruman.getConstituicao());
    }

    @Test
    public void deveDescontarConstituicaoDoInimigoQuandoArqueiroSociedadeAtacar1Posicao() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "|L|S| | | | | | | | |"

        Legolas legolas = new Legolas();
        Saruman saruman = new Saruman();
        Mapa mapa = new Mapa();

        int resultadoEsperado = 60;

        mapa.inserir(0, legolas);
        mapa.inserir(1, saruman);

        legolas.atacar(mapa, mapa.buscarPosicao(legolas));
        legolas.movimentacao(mapa, mapa.buscarPosicao(legolas));

        Assert.assertEquals(resultadoEsperado, saruman.getConstituicao());
    }

    @Test
    public void deveDescontarConstituicaoDoInimigoQuandoArqueiroNaoSociedadeAtacar3Posicao() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "| | | | | | |G| | |M|"

        Goblim goblim = new Goblim();
        Gandalf gandalf = new Gandalf();
        Mapa mapa = new Mapa();

        int resultadoEsperado = 62;

        mapa.inserir(9, goblim);
        mapa.inserir(6, gandalf);

        goblim.atacar(mapa, mapa.buscarPosicao(goblim));
        goblim.movimentacao(mapa, mapa.buscarPosicao(goblim));

        Assert.assertEquals(resultadoEsperado, gandalf.getConstituicao());
    }

    @Test
    public void deveDescontarConstituicaoDoInimigoQuandoArqueiroNaoSociedadeAtacar2Posicao() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "| | | | | | | |G| |M|"

        Goblim goblim = new Goblim();
        Gandalf gandalf = new Gandalf();
        Mapa mapa = new Mapa();

        int resultadoEsperado = 68;

        mapa.inserir(9, goblim);
        mapa.inserir(7, gandalf);

        goblim.atacar(mapa, mapa.buscarPosicao(goblim));
        goblim.movimentacao(mapa, mapa.buscarPosicao(goblim));

        Assert.assertEquals(resultadoEsperado, gandalf.getConstituicao());
    }

    @Test
    public void deveDescontarConstituicaoDoInimigoQuandoArqueiroNaoSociedadeAtacar1Posicao() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "| | | | | | | | |G|M|"

        Goblim goblim = new Goblim();
        Gandalf gandalf = new Gandalf();
        Mapa mapa = new Mapa();

        int resultadoEsperado = 74;

        mapa.inserir(9, goblim);
        mapa.inserir(8, gandalf);

        goblim.atacar(mapa, mapa.buscarPosicao(goblim));
        goblim.movimentacao(mapa, mapa.buscarPosicao(goblim));

        Assert.assertEquals(resultadoEsperado, gandalf.getConstituicao());
    }

    @Test
    public void deveDescontarConstituicaoDoInimigoMaisDistanteQuandoArqueiroAtacar() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "|L| |M|S| | | | | | |"

        Legolas legolas = new Legolas();
        Saruman saruman = new Saruman();
        Goblim goblim = new Goblim();
        Mapa mapa = new Mapa();

        int resultadoEsperadoSaruman = 40;
        int resultadoEsperadoGoblim = 20;

        mapa.inserir(0, legolas);
        mapa.inserir(2, goblim);
        mapa.inserir(3, saruman);

        legolas.atacar(mapa, mapa.buscarPosicao(legolas));
        legolas.movimentacao(mapa, mapa.buscarPosicao(legolas));

        Assert.assertEquals(resultadoEsperadoSaruman, saruman.getConstituicao());
        Assert.assertEquals(resultadoEsperadoGoblim, goblim.getConstituicao());
    }

    @Test
    public void deveDescontarConstituicaoDoInimigoMaisDistanteQueConseguirQuandoArqueiroAtacarETiverMaisDeUmInimigo() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "|L|M| | |S| | | | | |"

        Legolas legolas = new Legolas();
        Saruman saruman = new Saruman();
        Goblim goblim = new Goblim();
        Mapa mapa = new Mapa();

        int resultadoEsperadoSaruman = 70;
        int resultadoEsperadoGoblim = 10;

        mapa.inserir(0, legolas);
        mapa.inserir(1, goblim);
        mapa.inserir(4, saruman);

        legolas.atacar(mapa, mapa.buscarPosicao(legolas));
        legolas.movimentacao(mapa, mapa.buscarPosicao(legolas));

        Assert.assertEquals(resultadoEsperadoSaruman, saruman.getConstituicao());
        Assert.assertEquals(resultadoEsperadoGoblim, goblim.getConstituicao());
    }

    @Test
    public void deveManterConstituicaoDosAliadosQuandoArqueiroSociedadeAtacar() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "|L|A|G| | | | | | | |"

        Legolas legolas = new Legolas();
        Aragorn aragorn = new Aragorn();
        Gandalf gandalf = new Gandalf();
        Mapa mapa = new Mapa();

        int resultadoEsperadoAragorn = 60;
        int resultadoEsperadoGandalf = 80;

        mapa.inserir(0, legolas);
        mapa.inserir(1, aragorn);
        mapa.inserir(2, gandalf);

        legolas.atacar(mapa, mapa.buscarPosicao(legolas));
        legolas.movimentacao(mapa, mapa.buscarPosicao(legolas));

        Assert.assertEquals(resultadoEsperadoAragorn, aragorn.getConstituicao());
        Assert.assertEquals(resultadoEsperadoGandalf, gandalf.getConstituicao());
    }

    @Test
    public void deveManterConstituicaoDosAliadosQuandoArqueiroNaoSociedadeAtacar() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "| | | | | | | |S|O|M|"

        Goblim goblim = new Goblim();
        Orc orc = new Orc();
        Saruman saruman = new Saruman();
        Mapa mapa = new Mapa();

        int resultadoEsperadoOrc = 30;
        int resultadoEsperadoSaruman = 70;

        mapa.inserir(9, goblim);
        mapa.inserir(8, orc);
        mapa.inserir(7, saruman);

        goblim.atacar(mapa, mapa.buscarPosicao(goblim));
        goblim.movimentacao(mapa, mapa.buscarPosicao(goblim));

        Assert.assertEquals(resultadoEsperadoOrc, orc.getConstituicao());
        Assert.assertEquals(resultadoEsperadoSaruman, saruman.getConstituicao());
    }

    @Test
    public void deveAvancar2CasasQuandoEstiverDisponivelParaArqueiro() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "|L| | | |S| | | | | |"

        Legolas legolas = new Legolas();
        Saruman saruman = new Saruman();
        Mapa mapa = new Mapa();

        int resultadoEsperado = 2;

        mapa.inserir(0, legolas);
        mapa.inserir(4, saruman);

        legolas.movimentacao(mapa, mapa.buscarPosicao(legolas));

        Assert.assertEquals(resultadoEsperado, mapa.buscarPosicao(legolas));
    }

    @Test
    public void deveAvancarApenas1CasaQuandoNaoEstiverDisponivelParaArqueiro() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {
        // Mapa:  "|L| |S| | | | | | | |"

        Legolas legolas = new Legolas();
        Saruman saruman = new Saruman();
        Mapa mapa = new Mapa();

        int resultadoEsperado = 1;

        mapa.inserir(0, legolas);
        mapa.inserir(2, saruman);

        legolas.movimentacao(mapa, mapa.buscarPosicao(legolas));

        Assert.assertEquals(resultadoEsperado, mapa.buscarPosicao(legolas));
    }

    @Test
    public void deveVencerSociedadeQuandoAragornELegolasBatalharemContraOrcEGoblim() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, SauronDominaOMundoException, PersonagemNaoEncontradoNoMapaException {
        // Início:  "|A|L| | | | | |O| |M|"
        String resultadoEsperado = "| | | | |A| | | | |L|";

        Aragorn aragorn = new Aragorn();
        Legolas legolas = new Legolas();
        Orc orc = new Orc();
        Goblim goblim = new Goblim();
        Mapa mapa = new Mapa();
        Simulador simulador = new Simulador(mapa);

        mapa.inserir(0, aragorn);
        mapa.inserir(1, legolas);
        mapa.inserir(7, orc);
        mapa.inserir(9, goblim);
        simulador.simular();

        Assert.assertEquals(resultadoEsperado, mapa.exibir());
    }

    @Test(expected = SauronDominaOMundoException.class)
    public void deveLancarSauronDominaOMundoExceptionQuandoInimigosDerrotaremMembrosDaSociedade() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, SauronDominaOMundoException, PersonagemNaoEncontradoNoMapaException {
        // Início: "|A| |I| | | | |U|O|G|"
        // Fim:    "| | | | | | |O|M| | |"

        Aragorn aragorn = new Aragorn();
        Gimli gimli = new Gimli();
        Urukhai urukhai = new Urukhai();
        Orc orc = new Orc();
        Goblim goblim = new Goblim();
        Mapa mapa = new Mapa();
        Simulador simulador = new Simulador(mapa);

        mapa.inserir(0, aragorn);
        mapa.inserir(2, gimli);
        mapa.inserir(7, urukhai);
        mapa.inserir(8, orc);
        mapa.inserir(9, goblim);
        simulador.simular();
    }

    @Test
    public void deveVencerSociedadeQuandoGandalfBatalharSozinhoContraSaruman() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, SauronDominaOMundoException, PersonagemNaoEncontradoNoMapaException {
        // Início:  "|G| | | | | | | | |S|"
        String resultadoEsperado = "| | | | | | | | | |G|";

        Gandalf gandalf = new Gandalf();
        Saruman saruman = new Saruman();
        Mapa mapa = new Mapa();
        Simulador simulador = new Simulador(mapa);

        mapa.inserir(0, gandalf);
        mapa.inserir(9, saruman);
        simulador.simular();

        Assert.assertEquals(resultadoEsperado, mapa.exibir());
    }

    @Test(expected = SauronDominaOMundoException.class)
    public void deveLancarSauronDominaOMundoExceptionQuandoLegolasBatalharSozinhoContraOrcEUrukhai() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, SauronDominaOMundoException, PersonagemNaoEncontradoNoMapaException {
        // Início:  "|L| | | | | | | |U|O|"
        // Fim:     "| | | | | |U| | | | |";

        Legolas legolas = new Legolas();
        Orc orc = new Orc();
        Urukhai urukhai = new Urukhai();
        Mapa mapa = new Mapa();
        Simulador simulador = new Simulador(mapa);

        mapa.inserir(0, legolas);
        mapa.inserir(8, urukhai);
        mapa.inserir(9, orc);
        simulador.simular();
    }

    @Test(expected = SauronDominaOMundoException.class)
    public void deveLancarSauronDominaOMundoExceptionQuandoBoromirBatalharSozinhoContraUrukhai() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, SauronDominaOMundoException, PersonagemNaoEncontradoNoMapaException {
        // Início:  "|B| | | | | | | | |U|"
        // Fim:     "| | | | |U| | | | | |";

        Boromir boromir = new Boromir();
        Urukhai urukhai = new Urukhai();
        Mapa mapa = new Mapa();
        Simulador simulador = new Simulador(mapa);

        mapa.inserir(0, boromir);
        mapa.inserir(9, urukhai);
        simulador.simular();
    }

    @Test(expected = PersonagemJaEstaNoMapaException.class)
    public void deveLancarPersonagemJaEstaNoMapaExceptionQuandoInserirPersonagemQueJaEstaNoMapa() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException {

        Aragorn aragorn = new Aragorn();
        Mapa mapa = new Mapa();

        mapa.inserir(0, aragorn);
        mapa.inserir(1, aragorn);
    }

    @Test(expected = PersonagemNaoEncontradoNoMapaException.class)
    public void deveLancarPersonagemNaoEncontradoNoMapaExceptionQuandoNaoTiverOPersonagemNoMapa() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, PersonagemNaoEncontradoNoMapaException {

        Aragorn aragorn = new Aragorn();
        Mapa mapa = new Mapa();

        mapa.inserir(0, aragorn);
        mapa.remover(0);

        mapa.buscarPosicao(aragorn);
    }

    @Test(expected = PosicaoOcupadaException.class)
    public void deveLancarPosicaoOcupadaExceptionQuandoAPosicaoJaEstiverOcupada() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException {

        Aragorn aragorn = new Aragorn();
        Legolas legolas = new Legolas();
        Mapa mapa = new Mapa();

        mapa.inserir(0, aragorn);
        mapa.inserir(0, legolas);
    }

    @Test
    public void deveIgnorarInimigo2DoGuerreiroQuandoPosicaoMaiorQue9() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException, SauronDominaOMundoException, PersonagemNaoEncontradoNoMapaException {

        Aragorn aragorn = new Aragorn();
        Saruman saruman = new Saruman();
        Mapa mapa = new Mapa();
        Simulador simulador = new Simulador(mapa);
        int posicaoEsperada = 9;

        mapa.inserir(8, saruman);
        mapa.inserir(9, aragorn);

        simulador.simular();

        Assert.assertEquals(posicaoEsperada, mapa.buscarPosicao(aragorn));
    }

    @Test
    public void deveTestarTodasFalasQuandoAcionado() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException {

        Aragorn aragorn = new Aragorn();
        Boromir boromir = new Boromir();
        Gandalf gandalf = new Gandalf();
        Gimli gimli = new Gimli();
        Goblim goblim = new Goblim();
        Legolas legolas = new Legolas();
        Orc orc = new Orc();
        Saruman saruman = new Saruman();
        Urukhai urukhai = new Urukhai();
        Mapa mapa = new Mapa();

        mapa.inserir(0, aragorn);
        mapa.inserir(1, boromir);
        mapa.inserir(2, gandalf);
        mapa.inserir(3, gimli);
        mapa.inserir(4, goblim);
        mapa.inserir(5, legolas);
        mapa.inserir(6, orc);
        mapa.inserir(7, saruman);
        mapa.inserir(8, urukhai);

        gimli.falar();
        gimli.beber();
        gimli.beber();
        gimli.beber();
        gimli.beber();
        gimli.falar();
        urukhai.envelhecer();
        urukhai.falar();
        urukhai.grunir();
        gandalf.setConstituicao(0);
        gandalf.ressucitar();
        gandalf.falar();
        aragorn.envelhecer();
        aragorn.falar();
        boromir.envelhecer();
        boromir.falar();
        legolas.falarElfico();
        legolas.falar();
        saruman.falar();
        saruman.ressucitar();
        goblim.grunir();
        orc.grunir();
    }

    @Test
    public void deveRetornarNullQuandoNaoTiverPersonagemNaCasaInformada() throws PosicaoOcupadaException, PersonagemJaEstaNoMapaException {
        Mapa mapa = new Mapa();
        Aragorn aragorn = new Aragorn();
        Personagem resultadoEsperadoForaDeRange = null;

        mapa.inserir(9, aragorn);

        Assert.assertEquals(resultadoEsperadoForaDeRange, mapa.buscarCasa(10));
        Assert.assertEquals(aragorn, mapa.buscarCasa(9));
    }

    @Test
    public void deveManterConstituicaoQuandoSetadoParaMaior(){
        Aragorn aragorn = new Aragorn();
        int resultadoEsperado = 60;

        aragorn.setConstituicao(80);

        Assert.assertEquals(resultadoEsperado, aragorn.getConstituicao());
    }
}
