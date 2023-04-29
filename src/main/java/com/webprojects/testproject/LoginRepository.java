package com.webprojects.testproject;

import org.springframework.data.jpa.repository.JpaRepository;
import com.webprojects.testproject.models.Login;

public interface LoginRepository extends JpaRepository<Login, Integer>{
}
