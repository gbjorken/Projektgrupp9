package DTO;

/**
 * Gränssnitt som är DTO för applikationer.
 */
public interface ApplicationDTO 
{
    /**
     * Hämtar id:t för ansökan.
     * @return Id:t för ansökan 
     */
    public Integer getId();
    
    /**
     * Hämtar datumet för då ansökan gjordes.
     * @return Datumet för ansökan
     */
    public String getDateMade();
    
    /**
     * Hämtar användarnamnet för den sökande som gjorde ansökan.
     * @return Användarnamnet för den sökande
     */
    public Integer getPerson();
    
    /**
     * Hämtar id:t för statusen på ansökan.
     * @return Id:t för statusen på ansökan
     */
    public Integer getStatus();
    
    /**
     * Hämtar id:t för det jobb som ansökan berör.
     * @return Id:t för jobbet som ansökan berör
     */
    public Integer getJob();
}

