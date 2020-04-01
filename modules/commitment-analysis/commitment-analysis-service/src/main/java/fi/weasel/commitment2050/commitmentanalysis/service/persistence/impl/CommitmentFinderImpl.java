package fi.weasel.commitment2050.commitmentanalysis.service.persistence.impl;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringUtil;
import fi.weasel.commitment2050.commitmentanalysis.model.Commitment;
import fi.weasel.commitment2050.commitmentanalysis.model.impl.CommitmentImpl;
import fi.weasel.commitment2050.commitmentanalysis.model.impl.MeterImpl;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.CommitmentFinder;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommitmentFinderImpl extends CommitmentFinderBaseImpl implements CommitmentFinder {
    public List<Commitment> findByUserId(long userId) {

        Session session = null;
        try {
            session = openSession();

            String sql = CustomSQLUtil.get(
                    getClass(),
                    FIND_BY_USERID);

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("Commitment", CommitmentImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(userId);

            return (List<Commitment>) q.list();
        }
        catch (Exception e) {
            try {
                throw new SystemException(e);
            }
            catch (SystemException se) {
                se.printStackTrace();
            }
        }
        finally {
            closeSession(session);
        }

        return null;
    }

    public static final String FIND_BY_USERID = CommitmentFinder.class.getName() + ".findByUserId";

    public List getAverageReduction() {

        Session session = null;
        try {
            session = openSession();

            String sql = CustomSQLUtil.get(
                    getClass(),
                    CALC_AVG_RED);

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
/*
            q.addEntity("Meter", MeterImpl.class);
*/
/*
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(userId);*/

            return q.list();
        }
        catch (Exception e) {
            try {
                throw new SystemException(e);
            }
            catch (SystemException se) {
                se.printStackTrace();
            }
        }
        finally {
            closeSession(session);
        }

        return null;
    }

    public static final String CALC_AVG_RED = CommitmentFinder.class.getName() + ".getAverageReduction";

    public List getAvgReductionByMeter() {

        Session session = null;
        try {
            session = openSession();

            String sql = CustomSQLUtil.get(
                    getClass(),
                    CALC_AVG_RED_BY_METER);

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
/*
            q.addEntity("Meter", MeterImpl.class);
*/
/*
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(userId);*/

            return q.list();
        }
        catch (Exception e) {
            try {
                throw new SystemException(e);
            }
            catch (SystemException se) {
                se.printStackTrace();
            }
        }
        finally {
            closeSession(session);
        }

        return null;
    }

    public static final String CALC_AVG_RED_BY_METER = CommitmentFinder.class.getName() + ".getAvgReductionByMeter";

    public Double getAverageReductionKg() {

        Session session = null;
        try {
            session = openSession();

            String sql = CustomSQLUtil.get(
                    getClass(),
                    CALC_AVG_RED_KG);

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);


            return (Double) q.list().get(0);
        }
        catch (Exception e) {
            try {
                throw new SystemException(e);
            }
            catch (SystemException se) {
                se.printStackTrace();
            }
        }
        finally {
            closeSession(session);
        }

        return null;
    }

    public static final String CALC_AVG_RED_KG = CommitmentFinder.class.getName() + ".getAverageReductionKg";

    public BigInteger getCommitmentCountLifestyleTest() {

        Session session = null;
        try {
            session = openSession();

            String sql = CustomSQLUtil.get(
                    getClass(),
                    COUNT_COMMITMENTS_FROM_LIFESTYLE_TEST);

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);

            return (BigInteger) q.list().get(0);
        }
        catch (Exception e) {
            try {
                throw new SystemException(e);
            }
            catch (SystemException se) {
                se.printStackTrace();
            }
        }
        finally {
            closeSession(session);
        }

        return null;
    }

    public static final String COUNT_COMMITMENTS_FROM_LIFESTYLE_TEST = CommitmentFinder.class.getName() + ".countApprovedCommitmentsFromLifestyleTest";

    public BigInteger getCommitmentCountByTypeAndOrgIdNullOrNotNull(String[] commitmentTypes, boolean isOrganization) {

        StringBuilder csb = new StringBuilder();

        for (String c : commitmentTypes)
            csb.append("'").append(c).append("',");

        csb.deleteCharAt(csb.length()-1);

        Session session = null;
        try {
            session = openSession();

            String sql = CustomSQLUtil.get(
                    getClass(),
                    COUNT_COMMITMENTS_OF_TYPE);

            sql = StringUtil.replace(sql, "[$COMMITMENT_TYPES$]", csb.toString());

            sql = StringUtil.replace(sql, "[$ORGANIZATION_IS_NULL$]", isOrganization? "IS NOT NULL" : "IS NULL");

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);

            return (BigInteger) q.list().get(0);
        }
        catch (Exception e) {
            try {
                throw new SystemException(e);
            }
            catch (SystemException se) {
                se.printStackTrace();
            }
        }
        finally {
            closeSession(session);
        }

        return null;
    }

    public static final String COUNT_COMMITMENTS_OF_TYPE = CommitmentFinder.class.getName() + ".getCommitmentCountByTypeAndOrgIdNullOrNotNull";

    public List getCountForAllTypes() {

        Session session = null;
        try {
            session = openSession();

            String sql = CustomSQLUtil.get(
                    getClass(),
                    COUNT_ALL_COMM_TYPES);

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);

            return q.list();
        }
        catch (Exception e) {
            try {
                throw new SystemException(e);
            }
            catch (SystemException se) {
                se.printStackTrace();
            }
        }
        finally {
            closeSession(session);
        }

        return null;
    }

    public static final String COUNT_ALL_COMM_TYPES = CommitmentFinder.class.getName() + ".getCountForAllTypes";

    public BigInteger getWillDoLifestyleCommitmentCountByMeterId(long meterId) {

        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    getClass(),
                    COUNT_WILL_DO_LIFESTYLE_COMMITMENTS_BY_METERID);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);

            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(meterId);
            List l = q.list();
            return (BigInteger) l.get(0);
        }
        catch (Exception e) {
            try {
                throw new SystemException(e);
            }
            catch (SystemException se) {
                se.printStackTrace();
            }
        }
        finally {
            closeSession(session);
        }

        return null;
    }

    public static final String COUNT_WILL_DO_LIFESTYLE_COMMITMENTS_BY_METERID = CommitmentFinder.class.getName() + ".getWillDoLifestyleCommitmentCountByMeterId";
}
