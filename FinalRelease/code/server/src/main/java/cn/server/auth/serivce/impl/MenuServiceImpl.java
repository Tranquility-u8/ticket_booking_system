package cn.server.auth.serivce.impl;



import cn.server.auth.entity.Menu;
import cn.server.auth.entity.Permission;
import cn.server.auth.mapper.MenuMapper;
import cn.server.auth.mapper.PermissionMapper;
import cn.server.auth.serivce.IMenuService;
import cn.server.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("menuService")
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Menu> findAll(Long loginUserid) {
        return menuMapper.findAll(loginUserid);
    }

    //查询所有的菜单
    @Override
    public List<Menu> queryAllMenu() {
        return menuMapper.queryAllMenu();
    }

    @Override
    public void addTopMenu(Menu menu) {
        menuMapper.addTopMenu(menu);
    }

    @Override
    @Transactional
    public void addSubMenu(Menu menu) {
        //保存子菜单
        menuMapper.addSubMenu(menu);
        //保存权限
        Permission permission = menu.getPermission();
        permission.setMenuid(menu.getId());
        permission.setPid(0L);
        //添加权限
        permissionMapper.addMenuPermission(permission);
    }

    //删除菜单
    @Override
    public void deleteMenuById(Long id) {
        //删除菜单已经它下面的子菜单
        menuMapper.deleteMenuById(id);

    }

    @Override
    public void editMenu(Menu menu) {
        menuMapper.editMenu(menu);
    }


}
