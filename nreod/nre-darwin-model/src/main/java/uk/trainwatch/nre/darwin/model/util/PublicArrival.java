/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.trainwatch.nre.darwin.model.util;

/**
 * Object contains a public arrival time.
 * <p>
 * @author peter
 */
public interface PublicArrival
        extends WorkArrival
{

    String getPta();
}
