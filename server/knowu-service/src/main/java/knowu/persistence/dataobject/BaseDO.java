/**
 * 
 */
package knowu.persistence.dataobject;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

/**
 * @author liumingde
 */
@Getter
@Setter
public class BaseDO {

  private Timestamp gmtCreated;
  private Timestamp gmtModified;
  private Integer version;

}
