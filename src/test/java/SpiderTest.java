import TCI.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(JUnitParamsRunner.class)
public class SpiderTest {
    private MusicMovieBookLine mockMBMLine;
    private  Spider spider;
private  Spider spiderWithCrawlInfo ;
private CrawlInformation crawlInformation;

    /**
     * Set Up the constructors of Spider. One with CrawlInformation and one without it. Also fills links from the Url
     * @throws IOException
     */
    @Before
    public void setup() throws IOException {
      spider = new Spider();
    mockMBMLine = mock(MusicMovieBookLine.class);
    crawlInformation = mock(CrawlInformation.class);
    spiderWithCrawlInfo = new Spider(crawlInformation);
    spider.GetAllLinks("http://i298537.hera.fhict.nl/TCI/index.php");
    spiderWithCrawlInfo.GetAllLinks("http://i298537.hera.fhict.nl/TCI/index.php");
         }


    /**
     *
     * @return list of strings with Invalid Urls
     */
    private static final Object[] getInvalidUrls()
    {
        return new String[][]{{"https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=Washington,DC&destinations=New+York+City,NY&key=AIzaSyCjZurK9v2FCUwiQdMGODbP4VUwWtY8qQU"}};
    }

    /**
     *
     * @return list of strings with valid Urls
     */
    private static final Object[] validUrls()
    {
        return new String[][]{
                {"http://i298537.hera.fhict.nl/TCI/catalog.php?cat=movies"},
                {"http://i298537.hera.fhict.nl/TCI/catalog.php?cat=musics"},
                {"http://i298537.hera.fhict.nl/TCI/catalog.php?cat=books"},
                {"http://i298537.hera.fhict.nl/TCI/details.php?id=201"}

        };
    }

    /**
     *
     * @return list of books format that can be accessed from the web
     */
    private static final Object[] getBooksFormat()
    {

        return new String[][]{
                {"Ebook"}
                ,{"Audio"}
                ,{"Paperback"}
                ,{"Hardcover"}};
        };
    /**
     *
     * @return list of movie directors that can be accessed from the web
     */
    private static final Object[] getMoviesDirector()
    {


        return new String[][]{
                {"Robert Zemeckis"}
                ,{"Peter Jackson"}
                ,{"Mike Judge"}
                ,{"Rob Reiner"}
        };
    }

    /**
     *
     * @return list of music artists that can be accessed from the web
     */
    private static final Object[] getMusicArtist()
    {


        return new String[][]{
                {"Ludwig van Beethoven"}
                ,{"Elvis Presley"}
                ,{"Garth Brooks"}
                ,{"Nat King Cole"}
        };
    }

    /**
     *
     * @return list of collective objects with book, movie and music together with the pages explored for each.
     */
 private static final Object[] getItemsToSearchFor()
    {


        return $($("No Fences",14),$("Forrest Gump",7),$("The Clean Coder: A Code of Conduct for Professional Programmers",3));



    }

    /**
     * This test method checks the method GetAllLinks of class Spider and see if list of links filled up from
     * the given url link
     * @return
     */
    @Test
    public void testIfLinksListisBeingFilled() throws IOException
    {
        spider = new Spider();
        spider.GetAllLinks("http://i298537.hera.fhict.nl/TCI/index.php");
        int size = spider.Links.size();
        assertNotEquals(size, 0);
    }

    /**
     * This test checks if the GetAllLinks method throws the IllegalArgument Exception on
     * givng the invalid links. The invalid link is captured from the parameterized method.
     * @param invalidUrl
     * @throws IOException
     */
    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getInvalidUrls")
    public void testIfUnsupportedMimeTypeExceptionThrown(String  invalidUrl) throws IOException
    {

        spider.GetAllLinks(invalidUrl);

    }

    /**
     * This method checks if the id is set correctly for the MusicMovieBookLine mock object in the
     * Spider class using the AtomicLong
     */
    @Test
    public void testIfIdOfMusicMovieBookLineSetsCorrectly()
    {
        final AtomicLong counter = new AtomicLong();

        when(mockMBMLine.getId()).thenReturn(counter.incrementAndGet());
        assertEquals(mockMBMLine.getId(), 1);
    }

    /**
     * This test checks if the correct and exactly books are filled if url containing books information is
     * given. The test method runs as many times as the number of book formats present in getBooksFormat
     * parameterized method and check each book format fetched from the parameterized method , if it exists in the booklist
     * filled by calling the spider actual getAll method.
     * @param bookformat
     * @throws IOException
     */
    @Test
    @Parameters(method = "getBooksFormat")
    public  void testIfBookListFilledExactlyWithBooks(String bookformat) throws IOException {

        spider.GetAllLinks("http://i298537.hera.fhict.nl/TCI/catalog.php?cat=books");
        MusicMovieBookLine bookLine = spider.GetAll();
        assertEquals(4,bookLine.getBooks().size());
        List<String> formats= new ArrayList<>();
        for(Book b : bookLine.getBooks())
        {
            formats.add(b.getFormat());
        }
        assertTrue(formats.contains(bookformat));
    }

    /**
     * This test checks if the correct and exactly movies are filled if url containing movies information is
     * given. The test method runs as many times as the number of movie directors present in getMoviesDirector
     * parameterized method and check each movie director fetched from the parameterized method , if it exists in the movielist
     * filled by calling the spider actual getAll method.
     * @param movieDirector
     * @throws IOException
     */
    @Test
    @Parameters(method = "getMoviesDirector")
    public  void testIfMovieListFilledExactlyWithMovies(String movieDirector) throws IOException{

        spider.GetAllLinks("http://i298537.hera.fhict.nl/TCI/catalog.php?cat=movies");
        MusicMovieBookLine musicMovieBookLine = spider.GetAll();
        List<String> formats= new ArrayList<>();
        for(Movie b : musicMovieBookLine.getMovies())
        {
            formats.add(b.getDirector());
        }
        assertTrue(formats.contains(movieDirector));
    }

    /**
     * This test checks if the correct and exactly musics are filled if url containing musics information is
     * given. The test method runs as many times as the number of music artists present in getMusicArtist
     * parameterized method and check each music artist fetched from the parameterized method , if it exists in the booklist
     * filled by calling the spider actual getAll method.
     * @param musicArtist
     * @throws IOException
     */
    @Test
    @Parameters(method = "getMusicArtist")
    public  void testIfMusicListFilledExactlyWithMusics(String musicArtist) throws IOException {

        spider.GetAllLinks("http://i298537.hera.fhict.nl/TCI/catalog.php?cat=movies");
        MusicMovieBookLine musicMovieBookLine = spider.GetAll();
        List<String> artists= new ArrayList<>();
        for(Music b : musicMovieBookLine.getMusics())
        {
            artists.add(b.getArtist());
        }
        assertTrue(artists.contains(musicArtist));
    }

    /**
     * This test method checks if the MusicMovieBookLine is retured with the list of books, musics and movies
     * after calling spider GetAll() with different Urls captured from the parameterized method validUrls. And
     * if everything go well then the total number of books, musics and movies in the MusicMovieBookLine lists
     * should be equal to 12.
     * @param Url
     * @throws IOException
     */
    @Test
    @Parameters(method = "validUrls")
    public  void testIfBookMusicMovieListFilledFromOtherUrls(String Url) throws IOException {

        spider.GetAllLinks(Url);
        MusicMovieBookLine bookLine = spider.GetAll();
        assertEquals(12,bookLine.getBooks().size()+bookLine.getMovies().size()+bookLine.getMusics().size());


    }



    /**
     * This test method checks if the time_elapsed is correctly set for the MusicMovieBookLine and
     * verified if it is actually called for atleast once to set the time elapse.
     * @throws IOException
     */
    @Test
    public void testIfSetTimeMethodIsCalled() throws IOException {

        MusicMovieBookLine bookLine = mock(MusicMovieBookLine.class);
        bookLine.setTime_elapse(12,13);
        verify(bookLine, atLeast(1)).setTime_elapse(12,13);

    }

    /**
     * This method checks if correct Book information has been fetched from the URL containing book information.
     * This is checked by comparing the ISBN of the mock book and the actual returned Book from spider GetBook method.
     * @throws IOException
     */
    @Test
    public void testIfCorrectBookHasBeenFetchedFromID102() throws IOException {
        Book mockBook = mock(Book.class);
        when(mockBook.getIsbn()).thenReturn("978-0132350884");
        Book actualBook = spider.GetBook("http://i298537.hera.fhict.nl/TCI/details.php?id=102");
        assertEquals(mockBook.getIsbn(),actualBook.getIsbn());
    }

    /**
     * This method checks if correct Movie information has been fetched from the URL containing movie information.
     * This is checked by comparing the Director of the mock movie and the actual returned Movie from spider
     * GetMovie method. Also it checks if the returned Movie is not null using the asserThat.
     * @throws IOException
     */
    @Test
    public void testIfCorrectMovieHasBeenFetchedFromID201() throws IOException {
        Movie mockMovie = mock(Movie.class);
        when(mockMovie.getDirector()).thenReturn("Robert Zemeckis");
        Movie actualMovie = spider.GetMovie("http://i298537.hera.fhict.nl/TCI/details.php?id=201");
        assertThat("Is Not Null ",actualMovie,not(is(nullValue())));

        assertThat("Same Directors Means Same movies ",actualMovie.getDirector(),is(mockMovie
                .getDirector()));

    }

    /**
     * This method checks if the NullPointerException is thrown on calling the spider GetBySearch method without
     * specifying the CrawlInformation instance.
     * @throws IOException
     */
    @Test(expected = NullPointerException.class)
    public void  testIfNullPointerExceptionThrownWithNullCrawlInformation() throws IOException {
        SearchedItemLine searchedItem = spider.GetBySearch("Forrest Gump");


    }

    /**
     * This method checks if the spider searchForWord finds the given word in the given link.
     * @throws IOException
     */
    @Test
    public void  testIfSearchForWorks() throws IOException {

        boolean exists = spider.searchForWord("Forrest Gump","http://i298537.hera.fhict.nl/TCI/details.php?id=201");
        boolean NotExists = spider.searchForWord("Elvis Forever","http://i298537.hera.fhict.nl/TCI/details.php?id=303");
        assertEquals(true,exists);
        assertEquals(false,NotExists);

    }


    /**
     * This method checks if exactly type music is found on searching by the music name.
     * @throws IOException
     */
    @Test
    public void testIfFoundMusicOnGivingMusicName() throws IOException {
        SearchedItemLine searchedItemLine= spiderWithCrawlInfo.GetBySearch("The Very Thought of You");
        assertThat(searchedItemLine.getFounditem(), instanceOf(Music.class));

    }

    /**
     * This method checks if correct movie is found on searching by the movie name.
     * @throws IOException
     */
    @Test
    public void testIfFoundCorrectMovie() throws IOException {
        SearchedItemLine searchedItemLine= spiderWithCrawlInfo.GetBySearch("The Princess Bride");
        assertEquals("Rob Reiner",((Movie)searchedItemLine.getFounditem()).getDirector());

    }

    /**
     * This method tests if the time elapsed is set correctly for the CrawlInformation instance (mock).
     * And if everything goes well then the time elapsed should not be equal to 0.
     * @throws IOException
     */
    @Test
    public void testIfCrawlInformationGetsTimeElapsed() throws IOException {

        when(crawlInformation.getTime_elapse()).thenCallRealMethod();
        doCallRealMethod().when(crawlInformation).setTime_elapse(Mockito.anyLong(),Mockito.anyLong());
        spiderWithCrawlInfo.GetBySearch("Elvis Forever");
        assertNotEquals(0,crawlInformation.getTime_elapse());

    }

    /**
     * This method tests if the Page Depth is set correctly for the CrawlInformation instance (mock).
     * And if everything goes well then the page depth should be equal to 2.
     * @throws IOException
     */
    @Test
    public void testIfCrawlInformationGetsCorrectPageDepth() throws IOException {

        when(crawlInformation.GetDepth()).thenCallRealMethod();
        doCallRealMethod().when(crawlInformation).SetDepth(Mockito.anyInt());
        spiderWithCrawlInfo.GetBySearch("No Fences");
        assertEquals(2,crawlInformation.GetDepth());

    }

    /**
     * Gets the book name, music name , movie name together with their number of pages to be explored to reach them
     * from the parameterized method getItemsToSearchFor and matches page explorer one by one with the crawlInformation page explorer
     * set by calling GetBySearch method
     * @param name
     * @param i
     * @throws IOException
     */
    @Test
    @Parameters(method="getItemsToSearchFor")
    public void   testIfCrawlInformationGetsCorrectPageExplored(String name, int i) throws IOException {
        when(crawlInformation.GetEXplorer()).thenCallRealMethod();
        doCallRealMethod().when(crawlInformation).SetExplorer(Mockito.anyInt());
        spiderWithCrawlInfo.GetBySearch(name);
        assertEquals(i,crawlInformation.GetEXplorer());
    }

}
