public class Productor extends Thread {
    private int id = 0;
    static private Buffer buffer;
    private String tipo;
    static private Consecutivo identificador;
    static private int cantidadProductos;

    public Productor(int id, String tipo, Consecutivo identificador, Buffer buffer, int cantidadProductos) {
        this.id = id;
        this.tipo = tipo;
        this.identificador = identificador;
        this.buffer = buffer;
        this.cantidadProductos = cantidadProductos;
    }

    private void etapa1(){
        System.out.println("Entra etapa 1" + "-" + "Productor " + id);
        for (int i = 0; i < cantidadProductos; i++) {
            Producto producto = new Producto(tipo, identificador.getNumero());
            identificador.addNumero();
            System.out.println("Productor " + id + " produce " + producto.getTipo() +  " " + producto.getId());
            buffer.almacenar(producto, producto.getTipo());
        }
    }

    public void run()
    {
        etapa1();
    }
}
