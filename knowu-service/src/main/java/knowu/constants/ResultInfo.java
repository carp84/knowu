/**
 * 
 */
package knowu.constants;

/**
 * @author liumingde
 */
public enum ResultInfo {

  SUCCESS("200", "success"),
  SUCCESS_FIRSTLOGIN("200", "first login success"),
  INTERNAL_ERROR("500", "request invoke failed due to some unkonwn reason"),
  DATABASE_ERROR("500", "request invoke failed due to database exception"),
  INCORRECT_PASSWORD("401", "Incorrect password"),
  EMPTY_USERID("400", "Passed userId is NULL"),
  LOGIN_FAIL("401", "Wrong username/password"),
  USER_ALREADY_EXISTS_ERROR("403","user already exists, please change user name and retry");

  public String code;
  public String message;

  ResultInfo(String code, String message) {
    this.code = code;
    this.message = message;
  }

}
