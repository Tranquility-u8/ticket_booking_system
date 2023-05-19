package cn.server.auth.serivce;




import cn.server.auth.entity.Permission;
import cn.server.common.service.IBaseService;

import java.util.List;

public interface IPermissionService extends IBaseService<Permission> {

    //根据用户查询权限
    List<Permission> listPermissionByUserId(Long userid);

    List<Permission> findAllPermisisons();
    /**
     * 添加页面按钮权限
     */
    void addBtnPermisison(Permission permission);
    /**
     * 删除权限
     */
    void deletePermisisonByPid(Long id);
    /**
     * 修改页面按钮权限
     */
    void editBtnPermisison(Permission permission);
}
