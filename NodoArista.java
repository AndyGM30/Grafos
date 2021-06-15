/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andet
 */
public class NodoArista {
    NodoVertice direccion;
    NodoArista abajo;
    NodoArista arriba;
    
    public NodoArista(NodoVertice d){
        direccion = d;
        abajo = arriba = null;
    }
}
