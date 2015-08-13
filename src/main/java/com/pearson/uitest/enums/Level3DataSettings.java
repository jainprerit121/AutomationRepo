package com.pearson.uitest.enums;

/**
 * 
 * @author VGADDRA
 *
 */
public enum Level3DataSettings {

    LEVEL1( "75-100%", 75, 100, "75 <--> 100 %" ),
    LEVEL2( "60-74%", 60, 74, "60 <--> 74 %" ),
    LEVEL3( "0-59%", 0, 59, "0 <--> 59 %" );

    private String gradeType;
    private int startPercent;
    private int endPercent;
    private String range;

    Level3DataSettings( String gradeType, int startPercent, int endPercent, String range ) {
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
