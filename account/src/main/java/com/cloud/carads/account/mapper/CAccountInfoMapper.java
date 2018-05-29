package com.cloud.carads.account.mapper;

import com.cloud.carads.account.entity.CAccountInfo;
import com.cloud.carads.account.entity.CAccountInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CAccountInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table c_account_info
     *
     * @mbg.generated Tue May 29 23:15:07 CST 2018
     */
    long countByExample(CAccountInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table c_account_info
     *
     * @mbg.generated Tue May 29 23:15:07 CST 2018
     */
    int deleteByExample(CAccountInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table c_account_info
     *
     * @mbg.generated Tue May 29 23:15:07 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table c_account_info
     *
     * @mbg.generated Tue May 29 23:15:07 CST 2018
     */
    int insert(CAccountInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table c_account_info
     *
     * @mbg.generated Tue May 29 23:15:07 CST 2018
     */
    int insertSelective(CAccountInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table c_account_info
     *
     * @mbg.generated Tue May 29 23:15:07 CST 2018
     */
    List<CAccountInfo> selectByExample(CAccountInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table c_account_info
     *
     * @mbg.generated Tue May 29 23:15:07 CST 2018
     */
    CAccountInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table c_account_info
     *
     * @mbg.generated Tue May 29 23:15:07 CST 2018
     */
    int updateByExampleSelective(@Param("record") CAccountInfo record, @Param("example") CAccountInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table c_account_info
     *
     * @mbg.generated Tue May 29 23:15:07 CST 2018
     */
    int updateByExample(@Param("record") CAccountInfo record, @Param("example") CAccountInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table c_account_info
     *
     * @mbg.generated Tue May 29 23:15:07 CST 2018
     */
    int updateByPrimaryKeySelective(CAccountInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table c_account_info
     *
     * @mbg.generated Tue May 29 23:15:07 CST 2018
     */
    int updateByPrimaryKey(CAccountInfo record);
}