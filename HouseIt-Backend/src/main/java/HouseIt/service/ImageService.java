package HouseIt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import HouseIt.dao.ImageDAO;
import HouseIt.model.Image;

@Service
public class ImageService {
    
    @Autowired
    private ImageDAO imageDAO;

    @Transactional
    public Image createImage(Image image) {
        return imageDAO.save(image);
    }

    @Transactional
    public Image updateImage(Image image) {
        return imageDAO.save(image);
    }
}
