import java.util.ArrayList;

public class Buffer {
    private ArrayList<Producto> buff ;
    private int n ;
    public Buffer (int n) {
        this.n = n ;
        buff = new ArrayList<Producto>();
    }

    public synchronized void almacenar (Producto i, String tipo) {
        while (buff.size() == n)
        {
            if (tipo.equals("Naranja")) {
                System.out.println("Buffer lleno, Producto Naranja " + i.getId() + " espera");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("Buffer lleno, Producto Azul " + i.getId() + " espera");
                try {
                    Thread.sleep(1000); // Espera 1 segundo antes de volver a intentar
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
        }
        buff.add(i);
        notify () ;
        }
    
        public synchronized Producto extraer (String tipo) {
            while (buff.size() == 0 || !buff.get(0).getTipo().equals(tipo))
            {
                System.out.println("VEN Y SANA MI DOLOR");
                if (tipo.equals("Naranja")) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        Thread.sleep(500); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Buffer no vacio, Producto " + tipo + " " + buff.get(buff.size() - 1).getId() + " extraido");
            Producto i = buff.remove(0);
            notify () ;
            return i ;
        }

        public synchronized Producto extraerFinal() {
            while (buff.size() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        
            Producto i = buff.remove(0);
            notify();
            return i;
        }
        
}
