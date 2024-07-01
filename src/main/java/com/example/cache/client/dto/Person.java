package com.example.cache.client.dto;

import lombok.Data;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@Indexed
public class Person implements Serializable {
    @Id
    @Field(index = Index.YES)
    private String nik;
    private String firstName;
    private String lastName;
    private String fullName;
}
