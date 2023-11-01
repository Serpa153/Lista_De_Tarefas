package lista.de.tarefas.api.models;

public class Tarefa {
    private int id;
    private String descricao;
    private boolean finalizado;

    public Tarefa(int id, String descricao, boolean finalizado) {
        this.id = id;
        this.descricao = descricao;
        this.finalizado = finalizado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
}
