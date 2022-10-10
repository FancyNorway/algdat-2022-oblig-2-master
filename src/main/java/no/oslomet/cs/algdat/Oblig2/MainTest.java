package no.oslomet.cs.algdat.Oblig2;

public class MainTest {
    public static void main(String[] args) {

        Liste<Integer> nyliste = new DobbeltLenketListe<>();

        for (int i = 1; i <= 100_000; i++) nyliste.leggInn(i);
        long tid1 = System.currentTimeMillis();
        for (int i = 40000; i <= 50000; i++) nyliste.fjern(new Integer(i));
        tid1 = System.currentTimeMillis() - tid1;
        nyliste = new DobbeltLenketListe<>();

        for (int i = 1; i <= 100_000; i++) nyliste.leggInn(i);
        long tid2 = System.currentTimeMillis();
        for (int i = 40000; i <= 50000; i++) nyliste.fjern(i);
        tid2 = System.currentTimeMillis() - tid2;

        long maks = Math.max(tid1, tid2);
        long min = Math.min(tid1, tid2);

        System.out.println(tid1);
        System.out.println(tid2);

        if (maks > 1.5 * min) {
            System.out.println("Oppgave 6zg: Ineffektiv kode! Har du kodet den ene fjern-metoden");
            System.out.println("ved hjelp av den andre? Eller så er en av fjern-metodene dine litt treg.");
        }

    }
}
