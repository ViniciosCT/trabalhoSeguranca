package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.Util.UtilTokenGenerator;
import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.dao.HibernateDAODecorator;
import br.ufsm.csi.seguranca.model.Cliente;
import br.ufsm.csi.seguranca.model.Log;
import br.ufsm.csi.seguranca.model.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static br.ufsm.csi.seguranca.Util.UtilCliente.addNovaListaDeVeiculos;
import static br.ufsm.csi.seguranca.Util.UtilCliente.removeRelacoes;
import static br.ufsm.csi.seguranca.Util.UtilLog.gerarLog;

@Controller
public class ClienteController {

    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("gerenciarClientes.priv")
    public String gerenciarClientes(Model model, HttpSession session) {
        Map<String, String> map = new HashMap<>();
        map.put("nome", "");
        Collection<Cliente> clientes = (Collection<Cliente>) hibernateDAO.listaObjetos(Cliente.class, map, null, null, false);
        for(Cliente cliente : clientes){
            gerarLog(Cliente.class, cliente.getId(), Log.Tipo.Read, hibernateDAO, session);
        }
        model.addAttribute("clientes", clientes);
        return "listaClientes";
    }

    @Transactional
    @RequestMapping("paginaCriaCliente.priv")
    public String paginaCriaCliente(Model model) {
        Map<String, String> map = new HashMap<>();
        map.put("placa", "");
        Collection veiculos = hibernateDAO.listaObjetos(Veiculo.class, map, null, null, false);
        Collection<Veiculo> veiculosNaoSelecionados = new ArrayList<>();
        for(Object veicO : veiculos){
            Veiculo veiculo = (Veiculo) veicO;
            if( veiculo.getCliente() == null ){
                veiculosNaoSelecionados.add(veiculo);
            }
        }
        model.addAttribute("veiculos", veiculosNaoSelecionados);
        return "criaCliente";
    }

    @Transactional
    @RequestMapping(value = "paginaEditaCliente.priv", method = RequestMethod.GET)
    public String paginaEditaCliente(Model model, Long id, HttpSession session) {
        Cliente cliente = (Cliente) hibernateDAO.carregaObjeto(Cliente.class, id);

        Map<String, String> map = new HashMap<>();
        map.put("placa", "");
        Collection veiculos = hibernateDAO.listaObjetos(Veiculo.class, map, null, null, false);
        Collection<Veiculo> veiculosNaoSelecionados = new ArrayList<>();
        for(Object veicO : veiculos){
            Veiculo veiculo = (Veiculo) veicO;
            if( veiculo.getCliente() == null ){
                veiculosNaoSelecionados.add(veiculo);
            }
        }

        UtilTokenGenerator tokenGenerator = new UtilTokenGenerator();
        String token = tokenGenerator.generateToken("AlteraCliente");
        session.setAttribute("tokenAlteraCliente", token);

        model.addAttribute("cliente", cliente);
        model.addAttribute("veiculos", veiculosNaoSelecionados);
        model.addAttribute("veiculosChecked", cliente.getVeiculos());

        return "editaCliente";
    }

    @Transactional
    @RequestMapping("criaCliente.priv")
    public String criaCliente(Cliente cliente, Long[] idsVeiculos, String token, HttpSession session) {
        String tokenSession = (String) session.getAttribute("tokenAlteraCliente");
        if( cliente.getId() != null && (token != null && tokenSession.equals(token)) ) {
            removeRelacoes(cliente, hibernateDAO);
            addNovaListaDeVeiculos(idsVeiculos, cliente, hibernateDAO);
        }
        if(cliente.getId() == null) {
            hibernateDAO.criaObjeto(cliente);
            gerarLog(Cliente.class, cliente.getId(), Log.Tipo.Create, hibernateDAO, session);
        }else{
            if( token != null && tokenSession.equals(token) ) {
                Cliente clienteBanco = (Cliente) hibernateDAO.carregaObjeto(Cliente.class, cliente.getId());
                clienteBanco.setNome(cliente.getNome());
                clienteBanco.setVeiculos(cliente.getVeiculos());
                gerarLog(Cliente.class, cliente.getId(), Log.Tipo.Update, hibernateDAO, session);
            }
        }
        return "redirect:gerenciarClientes.priv";
    }

    @Transactional
    @RequestMapping(value = "removeCliente.priv", method = RequestMethod.GET)
    public String removeCliente(Long id, HttpSession session){
        Cliente cliente = (Cliente) hibernateDAO.carregaObjeto(Cliente.class, id);
        //Desfaz vinculo para não remover o video vinculado junto
        for(Veiculo veiculo : cliente.getVeiculos()){
            veiculo.setCliente(null);
        }
        cliente.setVeiculos(null);
        gerarLog(Cliente.class, cliente.getId(), Log.Tipo.Delete, hibernateDAO, session);
        hibernateDAO.removeObjeto(cliente);
        return "redirect:gerenciarClientes.priv";
    }

}
