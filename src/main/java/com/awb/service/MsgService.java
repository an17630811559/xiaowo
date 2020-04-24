package com.awb.service;

import com.awb.entity.Msg;
import com.awb.entity.User;
import com.awb.entity.vo.MsgAndUser;

import java.util.List;

/**
 * (Msg)表服务接口
 *
 * @author awb
 * @since 2020-04-04 14:58:34
 */
public interface MsgService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Msg queryById(Integer id);

    /**
     * 查询多条数据
     * @return 对象列表
     */
    List<MsgAndUser> queryAllByLimit();

    List<MsgAndUser> queryAllByUid(Integer uid);

    /**
     * 新增数据
     *
     * @param msg 实例对象
     * @return 实例对象
     */
    Msg insert(Msg msg);

    /**
     * 修改数据
     *
     * @param msg 实例对象
     * @return 实例对象
     */
    Msg update(Msg msg);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);



}