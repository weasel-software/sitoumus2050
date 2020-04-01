// @flow

import React from "react";
import styled from "styled-components";
import Downshift from "downshift";
import { get } from "lodash";

const getActiveStateBackground = activeState => {
  if (activeState === "matchesInput") return "papayawhip";
  if (activeState === "selected") return "#eee";
  return "#fff";
};

const StyledAutocompleteRow = styled.div`
  min-height: 48px;
  border-bottom: 1px solid #ccc;
  display: flex;
  align-items: center;
  padding: 6px;
  padding-left: 18px;
  &:hover {
    background: #eee;
    cursor: pointer;
  }
  font-weight: ${props => (props.activeState ? "bold" : "normal")};
  background: ${props => getActiveStateBackground(props.activeState)};
  color: ${props => (props.activeState === "matchesInput" ? "green" : "black")};
`;

const StyledDropdown = styled.div`
  background: #fff;
  box-shadow: 0 8px 36px #aaa;
  position: absolute;
  z-index: 100;
  width: 100%;
  max-height: 576px;
  overflow-y: scroll;
`;

const AutoComplete = ({
  items,
  onChange,
  renderInput,
  keyPath = "id",
  namePath = "name",
  enableFilter,
  selectedItem,
  defaultSelectedItem,
  openOnClick,
  closeOnSelect,
  style
}: {
  // items?: ?ITEM_ARRAY_TYPE,
  items: any, // FIXME: Add type safety
  onChange: any => void,
  renderInput: any => React$Node,
  keyPath?: string,
  namePath?: string,
  enableFilter?: boolean,
  selectedItem?: any,
  defaultSelectedItem?: any,
  openOnClick?: boolean,
  closeOnSelect?: boolean,
  style?: any
}) => (
  <Downshift
    onChange={e => {
      onChange(e);
    }}
    itemToString={item =>
      item && namePath && get(item, [namePath], get(item, ["name"], ""))
    }
    selectedItem={selectedItem}
    defaultSelectedItem={defaultSelectedItem}
    render={({
      getInputProps,
      getItemProps,
      isOpen,
      inputValue,
      selectedItem,
      highlightedIndex,
      selectItem,
      toggleMenu
    }) => (
      <div
        style={{ width: "100%" }}
        onFocus={() => openOnClick && !isOpen && toggleMenu()}
      >
        {renderInput &&
          renderInput({
            getInputProps,
            toggleMenu,
            isOpen,
            selectedItem
          })}
        {isOpen ? (
          <StyledDropdown style={style}>
            {items &&
              items
                .filter(i => {
                  if (enableFilter)
                    return (
                      !inputValue ||
                      i[namePath]
                        .toLowerCase()
                        .includes(inputValue.toLowerCase())
                    );
                  else return i;
                })
                .map((item, index) => {
                  const id = item[keyPath] || item.id || item.categoryId;
                  const activeState =
                    !enableFilter &&
                    item &&
                    inputValue &&
                    item[namePath] &&
                    item[namePath]
                      .toLowerCase()
                      .includes(inputValue.toLowerCase()) &&
                    "matchesInput";
                  return (
                    <StyledAutocompleteRow
                      {...getInputProps({ item })}
                      key={id}
                      onClick={() => {
                        selectItem(item);
                        if (closeOnSelect) toggleMenu();
                      }}
                      activeState={activeState}
                    >
                      {namePath ? item[namePath] : item.name}
                    </StyledAutocompleteRow>
                  );
                })}
          </StyledDropdown>
        ) : null}
      </div>
    )}
  />
);

export default AutoComplete;
