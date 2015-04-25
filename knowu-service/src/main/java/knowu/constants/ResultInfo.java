/**
 * 
 */
package knowu.constants;

/**
 * @author liumingde
 */
public enum ResultInfo {

  SUCCESS("200", "success"), INTERNAL_ERROR("500",
      "request invoke failed due to some unkonwn reason");

  public String code;
  public String message;

  ResultInfo(String code, String message) {
    this.code = code;
    this.message = message;
  }

}
