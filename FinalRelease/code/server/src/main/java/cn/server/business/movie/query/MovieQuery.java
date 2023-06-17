package cn.server.business.movie.query;

import cn.server.common.query.BaseQuery;
import lombok.Data;


@Data
public class MovieQuery extends BaseQuery {
    private String moviename;
}
