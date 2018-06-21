package br.ufsm.csi.seguranca.Util;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Funcionario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UtilFuncionario {

    public static void transformaTabelaParaHash(HibernateDAO hibernateDAO) throws NoSuchAlgorithmException {
        Map<String, String> map2 = new HashMap<>();
        map2.put("login", "");
        Collection funcionarios = hibernateDAO.listaObjetos(Funcionario.class, map2, null, null, false);

        for(Object funO : funcionarios){
            Funcionario funcionario = (Funcionario) funO;
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            funcionario.setSenha( md.digest( funcionario.getSenha() ) );
        }
    }

}
