/**
 * 
 */
package knowu.persistence.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import knowu.persistence.dao.TraceDataDAO;
import knowu.persistence.dataobject.TraceDataDO;

/**
 * @author liumingde
 */
@Transactional
public class TraceDataDAOImpl implements TraceDataDAO {

  @Autowired
  private SqlSession sqlSession;

  /*
   * (non-Javadoc)
   * @see knowu.controller.TraceDataDAO#insert(knowu.persistence.dataobject.TraceDataDO)
   */
  @Override
  public void insert(TraceDataDO traceDO) {
    sqlSession.insert("knowu.persistence.mapper.TraceDataMapper.insert", traceDO);
  }

  /*
   * (non-Javadoc)
   * @see knowu.persistence.dao.TraceDataDAO#select(java.lang.String)
   */
  @Override
  public TraceDataDO select(String userId) {
    return sqlSession.selectOne("knowu.persistence.mapper.TraceDataMapper.select", userId);
  }

  /*
   * (non-Javadoc)
   * @see knowu.persistence.dao.TraceDataDAO#update(knowu.persistence.dataobject.TraceDataDO)
   */
  @Override
  public void update(TraceDataDO traceDO) {
    sqlSession.update("knowu.persistence.mapper.TraceDataMapper.insert", traceDO);
  }

}
