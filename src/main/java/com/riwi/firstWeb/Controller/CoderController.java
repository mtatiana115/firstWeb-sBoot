package com.riwi.firstWeb.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.riwi.firstWeb.Entity.Coder;
import com.riwi.firstWeb.Service.CoderService;


import org.springframework.web.bind.annotation.PostMapping;




@Controller
@RequestMapping("/")
public class CoderController {

  @Autowired
  private CoderService objCoderService;

  /*
   * Método para mostrar la vista y enviarle la lista coders
   */
  @GetMapping   //Lo dejo sin ruta para que se active automáticamente este de acá
//Importo model de springFramneWork.ui.model=> es el conector entre la vista y java es como un carrito que conecta java con la vista en html
  public String showViewGetAll(Model objModel, @RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "3") int size){
    //traer del servicio, llamo el servicio y guardo la lista de coder
    Page<Coder> list = this.objCoderService.findPaginated(page-1, size);
    //Agregar la lista de coders al carrito para que lo lleve a la vista, uso addAtribute (llave, valor)
    objModel.addAttribute("coderList", list);
    objModel.addAttribute("currentPage", page);
    objModel.addAttribute("totalPages", list.getTotalPages());
    //retornar el nombre de cómo se va a llamar el archivo html en la vista
    return "viewCoder";
    //se crea la vista html en la carpeta main/resource/templates
  }
//formulario para agregar coder
  @GetMapping("/form")
  public String showViewFormCoder(Model objModel) {
    
    objModel.addAttribute("coder", new Coder());
    // para recibir info de la vita
    objModel.addAttribute("action", "/coder/create");
    //necesito crear un postmapping para recibir la info
    return"viewForm";
  }


/*
 * Método para mostrar el formulario de actualizar un coder
 */
//patParam me permite obtener el valor de la ruta
@GetMapping("/update/{id}")
public String showFormUpdate(@PathVariable Long id, Model objModel) {
  Coder objCoderFind = this.objCoderService.findById(id);
  objModel.addAttribute("coder", objCoderFind);
  objModel.addAttribute("action", "/edit/" + id);

    return "viewForm";
}

/*
 * Métoddo para actualizar
 */
//postMapping es cuando tenemos un formulario
@PostMapping("/edit/{id}")
public String updateCoder(@PathVariable Long id,@ModelAttribute Coder objCoder) {
  
  this.objCoderService.update(id, objCoder);
    
    return "redirect:/";
}

/*
 * Método para eliminar
 */
//getMapping es cuando NO tenemos un formulario
@GetMapping("/delete/{id}")
//pathVariable es para poder recibir el id
public String deleteCoder(@PathVariable Long id, @ModelAttribute Coder objCoder) {
  this.objCoderService.delete(id);    
    return "redirect:/";
}



    /*Método para insertar un coder mediante el verbo POST
     * @ModelAttribute se encarga de obtener la información enviada desde la vista
     */
  @PostMapping("/coder/create")
  public String createCoder(@ModelAttribute Coder objCoder) {
      //para recibir info @ModelAttribute
      //llamamos el servicio enviandole el coder a insertar
      this.objCoderService.insert(objCoder);
      return "redirect:/";
  }
  
}


//static guarda archivos css, javaScript