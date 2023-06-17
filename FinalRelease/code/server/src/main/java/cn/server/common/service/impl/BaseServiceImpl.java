package cn.server.common.service.impl;

import cn.server.common.mapper.BaseMapper;
import cn.server.common.page.PageList;
import cn.server.common.query.BaseQuery;
import cn.server.common.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @description: BaseServiceImpl
 * @author: marker
 * @email: 2579692606@qq.com
 * @date: created by 2020/12/22 17:27
 * @copyright: www.itxfq.cn 项目分享圈
 */


public class BaseServiceImpl<T> implements IBaseService<T> {

    @Autowired
    private BaseMapper<T> baseMapper;

    @Override
    public List<T> queryAll() {
        return baseMapper.queryAll();
    }

    @Override
    public PageList listpage(BaseQuery baseQuery) {
        PageList pageList  = new PageList();
        //查询总的条数
        Long total = baseMapper.queryTotal(baseQuery);
        List<T> users = baseMapper.queryData(baseQuery);
        pageList.setTotal(total);
        pageList.setRows(users);
        //分页查询的数据
        return pageList;
    }
}
