/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Sony
 */
@Named(value = "login")
@SessionScoped
public class login implements Serializable {

    private String usuario;
    private String contrasena;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public String iniciarSesion(){
        if(usuario.equals("admin") && contrasena.equals("clave1234")){
            return "inicio";    
        }else{
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario y/o contraseña inválidos", "MSG_ERROR");
            contexto.addMessage(null, fm);
                return null;
        }
    }

    public login() {
    }
    
}
