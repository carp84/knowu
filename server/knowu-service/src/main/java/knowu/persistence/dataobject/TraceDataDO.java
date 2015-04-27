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
public class TraceDataDO extends BaseDO {

  private String userId;
  private Double latitude;
  private Double longitude;
  private Timestamp timestamp;
  private String address;
  private String action;
  private String otherDescription;
  /**
   * 1表示周一，7表示周日
   */
  private Integer dayOfWeek;

}
