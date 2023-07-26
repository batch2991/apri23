package pages;

import java.util.ArrayList;

import org.openqa.selenium.By;

import base.BasePage;

public class SingleProductPage extends BasePage
{
  By prodname=By.cssSelector(".B_NuCI");
  By price=By.cssSelector("._30jeq3");
  
  public String[] getProductNameandPrice()
  {
	  ArrayList<String> tabs=new ArrayList<String>(driver.getWindowHandles());
	  driver.switchTo().window(tabs.get(1));
	  String second[]=new String[2];
	  second[0]=driver.findElement(prodname).getText();
	  second[1]=driver.findElement(price).getText();
	  driver.close();
	  driver.switchTo().window(tabs.get(0));
	  return second;
	  
  }
}
