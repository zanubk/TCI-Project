import TCI.Book;
import TCI.SearchedItemLine;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchedItemLineTest {

    private SearchedItemLine searchedItemLine ;

    /**
     * it tests if the constructor sets correctly by passing the correct type of parameter.
     */
    @Test
    public  void IfConstructorMadeCorrectly()
    {
        Book bk = mock(Book.class);
        searchedItemLine = new SearchedItemLine(bk);
        assertThat("Is Instance Of Book",searchedItemLine.getFounditem(),instanceOf(Book.class));

    }

    /**
     * This method checks if the FoundItem is correctly get and set by checking the ISBN of the Book entered in
     * the parameter and comparing it with the mock Book ISBN.
     */
    @Test
    public  void IfConstructorSetsCorrectFoundItem()
    {
        Book mockbk = mock(Book.class);
        when(mockbk.getIsbn()).thenReturn("123456");
        searchedItemLine = new SearchedItemLine(mockbk);
        Book book = (Book) searchedItemLine.getFounditem();
        assertThat("Have same ISBN",book.getIsbn(),is("123456"));

    }

    /**
     * This method checks if ID sets correctly  for the searchedItemLine object.
     */
    @Test
    public void  IfIdSetsCorrectly()
    {
        Book mockbk = mock(Book.class);
        searchedItemLine = new SearchedItemLine(mockbk);
        searchedItemLine.setId(1);
        assertEquals(1,searchedItemLine.getId());
    }
}


