// @flow

import type {
    LocaleType
} from "../../constants/types";
import React, {Component, Fragment} from "react";
import MarkerClusterGroup from 'react-leaflet-markercluster';

import {GeoJSON, Map, Marker, TileLayer} from "react-leaflet";
import WhiteSpace from "../reusable/WhiteSpace";

import 'leaflet/dist/leaflet.css';
import L from 'leaflet';
import {withRouter} from "react-router-dom";
import {connect} from "react-redux";
import type {STORE_STATE} from "../../redux/store";
import {pick} from "lodash";
import {reduce} from "rxjs";

require('react-leaflet-markercluster/dist/styles.min.css');

/* Marker 404 fix */

delete L.Icon.Default.prototype._getIconUrl;

L.Icon.Default.mergeOptions({
    iconRetinaUrl: require('leaflet/dist/images/marker-icon-2x.png'),
    iconUrl: require('leaflet/dist/images/marker-icon.png'),
    shadowUrl: require('leaflet/dist/images/marker-shadow.png')
});

class ClusterMap extends Component <{ locale: LocaleType, commitmentsPerCity: Object }> {
    state = {
        mapHidden: true
    };

    constructor(props) {
        super(props);
    }

    componentDidMount() {
    }

    mapstyle(feature) {
        return {
            fillColor: "#fff",
            weight: 2,
            color: "#e4dcdc",
            opacity: 1,
            dashArray: '1',
            fillOpacity: 1
        };
    };
     newIcon = L.icon({
        className: 'new_icon_location.png'
    });
    clusterStyle = function (cluster) {
        let sum = cluster.getAllChildMarkers().reduce(function(prev, cur) {
            return prev + cur.options.count;
        }, 0);
        let c = ' marker-cluster-';
        if (sum < 2) {
            c += 'tiny';
            return new L.DivIcon({
                className: 'marker-cluster' + c, iconSize: new L.Point(20, 20)
            });
        } else if (sum < 100) {
            c += 'small';
            return new L.DivIcon({
                html: '<div><span style="color: #fff; font-weight: bold;">' + sum + '</span></div>',
                className: 'marker-cluster' + c, iconSize: new L.Point(40, 40)
            });
        } else if (sum < 500) {
            c += 'medium';
            return new L.DivIcon({
                html: '<div><span style="color: #fff; font-weight: bold;">' + sum + '</span></div>',
                className: 'marker-cluster' + c, iconSize: new L.Point(50, 50)
            });
        } else {
            c += 'large';
            return new L.DivIcon({
                html: '<div><span style="color: #fff; font-weight: bold;">' + sum + '</span></div>',
                className: 'marker-cluster' + c, iconSize: new L.Point(60, 60)
            });
        }
    }

    render() {

        let finland = require('../../constants/finland.json');
        const markerArray = this.props.commitmentsPerCity.data;

        return (
            <Fragment>
                <Map className="markercluster-map" center={[65.467754, 25.433493]}
                     style={{height: "620px", width: "300px", background: "#fff", zIndex: "0"}}
                     doubleClickZoom={false} closePopupOnClick={false} dragging={false} zoomSnap={false} zoomDelta={false} trackResize={false}
                     touchZoom={false} scrollWheelZoom={false} zoom={5} maxZoom={5} zoomControl={false}
                >

                    {/*
                      <TileLayer
                          url="https://{s}.basemaps.cartocdn.com/light_nolabels/{z}/{x}/{y}{r}.png"
                          attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
                      />
                    */}

                    <GeoJSON data={finland} style={this.mapstyle}/>

                    {/* Cluster options : https://github.com/Leaflet/Leaflet.markercluster#all-options */}
                    <MarkerClusterGroup
                        showCoverageOnHover={false} iconCreateFunction={this.clusterStyle} spiderfyOnMaxZoom={false} singleMarkerMode={true}
                        zoomToBoundsOnClick={false} onClusterClick={(e) => console.log(e.layer.getAllChildMarkers())} maxClusterRadius={70}

                    >
                        {markerArray ? markerArray.map(city => {
                                return (
                                    <Marker
                                        key={city.city}
                                        position={[city.latitude, city.longitude]}
                                        name={city.city}
                                        count={city.count}
                                    />
                                );
                            })
                            : null
                        }

                    </MarkerClusterGroup>

                </Map>
            </Fragment>
        );
    }
}

export default withRouter(
    connect(
        (state: STORE_STATE) => ({
            locale: state.user.locale || "fi_FI",
            commitmentsPerCity: state.articles.commitmentsPerCity
        }),
        {
            ...pick(
                "getCommitmentsPerCity"
            )
        }
    )(ClusterMap)
);
