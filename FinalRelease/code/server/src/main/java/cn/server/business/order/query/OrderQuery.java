package cn.server.business.order.query;

import cn.server.common.query.BaseQuery;
import lombok.Data;


@Data
public class OrderQuery extends BaseQuery {
    private String username;
    private String ordernum;
    private Long movieid;
    private String housename;
    private String beginTime;
}
