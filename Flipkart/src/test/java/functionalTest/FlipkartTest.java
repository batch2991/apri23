package functionalTest;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import base.BasePage;
import pages.HomePage;
import pages.ProductsPage;
import pages.SingleProductPage;

public class FlipkartTest extends BasePage
{

	HomePage homepage=new HomePage();
	ProductsPage productspage=new ProductsPage();
	SingleProductPage singleproductpage=new SingleProductPage();
	
	String searchvalue="mobiles";
	
	@Test
	public void validateTitle()
	{	
		homepage.setUp();
		homepage.openUrl();
		homepage.closeLoginPopup();
		homepage.search(searchvalue);
		String title=productspage.getTitle().toLowerCase();
		test=report.createTest("ValidateTitle");
		if(title.contains(searchvalue))
		{			
			test.log(Status.PASS, "Title is as expected and has the value : "+searchvalue);			
		}
		else
		{			
			test.log(Status.FAIL, "Title NOT as expected and no value  : "+searchvalue);			
		}
		takescreenshot("title.png");
			
		
		homepage.tearDown();
	}
	@Test
	public void validateThePricesLowToHigh()
	{
		homepage.setUp();
		homepage.openUrl();
		homepage.closeLoginPopup();
		homepage.search(searchvalue);
		productspage.clickLowToHigh();		
		List<Integer> actprices=productspage.getPrices();
		List<Integer> exprices=actprices.stream().sorted().toList();
		test=report.createTest("Validate prices in sorting order");
		test.log(Status.INFO,actprices.toString());
		if(actprices.equals(exprices))
		{
			test.log(Status.PASS,"All the prices are in ascending order");			
		}			
		else
		{
			test.log(Status.FAIL,"All the prices are not in Sorting order");		
		}	
		takescreenshot("products.png");
		homepage.tearDown();		
	}
	@Test
	public void validateProductNameandPrice()
	{
		homepage.setUp();
		homepage.openUrl();
		homepage.closeLoginPopup();
		homepage.search(searchvalue);
		String firstproductnameprice[]=productspage.clickOneProduct(3);
		String secondproductnameprice[]=singleproductpage.getProductNameandPrice();
		System.out.println(Arrays.toString(firstproductnameprice));
		System.out.println(Arrays.toString(secondproductnameprice));
		test=report.createTest("Validate ProductName and Price");
		if(firstproductnameprice[0].matches(secondproductnameprice[0]) && firstproductnameprice[1].matches(secondproductnameprice[1]))
		{
			test.log(Status.PASS, "ProductName and price are same in both pages");			
		}			
		else
		{
		    test.log(Status.FAIL, "ProductName and price are not same in both pages");	
		}		
		takescreenshot("singleproduct.png");
		
		homepage.tearDown();
		
	}
}
