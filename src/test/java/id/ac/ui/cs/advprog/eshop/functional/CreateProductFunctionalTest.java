package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createNewProduct_isSuccessful(ChromeDriver driver) throws Exception {
        // 1️⃣ Navigate ke halaman create product
        driver.get(baseUrl + "/product/create");

        // 2️⃣ Isi form product
        WebElement nameInput = driver.findElement(By.name("productName"));
        WebElement quantityInput = driver.findElement(By.name("productQuantity"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        nameInput.sendKeys("Laptop Gaming");
        quantityInput.sendKeys("10");

        // 3️⃣ Klik tombol submit
        submitButton.click();

        // 4️⃣ Redirect ke halaman list product
        driver.get(baseUrl + "/product/list");

        // 5️⃣ Cek apakah produk berhasil ditampilkan di list
        WebElement productTable = driver.findElement(By.tagName("table"));
        String pageSource = productTable.getText();

        assertTrue(pageSource.contains("Laptop Gaming"), "Product name should be in the list");
        assertTrue(pageSource.contains("10"), "Product quantity should be in the list");
    }
}
