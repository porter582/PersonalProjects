package com.example.demo.Services;
import com.example.demo.Models.Director;

import java.util.ArrayList;

public interface IDirectorService {
    ArrayList<Director> getDirectors();

    Director getDirectorById(Integer id);

    Director insert(Director director);

    void updateDirector(Integer id, Director director);

    void deleteDirector(Integer id);
}
