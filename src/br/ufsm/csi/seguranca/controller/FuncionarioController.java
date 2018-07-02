package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Funcionario;
import br.ufsm.csi.seguranca.model.Log;
import br.ufsm.csi.seguranca.model.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static br.ufsm.csi.seguranca.Util.UtilLog.gerarLog;

@Controller
public class FuncionarioController {

    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("paginaCriaFuncionario.priv")
    public String paginaCriaFuncionario(Model model) {
        Map<String, String> map = new HashMap<>();
        map.put("placa", "");
        Collection veiculos = hibernateDAO.listaObjetos(Veiculo.class, map, null, null, false);
        model.addAttribute("veiculos", veiculos);
        return "criaFuncionario";
    }

    @Transactional
    @RequestMapping(value = "paginaEditaFuncionario.priv", method = RequestMethod.GET)
    public String paginaEditaFuncionario(Model model, Long id) {

        Funcionario funcionario = (Funcionario) hibernateDAO.carregaObjeto(Funcionario.class, id);
        Map<String, String> map = new HashMap<>();
        map.put("placa", "");
        Collection veiculos = hibernateDAO.listaObjetos(Veiculo.class, map, null, null, false);

        model.addAttribute("veiculos", veiculos);
        model.addAttribute("funcionario", funcionario);
        return "editaFuncionario";
    }

    @Transactional
    @RequestMapping("criaFuncionario.priv")
    public String criaFuncionario(Funcionario funcionario, String senhaStr, Long idVeiculo, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        funcionario.setSenha(md.digest(senhaStr.getBytes("ISO-8859-1")));
        Veiculo veiculo = (Veiculo) hibernateDAO.carregaObjeto(Veiculo.class, idVeiculo);
        funcionario.setVeiculo(veiculo);
        if(funcionario.getId() == null) {
            hibernateDAO.criaObjeto(funcionario);
            gerarLog(Funcionario.class, funcionario.getId(), Log.Tipo.Create, hibernateDAO, session);
        }else{
            Funcionario funcionarioBanco = (Funcionario) hibernateDAO.carregaObjeto(Funcionario.class, funcionario.getId());
            funcionarioBanco.setNome( funcionario.getNome() );
            funcionarioBanco.setVeiculo( funcionario.getVeiculo() );
            funcionarioBanco.setSenha( funcionario.getSenha() );
            funcionarioBanco.setLogin( funcionario.getLogin() );
            gerarLog(Funcionario.class, funcionario.getId(), Log.Tipo.Update, hibernateDAO, session);
        }
        return "redirect:gerenciarFuncionarios.priv";
    }

    @Transactional
    @RequestMapping("gerenciarFuncionarios.priv")
    public String gerenciarFuncionarios(Model model, HttpSession session){
        Map<String, String> map2 = new HashMap<>();
        map2.put("login", "");
        Collection<Funcionario> funcionarios = hibernateDAO.listaObjetos(Funcionario.class, map2, null, null, false);
        for(Funcionario funcionario : funcionarios){
            gerarLog(Funcionario.class, funcionario.getId(), Log.Tipo.Read, hibernateDAO, session);
        }
        model.addAttribute("funcionarios" , funcionarios);
        return "listaFuncionarios";
    }

    @Transactional
    @RequestMapping(value = "removeFuncionario.priv", method = RequestMethod.GET)
    public String removeFuncionario(Long id, HttpSession session){
        Funcionario funcionario = (Funcionario) hibernateDAO.carregaObjeto(Funcionario.class, id);
        gerarLog(Funcionario.class, funcionario.getId(), Log.Tipo.Delete, hibernateDAO, session);
        hibernateDAO.removeObjeto(funcionario);
        return "redirect:gerenciarFuncionarios.priv";
    }
}
