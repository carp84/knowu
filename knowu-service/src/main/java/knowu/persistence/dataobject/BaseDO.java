/**
 * 
 */
package knowu.persistence.dataobject;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author liumingde
 */
@Getter
@Setter
public class BaseDO {

  private Date gmtCreated;
  private Date gmtModified;
  private Integer version;

}
