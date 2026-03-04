package org.example.pom;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.example.utils.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.ByteArrayInputStream;

public class FormPom {

    static public WebDriver driver;
    static public JavascriptExecutor js;

    @FindBy(xpath = "//*[text()='Forms']")
    WebElement forms;

    @FindBy(xpath = "//*[text()='Practice Form']")
    WebElement practiceForm;

    @FindBy(xpath = "//*[@id='firstName']")
    WebElement firstName;

    @FindBy(xpath = "//*[@id='lastName']")
    WebElement lastName;

    @FindBy(xpath = "//*[@id='userEmail']")
    WebElement userEmail;

    @FindBy(xpath = "//*[@id='userNumber']")
    WebElement userNumber;

    @FindBy(xpath = "//*[@id='dateOfBirthInput']")
    WebElement dateOfBirthInput;

    @FindBy(xpath = "//*[@id='subjectsInput']")
    WebElement subjectsInput;

    @FindBy(xpath = "//*[@id='state']")
    WebElement state;

    @FindBy(xpath = "//*[@id='city']")
    WebElement city;

    @FindBy(xpath = "//*[@id='submit']")
    WebElement submit;

    public FormPom(WebDriver driverParam) {
        driver = driverParam;
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public String getTableDataByLabel(String labelParam){
        WebElement data = driver.findElement(By.xpath("//table//*[text()='" + labelParam +"']/../*[2]"));
        return data.getText();
    }



    public void clickSubmit(){
        submit.click();
    }
    @Step("Set state")
    public void setState(String stateParam){
        takeScreenshot("before state");
        state.click();
        WebElement dbState =  state.findElement(By.xpath("//*[text()='" + stateParam + "']"));
        //Utils.explicitWait(driver, ExpectedConditions.elementToBeClickable(dbState), 10);
        pause(1000);
        dbState.click();
        takeScreenshot("after state");
    }
    @Step("Set city")
    public void setCity(String cityParam){
        takeScreenshot("before city");
        city.click();
        WebElement dbCity =  state.findElement(By.xpath("//*[text()='" + cityParam + "']"));
        dbCity.click();
        takeScreenshot("after city");
    }
    @Step("Set hobby")
    public void setHobby(String hobbyParam){
        takeScreenshot("before hobby");
        WebElement hobby = driver.findElement(By.xpath("//*[@id= 'hobbiesWrapper']//label[text() ='" +hobbyParam + "']/../input"));
        hobby.sendKeys(" ");
        takeScreenshot("after hobby");
    }
    @Step("Set number")
    public void setNumber(String numberParam){
        takeScreenshot("before number");
        userNumber.clear();
        userNumber.sendKeys(numberParam);
        takeScreenshot("after number");
    }
    @Step("Set date")
    public void setDate(String dateParam){
        takeScreenshot("before date");
        dateOfBirthInput.sendKeys(Keys.CONTROL,"a");
        dateOfBirthInput.sendKeys(dateParam);
        dateOfBirthInput.sendKeys(Keys.ENTER);
        takeScreenshot("after date");
    }
    @Step("Set subject")
    public void setSubject(String subjectParam){
        takeScreenshot("before subject");
        subjectsInput.sendKeys(subjectParam);
        subjectsInput.sendKeys(Keys.ENTER);
        takeScreenshot("after subject");
    }
    @Step("Set gender")
    public void setGender(String genderParam) {
        takeScreenshot("before gender");
        WebElement gender = driver.findElement(By.xpath("//*[@id='genterWrapper']//label[text()='" + genderParam + "']"));
        gender.click();
        takeScreenshot("after gender");
    }
    @Step("Set email")
    public void setEmail(String emailParam) {
        takeScreenshot("before email");
        userEmail.clear();
        userEmail.sendKeys(emailParam);
        takeScreenshot("after email");
    }
    @Step("Set last name")
    public void setLastName(String lastNameParam) {
        takeScreenshot("before last name");
        lastName.clear();
        lastName.sendKeys(lastNameParam);
        takeScreenshot("after last name");
    }
    @Step("Set first name")
    public void setFirstName(String firstNameParam) {
        takeScreenshot("before first name");
        firstName.clear();
        firstName.sendKeys(firstNameParam);
        takeScreenshot("after first name");
    }

    public void clickPracticeForm() {
        Utils.explicitWait(driver, ExpectedConditions.visibilityOf(practiceForm), 10);
        practiceForm.click();
    }

    public void clickForms() {
        forms.click();
    }

    public void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void closeAdvert() {
        try {
            js.executeScript("var elem = document.evaluate(\"//*[@id='adplus-anchor']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "elem.parentNode.removeChild(elem);");
        } catch (Exception ignored) {}
        try {
            js.executeScript("var elem = document.evaluate(\"//footer\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "elem.parentNode.removeChild(elem);");
        } catch (Exception ignored) {}
    }

    private void takeScreenshot(String stepName) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(stepName, "image/png", new ByteArrayInputStream(screenshot), ".png");
        } catch (Exception e) {
            Allure.addAttachment("Screenshot Error", e.toString());
        }
    }
}
