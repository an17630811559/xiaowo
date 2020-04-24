package com.awb.service;

import com.awb.entity.Casts;

import java.util.List;

/**
 * (Casts)表服务接口
 *
 * @author awb
 * @since 2020-04-09 16:57:50
 */
public interface CastsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Casts queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Casts> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param casts 实例对象
     * @return 实例对象
     */
    Casts insert(Casts casts);

    /**
     * 修改数据
     *
     * @param casts 实例对象
     * @return 实例对象
     */
    Casts update(Casts casts);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}