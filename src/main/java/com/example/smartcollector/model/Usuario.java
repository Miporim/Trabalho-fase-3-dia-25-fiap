package com.example.smartcollector.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @Column(nullable = false, length = 100)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private UsuarioRole funcao;

    public Usuario() {}

    public Usuario(String nome, String email, String senha, UsuarioRole funcao) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.funcao = funcao;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.funcao == UsuarioRole.ADMIN)
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() { return senha; }

    @Override
    public String getUsername() { return email; }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public UsuarioRole getFuncao() { return funcao; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setFuncao(UsuarioRole funcao) { this.funcao = funcao; }
}