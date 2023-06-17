package cn.server.common.service;


import cn.server.common.page.PageList;
import cn.server.common.query.BaseQuery;

import java.util.List;


public interface IBaseService<T> {

    //查询所有
    List<T> queryAll();
    //分页查询
    PageList  listpage(BaseQuery baseQuery);


}
