package de.hbrs.ia.code;
import de.hbrs.ia.model.SalesMan;
import de.hbrs.ia.model.SocialPerformanceRecord;

import java.util.List;

public interface ManagePersonal {
    public void createSalesMan( SalesMan record );

    public void addSocialPerformanceRecord(SocialPerformanceRecord record , SalesMan salesMan );

    public SalesMan readSalesMan( int sid );

    public List<SalesMan> readAllSalesMen();

    public List<SocialPerformanceRecord> readSocialPerformanceRecord( SalesMan salesMan );
    
    // filter for just one year
    public SocialPerformanceRecord readSocialPerformanceRecord( SalesMan salesMan, int year );

    public void deleteSalesMan(int sid); //Delete was missing from the original interface. 
}
