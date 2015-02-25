package DTO;

/**
 * Interface för klassen Job.
 */
public interface JobDTO 
{
    /**
     * Hämtar språket för detta jobb.
     * @return Spåkkoden
     */
    public String getLocale();
    
    /**
     * Hämtar id:t för jobbet.
     * @return Id:t för jobbet
     */
    public Integer getJob();
    
    /**
     * Hämtar namnet på jobbet.
     * @return Namnet på jobbet
     */
    public String getJobTypeName();
}
