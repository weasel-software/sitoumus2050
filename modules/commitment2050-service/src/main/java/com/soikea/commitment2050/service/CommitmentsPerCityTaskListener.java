package com.soikea.commitment2050.service;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.*;
import org.osgi.service.component.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Liferay persisted scheduler task for sending reminders about commitment reporting
 *
 * Job runs daily, at 04:00:00 am.
 *
 * @link https://community.liferay.com/fi/blogs/-/blogs/liferay-7-ce-liferay-dxp-scheduled-tasks
 */
@Component(
        immediate = true, property = {"cron.expression=0 30 4 ? * * *"},
        service = CommitmentsPerCityTaskListener.class
)
public class CommitmentsPerCityTaskListener extends BaseMessageListener {

    private static Logger log = LoggerFactory.getLogger(CommitmentsPerCityTaskListener.class.getName());

    //  0 0 4 ? * * *   = At 04:00:00am every day
    private static final String COMMITMENTS_PER_CITY_CALCULATION_TIMER = "0 30 4 ? * * *";

    private volatile boolean initialized;
    private TriggerFactory triggerFactory;
    private SchedulerEngineHelper schedulerEngineHelper;
    private SchedulerEntryImpl schedulerEntryImpl = null;

    @Reference
    CommitmentsPerCityService commitmentsPerCityService;

    @Reference
    CommitmentMailService commitmentMailService;

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
            log.info("Starting scheduled job: calculate commitments per city");
        }
        calculateCommitmentsPerCity();
        sendCommitmentApprovedEmails();
    }

    private void calculateCommitmentsPerCity() {
        commitmentsPerCityService.calculateCommitmentsPerCity();
    }
    private void sendCommitmentApprovedEmails() { commitmentMailService.sendCommitmentApprovedEmails(); }

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
        Trigger jobTrigger = triggerFactory.createTrigger(clazz, clazz, new Date(), null, COMMITMENTS_PER_CITY_CALCULATION_TIMER);

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
            log.debug("Commitments per city calculation scheduler initialized, job runs every day at 4:30 am");
        }
    }

    @Deactivate
    protected void deactivate() {
        if (initialized) {
            try {
                schedulerEngineHelper.unschedule(schedulerEntryImpl, getStorageType());
            } catch (SchedulerException e) {
                if (log.isWarnEnabled()) {
                    log.warn("Scheduled job: calculation of commitments per city could not be unscheduled", e);
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