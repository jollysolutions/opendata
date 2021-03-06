/*
 * Copyright 2014 Peter T Mount.
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

import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import uk.trainwatch.io.DatePathMapper;
import uk.trainwatch.io.FileRecorder;
import uk.trainwatch.rabbitmq.Rabbit;
import uk.trainwatch.rabbitmq.RabbitMQ;
import uk.trainwatch.util.Consumers;
import uk.trainwatch.util.counter.RateMonitor;

/**
 * Archives the raw RTPPM feed to disk
 * <p>
 * @author Peter T Mount
 */
@ApplicationScoped
public class RtppmArchiver
        implements Consumer<String>
{

    protected static final Logger LOG = Logger.getLogger( RtppmArchiver.class.getName() );
    private static final String ROUTING_KEY = "nr.rtppm";
    private static final String QUEUE_NAME = "archive." + ROUTING_KEY;
    @Inject
    private Rabbit rabbit;

    private Consumer<String> logger;
    private Consumer<String> monitor;

    public void start()
    {
        monitor = RateMonitor.log( LOG, QUEUE_NAME );

        Function<String, Path> rtppmMapper = new DatePathMapper( "/usr/local/networkrail", "rtppm/raw" );

        logger = Consumers.guard( LOG, FileRecorder.recordTo( rtppmMapper ) );

        rabbit.queueDurableConsumer( QUEUE_NAME, ROUTING_KEY, RabbitMQ.toString, this );
    }

    @Override
    public void accept( String t )
    {
        logger.accept( t );
        monitor.accept( t );
    }

}
