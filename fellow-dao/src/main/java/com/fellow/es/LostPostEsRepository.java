package com.fellow.es;

import com.fellow.domain.es.LostPostEsDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LostPostEsRepository extends ElasticsearchRepository<LostPostEsDomain, Long> {

//	List<LostPostEsDomain> findByNameAndPrice(String name, Float price);
//
//    List<LostPostEsDomain> findByNameOrPrice(String name, Float price);
//
//    Page<LostPostEsDomain> findByName(String name, Pageable page);
//
//    Page<LostPostEsDomain> findByNameNot(String name, Pageable page);
//
//    Page<LostPostEsDomain> findByPriceBetween(Float price, Pageable page);
//
//    Page<LostPostEsDomain> findByNameLike(String name, Pageable page);
//
//    @Query("{\"bool\" : {\"must\" : {\"term\" : {\"price\" : \"?0\"}}}}")
//    Page<LostPostEsDomain> findByPrice(Float price, Pageable pageable);
	
}
