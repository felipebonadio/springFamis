package br.com.famis.controller;

import br.com.famis.model.Produto;
import br.com.famis.service.FamisService;
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

    private final FamisService famisService;

    public ProdutoController(FamisService famisService){
        this.famisService = famisService;
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<Produto> getProduct(@PathVariable("produtoId") UUID produtoId){
        Optional<Produto> produto = this.famisService.findProdutoById(produtoId);
        return produto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getProdutos(){
        List<Produto> produtos = famisService.findAllProduto();
        if(produtos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ResponseEntity<Produto> saveProduto(@RequestBody @Valid Produto produto, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || (produto == null) || (produto.getNome() == null)){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(famisService.saveProduto(produto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Produto> updateProduto(@RequestBody Produto produto, BindingResult bindingResult){
        if(bindingResult.hasErrors() || (produto == null) || (produto.getNome() == null)){
            return ResponseEntity.badRequest().build();
        }
        Optional<Produto> updatedProduto = this.famisService.updateProduto(produto);
        return updatedProduto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{produtoId}")
    public ResponseEntity<Produto> deleteById(@PathVariable("produtoId") UUID produtoId) {
        Optional<Produto> produto = this.famisService.findProdutoById(produtoId);
        if(produto.isPresent()) {
            famisService.deleteProduto(produto.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}