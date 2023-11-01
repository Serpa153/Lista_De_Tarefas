package lista.de.tarefas.api.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lista.de.tarefas.api.models.Tarefa;

public class TarefasDAO {
    private static String listarTarefasQuery = "SELECT Id, Descricao, Finalizado FROM tarefas";
    private static String adicionarTarefaQuery = "INSERT INTO tarefas (Descricao) VALUES (?)";
    private static String atualizarTarefaQuery = "UPDATE tarefas SET Descricao = ?, Finalizado = ? WHERE Id = ?";
    private static String deletarTarefaQuery = "DELETE from tarefas WHERE Id = ?";

    public List<Tarefa> listarTarefas() throws RuntimeException {

        try {
            var dao = new DAO();
            var conexao = dao.conectar();

            PreparedStatement preparedStatement = conexao.prepareStatement(listarTarefasQuery);
            ResultSet result = preparedStatement.executeQuery();

            List<Tarefa> tarefas = new ArrayList<Tarefa>();

            while (result.next()) {
                int id = result.getInt(1);
                String descricao = result.getString(2);
                boolean finalizado = result.getBoolean(3);

                tarefas.add(new Tarefa(id, descricao, finalizado));
            }

            dao.desconectar(conexao);

            return tarefas;

        } catch (Exception ex) {
            throw new RuntimeException("Falha ao obter tarefas", ex);
        }
    }

    public void adicionarTarefa(String descricao) throws RuntimeException {
        if(descricao.isEmpty()) {
            throw new RuntimeException("O campo descricao nao pode ser vazio");
        }

        try {
            var dao = new DAO();
            var conexao = dao.conectar();

            PreparedStatement preparedStatement = conexao.prepareStatement(adicionarTarefaQuery);
            preparedStatement.setString(1, descricao);

            preparedStatement.executeUpdate();
            dao.desconectar(conexao);

        } catch (Exception ex) {
            throw new RuntimeException("Falha ao adicionar tarefas", ex);
        }
    }

    public void atualizarTarefa(Tarefa tarefa) throws RuntimeException {
        if(tarefa.getDescricao().isEmpty()) {
             throw new RuntimeException("O campo descricao nao pode ser vazio");
        }

        if(tarefa.getId() <= 0) {
             throw new RuntimeException("O id esta invalido");
        }


        try {
            var dao = new DAO();
            var conexao = dao.conectar();

            PreparedStatement preparedStatement = conexao.prepareStatement(atualizarTarefaQuery);
            preparedStatement.setString(1, tarefa.getDescricao());
            preparedStatement.setBoolean(2, tarefa.isFinalizado());
            preparedStatement.setInt(3, tarefa.getId());

            preparedStatement.executeUpdate();
            dao.desconectar(conexao);

        } catch (Exception ex) {
            throw new RuntimeException("Falha ao atualizar tarefa", ex);
        }
    }

    public void deletarTarefa(int id) {
        try {
            var dao = new DAO();
            var conexao = dao.conectar();

            PreparedStatement preparedStatement = conexao.prepareStatement(deletarTarefaQuery);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            dao.desconectar(conexao);

        } catch (Exception ex) {
            throw new RuntimeException("Falha ao deletar tarefa", ex);
        }
    }

}
