package com.ziyu.tender.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import com.ziyu.tender.model.Gkzb;

@Component
public interface GkzbRepository extends ElasticsearchRepository<Gkzb,String> {
}
