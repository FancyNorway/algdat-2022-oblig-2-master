package no.oslomet.cs.algdat.Oblig2;

public class MainTest {
    public static void main(String[] args) {
        Integer[] i = {};
        DobbeltLenketListe<Integer> iliste = new DobbeltLenketListe<>(i);
        String s = iliste.subliste(0, 0).toString();
        System.out.println(s);
        if (!iliste.subliste(0, 0).toString().equals("[]")) {
            System.out.println
                    ("Oppgave 3u: Sublisten til en tom liste skal være tom!");
        }

    }
}
