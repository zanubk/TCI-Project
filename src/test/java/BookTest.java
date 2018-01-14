import TCI.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BookTest {

    private Book book;

    @Before
    public void Setup()
    {book = new Book();}

    @Test
    public void IfBookNameSetAndGet()
    {

    }

    @Test
   public void IfBookGenreSetAndGet()
    {
        book.setGenre("Mock Genre");
        Assert.assertEquals("Mock Genre", book.getGenre());
    }

    @Test
    public void IfBookFormatSetAndGet()
    {
        book.setFormat("Hardcover");
        Assert.assertEquals("Hardcover", book.getFormat());
    }
    @Test
    public void IfBookYearSetAndGet()
    {
        book.setYear("1999");
        Assert.assertEquals("1999", book.getYear());
    }

    @Test
    public void IfBookAuthorSetAndGet()
    {
          }
    @Test
    public void IfBookPublisherSetAndGet()
    {
        book.setPublisher("Addison-Wesley Professional");
        Assert.assertEquals("Addison-Wesley Professional", book.getPublisher());
    }

    @Test
    public void IfBookIsbnSetAndGet()
    {
        book.setIsbn("978-0201485677");
        Assert.assertEquals("978-0201485677", book.getIsbn());
    }

}
