import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserInterface {
    private int menuvalg;
    private Scanner input = new Scanner(System.in);
    private Controller controller = new Controller();


    public void menu() {
        System.out.println("Velkommen til Delfinens administrative system");
        System.out.println("1) Indmeldelse af nye medlemmer");
        System.out.println("2) Redigere medlemmer oplysninger");
        System.out.println("3) Slette medlemmer");
        System.out.println("4) Oversigt over medlemmer");
        System.out.println("5) Opdater medlemmers resultater");
        System.out.println("6) Kontigent oversigt");
        System.out.println("7) Oversigt over hold");
        System.out.println("9) Exit");
    }

    public void start() throws FileNotFoundException {

        do {
            menu();
            menuvalg = input.nextInt();
            input.nextLine(); //Fix for Scanner Bug
            switch (menuvalg) {
                case 1:
                    indmeldelse();
                    break;
                case 2:
                    redigerMedlem();
                    break;
                case 3:
                    sletteMedlemmer();
                    break;
                case 4:
                    medlemsOversigt();
                    break;
                case 5:
//                    controller.sorteretMedlemmer();
                    break;
                case 6:
                    kontigentOversigt();
            }
        } while (menuvalg != 9);
        System.out.println("Du aflsutter nu programmet");
        controller.saveData();
    }


    public void indmeldelse() {
        String hold = null;
        String disciplin = null;
        double træningsresultat = 0;

        System.out.println("Navn på medlem: ");
        String navn = input.nextLine();

        System.out.println("Efternavn på medlem: ");
        String efternavn = input.nextLine();

        System.out.println("Alder på medlem: ");
        int alder = input.nextInt();
        input.nextLine();

        System.out.println("Køn på medlem: ");
        String køn = input.nextLine();

        System.out.println("aktivitetsform på medlem (Aktiv/Passiv): ");
        boolean aktivitetsform = true;
        if (input.nextLine().equalsIgnoreCase("aktiv")) {
            aktivitetsform = true;
        } else if (input.nextLine().equalsIgnoreCase("passiv")) {
            aktivitetsform = false;
        }

        System.out.println("Er medlem en konkurrencesvømmer (Motionist/Konkurrencesvømmer): ");
        boolean konkurrencesvømmer = input.nextBoolean();

        if (konkurrencesvømmer) {
            System.out.println("Hvilket hold skal medlem være en del af? (Junior/Senior)");
            hold = input.nextLine();

            System.out.println("Hvilken disciplin skal medlem svømme i?");
            disciplin = input.nextLine();
        }

        System.out.println("Er medlemmet studerende?");
        boolean studerende = input.nextBoolean();

        // TODO: Flyt/ændr til at være i databasen
        int medlemsNummer = 0;
        for (Medlem medlemmer : controller.getMedlemmer()) {
            int midlertidigtMedlemsNummer = controller.getMedlemmer().size();
            medlemsNummer = midlertidigtMedlemsNummer + 1;
        }

        System.out.println("Medlem er gemt i databasen");
        Medlem medlem = controller.nyMedlem(navn, efternavn, alder, køn, aktivitetsform, konkurrencesvømmer, hold, disciplin, træningsresultat, studerende, medlemsNummer);

        System.out.println("---------------------------------");


        udskrivMedlem( medlem );

        System.out.println("Navn: " + navn + " " + efternavn);
        System.out.println("Alder: " + alder);
        System.out.println("Køn: " + køn);
        String aktivitet = "";
        if (aktivitetsform == true) {
            aktivitet = "aktiv";
        } else {
            aktivitet = "passiv";
        }
        System.out.println("Aktivitetsform: " + aktivitet);
        System.out.println("Konkurrencesvømmer: " + konkurrencesvømmer);
        System.out.println("Studerende: " + studerende);
        System.out.println("Medlemsnummer: " + medlemsNummer);
        System.out.println("---------------------------------");
    }

    public void redigerMedlem() {
        for (int i = 0; i < controller.getMedlemmer().size(); i++) {
            System.out.println(i + 1 + ":" + controller.getMedlemmer().get(i));
        }

        System.out.println("indtast nummer på medlem der skal redigeres:");
        int nr = input.nextInt();
        input.nextLine();

        Medlem editMedlem = controller.getMedlemmer().get(nr - 1); // index starter fra 0
        System.out.println("Edit medlem: " + editMedlem);

        System.out.println("Rediger data og tryk ENTER. Hvis data ikke skal redigeres tryk ENTER");

        System.out.println("Navn: " + editMedlem.getNavn());
        String nyNavn = input.nextLine();
        if (!nyNavn.isEmpty())
            editMedlem.setNavn(nyNavn);

        System.out.println("Efternavn: " + editMedlem.getEfternavn());
        String nyEfternavn = input.nextLine();
        if (!nyEfternavn.isEmpty()) {
            editMedlem.setEfternavn(nyEfternavn);
        }

        System.out.println("Alder: " + editMedlem.getAlder());
        String nyAlder = input.nextLine();
        if (!nyAlder.isEmpty()) {
            editMedlem.setAlder(Integer.parseInt(nyAlder));
        }

        System.out.println("Køn: " + editMedlem.getKøn());
        String nyKøn = input.nextLine();
        if (!nyKøn.isEmpty()) {
            editMedlem.setKøn(nyKøn);
        }

        System.out.println("Aktivitetsform: " + editMedlem.isAktivitetsForm());
        String nyAktivitetsform = input.nextLine();
        if (!nyAktivitetsform.isEmpty()) {
            editMedlem.setAktivitetsForm(Boolean.parseBoolean(nyAktivitetsform));
        }

        System.out.println("Konkurrencesvømmer: " + editMedlem.isKonkurrenceSvømmer());
        String nyKonkurrencesvømmer = input.nextLine();
        if (!nyKonkurrencesvømmer.isEmpty()) {
            editMedlem.setKonkurrenceSvømmer(Boolean.parseBoolean(nyKonkurrencesvømmer));
        }
    }

    public void sletteMedlemmer() {
        for (int i = 0; i < controller.getMedlemmer().size(); i++) {
            System.out.println(i + 1 + ":" + controller.getMedlemmer().get(i));
        }

        System.out.println("indtast nummer på medlem der skal slettes:");
        int nr = input.nextInt();
        input.nextLine();

        // TODO: Lav en deleteMedlem metode i controller i stedet
        Medlem sletMedlem = controller.getMedlemmer().remove(nr - 1); // index starter fra 0
        System.out.println("Medlem " + sletMedlem + " er slettet fra systemet");

    }

    public void medlemsOversigt() {
        for (Medlem medlem : controller.getMedlemmer()) {
            System.out.println("---------------------------------");
            udskrivMedlem(medlem);

        }
    }

    private void udskrivMedlem(Medlem medlem) {
        System.out.println("Navn: " + medlem.getNavn() + " " + medlem.getEfternavn());
        System.out.println("Alder: " + medlem.getAlder());
        System.out.println("Køn: " + medlem.getKøn());
        System.out.println("Aktivitetsform: " + medlem.isAktivitetsForm());
        System.out.println("Konkurrencesvømmer: " + medlem.isKonkurrenceSvømmer());
        System.out.println("Medlemsnummer : " + medlem.getMedlemsNummer());
    }

    public void kontigentOversigt(){
        //For aktive medlemmer er kontingentet for ungdomssvømmere (under 18 år) 1000 kr. årligt,
        //For seniorsvømmere (18 år og over) 1600 kr. årligt.
        //For medlemmer over 60 år gives der 25 % rabat af seniortaksten.
        //For passivt medlemskab er taksten 500 kr. årligt.
        //For studerende givers der 15 % rabat af seniortaksten.

        for (Medlem medlemmer: controller.getMedlemmer()){
            if (medlemmer.getAlder()<18){
               int kontigentUng;
                kontigentUng=1000;
                System.out.println("Kontigent for den medlem er: "+kontigentUng);
            } else if (medlemmer.getAlder()==18 && medlemmer.getAlder()<=60) {
                int kontigentSenior=1600;
                System.out.println("Kontigent for den medlem er: "+kontigentSenior);
            } else if (medlemmer.getAlder()>60){
                double kontigentST=1600*(1-0.25);
                System.out.println("Kontigent for den medlem er: "+kontigentST);
            }else if (medlemmer.isAktivitetsForm()==true){
                double kontigentAktiv=500;
                System.out.println("Kontigent for den medlem er: "+kontigentAktiv);
            } else if (medlemmer.isStuderende()==true){
                double kontigentStud=1600*(1-0.15);
                System.out.println("Kontigent for den medlem er: "+kontigentStud);
            }
        }

    }
}

