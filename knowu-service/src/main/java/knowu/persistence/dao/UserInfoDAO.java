/**
 * 
 */
package knowu.persistence.dao;

import knowu.persistence.dataobject.UserInfoDO;

/**
 * @author liumingde
 */
public interface UserInfoDAO {

  void insert(UserInfoDO userInfoDO);

  UserInfoDO select(String userId);

  void update(UserInfoDO userInfoDO);

}
