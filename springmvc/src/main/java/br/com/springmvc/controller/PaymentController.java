package br.com.springmvc.controller;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.springmvc.model.PaymentData;
import br.com.springmvc.model.ShoppingCart;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private ShoppingCart shoppingCart;
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(method = RequestMethod.POST)
	public Callable<ModelAndView> checkout(RedirectAttributes redirectAttributes) {
		return () -> {// faz parte do Callable
			BigDecimal total = shoppingCart.getTotal();
			String uriToPay = "http://book-payment.herokuapp.com/payment";
			String response="";
			
			try {			
				response = restTemplate.postForObject(uriToPay, new PaymentData(total), String.class);
				System.out.println("response : "+response);
				redirectAttributes.addFlashAttribute("message", response);
				
				return new ModelAndView("redirect:/produtos");	
				
			} catch (HttpClientErrorException exception) {
				redirectAttributes.addFlashAttribute("message", response);
				return new ModelAndView("redirect:/produtos");	
			}
		};
	}

}
