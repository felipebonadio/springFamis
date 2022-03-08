package br.com.famis.service;

import br.com.famis.dto.ProdutoDto;
import br.com.famis.exception.BadRequestException;
import br.com.famis.exception.NotFoundException;
import br.com.famis.mapper.ProdutoMapper;
import br.com.famis.model.Produto;
import br.com.famis.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public Optional<ProdutoDto> findProdutoById(Long id) {
        Optional<ProdutoDto> produtoParaEncontrar = produtoRepository.findById(id).map(ProdutoMapper::produtoToDto);
        if(produtoParaEncontrar.isEmpty()){
            throw new NotFoundException("Não foi possível encontrar o produto com id: " + id);
        }
        return produtoParaEncontrar;
    }

    public List<ProdutoDto> findAllProduto() {
        return produtoRepository.findAll().stream().map(ProdutoMapper::produtoToDto).toList();
    }

    public ProdutoDto saveProduto(ProdutoDto produtoDto) {
        if(produtoDto.getNome() == null){
            throw new BadRequestException("Não é possível salvar um produto sem o nome");
        }
        Produto produto = ProdutoMapper.dtoToProduto(produtoDto);
        produto.setId(null);
        return ProdutoMapper.produtoToDto(produtoRepository.save(produto));
    }

    public Optional<ProdutoDto> updateProduto(ProdutoDto produtoDto)  {
        Optional<Produto> produtoParaAtualizar = produtoRepository.findById(produtoDto.getId());
        if (produtoParaAtualizar.isPresent()) {
            produtoParaAtualizar.get().setNome(produtoDto.getNome());
            produtoParaAtualizar.get().setCategoria(produtoDto.getCategoria());
            produtoParaAtualizar.get().setValor(produtoDto.getValor());
            return Optional.of(produtoRepository.save(produtoParaAtualizar.get())).map(ProdutoMapper::produtoToDto);
        }
        throw new NotFoundException("Nçao foi possível localizar o produto com ID: " + produtoDto.getId());
    }

    public void deleteProduto(Long produtoId) {
        Optional<Produto> produtoParaApagar = produtoRepository.findById(produtoId);
        produtoRepository.delete(produtoParaApagar.get());
    }
}
