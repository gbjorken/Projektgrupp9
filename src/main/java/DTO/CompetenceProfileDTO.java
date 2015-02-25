package DTO;

/**
 * Gränssnitt som är DTO för kompetensprofiler.
 */
public interface CompetenceProfileDTO 
{
    /**
     * Hämtar id:t för kompetensprofilen.
     * @return Id:t för kompetensprofilen
     */
    public Integer getId();
    
    /**
     * Hämtar id:t för den kompetens som denna kompetensprofil gäller.
     * @return Id:t på kompetensen
     */
    public Integer getCompetence();
    
    /**
     * Hämtar antalet år för kompetensprofilen.
     * @return Antalet år för kompetensprofilen
     */
    public Double getYearsOfExperience();
    
    /**
     * Hämtar id:t för den specifika ansökan.
     * @return Id:t för den specifika ansökan
     */
    public Integer getApplication();
}
