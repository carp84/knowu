/**
 * 
 */
package knowu.persistence.dao;

import java.sql.Date;

import knowu.persistence.dataobject.UserInfoDO;

/**
 * @author liumingde
 */
public interface UserInfoDAO {

  void insert(UserInfoDO userInfoDO);

  UserInfoDO select(String userId);

  void update(UserInfoDO userInfoDO);

  void completeUserInfo(UserInfoDO userInfoDO);

  Integer getPetType(String userId);

  Date getFirstLoginDate(String userId);

}
