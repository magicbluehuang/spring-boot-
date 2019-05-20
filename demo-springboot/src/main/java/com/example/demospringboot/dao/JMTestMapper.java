package com.example.demospringboot.dao;

import com.example.demospringboot.model.JMTest;
import org.springframework.stereotype.Repository;

@Repository
public interface JMTestMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(JMTest record);

    int insertSelective(JMTest record);

    JMTest selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(JMTest record);

    int updateByPrimaryKey(JMTest record);
}