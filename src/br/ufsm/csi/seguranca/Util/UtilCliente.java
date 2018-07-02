package br.ufsm.csi.seguranca.Util;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Cliente;
import br.ufsm.csi.seguranca.model.Veiculo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UtilCliente {

    public static void removeRelacoes(Cliente cliente, HibernateDAO hibernateDAO){
        Map<String, String> map = new HashMap<>();
        Collection<Veiculo> todosVeiculos = (Collection<Veiculo>) hibernateDAO.listaObjetos(Veiculo.class, map, null, null, false);
        for(Veiculo veiculo : todosVeiculos){
            if( veiculo.getCliente() != null && veiculo.getCliente().getId().equals( cliente.getId() ) ){
                veiculo.setCliente(null);
            }
        }
    }

    public static void addNovaListaDeVeiculos(Long[] idsVeiculos, Cliente cliente, HibernateDAO hibernateDAO){
        if (idsVeiculos != null) {
            Collection<Veiculo> veiculos = new ArrayList<>();
            for (Long idVeiculo : idsVeiculos) {
                Veiculo veiculo = (Veiculo) hibernateDAO.carregaObjeto(Veiculo.class, idVeiculo);
                veiculo.setCliente(cliente);//veiculo tem que guardar o cliente!
                veiculos.add(veiculo);
            }
            cliente.setVeiculos(veiculos);
        }
    }

}
