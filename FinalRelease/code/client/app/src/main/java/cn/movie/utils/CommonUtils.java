package cn.movie.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cn.movie.entity.MovieEntity;
import cn.movie.entity.MovieManageEntity;
import cn.movie.entity.UserEntity;


public class CommonUtils extends Activity {



    public static String formatTime(int length){
        Date date = new Date(length);
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        String totalTime = sdf.format(date);
        return totalTime;
    }

    public static boolean isEmpty(String content){
        if(content == null || "".equals(content)){
            return true;
        }else{
            return false;
        }

    }

    public static void navigateTo(Context from, Class<?> to){
        Intent intent=new Intent();
        intent.setClass(from, to);
        from.startActivity(intent);
    }


    public static Activity getActivityByContext(Context context){
        while(context instanceof ContextWrapper){
            if(context instanceof Activity){
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }


    public static void navigateTo(Context from, Class<?> to, Bundle bundle){
        Intent intent=new Intent();
        intent.setClass(from, to);
        intent.putExtras(bundle);
        from.startActivity(intent);
    }

    //存储用户信息
    public static void storeLoginUser(Map userMap, Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("username",(String)userMap.get("username"));
        editor.putString("password",(String)userMap.get("password"));
        editor.putString("tel",(String)userMap.get("tel"));
        editor.putString("email",(String)userMap.get("email"));
        editor.commit();
        //设置登录 editor1
        SharedPreferences settings1 = context.getSharedPreferences("firstRun", 0);
        SharedPreferences.Editor editor1 = settings1.edit();
        editor1.putBoolean("firstRun",false);
        editor1.commit();



    }
    //清理用户的信息
    public static void clearStoreUser( Context context){

    }

    //获取用户的信息
    public static UserEntity getLoginUser(Context context){
        SharedPreferences settings = context.getSharedPreferences(ItFxqConstants.LOGIN_USER_KEY, 0);
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(settings.getString("username",""));
        userEntity.setPassword(settings.getString("password",""));
        userEntity.setEmail(settings.getString("email",""));
        userEntity.setTel(settings.getString("tel",""));
        return userEntity;
    }

    //订单生成
    public static String getOrderNum(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateStr = simpleDateFormat.format(date);
        String randomNum = String.format("%04d", new Random().nextInt(9999));
        return dateStr+randomNum;
    }

    public static void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1))
                + 15;
        listView.setLayoutParams(params);
    }

    public static List<MovieManageEntity> getMoviePPEntityList(MovieEntity movie){
        String pptimeStr= movie.getPptime();
        List<MovieManageEntity> moviePPEntityList = new ArrayList<>();
        if(pptimeStr != null){
            String[] ppStrArr = pptimeStr.split("-");
            for (int i = 0; i < ppStrArr.length; i++) {
                String ppStr = ppStrArr[i];
                String[] ppArr = ppStr.split(":");
                String pptime = ppArr[0]+":"+ppArr[1];
                String pparea = ppArr[2];
                MovieManageEntity moviePPEntity = new MovieManageEntity();
                moviePPEntity.setMovieid(movie.getId());
                moviePPEntity.setMoviename(movie.getMoviename());
                moviePPEntity.setMoviehousename(movie.getMovieHouse().getName());
                moviePPEntity.setPrice(movie.getPrice());
                moviePPEntity.setPpprice(movie.getPrice());
                moviePPEntity.setPptime(pptime);
                moviePPEntity.setPparea(pparea);
                moviePPEntityList.add(moviePPEntity);
            }
        }
        return moviePPEntityList;
    }







}
