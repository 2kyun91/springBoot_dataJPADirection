package com.example.persistence;

import org.springframework.data.repository.CrudRepository;

import com.example.dao.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Long>{

}
