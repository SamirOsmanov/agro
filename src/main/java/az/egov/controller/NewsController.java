package az.egov.controller;

import az.egov.entity.News;
import az.egov.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 10.09.2018.
 */
@RestController
@RequestMapping("/agroculture/news")
public class NewsController {

    @Autowired
    private NewsService newsService ;

    @PostMapping("/save")
    public Object saveNews(@RequestBody News entity)
    {
        System.out.println(entity.toString());
        return newsService.save(entity) ;
    }

    @GetMapping("/find")
    public Object findNews(@RequestParam("id") Integer newsId)
    {
        return newsService.findById(newsId) ;
    }

    @GetMapping("/list")
    public Object newsPagination(@RequestParam("offset") Integer offset,
                                 @RequestParam("fetch") Integer fetch )
    {

        Map<String,List<News>> listMap = new HashMap<>() ;
        listMap.put("items",newsService.list(offset,fetch) ) ;
        return listMap ;
    }
}
