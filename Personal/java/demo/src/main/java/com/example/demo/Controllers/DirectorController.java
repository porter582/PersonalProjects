package com.example.demo.Controllers;
import com.example.demo.Models.Director;
import com.example.demo.Services.DirectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/Directors/director")
public class DirectorController
{
    DirectorService _directorService;

    public DirectorController(DirectorService directorService)
    {
        _directorService = directorService;
    }

    @GetMapping
    public ResponseEntity<ArrayList<Director>> getDirectors() {
        return new ResponseEntity<>(_directorService.getDirectors(), HttpStatus.OK);
    }

    @GetMapping({"/{directorId}"})
    public ResponseEntity<Director> getDirectorsById(@PathVariable("directorId") Integer id) {
        return new ResponseEntity<>(_directorService.getDirectorById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Director> insert(@RequestBody Director director) {
        Director insertedDirector = _directorService.insert(director);
        if(insertedDirector.getId().equals(_directorService.getDirectorById(insertedDirector.getId()).getId()))
        {
            return new ResponseEntity<>(insertedDirector, HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<>(insertedDirector, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping({"/{directorId}"})
    public ResponseEntity<Director> updateDirector(@PathVariable("directorId") Integer id, @RequestBody Director director) {
        _directorService.updateDirector(id, director);
        return new ResponseEntity<>(_directorService.getDirectorById(id), HttpStatus.OK);
    }

    @DeleteMapping({"/{directorId}"})
    public ResponseEntity<Director> deleteDirector(@PathVariable("directorId") Integer id) {
        _directorService.deleteDirector(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
