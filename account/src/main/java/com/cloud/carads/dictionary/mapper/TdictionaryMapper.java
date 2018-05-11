package com.cloud.carads.dictionary.mapper;

import com.cloud.carads.dictionary.entity.Tdictionary;
import com.cloud.carads.dictionary.entity.TdictionaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TdictionaryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dictionary
     *
     * @mbg.generated Fri May 11 23:07:46 CST 2018
     */
    long countByExample(TdictionaryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dictionary
     *
     * @mbg.generated Fri May 11 23:07:46 CST 2018
     */
    int deleteByExample(TdictionaryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dictionary
     *
     * @mbg.generated Fri May 11 23:07:46 CST 2018
     */
    int deleteByPrimaryKey(String dicCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dictionary
     *
     * @mbg.generated Fri May 11 23:07:46 CST 2018
     */
    int insert(Tdictionary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dictionary
     *
     * @mbg.generated Fri May 11 23:07:46 CST 2018
     */
    int insertSelective(Tdictionary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dictionary
     *
     * @mbg.generated Fri May 11 23:07:46 CST 2018
     */
    List<Tdictionary> selectByExample(TdictionaryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dictionary
     *
     * @mbg.generated Fri May 11 23:07:46 CST 2018
     */
    Tdictionary selectByPrimaryKey(String dicCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dictionary
     *
     * @mbg.generated Fri May 11 23:07:46 CST 2018
     */
    int updateByExampleSelective(@Param("record") Tdictionary record, @Param("example") TdictionaryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dictionary
     *
     * @mbg.generated Fri May 11 23:07:46 CST 2018
     */
    int updateByExample(@Param("record") Tdictionary record, @Param("example") TdictionaryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dictionary
     *
     * @mbg.generated Fri May 11 23:07:46 CST 2018
     */
    int updateByPrimaryKeySelective(Tdictionary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dictionary
     *
     * @mbg.generated Fri May 11 23:07:46 CST 2018
     */
    int updateByPrimaryKey(Tdictionary record);
}