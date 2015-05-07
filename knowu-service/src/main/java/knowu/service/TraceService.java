package knowu.service;

import knowu.api.result.BaseResult;
import knowu.api.result.PlainResult;
import knowu.persistence.dao.TraceDataDAO;
import knowu.persistence.dataobject.TraceDataDO;

public interface TraceService {
  /**
   * Insert one trace spot data into database
   * @param userId which user the trace data belongs to
   * @param data converted TraceDataDAO object
   * @return the result including a success flag and error-code/message if failed
   */
  BaseResult addTraceData(String userId, TraceDataDO data);

  PlainResult<TraceDataDO> getTraceData(String userId);
}
