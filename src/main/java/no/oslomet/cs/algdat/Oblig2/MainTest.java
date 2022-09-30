package no.oslomet.cs.algdat.Oblig2;

public class MainTest {
    public static void main(String[] args) {
        DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();
        liste.leggInn(1);
        liste.leggInn(2);
        liste.leggInn(3);

        int tall = 0;
        int a = liste.indeksTil(tall);
        boolean b = liste.inneholder(tall);
        System.out.println("Inneholder: " + b + " Plass: " + a);

    }
}
