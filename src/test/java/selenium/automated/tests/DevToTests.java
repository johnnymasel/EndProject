package selenium.automated.tests;

import net.bytebuddy.asm.Advice;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class DevToTests {
    WebDriver driver; //inicjalizacja drivera - pustej przegladarki

    @Before //wykona sie przed kazdym @Testem
    public void SetUp(){ // pre-requirements - warunki poczatkowe
        System.setProperty("webdriver.chrome.driver", "c://SeleniumDrivers/chromedriver.exe"); //sciezka do chromedriver
        driver = new ChromeDriver(); //nadpisanie drivera jako przgladarki chrome
    }

    @Test
    public void OpenDevTo(){
        String url = "https://dev.to/"; //w zmiennej url zapisujemy linku strony
        driver.get(url); //otworzenie linku w przegladarce

        //teraz mamy otworzona strone dev.to w przegladarce
        //zeby to sprawdzic chcemy porównac url ze zmiennej do obecnego url

        //assercja - sprawdzenie czy strona sie zgadza. w google wpisac junit assert
        String currentURL = driver.getCurrentUrl(); //wyciagamy obecny url z przegladarki i przypisujemy do zmiennej currentURl

        // assertTrue(tekst jeżeli niespełniony; condition) - sprawdza poprawnosc warunku
        assertTrue("The current URL isn't dev.to", url.equals(currentURL));
    }

    @Test //praca domowa
    @Ignore
    public void searchTest(){

        driver.get("https://dev.to/");

        String phrase = "101 coding problems";
        WebElement searchField = driver.findElement(By.id("nav-search"));
        searchField.click();
        searchField.sendKeys(phrase);
        searchField.sendKeys(Keys.ENTER);


    }

}
