import TCI.CrawlInformation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CrawlInformationTest {

    private CrawlInformation  crawlInformation;

    @Before
    public  void SetUp()
    {
        crawlInformation = new CrawlInformation();
    }

    @Test(expected = IllegalArgumentException.class)
    public void IfThrowsExceptionOnNegativeDepth()
    {
        crawlInformation.SetDepth(-2);

    }

    @Test
    public void IfSetsDepthOnPositiveDepth()
    {
        crawlInformation.SetDepth(2);
        Assert.assertEquals(2,crawlInformation.GetDepth());

    }

    @Test(expected = IllegalArgumentException.class)
    public void IfThrowsExceptionOnNegativeExplorer()
    {
        crawlInformation.SetExplorer(-2);

    }

    @Test
    public void IfSetsExplorerOnPositiveExplorer()
    {
        crawlInformation.SetExplorer(2);
        Assert.assertEquals(2,crawlInformation.GetEXplorer());

    }
}
