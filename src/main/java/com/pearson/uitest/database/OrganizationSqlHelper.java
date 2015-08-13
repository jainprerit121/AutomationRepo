package com.pearson.uitest.database;

import java.util.ArrayList;
import java.util.List;

import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.AdminUIConstants;

/**
 * This class is for organization related data setup in backend DB This will
 * enable to create / delete / fetch organizations.
 * 
 * @author vgaddra
 *
 */
public class OrganizationSqlHelper {
    private static final Logger log = Logger.getLogger( OrganizationSqlHelper.class );

    public OrganizationSqlHelper() {}

    public Integer createOrganization( String organizationName, String organizationDistrictNum ) {
        log.info( "Creating school -> " + organizationName );
        Object[] params = { organizationName, AdminUIConstants.SCHOOL_ORG_TYPE_ID, organizationDistrictNum, AdminUIConstants.DEFAULT_DISTRICT_ID, AdminUIConstants.SIF_REF_ID };
        int orgId = SqlHelper.executeFunction( "{call successmaker.create_organization(?,?,?,?,?)}", params );
        log.info( "Created school -> " + organizationName + " with school id -> " + orgId );
        return orgId;
    }

    public void deleteOrganization( String organizationName ) {
        log.info( "Deleting school -> " + organizationName );
        Object[] params = { organizationName };
        SqlHelper.executeFunction( "{call successmaker.delete_organization_proc(?)}", params );
        log.info( "Deleted school -> " + organizationName );
    }

    public void deleteOrganizationbyID( String organizationID ) {
        log.info( "Deleting school with ID -> " + organizationID );
        List<Object[]> orgNameList = getOrganizationNamebyID(organizationID);
        if (orgNameList.size() > 0) {
        	deleteOrganization( orgNameList.get(0)[0].toString());
        }
    }
    
    public List<Object[]> getOrganizationDetails( String username ) {
        String queryString = "SELECT organization_name, organization_district_num, organization_id, parent_organization_id FROM school.organization WHERE organization_type_id IN (1,2) ";
        if ( username != null ) {
            queryString += " AND organization_id IN (SELECT po.organization_id FROM school.person_organization po, person p WHERE po.person_id = p.person_id AND username='" + username + "')";
        }
        return SqlHelper.executeQuery( queryString );
    }

    public List<Object[]> getOrganizationNamebyID( String orgID ) {
        String queryString = "SELECT organization_name FROM school.organization WHERE organization_district_num= '" + orgID +"'";
        return SqlHelper.executeQuery( queryString );
    }
    
    public List<String> getOrganizationNames( String username ) {
        return getOrgNameList( getOrganizationDetails( username ) );
    }

    private List<String> getOrgNameList( List<Object[]> orgDetails ) {
        List<String> orgNameList = new ArrayList<String>();
        for ( Object[] org : orgDetails ) {
            if ( org.length > 0 ) {
                orgNameList.add( org[0].toString() );
            }
        }
        return orgNameList;
    }

    public List<String> getAllDistrictsList() {
        String queryString = "SELECT Organization_Name from organization where organization_type_id = 1";
        return getOrgNameList( SqlHelper.executeQuery( queryString ) );
    }
}
