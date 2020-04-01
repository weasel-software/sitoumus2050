package com.soikea.commitment2050.service;

import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.util.comparator.ArticleCreateDateComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.*;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.soikea.commitment2050.api.CommitmentApi;
import com.soikea.commitment2050.model.Commitment;
import com.soikea.commitment2050.model.ReportingInterval;
import com.soikea.commitment2050.model.StatisticsValueAndDate;
import org.osgi.service.component.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Liferay persisted scheduler task for sending reminders about commitment reporting
 *
 * Job runs daily, at 04:00:00 am.
 *
 * @link https://community.liferay.com/fi/blogs/-/blogs/liferay-7-ce-liferay-dxp-scheduled-tasks
 */
@Component(
        immediate = true, property = {"cron.expression=0 0 4 ? * * *"},
        service = ReportReminderTaskListener.class
)
public class ReportReminderTaskListener extends BaseMessageListener {

    private static Logger log = LoggerFactory.getLogger(ReportReminderTaskListener.class.getName());

    //  0 0 4 ? * * *   = At 04:00:00am every day
    private static final String REPORT_REMINDER_TIMER = "0 0 4 ? * * *";

    private volatile boolean initialized;
    private TriggerFactory triggerFactory;
    private SchedulerEngineHelper schedulerEngineHelper;
    private SchedulerEntryImpl schedulerEntryImpl = null;

    @Reference
    CommitmentService commitmentService;

    @Reference
    CommitmentMailService commitmentMailService;

    @Reference
    UserService userService;

    /**
     * OSGi will start a component once all of it's dependencies are satisfied.  However, there
     * are times where you want to hold off until the portal is completely ready to go.
     *
     * This reference declaration is waiting for the ModuleServiceLifecycle's PORTAL_INITIALIZED
     * component which will not be available until, surprise surprise, the portal has finished
     * initializing.
     *
     * With this reference, this component activation waits until portal initialization has completed.
     * @param moduleServiceLifecycle
     */
    @Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
    protected void setModuleServiceLifecycle(ModuleServiceLifecycle moduleServiceLifecycle) {
    }

    @Reference(unbind = "-")
    protected void setTriggerFactory(TriggerFactory triggerFactory) {
        this.triggerFactory = triggerFactory;
    }

    @Reference(unbind = "-")
    protected void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {
        this.schedulerEngineHelper = schedulerEngineHelper;
    }

    /**
     *
     * @param message This is the message object tied to the job.  If you stored data with the
     *                job, the message will contain that data.
     */
    @Override
    protected void doReceive(Message message) {
        if (log.isInfoEnabled()) {
            log.info("Starting scheduled job: send report reminders");
        }
        sendReportReminders();
    }

    private void sendReportReminders() {
        try {
            List<Commitment> commitments = commitmentService.getAllLatestApprovedCommitments();
            LocalDate now = LocalDate.now();
            for (Commitment c : commitments ) {
                sendReportReminder(now, c);
            }
        } catch (Exception e) {
            log.error("Report reminders could not be send! ", e.getMessage());
        }

    }

    /**
     * Send report reminder to user if commitment's reportReminder option is set to true and commitment's selected
     * reporting interval matches to period between commitments startDate and current time or current time is same
     * as commitment's end time
     *
     * @param now time now
     * @param commitment
     */
    private void sendReportReminder(LocalDate now, Commitment commitment) {
        Commitment c = commitment;
        try {
            // check if report reminder is on and interval is set
            if ( c != null && c.isReportReminder() && c.getReportingInterval() != null && !ReportingInterval.CUSTOM.equals(c.getReportingInterval())) {
                LocalDate startDate = LocalDate.parse(c.getStartDate(), DateTimeFormatter.ISO_LOCAL_DATE);
                LocalDate endDate = LocalDate.parse(c.getEndDate(), DateTimeFormatter.ISO_LOCAL_DATE);
                //Send reminders only when commitment is in effect or commitment is ended
                if (now.isAfter(startDate) && ( now.isBefore(endDate) || now.isEqual(endDate)) ) {
                    //calculate period between startDate and now
                    long months = ChronoUnit.MONTHS.between(startDate, now);
                    long monthsYesterday = ChronoUnit.MONTHS.between(startDate, now.minusDays(1));
                    long reminder = months % c.getReportingInterval().getInterval();

                    if ( log.isDebugEnabled() ) {
                        log.debug("sendReportReminder(), reminder: " + reminder + " months: " + months + " monthyesterday: " + monthsYesterday);
                    }

                    if ( now.isEqual(endDate) || ( reminder == 0 && months > monthsYesterday )) {
                        User u = userService.getUser(c.getCreatedByUserId());
                        commitmentMailService.sendEmail(u.getEmailAddress(), Constants.EMAIL_REPORT_REMINDER, Constants.lang_fi);
                    }
                }
            }
        } catch (Exception e) {
            log.error("Sending report reminder failed! ", e);
        }
    }

    /**
     * activate: Called whenever the properties for the component change
     * or OSGi is activating the component.
     * @param properties The properties map.
     * @throws SchedulerException in case of error.
     */
    @Activate
    @Modified
    protected void activate(Map<String,Object> properties) throws SchedulerException {

        // new trigger for the job.
        String clazz = getClass().getName();
        Trigger jobTrigger = triggerFactory.createTrigger(clazz, clazz, new Date(), null, REPORT_REMINDER_TIMER);

        //init scheduler
        schedulerEntryImpl = new SchedulerEntryImpl();
        schedulerEntryImpl.setEventListenerClass(getClass().getName());
        schedulerEntryImpl.setTrigger(jobTrigger);
        schedulerEntryImpl = new StorageTypeAwareSchedulerEntryImpl(schedulerEntryImpl, StorageType.PERSISTED);
        schedulerEntryImpl.setTrigger(jobTrigger);
        if (initialized) {
            deactivate();
        }
        // register task
        schedulerEngineHelper.register(this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);

        // set the initialized flag.
        initialized = true;
        if (log.isDebugEnabled()) {
            log.debug("Report reminder scheduler initialized, job runs every day at 04:00 am");
        }
    }

    @Deactivate
    protected void deactivate() {
        if (initialized) {
            try {
                schedulerEngineHelper.unschedule(schedulerEntryImpl, getStorageType());
            } catch (SchedulerException e) {
                if (log.isWarnEnabled()) {
                    log.warn("Scheduled job: send report reminders could not be unscheduled", e);
                }
            }
            schedulerEngineHelper.unregister(this);
        }
        initialized = false;
    }

    /**
     * getStorageType: Utility method to get the storage type from the scheduler entry wrapper.
     * @return StorageType The storage type to use.
     */
    protected StorageType getStorageType() {
        if (schedulerEntryImpl instanceof StorageTypeAware) {
            return ((StorageTypeAware) schedulerEntryImpl).getStorageType();
        }
        return StorageType.PERSISTED;
    }

}