import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Consumidor extends Thread{

    static private int id = 0;
    static private Buffer bufferEntrada;
    static private Buffer bufferSalida;
    private String tipo;
    static private Consecutivo identificador;
    static private int cantidadProductos;
    static private CyclicBarrier barrera;

    public Consumidor(int id, String tipo, Consecutivo identificador, Buffer bufferEntrada, Buffer bufferSalida, int cantidadProductos, CyclicBarrier barrera) {
        this.id = id;
        this.tipo = tipo;
        this.identificador = identificador;
        this.bufferEntrada = bufferEntrada;
        this.bufferSalida = bufferSalida;
        this.cantidadProductos = cantidadProductos;
        this.barrera = barrera; 
    }

    public void etapaTransformacion()
    {
        System.out.println("Entra etapa 2 o 3" + "-" + "Consumidor " + id);
        System.out.println("Llegue 1");
        Producto producto = bufferEntrada.extraer(tipo);
        System.out.println("Extraje");
        //System.out.println("Consumidor " + id + " consume " + producto.getTipo() +  " " + producto.getId());
        try {
            Random random = new Random();
            Thread.sleep(random.nextInt(451) + 50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bufferSalida.almacenar(producto, producto.getTipo());
    }

    public void etapaFinal() throws InterruptedException, BrokenBarrierException
    {
        System.out.println("Entra etapa Final" + "-" + "Consumidor " + id);
        ArrayList<Producto> productos = new ArrayList<Producto>();
        while (productos.size() < cantidadProductos) {
            System.out.println("Llegue");
            Producto producto = bufferEntrada.extraer(tipo);
            System.out.println("Sali");
            //System.out.println("Consumidor " + id + " consume " + producto.getTipo() +  " " + producto.getId());
            productos.add(producto);
        }
        barrera.await();
        productos.sort((p1, p2) -> p1.getId() - p2.getId());
        for (Producto producto : productos) {
            System.out.println("Consumidor " + id + " consume " + producto.getTipo() +  " " + producto.getId());
        }
    }

    public void run()
    {
        etapaTransformacion();
    }

    
}
