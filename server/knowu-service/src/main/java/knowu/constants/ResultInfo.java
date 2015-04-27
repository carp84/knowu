/**
 * 
 */
package knowu.constants;

/**
 * @author liumingde
 */
public enum ResultInfo {

  SUCCESS("200", "success"), INTERNAL_ERROR("500",
      "request invoke failed due to some unkonwn reason"), LOGIN_FAIL("401",
      "Wrong username/password");

  public String code;
  public String message;

  ResultInfo(String code, String message) {
    this.code = code;
    this.message = message;
  }

}