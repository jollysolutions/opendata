/*
 * This is a fork of GHEAT_JAVA https://github.com/varunpant/GHEAT-JAVA
 * <p>
 * The MIT License
 * <p>
 * Copyright (c) 2014 Varun Pant
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * <p>
 */
package uk.trainwatch.gis.gheat;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Cache
{

    private static final Map<String, BufferedImage> _emptyTile = new HashMap<>();
    private static final Object syncroot = new Object();

    public boolean hasEmptyTile( int key, int zoomOpacity )
    {
        return _emptyTile.containsKey( getKey( key, zoomOpacity ) );
    }

    public BufferedImage getEmptyTile( int key, int zoomOpacity )
    {
        return _emptyTile.get( getKey( key, zoomOpacity ) );
    }

    public void putEmptyTile( BufferedImage tile, int key, int zoomOpacity )
    {
        synchronized( syncroot ) {
            _emptyTile.put( getKey( key, zoomOpacity ), tile );
        }
    }

    private String getKey( int key, int zoomOpacity )
    {
        return String.valueOf( key ) + "_" + String.valueOf( zoomOpacity );
    }
}
