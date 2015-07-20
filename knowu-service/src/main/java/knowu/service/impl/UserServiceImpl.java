/**
 * 
 */
package knowu.service.impl;

import java.sql.Date;
import java.util.Calendar;

import knowu.api.result.BaseResult;
import knowu.api.result.PlainResult;
import knowu.constants.ResultInfo;
import knowu.persistence.dao.UserInfoDAO;
import knowu.persistence.dataobject.UserInfoDO;
import knowu.service.UserService;
import knowu.utils.ResultUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
  public BaseResult addUser(UserInfoDO userInfo) {
    return doAddUser(userInfo);
  }

  private BaseResult doAddUser(UserInfoDO userInfoDO) {
    UserInfoDO userToCheck = null;
    try {
      userToCheck = userInfoDAO.select(userInfoDO.getUserId());
    } catch (Exception e) {
      logger.error("database operation error", e);
      return ResultUtils.buildBaseResult(ResultInfo.DATABASE_ERROR);
    }
    if (userToCheck != null
        && userToCheck.getUserId().equals(userInfoDO.getUserId())) {
      return ResultUtils.buildBaseResult(ResultInfo.USER_ALREADY_EXISTS_ERROR);
    }
    try {
      userInfoDAO.insert(userInfoDO);
    } catch (Exception e) {
      logger.error("database operation error", e);
      return ResultUtils.buildBaseResult(ResultInfo.DATABASE_ERROR);
    }
    return ResultUtils.buildBaseResult(ResultInfo.SUCCESS);
  }

  @Override
  public BaseResult loginUser(UserInfoDO userInfo) {
    UserInfoDO userInfoForCheck = null;
    try {
      userInfoForCheck = userInfoDAO.select(userInfo.getUserId());
    } catch (Exception e) {
      logger.error("database operation error", e);
      return ResultUtils.buildBaseResult(ResultInfo.DATABASE_ERROR);
    }
    String passwd = userInfoForCheck.getPassword();
    if (passwd.equals(userInfo.getPassword())) {
      // check whether is first login
      if (userInfoForCheck.getFirstLogInDate() == null) {
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
    // set first login date here
    userInfoDO.setFirstLogInDate(new Date(Calendar.getInstance()
        .getTimeInMillis()));
    // check passed, begin update
    try {
      userInfoDAO.completeUserInfo(userInfoDO);
    } catch (Exception e) {
      logger.error("database operation error", e);
      return ResultUtils.buildBaseResult(ResultInfo.DATABASE_ERROR);
    }
    return ResultUtils.buildBaseResult(ResultInfo.SUCCESS);
  }

  @Override
  public PlainResult<Integer> getLoginDays(String userId) {
    Date firstLoginDate = null;
    try {
      firstLoginDate = userInfoDAO.getFirstLoginDate(userId);
    } catch (Exception e) {
      logger.error("database operation error", e);
      return ResultUtils.buildPlainResult(ResultInfo.DATABASE_ERROR, -1);
    }
    Calendar calendar = Calendar.getInstance();
    int currentDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
    calendar.setTime(firstLoginDate);
    int firstLoginDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
    int loginDays = currentDayOfYear - firstLoginDayOfYear;
    return ResultUtils.buildPlainResult(ResultInfo.SUCCESS, loginDays);
  }

  @Override
  public PlainResult<Integer> getPetType(String userId) {
    int petType = -1;
    Integer typeFromDB = null;
    try {
      typeFromDB = userInfoDAO.getPetType(userId);
    } catch (Exception e) {
      logger.error("database operation error", e);
      return ResultUtils.buildPlainResult(ResultInfo.DATABASE_ERROR, petType);
    }
    if (typeFromDB != null) {
      petType = typeFromDB;
    }
    return ResultUtils.buildPlainResult(ResultInfo.SUCCESS, petType);
  }
}
