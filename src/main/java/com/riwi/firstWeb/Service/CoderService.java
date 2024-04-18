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

  /*
  *Servicio para guardar un coder
  */
  public Coder insert(Coder objCoder){
    return this.objCoderRepository.save(objCoder);
  }

  /*
   * Servicio para actualizar un coder
   */

  public Coder update(Long id,Coder objCoder){
    /* Buscar al coder con ese id */
    Coder objCoderDB = this.findById(id);

    /* Veridicar que si exista */
    if (objCoder == null) return null;
  
    /*Actualizar el coder antiguo */
    objCoderDB = objCoder;

    /*guardarlo */

    return this.objCoderRepository.save(objCoderDB);
  }

  //obtener el id
  public Coder findById(Long id){
    
    return this.objCoderRepository.findById(id).orElse(null);
  }
}
