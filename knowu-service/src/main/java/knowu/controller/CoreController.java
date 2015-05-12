/**
 * 
 */
package knowu.controller;

import knowu.api.result.BaseResult;
import knowu.persistence.dataobject.TraceDataDO;
import knowu.persistence.dataobject.UserInfoDO;
import knowu.service.TraceService;
import knowu.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liumingde
 */
@Controller
@RequestMapping("/core")
public class CoreController {

  @Autowired
  private UserService userService;
  
  @Autowired
  private TraceService traceService;

  @RequestMapping("/registerUser")
  @ResponseBody
  public BaseResult registerUser(@RequestParam("userId") String userId,
      @RequestParam("password") String password, @RequestParam("emailAddress") String emailAddress) {
    return userService.addUser(userId, password, emailAddress);
  }

  @RequestMapping("/login")
  @ResponseBody
  public BaseResult login(@RequestParam("userId") String userId,
      @RequestParam("password") String password) {
    return userService.loginUser(userId, password);
  }

  @RequestMapping(value = "/completeUserInfo", method = RequestMethod.POST)
  @ResponseBody
  public BaseResult completeUserInfo(@RequestBody UserInfoDO userInfo) {
    return userService.completeUserInfo(userInfo);
  }

  @RequestMapping(value = "/uploadTrace", method = RequestMethod.POST)
  @ResponseBody
  public BaseResult uploadTrace(@RequestParam("userId") String userId,
      @RequestBody TraceDataDO data) {
    return traceService.addTraceData(userId, data);
  }

}
