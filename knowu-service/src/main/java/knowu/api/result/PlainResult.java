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
public class PlainResult<T> extends BaseResult {

  public PlainResult() {
    super();
  }

  public PlainResult(BaseResult baseResult) {
    super();
    this.setCode(baseResult.getCode());
    this.setMessage(baseResult.getMessage());
    this.setSuccess(baseResult.isSuccess());
  }

  private T data;

}
