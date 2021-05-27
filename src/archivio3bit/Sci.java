package archivio3bit;

/**
 * Classe utilizzata per l'oggetto da contenere nella collezione.
 * Contiene le variabili di istanza, il metodo costruttore, i setter/getter
 * e eventuali altri metodi
 * @author melissa
 */
public class Sci {
    
    private String codice;
    private int quantita;
    private float prezzo;
    private String marca;
    private float lunghezza;
    private String colore;

    public Sci(String codice, int quantita, float prezzo, String marca, float lunghezza, String colore) {
        this.codice = codice;
        this.quantita = quantita;
        this.prezzo = prezzo;
        this.marca = marca;
        this.lunghezza = lunghezza;
        this.colore = colore;
    }

    public String getCodice() {
        return codice;
    }

    public int getQuantita() {
        return quantita;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public String getMarca() {
        return marca;
    }

    public float getLunghezza() {
        return lunghezza;
    }

    public String getColore() {
        return colore;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setLunghezza(float lunghezza) {
        this.lunghezza = lunghezza;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }
    
    @Override
    public String toString() {
        String s;
        s = codice;
        s += ",";
        s += Integer.toString (quantita) ;
        s += ",";
        s += Float.toString (prezzo);
        s += ",";
        s += marca;
        s += ",";
        s += Float.toString (lunghezza);
        s += ",";
        s += colore;
        return s;
    }
    
}

