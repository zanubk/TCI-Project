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

    Spider spider = new Spider();

    @RequestMapping("/getall")
    public @ResponseBody String GetAll() throws IOException {

        spider.GetAllLinks("http://i298537.hera.fhict.nl/TCI/index.php");
        MusicMovieBookLine bookLine = spider.GetAll();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String userJson = gson.toJson(bookLine);

        return userJson;

    }


}