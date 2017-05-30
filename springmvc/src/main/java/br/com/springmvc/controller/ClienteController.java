package br.com.springmvc.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.springmvc.model.Cliente;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@RequestMapping("/form")
	public ModelAndView form(Cliente cliente){	
		return new ModelAndView("cliente/form");
	}
	
	@RequestMapping( method = RequestMethod.POST )
	public ModelAndView save(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result,
			RedirectAttributes redirectAttributes){
		
		if (result.hasErrors()) {
            return form(cliente);
        }
		redirectAttributes.addFlashAttribute("message", "CADASTRADO!");
		return new ModelAndView("cliente/form");
	}
}
