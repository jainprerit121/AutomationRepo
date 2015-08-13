package com.pearson.uitest.enums;

/**
 * 
 * @author VGADDRA
 *
 */
public enum Level4DataSettings {
    LEVEL1( "85-100%", 85, 100, "85 <--> 100 %" ),
    LEVEL2( "70-84%", 70, 84, "70 <--> 84 %" ),
    LEVEL3( "60-69%", 60, 69, "60 <--> 69 %" ),
    LEVEL4( "0-59%", 0, 59, "0 <--> 59 %" );

    private String gradeType;
    private int startPercent;
    private int endPercent;
    private String range;

    Level4DataSettings( String gradeType, int startPercent, int endPercent, String range ) {
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
