/**
 * 
 */
package knowu.api.result;

import lombok.Setter;

import lombok.Getter;

/**
 * @author liumingde
 */
@Getter
@Setter
public class PageResult<T> extends PlainResult<T> {

  private Integer pageNumber;
  private Integer pageSize;
  private Integer totalPage;

}
