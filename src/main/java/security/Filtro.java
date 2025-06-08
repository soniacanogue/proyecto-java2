/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Sony
 */
public class Filtro implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
 
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
    //CREAR OBJETOS 
        HttpServletRequest solicitud = (HttpServletRequest)  request;
        HttpServletResponse respuesta = (HttpServletResponse) response;
        
        respuesta.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");//como se tiene cache(LA FORMA)
        respuesta.setHeader("Pragma", "no-cache");
        respuesta.setDateHeader("Expires", 0);//TIEMPO EN EL QUE MANTIENE CACHE
        //Valores para validaciones
        HttpSession sesion = solicitud.getSession(); //VALIDA -> SESSION = ACTIVA 
        String rutaSolicitud = solicitud.getRequestURI();
        String raiz = solicitud.getContextPath();
        
        //validaciones
        //1.Validar el estado de la session
        boolean validarSesion = ((sesion!=null) && (sesion.getAttribute("usuario")!=null) );
        //2.validacion login (debe venir de login)
        boolean validarRutaLogin = (rutaSolicitud.equals(raiz + "/") || (rutaSolicitud.equals(raiz + "/login.xhtml"))); //opcion1   or(||)  option2
        //3.cargar contenido estatico
        boolean validarRecurso = rutaSolicitud.contains("/resources");
        
        if (validarSesion || validarRutaLogin || validarRecurso ){
            chain.doFilter(request, response);
        }else{
        respuesta.sendRedirect(raiz);
        }
    }

    @Override
    public void destroy() {
       
    }
    
}
