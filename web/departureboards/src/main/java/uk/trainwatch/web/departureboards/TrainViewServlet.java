/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.trainwatch.web.departureboards;

import java.io.IOException;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import uk.trainwatch.web.ldb.LDBUtils;
import uk.trainwatch.web.ldb.model.Train;
import uk.trainwatch.web.servlet.AbstractServlet;
import uk.trainwatch.web.servlet.ApplicationRequest;

/**
 *
 * @author peter
 */
@WebServlet(name = "TrainViewServlet", urlPatterns = "/vtrain/*")
public class TrainViewServlet
        extends AbstractServlet
{

    @Override
    protected void doGet( ApplicationRequest request )
            throws ServletException,
                   IOException
    {
        String rid = request.getPathInfo().substring( 1 ).toUpperCase();
        log( "Retrieving train " + rid );

        try {
            Train train = LDBUtils.getTrain( rid );

            Map<String, Object> req = request.getRequestScope();
            req.put( "train", train );
            req.put( "pageTitle", rid );

            // Set headers for caching
            request.expiresIn( 1, ChronoUnit.MINUTES );
            request.lastModified( train.getLastUpdate() );
            request.maxAge( 1, ChronoUnit.MINUTES );

            request.renderTile( "ldb.train.view" );
        }
        catch( SQLException ex ) {
            log( "Failed rid " + rid, ex );
            request.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
        }
    }

}
