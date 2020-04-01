// @flow

import React from "react";
import styled from "styled-components";

const StyledAgendaIcon = styled.svg`
`;


const ShareIcon = ({
                      width = "1em",
                      height = "1em",
                      strokeWidth = "120px",
                      ...rest
                    }: {
  width?: string,
  height?: string,
  strokeWidth?: string,
  rest?: any
}) => {
  return (
    <StyledAgendaIcon viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlnsXlink="http://www.w3.org/1999/xlink" xmlSpace="preserve" style={{width:width,height:height,overflow:'visible',fillRule:'evenodd',clipRule:'evenodd',strokeLinecap:'round',strokeLinejoin:'round',strokeMiterlimit:'1.5'}}>
    <g transform="matrix(1.06233,0,0,1.06233,-32.5472,38.204)">
        <g transform="matrix(1,0,0,1,0.595275,-66.0045)">
            <path d="M381.856,220.179C392.614,281.967 446.559,329.005 511.405,329.005C576.25,329.005 630.196,281.967 640.953,220.179C787.115,273.209 891.65,413.368 891.65,577.75C891.65,598.572 889.973,619.005 886.746,638.922C873.383,634.342 859.052,631.856 844.145,631.856C771.568,631.856 712.645,690.779 712.645,763.356C712.645,804.215 731.32,840.747 760.594,864.874C693.838,922.871 606.691,957.995 511.405,957.995C416.393,957.995 329.474,923.073 262.794,865.375C292.419,841.255 311.355,804.499 311.355,763.356C311.355,690.779 252.432,631.856 179.855,631.856C164.528,631.856 149.81,634.484 136.127,639.314C132.859,619.273 131.159,598.708 131.159,577.75C131.159,413.368 235.694,273.209 381.856,220.179Z" style={{fill:'none',stroke:'black',strokeWidth:strokeWidth}}/>
        </g>
      <g transform="matrix(0.998901,0,0,1.00111,-60.0295,-27.4863)">
            <ellipse cx="572.659" cy="158.809" rx="131.645" ry="131.354" style={{fill:'none',stroke:'black',strokeWidth:strokeWidth}}/>
        </g>
      <g transform="matrix(0.998901,0,0,1.00111,272.711,538.365)">
            <ellipse cx="572.659" cy="158.809" rx="131.645" ry="131.354" style={{fill:'none',stroke:'black',strokeWidth:strokeWidth}}/>
        </g>
      <g transform="matrix(0.998901,0,0,1.00111,-391.579,538.365)">
            <ellipse cx="572.659" cy="158.809" rx="131.645" ry="131.354" style={{fill:'none',stroke:'black',strokeWidth:strokeWidth}}/>
        </g>
    </g>
    </StyledAgendaIcon>
  );
};

export default ShareIcon;
