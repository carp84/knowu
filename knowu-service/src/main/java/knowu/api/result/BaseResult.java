/**
 * 
 */
package knowu.api.result;

import lombok.Getter;
import lombok.Setter;

/**
 * @author liumingde
 */
@Getter
@Setter
public class BaseResult {

  private String requestId;
  private boolean success = true;
  private String code;
  private String message;

}
