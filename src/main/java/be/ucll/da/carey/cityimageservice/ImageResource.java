package be.ucll.da.carey.cityimageservice;

import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class ImageResource {
    private static String WIKIPEDIAURL = "https://en.wikipedia.org/w/api.php?action=query&prop=pageimages&format=json&piprop=original&titles=";
    private static final Pattern urlPattern = Pattern.compile("(http|ftp|https)://([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?");
    @GetMapping("/{city}")
    public String hello(@PathVariable String city) {
        try {
            String body = new Scanner(new URL(WIKIPEDIAURL + city).openStream(), "UTF-8")
                    .useDelimiter("\\A")
                    .next();
            Matcher m = urlPattern.matcher(body);
            if (m.find()) {
                return m.group(0);
            }
            return "";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
