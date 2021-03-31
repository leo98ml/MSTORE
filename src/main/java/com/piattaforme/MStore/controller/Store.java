package com.piattaforme.MStore.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piattaforme.MStore.entity.Prodotto;
import com.piattaforme.MStore.service.ProdottoService;

@CrossOrigin
@RestController
public class Store {
	

	@Autowired
	private ProdottoService service;
	
	@GetMapping(value = "getImage/{idCartella}/{idImage}", produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE})
	public void getImage(HttpServletResponse response, @PathVariable String idCartella, @PathVariable int idImage) {
		try {
			int numOfPics = new File(ProdottoService.projectImagesDirectory+idCartella).list().length;
			if (idImage<0) {
				idImage = Math.abs(idImage)%numOfPics;
				idImage = (numOfPics - idImage)%numOfPics;
			} else {
				idImage = idImage%numOfPics;
			}
			InputStream in = new FileInputStream(new File(ProdottoService.projectImagesDirectory+idCartella+"/"+idImage));
			IOUtils.copy(in, response.getOutputStream());
		} catch (Exception e) {
			
		}
	}
	
	@PostMapping(value = "addProdotto", consumes = {"application/json","multipart/form-data"})
	public void addProdotto(@RequestPart String p, @RequestPart List<MultipartFile> listaFile) {
		try {
			service.insert(new ObjectMapper().readValue(p, Prodotto.class),listaFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@GetMapping(value = "getProdottiOfferta", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Prodotto> getProdotto(){
		return service.getProdottiInOfferta();
		
	}
	@GetMapping(value = "getProdotti", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Prodotto> getProdotti(@RequestParam String type){
		return service.getByType(type);
		
	}
	
	@GetMapping(value = "getItemsById", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Prodotto> getItemsById(@RequestParam List<Integer> id){
		List<Prodotto> ret = new LinkedList<>();
		for ( Integer i : id) {
			try {
				ret.add(service.findById(i));
			}
			catch(Exception e) {
				System.err.print(e.getStackTrace());
			}
		}
		return ret;
		
	}
	
	
}
