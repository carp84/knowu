/**
 * 
 */
package knowu.persistence.dao.impl;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import knowu.persistence.dao.UserInfoDAO;
import knowu.persistence.dataobject.UserInfoDO;

/**
 * @author liumingde
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test.xml" })
public class UserInfoDAOImplTest {

  @Autowired
  private UserInfoDAO userInfoDAO;

  @Test
  public void testInsert() throws Exception {
    UserInfoDO userInfoDO = generateUserInfoDO();
    userInfoDAO.insert(userInfoDO);
  }

  @Test
  public void testSelect() throws Exception {
    userInfoDAO.select("user_id");
  }

  @Test
  public void testUpdate() throws Exception {
    UserInfoDO userInfoDO = generateUserInfoDO();
    userInfoDAO.update(userInfoDO);
  }

  private UserInfoDO generateUserInfoDO() {
    UserInfoDO userInfoDO = new UserInfoDO();
    userInfoDO.setUserId("user_id");
    userInfoDO.setPassword("password");
    userInfoDO.setEmailAddress("email_address");
    userInfoDO.setGender(1);
    userInfoDO.setHomeAddress("home_address");
    userInfoDO.setHomeAddressLocation("home_address_location");
    userInfoDO.setWorkAddress("work_address");
    userInfoDO.setWorkAddressLocation("work_address_location");
    userInfoDO.setBirthday(new Date(System.currentTimeMillis()));
    userInfoDO.setJobDescription("job_speciption");
    userInfoDO.setMobile("mobile");
    userInfoDO.setSpecificName("specific_name");
    userInfoDO.setUserType(1);
    userInfoDO.setOtherDescription("other_description");
    userInfoDO.setThumbnail("thumbnail".getBytes());
    userInfoDO.setFirstLogInDate(new Date(System.currentTimeMillis()));
    userInfoDO.setPetType(1);
    return userInfoDO;
  }

}
