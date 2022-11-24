import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    Database database = new Database();
    Filehandler filehandler;

    {
        try {
            filehandler = new Filehandler();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void nyMedlem(String navn, String efternavn, int alder, String køn, boolean aktivitetsform, boolean konkurrencesvømmer, int medlemsNummer){
        database.nyMedlem(navn, efternavn, alder, køn, aktivitetsform, konkurrencesvømmer, medlemsNummer);
    }

    public ArrayList<Medlemmer> getMedlemmer() {
        return database.getMedlemmers();
    }

    public void saveData() {
    database.getMedlemmers();
    filehandler.gemMedlemmer(getMedlemmer());
    }


}