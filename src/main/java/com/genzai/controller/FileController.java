package com.genzai.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

	@PostMapping("/uploadFile")
	public String upload(@RequestParam("file") MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Path targetLocation = Paths.get("C:\\development\\workspace-springboot\\ParametrosRest\\" + fileName);
		try {
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return "Se publicó archivo con éxito";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Problemas al publicar archivo";
		}
		
	}
}
