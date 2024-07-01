package com.example.cache.client.repositories.cache;

import com.example.cache.client.dto.Person;
import com.example.cache.client.view.PersonView;
import com.telkomsigma.framework.core.api.cache.repository.annotation.*;
import com.telkomsigma.framework.core.api.cache.repository.interfaces.IBaseCacheRepository;
import com.telkomsigma.framework.core.api.cache.repository.spec.PageableSearchCriteria;

import java.util.List;

/**
 * Library Mode / Embedded Cache Repository
 */
@CacheRepository(cacheName = "personCache", entityClass = Person.class, remoteCache = false)
public interface PersonCacheRepository extends IBaseCacheRepository<String, Person> {

    @Query("select nik, firstName from com.example.cache.client.dto.Person where nik = :nik")
    List<Object> findById(@Param String nik);

    @Query("select nik, fullName from com.example.cache.client.dto.Person")
    List<PersonView> findPersonView();

    @Query("select nik, fullName from com.example.cache.client.dto.Person")
    List<PersonView> findPersonView(@CachePostfix String nik);

    List<Person> findByUserName(PageableSearchCriteria psc);

    void save(Person data, @CachePostfix String nik);

    void add(Person data, @CachePostfix String nik);

    List<Person> findByUserName(PageableSearchCriteria psc, @CachePostfix String nik);

    List<Person> selectAll(@CachePostfix String nik);

}
