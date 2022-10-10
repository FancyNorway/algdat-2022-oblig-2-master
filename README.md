# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Magnus Reinholdt Belsvik, s362088, s362088@oslomet.no
* Karl Anders Eide Haugholt, s362061, s362061@oslomet.no


# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Magnus har hatt hovedansvar for oppgave 2, 5, 6, 8. 
* Anders har hatt hovedansvar for oppgave 1, 2, 4, 3, 7. 


# Oppgavebeskrivelse

Vi har to warings en på linje 510 og en på 511, disse kommer fra kildekode. Derfor velger vi
å ikke endre noe fordi i oppgave 8 står det "Metoden boolean  hasNext() og konstruktøren 
public  DobbeltLenketListeIterator() i klassen DobbeltLenketListeIterator er ferdigkodet og skal ikke endres."

I oppgave 1 Lagde vi først int antall() metoden, og deretter boolean tom() metoden. 
Deretter lagde vi konstruktøren public DobbeltLenketListe(T[] a). I denne brukte vi requireNonNull metoden for
sørge for tabellen ikke var null. Og deretter en forAll løkke for å sørge for at vi ikke fikk med noen null-verdier.

I oppgave 2 fungerer String toString() ved at den Starter på hode og sjekker om listen er tom. er den tom
retunerer den da "[]". så looper den gjennom Nodene og legger til verdien i Stringbuilderen ved hjelp av 
append(). Til sull er det bare en Node igjen og derfor har vi sjekken slik at Stringen ikke skal bli sånn 
"[A, B, C, ]". Men slik "[A, B, C]". Metoen String omvendtString() Fungerer på samme måte bare at den starter
på halen og går bakover.
Metoen boolen leggInn(T verdi) sjekker først at det ikke er en null verdi. Hvis listen er tom lager den
Hode, hale og selg selv som noder og legger pekeren riktig. Er ikke listen tom oppreter vi en Node med verdi
og legger den bakerst. Fikser pekrne slik at hale perker på nyNode og den forrige siste peker på nyNode.

I oppgave 3

I oppgave 4 lagde vi først metoden int indeksTil(T verdi) som skal finne den første verdien i listen. 
Dette gjør vi ved å gå gjennom den lenkede listen med current.next til current.verdi = verdi. Når vi finner verdien 
returnerer vi indeksen til denne verdien. Hvis verdien ikke finnes, eller listen er tom eller verdien er null så 
returneres indeks -1

I oppgave 5 

I oppgave 6 

I oppgave 7 er metode 1 ca 25% raskere enn metode 2. Derfor valgte vi å bruke den. Metode 1
et løst ved å starte på hode.neste og bruke en while løkke til å loope gjennom alle Noder og
sette innholde til null. Metode 2 fjerner den 0 noden helt til listen er tom med hjelp av en while løkke.

I oppgave 8 lagde vi metoden T next() ved å først sjekke om iteratorendringer var lik endringer,
og deretter sjekke om noden hadde en neste-verdi som er null ved å bruke hasNext()-metoden. Vi sjekker også om
denne-pekeren er lik hodet. Etter disse sjekkene setter vi fjernOK lik true, og returnerer verdien til denne, og
sette denne = denne.neste. Vi lager også metoden Iterator<T> iterator() som returnerer en instans av
DobbeltLenketListeIterator-klassen. Vi lager en ny konstruktør i klassen, som tar inn en parameter indeks. 
Deretter lager vi en ny metode Iterator<T> iterator(int indeks) som først bruker en indekssjekk, og deretter returnerer
en instans av iteratorklassen ved hjelp av konsttrukøren i fra oppgave c.


