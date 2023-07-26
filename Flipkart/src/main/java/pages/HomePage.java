package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import base.BasePage;

public class HomePage extends BasePage
{

	By closebtn=By.cssSelector("._2doB4z");
	By searchinput=By.name("q");
	
	public void closeLoginPopup()
	{
	   	try{driver.findElement(closebtn).click();}catch(Exception e) {}
	}
	public void search(String value)
	{
		driver.findElement(searchinput).sendKeys(value);
		driver.findElement(searchinput).sendKeys(Keys.ENTER);
	}
}
