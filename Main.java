import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {
    
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        System.out.println("Inicio");
        int cantidadProcesos = 2;
        int cantidadProductosNaranjas = 1;
        int cantidadProductosAzules = 1;
        int capacidadBuffer = 1;

        Consecutivo identificador = new Consecutivo();

        Buffer buffer1 = new Buffer(capacidadBuffer);
        Buffer buffer2 = new Buffer(capacidadBuffer);
        Buffer buffer3 = new Buffer(200);

        CyclicBarrier barrera = new CyclicBarrier ( cantidadProductosNaranjas + cantidadProductosAzules) ;


        Productor productorNaranja = new Productor(0, "Naranja", identificador, buffer1, cantidadProductosNaranjas);
        Productor productor = new Productor(1, "Azul", identificador, buffer1, cantidadProductosAzules);

        Consumidor consumidorNaranja = new Consumidor(0, "Naranja", identificador, buffer1, buffer2, cantidadProductosNaranjas, barrera);
        Consumidor consumidorNaranja2 = new Consumidor(0, "Naranja", identificador, buffer2, buffer3, cantidadProductosNaranjas, barrera);
        //Consumidor consumidorNaranjaFinal = new Consumidor(0, "Naranja", identificador, buffer3, null, cantidadProductosNaranjas, barrera);

        for (int i = 1; i < cantidadProcesos; i++) {
            Consumidor consumidor = new Consumidor(i, "Azul", identificador, buffer1, buffer2, cantidadProductosAzules, barrera);
            Consumidor consumidor2 = new Consumidor(i, "Azul", identificador, buffer2, buffer3, cantidadProductosAzules, barrera);
            //Consumidor consumidorFinal = new Consumidor(i, "Azul", identificador, buffer3, null, cantidadProductosAzules, barrera);
            consumidor.start();
            consumidor2.start();
            //consumidorFinal.start();
        }

        productor.start();
        productorNaranja.start();
        consumidorNaranja.start();
        consumidorNaranja2.start();
        //consumidorNaranjaFinal.start();

        barrera.await();
        
    }
    
}
