package archivio3bit;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contiene gli oggetti del programma.
 * Implementata tramite un ArrayList.
 * Ci permette di inserire gli oggetti, effettuare ricerche e
 * aggiungere/rimuovere/modificare un oggetto.
 * @author melissa
 */

public class Archivio {

  private ArrayList<Sci> archivio;

  public Archivio() {
    
    archivio = new ArrayList(0);
    archivio = leggiDaFile();
  }

  // Metodo che restituisce l'elenco degli articoli
  public ArrayList<Sci> elencoScii() {
    return archivio;
  }

  public boolean aggiungiSci(Sci sci) {
    // controllo che non ci sia già un articolo con lo stesso 
    // codice
    if (Archivio.this.cercaSci(sci.getCodice()) != null) {
      return false;
    }

    archivio.add(sci);
    salvaSuFile();
    return true;
  }

  private Sci cercaSci(String codice) {
    for (int i = 0; i < archivio.size(); i++) {
      if (archivio.get(i).getCodice().equals(codice)) {
        return archivio.get(i);
      }
    }
    return null;
  }

  public boolean elimina(String codice) {
    Sci sci;
    // Ricerco l'articolo
    sci = Archivio.this.cercaSci(codice);

    // Se l'articolo c'è lo cancello
    if (sci != null) {
      // Cancello l'articolo dall'array list
      archivio.remove(sci);
      //Salvo tutto nel file
      salvaSuFile();
      return true;
    }
    return false;
  }

  public boolean modificaSci(String codice, int quantita, float prezzo, String marca, float lunghezza, String colore ) {

    // Cerco l'articolo in base codice.
    Sci sci;
    sci = Archivio.this.cercaSci(codice);
    // Se è presente nell'archivio modifico le sue caratteristiche
    if (sci != null) {
      sci.setQuantita(quantita);
      sci.setPrezzo(prezzo);
      sci.setMarca(marca);
      sci.setLunghezza(lunghezza);
      sci.setColore(colore);

      // Salvo l'archivio su file
      salvaSuFile();
      return true;
    } else {
      return false;
    }
  }

  public ArrayList<Sci> cercaSci(float prezzoMinimo, float prezzoMassimo) {

    ArrayList<Sci> sciTrovati = new ArrayList(0);

    for (int i = 0; i < archivio.size(); i++) {
      if (archivio.get(i).getPrezzo() >= prezzoMinimo
              && archivio.get(i).getPrezzo() <= prezzoMassimo) {
        // Aggiungo l'articolo in posizione i nell'array list degli articoli trovati
        sciTrovati.add(archivio.get(i));
      }
    }
    return sciTrovati;
  }

  // Metodo per cercare le articolo in base al modello
  public ArrayList<Sci> cercaSciPerMarca(String marca) {

    ArrayList<Sci> sciTrovati = new ArrayList(0);

    // Converto la descrizione da cercare in minuscolo
    String marcaDaCercareMinuscolo = marca.toLowerCase();

    for (int i = 0; i < archivio.size(); i++) {
      // Converto la descrizione dell'articolo in posizione i in minucolo
      String marcaMinuscolo = archivio.get(i).getMarca().toLowerCase();

      if (marcaMinuscolo.contains(marcaDaCercareMinuscolo)) {
        // Aggiungo l'articolo in posizione i nell'ArrayList degli
        // articoli trovati.
        sciTrovati.add(archivio.get(i));
      }
    }

    // Restituisco l'ArrayList dell'articolo trovato.
    return sciTrovati;
  }
  
  public String toString() {
    //stringa vuota
    String s = "";

    for (int i = 0; i < archivio.size(); i++) {
      Sci sci;
      // estraggo l'articolo in posizione i
      sci = archivio.get(i);

      // aggiungo l'articolo alla stringa
      s += sci.toString();

      // dopo aver aggiunto un articolo dobbiamo inserire un ritorno a capo
      //\r torna all'inizio
      s += "\r\n";
    }
    return s;
  }

  // Metodo che salva i dati nel file archivio.txt
  public void salvaSuFile() {
    //classe di java
    FileWriter out;
    //try e catch eccezioni
    try {
      //metto l'archivio dentro ad out per modificarlo
      out = new FileWriter("archivio.txt");

      // Ci facciamo dare dal metodo toString la stringa che rappresenta tutto
      //l'archivio
      String sArchivio;
      sArchivio = toString();

      // Scrivo nel file la stringa ricevuta
      out.write(sArchivio);

      // Chiudo il file
      out.close();

    } catch (IOException ex) {
      Logger.getLogger(Archivio.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private ArrayList<Sci> leggiDaFile() {
    //creo un array list di articoli vuoto
    ArrayList<Sci> scii;
    scii = new ArrayList(0);

    FileReader fileReader;
    try {
      fileReader = new FileReader("archivio.txt");

      // Creo un oggetto BufferedReader, in quanto contiene il metodo
      // readLine(), passandogli l'oggetto FileReader creato prima
      //per leggere tutta la riga
      BufferedReader in;
      in = new BufferedReader(fileReader);

      String linea;
      String campi[];
      while ((linea = in.readLine()) != null) { //readLine legge ogni linea
        //divido con una virgola
        campi = linea.split(",");
        
        int quantita = Integer.parseInt(campi[1]); //casting
        float prezzo = Float.parseFloat(campi[2]);
        float lunghezza = Float.parseFloat(campi[4]);

        // Adesso ho i dati necessari per costruite un oggetto
        // Articolo
        Sci sci = new Sci(campi[0], quantita, prezzo, campi[3], lunghezza, campi[5]);

        // aggiungo l'articolo all'ArrayList
        scii.add(sci);
      }

    } catch (FileNotFoundException ex) {
      Logger.getLogger(Archivio.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(Archivio.class.getName()).log(Level.SEVERE, null, ex);
    }
    return scii;
  }
}

