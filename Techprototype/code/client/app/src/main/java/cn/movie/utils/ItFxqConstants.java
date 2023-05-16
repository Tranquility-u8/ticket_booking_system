package cn.movie.utils;

public class ItFxqConstants {


    public static final String CHARSET = "utf-8";

    //服务器IP
    public static final String BASEURL = "http://10.181.106.208:80";

    public static final String MOIVE_URL = BASEURL+"/frontmovie/getMovieByHouseid";

    public static final String MOIVEHOUSE_URL = BASEURL+"/frontmovie/allmoviehouse";
    public static final String MOIVESEAT_URL = BASEURL+"/frontorder/queryisCheckSeat";

    public static final String LOGIN_URL = BASEURL+"/frontuser/login";

    public static final String ORDER_URL = BASEURL+"/frontorder/add";

    public static final String MYORDER_URL = BASEURL+"/frontorder/queryOrderByUsername";

    public static final String REG_URL = BASEURL+"/frontuser/reg";


    public static final int OK_STATUS = 200;

    public static final int ERROR_STATUS = 500;

    public static final String LOGIN_USER_KEY = "loginUser";

}
