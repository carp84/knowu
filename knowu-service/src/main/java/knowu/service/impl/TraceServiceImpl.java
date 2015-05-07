package knowu.service.impl;

import knowu.api.result.BaseResult;
import knowu.api.result.PlainResult;
import knowu.constants.ResultInfo;
import knowu.persistence.dao.TraceDataDAO;
import knowu.persistence.dataobject.TraceDataDO;
import knowu.service.TraceService;
import knowu.utils.ResultUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TraceServiceImpl implements TraceService {
  @Autowired
  private TraceDataDAO traceDataDAO;

  private static final Logger logger = LoggerFactory
      .getLogger(UserServiceImpl.class);

  @Override
  public BaseResult addTraceData(String userId, TraceDataDO data) {
    return doAddTrace(data);
  }

  private BaseResult doAddTrace(TraceDataDO traceDataDO) {
    try {
      traceDataDAO.insert(traceDataDO);
    } catch (Exception e) {
      logger.error("database operation error", e);
      return ResultUtils.buildBaseResult(ResultInfo.INTERNAL_ERROR);
    }
    return ResultUtils.buildBaseResult(ResultInfo.SUCCESS);
  }

  @Override
  public PlainResult<TraceDataDO> getTraceData(String userId) {
    // TODO Auto-generated method stub
    return null;
  }

}
