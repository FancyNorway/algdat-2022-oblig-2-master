package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {

    }

    public DobbeltLenketListe(T[] a) {

        Objects.requireNonNull(a, "Tabellen a er null!");

        if (a.length == 0) return;

        hode = new Node<T>(null, null, null);
        hale = new Node<T>(null, null, null);
        Node<T> current = hode;

        for (int i = 0; i < a.length; i++) {
            if (a[i] != null) {
                // Lager en ny node med verdi a[i]
                Node nyNode = new Node<T>(a[i]);

                // Legger til neste
                current.neste = nyNode;

                // Legger til forrige
                if (i != 0) {
                    nyNode.forrige = current;
                }

                // Sjekker om vi er på siste Node og Oppdaterer current
                if (i == a.length - 1) {
                    nyNode.neste = null;
                }
                current = current.neste;
            }

        }
        hale.forrige = current;
        this.antall = a.length;
    }

    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int antall() {
        if(tom()){
            return antall = 0;
        }
        int antall = 0;
        Node current = hode;

        while(current.neste != null){
            antall++;
            current = current.neste;

        }
        return antall;
    }



    @Override
    public boolean tom() {
        if(hode == null){
            return true;
        }
        return false;
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Dette er en null-verdi");
        if (tom()){
            hode = new Node<T>(null, null, null);
            hale = new Node<T>(null, null, null);
            Node nyNode = new Node<T>(verdi);
            nyNode.forrige = hode;
            nyNode.neste = hale;
            hode.neste = nyNode;
            hale.forrige = nyNode;
            return true;
        }
        Node temp = hale.forrige;
        Node nyNode = new Node<T>(verdi);
        temp.neste = nyNode;
        nyNode.forrige = temp;
        hale.forrige = nyNode;
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(indeks, "Indeks skal ikke være en null-verdi");
        Objects.requireNonNull(verdi, "Verdi skal ikke være en null-verdi");
        antall();
        if (indeks < 0 || indeks > antall) throw
            new IndexOutOfBoundsException("Indexen må være i intervallet [0, antall]");

        // Listen er tom
        if (tom()) {
            leggInn(verdi);
            return;
        }

        // Verdien skal legges først
        if (indeks == 0)
        {

            return;
        }

        // Verdien skal legges bakerst
        if (indeks == antall)
        {

            return;
        }

        // verdien skal legges mellom to andre verdier



    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) < 0 ? false : true;
    }

    private Node <T> finnNode(int indeks){

        if (indeks < antall/2){
            Node current = hode;
            int counter = 0;
            while(counter < indeks){
                current = current.neste;
                counter++;
            }
            return current;

        }
        Node current = hale;
        while (antall > indeks){
            current = current.forrige;
            antall--;
        }
        return current;
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
    }

    @Override
    public int indeksTil(T verdi) {
        if (verdi == null) return -1;
        int index = 0;

        if (hode == null) return -1;
        Node current = hode;

        while (current.neste != null)
        {
            current = current.neste;
            if ((current.verdi).equals(verdi)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append('[');

        // Starter på hode og sjekker om listen er tom
        Node current = hode;
        int listeAntall = antall();
        if (listeAntall == 0) {
            s.append(']');
            return s.toString();
        }

        // Går til første element
        current = current.neste;

        // Looper gjennom
        for (int i = 0; i < listeAntall; i++){
            if (current.verdi != null) {
                s.append(current.verdi);
            }
            if (current.neste != null) {
                current = current.neste;
                if (current.verdi != null) {
                    s.append(','); s.append(' ');
                }
            }
        }
        s.append(']');
        return s.toString();
    }

    public String omvendtString() {
        StringBuilder s = new StringBuilder();
        s.append('[');

        // Starter på Hale og sjekker om det er null
        Node current = hale;
        if (current == null) {
            s.append(']');
            return s.toString();
        }

        // Går til siste element
        current = current.forrige;

        // Looper gjennom
        while(current.forrige != null){
            s.append(current.verdi);
            current = current.forrige;
            if (current.verdi != null) {
                s.append(','); s.append(' ');
            }
        }
        // Legger til veriden av det siste Elementet
        if (current.verdi != null) {
            s.append(current.verdi);
        }
        s.append(']');
        return s.toString();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


