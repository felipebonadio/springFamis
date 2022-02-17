package br.com.famis.service;

import br.com.famis.model.*;
import br.com.famis.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FamisServiceImpl implements FamisService {

    private final EnderecoRepository enderecoRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final MesaRepository mesaRepository;
    private final RestauranteRepository restauranteRepository;
    private final ProdutoRepository produtoRepository;

    @Autowired
    public FamisServiceImpl(
            EnderecoRepository enderecoRepository,
            ColaboradorRepository colaboradorRepository,
            MesaRepository mesaRepository,
            RestauranteRepository restauranteRepository,
            ProdutoRepository produtoRepository) {
        this.enderecoRepository = enderecoRepository;
        this.colaboradorRepository = colaboradorRepository;
        this.mesaRepository = mesaRepository;
        this.restauranteRepository = restauranteRepository;
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Optional<Endereco> findEnderecoById(UUID id) throws DataAccessException {
        return enderecoRepository.findById(id);
    }

    @Override
    public List<Endereco> findAllEnderecos() throws DataAccessException {
        return (List<Endereco>) enderecoRepository.findAll();
    }

    @Override
    public Endereco saveEndereco(Endereco endereco) throws DataAccessException {
        return enderecoRepository.save(endereco);
    }

    @Override
    public Optional<Endereco> updateEndereco(Endereco endereco) throws DataAccessException {
        Optional<Endereco> currentAddress = enderecoRepository.findById(endereco.getId());
        if (currentAddress.isPresent()) {
            currentAddress.get().setCep(endereco.getCep());
            currentAddress.get().setLogradouro(endereco.getLogradouro());
            currentAddress.get().setNumero(endereco.getNumero());
            currentAddress.get().setBairro(endereco.getBairro());
            currentAddress.get().setLocalidade(endereco.getLocalidade());
            currentAddress.get().setUf(endereco.getUf());
            return Optional.of(enderecoRepository.save(currentAddress.get()));
        }
        return Optional.empty();
    }

    @Override
    public void deleteEndereco(Endereco endereco) throws DataAccessException {
        enderecoRepository.delete(endereco);
    }

    @Override
    public Optional<Colaborador> findColaboradorById(UUID id) {
        return colaboradorRepository.findById(id);
    }

    @Override
    public List<Colaborador> findAllColaboradores() throws DataAccessException {
        return (List<Colaborador>) colaboradorRepository.findAll();
    }

    @Override
    public Colaborador saveColaborador(Colaborador colaborador) throws DataAccessException {
        return colaboradorRepository.save(colaborador);
    }

    @Override
    public Optional<Colaborador> updateColaborador(Colaborador colaborador) throws DataAccessException {
        Optional<Colaborador> currentCollaborator = this.findColaboradorById(colaborador.getId());
        if (currentCollaborator.isPresent()) {
            currentCollaborator.get().setNome(colaborador.getNome());
            currentCollaborator.get().setSobrenome(colaborador.getSobrenome());
            currentCollaborator.get().setEndereco(colaborador.getEndereco());
            currentCollaborator.get().setCpf(colaborador.getCpf());
            currentCollaborator.get().setFuncao(colaborador.getFuncao());
            currentCollaborator.get().setEmail(colaborador.getEmail());
            currentCollaborator.get().setSenha(colaborador.getSenha());
            currentCollaborator.get().setTelefone(colaborador.getTelefone());
            currentCollaborator.get().setRestaurante(colaborador.getRestaurante());
            return Optional.of(colaboradorRepository.save(currentCollaborator.get()));
        }
        return Optional.empty();
    }

    @Override
    public void deleteColaborador(Colaborador colaborador) throws DataAccessException {
        colaboradorRepository.delete(colaborador);
    }

    @Override
    public Optional<Mesa> findMesaById(UUID id) throws DataAccessException {
        return mesaRepository.findById(id);
    }

    @Override
    public List<Mesa> findAllMesas() throws DataAccessException {
        return (List<Mesa>) mesaRepository.findAll();
    }

    @Override
    public Mesa saveMesa(Mesa mesa) throws DataAccessException {
        return mesaRepository.save(mesa);
    }

    @Override
    public Optional<Mesa> updateMesa(Mesa mesa) throws DataAccessException {
        Optional<Mesa> currentConsumer = mesaRepository.findById(mesa.getId());
        if (currentConsumer.isPresent()) {
            currentConsumer.get().setNumero(mesa.getNumero());
            return Optional.of(mesaRepository.save(currentConsumer.get()));
        }
        return Optional.empty();
    }

    @Override
    public void deleteMesa(Mesa mesa) throws DataAccessException {
        mesaRepository.delete(mesa);
    }

    @Override
    public Optional<Restaurante> findRestauranteById(UUID id) throws DataAccessException {
        return restauranteRepository.findById(id);
    }

    @Override
    public List<Restaurante> findAllRestaurantes() throws DataAccessException {
        return (List<Restaurante>) restauranteRepository.findAll();
    }

    @Override
    public Restaurante saveRestaurante(Restaurante restaurante) throws DataAccessException {
        return restauranteRepository.save(restaurante);
    }

    @Override
    public Optional<Restaurante> updateRestaurante(Restaurante restaurante) throws DataAccessException {
        Optional<Restaurante> currentRestaurant = this.findRestauranteById(restaurante.getId());
        if (currentRestaurant.isPresent()) {
            currentRestaurant.get().setNome(restaurante.getNome());
            currentRestaurant.get().setTelefone(restaurante.getTelefone());
            currentRestaurant.get().setCnpj(restaurante.getCnpj());
            currentRestaurant.get().setEndereco(restaurante.getEndereco());
            currentRestaurant.get().setMesa(restaurante.getMesa());
            currentRestaurant.get().setHorarioAbertura(restaurante.getHorarioAbertura());
            currentRestaurant.get().setHorarioEncerramento(restaurante.getHorarioEncerramento());
            return Optional.of(restauranteRepository.save(currentRestaurant.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Restaurante> updateMesaOnRestaurante(Restaurante restaurante) throws DataAccessException {
        Optional<Restaurante> currentRestaurant = this.findRestauranteById(restaurante.getId());
        if (currentRestaurant.isPresent()) {
            currentRestaurant.get().setMesa(restaurante.getMesa());
            return Optional.of(restauranteRepository.save(currentRestaurant.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Restaurante> updateHorarioOnRestaurante(Restaurante restaurante) throws DataAccessException {
        Optional<Restaurante> currentRestaurant = this.findRestauranteById(restaurante.getId());
        if (currentRestaurant.isPresent()) {
            currentRestaurant.get().setHorarioAbertura(restaurante.getHorarioAbertura());
            currentRestaurant.get().setHorarioEncerramento(restaurante.getHorarioEncerramento());
            return Optional.of(restauranteRepository.save(currentRestaurant.get()));
        }
        return Optional.empty();
    }

    @Override
    public void deleteRestaurante(Restaurante restaurante) throws DataAccessException {
        restauranteRepository.delete(restaurante);
    }

    @Override
    public Optional<Produto> findProdutoById(UUID id) throws DataAccessException {
        return produtoRepository.findById(id);
    }

    @Override
    public List<Produto> findAllProduto() throws DataAccessException {
        return (List<Produto>) produtoRepository.findAll();
    }

    @Override
    public Produto saveProduto(Produto produto) throws DataAccessException {
        return produtoRepository.save(produto);
    }

    @Override
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

    @Override
    public void deleteProduto(Produto produto) throws DataAccessException {
        produtoRepository.delete(produto);
    }

}
