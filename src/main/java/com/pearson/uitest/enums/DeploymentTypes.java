package com.pearson.uitest.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author vgaddra
 *
 */
public enum DeploymentTypes {
    DISTRICT( "District", 1 ),
    SCHOOL( "School", 2 );

    private String name;
    private int id;

    DeploymentTypes( String name, int id ) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static List<String> getDeploymentTypeNames() {
        List<String> deploymentTypeNames = new ArrayList<String>();
        for ( DeploymentTypes dType : DeploymentTypes.values() ) {
            deploymentTypeNames.add( dType.name );
        }
        return deploymentTypeNames;
    }
}
