/**
 * 
 */
package knowu.persistence.dao;

import knowu.persistence.dataobject.TraceDataDO;

/**
 * @author liumingde
 */
public interface TraceDataDAO {

  void insert(TraceDataDO traceDO);

  TraceDataDO select(String userId);

  void update(TraceDataDO traceDO);

}
