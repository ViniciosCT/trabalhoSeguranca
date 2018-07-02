package br.ufsm.csi.seguranca.dao;

import br.ufsm.csi.seguranca.model.Log;
import br.ufsm.csi.seguranca.model.exemplos.Usuario;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class HibernateDAODecorator implements HibernateDAOInterface {

    @Autowired
    private HibernateDAO hibernateDAO = new HibernateDAO();

    @Override
    public void criaObjeto(Object o) {
        hibernateDAO.criaObjeto(o);
    }

    @Override
    public void removeObjeto(Object o) {
        hibernateDAO.removeObjeto(o);
    }

    @Override
    public Collection listaObjetos(Class classe, Map<String, String> likeMap, Integer maxResults, String propOrdem, boolean asc) {
        return hibernateDAO.listaObjetos(classe, likeMap, maxResults, propOrdem, asc);
    }

    @Override
    public Collection<Object> listaObjetosEquals(Class classe, Map<String, Object> equalsMap) {
        return hibernateDAO.listaObjetosEquals(classe, equalsMap);
    }

    @Override
    public Object carregaObjeto(Class classe, Serializable id) {
        return hibernateDAO.carregaObjeto(classe, id);
    }

    @Override
    public Usuario findUsuario(String login, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return hibernateDAO.findUsuario(login, senha);
    }

    @Override
    public Usuario findUsuarioHQL(String login, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return hibernateDAO.findUsuarioHQL(login, senha);
    }
}
