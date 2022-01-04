package com.example.videojuegos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.videojuegos.Model.Cliente;


public interface ClienteRepository extends JpaRepository <Cliente,Integer>{

}
