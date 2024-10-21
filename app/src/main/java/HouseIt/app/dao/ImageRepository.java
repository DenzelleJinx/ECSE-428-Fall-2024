package HouseIt.app.dao;

import HouseIt.app.model.Image;

import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Integer> {

    Image findImageByURL(String url);

}
