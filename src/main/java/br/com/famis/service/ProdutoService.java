package br.com.famis.service;

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

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public Optional<Produto> findProdutoById(UUID id) throws DataAccessException {
        return produtoRepository.findById(id);
    }

    public List<Produto> findAllProduto() throws DataAccessException {
        return (List<Produto>) produtoRepository.findAll();
    }

    public Produto saveProduto(Produto produto) throws DataAccessException {
        return produtoRepository.save(produto);
    }

    public Optional<Produto> updateProduto(Produto produto) throws DataAccessException {
        Optional<Produto> currentProduct = produtoRepository.findById(produto.getId());
        if (currentProduct.isPresent()) {
            currentProduct.get().setNome(produto.getNome());
            currentProduct.get().setCategoria(produto.getCategoria());
            currentProduct.get().setValor(produto.getValor());
            return Optional.of(produtoRepository.save(currentProduct.get()));
        }
        return Optional.empty();
    }

    public void deleteProduto(Produto produto) throws DataAccessException {
        produtoRepository.delete(produto);
    }
}
