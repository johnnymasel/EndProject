package selenium.automated.tests;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class DevToTests {

    WebDriver driver; //inicjalizacja drivera - pustej przegladarki
    String url = "https://dev.to/"; //w zmiennej url zapisujemy linku strony

    public void HighlightElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", element);

    }


    @Before //wykona sie przed kazdym @Testem
    public void SetUp(){ // pre-requirements - warunki poczatkowe
        System.setProperty("webdriver.chrome.driver", "c://SeleniumDrivers/chromedriver.exe"); //sciezka do chromedriver
        driver = new ChromeDriver();
        driver.get(url); //otworzenie linku w przeglądarce
        driver.manage().window().maximize();

        //fajny trick na czekanie
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //czekaj az element sie pojawi przez 10 sec, sprawdzaj co 0,5sec

    }

    @Test
    public void OpenDevTo(){

        //teraz mamy otworzona strone dev.to w przegladarce
        //zeby to sprawdzic chcemy porównac url ze zmiennej do obecnego url

        //assercja - sprawdzenie czy strona sie zgadza. w google wpisac junit assert
        String currentURL = driver.getCurrentUrl(); //wyciagamy obecny url z przegladarki i przypisujemy do zmiennej currentURl

        // assertTrue(tekst jeżeli niespełniony; condition) - sprawdza poprawnosc warunku
        assertTrue("The current URL isn't dev.to", url.equals(currentURL));
    }


    @Test
    public void GoToWeekAndOpenFirstPost() {
        WebElement week = driver.findElement(By.cssSelector("#on-page-nav-controls > div > nav > a:nth-child(2)")); //znalezienie elementu week na stronie
        HighlightElement(week); // podswietlenie elementu week
        week.click(); // klikniecie elementu week

        WebDriverWait wait = new WebDriverWait(driver, 5); //zainicjalizowanie Explicit Wait
        wait.until(ExpectedConditions.urlToBe("https://dev.to/top/week")); //poczekaj aż url będzie : https://dev.to/top/week
        //wait.until(ExpectedConditions.attributeContains(week,"class","item--current"));

        WebElement firstPostOnWeek = driver.findElement(By.className("crayons-story__body")); //odnalezienie pierwszego posta
        HighlightElement(firstPostOnWeek); //podswietlenie 1 postu
        WebElement firstPostTitle = driver.findElement(By.cssSelector(".crayons-story__title > a")); //znajdź element za pomocą cssSelector -  będzie to nazwa 1 posta
        HighlightElement(firstPostTitle);
        String linkToFirstPost = firstPostTitle.getAttribute("href"); //wyciagnij z nazwy pierwszego posta link do strony

        firstPostOnWeek.click(); //klikniecie 1 postu
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("crayons-article__header__meta"))); //poczekaj az element psotu będzie widoczny
        String currentUrl = driver.getCurrentUrl(); //wyciagnij obecny link

        //sprawdz czy link do postu jest taki sam jak obecny url
        assertEquals("url isn't the same as link(href) value", linkToFirstPost, currentUrl);
    }


}
