package br.com.springmvc.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.springmvc.model.Product;

public class ProductValidator implements Validator{

	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dscription", "field.required");
		Product produto = (Product) target;
		if(produto.getPages() == 0){
			errors.rejectValue("pages", "field.required");
		}
		
	}

}
