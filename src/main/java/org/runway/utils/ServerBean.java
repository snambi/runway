/*
 * Copyright 2002-2005 the original author or authors.
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


package org.runway.utils;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hsqldb.ServerConfiguration;
import org.hsqldb.ServerConstants;
import org.hsqldb.persist.HsqlProperties;

/**
 * Bean that will start an instance of an HSQL database.  This class is primarily intended
 * to be used in demo applications.  It allows for a self contained distribution including
 * a database instance.  The DataSource reference is necessary for proper shutdown.
 * <p/>
 * This is an example of a bean configuration:
 * <p/>
 * <pre>
 *     &lt;bean id="dataBase" class="org.springmodules.db.hsqldb.ServerBean" singleton="true" lazy-init="false"&gt;
 *         &lt;property name="dataSource"&gt;&lt;ref local="dataSource"/&gt;&lt;/property&gt;
 *         &lt;property name="serverProperties"&gt;
 *             &lt;props&gt;
 *                 &lt;prop key="server.port"&gt;9101&lt;/prop&gt;
 *                 &lt;prop key="server.database.0"&gt;webapps/myapp/db/test&lt;/prop&gt;
 *                 &lt;prop key="server.dbname.0"&gt;test&lt;/prop&gt;
 *             &lt;/props&gt;
 *         &lt;/property&gt;
 *     &lt;/bean&gt;
 * </pre>
 *
 * @author Thomas Risberg
 * @see org.hsqldb.Server
 */

public class ServerBean {

    /**
     * Commons Logging instance.
     */
    private static final Log log = LogFactory.getLog(ServerBean.class);
    private static final Logger logger = Logger.getLogger(ServerBean.class.getName());

    /**
     * Properties used to customize instance.
     */
    private Properties serverProperties;

    /**
     * The actual server instance.
     */
    private org.hsqldb.Server server;

    /**
     * DataSource used for shutdown.
     */
    private DataSource dataSource;

    public Properties getServerProperties() {
        return serverProperties;
    }

    public void setServerProperties(Properties serverProperties) {
        this.serverProperties = serverProperties;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void initialize() throws Exception {

        HsqlProperties configProps = new HsqlProperties(serverProperties);
//        if (configProps == null) {
//            configProps = new HsqlProperties();
//        }

        ServerConfiguration.translateDefaultDatabaseProperty(configProps);

        // finished setting up properties - set some important behaviors as well;
        server = new org.hsqldb.Server();
        server.setRestartOnShutdown(false);
        server.setNoSystemExit(true);
        server.setProperties(configProps);

        String dbPath = getDatabasePath(server.getDatabasePath(0, true));
        //server.setDatabasePath(0, "emarket.db");
        server.setDatabasePath(0, dbPath);


        logger.info("HSQL Server Startup sequence initiated");

        server.start();

        String portMsg = "port " + server.getPort();
        logger.info("HSQL Server listening on " + portMsg);
    }

    public void shutdown() {

        logger.info("HSQL Server Shutdown sequence initiated");
        if (dataSource != null) {
            Connection con = null;
            try {
                con = dataSource.getConnection();
                con.createStatement().execute("SHUTDOWN");
            } catch (SQLException e) {
                logger.severe("HSQL Server Shutdown failed: " + e.getMessage());
            } finally {
                try {
                    if (con != null)
                        con.close();
                } catch (Exception ignore) {
                }
            }
        } else {
            logger.fine("HSQL ServerBean needs a dataSource property set to shutdown database safely.");
        }

        server.signalCloseAllServerConnections();
        int status = server.stop();
        long timeout = System.currentTimeMillis() + 5000;
        while (status != ServerConstants.SERVER_STATE_SHUTDOWN && System.currentTimeMillis() < timeout) {
            try {
                Thread.sleep(100);
                status = server.getState();
            } catch (InterruptedException e) {
                log.error("Error while shutting down HSQL Server: " + e.getMessage());
                break;
            }
        }

        if (status != ServerConstants.SERVER_STATE_SHUTDOWN) {
            logger.info("HSQL Server failed to shutdown properly.");
        } else {
            logger.info("HSQL Server Shutdown completed");
        }
        server = null;

    }

    private String getDatabasePath(String dbname) {

        String dbpath = null;

        String dbscr = dbname + ".script";
        dbname = dbname + ".properties";

        // find the location of this file from classpath
        //ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader sysClassLoader = ServerBean.class.getClassLoader();
        URL abspath = sysClassLoader.getResource(dbname);
        try {
            Enumeration<URL> scr = sysClassLoader.getResources(dbscr);

            for (; scr.hasMoreElements();) {
                URL u = scr.nextElement();
                System.out.println("uu " + u);
            }
        } catch (IOException e) {
            System.out.println("unable to find " + dbscr);
            e.printStackTrace();
        }


        //URL dd = getClass().getResource("target/classes/emarket.db.properties");

        if (abspath != null) {
            dbpath = abspath.toString();

            // then remove the .properties at the end
            if (dbpath.endsWith(".properties")) {
                dbpath = dbpath.replaceFirst(".properties", "");
            }
        } else {
            System.out.println("unable to find " + dbname);
            //throw new NullPointerException();
        }

        System.out.println("database : "+ dbpath);


        return dbpath;
    }

    public static void printClasspath() {

        //Get the System Classloader
        ClassLoader classloader = ServerBean.class.getClassLoader();

        //Get the URLs
        URL[] urls = ((URLClassLoader) classloader).getURLs();

        for (int i = 0; i < urls.length; i++) {
            System.out.println("classpath." + i + " :" + urls[i].getFile());
        }
    }


    public static void main(String[] args) {

        //String propFileName = args[0];
        Properties props = new Properties();

        props.put("server.port", "9101");
        props.put("server.database.0", "emarket.db");
        props.put("server.dbname.0", "shark");


        BasicDataSource datasource = new BasicDataSource();

        datasource.setDriverClassName("org.hsqldb.jdbcDriver");
        datasource.setUrl("jdbc:hsqldb:hsql://localhost:9101/shark");
        datasource.setUsername("sa");
        datasource.setPassword("");

        ServerBean server = new ServerBean();

        server.setServerProperties(props);
        server.setDataSource(datasource);

        try {
            server.initialize();
        } catch (Exception e) {
            e.printStackTrace();  
        }

    }

}
