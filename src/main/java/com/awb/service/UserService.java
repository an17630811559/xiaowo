package com.awb.service;

import com.awb.entity.User;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author awb
 * @since 2020-04-04 11:27:23
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);
    /**
     * 通过对象查询单条数据
     *
     * @return 实例对象
     */
    User queryByUser(User user);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 搜索
     */
    List search(String key);

    /**
     * 为修改密码提供原密码判断
     * @param user
     * @return
     */
    boolean checkPassword(User user);

}