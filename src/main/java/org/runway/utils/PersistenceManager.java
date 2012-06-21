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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * PersistenceManager Description.
 *
 * @author : snambi
 */
public class PersistenceManager {

  private static final PersistenceManager persistenceManager = new PersistenceManager();
  protected EntityManagerFactory emf;
  //private static Logger logger = Logger.getLogger( PersistenceManager.class.getName() );
    

  public static PersistenceManager getInstance() {

    return persistenceManager;
  }

  private PersistenceManager() {
  }

    /**
     * init() method loads hibernate by identifying the database server and loading appropriate driver etc
     */
  public void init(){

  }

  public EntityManagerFactory getEntityManagerFactory() {

    if (emf == null){
      emf = createEntityManagerFactory();
    }
      
    return emf;
  }

  public void closeEntityManagerFactory() {

    if (emf != null) {
      emf.close();
      emf = null;
    }
  }

  public EntityManager getEntityManager(){
      return getEntityManagerFactory().createEntityManager();
  }

  protected EntityManagerFactory createEntityManagerFactory() {
    return createEntityManagerFactory("sample");
  }

  protected EntityManagerFactory createEntityManagerFactory(String name ){
    return Persistence.createEntityManagerFactory(name);
  }
}
