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
package uk.trainwatch.web.ldb;

import java.sql.SQLException;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import uk.trainwatch.nre.darwin.stationmsg.StationMessageManager;
import uk.trainwatch.util.sql.DBContextListener;

/**
 *
 * @author peter
 */
@WebListener
public class LDBContextListener
        extends DBContextListener
{

    private static DataSource dataSource;

    public static DataSource getDataSource()
    {
        return dataSource;
    }

    @Override
    protected void init( ServletContextEvent sce )
            throws SQLException
    {
        dataSource = getRailDataSource();

        StationMessageManager.INSTANCE.setDataSource( dataSource );
    }

}