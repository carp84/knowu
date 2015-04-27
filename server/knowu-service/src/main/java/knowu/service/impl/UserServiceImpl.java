/**
 * 
 */
package knowu.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import knowu.api.result.BaseResult;
import knowu.constants.ResultInfo;
import knowu.persistence.dao.UserInfoDAO;
import knowu.persistence.dataobject.UserInfoDO;
import knowu.service.UserService;
import knowu.utils.ResultUtils;

/**
 * @author liumingde
 */
public class UserServiceImpl implements UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  private UserInfoDAO userInfoDAO;

  /*
   * (non-Javadoc)
   * @see knowu.service.UserService#addUser(java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public BaseResult addUser(String userId, String password, String emailAddress) {
    UserInfoDO userInfoDO = new UserInfoDO();
    userInfoDO.setUserId(userId);
    userInfoDO.setPassword(password);
    userInfoDO.setEmailAddress(emailAddress);
    return doAddUser(userInfoDO);
  }

  private BaseResult doAddUser(UserInfoDO userInfoDO) {
    try {
      userInfoDAO.insert(userInfoDO);
    } catch (Exception e) {
      logger.error("database operation error", e);
      return ResultUtils.buildBaseResult(ResultInfo.INTERNAL_ERROR);
    }
    return ResultUtils.buildBaseResult(ResultInfo.SUCCESS);
  }

  @Override
  public BaseResult loginUser(String userId, String password) {
    UserInfoDO userInfo = null;
    try {
      userInfo = userInfoDAO.select(userId);
    } catch (Exception e) {
      logger.error("database operation error", e);
      return ResultUtils.buildBaseResult(ResultInfo.INTERNAL_ERROR);
    }
    String passwd = userInfo.getPassword();
    if (passwd.equals(password)) {
      return ResultUtils.buildBaseResult(ResultInfo.SUCCESS);
    } else {
      return ResultUtils.buildBaseResult(ResultInfo.LOGIN_FAIL);
    }
  }
}
