package com.twitter.TwitterWS.persistence.repository;

import com.twitter.TwitterWS.persistence.model.Hashtag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface HashtagRepository extends PagingAndSortingRepository<Hashtag, Integer> {
    Optional<Hashtag> findHashtagByText(String text);
    List<Hashtag> findAllByOrderByCounterDesc(Pageable pageable);
}
