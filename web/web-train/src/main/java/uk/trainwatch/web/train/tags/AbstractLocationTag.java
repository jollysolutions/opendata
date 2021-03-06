/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.trainwatch.web.train.tags;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import uk.trainwatch.nrod.location.TrainLocation;
import uk.trainwatch.nrod.location.TrainLocationFactory;
import uk.trainwatch.util.CDIUtils;

/**
 *
 * @author Peter T Mount
 */
public abstract class AbstractLocationTag
        extends BodyTagSupport
{

    private static final String DEFAULT_PREFIX = "/station/";
    private String value;
    private boolean link = true;
    private String prefix = DEFAULT_PREFIX;
    private boolean nowrap = false;

    @SuppressWarnings("LeakingThisInConstructor")
    public AbstractLocationTag()
    {
        CDIUtils.inject( this );
    }

    @Override
    public void release()
    {
        value = null;
        link = true;
        prefix = DEFAULT_PREFIX;
        nowrap = false;
    }

    @Inject
    protected TrainLocationFactory trainLocationFactory;

    protected abstract TrainLocation getLocationRef( String value );

    @Override
    public int doStartTag()
            throws JspException
    {
        if( value != null ) {

            String name = null;

            TrainLocation ref = getLocationRef( value );
            if( ref != null && ref.isSetLocation() ) {
                name = ref.getLocation();
            }

            // Try our own, has depot's etc in there
            if( name == null || name.equals( value ) ) {
                TrainLocation loc = trainLocationFactory.resolveTrainLocation( value );
                if( loc != null ) {
                    name = loc.getLocation();
                }
            }

            if( name == null ) {
                name = value;
            }

            if( nowrap ) {
                name = name.replace( " ", "&nbsp;" );
            }

            try {
                JspWriter w = pageContext.getOut();

                if( link && ref != null && ref.isSetCrs() ) {
                    w.write( "<a href=\"" );
                    w.write( prefix );
                    w.write( ref.getCrs() );
                    w.write( "\">" );
                }
                w.write( name );
                if( link && ref != null && ref.isSetCrs() ) {
                    w.write( "</a>" );
                }
            }
            catch( IOException ex ) {
                throw new JspException( ex );
            }
        }

        return SKIP_BODY;
    }

    public void setValue( String value )
    {
        this.value = value;
    }

    public void setLink( boolean link )
    {
        this.link = link;
    }

    public void setPrefix( String prefix )
    {
        this.prefix = prefix;
    }

    public void setNowrap( boolean nowrap )
    {
        this.nowrap = nowrap;
    }

}
