package caseStudy.stepdefinitions;

import caseStudy.pages.UIPage;
import caseStudy.utilities.ConfigReader;
import caseStudy.utilities.Driver;
import caseStudy.utilities.ReusableMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javassist.compiler.ast.Keyword;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.io.*;
import java.util.List;
import java.util.Properties;

public class UIStepDefs {

    UIPage uiPage = new UIPage();
    Actions action = new Actions(Driver.getDriver());

    @Given("Kullanici {string} sitesine gider")
    public void kullanici_sitesine_gider(String url) throws InterruptedException {
        Driver.getDriver().get(ConfigReader.getProperty(url));
        Thread.sleep(2000);
    }

    @Given("Kullanici Giris Yap sekmesine tiklar")
    public void kullanici_giris_yap_sekmesine_tiklar() throws InterruptedException {
        uiPage.girisYapSekme.click();
        Thread.sleep(2000);
    }

    @Given("Kullanici {string} ve {string} bilgilerini girer")
    public void kullanici_ve_bilgilerini_girer(String eposta, String sifre) throws InterruptedException {
        uiPage.emailInput.sendKeys(ConfigReader.getProperty(eposta));
        Thread.sleep(1000);
        uiPage.sifreInput.sendKeys(ConfigReader.getProperty(sifre));
        Thread.sleep(2000);
    }

    @Then("Kullanici Giris Yap butonuna tiklar")
    public void kullanici_giris_yap_butonuna_tiklar() throws InterruptedException {
        uiPage.girisYapButton.click();
        Thread.sleep(2000);
    }

    @Then("Login olup {string} geldigini dogrular")
    public void login_olup_geldigini_dogrular(String username) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("hesabım name: " + uiPage.username.getText());
        System.out.println(uiPage.username.getText().equals(username));
        Assert.assertTrue(uiPage.username.getText().equals(username));
    }

    @When("Hesabim menusunun uzerine gelir")
    public void hesabimMenusununUzerineGelir() throws InterruptedException {
        action.moveToElement(uiPage.username).perform();
        Thread.sleep(2000);
    }

    @When("{string} dropdown secer")
    public void dropdown_secer(String dropdown) throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> dropdownHesabim = uiPage.dropdownHesabim;
        for (int i = 0; i < dropdownHesabim.size(); i++) {
            if(dropdownHesabim.get(i).getText().equals(dropdown)){
                dropdownHesabim.get(i).click();
                break;
            }
        }
    }

    @Then("{string} sekmesinin geldigini dogrular")
    public void sekmesininGeldiginiDogrular(String sekme) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("sekme: " + uiPage.girisYapSekme.getText());
        System.out.println(uiPage.girisYapSekme.getText().equals(sekme));
        Assert.assertTrue(uiPage.girisYapSekme.getText().equals(sekme));
    }

    @Then("Kullanici sayfayi kapatir")
    public void kullanici_sayfayi_kapatir() {
        Driver.getDriver().quit();
    }

    @Then("{string} mesaji gorur")
    public void mesaji_gorur(String message) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("message: " + uiPage.hataMesaji.getText());
        System.out.println(uiPage.hataMesaji.getText().equals(message));
        Assert.assertTrue(uiPage.hataMesaji.getText().equals(message));
    }

    @Then("Hata mesajini {string} dosyasina yazdirir")
    public void hata_mesajini_dosyasina_yazdirir(String fileName) {
        File file = new File(System.getProperty("user.dir") + "/src/test/resources/documents/" + fileName);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(uiPage.hataMesaji.getText());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String search="";
    @Then("Kullanici {string} icin arama islemi yapar")
    public void kullanici_icin_arama_islemi_yapar(String searchKeyword) throws InterruptedException {
        search = searchKeyword;
        uiPage.searchBox.sendKeys(searchKeyword);
        Thread.sleep(1000);
        uiPage.searchBox.sendKeys(Keys.ENTER);
    }

    String aramaSonucu="";
    @Then("Arama sonuclarini goruntuler")
    public void arama_sonuclarini_goruntuler() throws InterruptedException {
        aramaSonucu=uiPage.aramaSonucu.getText();
        System.out.println(aramaSonucu);
        System.out.println(uiPage.aramaSonucu.isDisplayed());
        Assert.assertTrue(uiPage.aramaSonucu.isDisplayed());
        Thread.sleep(1000);
    }

    @Then("Arama sonuclarini {string} dosyasina kaydeder")
    public void arama_sonuclarini_dosyasina_kaydeder(String fileName) {
        try {
            File file = new File(System.getProperty("user.dir") + "/src/test/resources/documents/" + fileName);
            FileWriter writer = new FileWriter(file, true);
            writer.write(search + " aramasi icin " + uiPage.aramaSonucu.getText() + " sonuc bulundu" + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Then("Arama sonuclarinin ekran goruntusunu alir")
    public void arama_sonuclarinin_ekran_goruntusunu_alir() throws IOException {
        ReusableMethods.getScreenshot("ss");
    }

    String aramaSonucuYok="";
    @Then("Arama sonuclarinin olmadigini goruntuler")
    public void arama_sonuclarinin_olmadigini_goruntuler() throws InterruptedException {
        aramaSonucuYok=uiPage.aramaSonucuYok.getText();
        System.out.println(aramaSonucuYok);
        System.out.println(uiPage.aramaSonucuYok.isDisplayed());
        Assert.assertTrue(uiPage.aramaSonucuYok.isDisplayed());
        Thread.sleep(1000);
    }

    @And("Arama sonuclarinin olmadigi {string} dosyasina kaydeder")
    public void aramaSonuclarininOlmadigiDosyasinaKaydeder(String fileName) {
        try {
            File file = new File(System.getProperty("user.dir") + "/src/test/resources/documents/" + fileName);
            FileWriter writer = new FileWriter(file, true);
            writer.write(search + " aramasi icin " + uiPage.aramaSonucuYok.getText() + " mesaji alarak 0 sonuc bulundu" + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @And("Kullanici {string} ve {string} bilgilerini {string} dosyasindan okur")
    public void kullaniciVeBilgileriniDosyasindanOkur(String eposta, String sifre, String fileName) throws IOException, InterruptedException {
        Properties properties = new Properties();
        FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/documents/" + fileName);
        properties.load(inputStream);
        System.out.println(properties.getProperty(eposta));
        uiPage.emailInput.sendKeys(properties.getProperty(eposta));
        Thread.sleep(1000);
        System.out.println(properties.getProperty(sifre));
        uiPage.sifreInput.sendKeys(properties.getProperty(sifre));
        Thread.sleep(2000);
    }

    @Then("Kullanici {string} icin parametreleri {string} dosyasindan okur")
    public void kullaniciIcinParametreleriDosyasindanOkur(String search, String fileName) throws InterruptedException, IOException {
        Properties properties = new Properties();
        FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/documents/" + fileName);
        properties.load(inputStream);
        System.out.println(properties.getProperty(search));
        uiPage.searchBox.sendKeys(properties.getProperty(search));
        Thread.sleep(1000);
        uiPage.searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
    }
}
