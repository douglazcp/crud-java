package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.TipoItem;
import com.repository.TipoItemRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TipoItemController {

	@Autowired
	TipoItemRepository tipoItemRepository;

	@GetMapping("/tipoItems")
	public ResponseEntity<List<TipoItem>> getAllTipoItems(@RequestParam(required = false) String descricao, @RequestParam(required = false) String tipo) {
		try {
			List<TipoItem> tipoItems = new ArrayList<TipoItem>();
			System.out.println("descricao -> "+descricao);
			System.out.println("tipo -> "+tipo);
			if(tipo != null && !tipo.isEmpty() && descricao != null && !descricao.isEmpty()){
				tipoItemRepository.findByDescricaoAndTipo(descricao, tipo);
			}else if(tipo != null && !tipo.isEmpty())
				tipoItemRepository.findByTipo(tipo).forEach(tipoItems::add);
			else if (descricao != null && !descricao.isEmpty())
				tipoItemRepository.findByDescricao(descricao).forEach(tipoItems::add);
			else {
				tipoItemRepository.findAll().forEach(tipoItems::add);
			}

			if (tipoItems.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tipoItems, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/tipoItems/{id}")
	public ResponseEntity<TipoItem> getTipoItemById(@PathVariable("id") long id) {
		Optional<TipoItem> tipoItemData = tipoItemRepository.findById(id);
		if (tipoItemData.isPresent()) {
			return new ResponseEntity<>(tipoItemData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/tipoItems")
	public ResponseEntity<TipoItem> createTipoItem(@RequestBody TipoItem tipoItem) {
		try {
			TipoItem tipoItemNovo = tipoItemRepository.save(new TipoItem(tipoItem.getDescricao(), tipoItem.getTipo()));
			return new ResponseEntity<>(tipoItemNovo, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/tipoItems/{id}")
	public ResponseEntity<TipoItem> updateTipoItem(@PathVariable("id") long id, @RequestBody TipoItem tipoItem) {
		Optional<TipoItem> tutorialData = tipoItemRepository.findById(id);

		if (tutorialData.isPresent()) {
			TipoItem tipoItemNovo = tutorialData.get();
			tipoItemNovo.setDescricao(tipoItem.getDescricao());
			return new ResponseEntity<>(tipoItemRepository.save(tipoItemNovo), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/tipoItems/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
		try {
			System.out.println("Delete -> "+id);
			tipoItemRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/tipoItems")
	public ResponseEntity<HttpStatus> deleteAllTipoItems() {
		try {
			tipoItemRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
