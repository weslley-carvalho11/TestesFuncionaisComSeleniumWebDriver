package br.ce.weslley.core;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    @Rule
    public TestName testName = new TestName();

    @After
    public void finalizar() throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) DriverFactory.getDriver();
        File arquivo = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(arquivo, new File("target" + File.separator + "screenshot" + File.separator + testName.getMethodName() + ".png"));


        if (Propriedades.fechar_browser) {
            DriverFactory.killDriver();
        }
    }
}
