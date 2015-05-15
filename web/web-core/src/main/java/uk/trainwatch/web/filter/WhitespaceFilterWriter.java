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
package uk.trainwatch.web.filter;

import java.io.IOException;
import java.io.Writer;

/**
 * {@link Writer} which strips whitespace from a page.
 * <p>
 * Note: This can add some whitespace between elements however that is preferable as it's not easily possible to identify which
 * elements require that whitespace - e.g. a link needs it if it's mid sentence otherwise words will appear joined together on
 * the page.
 * <p>
 * @author Peter T Mount
 */
public class WhitespaceFilterWriter
        extends Writer
{

    private final Writer out;
    private State state = State.OFF;

    public WhitespaceFilterWriter( Writer out )
    {
        this.out = out;
    }

    @Override
    public void write( char[] cbuf, int off, int len )
            throws IOException
    {
        for( int i = off, j = 0; j < len; i++, j++ )
        {
            state = state.filter( out, cbuf[i] );
        }
    }

    @Override
    public void flush()
            throws IOException
    {
        state.finish( out );
        state = State.OFF;
        out.flush();
    }

    @Override
    public void close()
            throws IOException
    {
        state.finish( out );
        out.close();
    }

    private static enum State
    {

        OFF
                {
                    @Override
                    public State filter( Writer out, char c )
                    throws IOException
                    {
                        out.write( c );

                        if( c == '<' )
                        {
                            return START_TAG;
                        }

                        return this;
                    }

                },
        START_TAG
                {
                    @Override
                    public State filter( Writer out, char c )
                    throws IOException
                    {
                        out.write( c );

                        if( c == '>' )
                        {
                            return TRIM;
                        } else if( c == 'p' )
                        {
                            return START_PRE1;
                        }

                        return this;
                    }
                },
        START_PRE1
                {
                    @Override
                    public State filter( Writer out, char c )
                    throws IOException
                    {
                        out.write( c );

                        if( c == '>' )
                        {
                            return TRIM;
                        } else if( c == 'r' )
                        {
                            return START_PRE2;
                        }

                        return START_TAG;
                    }
                },
        START_PRE2
                {
                    @Override
                    public State filter( Writer out, char c )
                    throws IOException
                    {
                        out.write( c );

                        if( c == '>' )
                        {
                            return TRIM;
                        } else if( c == 'e' )
                        {
                            return START_PRE3;
                        }

                        return START_TAG;
                    }
                },
        START_PRE3
                {
                    @Override
                    public State filter( Writer out, char c )
                    throws IOException
                    {
                        out.write( c );

                        if( c == '>' )
                        {
                            return SKIP_PRE;
                        }
                        return START_TAG;
                    }
                },
        TRIM
                {
                    @Override
                    public State filter( Writer out, char c )
                    throws IOException
                    {
                        if( c == ' ' || c == '\t' || c == '\r' || c == '\n' )
                        {
                            return SKIP;
                        }

                        out.write( c );

                        if( c == '<' )
                        {
                            return START_TAG;
                        }

                        return this;
                    }

                },
        SKIP_PRE
                {
                    @Override
                    public State filter( Writer out, char c )
                    throws IOException
                    {
                        out.write( c );

                        if( c == '<' )
                        {
                            return SKIP_PRE1;
                        }

                        return this;
                    }

                },
        SKIP_PRE1
                {
                    @Override
                    public State filter( Writer out, char c )
                    throws IOException
                    {
                        out.write( c );

                        if( c == '/' )
                        {
                            return SKIP_PRE2;
                        }

                        return SKIP_PRE;
                    }

                },
        SKIP_PRE2
                {
                    @Override
                    public State filter( Writer out, char c )
                    throws IOException
                    {
                        out.write( c );

                        if( c == 'p' )
                        {
                            return SKIP_PRE3;
                        }

                        return SKIP_PRE;
                    }

                },
        SKIP_PRE3
                {
                    @Override
                    public State filter( Writer out, char c )
                    throws IOException
                    {
                        out.write( c );

                        if( c == 'r' )
                        {
                            return SKIP_PRE4;
                        }

                        return SKIP_PRE;
                    }

                },
        SKIP_PRE4
                {
                    @Override
                    public State filter( Writer out, char c )
                    throws IOException
                    {
                        out.write( c );

                        if( c == 'e' )
                        {
                            return SKIP_PRE5;
                        }

                        return SKIP_PRE;
                    }

                },
        SKIP_PRE5
                {
                    @Override
                    public State filter( Writer out, char c )
                    throws IOException
                    {
                        out.write( c );

                        if( c == '>' )
                        {
                            return TRIM;
                        }

                        return SKIP_PRE;
                    }

                },
        SKIP
                {
                    @Override
                    public State filter( Writer out, char c )
                    throws IOException
                    {
                        if( c == ' ' || c == '\t' || c == '\r' || c == '\n' )
                        {
                            return this;
                        }

                        finish( out );
                        out.write( c );

                        if( c == '<' )
                        {
                            return START_TAG;
                        }

                        return TRIM;
                    }

                    @Override
                    public void finish( Writer out )
                    throws IOException
                    {
                        out.write( ' ' );
                    }
                };

        public abstract State filter( Writer out, char c )
                throws IOException;

        public void finish( Writer out )
                throws IOException
        {
        }
    }
}
