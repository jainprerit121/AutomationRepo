package com.pearson.uitest.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author VGADDRA
 *
 */
public enum ProficiencyScaleSettings {

    LEVEL_5_PERFORMANCE( "5 Levels of Performance" ),
    LEVEL_4_PERFORMANCE( "4 Levels of Performance" ),
    LEVEL_3_PERFORMANCE( "3 Levels of Performance" ),
    LEVEL_2_PERFORMANCE( "2 Levels of Performance" );

    private String performanceType;

    private ProficiencyScaleSettings( String level ) {
        performanceType = level;
    }

    public String getPerformanceType() {
        return performanceType;
    }

    public static List<String> getProficiencyTypes() {
        List<String> values = new ArrayList<String>();
        for ( ProficiencyScaleSettings settings : ProficiencyScaleSettings.values() ) {
            values.add( settings.performanceType );
        }
        return values;
    }

}
