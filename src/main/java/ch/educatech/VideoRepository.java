package ch.educatech;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video, String> {

    public List<Video> findByCategory(String category);
    public List<Video> findById(String id);

}
