package no.oslomet.cs.algdat.Oblig2;

public class MainTest {
    public static void main(String[] args) {
        Liste<Integer> liste = new DobbeltLenketListe<>();
        liste = new DobbeltLenketListe<>(new Integer[]{1});
        System.out.println(liste.antall() + " " + liste.tom());
    }
}
