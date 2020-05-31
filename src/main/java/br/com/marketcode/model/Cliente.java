package br.com.marketcode.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "tb_cliente")
public class Cliente extends PanacheEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    public Long id;
    
    @Column(name="senha",nullable = false)
    @NotNull(message = "Campo obrigatório")
    public String senha;

    @Column(name="nome",nullable = false)
    @NotNull(message = "Campo obrigatório")
    public String nome;
    
    @Column(name="cpf",nullable = false, unique = true)
    @NotNull(message = "Campo obrigatório")
    public String cpf;
    
    @Column(name="email",nullable = false)
    @NotNull(message = "Campo obrigatório")
    public String eMail;
    
    @Column(name="telefone",nullable = false)
    @NotNull(message = "Campo obrigatório")
    public String telefone;
    
    @Column(name="num_cartao",nullable = false)
    @NotNull(message = "Campo obrigatório")
    public String numCartao;
    
    @Column(name="validade_cartao",nullable = false)
    @NotNull(message = "Campo obrigatório")
    public String validadeCartao;
    
    @Column(name="cvc_cartao",nullable = false)
    @NotNull(message = "Campo obrigatório")
    public String cvcCartao;
    
    @Column(name="nome_cartao",nullable = false)
    @NotNull(message = "Campo obrigatório")
    public String nomeCartao;
    


  


}