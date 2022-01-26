package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    Integer Id;
    @Column
    String Title;
    @Column
    String ReviewScore;
    @Column
    Integer DirectorId;
}
