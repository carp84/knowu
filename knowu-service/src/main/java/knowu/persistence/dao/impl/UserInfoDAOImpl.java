/**
 * 
 */
package knowu.persistence.dao.impl;

import java.sql.Date;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import knowu.persistence.dao.UserInfoDAO;
import knowu.persistence.dataobject.UserInfoDO;

/**
 * @author liumingde
 */
@Transactional
public class UserInfoDAOImpl implements UserInfoDAO {

  @Autowired
  private SqlSession sqlSession;

  /*
   * (non-Javadoc)
   * @see knowu.persistence.dao.UserInfoDAO#insert(knowu.persistence.dataobject.UserInfoDO)
   */
  @Override
  public void insert(UserInfoDO userInfoDO) {
    sqlSession.insert("knowu.persistence.mapper.UserInfoMapper.insert", userInfoDO);
  }

  /*
   * (non-Javadoc)
   * @see knowu.persistence.dao.UserInfoDAO#select()
   */
  @Override
  public UserInfoDO select(String userId) {
    return sqlSession.selectOne("knowu.persistence.mapper.UserInfoMapper.select", userId);
  }

  /*
   * (non-Javadoc)
   * @see knowu.persistence.dao.UserInfoDAO#update(knowu.persistence.dataobject.UserInfoDO)
   */
  @Override
  public void update(UserInfoDO userInfoDO) {
    sqlSession.update("knowu.persistence.mapper.UserInfoMapper.update", userInfoDO);
  }

  @Override
  public void completeUserInfo(UserInfoDO userInfoDO) {
    sqlSession.update("knowu.persistence.mapper.UserInfoMapper.completeUserInfo", userInfoDO);
  }

  @Override
  public Integer getPetType(String userId) {
    return sqlSession.selectOne(
      "knowu.persistence.mapper.UserInfoMapper.selectPetType", userId);
  }

  @Override
  public Date getFirstLoginDate(String userId) {
    return sqlSession.selectOne(
      "knowu.persistence.mapper.UserInfoMapper.selectFirstLoginDate", userId);
  }

}
