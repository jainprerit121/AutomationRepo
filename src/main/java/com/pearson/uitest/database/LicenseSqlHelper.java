package com.pearson.uitest.database;

import java.util.List;

import org.testng.log4testng.Logger;
/**
 * Common SQL helper for License Page
 * @author Rakshit Rao
 *
 */
public class LicenseSqlHelper {

    private static final Logger log = Logger.getLogger(LicenseSqlHelper.class);
    
    public LicenseSqlHelper(){
        
    }
    
    public static List<Object[]> getOrgNamesInAlphanumericOrderExceptSourceOrg(String orgName){
        String queryString = "Select organization_name from school.organization where organization_type_id = 2 and organization_name not in ('District', 'Pearson', '" + orgName + "') order by organization_name asc"; 
        log.info( "Extracting Org list displayed under Transfer To drop-down..." );
        return SqlHelper.executeQuery( queryString );        
    }
    
    public static void updateLicenseKeyWithExpiredDate(String licenseKey){
        String queryString = "update school.license set license_expiration_date = '2012-01-01 00:00:00+00' where license_key='"+ licenseKey + "'";        
        SqlHelper.executeUpdateQuery( queryString );
        log.info( "License Key updated with expired date" );
    }   
    
    public static List<Object[]> getAllOrgNames(){
        String queryString = "Select organization_name from school.organization where organization_type_id = 2 and organization_name not in ('Pearson') order by organization_name asc"; 
        log.info( "Extracting Org list displayed under Transfer To drop-down..." );
        return SqlHelper.executeQuery( queryString );        
    }
    
    public static List<Object[]> getAllSchoolsData(){
        String queryString = "SELECT concat(organization_name, ' ',Lic_Type, ' ', id, sub_name) as lic_org_name"
                + ",lic_expiry_date,seats_allowed,in_use_licenses from school.organization "
                + "org inner join (SELECT row_number() over(partition by organization_id,sub_count order by "
                + "organization_id, sum_subject_id desc, seats_allowed, lic_expiry_date asc) as id, "
                + "organization_id, sub_count,Lic_Type,sub_name,seats_allowed,in_use_licenses,"
                + "lic_expiry_date from (SELECT organization_id, count(license_key) as sub_count, sum(subject_id) as sum_subject_id, "
                + "CASE WHEN count(license_key) =2 THEN 'Flexible' ELSE 'Single' END as Lic_Type, CASE WHEN sum(subject_id) = 3 THEN '' WHEN sum(subject_id) = 2 THEN ' - Language Arts' WHEN sum(subject_id) = 1 THEN ' - Mathematics' END as sub_name, "
                + "CASE WHEN avg(allow_bundl_con_users) > avg(allow_lic_con_users) THEN cast(avg(allow_lic_con_users) as int) ELSE cast(avg(allow_bundl_con_users) as int) END seats_allowed, CASE WHEN date_part('year',  "
                + "max(license_expiration_date)) = 1 THEN 'None' ELSE to_char(max(license_expiration_date), 'yyyy/mm/dd') "
                + "END as lic_expiry_date, cast(avg(current_number_lic_in_use) as int) as in_use_licenses from license group by license_key,organization_id order by organization_id) as subtab) lictab ON "
                + "(org.organization_id = lictab.organization_id) where org.organization_TYPE_ID IN (1,2) order by lic_org_name";
        log.info( "Extracting list of license names for all the schools..." );
        return SqlHelper.executeQuery( queryString );
    }
        
    public static List<Object[]> getSchoolSpecificData(String orgName){
        String queryString = "SELECT concat(organization_name, ' ',Lic_Type, ' ', id, sub_name) as lic_org_name,lic_expiry_date,seats_allowed,"
                + "in_use_licenses from school.organization org inner join (SELECT row_number() over(partition by organization_id,sub_count order by organization_id, "
                + "sum_subject_id desc, seats_allowed, lic_expiry_date asc) as id, organization_id, sub_count,Lic_Type,sub_name,seats_allowed,in_use_licenses,lic_expiry_date "
                + "from (SELECT organization_id, count(license_key) as sub_count, sum(subject_id) as sum_subject_id, CASE WHEN count(license_key) =2 THEN 'Flexible' "
                + "ELSE 'Single' END as Lic_Type, CASE WHEN sum(subject_id) = 3 THEN '' WHEN sum(subject_id) = 2 THEN ' - Language Arts' WHEN sum(subject_id) = 1 "
                + "THEN ' - Mathematics' END as sub_name, CASE WHEN avg(allow_bundl_con_users) > avg(allow_lic_con_users) THEN cast(avg(allow_lic_con_users) as int) "
                + "ELSE cast(avg(allow_bundl_con_users) as int) END seats_allowed, CASE WHEN date_part('year',  max(license_expiration_date)) = 1 "
                + "THEN 'None' ELSE to_char(max(license_expiration_date), 'yyyy/mm/dd') END as lic_expiry_date, cast(avg(current_number_lic_in_use) as int) as in_use_licenses "
                + "from license group by license_key,organization_id order by organization_id) as subtab) lictab ON (org.organization_id = lictab.organization_id) where org.organization_TYPE_ID IN (1,2) "
                + "AND org.organization_name='" + orgName + "' order by lic_org_name";
        log.info( "Extracting list of license names for Org Name:: " + orgName );
        return SqlHelper.executeQuery( queryString );        
    }    
}
