package az.egov.repository;


import az.egov.response.ResponseEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 06.09.2018.
 */
@Repository
public interface Log4MongoRepository extends MongoRepository<ResponseEntity,ObjectId> {

}
