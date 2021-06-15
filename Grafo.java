/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andet
 */
public class Grafo {
    NodoVertice vertice;
    
    public Grafo(){
        vertice = null;
    }
    
    public boolean insertarVertice(char dato){
        NodoVertice nuevo = new NodoVertice(dato);
        if(nuevo == null) return false;
        if(vertice == null){
            vertice = nuevo;
            return true;
        }
        //el nuevo se enlaza al final de la lista de vertices
        irUltimo();
        vertice.sig = nuevo;
        nuevo.ant = vertice;
        return true;
    }

    private void irUltimo() {
        while(vertice.sig != null){
            vertice = vertice.sig;
        }
    }
    
    private void irPrimero(){
     while(vertice.ant != null){
         vertice = vertice.ant;
        }
    }   
    
    private NodoVertice buscarVertice(char dato){
        if(vertice == null) return null;
        irPrimero();
        for(NodoVertice buscar = vertice; buscar!=null; buscar = buscar.sig){
            if(buscar.dato == dato){
                return buscar;
            }
        }
        return null;
    }
    
    public boolean insertarArista(char origen, char destino){
        NodoVertice nodoOrigen = buscarVertice(origen);
        NodoVertice nodoDestino = buscarVertice(destino);
        
        if(nodoOrigen == null || nodoDestino == null){
            return false;
        }
        return nodoOrigen.insertarArista(nodoDestino);
    }
    
    public boolean eliminarArista(char origen, char destino){
        NodoVertice nodoOrigen = buscarVertice(origen);
        NodoVertice nodoDestino = buscarVertice(destino);
        if(nodoOrigen == null || nodoDestino == null){
            return false;
        }
        return nodoOrigen.eliminarArista(nodoDestino);
    }
    
    public boolean unSoloVertice(){
        return vertice.ant == null && vertice.sig == null;
    }
    
    public boolean eliminarVertice(char dato){
        if(vertice == null) return false;
        NodoVertice temp = buscarVertice(dato);
        if(temp == null) return false; //No encontró nodo buscado
        //1.Que el vertice NO TENGA aristas a otros vertices.
        if(temp.arista != null) return false;
        //2.Que otros vertices NO TENGAN arista a este vertice a eliminar.
        quitarAristasDeOtrosVertices(temp);
        //EStá temp en el 1ro?
        if(temp == vertice){
            if(unSoloVertice()) vertice = null;
            else{
                vertice = temp.sig;
                temp.sig.ant = temp.sig = null;
                
            }
            return true;
        }
        //Está en el último?
        if(temp.sig == null){
            temp.ant.sig = temp.ant = null;
            return true;
        }
        //Temp está en medio
        temp.ant.sig = temp.sig;
        temp.sig.ant = temp.ant;
        temp.sig = temp.ant = null;
        return true;
    }

    private void quitarAristasDeOtrosVertices(NodoVertice NodoEliminar) {
        irPrimero();
        for(NodoVertice buscar = vertice; buscar != null; buscar = buscar.sig){
            buscar.eliminarArista(NodoEliminar);
        }
    }
    
    public int cuantosVertices(){
        if(vertice == null) return 0;
        int v = 0;
        irPrimero();
        NodoVertice aux = vertice;
        while(aux != null){
            v++;
            aux = aux.sig;
        }
        return v;
    }
    
    public boolean [][]matrizAd(){
        if(vertice == null) return null;
        int q = cuantosVertices(), c = 0, i = 0;
        boolean m[][] = new boolean[q][q];
        for(; i < q; i++){
            for(; c < q; c++){
                m[i][c] = false;
            }
        }
        for(; i < q; i++){
            c = 0;
            while(i != c){
                c++;
                vertice = vertice.sig;
            }
            NodoArista aux = vertice.arista;
            irPrimero();
            while(aux != null){
                c = 0;
                while(aux.direccion != vertice){
                    vertice = vertice.sig;
                    c++;
                }
                m[i][c] = true;
                aux = aux.abajo;
                irPrimero();
            }
        }
        return m;
    }
    
    public String listaAd(char dato){
        return buscarVertice(dato).toString();
    }
    
}
