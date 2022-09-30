package no.oslomet.cs.algdat.Oblig2;

public class MainTest {
    public static void main(String[] args) {
        DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();
        liste.leggInn(1);

        String s = liste.toString();
        System.out.println(s);

    }
}
