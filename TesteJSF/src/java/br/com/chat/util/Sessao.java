/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chat.util;

import javax.faces.context.FacesContext;

/**
 *
 * @author Gabriel
 */
public class Sessao {
    
    public static Object getSessao (String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(key);
    }
    public static void setSessao (String key, Object value) {
        FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put(key, value);
    }
    
}
