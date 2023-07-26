package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class ProductsPage extends BasePage
{
   By lowtohigh=By.xpath("//div[text()='Price -- Low to High']");
   By prices=By.cssSelector("._30jeq3");
   By oneproduct=By.cssSelector("._4rR01T");
   
   
   public String getTitle()
   {
	  String title=driver.getTitle();
	  return title;
   }
   public void clickLowToHigh()
   {
	   driver.findElement(lowtohigh).click();
	   try {Thread.sleep(3000);}catch(Exception e) {}
   }
   public List<Integer> getPrices()
   {
	   List<WebElement> allprices=driver.findElements(prices);
	   List<Integer> arr=new ArrayList<Integer>();
	   
	   for(int i=0;i<allprices.size();i++)
	   {
		   arr.add(Integer.parseInt(allprices.get(i).getText().substring(1).replace(",","")));
	   }
	   return arr;
   }
   public String[] clickOneProduct(int index)
   {
	   String first[]=new String[2];
	   first[0]=driver.findElements(oneproduct).get(index).getText();
	   first[1]=driver.findElements(prices).get(index).getText();
	   driver.findElements(oneproduct).get(index).click();
	   try {Thread.sleep(3000);}catch(Exception e) {}
	   return first;
   }
}
