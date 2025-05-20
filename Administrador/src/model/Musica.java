package model;

public class Musica {
    private int idMusica;
    private String nomeMusica;
    private Artista artista;
    private String genero; 
    private int curtidas;
    private int descurtidas;
    private String duracao;

    public Musica(int idMusica, String nomeMusica, int curtidas, int descurtidas, String duracao, Artista artista, String genero) {
        this.idMusica = idMusica;
        this.nomeMusica = nomeMusica;
        this.curtidas = curtidas;
        this.descurtidas = descurtidas;
        this.duracao = duracao;
        this.artista = artista;
        this.genero = genero;
    }

    public Musica(String nomeMusica, int curtidas, int descurtidas, String duracao, Artista artista, String genero) {
        this(-1, nomeMusica, curtidas, descurtidas, duracao, artista, genero);
    }
    
    public Musica(String nomeMusica, int curtidas, int descurtidas, String duracao){
        this.nomeMusica = nomeMusica;
        this.curtidas = curtidas;
        this.descurtidas = descurtidas;
        this.duracao = duracao;
    }

    public int getIdMusica() {
        return idMusica;
    }

    public void setIdMusica(int idMusica) {
        this.idMusica = idMusica;
    }

    public String getNomeMusica() {
        return nomeMusica;
    }

    public void setNomeMusica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }

    public int getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(int curtidas) {
        this.curtidas = curtidas;
    }

    public int getDescurtidas() {
        return descurtidas;
    }

    public void setDescurtidas(int descurtidas) {
        this.descurtidas = descurtidas;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String infoMusicasCompleta() {
        return  " Id: " + idMusica +
                "\n Nome: " + nomeMusica +
                "\n Artista: " + artista.getNome() +
                "\n Gênero: " + genero +
                "\n Curtidas: " + curtidas +
                "\n Descurtidas: " + descurtidas +
                "\n Duração: " + duracao +
                "\n------------------------\n";
    }
    
    public String infoMusicas() {
        return  " Nome: " + nomeMusica +
                "\n Curtidas: " + curtidas +
                "\n Descurtidas: " + descurtidas +
                "\n Duração: " + duracao +
                "\n------------------------\n";
    }

    @Override
    public String toString() {
        return nomeMusica + " - " + artista.getNome() + " (" + genero + ")";
    }
}
