package test;

import de.hbrs.ia.code.ManagePersonalImpl;
import de.hbrs.ia.model.SalesMan;
import de.hbrs.ia.model.SocialPerformanceRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ManagePersonalMongoImplTest {
    private ManagePersonalImpl managePersonal;

    @BeforeEach
    public void setUp() {
        managePersonal = new ManagePersonalImpl();
    }


    @Test
    public void testSalesMan() {
        // new salesman
        SalesMan salesMan = new SalesMan("Niklas", "Nord", 2105);
        managePersonal.createSalesMan(salesMan);

        // read
        SalesMan salesManRead = managePersonal.readSalesMan(2105);

        // successful?
        System.out.println(salesManRead.getFirstname() + ", " + salesManRead.getLastname() + ", " + salesManRead.getId());
        assertNotNull(salesManRead);
        assertEquals("Niklas", salesManRead.getFirstname());
        assertEquals("Nord", salesManRead.getLastname());
        assertEquals(2105, salesManRead.getId());
    }

    @Test
    public void testPerformanceRecord() {
        // new salesman
        SalesMan salesMan = new SalesMan("Firstname", "Lastname", 1111);
        managePersonal.createSalesMan(salesMan);

        // new social performance record
        SocialPerformanceRecord recordInput1 = new SocialPerformanceRecord(salesMan,2023,3,3,3,3,3,3);
        managePersonal.addSocialPerformanceRecord(recordInput1, salesMan);

        // new social performance record
        SocialPerformanceRecord recordInput2 = new SocialPerformanceRecord(salesMan,2022,2,2,2,2,2,2);
        managePersonal.addSocialPerformanceRecord(recordInput2, salesMan);

        // read specific record
        SocialPerformanceRecord recordOutput = managePersonal.readSocialPerformanceRecord(salesMan, 2023);

        // assert values
        System.out.println(recordOutput.getSalesMan() + ", " + recordOutput.getYear() + ", " + recordOutput.getAttitudeScore()+ ", " + recordOutput.getCommunicationScore()+ ", " + recordOutput.getIntegrityScore()+ ", " + recordOutput.getLeadershipScore()+ ", " + recordOutput.getOpennessScore()+ ", " + recordOutput.getSocialScore());
        assertNotNull(recordOutput);
        assertEquals(salesMan, recordOutput.getSalesMan());
        assertEquals(2023, recordOutput.getYear());
        assertEquals(3, recordOutput.getAttitudeScore());
        assertEquals(3, recordOutput.getCommunicationScore());
        assertEquals(3, recordOutput.getIntegrityScore());
        assertEquals(3, recordOutput.getOpennessScore());
        assertEquals(3, recordOutput.getSocialScore());
        assertEquals(3, recordOutput.getLeadershipScore());

        // Read all performance records for the salesman
        List<SocialPerformanceRecord> records = managePersonal.readSocialPerformanceRecord(salesMan);

        // Assert proper values
        assertNotNull(records);
        assertEquals(2, records.size());
        assertEquals(salesMan, records.get(1).getSalesMan());
        assertEquals(2022, records.get(1).getYear());
        assertEquals(2, records.get(1).getAttitudeScore());
        assertEquals(2, records.get(1).getCommunicationScore());
        assertEquals(2, records.get(1).getIntegrityScore());
        assertEquals(2, records.get(1).getOpennessScore());
        assertEquals(2, records.get(1).getSocialScore());
        assertEquals(2, records.get(1).getLeadershipScore());
    }

    @Test
    public void testDeleteSalesManAndRecords() {
        // new Salesman
        SalesMan salesMan = new SalesMan("Tobe", "Deleted", 222);
        managePersonal.createSalesMan(salesMan);

        // new record
        SocialPerformanceRecord record = new SocialPerformanceRecord(salesMan,2023,1,1,1,1,1,1);
        managePersonal.addSocialPerformanceRecord(record, salesMan);

        // Does it exist before deletion?
        assertNotNull(managePersonal.readSalesMan(222));
        System.out.println(managePersonal.readSocialPerformanceRecord(salesMan));
        assertEquals(1, managePersonal.readSocialPerformanceRecord(salesMan).size());

        // Delete
        managePersonal.deleteSalesMan(222);

        // Does it no longer exist after deletion?
        assertNull(managePersonal.readSalesMan(222));
        assertTrue(managePersonal.readSocialPerformanceRecord(salesMan).isEmpty());
    }

    @AfterEach
    public void tearDown() {
        managePersonal.performanceRecordCollection.drop();
        managePersonal.salesmenCollection.drop();
        managePersonal.closeConnection();
    }
}
