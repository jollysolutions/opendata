/*
 * Copyright 2015 peter.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.trainwatch.nrod.feed.rtppm;

import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import uk.trainwatch.nrod.rtppm.factory.RTPPMDataMsgFactory;
import uk.trainwatch.nrod.rtppm.model.RTPPMDataMsg;
import uk.trainwatch.rabbitmq.Rabbit;
import uk.trainwatch.rabbitmq.RabbitMQ;
import uk.trainwatch.util.JsonUtils;
import uk.trainwatch.util.counter.RateMonitor;

/**
 *
 * @author peter
 */
@ApplicationScoped
public class RtppmLogger
        implements Consumer<String>
{

    protected static final Logger LOG = Logger.getLogger( RtppmArchiver.class.getName() );
    private static final String ROUTING_KEY = "nr.rtppm";
    private static final String QUEUE_NAME = "db." + ROUTING_KEY;

    @Inject
    private RtppmReporter rtppmReporter;

    @Inject
    private Rabbit rabbit;

    private Consumer<String> monitor;

    public void start()
    {
        monitor = RateMonitor.log( LOG, QUEUE_NAME );

        rabbit.queueDurableConsumer( QUEUE_NAME, ROUTING_KEY, RabbitMQ.toString, this );
    }

    @Override
    public void accept( String t )
    {
        if( t != null ) {
            monitor.accept( t );

            // Store the ppm in the database
            JsonObject o = JsonUtils.parseJsonObject.apply( t );
            if( o != null ) {
                try {
                    RTPPMDataMsg msg = RTPPMDataMsgFactory.INSTANCE.apply( o );
                    rtppmReporter.accept( msg );
                }
                catch( SQLException ex ) {
                    LOG.log( Level.SEVERE, null, ex );
                }
            }
        }
    }

}
