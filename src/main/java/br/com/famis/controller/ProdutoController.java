package br.com.famis.controller;

import br.com.famis.dto.ProdutoDto;
import br.com.famis.model.Produto;
import br.com.famis.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<ProdutoDto> getProduct(@PathVariable Long produtoId) {
        Optional<ProdutoDto> produtoParaEncontrar = this.produtoService.findProdutoById(produtoId);
        return ResponseEntity.ok(produtoParaEncontrar.get());
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> getProdutos() {
        List<ProdutoDto> produtos = produtoService.findAllProduto();
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> saveProduto(@RequestBody ProdutoDto produtoDto) {
        return new ResponseEntity<>(produtoService.saveProduto(produtoDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProdutoDto> updateProduto(@RequestBody ProdutoDto produtoDto) {
        Optional<ProdutoDto> produtoParaAtualizar = this.produtoService.updateProduto(produtoDto);
        return ResponseEntity.ok(produtoParaAtualizar.get());
    }

    @DeleteMapping("/{produtoId}")
    public ResponseEntity<Produto> deleteById(@PathVariable("produtoId") Long produtoId) {
        produtoService.deleteProduto(produtoId);
        return ResponseEntity.notFound().build();
    }
}
