/*
 * Copyright 2014 peter.
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
package uk.trainwatch.util.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author peter
 * @param <T>
 */
@FunctionalInterface
public interface SQLResultSetHandler<T>
        extends SQLFunction<ResultSet, T>
{

    default ResultSetMetaData getResultSetMetaData( ResultSet rs )
            throws SQLException
    {
        return rs.getMetaData();
    }

    default void forEachColumn( ResultSet rs, SQLBiConsumer<String, Object> c )
            throws SQLException
    {
        ResultSetMetaData meta = getResultSetMetaData( rs );
        int s = meta.getColumnCount();
        for( int i = 1; i <= s; i++ )
        {
            c.accept( meta.getColumnName( i ), rs.getObject( i ) );
        }
    }
}
