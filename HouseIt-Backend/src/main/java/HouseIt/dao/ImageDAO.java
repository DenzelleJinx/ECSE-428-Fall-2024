package HouseIt.dao;

import HouseIt.model.Image;

import org.springframework.data.repository.CrudRepository;

public interface ImageDAO extends CrudRepository<Image, Integer> {

    Image findImageById(int id);

    Image findImageByUrl(String url);

    void deleteById(int id);
}
