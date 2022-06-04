package br.cursojava.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import br.cursojava.entity.Cliente;
import br.cursojava.entity.Endereco;
import br.cursojava.entity.Setor;
import br.cursojava.exception.MenssageNotFoundException;
import br.cursojava.repository.ClienteRepository;
import br.cursojava.services.ClienteService;

/**
 * @author Hendrew Martins
 *         Classe de Negocio Cliente
 */
@ApplicationScoped
public class DefaultClienteService implements ClienteService {

    @Inject
    public ClienteRepository respository;

    @Inject
    EntityManager entityManager;

    @Transactional
    @Override
    public Cliente save(Cliente cliente) {
        for (Endereco endereco : cliente.getEndereco()) {
            endereco.setCliente(cliente);
        }
        respository.persistAndFlush(cliente);
        return cliente;
    }

    @Transactional
    @Override
    public Cliente update(Long id, Cliente cliente) throws MenssageNotFoundException {
        Cliente update = getById(id);
        update.setNome(cliente.getNome());
        this.atualizarEnderecoCliente(update, cliente);
        this.atualizarSetor(update, cliente);
        return update;
    }

    @Transactional
    @Override
    public void delete(Long id) throws MenssageNotFoundException {
        respository.delete(getById(id));

    }

    @Override
    public List<Cliente> all() {
        return entityManager.createNativeQuery("Select * from cliente", Cliente.class)
                .getResultList();
    }

    @Override
    public Cliente getById(Long id) throws MenssageNotFoundException {
        return respository.findByIdOptional(id)
                .orElseThrow(() -> new MenssageNotFoundException("Não foi possivel localizar o Id informado"));

    }

    /**
     * 
     * @param   update
     * @param cliente
     */
    private void atualizarEnderecoCliente(Cliente update, Cliente cliente) {
        List<Endereco> enderecoRemover = new ArrayList<>();

        for (Endereco enderecoBanco : update.getEndereco()) {
            String acao = "REMOVER";

            // Endereco cliente parametro
            for (Endereco enderecoRecebido : cliente.getEndereco()) {
                if (enderecoBanco.getId() == enderecoRecebido.getId()) {
                    enderecoBanco.setNome(enderecoRecebido.getNome());
                    acao = "ATUALIZAR";
                }
            }

            // se não localizar para atualizar remove
            if (acao == "REMOVER") {
                enderecoRemover.add(enderecoBanco);
            }
        }

        // remove registro que não estão mais no Array
        for (Endereco enderecoRemove : enderecoRemover) {
            update.removeEndereco(enderecoRemove);
        }

        for (Endereco endereco : cliente.getEndereco()) {
            if (endereco.getId() == null) {
                update.addEndereco(endereco);
            }
        }
    }

    private void atualizarSetor(Cliente update, Cliente cliente) {
        List<Setor> setorRemover = new ArrayList<>();
        List<Setor> setorValidado = new ArrayList<>();

        for (Setor setorBanco : update.getSetor()) {
            String acao = "REMOVER";
            for (Setor setorRecebido : cliente.getSetor()) {
                if (setorRecebido.getId() == setorBanco.getId()) {
                    setorValidado.add(setorRecebido);
                    acao = "ATUALIZAR";
                }
            }

            if (acao == "REMOVER") {
                setorRemover.add(setorBanco);
            }
        }

        for (Setor setor : setorRemover) {
            update.removeSetor(setor);
        }

        for (Setor setor : cliente.getSetor()) {
            String acao = "ADICIONAR";
            for (Setor setorExiste : setorValidado) {
                if (setor.getId() == setorExiste.getId()) {
                    acao = "EXISTE";
                }
            }
            if (acao == "ADICIONAR") {
                update.addSetor(setor);
            }
        }
    }

}
