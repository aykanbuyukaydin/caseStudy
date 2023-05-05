package caseStudy.pages;

import caseStudy.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UIPage {
    public UIPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//a[@class='btnSignIn']")
    public WebElement girisYapSekme;

    @FindBy(xpath = "//input[@id='email']")
    public WebElement emailInput;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement sifreInput;

    @FindBy(xpath = "//div[@id='loginButton']")
    public WebElement girisYapButton;

    @FindBy(xpath = "//a[@class='user']")
    public WebElement username;

    @FindBy(xpath = "//*[@id='header']/div/div/div/div[2]/div[5]/div[2]/div/a")
    public List<WebElement> dropdownHesabim;

    @FindBy(xpath = "(//div[@class='errorText'])[4]")
    public WebElement hataMesaji;

    @FindBy(xpath = "//input[@id='searchData']")
    public WebElement searchBox;

    @FindBy(xpath = "//*[@id='contentListing']/div/div[2]/div[2]/section/div[1]/div[1]/div[1]/div/strong")
    public WebElement aramaSonucu;

    @FindBy(xpath = "//*[@id='searchResultNotFound']/div/div[1]/h1")
    public WebElement aramaSonucuYok;



}
