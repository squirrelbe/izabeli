package org.example.testng;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pom.FormPom;
import org.example.utils.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.Scanner;

public class FormTest {

    private static final Logger logger = LogManager.getLogger(FormTest.class);
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
        logger.info("Start before method");
        //driver = Driver.getAutoLocalDriver();
        driver = Driver.getRemoteDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void formTest() {
        logger.info("Start test");
        driver.get(URL);
        FormPom formPom = new FormPom(driver);
        formPom.clickForms();
        formPom.clickPracticeForm();
        formPom.closeAdvert();
        formPom.setFirstName(FIRST_NAME);
        formPom.setLastName(LAST_NAME);
        logger.info("Set email");
        formPom.setEmail(EMAIL);
        formPom.setGender(GENDER);
        formPom.setNumber(NUMBER);
        formPom.setDate(DATE);
        formPom.setSubject(SUBJECT);
        formPom.setHobby(HOBBY);
        logger.info("Set state");
        formPom.setState(STATE);
        formPom.setCity(CITY);
        formPom.clickSubmit();
        formPom.pause(5000);

        String actualName = formPom.getTableDataByLabel("Student Name");
        Assert.assertEquals(actualName, FIRST_NAME + " "+ LAST_NAME);

        String actualEmail = formPom.getTableDataByLabel("Student Email");
        Assert.assertEquals(actualEmail, EMAIL);

        String actualGender = formPom.getTableDataByLabel("Gender");
        Assert.assertEquals(actualGender, GENDER);

        String actualMobile = formPom.getTableDataByLabel("Mobile");
        Assert.assertEquals(actualMobile, NUMBER);

        String actualSubjects = formPom.getTableDataByLabel("Subjects");
        Assert.assertEquals(actualSubjects, SUBJECT);

        String actualHobbies = formPom.getTableDataByLabel("Hobbies");
        Assert.assertEquals(actualHobbies, HOBBY);

        String actualState = formPom.getTableDataByLabel("State and City");
        Assert.assertEquals(actualState, STATE + " " + CITY);

        logger.info("Finish test");

    }

    @AfterMethod
    public void afterMethod() {
        logger.info("Start after method");
        driver.quit();
    }
}
