package br.cursojava.services;


import java.util.List;

import br.cursojava.entity.Empresa;
import br.cursojava.exception.MenssageNotFoundException;

public interface EmpresaService {
    
    Empresa saveEmpresa(Empresa empresa);

    Empresa updateEmpresa(Long id, Empresa empresa) throws MenssageNotFoundException;

    void deleteEmpresa(Long id) throws MenssageNotFoundException;

    List<Empresa> allEmpresa();

    Empresa getEmpresaById(Long id) throws MenssageNotFoundException;
    
}
