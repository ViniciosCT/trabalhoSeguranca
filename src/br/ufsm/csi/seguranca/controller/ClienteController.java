package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Cliente;
import br.ufsm.csi.seguranca.model.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ClienteController {

    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("gerenciarClientes.priv")
    public String gerenciarClientes(Model model) {
        Map<String, String> map = new HashMap<>();
        map.put("nome", "");
        Collection clientes = hibernateDAO.listaObjetos(Cliente.class, map, null, null, false);
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
    @RequestMapping("criaCliente.priv")
    public String criaCliente(Cliente cliente, Long[] idsVeiculos) {
        hibernateDAO.criaObjeto(cliente);
        //olhar funcao no sistema de videos
        if(idsVeiculos!=null) {
            Collection<Veiculo> veiculos = new ArrayList<>();
            for (Long idVeiculo : idsVeiculos) {
                Veiculo veiculo = (Veiculo) hibernateDAO.carregaObjeto(Veiculo.class, idVeiculo);
                veiculo.setCliente(cliente);//veiculo tem que guardar o cliente!
                veiculos.add(veiculo);
            }
            cliente.setVeiculos(veiculos);
        }
        return "redirect:gerenciarClientes.priv";
    }

    @Transactional
    @RequestMapping(value = "removeCliente.priv", method = RequestMethod.GET)
    public String removeCliente(Long id){
        Cliente cliente = (Cliente) hibernateDAO.carregaObjeto(Cliente.class, id);
        //Desfaz vinculo para não remover o video vinculado junto
        for(Veiculo veiculo : cliente.getVeiculos()){
            veiculo.setCliente(null);
        }
        cliente.setVeiculos(null);
        hibernateDAO.removeObjeto(cliente);
        return "redirect:gerenciarClientes.priv";
    }

}
