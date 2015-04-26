/**
 * 
 */
package knowu.controller;

import knowu.api.result.BaseResult;
import knowu.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

  @RequestMapping("/registerUser")
  @ResponseBody
  public BaseResult registerUser(@RequestParam("userId") String userId,
      @RequestParam("password") String password, @RequestParam("emailAddress") String emailAddress) {
    return userService.addUser(userId, password, emailAddress);
  }

}
