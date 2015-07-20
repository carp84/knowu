/**
 * 
 */
package knowu.controller;

import knowu.api.result.BaseResult;
import knowu.api.result.PlainResult;
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

  @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
  @ResponseBody
  public BaseResult registerUser(@RequestBody UserInfoDO userInfo) {
    return userService.addUser(userInfo);
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  @ResponseBody
  public BaseResult login(@RequestBody UserInfoDO userInfo) {
    return userService.loginUser(userInfo);
  }

  @RequestMapping(value = "/completeUserInfo", method = RequestMethod.POST)
  @ResponseBody
  public BaseResult completeUserInfo(@RequestBody UserInfoDO userInfo) {
    return userService.completeUserInfo(userInfo);
  }
  
  @RequestMapping(value = "/getLoginDays", method = RequestMethod.GET)
  @ResponseBody
  public PlainResult<Integer> getLoginDays(@RequestParam("userId") String userId) {
    return userService.getLoginDays(userId);
  }

  @RequestMapping(value = "/getPetType", method = RequestMethod.GET)
  @ResponseBody
  public PlainResult<Integer> getPetType(@RequestParam("userId") String userId) {
    return userService.getPetType(userId);
  }

  @RequestMapping(value = "/uploadTrace", method = RequestMethod.POST)
  @ResponseBody
  public BaseResult uploadTrace(@RequestParam("userId") String userId,
      @RequestBody TraceDataDO data) {
    return traceService.addTraceData(userId, data);
  }

}
