package org.aist.aide.apiservice.service.repos;

import org.aist.aide.apiservice.domain.models.creditcard.CardPattern;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardPatternRepo extends MongoRepository<CardPattern, String> {
}
