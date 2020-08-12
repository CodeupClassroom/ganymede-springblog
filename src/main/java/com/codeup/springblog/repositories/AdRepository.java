package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Ad is the reference type of the entity to CRUD
// Long is the reference type for the primary key of Ad
public interface AdRepository extends JpaRepository<Ad, Long> {

    Ad findByTitle(String title);

}
