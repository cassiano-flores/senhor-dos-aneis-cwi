package personagens;

public abstract class Personagem {

    protected int forca;
    protected int agilidade;
    protected int inteligencia;
    protected int constituicao;
    private boolean sociedadeDoAnel;

    public Personagem(int forca, int agilidade, int inteligencia, int constituicao, boolean sociedadeDoAnel) {
        this.forca = forca;
        this.agilidade = agilidade;
        this.inteligencia = inteligencia;
        this.constituicao = constituicao;
        this.sociedadeDoAnel = sociedadeDoAnel;
    }

//    public int getForca() {
//        return forca;
//    }
//
//    public int getAgilidade() {
//        return agilidade;
//    }
//
//    public int getInteligencia() {
//        return inteligencia;
//    }

    public int getConstituicao() {
        return constituicao;
    }

    public void setConstituicao(int constituicao) {
        if ((constituicao <= this.constituicao) && (constituicao >= 0))
            this.constituicao = constituicao;
    }

    public boolean isSociedadeDoAnel() {
        return sociedadeDoAnel;
    }

    public void sofrerDano(Personagem personagem, int dano){
        personagem.constituicao -= dano;
    }
}
