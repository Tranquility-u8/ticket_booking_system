package cn.server.auth.serivce.impl;


import cn.server.auth.mapper.RoleMapper;
import cn.server.auth.entity.Role;
import cn.server.auth.serivce.IRoleService;
import cn.server.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("roleService")
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> listRoleByUserId(Long userid) {
        return roleMapper.listRoleByUserId(userid);
    }



    @Override
    public void saveRole(Role role) {
        roleMapper.saveRole(role);
    }

    @Override
    @Transactional
    public void addRolePermission(String roleId, List permissionIds) {

        List rolePermissionList = new ArrayList();
        for (Object permissionId : permissionIds) {
            Map mp = new HashMap();
            mp.put("roleId",roleId);
            mp.put("permissionId",permissionId);
            rolePermissionList.add(mp);
        }
        //先删除角色对应的权限
        roleMapper.deleteRolePermission(Long.parseLong(roleId));
        //添加角色对应的权限
        roleMapper.addRolePermission(rolePermissionList);

    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        roleMapper.deleteRole(id);
    }


    @Transactional
    @Override
    public void addUserRole(String userId, List roleIds) {
        List userRolesList = new ArrayList();
        for (Object roleId : roleIds) {
            Map mp = new HashMap();
            mp.put("userId",userId);
            mp.put("roleId",roleId);
            userRolesList.add(mp);
        }
        //先删除用户角色
        roleMapper.deleteUserRole(Long.parseLong(userId));
        //添加用户角色
        roleMapper.addUserRole(userRolesList);
    }
}
