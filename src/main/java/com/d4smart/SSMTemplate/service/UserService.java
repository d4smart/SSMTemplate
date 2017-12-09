package com.d4smart.SSMTemplate.service;

import com.d4smart.SSMTemplate.common.Const;
import com.d4smart.SSMTemplate.common.ServerResponse;
import com.d4smart.SSMTemplate.dao.UserMapper;
import com.d4smart.SSMTemplate.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by d4smart on 2017/12/9 16:34
 */
@Service("userService")
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public ServerResponse<String> register(User user) {
        if (user.getUsername() == null || user.getEmail() == null || user.getPassword() == null) {
            return ServerResponse.createByErrorMessage("用户信息不完整");
        }

        // todo 唯一性检查

        user.setStatus(Const.Role.NORMAL);

        if (userMapper.insertSelective(user) != 1) {
            return ServerResponse.createByErrorMessage("用户注册失败");
        }

        return ServerResponse.createBySuccessMessage("用户注册成功");
    }

    public ServerResponse<User> login(User param) {
        User user = userMapper.selectUser(param);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户名与密码不匹配");
        }

        user.setPassword(null);

        return ServerResponse.createBySuccess("登陆成功", user);
    }

    public ServerResponse<User> update(User user) {
        // todo 唯一性检查

        user.setStatus(null);

        if (userMapper.updateByPrimaryKeySelective(user) != 1) {
            return ServerResponse.createByErrorMessage("更新用户信息失败");
        }

        user = userMapper.selectByPrimaryKey(user.getId());
        user.setPassword(null);
        return ServerResponse.createBySuccess("更新用户信息成功", user);
    }
}
