package servidor.DTO;

import java.io.Serializable;

public class CancionDTO implements Serializable
{	
    private int id;
    private String tipo;
    private String artista;
    private String titulo;
    private int tamKB;
    private byte[] arrayBytes;

    public CancionDTO(String tipo, String artista, String titulo, int tamKB) {
        this.artista = artista;
        this.titulo = titulo;
        this.tamKB = tamKB;       
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    
    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getTamKB() {
        return tamKB;
    }

    public void setTamKB(int tamKB) {
        this.tamKB = tamKB;
    }

    public byte[] getArrayBytes() {
        return arrayBytes;
    }

    public void setArrayBytes(byte[] arrayBytes) {
        this.arrayBytes = arrayBytes;
    }   
}
