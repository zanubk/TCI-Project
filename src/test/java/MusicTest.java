import TCI.Music;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MusicTest {
    private Music music;

    @Before
    public void Setup()
    {
        music = new Music();}

    @Test
    public void IfMusicNameSetAndGet()
    {

    }

    @Test
    public void IfMusicGenreSetAndGet()
    {
        music.setGenre("Rock");
        Assert.assertEquals("Rock", music.getGenre());
    }

    @Test
    public void IfBookFormatSetAndGet()
    {
        music.setFormat("Vinyl");
        Assert.assertEquals("Vinyl", music.getFormat());
    }
    @Test
    public void IfBookYearSetAndGet()
    {
        music.setYear("2015");
        Assert.assertEquals("2015", music.getYear());
    }


    @Test
    public void IfBookArtistSetAndGet()
    {
        music.setArtist("Elvis Presley");
        Assert.assertEquals("Elvis Presley", music.getArtist());
    }



}

