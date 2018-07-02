package br.ufsm.csi.seguranca.dao;

import br.ufsm.csi.seguranca.model.exemplos.Usuario;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Map;

public interface HibernateDAOInterface {

    public void criaObjeto(Object o);

    public void removeObjeto(Object o);

    public Collection listaObjetos(Class classe,
                                   Map<String, String> likeMap,
                                   Integer maxResults,
                                   String propOrdem,
                                   boolean asc);

    public Collection<Object> listaObjetosEquals(Class classe, Map<String, Object> equalsMap);

    public Object carregaObjeto(Class classe, Serializable id);

    public Usuario findUsuario(String login, String senha)throws NoSuchAlgorithmException, UnsupportedEncodingException;

    public Usuario findUsuarioHQL(String login, String senha)throws NoSuchAlgorithmException, UnsupportedEncodingException;
}
