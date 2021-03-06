package ch.educatech;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ws")
public class VideoController {

    @Autowired
    private VideoRepository repository;

    @RequestMapping(path="/category/{category}",method=RequestMethod.GET)
    public @ResponseBody List<Video> findVideoBySubjet(@PathVariable String category) {
        return repository.findByCategory(category);
    }
    
    @RequestMapping(value="pedagogic",method=RequestMethod.POST)
    public @ResponseBody Video pedagogocSubmit( @RequestBody final  Video video) {
    	repository.save(video);
        return video;
    }

}
