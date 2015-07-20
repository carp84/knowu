/**
 * 
 */
package knowu.service;

import knowu.api.result.BaseResult;
import knowu.api.result.PlainResult;
import knowu.persistence.dataobject.UserInfoDO;

/**
 * @author liumingde
 */
public interface UserService {

  BaseResult addUser(UserInfoDO userInfo);

  BaseResult loginUser(UserInfoDO userInfo);

  BaseResult completeUserInfo(UserInfoDO userInfo);

  PlainResult<Integer> getLoginDays(String userId);

  PlainResult<Integer> getPetType(String userId);

}
