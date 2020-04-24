package com.awb.service.impl;

import com.awb.entity.Casts;
import com.awb.mapper.CastsMapper;
import com.awb.service.CastsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Casts)表服务实现类
 *
 * @author awb
 * @since 2020-04-09 16:57:16
 */
@Service("castsService")
public class CastsServiceImpl implements CastsService {
    @Resource
    private CastsMapper castsMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Casts queryById(Integer id) {
        return this.castsMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Casts> queryAllByLimit(int offset, int limit) {
        return this.castsMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param casts 实例对象
     * @return 实例对象
     */
    @Override
    public Casts insert(Casts casts) {
        this.castsMapper.insert(casts);
        return casts;
    }

    /**
     * 修改数据
     *
     * @param casts 实例对象
     * @return 实例对象
     */
    @Override
    public Casts update(Casts casts) {
        this.castsMapper.update(casts);
        return this.queryById(casts.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.castsMapper.deleteById(id) > 0;
    }
}