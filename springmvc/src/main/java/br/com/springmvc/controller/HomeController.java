package br.com.springmvc.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.springmvc.model.BookType;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(){
		
		System.out.println("Hello World!");
		System.out.println(request.getServletContext().getRealPath("/"));
		System.out.println(request.getRequestURL());
		for(int i=0; i<BookType.values().length;i++){
			System.out.println(BookType.values()[i]);
		}
		return "index";
	}
	   
	@RequestMapping(method = RequestMethod.POST)
	public String save(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes){
    	
    	String UPLOADED_FOLDER = request.getServletContext().getRealPath("/WEB-INF/");
		
		 if (file.isEmpty()) {
	            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
	            return "redirect:/";
	        }

	        try {

	            // Get the file and save it somewhere
	            byte[] bytes = file.getBytes();
	            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
	            Files.write(path, bytes);

	            redirectAttributes.addFlashAttribute("message",
	                        "You successfully uploaded '" + file.getOriginalFilename() + "'");

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        return "redirect:/sucesso";
	}
	
	@GetMapping("/sucesso")
    public String sucesso() {
        return "success";// jsp
    }
}
