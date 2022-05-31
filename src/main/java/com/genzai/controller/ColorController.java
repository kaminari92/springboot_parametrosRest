package com.genzai.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.genzai.model.Color;

@RestController
public class ColorController {

	List<Color> listado;

	@PostConstruct
	public void init() {
		if (getListado() == null) {
			setListado(new ArrayList<Color>());
		}
		if (getListado().isEmpty()) {
			getListado().add(new Color(1, "Negro", "0.0.0"));
			getListado().add(new Color(2, "Blanco", "255.255.255"));
		}
	}

	@GetMapping("/colors")
	public List<Color> findAll() {
		return listado;
	}

	// Parámetros por URL
	@GetMapping("/colors/{id}")
	public Color findById(@PathVariable("id") Integer id) {
		return getListado().stream().filter(c -> c.getId().equals(id)).findFirst().get();
	}

	// Parámetros por URL
	@GetMapping("/colors/{id}/{name}")
	public String findByIdNombre(@PathVariable("id") Integer id, @PathVariable("name") String name) {
		return getListado().stream().filter(c -> c.getId().equals(id)).filter(c -> c.getColor().equals(name))
				.findFirst().get().getRgb();
	}

	// Parámetros por Query
	@GetMapping("/colorsQuery")
	public Color findById(@RequestParam("name") String name) {
		return getListado().stream().filter(c -> c.getColor().equals(name))
				.findFirst().get();
	}

	@PostMapping("/colors")
	public String saveColor(@RequestBody Color color) {
		getListado().add(color);
		return "usted guardo el color con id: " + color.getId() + " Descripción: " + color.getColor();
	}

	@PutMapping("/colors/{id}")
	public String updateColor(@PathVariable("id") Integer id, @RequestBody Color color) {
		getListado().stream().filter(c -> c.getId().equals(id)).findFirst().get().setColor(color.getColor());
		return "usted actualizó el color con id: " + id + " con el nuevo color: " + color.getColor();
	}

	@DeleteMapping("/colors/{id}")
	public String deleteColor(@PathVariable("id") Integer id) {
		Color color = getListado().stream().filter(c -> c.getId().equals(id)).findFirst().get();
		getListado().remove(color);
		return "usted eliminó el color con id: " + id + " descripción: " + color.getColor();
	}

	public List<Color> getListado() {
		return listado;
	}

	public void setListado(List<Color> listado) {
		this.listado = listado;
	}

}
