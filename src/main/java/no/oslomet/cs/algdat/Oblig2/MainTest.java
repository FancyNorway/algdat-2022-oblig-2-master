package no.oslomet.cs.algdat.Oblig2;

public class MainTest {
    public static void main(String[] args) {
        Liste<Integer> testliste = new DobbeltLenketListe<>();
        for (int i = 0; i < 100000; i++) testliste.leggInn(i);
        long tid = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) testliste.hent(99999);
        tid = System.currentTimeMillis() - tid;

        System.out.println(tid);

    }
}
