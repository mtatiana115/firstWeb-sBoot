package com.riwi.firstWeb.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.riwi.firstWeb.Entity.Coder;
import com.riwi.firstWeb.Service.CoderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
  public String showViewGetAll(Model objModel){
    //traer del servicio, llamo el servicio y guardo la lista de coder
    List<Coder> list = this.objCoderService.findAll();
    //Agregar la lista de coders al carrito para que lo lleve a la vista, uso addAtribute (llave, valor)
    objModel.addAttribute("coderList", list);
    //retornar el nombre de cómo se va a llamar el archivo html en la vista
    return "viewCoder";
    //se crea la vista html en la carpeta main/resource/templates
  }

  @GetMapping("/form")
  public String showViewFormCoder(Model objModel) {
    
    objModel.addAttribute("coder", new Coder());
    // para recibir info de la vita
    objModel.addAttribute("action", "/coder/create");
    //necesito crear un postmapping para recibir la info
    return"viewForm";
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