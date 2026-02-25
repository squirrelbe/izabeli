package org.example.testng;

import org.example.pom.FormPom;
import org.example.utils.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class FormTest {

    static public WebDriver driver;
    static public String URL = "https://demoqa.com/";
    static public String FIRST_NAME = "Izabeli";
    static public String LAST_NAME = "Boroznet";
    static public String EMAIL = "iboroznets@gmail.com";
    static public String GENDER = "Female";
    static public String NUMBER = "0676126054";
    static public String DATE = "4 Sep 2006";
    static public String SUBJECT = "English";
    static public String HOBBY = "Music";
    static public String STATE = "NCR";
    static public String CITY = "Delhi";

    @BeforeMethod
    public void beforeMethod() throws MalformedURLException {
        //driver = Driver.getAutoLocalDriver();
        driver = Driver.getRemoteDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void formTest() {
        System.out.println("Start test");
        driver.get(URL);
        FormPom formPom = new FormPom(driver);
        formPom.clickForms();
        formPom.clickPracticeForm();
        formPom.closeAdvert();
        formPom.setFirstName(FIRST_NAME);
        formPom.setLastName(LAST_NAME);
        formPom.setEmail(EMAIL);
        formPom.setGender(GENDER);
        formPom.setNumber(NUMBER);
        formPom.setDate(DATE);
        formPom.setSubject(SUBJECT);
        formPom.setHobby(HOBBY);
        formPom.setState(STATE);
        formPom.setCity(CITY);
        formPom.clickSubmit();
        formPom.pause(5000);

        String actualName = formPom.getTableDataByLabel("Student Name");
        Assert.assertEquals(actualName, FIRST_NAME + " "+ LAST_NAME);
        System.out.println("Finish test");

    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
