package com.awb.service.impl;

import com.awb.entity.Msg;
import com.awb.entity.User;
import com.awb.entity.vo.MsgAndUser;
import com.awb.mapper.MsgMapper;
import com.awb.service.MsgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Msg)表服务实现类
 *
 * @author awb
 * @since 2020-04-04 14:58:34
 */
@Service("msgService")
public class MsgServiceImpl implements MsgService {
    @Resource
    private MsgMapper msgMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Msg queryById(Integer id) {
        return this.msgMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<MsgAndUser> queryAllByLimit() {
        return this.msgMapper.queryMsgAndUserByLimit();
    }

    /**
     * 根据uid查询多条数据
     * @return
     */
    @Override
    public List<MsgAndUser> queryAllByUid(Integer uid) {

        return this.msgMapper.queryMsgAndUserByUid(uid);
    }

    /**
     * 新增数据
     *
     * @param msg 实例对象
     * @return 实例对象
     */
    @Override
    public Msg insert(Msg msg) {
        this.msgMapper.insert(msg);
        return msg;
    }

    /**
     * 修改数据
     *
     * @param msg 实例对象
     * @return 实例对象
     */
    @Override
    public Msg update(Msg msg) {
        this.msgMapper.update(msg);
        return this.queryById(msg.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.msgMapper.deleteById(id) > 0;
    }


}