package DTO;

/**
 * Interface för klassen Competens.
 */
public interface CompetenceDTO 
{
    /**
     * Hämtar vilket språk denna specifika kompetens är på.
     * @return Språkkoden
     */
    public String getLocale();
    
    /**
     * Hämtar id:t för kompetensen.
     * @return Id:t för kompetensen
     */
    public Integer getCompetence();
    
    /**
     * Hämtar namnet på kompetensen.
     * @return Namnet för kompetensen
     */
    public String getCompetenceName();
}
