package com.pearson.uitest.database;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.AdminUIConstants;

/**
 * This class is for user data setup It creates the various level of users
 * required for test execution
 * 
 * @author vgaddra
 * 
 */
public class UserSqlHelper {

    private static final Logger log = Logger.getLogger( UserSqlHelper.class );

    public UserSqlHelper() {

    }

    public Integer createStudent( String firstName, String lastName, String userName, String password, Integer schoolId, Integer teacherId, Integer grade ) {
        //"guid", "firstName", "middleName", "lastName", "username", "password", "dob", "genderId", "gradeId", "isDefault", "orgId", "userId", "teacherId", "contextId",  
        //"migrantStatusId", "raceId", "englishProfId","disabilityId", "specialServiceId", "econStatusId"
        Object[] paramValues = { UUID.randomUUID().toString(), firstName, "", lastName, userName, password, AdminUIConstants.MALE_GENDER_ID, grade == null ? 3 : grade, AdminUIConstants.DEFAULT_SCHOOL_ID, schoolId, userName,
                teacherId == null ? null : teacherId, userName, AdminUIConstants.DEFULT_DEMOGRAPHIC_ID, AdminUIConstants.DEFULT_DEMOGRAPHIC_ID, AdminUIConstants.DEFULT_DEMOGRAPHIC_ID, AdminUIConstants.DEFULT_DEMOGRAPHIC_ID,
                AdminUIConstants.DEFULT_DEMOGRAPHIC_ID, AdminUIConstants.DEFULT_DEMOGRAPHIC_ID };
        return SqlHelper.executeFunction( "{call successmaker.create_student(?,?,?,?,?,?, date '2001-10-05',?,?,?,?,?,?,?,?,?,?,?,?,?)}", paramValues );
    }

    public Integer createSchoolAdmin( String firstName, String lastName, String userName, String password, Integer schoolId ) {
        return createAdmin( firstName, lastName, userName, password, schoolId, AdminUIConstants.SCHOOL_ADMIN_PERSON_TYPE_ID );
    }

    public Integer createSystemAdmin( String firstName, String lastName, String userName, String password ) {
        return createAdmin( firstName, lastName, userName, password, AdminUIConstants.DEFAULT_DISTRICT_ID, AdminUIConstants.SYSTEM_ADMIN_PERSON_TYPE_ID );
    }

    public Integer createDistrictAdmin( String firstName, String lastName, String userName, String password ) {
        return createAdmin( firstName, lastName, userName, password, AdminUIConstants.DEFAULT_DISTRICT_ID, AdminUIConstants.DISTRICT_ADMIN_PERSON_TYPE_ID );
    }

    private Integer createAdmin( String firstName, String lastName, String userName, String password, Integer schoolId, Integer personTypeId ) {
        Object[] paramValues = { UUID.randomUUID().toString(), personTypeId, firstName, "", lastName, 4, userName, password, AdminUIConstants.DEFAULT_SCHOOL_ID, schoolId, userName, "", AdminUIConstants.MALE_GENDER_ID };
        return SqlHelper.executeFunction( "{call successmaker.create_admin(?,?,?,?,?,?,?,?,?,?,?,?,?)}", paramValues );
    }

    public Integer createTeacher( String firstName, String lastName, String userName, String password, Integer schoolId ) {
        //"guid", "personTypeId", "firstName", "middleName", "lastName", "titleId", "username", "password", "isDefault", "orgId", "staffId", "email", "genderId"
        return createAdmin( firstName, lastName, userName, password, schoolId, AdminUIConstants.TEACHER_PERSON_TYPE_ID );

    }

    public Integer createGroup( String groupName, Integer groupTypeId, Integer ownerPersonId, String descriptionText ) {
        Object[] paramValues = { groupName, groupTypeId, ownerPersonId, descriptionText };
        return SqlHelper.executeFunction( "{call create_group(?,?,?,?)}", paramValues );
    }

    public void deleteAdmin( String userName ) {
        deleteUser( userName, "delete_admin" );
    }

    public void deleteTeacher( String userName ) {
        deleteUser( userName, "delete_teacher" );
    }

    public void deleteStudent( String userName ) {
        deleteUser( userName, "delete_student" );
    }

    private void deleteUser( String userName, String functionName ) {
        List<Object[]> data = SqlHelper.executeQuery( "SELECT person_id FROM school.person WHERE username='" + userName + "'" );
        if ( data != null && !data.isEmpty() && data.get( 0 ).length > 0 ) {
            log.info( "Deleting user ->" + userName + " with id ->" + data.get( 0 )[0].toString() );
            Integer[] paramValues = {};
            SqlHelper.executeFunction( "{call successmaker." + functionName + "(ARRAY[" + Integer.valueOf( data.get( 0 )[0].toString() ) + "])}", paramValues );
        }
    }

    private List<String> getGradeList( List<Object[]> gradeListItems ) {
        List<String> gradeList = new ArrayList<String>();
        for ( Object[] grade : gradeListItems ) {
            if ( grade.length > 0 ) {
                gradeList.add( grade[0].toString() );
            }
        }
        return gradeList;
    }

    public List<String> getGradeListFromDB() {
        String queryString = "SELECT grade_name FROM successmaker.grade order by display_order asc;";
        return getGradeList( SqlHelper.executeQuery( queryString ) );
    }

}
