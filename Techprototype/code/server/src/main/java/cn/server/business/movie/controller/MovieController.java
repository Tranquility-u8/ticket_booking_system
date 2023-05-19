package cn.server.business.movie.controller;

import cn.server.business.movie.entity.Movie;
import cn.server.business.movie.query.MovieQuery;
import cn.server.business.movie.service.IMovieService;
import cn.server.common.page.PageList;
import cn.server.common.result.ResultResponse;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;


@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @Value("${server.upload.path}")
    private String uploadMoviePath;




    @GetMapping("/index")
    public String index(String menuid, Model model){
        model.addAttribute("menuid",menuid);

        //电影首页 templates/views/movie/movie_list.html
        return "views/movie/movie_list";
    }



    @GetMapping("/listpage")
    @ResponseBody
    public PageList listpage(MovieQuery movieQuery){

        return  movieService.listpage(movieQuery);
    }

    //新增电影
    @PostMapping("/addMovie")
    @ResponseBody
    public int addMovie(Movie movie){
        movieService.addMovie(movie);
        Integer movieId = Integer.parseInt(movie.getId()+"");
        //添加成功返回电影id
        return movieId;
    }

    @PostMapping("/editSaveMovie")
    @ResponseBody
    public ResultResponse editSaveMovie(Movie movie){
        try{
            movieService.editSaveMovie(movie);
            return ResultResponse.ok();
        }catch(Exception e) {
            e.printStackTrace();
            return ResultResponse.fail("修改失败");
        }

    }

    @RequestMapping(value="/uploadMoviePic", method= RequestMethod.POST)
    @ResponseBody
    public ResultResponse uploadMoviePic(HttpServletRequest req, Integer id, @RequestParam("file") MultipartFile file){
        try {
            if(file.isEmpty()){
                return new ResultResponse("文件为空");
            }
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String uuidString = UUID.randomUUID().toString();
            String newFileName= uuidString + suffixName;

            String imgUploadPath = uploadMoviePath+"/movie";
            File path = new File(imgUploadPath);
            if (!path.exists()) path.mkdirs();

            File savefile = new File(path,newFileName);
            if (!savefile.getParentFile().exists()) savefile.getParentFile().mkdirs();
            file.transferTo(savefile);

            //更新电影表
            Movie movie = new Movie();
            movie.setId(Long.parseLong(id+""));
            movie.setFmUrl(newFileName);
            movieService.updateMoviePic(movie);

            return new ResultResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/showimg/{image_name}")
    public String showimg(@PathVariable("image_name") String image_name,HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        // 获得的系统的根目录
        File fileParent = new File(File.separator);
        // 获得/usr/CBeann目录
        System.out.println("读取头像:"+image_name);
        File file = null ;
        String os = System.getProperty("os.name");
        ServletOutputStream out = response.getOutputStream();
        try {
            if (os.toLowerCase().startsWith("win")) {  //如果是Windows系统
                String uploadMovieImgPath = uploadMoviePath+"/movie";
                file = new File(uploadMovieImgPath +"\\"+ image_name);
            } else {  //linux 和mac
                file = new File(fileParent, uploadMoviePath.substring(1) +"/"+ image_name);
            }
            IOUtils.copy(new FileInputStream(file),out);
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }

    //删除电影
    @GetMapping("/deleteMovie")
    @ResponseBody
    public ResultResponse deleteMovie(@RequestParam(required = true) Long id){
        ResultResponse ajaxResult = new ResultResponse();
        try {
            movieService.deleteMovie(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultResponse("删除失败");
        }

        return ajaxResult;
    }






}
