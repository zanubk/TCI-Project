import TCI.Movie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class MovieTest {

private Movie movie;

@Before
public  void SetUp()
{
    movie = new Movie();
}

    @Test
    public void IfMovieNameSetAndGet()
    {
        movie.setName("MovieName");
        Assert.assertEquals("MovieName",movie.getName());

    }

    @Test
    public void IfMovieGenreSetAndGet()
    {
        movie.setGenre("Drama");
        Assert.assertEquals("Drama", movie.getGenre());
    }

    @Test
    public void IfMovieFormatSetAndGet()
    {
        movie.setFormat("DVD");
        Assert.assertEquals("DVD", movie.getFormat());
    }
    @Test
    public void IfMovieYearSetAndGet()
    {
        movie.setYear("1994");
        Assert.assertEquals("1994", movie.getYear());
    }


    @Test
    public void IfWritersArrayFilled()
    {
          Movie mockMovie = mock(Movie.class);
          String[] arrayOfWriters = new String[]{"FirstItem"};
          when(mockMovie.getWriters()).thenReturn(arrayOfWriters);
          movie.setWriters(arrayOfWriters);
          Assert.assertEquals(mockMovie.getWriters(),movie.getWriters());
          Mockito.verify(mockMovie, Mockito.times(1)).getWriters();

    }


}
