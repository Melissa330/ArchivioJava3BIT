package archivio3bit;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Si occupa dell'interfaccia utente con un menu.
 * Si può usare println.
 * @author melissa
 */

public class Archivio3bit {

  public static void main(String[] args) {

    // Creo il archivio
    Archivio archivio;
    archivio = new Archivio();

    Scanner tastiera;
    tastiera = new Scanner(System.in);

    int scelta = 0;

    do {
      System.out.println("--- Negozio sci ---");
      System.out.println("1) Elenco sci");
      System.out.println("2) Inserisci sci");
      System.out.println("3) Elimina sci");
      System.out.println("4) Cerca sci in base al prezzo");
      System.out.println("5) Cerca sci in base alla marca");
      System.out.println("6) Modifica sci");
      System.out.println("7) Esci");
      System.out.print("Scegli l'operazione (1-7): ");

      scelta = Integer.parseInt(tastiera.nextLine()); 

      switch (scelta) {
        case 1:
          // Chiedo al archivio l'elenco di tutte le articolo
          ArrayList<Sci> elencoScii;
          elencoScii = archivio.elencoScii();

          visualizzaElencoScii(elencoScii);
          break;

        case 2:
          // 1. Chiedo all'utente i dati del nuovo articolo da inserire         
          System.out.print("Inserisci il codice: ");
          String codice = tastiera.nextLine();
          System.out.print("Inserisci la quantità: ");
          int quantita = Integer.parseInt(tastiera.nextLine());
          System.out.print("Inserisci il prezzo: ");
          float prezzo = Float.parseFloat(tastiera.nextLine());
          System.out.print("Inserisci la marca: ");
          String marca = tastiera.nextLine();
          System.out.print("Inserisci la lunghezza: ");
          float lunghezza = Float.parseFloat(tastiera.nextLine());
          System.out.print("Inserisci il colore: ");
          String colore = tastiera.nextLine();

          // 2. Creo un oggetto articolo con i dati forniti dall'utente
          Sci sci = new Sci(codice, quantita, prezzo, marca, lunghezza, colore);

          // 3. Aggiungo larticolo nel archivio
          if (archivio.aggiungiSci(sci)) {
            System.out.print("Articolo inserito correttamente");
          } else {
            System.out.print("Esiste già un articolo con lo stesso codice !");
          }
          break;

        case 3:
          // 1. Chiedo all'utente il codice dell'articolo da eliminare         
          System.out.print("Inserisci il codice: ");
          codice = tastiera.nextLine();

          // 2. Elimino la articolo dal archivio         
          if (archivio.elimina(codice)) {
            System.out.println("Articolo eliminato correttamente.");
          } else {
            System.out.println("Eliminazione non riuscita.");
          }
          break;

        case 4:
          System.out.print("Inserisci il prezzo minimo: ");
          float prezzoMinimo = Float.parseFloat(tastiera.nextLine());
          System.out.print("Inserisci il prezzo massimo: ");
          float prezzoMassimo = Float.parseFloat(tastiera.nextLine());

          elencoScii = archivio.cercaSci(prezzoMinimo, prezzoMassimo);

          visualizzaElencoScii(elencoScii);
          
          break;

        case 5:
          System.out.print("Inserisci la marca: ");
          marca = tastiera.nextLine();

          elencoScii = archivio.cercaSciPerMarca(marca);

          visualizzaElencoScii(elencoScii);
          break;
          
        case 6:
          System.out.print("Inserisci il codice: ");
          codice = tastiera.nextLine();
          System.out.print("Inserisci la quantità: ");
          quantita = Integer.parseInt(tastiera.nextLine());
          System.out.print("Inserisci il prezzo: ");
          prezzo = Float.parseFloat(tastiera.nextLine());
          System.out.print("Inserisci la marca: ");
          marca = tastiera.nextLine();
          System.out.print("Inserisci la lunghezza: ");
          lunghezza = Float.parseFloat(tastiera.nextLine());
          System.out.print("Inserisci il colore: ");
          colore = tastiera.nextLine();


          if (archivio.modificaSci(codice,quantita, prezzo, marca, lunghezza, colore )) {
            System.out.println("Modifica sci avvenuta correttamente.");
          } else {
            System.out.println("Non è stato possibile modificare gli sci.");
          }
          break;
      }

    } while (scelta != 7);

  }

  private static void visualizzaElencoScii(ArrayList<Sci> elencoScii) {
    System.out.println("N°\tCodice\t\tQuantità\tPrezzo\t\tMarca\t\tLunghezza\tColore");
    for (int i = 0; i < elencoScii.size(); i++) {
      
			System.out.print(i + 1);
            System.out.print("\t" + elencoScii.get(i).getCodice());
            System.out.print("\t\t" + elencoScii.get(i).getQuantita());
            System.out.print("\t\t" + elencoScii.get(i).getPrezzo());
            System.out.print("\t\t" + elencoScii.get(i).getMarca());
            System.out.print("\t\t" + elencoScii.get(i).getLunghezza());
            System.out.print("\t\t" + elencoScii.get(i).getColore());
            System.out.print("\n");
    }

    System.out.println("\n");

  }

}


