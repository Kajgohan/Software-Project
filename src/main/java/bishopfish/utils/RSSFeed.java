package bishopfish.utils;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RSSFeed {
    String output = "";
    URL feedUrl = new URL("https://www.medicinenet.com/rss/dailyhealth.xml");

    SyndFeedInput input = new SyndFeedInput();
    SyndFeed feed = input.build(new XmlReader(feedUrl));

    public RSSFeed() throws IOException, FeedException {
    }


    public String rssOut(){
        RSSFeed r = null;
        try {
            r = new RSSFeed();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        }

        ArrayList<SyndEntry> feedOut =(ArrayList<SyndEntry>) r.feed.getEntries();
        for(SyndEntry synd: feedOut){
            String formatsynd = synd.getDescription().toString().replace("SyndContentImpl.mode=null", "");
            formatsynd = formatsynd.replace("SyndContentImpl.type=text/html", "");
            formatsynd = formatsynd.replace("SyndContentImpl.interface=interface com.rometools.rome.feed.synd.SyndContent", "");
            formatsynd = formatsynd.replace("SyndContentImpl.value=Title: ", "");
            formatsynd = formatsynd.replace("<br />", "splithere");

            String[] splitarray = formatsynd.split("splithere");
            formatsynd = "";
            for (String S:splitarray){
                if (S.contains("Category : Health News")){
                    S = "";
                }
                if (S.contains("Last Editorial Review")){
                    S = "";
                }
                if (S.contains("Created")){
                    S = "";
                }
                  S = S.replace("Category: Health News", "");
                formatsynd += S;
            }
            output += formatsynd;
        }
        return output.replace("\n", "   ");
    }

    public static void main(String[] args) throws IOException, FeedException {
        RSSFeed R = new RSSFeed();

        System.out.println(R.rssOut());
    }
}


