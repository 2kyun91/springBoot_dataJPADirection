package com.example.persistence;

import org.springframework.data.repository.CrudRepository;

import com.example.dto.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Long>{

}
