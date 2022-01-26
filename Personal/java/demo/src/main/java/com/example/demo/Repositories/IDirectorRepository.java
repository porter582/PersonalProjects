package com.example.demo.Repositories;

import com.example.demo.Models.Director;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDirectorRepository extends CrudRepository<Director, Integer>
{
}
