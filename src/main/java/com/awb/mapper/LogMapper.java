package com.awb.mapper;

import com.awb.entity.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Log)表数据库访问层
 *
 * @author awb
 * @since 2020-04-06 16:44:13
 */
public interface LogMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Log queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Log> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param log 实例对象
     * @return 对象列表
     */
    List<Log> queryAll(Log log);

    /**
     * 新增数据
     *
     * @param log 实例对象
     * @return 影响行数
     */
    int insert(Log log);

    /**
     * 修改数据
     *
     * @param log 实例对象
     * @return 影响行数
     */
    int update(Log log);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 根据用户id查最后登录时间
     */
    String getDate(Integer id);
}