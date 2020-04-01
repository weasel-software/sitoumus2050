// @flow

import React, { Component } from "react";
import Button from "../reusable/Button";
import { connect } from "react-redux";
import { MaintenanceActions } from "../../redux/maintenance";

class Initialisators extends Component<{
  formatCommitmentAddressesResponse: Object,
  preCalculateCommitmentsPerCityResponse: Object,
  addOrganizationIdsResponse: Object,
  formatAllAddresses: () => *,
  preCalculateCommitmentsPerCity: () => *,
  addOrganizationIds: () => *
}> {
  formatAllAddressesClicked = () => {
    if (
      window.confirm(
        "Haluatko varmasti formatoida kaikkien sitoumuksien osoitteet?"
      )
    ) {
      this.props.formatAllAddresses();
    }
  };

  preCalculateCommitmentsPerCityClicked = () => {
    if (
      window.confirm(
        "Haluatko varmasti laskea kaikkien paikkakuntien sitoumukset?"
      )
    ) {
      this.props.preCalculateCommitmentsPerCity();
    }
  };

  addOrganizationIdsClicked = () => {
    if (window.confirm("Haluatko varmasti lisää organisaatio id:t?")) {
      this.props.addOrganizationIds();
    }
  };

  render() {
    const formatNotification = this.props.formatCommitmentAddressesResponse;
    const formatInProgress =
      formatNotification && formatNotification.state === "IN_PROGRESS";
    const formatSuccess =
      formatNotification && formatNotification.state === "SUCCESS";
    const formatFailure =
      formatNotification && formatNotification.state === "FAILURE";

    const addNotification = this.props.addOrganizationIdsResponse;
    const addInProgress =
      addNotification && addNotification.state === "IN_PROGRESS";
    const addSuccess = addNotification && addNotification.state === "SUCCESS";
    const addFailure = addNotification && addNotification.state === "FAILURE";

    const calculateNotification = this.props
      .preCalculateCommitmentsPerCityResponse;
    const calculateInProgress =
      calculateNotification && calculateNotification === "IN_PROGRESS";
    const calculateSuccess =
      calculateNotification && calculateNotification.state === "SUCCESS";
    const calculateFailure =
      calculateNotification && calculateNotification.state === "FAILURE";

    return (
      <div className="row">
        <div className="col-md-12">
          <div>
            <p>
              Tästä muokataan kaikkien sitoumusten osoitteet näyttämään
              pelkästään paikkakunta. Muutos tehdään VNK:n tarjoamaa
              paikkakunnat tiedostoa vastaan. Jos sitoumuksen osoite ei vastaan
              yhtään paikkakuntaa paikkakunnat tiedostossa, osoite pysyy
              muuttumattomana.
            </p>
          </div>
          <br />
          <Button
            disabled={formatInProgress}
            onClick={this.formatAllAddressesClicked}
          >
            Muokkaa kaikkien sitoumuksien osoitteet
          </Button>
          {formatInProgress && <div>{"Osoitteita muokataan..."}</div>}
          {formatSuccess && <div>{formatNotification.message}</div>}
          {formatFailure && <div>{"VIRHE! " + formatNotification.message}</div>}
          <br />
          <br />

          <div>
            <p>
              Tästä suoritetaan laskenta montako paikkakuntaa löytyy per
              paikkakunta. Tieto ylläpidetään palvelimella ja sitoumuksien
              lukumäärän kartalla näyttävä komponentti hyödyntää tätä. Laskenta
              on ajastettu toiminto joka yölle kello 04:00. Jos palvelin
              käynnistetään sen jälkeen kartta ei näy ennen kuin laskenta on
              suoritettu seuraavana yönä tai täältä käynnistettynä.
            </p>
          </div>
          <br />
          <Button
            disabled={calculateInProgress}
            onClick={this.preCalculateCommitmentsPerCityClicked}
          >
            Laske sitoumukset per paikkakunta
          </Button>
          {calculateInProgress && <div>{"Sitoumuksia lasketaan..."}</div>}
          {calculateSuccess && <div>{calculateNotification.message}</div>}
          {calculateFailure && (
            <div>{"VIRHE! " + calculateNotification.message}</div>
          )}
          <br />
          <br />

          <div>
            <p>Tästä lisätään org.id:t organisaatioden sitoumuksille.</p>
          </div>
          <br />
          <Button
            disabled={addInProgress}
            onClick={this.addOrganizationIdsClicked}
          >
            Lisää org.id:t organisaatioden sitoumuksille
          </Button>
          {addInProgress && <div>{"Org.id:t muokataan..."}</div>}
          {addSuccess && <div>{addNotification.message}</div>}
          {addFailure && <div>{"VIRHE! " + addNotification.message}</div>}
        </div>
      </div>
    );
  }
}

export default connect(
  state => ({
    formatCommitmentAddressesResponse:
      state.maintenance.formatCommitmentAddressesResponse,
    addOrganizationIdsResponse: state.maintenance.addOrganizationIdsResponse,
    preCalculateCommitmentsPerCityResponse:
      state.maintenance.preCalculateCommitmentsPerCityResponse
  }),
  {
    formatAllAddresses: MaintenanceActions.formatAllAddresses,
    addOrganizationIds: MaintenanceActions.addOrganizationIds,
    preCalculateCommitmentsPerCity:
      MaintenanceActions.preCalculateCommitmentsPerCity
  }
)(Initialisators);
