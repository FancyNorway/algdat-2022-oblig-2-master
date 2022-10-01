package no.oslomet.cs.algdat.Oblig2;

public class MainTest {
    public static void main(String[] args) {
        DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();

        liste.leggInn(1);

        liste.leggInn(2);
        liste.leggInn(3);
        liste.leggInn(4);

        if (liste.hent(3) != 4 || liste.hent(2) != 3
                || liste.hent(1) != 2 || liste.hent(0) != 1) {
            System.out.println("Oppgave 3i: Metoden hent() gir feil svar!");
        }

    }
}
