/**
 * 
 */
package knowu.persistence.dataobject;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户相关信息
 * @author liumingde
 */
@Getter
@Setter
public class UserInfoDO extends BaseDO {

  private String userId;
  private String password;
  private String emailAddress;
  /**
   * 1表示男性，2表示女性
   */
  private Integer gender;
  private String homeAddress;
  private String homeAddressLocation;
  private String workAddress;
  private String workAddressLocation;
  private Date birthday;
  private String jobDescription;
  private String mobile;
  private String specificName;
  /**
   * 1表示手机用户，2表示手表用户
   */
  private Integer userType;
  private String otherDescription;
  private byte[] thumbnail;
  private Date firstLogInDate;
  private Integer petType;

}
