package TCI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class TestController {
    CrawlInformation crawlInformation = new CrawlInformation();

    Spider spider = new Spider(crawlInformation);

    @RequestMapping("/getall")
    public @ResponseBody String GetAll() throws IOException {

        spider.GetAllLinks("http://i298537.hera.fhict.nl/TCI/index.php");
        MusicMovieBookLine bookLine = spider.GetAll();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String userJson = gson.toJson(bookLine);
        return userJson;

    }
    @RequestMapping("/Find")
    public @ResponseBody String Find(@RequestParam(value="name", defaultValue="Office Space") String name) throws IOException {

        spider.GetAllLinks("http://i298537.hera.fhict.nl/TCI/index.php");
        SearchedItemLine item = spider.GetBySearch(name);
        Gson gson = new Gson();
        String userJson = gson.toJson(item);
        return userJson;
    }
    @RequestMapping("/FindCrawlActionInformation")
    public @ResponseBody String CrawlingAction(@RequestParam(value="actionname", defaultValue="Find") String actionname,@RequestParam(value="name", defaultValue="The Clean Coder: A Code of Conduct for Professional Programmers") String name ) throws IOException {

        spider.GetAllLinks("http://i298537.hera.fhict.nl/TCI/index.php");
        String userJson;
        if(actionname.equals("Find"))
        {
            spider.GetBySearch(name);
            Gson gson = new Gson();
            userJson = gson.toJson(spider.crawl);
        }
        else
        {
            MusicMovieBookLine Line = spider.GetAll();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
           userJson = gson.toJson(spider.crawl);

        }

        return userJson;
    }

}
