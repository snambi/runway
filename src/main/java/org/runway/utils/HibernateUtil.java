/*
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  copyright (C) 2012 nambi sankaran.
 */
package org.runway.utils;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * HibernateUtil Description.
 *
 * @author : snambi
 */
public class HibernateUtil {



    public static void initHsql(){
        initHsql("sample");
    }

    public static void initHsql( String persistenceUnitName ) {

        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            //Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:sample");            
            DriverManager.getConnection("jdbc:hsqldb:mem:sample");
        } catch (InstantiationException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public static void initPsql(){
        initPsql("otp");
    }

    public static void initPsql(String persistenceUnitName ) {

        try {
            Class.forName("org.postgresql.Driver").newInstance();
            //c = DriverManager.getConnection("jdbc:postgresql://localhost/brokerdb", "otp", "otp123");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

/*
    public static EntityManager getEntityManager(){

        if( factory == null ){
            throw new NullPointerException("EntityManagerFactory not initialized");
        }

        return factory.createEntityManager();
    }
*/


}
