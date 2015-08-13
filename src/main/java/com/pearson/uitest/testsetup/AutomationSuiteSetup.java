package com.pearson.uitest.testsetup;

import java.util.List;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.pearson.uitest.constants.AdminUIConstants;
import com.pearson.uitest.constants.OrganizationConstants;
import com.pearson.uitest.database.OrganizationSqlHelper;
import com.pearson.uitest.database.UserSqlHelper;
import com.pearson.uitest.driver.AdminWebDriver;

/**
 * AutomationSuiteSetup class to trigger the setup the test data prior to tests
 * begin
 * 
 * @author vgaddra
 *
 */
public class AutomationSuiteSetup {

    private OrganizationSqlHelper orgHelper;
    private UserSqlHelper userHelper;

    public AutomationSuiteSetup() {
        orgHelper = new OrganizationSqlHelper();
        userHelper = new UserSqlHelper();
    }

    @BeforeSuite
    public void testDataSetup() {
        setupData();
    }

    @AfterSuite
    @Parameters ( "browser" )
    public void cleanupResources( @Optional ( "FireFox-Win7" ) String browser ) {
        AdminWebDriver.getInstance( browser ).releaseDriver();
    }

    public void setupData() {
        cleanData();
        loadData();
    }

    private void cleanData() {
        orgHelper.deleteOrganization( AdminUIConstants.NEW_SCHOOL_NAME );        
        orgHelper.deleteOrganization( OrganizationConstants.UPDATE_EDIT_ORG_NAME );
        
        orgHelper.deleteOrganizationbyID(AdminUIConstants.NEW_SCHOOL_ID);
        orgHelper.deleteOrganizationbyID(OrganizationConstants.EDIT_MY_ORD_ID);
        orgHelper.deleteOrganizationbyID(OrganizationConstants.ADD_ORGANIZATION_ID);
        orgHelper.deleteOrganizationbyID(OrganizationConstants.DATA_SETTINGS_ORGANIZATION_ID);
        orgHelper.deleteOrganizationbyID(OrganizationConstants.ADDITIONAL_ORG_ID);
        
        userHelper.deleteAdmin( AdminUIConstants.SYSTEM_ADMIN_USERNAME );
        userHelper.deleteAdmin( AdminUIConstants.DISTRICT_ADMIN_USERNAME );
        userHelper.deleteAdmin( AdminUIConstants.SCHOOL_ADMIN_USERNAME );
        userHelper.deleteTeacher( AdminUIConstants.TEACHER_USERNAME );
        userHelper.deleteStudent( AdminUIConstants.STUDENT_USERNAME );
    }

    private void loadData() {
        int organizationId = orgHelper.createOrganization( AdminUIConstants.NEW_SCHOOL_NAME, AdminUIConstants.NEW_SCHOOL_ID );
        userHelper.createDistrictAdmin( AdminUIConstants.DISTRICT_ADMIN_FIRSTNAME, AdminUIConstants.DISTRICT_ADMIN_LASTNAME, AdminUIConstants.DISTRICT_ADMIN_USERNAME, AdminUIConstants.DEFAULT_ENCRYPTED_PASSWORD );
        userHelper.createSystemAdmin( AdminUIConstants.SYSTEM_ADMIN_FIRSTNAME, AdminUIConstants.SYSTEM_ADMIN_LASTNAME, AdminUIConstants.SYSTEM_ADMIN_USERNAME, AdminUIConstants.DEFAULT_ENCRYPTED_PASSWORD );
        userHelper.createSchoolAdmin( AdminUIConstants.SCHOOL_ADMIN_FIRSTNAME, AdminUIConstants.SCHOOL_ADMIN_LASTNAME, AdminUIConstants.SCHOOL_ADMIN_USERNAME, AdminUIConstants.DEFAULT_ENCRYPTED_PASSWORD, organizationId );
        Integer teacherId = userHelper.createTeacher( AdminUIConstants.TEACHER_FIRSTNAME, AdminUIConstants.TEACHER_LASTNAME, AdminUIConstants.TEACHER_USERNAME, AdminUIConstants.DEFAULT_ENCRYPTED_PASSWORD, organizationId );
        userHelper.createStudent( AdminUIConstants.STUDENT_FIRSTNAME, AdminUIConstants.STUDENT_LASTNAME, AdminUIConstants.STUDENT_USERNAME, AdminUIConstants.DEFAULT_ENCRYPTED_PASSWORD, organizationId, teacherId, AdminUIConstants.STUDENT_DEFAULT_GRADE );
    }

    public void deleteSchools() {
        List<String> orgList = orgHelper.getOrganizationNames( null );
        for ( String schoolName : orgList ) {
            orgHelper.deleteOrganization( schoolName );
        }
    }

    public static void main( String[] args ) {
        AutomationSuiteSetup helper = new AutomationSuiteSetup();
        helper.setupData();
    }
}
