package com.soikea.commitment2050.service;

import com.liferay.portal.kernel.scheduler.*;

/**
 *  SchedulerEntryImpl extension which allows to save storagetype
 *  @link https://community.liferay.com/fi/blogs/-/blogs/liferay-7-ce-liferay-dxp-scheduled-tasks
 */
public class StorageTypeAwareSchedulerEntryImpl extends SchedulerEntryImpl implements SchedulerEntry, StorageTypeAware {

    private SchedulerEntryImpl schedulerEntry;
    private StorageType storageType;

    /**
     * StorageTypeAwareSchedulerEntryImpl: Constructor for the class.
     * @param schedulerEntry
     */
    public StorageTypeAwareSchedulerEntryImpl(final SchedulerEntryImpl schedulerEntry) {
        super();

        this.schedulerEntry = schedulerEntry;

        // use the same default that Liferay uses.
        storageType = StorageType.MEMORY_CLUSTERED;
    }

    /**
     * StorageTypeAwareSchedulerEntryImpl: Constructor for the class.
     * @param schedulerEntry
     * @param storageType
     */
    public StorageTypeAwareSchedulerEntryImpl(final SchedulerEntryImpl schedulerEntry, final StorageType storageType) {
        super();

        this.schedulerEntry = schedulerEntry;
        this.storageType = storageType;
    }

    @Override
    public String getDescription() {
        return schedulerEntry.getDescription();
    }

    @Override
    public String getEventListenerClass() {
        return schedulerEntry.getEventListenerClass();
    }

    @Override
    public StorageType getStorageType() {
        return storageType;
    }

    @Override
    public Trigger getTrigger() {
        return schedulerEntry.getTrigger();
    }

    public void setDescription(final String description) {
        schedulerEntry.setDescription(description);
    }
    public void setTrigger(final Trigger trigger) {
        schedulerEntry.setTrigger(trigger);
    }
    public void setEventListenerClass(final String eventListenerClass) {
        schedulerEntry.setEventListenerClass(eventListenerClass);
    }

}