package br.ufsm.csi.seguranca.Util;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Funcionario;
import br.ufsm.csi.seguranca.model.Log;

import javax.servlet.http.HttpSession;
import java.util.Date;

public class UtilLog {

    public static void gerarLog(Class classe ,Long idObject, Log.Tipo tipo, HibernateDAO hibernateDAO, HttpSession session){
        Log log = new Log();
        hibernateDAO.criaObjeto(log);
        log.setClasse(classe);
        log.setIdObjeto(idObject);
        log.setDataHora(new Date());
        log.setTipo(tipo);
        Funcionario funcionario = (Funcionario) session.getAttribute("funcionarioLogado");
        Funcionario funcionarioBanco = (Funcionario) hibernateDAO.carregaObjeto(Funcionario.class, funcionario.getId());
        log.setFuncionario(funcionarioBanco);

        System.out.println(" Log gerado -> \t Classe: " + log.getClasse()
                + " \t ID: " + log.getIdObjeto()
                + " \t Data: " + log.getDataHora()
                + " \t Tipo: " + log.getTipo()
                + " \t Funcionario: "
                + log.getFuncionario().getNome());
    }

}
