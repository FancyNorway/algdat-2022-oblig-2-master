package no.oslomet.cs.algdat.Oblig2;

public class MainTest {
    public static void main(String[] args) {
        DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();

        liste.leggInn(1);

        try {
            liste.hent(-1);
            System.out.println("Oppgave 3d: Feil i indeks-sjekken!");
        } catch (Exception e) {
            if (!(e instanceof IndexOutOfBoundsException)) {
                System.out.println("Oppgave 3e: Metoden hent() kaster feil type unntak!");
            }
        }

        try {
            liste.hent(1);
            System.out.println("Oppgave 3f: Feil i indeks-sjekken!");
        } catch (Exception e) {
            if (!(e instanceof IndexOutOfBoundsException)) {
                System.out.println("Oppgave 3g: Metoden hent() kaster feil type unntak!");
            }
        }

    }
}
