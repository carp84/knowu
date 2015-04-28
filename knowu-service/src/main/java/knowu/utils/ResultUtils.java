/**
 * 
 */
package knowu.utils;

import knowu.api.result.BaseResult;
import knowu.api.result.PageResult;
import knowu.api.result.PlainResult;
import knowu.constants.ResultInfo;

/**
 * @author liumingde
 */
public class ResultUtils {

  public static BaseResult newBaseResult() {
    BaseResult baseResult = new BaseResult();
    return baseResult;
  }

  public static <T> PlainResult<T> newPlainResult() {
    PlainResult<T> plainResult = new PlainResult<T>();
    return plainResult;
  }

  public static <T> PageResult<T> newPageResult() {
    PageResult<T> pageResult = new PageResult<T>();
    return pageResult;
  }

  public static BaseResult buildBaseResult(String code, String message) {
    return buildBaseResult(code, message, true);
  }

  public static BaseResult buildBaseResult(ResultInfo resultInfo) {
    if ("200".equals(resultInfo.code)) {
      return buildBaseResult(resultInfo.code, resultInfo.message, true);
    } else {
      return buildBaseResult(resultInfo.code, resultInfo.message, false);
    }
  }

  public static BaseResult buildBaseResult(String code, String message, boolean success) {
    BaseResult baseResult = newBaseResult();
    baseResult.setCode(code);
    baseResult.setMessage(message);
    baseResult.setSuccess(success);
    return baseResult;
  }

}
