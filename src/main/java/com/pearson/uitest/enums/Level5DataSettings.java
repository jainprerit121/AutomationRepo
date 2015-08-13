package com.pearson.uitest.enums;

/**
 * 
 * @author VGADDRA
 *
 */
public enum Level5DataSettings {

    LEVEL5( "90-100%", 90, 100, "90 <--> 100 %" ),
    LEVEL4( "85-89%", 85, 89, "85 <--> 89 %" ),
    LEVEL3( "65-84%", 65, 84, "65 <--> 84 %" ),
    LEVEL2( "60-64%", 60, 64, "60 <--> 64 %" ),
    LEVEL1( "0-59%", 0, 59, "0 <--> 59 %" );

    private String gradeType;
    private int startPercent;
    private int endPercent;
    private String range;

    Level5DataSettings( String gradeType, int startPercent, int endPercent, String range ) {
        this.gradeType = gradeType;
        this.startPercent = startPercent;
        this.endPercent = endPercent;
        this.range = range;
    }

    public int getStartPercent() {
        return startPercent;
    }

    public int getEndPercent() {
        return endPercent;
    }

    public String getGradeType() {
        return gradeType;
    }

    public String getRange() {
        return range;
    }
}
