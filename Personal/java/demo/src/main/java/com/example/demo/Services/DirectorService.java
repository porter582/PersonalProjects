package com.example.demo.Services;
import com.example.demo.Models.Director;
import com.example.demo.Repositories.IDirectorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DirectorService implements IDirectorService{

    IDirectorRepository _directorRepository;

    public DirectorService( IDirectorRepository directorRepository)
    {
        _directorRepository = directorRepository;
    }

    @Override
    public ArrayList<Director> getDirectors() {
        ArrayList<Director> directorList = new ArrayList<Director>();
        _directorRepository.findAll().forEach(directorList::add);
        return directorList;
    }

    @Override
    public Director getDirectorById(Integer id)
    {
        return _directorRepository.findById(id).get();
    }

    @Override
    public Director insert(Director director)
    {
        return _directorRepository.save(director);
    }

    @Override
    public void updateDirector(Integer id, Director director)
    {
        Director oldDirector = _directorRepository.findById(id).get();
        oldDirector.setFirstName(director.getFirstName());
        oldDirector.setLastName(director.getLastName());
        _directorRepository.save(oldDirector);
    }

    @Override
    public void deleteDirector(Integer id)
    {
        _directorRepository.deleteById(id);
    }
}
