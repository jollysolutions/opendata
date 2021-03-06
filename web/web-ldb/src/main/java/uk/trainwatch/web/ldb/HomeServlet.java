/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.trainwatch.web.ldb;

import uk.trainwatch.web.servlet.AbstractServlet;
import uk.trainwatch.web.servlet.ApplicationRequest;
import java.io.IOException;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import uk.trainwatch.nrod.location.TrainLocationFactory;

/**
 * Performance Home
 * <p>
 * @author Peter T Mount
 */
@WebServlet(name = "LDBHome", urlPatterns = {"/ldb", "/ldb/"})
public class HomeServlet
        extends AbstractServlet
{

    @Inject
    protected TrainLocationFactory trainLocationFactory;
    
    @Override
    protected void doGet( ApplicationRequest request )
            throws ServletException,
                   IOException
    {
        String index = request.getParam().
                getOrDefault( "s", "A" ).
                substring( 0, 1 ).
                toUpperCase();

        Map<String, Object> req = request.getRequestScope();
        req.put( "selected", index );
        req.put( "index", trainLocationFactory.getStationIndex() );
        req.put( "stations", trainLocationFactory.getStationIndex( index ) );

        request.renderTile( "ldb.home" );
    }

}
