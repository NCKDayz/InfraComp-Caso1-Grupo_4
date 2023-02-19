public class Producto {
    private String tipo;
    private Integer id;

    public Producto(String tipo, Integer id) {
        this.tipo = tipo;
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getId() {
        return id;
    }
}
