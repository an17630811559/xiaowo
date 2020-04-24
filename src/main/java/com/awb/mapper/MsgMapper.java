package com.awb.mapper;

import com.awb.entity.Msg;
import com.awb.entity.vo.MsgAndUser;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Msg)表数据库访问层
 *
 * @author awb
 * @since 2020-04-04 14:58:33
 */
public interface MsgMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Msg queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @return 对象列表
     */
    Page<Msg> queryAllByLimit();

    Page<MsgAndUser> queryMsgAndUserByLimit();

    Page<MsgAndUser> queryMsgAndUserByUid(@Param("uid") Integer uid);
    /**
     * 通过实体作为筛选条件查询
     *
     * @param msg 实例对象
     * @return 对象列表
     */
    List<Msg> queryAll(Msg msg);

    /**
     * 新增数据
     *
     * @param msg 实例对象
     * @return 影响行数
     */
    int insert(Msg msg);

    /**
     * 修改数据
     *
     * @param msg 实例对象
     * @return 影响行数
     */
    int update(Msg msg);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}