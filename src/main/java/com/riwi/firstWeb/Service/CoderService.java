package com.riwi.firstWeb.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riwi.firstWeb.Entity.Coder;
import com.riwi.firstWeb.Repository.CoderRepository;

/*
 *Indica que esta clase será un servicio 
 */
@Service
public class CoderService {

  //hacer la inyeccion de dependencias con @Autowired o @Bean
  @Autowired
  private CoderRepository objCoderRepository;

  //creo los métodos
  //Importo la lista de .util
  /*
   * Servicio para listar todos los coders
   */
  public List<Coder> findAll(){
    return this.objCoderRepository.findAll();
  }
}
