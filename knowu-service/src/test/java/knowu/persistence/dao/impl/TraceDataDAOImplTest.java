/**
 * 
 */
package knowu.persistence.dao.impl;

import java.sql.Timestamp;

import knowu.persistence.dao.TraceDataDAO;
import knowu.persistence.dataobject.TraceDataDO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liumingde
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test.xml" })
public class TraceDataDAOImplTest {

  @Autowired
  private TraceDataDAO traceDataDAO;

  @Test
  public void testInsert() throws Exception {
    TraceDataDO traceDataDO = generateTraceDataDO();
    traceDataDAO.insert(traceDataDO);
  }

  @Test
  public void testSelect() throws Exception {
    traceDataDAO.select("user_id");
  }

  @Test
  public void testUpdate() throws Exception {
    TraceDataDO traceDataDO = generateTraceDataDO();
    traceDataDAO.update(traceDataDO);
  }

  private TraceDataDO generateTraceDataDO() {
    TraceDataDO traceDataDO = new TraceDataDO();
    traceDataDO.setUserId("user_id");
    traceDataDO.setLatitude(1.0);
    traceDataDO.setLongitude(1.0);
    traceDataDO.setTimestamp(new Timestamp(System.currentTimeMillis()));
    traceDataDO.setAddress("address");
    traceDataDO.setAction("action");
    traceDataDO.setOtherDescription("other_description");
    traceDataDO.setDayOfWeek(1);
    return traceDataDO;
  }

}
