/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.trainwatch.web.performance;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import uk.trainwatch.nrod.rtppm.sql.OperatorManager;
import uk.trainwatch.nrod.rtppm.sql.PerformanceManager;

/**
 *
 * @author peter
 */
@Path("/rail/1/performance/rtppm")
public class RTPPMRest
{

    @Path("/operators")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response operators()
    {
        return Response.ok( OperatorManager.INSTANCE.getOperators() ).
                build();
    }

    @Path("/live/{operatorId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response live( @PathParam("operatorId") int id )
    {
        try {
            Optional ppm = PerformanceManager.INSTANCE.getOperatorPerformance( id, LocalDate.now() );
            if( ppm.isPresent() ) {
                return Response.ok( ppm.get() ).
                        build();
            }
            return Response.status( Response.Status.NOT_FOUND ).
                    build();
        }
        catch( SQLException ex ) {
            Logger.getLogger( RTPPMRest.class.getName() ).
                    log( Level.SEVERE, null, ex );
            return Response.serverError().
                    entity( ex ).
                    build();
        }
    }
}