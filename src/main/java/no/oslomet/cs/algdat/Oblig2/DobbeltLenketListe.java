package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;


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

        boolean ikkeNullVerdi = false;
        for (T verdi: a) {
            if (verdi != null) {
                ikkeNullVerdi = true;
                break;
            }
        }

        if (!ikkeNullVerdi) return;

        hode = new Node<>(null, null, null);
        hale = new Node<>(null, null, null);
        Node<T> current = hode;

        for (int i = 0; i < a.length; i++) {
            if (a[i] != null) {
                // Lager en ny node med verdi a[i]
                Node <T> nyNode = new Node<>(a[i]);

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

    public void fratilKontroll(int tablengde, int fra, int til)
    {
        // fra er negativ
        if (fra < 0) throw new IndexOutOfBoundsException
                ("fra(" + fra + ") er negativ!");

        // til er utenfor listen
        if (til > tablengde) throw new IndexOutOfBoundsException
                ("til(" + til + ") > antall(" + tablengde + ")");

        // fra er større enn til
        if (fra > til) throw new IllegalArgumentException
                ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    public Liste<T> subliste(int fra, int til)
    {
        fratilKontroll(antall, fra, til);
        DobbeltLenketListe<T> liste = new DobbeltLenketListe<>();
        liste.endringer = 0;
        if (hode == null) return liste;
        Node<T> current = hode.neste;
        int nr = 0;

        // Kommer til første node
        while (nr < fra) {
            current = current.neste;
            nr++;
        }

        // Looper gjennom [fra, til>
        int index = 0;
        while (nr < til) {
            liste.leggInn(index, current.verdi);
            current = current.neste;
            nr++;
            index++;
        }

        return liste;
    }

    @Override
    public int antall() {
        if(tom()){
            return antall = 0;
        }
        int antall = 1;
        Node<T> current = hode.neste;

        while(current.neste != null){
            antall++;
            current = current.neste;

        }
        return antall;
    }



    @Override
    public boolean tom() {
        return hode == null;
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Dette er en null-verdi");
        antall += 1;
        endringer++;
        if (tom()){
            hode = new Node<>(null, null, null);
            hale = new Node<>(null, null, null);
            Node<T> nyNode = new Node<>(verdi);
            hode.neste = nyNode;
            hale.forrige = nyNode;
            return true;
        }
        Node<T> temp = hale.forrige;
        Node<T> nyNode = new Node<>(verdi);
        temp.neste = nyNode;
        nyNode.forrige = temp;
        hale.forrige = nyNode;
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi, "Verdi skal ikke være en null-verdi");
        antall += 1;
        endringer ++;
        int listeAntall = antall();
        if (indeks < 0 || indeks > listeAntall) throw
            new IndexOutOfBoundsException("Indexen må være i intervallet [0, antall]");

        // Listen er tom
        if (tom()) {
            leggInn(verdi);
            return;
        }

        // Verdien skal legges først
        if (indeks == 0)
        {
            Node<T> temp = hode.neste;
            Node<T> nyNode = new Node<>(verdi);
            nyNode.neste = temp;
            hode.neste = nyNode;
            temp.forrige = nyNode;
        return;
        }

        // Verdien skal legges bakerst
        if (indeks == antall-1)
        {
            Node<T> temp = hale.forrige;
            Node<T> nyNode = new Node<>(verdi);
            hale.forrige = nyNode;
            nyNode.forrige = temp;
            temp.neste = nyNode;
            return;
        }

        // verdien skal legges mellom to andre verdier

        Node<T> current = hode;
        int posisjon = 0;
        while(posisjon < indeks){
            current = current.neste;
            posisjon++;
        }
        Node<T> temp = current.neste;
        Node<T> nyNode = new Node<>(verdi);
        nyNode.neste = temp;
        nyNode.forrige = current;
        temp.forrige = nyNode;
        current.neste = nyNode;

    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) >= 0;
    }

    private Node <T> finnNode(int indeks){

        if (indeks < antall/2){
            Node<T> current = hode;
            int counter = -1;
            while(counter < indeks){
                current = current.neste;
                counter++;
            }
            return current;

        }
        int counter = antall;
        Node<T> current = hale;
        while (counter > indeks){
            current = current.forrige;
            counter--;
        }
        return current;
    }

    @Override
    public T hent(int indeks) {
        // indeksKontroll(indeks, false);
        // IndeksKontroll bruker ca. 1500 ms derfor kan vi ikke bruke den når testen krever max 20ms runtime
        if (indeks < 0) {
            throw new IndexOutOfBoundsException(melding(indeks));
        }
        if (indeks >= antall) {
            throw new IndexOutOfBoundsException(melding(indeks));
        }
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        if (verdi == null) return -1;
        int index = 0;

        if (hode == null) return -1;
        Node<T> current = hode;

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
    public T oppdater(int indeks, T nyverdi)
    {
        if (indeks < 0) {
            throw new IndexOutOfBoundsException(melding(indeks));
        }
        if (indeks >= antall) {
            throw new IndexOutOfBoundsException(melding(indeks));
        }
        if (nyverdi == null) {
            throw new NullPointerException("Nyverdi kan ikke være null");
        }
        Node<T> listeNode = finnNode(indeks);
        T gammelVerdi = listeNode.verdi;
        listeNode.verdi = nyverdi;
        endringer += 1;
        return gammelVerdi;
    }

    @Override
    public boolean fjern(T verdi) {
        if(verdi == null) return false;
        if(antall == 0) return false;
        Node<T> current = hode.neste;

        if (antall == 1) {
            if (!verdi.equals(current.verdi)) return false;

            hode = null;
            hale = null;
            antall -= 1;
            endringer += 1;
            return true;
        }

        for(int i = 0; i < antall; i++){
            if (verdi.equals(current.verdi)){
                if(i == 0){
                    Node<T> temp = current.neste;
                    hode.neste = temp;
                    current.neste = null;
                    temp.forrige = null;
                    antall -= 1;
                    endringer += 1;
                    return true;
                }
                if(i == antall-1){
                    Node<T> temp = current.forrige;
                    hale.forrige = temp;
                    current.forrige = null;
                    temp.neste = null;
                    antall -= 1;
                    endringer += 1;
                    return true;
                }
                Node<T> temp1 = current.forrige;
                Node<T> temp2 = current.neste;
                temp1.neste = temp2;
                temp2.forrige = temp1;
                current.neste = null;
                current.forrige = null;
                antall -= 1;
                endringer += 1;
                return true;
            }
            current = current.neste;
        }
        return false;
    }

    @Override
    public T fjern(int indeks) {
        if(indeks < 0)
            throw new IndexOutOfBoundsException("indeks er for stor elr liten");
        if(indeks > antall-1)
            throw new IndexOutOfBoundsException("indeks er for stor elr liten");

        Node<T> current = hode.neste;
        T verdi = hode.verdi;

        if (antall == 1) {
            verdi = current.verdi;
            hode = null;
            hale = null;
            antall -= 1;
            endringer += 1;
            return verdi;
        }

        for (int i = 0; i<antall; i++){
            verdi = current.verdi;
            if(indeks == i) {
                if(i == 0){
                    Node<T> temp = current.neste;
                    temp.forrige = null;
                    hode.neste = temp;
                    break;
                }
                if(i == antall-1){
                    Node<T> temp = current.forrige;
                    temp.neste = null;
                    hale.forrige = temp;
                    break;
                }

                Node<T> temp1 = current.forrige;
                Node<T> temp2 = current.neste;
                temp1.neste = temp2;
                temp2.forrige = temp1;
                current.neste = null;
                current.forrige = null;
                break;
            }
            current = current.neste;
        }
        antall -= 1;
        endringer += 1;
        return verdi;
    }

    @Override
    public void nullstill()
    {
        // Metode 1
        Node<T> current = hode.neste;
        Node<T> temp;

        while (current != null) {
            current.verdi = null;
            current.forrige = null;
            temp = current;

            current = current.neste;

            temp.neste = null;
        }
        hode = null;
        hale = null;
        antall = 0;
        endringer += 1;

        // Metode 2
        /*
        while (antall > 0) {
            fjern(0);
        }
         */
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append('[');

        // Starter på hode og sjekker om listen er tom
        Node<T> current = hode;
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
        Node<T> current = hale;
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
        return new DobbeltLenketListeIterator();


    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);
        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK; // warning er fra kildekode
        private int iteratorendringer; // warning er fra kildekode

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            Node<T> current = hode.neste;
            int teller = 0;
            while (teller < indeks){
                current = current.neste;
                teller++;
            }

            denne = current;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer

        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            if(iteratorendringer != endringer) throw new ConcurrentModificationException();
            if(!hasNext()) throw new NoSuchElementException();
            if(denne == hode){
                denne = denne.neste;
            }
            fjernOK = true;
            T verdi = denne.verdi;
            denne = denne.neste;
            return verdi;

        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

} // class DobbeltLenketListe


