
package br.com.waterbridge.modelo;

import java.util.List;

public class User {
    
    private Long idUser;
    private Perfil perfil;
    private Pass pass;
    private String nome;
    private String email;
    private String sexo;
    private String dtNasc;
    private String dtInsert;
    private String situacao;
    List<Permissao> listPermissao;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Pass getPass() {
        return pass;
    }

    public void setPass(Pass pass) {
        this.pass = pass;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(String dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getDtInsert() {
        return dtInsert;
    }

    public void setDtInsert(String dtInsert) {
        this.dtInsert = dtInsert;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }    

    public List<Permissao> getListPermissao() {
        return listPermissao;
    }

    public void setListPermissao(List<Permissao> listPermissao) {
        this.listPermissao = listPermissao;
    }
    
    public boolean temPermissao(String pCodigo){
        
        for(Permissao permisao: listPermissao){
            
            if(permisao.getCodigo().equals(pCodigo)){
                
                return true;
            }
        }
        return false;
    }
}
