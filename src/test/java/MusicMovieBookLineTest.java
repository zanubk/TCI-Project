import TCI.Book;
import TCI.Movie;
import TCI.Music;
import TCI.MusicMovieBookLine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MusicMovieBookLineTest {

    private MusicMovieBookLine musicMovieBookLine;

    @Before
    public void Setup()
    {
        musicMovieBookLine = new MusicMovieBookLine();}

    @Test
    public void IfFillsUpBookList()
    {
        Book bk = mock(Book.class);
        List<Book> mockbooks = new ArrayList<>();
        mockbooks.add(bk);
        when(mockbooks.get(0).getIsbn()).thenReturn("007-6092046981");
        musicMovieBookLine.setBooks(mockbooks);
        Assert.assertEquals("007-6092046981",musicMovieBookLine.getBooks().get(0).getIsbn());
    }

    @Test
    public void IfFillsUpMusicList()
    {
        Music music = mock(Music.class);
        List<Music> mockmusics = new ArrayList<>();
        mockmusics.add(music);
        when(mockmusics.get(0).getArtist()).thenReturn("Garth Brooks");
        musicMovieBookLine.setMusics(mockmusics);
        Assert.assertEquals("Garth Brooks",musicMovieBookLine.getMusics().get(0).getArtist());
    }
    @Test
    public void IfFillsUpMovieList()
    {
        Movie movie = mock(Movie.class);
        List<Movie> mockmovies = new ArrayList<>();
        mockmovies.add(movie);
        when(mockmovies.get(0).getDirector()).thenReturn("Robert Zemeckis");
        musicMovieBookLine.setMovies(mockmovies);
        Assert.assertEquals("Robert Zemeckis",musicMovieBookLine.getMovies().get(0).getDirector());
    }
}
