package br.ufsm.csi.seguranca.Util;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Funcionario;
import br.ufsm.csi.seguranca.model.Veiculo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UtilVeiculo {

    public static void setFuncionarios(Veiculo veiculo, HibernateDAO hibernateDAO){
        Map<String, Object> map = new HashMap<>();
        map.put("veiculo", veiculo);
        Collection funcionarios = hibernateDAO.listaObjetosEquals(Funcionario.class, map);

        for(Object funcO : funcionarios){
            Funcionario funcionario = (Funcionario) funcO;
            funcionario.setVeiculo(null);
        }
    }

}
