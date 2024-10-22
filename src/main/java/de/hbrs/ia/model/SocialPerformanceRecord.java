package de.hbrs.ia.model;

import org.bson.Document;
public class SocialPerformanceRecord {
    private int year;
    private int opennessScore; 
    private int leadershipScore;  
    private int socialScore; 
    private int attitudeScore;
    private int communicationScore; 
    private int integrityScore;  
    private SalesMan salesMan; 

    public SocialPerformanceRecord(SalesMan salesMan, int year, int opennessScore, int leadershipScore, int socialScore, int attitudeScore, int communicationScore, int integrityScore) {
       //year, salesman and all six scores. 
        this.year = year;
        this.opennessScore = opennessScore;
        this.leadershipScore = leadershipScore;
        this.socialScore = socialScore;
        this.attitudeScore = attitudeScore;
        this.communicationScore = communicationScore;
        this.integrityScore = integrityScore;
        this.salesMan = salesMan;
    }

    //throw an exception for setters when scores are out of the 1-5 range?

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getOpennessScore() {
        return opennessScore;
    }

    public void setOpennessScore(int opennessScore) {
        this.opennessScore = opennessScore;
    }

    public int getLeadershipScore() {
        return leadershipScore;
    }

    public void setLeadershipScore(int leadershipScore) {
        this.leadershipScore = leadershipScore;
    }

    public int getAttitudeScore() {
        return attitudeScore;
    }

    public void setAttitudeScore(int attitudeScore) {
        this.attitudeScore = attitudeScore;
    }

    public int getCommunicationScore() {
        return communicationScore;
    }

    public void setCommunicationScore(int communicationScore) {
        this.communicationScore = communicationScore;
    }

    public int getIntegrityScore() {
        return integrityScore;
    }

    public void setIntegrityScore(int integrityScore) {
        this.integrityScore = integrityScore;
    }

    public int getSocialScore() {
        return socialScore;
    }

    public void setSocialScore(int socialScore) {
        this.socialScore = socialScore;
    }

    public SalesMan getSalesMan() {
        return salesMan;
    }

    public void setSalesMan(SalesMan salesMan) {
        this.salesMan = salesMan;
    }


    public Document toDocument() {
        Document document = new Document();
        document.append("year", this.year);
        document.append("opennessScore", this.opennessScore);
        document.append("leadershipScore", this.leadershipScore);
        document.append("socialScore", this.socialScore);
        document.append("communicationScore", this.communicationScore);
        document.append("attitudeScore", this.attitudeScore);
        document.append("integrityScore", this.integrityScore);
        document.append("sid", this.salesMan.getId());
        return document;
    }

    public static SocialPerformanceRecord fromDocument(Document document, SalesMan salesMan) {
        int year = document.getInteger("year", 0);
        int opennessScore = document.getInteger("opennessScore", 0);
        int leadershipScore = document.getInteger("leadershipScore", 0);
        int socialScore = document.getInteger("socialScore", 0);
        int attitudeScore = document.getInteger("attitudeScore", 0);
        int communicationScore = document.getInteger("communicationScore", 0);
        int integrityScore = document.getInteger("integrityScore", 0);
        
        SocialPerformanceRecord record = new SocialPerformanceRecord(
            salesMan, year, opennessScore, leadershipScore, socialScore, attitudeScore, communicationScore, integrityScore
        );
                return record;
    }

}
