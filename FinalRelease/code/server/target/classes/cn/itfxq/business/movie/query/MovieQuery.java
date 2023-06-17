package cn.itfxq.business.movie.query;

import cn.itfxq.common.query.BaseQuery;
import lombok.Data;


@Data
public class MovieQuery extends BaseQuery {
    private String moviename;
}
