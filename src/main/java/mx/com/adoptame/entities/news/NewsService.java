package mx.com.adoptame.entities.news;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    public List<News> findAll() {
        return (List<News>) newsRepository.findAll();
    }

    public Optional<News> findOne(Integer id) {
        return newsRepository.findById(id);
    }

    public Optional<News> save(News entity) {
        return Optional.of(newsRepository.save(entity));
    }

    public Optional<News> update(News entity) {
        Optional<News> updatedEntity = Optional.empty();
        updatedEntity = newsRepository.findById(entity.getId());
        if (!updatedEntity.isEmpty())
            newsRepository.save(entity);
        return updatedEntity;
    }

    public Optional<News> partialUpdate(Integer id, Map<Object, Object> fields) {
        try {
            News entity = findOne(id).get();
            if (entity == null) {
                return Optional.empty();
            }
            Optional<News> updatedEntity = Optional.empty();
            fields.forEach((updatedField, value) -> {
                Field field = ReflectionUtils.findField(News.class, (String) updatedField);
                field.setAccessible(true);
                ReflectionUtils.setField(field, entity, value);
            });
            newsRepository.save(entity);
            updatedEntity = Optional.of(entity);
            return updatedEntity;
        } catch (Exception exception) {
            System.err.println(exception);
            return Optional.empty();
        }
    }

    public Boolean delete(Integer id) {
        boolean entity = newsRepository.existsById(id);
        if (entity) {
            newsRepository.deleteById(id);
        }
        return entity;
    }
}
