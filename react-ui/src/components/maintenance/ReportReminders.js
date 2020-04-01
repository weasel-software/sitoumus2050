// @flow

import React, { Component } from "react";
import { connect } from "react-redux";
import { MaintenanceActions } from "../../redux/maintenance";
import Button from "../reusable/Button";

class ReportReminders extends Component<{
  getGreenDealReportReminderTemplate: () => *,
  sendGreenDealReportReminders: () => *,
  greenDealReportReminderTemplate: *,
  sendGreenDealReportRemindersNotification: *
}> {
  componentDidMount() {
    this.props.getGreenDealReportReminderTemplate();
  }

  sendGreenDealReportRemindersClicked = () => {
    if (window.confirm("Haluatko varmasti lähettää raportointimuistutukset?")) {
      this.props.sendGreenDealReportReminders();
    }
  };

  render() {
    const template = this.props.greenDealReportReminderTemplate;
    const notification = this.props.sendGreenDealReportRemindersNotification;
    const inProgress = notification && notification.state === "IN_PROGRESS";
    const success = notification && notification.state === "SUCCESS";
    const failure = notification && notification.state === "FAILURE";

    const templateExists = template && template.subject && template.body;

    return template ? (
      <div>
        <Button
          disabled={!templateExists || inProgress}
          onClick={this.sendGreenDealReportRemindersClicked}
        >
          {inProgress ? "Sending..." : "Send GreenDeal report reminders"}
        </Button>
        {success && <div>Reminders sent!</div>}
        {failure && <div>{"ERROR! " + notification.message}</div>}

        <h3>{template.subject}</h3>
        <p>{template.body}</p>
        <div>{template.error}</div>
      </div>
    ) : (
      <div />
    );
  }
}

export default connect(
  state => ({
    greenDealReportReminderTemplate:
      state.maintenance.greenDealReportReminderTemplate,
    sendGreenDealReportRemindersNotification:
      state.maintenance.sendGreenDealReportRemindersNotification
  }),
  {
    getGreenDealReportReminderTemplate:
      MaintenanceActions.getGreenDealReportReminderTemplate,
    sendGreenDealReportReminders:
      MaintenanceActions.sendGreenDealReportReminders
  }
)(ReportReminders);
