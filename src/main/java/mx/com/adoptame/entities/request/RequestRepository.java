package mx.com.adoptame.entities.request;

import mx.com.adoptame.entities.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends CrudRepository<Request,Integer> {
    List<Request> findAllByIsCanceledAndIsAcceptedFalse(Boolean isCanceled);
    Integer countByIsAcceptedIsFalse();
    Optional<Request> findByUser(User user);
}
