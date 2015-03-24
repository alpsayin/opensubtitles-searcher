/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subtitlesearcher;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Alp Sayin
 */
public class SubtitleSearcher
{
    public static final String SEARCH_URL = "http://www.opensubtitles.org/en/search/sublanguageid-eng/";
    private SearchType type;
    private String title;
    private int season;
    private int episode;
    private int year;
    private String conventionalSearchString;
    public SubtitleSearcher(SearchType type, String title, int season, int episode, int year)
    {
        this.type = type;
        this.title = title;
        this.season = season;
        this.episode = episode;
        this.year = year;
        this.conventionalSearchString = title+" "+encodeEpisodeIdentifier(season, episode);
    }    
    public SubtitleSearcher(SearchType type, String conventionalSearchString, int year)
    {
        this.type = type;
        this.year = year;
        this.conventionalSearchString = conventionalSearchString;
    }    
    public String generateSearchURL()
    {
        String result = SEARCH_URL;
        if(getType() == SearchType.SERIES)
        {
            result += "searchonlytvseries-on/";
            result += "season-"+getSeason()+"/";
            result += "episode-"+getEpisode()+"/";
        }
        else if(getType() == SearchType.MOVIES)
        {
            result += "searchonlymovies-on/";
        }
        
        if(getYear() > 0)
            result += "movieyearsign-1/movieyear-"+getYear()+"/";
        
        result += "moviename-"+getTitle().replaceAll(" ", "+");
        
        return result;
    }
    public void openBrowser()
    {
        if(Desktop.isDesktopSupported())
        {
            try
            {
                Desktop.getDesktop().browse(new URI(this.generateSearchURL()));
            }
            catch(IOException ioe)
            {
                ioe.printStackTrace();
            }
            catch(URISyntaxException use)
            {
                use.printStackTrace();
            }
        }
    }
    /**
     * @return the type
     */
    public SearchType getType()
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(SearchType type)
    {
        this.type = type;
    }

    /**
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * @return the season
     */
    public int getSeason()
    {
        return season;
    }

    /**
     * @param season the season to set
     */
    public void setSeason(int season)
    {
        this.season = season;
    }

    /**
     * @return the episode
     */
    public int getEpisode()
    {
        return episode;
    }

    /**
     * @param episode the episode to set
     */
    public void setEpisode(int episode)
    {
        this.episode = episode;
    }

    /**
     * @return the conventionalSearchString
     */
    public String getConventionalSearchString()
    {
        return conventionalSearchString;
    }

    /**
     * @param conventionalSearchString the conventionalSearchString to set
     */
    public void setConventionalSearchString(String conventionalSearchString)
    {
        this.conventionalSearchString = conventionalSearchString;
    }

    /**
     * @return the year
     */
    public int getYear()
    {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year)
    {
        this.year = year;
    }
    public static enum SearchType
    {
        MOVIES, SERIES;
    }
    public boolean decodeEpisodeIdentifier()
    {
        Pattern p = Pattern.compile("(.*?)[.\\s][sS](\\d{2})[eE](\\d{2}).*");
        Matcher m = p.matcher(getConventionalSearchString());
        if(m.matches())
        {
            setTitle(m.group(1));
            setSeason(Integer.parseInt(m.group(2)));
            setEpisode(Integer.parseInt(m.group(3)));
            return true;
        }
        else
        {
            setTitle(getConventionalSearchString());
            setSeason(-1);
            setEpisode(-1);
            return false;
        }
    }
    public static String encodeEpisodeIdentifier(int season, int episode)
    {
        String result = "s";
        if(season < 10)
            result += "0"+season;
        else
            result += season;
        
        result += "e";
        if(episode < 10)
            result += "0"+episode;
        else
            result += episode;
        return result;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
    }
    
}
