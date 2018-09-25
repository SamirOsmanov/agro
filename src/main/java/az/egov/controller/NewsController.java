package az.egov.controller;

import az.egov.entity.News;
import az.egov.service.NewsService;
import az.egov.utility.helper.OperationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static az.egov.utility.helper.OperationStatus.* ;
import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 10.09.2018.
 */
@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService ;

    @PostMapping("/save")
    public Object saveNews(@RequestBody News entity)
    {
        entity.setStatusId(INSERT_STATUS.getStatusId());
        entity.setPublish(new Date());
        return newsService.save(entity) ;
    }

    @GetMapping("/{id}")
    public Object findNews(@PathVariable("id") Integer newsId)
    {

        return newsService.findById(newsId) ;
    }

    @GetMapping("/list")
    public Object newsPagination(@RequestParam("offset") Integer offset,
                                 @RequestParam("fetch") Integer fetch )
    {

        Map<String,Object> listMap = new HashMap<>() ;
        listMap.put("items",newsService.list(offset,fetch) ) ;
        listMap.put("totalCount", newsService.totalCount()  ) ;
        return listMap ;
    }


    @PostMapping("/find")
    public Object findNews(@RequestBody News news)
    {
        Map<String,Object> response = new HashMap<>() ;
        response.put("items",newsService.findByNewsAndStatusId(news,DELETE_STATUS.getStatusId()) );

        return response ;
    }

    @DeleteMapping("/delete/{id}")
    public Object deleteNews(@PathVariable("id") Integer newsId)
    {
        HashMap<String,Object> response = new HashMap<>() ;

        try
        {
            News entity = newsService.findById(newsId) ;
            entity.setStatusId(DELETE_STATUS.getStatusId());

            if(newsService.update(entity) != null)
            {
                response.put("success",true) ;
                response.put("descr","Successfully deleted news") ;
            }
            else
            {
                response.put("success",true) ;
                response.put("descr","No rows deleted in the database") ;
            }
        }
        catch(Exception e)
        {
            response.put("success",false) ;
            response.put("errm",e.getMessage()) ;
        }

        return response ;
    }

    @PutMapping("/update")
    public Object updateNews(@RequestBody News entity)
    {
        HashMap<String,Object> response = new HashMap<>() ;
        entity.setStatusId(UPDATE_STATUS.getStatusId());

        News findNews = newsService.findById(entity.getId()) ;
        findNews.setPublish(entity.getPublish());
        findNews.setStatusId(entity.getStatusId());
        findNews.setContent(entity.getContent());
        findNews.setDescription(entity.getDescription());
        findNews.setImage(entity.getImage());
        findNews.setSubTitle(entity.getSubTitle());
        findNews.setTitle(entity.getTitle());




        try
        {
            News updated = newsService.update(findNews);
            if( updated != null)
            {
               response.put("success",true) ;
               response.put("item",updated) ;
               response.put("descr","Successfully updated news") ;
            }
            else
            {
                response.put("success",true) ;
                response.put("descr","No rows updated in the database") ;
            }
        }
        catch(Exception e)
        {
            response.put("success",false) ;
            response.put("errm",e.getMessage()) ;
        }

        return response ;
    }
}
