package com.awb.service.impl;

import com.awb.entity.User;
import com.awb.mapper.LogMapper;
import com.awb.mapper.UserMapper;
import com.awb.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author awb
 * @since 2020-04-04 11:27:23
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private LogMapper logMapper;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userMapper.queryById(id);
    }
    /**
     * 通过对象查询单条数据
     *
     * @return 实例对象
     */
    @Override
    public User queryByUser(User user) {
        User result = this.userMapper.queryUser(user);
        if (null==result){
            return null;
        }
        return result;
    }


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userMapper.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userMapper.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userMapper.deleteById(id) > 0;
    }

    @Override
    public List<String> search(String key) {
        List<User> user = userMapper.getUser(key);
        if (null==user){
            return null;
        }
        List<String> result=new ArrayList<String>();
        for (User u:user){
            Integer id = u.getId();
            if (key.equals(u.getNickname())){
                result.add("昵称为"+key+"的最后登录时间为:"+logMapper.getDate(id));
            }
            if (key.equals(u.getUsername())){
                result.add("用户名为"+key+"的最后登录时间为:"+logMapper.getDate(id));
            }
        }
        return result;
    }

    /**
     * @author awb
     * @description
     *  为修改密码提供密码验证
     * @date create in 21:24 2020/4/18
     * @param
     * @return
    */
    @Override
    public boolean checkPassword(User user) {
        User user1 = userMapper.queryUser(user);
        if (null!=user1){
            return true;
        }
        return false;
    }


}