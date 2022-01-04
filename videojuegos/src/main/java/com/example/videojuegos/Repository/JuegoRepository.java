package com.example.videojuegos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.videojuegos.Model.Juego;

public interface JuegoRepository extends JpaRepository <Juego,Integer>{

}
