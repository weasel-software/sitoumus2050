// @Flow

import React, { Component, Fragment } from "react";
//import { get } from "lodash";

import AutoComplete from "./AutoComplete";
import TextInput from "./TextInput";
import InputIconContainer from "./InputIconContainer";

import translate from "./translate";

class LocationAutoComplete extends Component<
  {
    id: string,
    enableFilter: boolean,
    selectLocation: () => void,
    selectedLocation: string,
    name: string,
    type: "City" | "Country",
    cities: any
  },
  { isOpen: boolean }
> {
  state = { isOpen: false };

  toggleMenu = () => {
    this.setState({ isOpen: !this.state.isOpen });
  };

  render() {
    return (
      <AutoComplete
        id={this.props.id}
        items={this.props.cities}
        namePath="name"
        keyPath="name"
        enableFilter
        onChange={selectedItem => {
          this.handleAddressInput({
            name: "cityId",
            value: selectedItem.cityId
          });
        }}
        selectedItem={null}
        renderInput={({ getInputProps, toggleMenu, isOpen, selectedItem }) => {
          return (
            <TextInput
              {...getInputProps({
                placeholder: translate({
                  textKey: "sit.profile.enterAndChoose"
                }),
                name: "cityId",
                style: { width: "100%" },
                labelStyle: { minWidth: "120px" },
                label: translate({ textKey: "sit.profile.country" }),
                id: `${this.props.id}_input`,
                onKeyDown: e => {
                  if (e.key === "Enter") {
                    const city = this.props.cities.find(
                      c =>
                        c.nameCurrentValue.toLowerCase() ===
                        e.target.value.toLowerCase()
                    );
                    if (city) {
                      this.handleAddressInput({
                        name: "cityId",
                        value: city.cityId
                      });
                      this.toggleMenu();
                    }
                  }
                },
                renderIcon: () => (
                  <Fragment>
                    {isOpen ? (
                      <InputIconContainer
                        onClick={this.toggleMenu}
                        key={this.state.isOpen}
                        render={() => <i className="fas fa-chevron-up" />}
                      />
                    ) : (
                      <InputIconContainer
                        onClick={this.toggleMenu}
                        key={this.state.isOpen}
                        render={() => <i className="fas fa-chevron-down" />}
                      />
                    )}
                  </Fragment>
                )
              })}
            />
          );
        }}
      />
    );
  }
}

export default LocationAutoComplete;
