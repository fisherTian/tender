package com.ziyu.tender.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import com.ziyu.tender.model.Xjgg;

@Component
public interface XjggRepository extends ElasticsearchRepository<Xjgg,String> {
}