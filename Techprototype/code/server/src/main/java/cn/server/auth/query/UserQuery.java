package cn.server.auth.query;

import cn.server.common.query.BaseQuery;
import lombok.Data;


@Data
public class UserQuery extends BaseQuery {


    private String username;

    private String email;

    private Long type;//1表示管理员

}