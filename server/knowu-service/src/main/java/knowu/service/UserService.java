/**
 * 
 */
package knowu.service;

import knowu.api.result.BaseResult;

/**
 * @author liumingde
 */
public interface UserService {

  BaseResult addUser(String userId, String password, String emailAddress);

  BaseResult loginUser(String userId, String password);

}
