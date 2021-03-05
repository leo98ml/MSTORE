package com.piattaforme.MStore.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.piattaforme.MStore.entity.Prodotto;
import com.piattaforme.MStore.repository.ProdottoRepo;

@Service
public class ProdottoService {

	@Autowired
	private ProdottoRepo repo;

	public void insert(Prodotto p, List<MultipartFile> listaFoto) {
		try {
			// mkdir for this product
			String nomeCartella = "C:/piattaforme/images/" + p.getName().hashCode() + "" + System.currentTimeMillis();
			Path path = Paths.get(nomeCartella);
			Files.createDirectories(path);
			// save all files
			int i = 0;
			for (MultipartFile f : listaFoto) {
				FileOutputStream fos = new FileOutputStream(nomeCartella + "/" + i++);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				bos.write(f.getBytes());
				bos.close();
			}
			// update p
			p.setDirectoryImages(nomeCartella);
			// insert
			repo.save(p);
		} catch (Exception e) {

		}
	}

}
