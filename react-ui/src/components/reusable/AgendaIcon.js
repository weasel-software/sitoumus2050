// @flow

import React from "react";
import styled from "styled-components";
import { icon_path_agenda2030_goal } from "../../constants/constants";

const StyledAgendaIcon = styled.img`
  border: 0;
  width: 60px;
  height: 60px;
`;

const AddRemoveIcon = styled.div`
  width: 16px;
  height: 16px;
  border-radius: 8px;
  position: absolute;
  background: rgba(72, 72, 72, 0.75);
  color: #eee;
  z-index: 1;
  right: 2px;
  top: 2px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  &:hover {
    background: rgba(0, 0, 0, 0.5);
    color: #fff;
  }
`;

const Container = styled.div`
  position: relative;
  max-width: 60px;
  max-height: 60px;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 0;
  margin-right: 12px;
  margin-bottom: 12px;
  color: #93be38;
  opacity: ${props => (props.isActive ? 1 : 0.35)};
`;

const AgendaIcon = ({
  icon,
  isActive = true,
  preventEdit = true,
  onClick,
  ...rest
}: {
  icon: string,
  isActive?: boolean,
  preventEdit?: boolean,
  onClick?: (SyntheticInputEvent<any>) => void,
  rest?: any
}) => {
  if (!icon) return null;
  const link = `${icon_path_agenda2030_goal}/${icon}`;
  return (
    <Container
      {...rest}
      isActive={isActive}
      onClick={e => {
        if (!preventEdit && onClick) onClick(e);
      }}
    >
      <StyledAgendaIcon src={link} />
      {!preventEdit && (
        <AddRemoveIcon>
          <span key={String(isActive)} style={{ fontSize: "13px" }}>
            {isActive ? (
              <i className="fas fa-minus-circle" />
            ) : (
              <i className="fas fa-plus-circle" />
            )}
          </span>
        </AddRemoveIcon>
      )}
    </Container>
  );
};

export default AgendaIcon;
