package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Cliente;
import br.ufsm.csi.seguranca.model.Funcionario;
import br.ufsm.csi.seguranca.model.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.MessageDigest;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static br.ufsm.csi.seguranca.Util.UtilVeiculo.setFuncionarios;

@Controller
public class VeiculoController {

    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("gerenciarVeiculos.priv")
    public String gerenciarVeículos(Model model) {
        Map<String, String> map = new HashMap<>();
        map.put("placa", "");
        Collection veiculos = hibernateDAO.listaObjetos(Veiculo.class, map, null, null, false);
        model.addAttribute("veiculos", veiculos);
        return "listarVeiculos";
    }

    @Transactional
    @RequestMapping("paginaCriaVeiculo.priv")
    public String paginaCriaVeiculo(Model model) {
        Map<String, String> map = new HashMap<>();
        map.put("nome", "");
        Collection clientes = hibernateDAO.listaObjetos(Cliente.class, map, null, null, false);
        model.addAttribute("clientes", clientes);
        return "criaVeiculo";
    }

    @Transactional
    @RequestMapping("criaVeiculo.priv")
    public String criaVeiculo(Veiculo veiculo, Long idCliente) {
        if(idCliente != null) {
            Cliente cliente = (Cliente) hibernateDAO.carregaObjeto(Cliente.class, idCliente);
            veiculo.setCliente(cliente);
        }
        veiculo.setDataEntrada( new Date() );
        hibernateDAO.criaObjeto(veiculo);
        return "redirect:gerenciarVeiculos.priv";
    }

    @Transactional
    @RequestMapping(value = "removeVeiculo.priv", method = RequestMethod.GET)
    public String removeVeiculo(Long id){
        Veiculo veiculo = (Veiculo) hibernateDAO.carregaObjeto(Veiculo.class, id);
        setFuncionarios(veiculo, hibernateDAO);
        hibernateDAO.removeObjeto(veiculo);
        return "redirect:gerenciarVeiculos.priv";
    }

}
