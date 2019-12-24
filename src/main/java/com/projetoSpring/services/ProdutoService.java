package com.projetoSpring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.projetoSpring.domain.Categoria;
import com.projetoSpring.domain.Produto;
import com.projetoSpring.repositories.CategoriaRepository;
import com.projetoSpring.repositories.ProdutoRepository;
import com.projetoSpring.services.exepitions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	public Produto find(Integer id) {
		Optional<Produto> obj = produtoRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado com id = "+ id + " Tipo " + Produto.class.getName()));
	}
	
	
	public Page<Produto> search(String nome ,List<Integer>ids,Integer page,Integer linesPages,String orderBy,String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPages,Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return produtoRepository.search(nome,categorias, pageRequest);
		
	}

}
