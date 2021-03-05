package ru.netology.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppOrderTest {

    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "F:\\QA learning\\Automatization\\1.3\\driver\\win\\chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestWebFormValidData() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Егоров Владимир");
        driver.findElement(By.cssSelector("input[type='tel']")).sendKeys("+79219212121");
        driver.findElement(By.cssSelector("[class='checkbox__box']")).click();
        driver.findElement(By.cssSelector("[role='button']")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    void shouldTestWebFormNoSymbolsInName() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[role='button']")).click();
        String text = driver.findElement(By.cssSelector("span[data-test-id = \"name\"] span + span +span")).getText();
        assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    void shouldTestWebFormInvalidSymbolsInName() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("1234567");
        driver.findElement(By.cssSelector("[role='button']")).click();
        String text = driver.findElement(By.cssSelector("span[data-test-id = \"name\"] span + span +span")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }

    @Test
    void shouldTestWebFormNoSymbolsInPhone() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Егоров Владимир");
        driver.findElement(By.cssSelector("[role='button']")).click();
        String text = driver.findElement(By.cssSelector("span[data-test-id = \"phone\"] span + span +span")).getText();
        assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    void shouldTestWebFormInvalidSymbolsInPhone() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Егоров Владимир");
        driver.findElement(By.cssSelector("input[type='tel']")).sendKeys("1234567");
        driver.findElement(By.cssSelector("[role='button']")).click();
        String text = driver.findElement(By.cssSelector("span[data-test-id = \"phone\"] span + span +span")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

//    @Test
//    void shouldTestWebFormBoxUnchecked() {
//        driver.get("http://localhost:9999");
//        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Егоров Владимир");
//        driver.findElement(By.cssSelector("input[type='tel']")).sendKeys("+79219212121");
//        driver.findElement(By.cssSelector("[role='button']")).click();
//        String text = driver.findElement(By.cssSelector("")).getText();
//        assertEquals("", text.trim());
//    }

}
