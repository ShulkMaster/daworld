/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.types.struchas;

/**
 *
 * @author yury_
 */

import factory.constrains.Structura;
import gameEntity.GameObject;

public class Extractor implements Structura{
    
    private GameObject recur;
    
    public Extractor(){}

    @Override
    public void setContenido(GameObject ctn) {
        recur = ctn;
    }

    @Override
    public GameObject getContent() {
        return recur;
    }
    
}
