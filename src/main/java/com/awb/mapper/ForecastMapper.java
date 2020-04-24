package com.awb.mapper;

import com.awb.entity.Forecast;
import com.awb.entity.vo.ForecastAndCasts2;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Forecast)表数据库访问层
 *
 * @author awb
 * @since 2020-04-09 11:02:39
 */
public interface ForecastMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Forecast queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Forecast> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param forecast 实例对象
     * @return 对象列表
     */
    List<Forecast> queryAll(Forecast forecast);

    /**
     * 新增数据
     *
     * @param forecast 实例对象
     * @return 影响行数
     */
    int insert(Forecast forecast);

    /**
     * 修改数据
     *
     * @param forecast 实例对象
     * @return 影响行数
     */
    int update(Forecast forecast);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 获取天气信息
     * @return
     */
    List<ForecastAndCasts2> getdata(@Param("city")String city);
}