public class Konto {
    private String bezeichnung;
    private Kunde[] zeichnungsberechtigte;

    public Konto(String bezeichnung, Kunde ersterZeichnungsberechtigter, Kunde... weitereZeichnungsberechtigte) { // mindestens 1 Zeichnungsberechtigter
        this.bezeichnung = bezeichnung;
        zeichnungsberechtigte = new Kunde[weitereZeichnungsberechtigte.length + 1];
        zeichnungsberechtigte[0] = ersterZeichnungsberechtigter;
        System.arrayCopy(weitereZeichnungsberechtigte, 0, zeichnungsberechtigte, 1, weitereZeichnungsberechtigte.length);
    }
    
    public GeldBetrag saldo() {
        ...
    }

    public void einzahlen(GeldBetrag eingBetrag) {
        ...
    }
}

public abstract class Kunde {
    private Konto[] konten;

    public Kunde(Konto erstKonto, Konto... weitereKonten) { // mindestens 1 Konto
        konten = new Konto[weitereKonten.length + 1];
        konten[0] = erstKonto;
        System.arrayCopy(weitereKonten, 0, konten, 1, weitereKonten.length);
    }
}

public class Privatkunde extends Kunde {
    private String vorname;
    private String nachname;
    private Adresse postAdresse;

    public Privatkunde(String vorname, String nachname, Adresse postAdresse, Konto erstKonto, Konto... weitereKonten) {
        super(erstKonto, weitereKonten);
        this.vorname = vorname;
        this.nachname = nachname;
        this.postAdresse = postAdresse;
    }
}

public class Geschäftskunde extends Kunde {
    private String firmenname;
    private Adresse domizilAdresse;

    public Geschäftskunde(String firmenname, Adresse domizilAdresse, Konto erstKonto, Konto... weitereKonten) {
        super(erstKonto, weitereKonten);
        this.firmenname = firmenname;
        this.domizilAdresse = domizilAdresse;
    }
}

public class Adresse {
    private String strasse;
    private int hausnummer;
    private String plz;
    private String ort;
    private Kunde[] kunden;

    public Adresse(String strasse, int hausnummer, String plz, String ort, Kunde... kunden) {
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ort = ort;
        this.kunden = kunden.clone();
    }
}
