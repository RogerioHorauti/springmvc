package br.com.springmvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.springmvc.daos.ProductDAO;
import br.com.springmvc.model.BookType;
import br.com.springmvc.model.Product;
//import br.com.springmvc.validation.ProductValidator;

@Controller// Interage com os requests vindo da web
@Transactional
@RequestMapping("/produtos")
public class ProductsController {
	
	/*@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new ProductValidator());
	}*/
	
	@Autowired// É responsável por indicar os pontos de injeção dentro da classe
	private ProductDAO dao;
	
	@RequestMapping("/form")
	public ModelAndView formulario(Product product){
		ModelAndView mv = new ModelAndView("products/form");// cadastro.jsp	
		mv.addObject("types", BookType.values());
		return mv;// cadastro.jsp
	}
	
	@RequestMapping(method=RequestMethod.POST)//http://localhost:8080/springmvc/produtos
	@CacheEvict(value="books", allEntries=true)
	public ModelAndView save(@Valid Product product, BindingResult result,
			RedirectAttributes redirectAttributes){
				
		if(result.hasErrors()){
			System.out.println(product.getDscription());
			return formulario(product);// Mantém os dados digitados
		}
		
		//dao = new ProductDAO();// @Autowired
		System.out.println("Cadastro de produtos"+product.getDscription());
		
		dao.save(product);
		// Quando acontecer um redirect enviar a variável success
		redirectAttributes.addFlashAttribute("success", "Produto cadastrado com sucesso");
		
		/*O prefixo redirect: indica para o Spring MVC que, em vez de simplesmente
		fazer um forward, é necessário que ele retorne o status 302 para o
		navegador, solicitando que omesmo faça um novo request para o novo endereço.*/	
		return new ModelAndView("redirect:produtos");
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@Cacheable(value="books")
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", dao.list());
		return modelAndView;
	}
	
	@RequestMapping("/{id}")
	public ModelAndView show(@PathVariable("id") Integer id){
		ModelAndView modelAndView = new ModelAndView("products/show");
		modelAndView.addObject("product", dao.find(id));
		return modelAndView;
	}
	
	@RequestMapping("/del")
	public void delete(Product product){
		dao.delete(product);
	}
	
}
