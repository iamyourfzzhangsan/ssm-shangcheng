package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.AdminMapper;
import com.bjpowernode.pojo.Admin;
import com.bjpowernode.pojo.AdminExample;
import com.bjpowernode.service.AdminService;
import com.bjpowernode.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {
//    在业务逻辑层中，一定会有数据访问层的对象
    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin login(String name, String pwd) {
        AdminExample example = new AdminExample();
//        添查询条件
        example.createCriteria().andANameEqualTo(name);
//        执行查询操作返回list
        List<Admin> list =adminMapper.selectByExample(example);
        if (list.size()>0){
            Admin admin = list.get(0);
            String miPwd = MD5Util.getMD5(pwd);
            if (pwd.equals(admin.getaPass())){

                return admin;
            }


        }
        return null;
    }
}
