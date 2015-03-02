package DTO;

/**
 * Gränssnitt som är DTO för tillgänglighetsperioder.
 */
public interface AvailabilityDTO 
{
    /**
     * Hämtar id:t för en tillgänglighetsperiod.
     * @return Id:t för en tillgänglighetsperiod
     */
    public Integer getId();
    
    /**
     * Hämtar startdatum.
     * @return Startdatum
     */
    public String getFromDate();
    
    /**
     * Hämtar slutdatum.
     * @return Slutdatum
     */
    public String getToDate();
    
    /**
     * Hämtar id:t för en applikation.
     * @return Id:t för en applikation
     */
    public Integer getApplication();
}
