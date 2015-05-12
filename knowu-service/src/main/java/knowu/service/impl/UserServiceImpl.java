/**
 * 
 */
package knowu.service.impl;

import java.sql.Date;

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

  private static final Logger logger = LoggerFactory
      .getLogger(UserServiceImpl.class);

  @Autowired
  private UserInfoDAO userInfoDAO;

  /*
   * (non-Javadoc)
   * @see knowu.service.UserService#addUser(java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public BaseResult
      addUser(String userId, String password, String emailAddress) {
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
      return ResultUtils.buildBaseResult(ResultInfo.DATABASE_ERROR);
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
      // check whether is first login
      if (userInfo.getFirstLogInDate() == null) {
        // don't set first login date until additional data uploaded
        return ResultUtils.buildBaseResult(ResultInfo.SUCCESS_FIRSTLOGIN);
      } else {
        return ResultUtils.buildBaseResult(ResultInfo.SUCCESS);
      }
    } else {
      return ResultUtils.buildBaseResult(ResultInfo.LOGIN_FAIL);
    }
  }

  @Override
  public BaseResult completeUserInfo(UserInfoDO userInfoDO) {
    // check password first to avoid malicious attack
    UserInfoDO userInfoForChek = null;
    String userId = userInfoDO.getUserId();
    if (userId == null) {
      logger.error("Passed userId is NULL");
      return ResultUtils.buildBaseResult(ResultInfo.EMPTY_USERID);
    }
    logger.debug("Checking password of userId [" + userId + "]");
    try {
      userInfoForChek = userInfoDAO.select(userInfoDO.getUserId());
    } catch (Exception e) {
      logger.error("database operation error", e);
      return ResultUtils.buildBaseResult(ResultInfo.DATABASE_ERROR);
    }
    String password = userInfoForChek.getPassword();
    if (password == null) {
      logger.error("Recorded passwod for userId [" + userId + "] is NULL");
      return ResultUtils.buildBaseResult(ResultInfo.INTERNAL_ERROR);
    }
    if (!password.equals(userInfoDO.getPassword())) {
      return ResultUtils.buildBaseResult(ResultInfo.INCORRECT_PASSWORD);
    }
    // check passed, begin update
    try {
      userInfoDAO.completeUserInfo(userInfoDO);
    } catch (Exception e) {
      logger.error("database operation error", e);
      return ResultUtils.buildBaseResult(ResultInfo.DATABASE_ERROR);
    }
    return ResultUtils.buildBaseResult(ResultInfo.SUCCESS);
  }
}
