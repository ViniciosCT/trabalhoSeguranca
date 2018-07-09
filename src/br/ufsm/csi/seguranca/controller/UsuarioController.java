package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.net.ssl.SSLEngine;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static br.ufsm.csi.seguranca.Util.UtilVerifyRecaptcha.verify;


/**
 * Created by cpol on 29/05/2017.
 */
@Controller
public class UsuarioController {

    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("login.html")
    public String login(String login, String senha, HttpSession session, Model model, HttpServletRequest request) throws IOException, NoSuchAlgorithmException {

        Map<String, Object> map = new HashMap<>();
        map.put("login", login);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        map.put("senha", md.digest(senha.getBytes("ISO-8859-1")) );
        Collection funcionarios = hibernateDAO.listaObjetosEquals(Funcionario.class, map);

        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        boolean verify = verify(gRecaptchaResponse);

        if ( verify && !(funcionarios == null || funcionarios.isEmpty()) ) {
            session.invalidate();
            HttpSession nS = request.getSession();
            nS.setAttribute("funcionarioLogado", funcionarios.toArray()[0] );
            return "hello";
        } else {
            model.addAttribute("msgDoServidor", "acesso-negado");
            return "../../index";
        }
    }

    @Transactional
    @RequestMapping("paginaListaVeículos.html")
    public String paginaListaVeículos(Model model) {
        Map<String, String> map = new HashMap<>();
        map.put("nome", "");
        Collection funcionarios = hibernateDAO.listaObjetos(Funcionario.class, map, null, null, false);
        model.addAttribute("funcionarios", funcionarios);
        return "listar";
    }

    @Transactional
    @RequestMapping("sair.priv")
    public String paginaListaVeículos(HttpSession session) {
        session.invalidate();
        return "../../index";
    }

}
