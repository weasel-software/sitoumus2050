package fi.weasel.commitment2050.commitmentanalysis.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringUtil;
import fi.weasel.commitment2050.commitmentanalysis.model.Commitment;
import fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult;
import fi.weasel.commitment2050.commitmentanalysis.model.impl.CommitmentAnalysisResultImpl;
import fi.weasel.commitment2050.commitmentanalysis.model.impl.CommitmentImpl;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.CommitmentAnalysisResultFinder;

import java.util.List;

public class CommitmentAnalysisResultFinderImpl extends CommitmentAnalysisResultFinderBaseImpl implements CommitmentAnalysisResultFinder {

    public static final String FIND_LATEST_BY_RESULT_TYPE = CommitmentAnalysisResultFinder.class.getName() + ".findLatestByResultType";

    public CommitmentAnalysisResult findLatestByResultType(String resultType) {
        Session session = null;

        try {
            session = openSession();

            String sql = CustomSQLUtil.get(
                    getClass(),
                    FIND_LATEST_BY_RESULT_TYPE);

            sql = StringUtil.replace(sql, "[$RESULT_TYPE_STR$]", resultType);

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("CommitmentAnalysisResult", CommitmentAnalysisResultImpl.class);

            return (CommitmentAnalysisResult) q.list().get(0);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            closeSession(session);
        }

        return null;

    }
}
