package br.cursojava.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

@ApplicationScoped
public class DefaultClienteService implements ClienteService {

    @Inject
    public ClienteRepository respository;

    @Inject 
    public EntityManager em;

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

        List<Cliente> cli = (List<Cliente>) em.createNativeQuery("select * from cliente",Cliente.class).getResultList();

        List<Endereco> enderecoRemover = new ArrayList<>();
        List<Setor> setorRemover = new ArrayList<>();
        List<Setor> setorValidado = new ArrayList<>();
        Cliente update = getById(id);
        update.setNome(cliente.getNome());

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

        for (Setor setor: cliente.getSetor()){
            String acao = "ADICIONAR";
            for(Setor setorExiste: setorValidado){
                if(setor.getId() == setorExiste.getId()){
                    acao = "EXISTE";
                }
            }
            if(acao == "ADICIONAR") {
                update.addSetor(setor);
            }
        }

        return update;
    }

    @Transactional
    @Override
    public void delete(Long id) throws MenssageNotFoundException {
        respository.delete(getById(id));

    }

    @Override
    public List<Cliente> all() {
        return respository.listAll();
    }

    @Override
    public Cliente getById(Long id) throws MenssageNotFoundException {
        return respository.findByIdOptional(id)
                .orElseThrow(() -> new MenssageNotFoundException("Não foi possivel localizar o Id informado"));

    }

}
