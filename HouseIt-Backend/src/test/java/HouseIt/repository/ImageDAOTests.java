package HouseIt.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import HouseIt.dao.ImageDAO;
import HouseIt.model.Image;

@SpringBootTest
public class ImageDAOTests {
    
    @Autowired
    private ImageDAO imageDAO;

    private String url = "testUrl";
    private Image image;

    @BeforeEach
    public void setUp() {
        image = new Image();
        image.setUrl(url);

        imageDAO.save(image);
    }

    @AfterEach
    public void tearDown() {
        imageDAO.deleteAll();
    }

    @Test
    public void testFindImageByUrl() {
        Image foundImage = imageDAO.findImageByUrl(url);

        assertNotNull(foundImage);
        assertEquals(image, foundImage);
    }

    @Test
    public void testFindImageById() {
        Image foundImage = imageDAO.findImageById(image.getId());

        assertNotNull(foundImage);
        assertEquals(image, foundImage);
    }

    @Test
    public void testDeleteImageById() {
        imageDAO.deleteById(image.getId());

        Image foundImage = imageDAO.findImageById(image.getId());

        assertNull(foundImage);
    }
}