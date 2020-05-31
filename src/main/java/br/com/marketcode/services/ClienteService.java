package br.com.marketcode.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;

import br.com.marketcode.model.Cliente;

@ApplicationScoped
public class ClienteService {

    public void  updateCliente(Cliente cliente, Long id) throws Exception{

        Cliente clienteBanco = Cliente.findById(id);
         if (clienteBanco == null) {
            throw new Exception("Não encontrado");
        }
        clienteBanco.nome = cliente.nome;

        //TODO COMPLETAR O METODO


        clienteBanco.persist();
    }
    
}