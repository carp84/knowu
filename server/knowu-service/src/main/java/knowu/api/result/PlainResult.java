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

  private T data;

}
