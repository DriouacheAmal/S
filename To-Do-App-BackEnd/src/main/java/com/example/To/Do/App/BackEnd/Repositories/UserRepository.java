package com.example.To.Do.App.BackEnd.Repositories;

import com.example.To.Do.App.BackEnd.Entities.Task;
import com.example.To.Do.App.BackEnd.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
