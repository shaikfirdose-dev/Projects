package com.firdose.ars.repository;

import com.firdose.ars.dto.Login;
import com.firdose.ars.enums.Role;
import com.firdose.ars.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {
    @Query("select lg from LoginDTO lg where lg.role=:p1")
    public List<Login> viewUsersByRole(@Param("p1") Role role);

    @Query("select lg from LoginDTO lg where lg.status=:p2")
    public List<Login> viewUsersByStatus(@Param("p2") Status status);

}