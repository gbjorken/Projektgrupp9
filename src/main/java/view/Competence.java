package view;

/**
 * Competence är till för droplistan i application-vyn 
 * som innehåller de olika kompetenserna.
 * För varje alternativ i droplistan skapas en ny Competence.
 */
public class Competence
{
    private String comLabel;
    private String comValue;
    
    /**
     * Konstruktor för att skapa en ny Competence till droplistan
     * @param comLabel dropliste-alternativets synliga värde
     * @param comValue dropliste-alternativets bakomliggande värde
     */
    public Competence(String comLabel, String comValue)
    {
        this.comLabel = comLabel;
        this.comValue = comValue;
    }

    /**
     * @return dropliste-alternativets synliga värde 
     */
    public String getComLabel(){
        return comLabel;
    }
    
    /**
     * @return dropliste-alternativets bakomliggande värde 
     */
    public String getComValue(){
        return comValue;
    }
}