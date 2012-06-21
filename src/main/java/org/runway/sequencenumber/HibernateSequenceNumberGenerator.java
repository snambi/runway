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

package org.runway.sequencenumber;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateSequenceNumberGenerator {

    public static long getNext( String sequenceName, SessionFactory sessionFactory ){
        long result = 0l;

        Session session = sessionFactory.openSession();
        String querySql = "from SequenceNumber  where id = :seqName";

        Query query = session.createQuery(querySql);
        query.setString("seqName", sequenceName);
        List<SequenceNumber> list = query.list();

        if( list.size() == 1){
            SequenceNumber seqNumber  =  list.get(0);
            long seqNo = seqNumber.getSequenceNumber() + 1;
            seqNumber.setSequenceNumber(seqNo);
            session.update(seqNumber);
            session.flush();
            result = seqNo;
        }

        session.close();

        return result;
    }
}

