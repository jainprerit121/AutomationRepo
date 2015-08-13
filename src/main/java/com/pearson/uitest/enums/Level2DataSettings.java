package com.pearson.uitest.enums;

/**
 * 
 * @author VGADDRA
 *
 */
public enum Level2DataSettings {

    LEVEL1( "60-100%", 75, 100, "60 <--> 100 %" ),
    LEVEL2( "0-59%", 0, 59, "0 <--> 59 %" );

    private String gradeType;
    private int startPercent;
    private int endPercent;
    private String range;

    Level2DataSettings( String gradeType, int startPercent, int endPercent, String range ) {
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
