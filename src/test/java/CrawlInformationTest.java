import TCI.CrawlInformation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CrawlInformationTest {

    private CrawlInformation crawlInformation;

    @Before
    public  void SetUp()
    {
        crawlInformation = new CrawlInformation();
    }


    /**
     * Checks If the exception IllegalArgument is thrown on entering the negative depth
     */
    @Test(expected = IllegalArgumentException.class)
    public void IfThrowsExceptionOnNegativeDepth()
    {
        crawlInformation.SetDepth(-2);

    }
    /**
     * Checks If the Depth set on entering the positive depth
     */
    @Test
    public void IfSetsDepthOnPositiveDepth()
    {
        crawlInformation.SetDepth(2);
        Assert.assertEquals(2,crawlInformation.GetDepth());

    }
    /**
     * Checks If the exception IllegalArgument is thrown on entering the negative Explorer
     */
    @Test(expected = IllegalArgumentException.class)
    public void IfThrowsExceptionOnNegativeExplorer()
    {
        crawlInformation.SetExplorer(-2);

    }

    /**
     *Checks If the Explorer set on entering the positive page explorer
     */
    @Test
    public void IfSetsExplorerOnPositiveExplorer()
    {
        crawlInformation.SetExplorer(2);
        Assert.assertEquals(2,crawlInformation.GetEXplorer());

    }
}
