// @flow

import type {STORE_STATE} from "../../redux/store";
import type {
    ARTICLE_TYPE,
    CATEGORY_TYPE,
    LocaleType
} from "../../constants/types";
import type {ORGANIZATION_STATE} from "../../redux/organizations";

import React, {Component, Fragment} from "react";
import MarkerClusterGroup from 'react-leaflet-markercluster';
import CommitmentList from "./CommitmentList";
import {allTypes, catCompany, catPerson} from "../../constants/constants";
import {GeoJSON, Map, Marker, TileLayer} from "react-leaflet";

import {Route, withRouter} from "react-router-dom";
import {connect} from "react-redux";
import styled from "styled-components";
import {toArray, get, flatMap, pick} from "lodash";

import {ArticleActions} from "../../redux/articles";
import {OrganizationActions} from "../../redux/organizations";
import qs from "../../vendor/query-string";

import {RadioButton, RadioButtonContainer} from "../reusable/Radio";
import Button from "../reusable/Button";
import TextInput from "../reusable/TextInput";
import Checkbox from "../reusable/Checkbox";
import Dropdown from "../reusable/Dropdown";
import GoalIcon from "../reusable/GoalIcon";
import WhiteSpace from "../reusable/WhiteSpace";
import translate from "../reusable/translate";

import Commitment from "../commitment";

import 'leaflet/dist/leaflet.css';
import ClusterMap from "../reusable/ClusterMap";
import L from 'leaflet';
import {Header} from "./styles";

require('react-leaflet-markercluster/dist/styles.min.css');

/* Marker 404 fix */


delete L.Icon.Default.prototype._getIconUrl;

L.Icon.Default.mergeOptions({
    iconRetinaUrl: require('leaflet/dist/images/marker-icon-2x.png'),
    iconUrl: require('leaflet/dist/images/marker-icon.png'),
    shadowUrl: require('leaflet/dist/images/marker-shadow.png')
});

const Container = styled.div`
    display: flex;
`;

const Row = styled.div`
    display: flex;
    flex-direction: row;
    flex: 1 1 auto;
    justify-content: center;
    align-items: center;
    padding: 30px;
`;

const PaddedCheckbox = styled(Checkbox)`
  margin: 0px 20px 0px 20px;
  border: 0;
  text-transform: uppercase;
  font-size: 16px;
  font-weight: normal;
`;

const SearchBox = styled(TextInput)`
    text-align: left;
`;

const FilterBar = styled.div`
    background: #f4f4f4;
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 54px;
    max-height: 54px;
    border-top: 1px solid #aeaeae;
    border-bottom: 1px solid #aeaeae;
    flex-direction: row;
    overflow: hidden;
`;

const JustifyRight = styled.div`
    display: flex;
    justify-content: flex-end;
    align-items: center;
    flex-direction: row;
    max-width: 960px;
    align-self: flex-end;
`;

const AlignCenter = styled.div`
    display: flex;
    flex: 1;
    justify-content: center;
    align-items: center;
    max-width: 960px;
    width: 100%;
    align-self: center;
    flex-direction: column;
`;

const StyledClearSelection = styled.span`
  color: #93be38;
  cursor: pointer;
  &:hover {
    text-decoration: underline;
  }
`;

const StyledCheckbox = styled(Checkbox)`
    padding: 8px;
    &:hover {
    background: #e8e8e8;
    }
`;
//NOTE: test edge and IE targeting and transform
const MapMarkerText = styled.p`
    transform: translate(-10px, -920%);
    font-weight: bold;
    /* Firefox */
    @supports (-moz-appearance: none) {
    & {
      transform: translate(-10px, -850%);
      font-weight: bold;
      color: black;
    }
    }
    /* Microsoft IE10 and above */
    @media all and (-ms-high-contrast: none) {
    & {
        transform: translate(-10px, -850%);
        color: black;
    }
    }
    /* Microsoft Edge */
    @supports (-ms-ime-align: auto) {
    & {
        transform: translate(-10px, -850%);
        color: black;
    }
}   
`;

class CommitmentListContainer extends Component<{
    locale: LocaleType,
    articles: Array<ARTICLE_TYPE>,
    getArticles: ({
                      searchTerm: ?string,
                      categoryIds: Array<string>,
                      locale: LocaleType,
                      range: {
                          start: number,
                          end: number
                      },
                      sort: ?string
                  }) => void,
    getMainCategories: () => void,
    mainCategories: Array<CATEGORY_TYPE>,
    organizations: ORGANIZATION_STATE,
    location: *,
    getOrganizationSizes: () => void,
    getOrganizationTypes: () => void,
    getCategoriesInHierarchy: string => void,
    getCommitmentsPerCity: () => void,
    getCommitmentCountForAllTypes: () => void,
    match: any,
    reportListing?: boolean,
    greenDealCategories: Object,
    commitmentsPerCity: Object,
    commitmentCountForAllTypes: Array<Number>
},
    {
        searchTerm: ?string,
        sortCriteria: ?{
            value: ?string,
            name: ?string
        },
        selectedMainCategories: Array<string>,
        selectedOrganizationTypes: Array<string>,
        selectedOrganizationSubTypes: Array<string>,
        selectedOrganizationSizes: Array<string>,
        selectedCommitmentTypes: Array<string>,
        range: {
            start: number,
            end: number
        },
        mapHidden: boolean
    }> {
    state = {
        mapHidden: true,
        searchTerm: "",
        sortCriteria: {
            value: "latest",
            name: "Uusin"
        },
        selectedMainCategories: [],
        selectedOrganizationTypes: [],
        selectedOrganizationSubTypes: [],
        selectedOrganizationSizes: [],
        selectedCommitmentTypes: [],
        currentPageType: "",
        currentPageTypeName: "",
        range: {
            start: 0,
            end: 50
        }
    };

    constructor(props) {
        super(props);
        this.props.getCategoriesInHierarchy("33554");
        this.props.getCommitmentsPerCity();
    }

    componentDidMount() {
        const query = this.props.location && qs.parse(this.props.location.search);
        const commitmentType = query && query.commitmentType;
        const commitmentCat = query && query.category;
        this.setState({
            currentPageType: commitmentType,
            currentPageTypeName: commitmentCat
        });
        let categoryIds = commitmentType ? [commitmentType] : [];
        const reportCategoryId = this.props.reportListing ? "65646" : null;
        reportCategoryId && categoryIds.push(reportCategoryId);
        this.props.getOrganizationSizes();
        this.props.getOrganizationTypes();
        if (commitmentType)
            this.setState({
                selectedCommitmentTypes: categoryIds,
            });
        if (commitmentCat === "organizations")
            allTypes.forEach(type => {
                categoryIds.push(type);
            });
        if (commitmentCat === "henkilo")
            categoryIds.push(catPerson);
        this.props.getCommitmentCountForAllTypes();
        this.props.getArticles({
            searchTerm: "",
            categoryIds,
            locale: this.props.locale,
            range: this.state.range,
            sort: this.state.sortCriteria ? this.state.sortCriteria.value : null
        });
    }

    renderName = name => {
        if (!name) return "";
        if (name.endsWith("puuttuva tieto")) return "";
        const s = name.split(" ");
        if (s[0] === s[1]) return s[0];
        else return name;
    };

    handleFilterInput = ({target: {value}}) => {
        this.setState({
            searchTerm: value
        });
    };

    getArticles = (range = this.state.range) => {
        this.setState({
            range
        });
        const subTypes =
            this.state.selectedOrganizationSubTypes.length > 0
                ? this.state.selectedOrganizationSubTypes
                : [];
        const reportCategoryId = this.props.reportListing ? ["65646"] : [];

        const orgTypes =
            subTypes.length > 0
                ? this.state.selectedOrganizationTypes.filter(
                el => el !== catCompany.toString()
                )
                : this.state.selectedOrganizationTypes;

        this.props.getArticles({
            searchTerm: this.state.searchTerm,
            categoryIds: [
                ...this.state.selectedMainCategories,
                ...orgTypes,
                ...subTypes,
                ...this.state.selectedOrganizationSizes,
                ...this.state.selectedCommitmentTypes,
                ...reportCategoryId
            ],
            locale: window.Liferay.ThemeDisplay.getLanguageId() || "fi_FI",
            range,
            sort: this.state.sortCriteria ? this.state.sortCriteria.value : null
        });
    };

    handleCategorySelect = e => {
        const {name} = e.target;
        this.setState(
            {
                selectedMainCategories: this.state.selectedMainCategories.includes(name)
                    ? this.state.selectedMainCategories.filter(type => type !== name)
                    : [...this.state.selectedMainCategories, name]
            },
            () => this.getArticles()
        );
    };

    handleOrganizationTypeSelect = e => {
        const {name} = e.target;
        this.clearSelectedCommitmentTypes();
        let categoryIds = [this.state.currentPageType];
        if( this.state.selectedOrganizationTypes.includes(name) && this.state.selectedOrganizationTypes.length === 1) {
            allTypes.forEach(type => {
                categoryIds.push(type)
            });
            this.setState({ selectedCommitmentTypes: categoryIds })
        }
        this.setState(
            {
                selectedOrganizationTypes: this.state.selectedOrganizationTypes.includes(
                    name
                )
                    ? this.state.selectedOrganizationTypes.filter(type => type !== name)
                    : [...this.state.selectedOrganizationTypes, name]
            },
            () => this.getArticles()
        );
    };

    /* Types -> Tekijä (organizations or private persons) */
    clearSelectedCommitmentTypes() {
        this.setState(
            { selectedCommitmentTypes: [(this.state.currentPageType)] }
        );
    }

    getOrganizationSubTypes = e => {
        const subs = this.props.organizations.organizationSubTypes
            ? // $FlowFixMe
            flatMap(this.props.organizations.organizationSubTypes, null, key =>
                this.props.organizations.organizationSubTypes
                    ? this.props.organizations.organizationSubTypes[key]
                    : ""
            )
            : [];
        return subs;
    };

    handleOrganizationSizeSelect = e => {
        const {name} = e.target;
        this.setState(
            {
                selectedOrganizationSizes: this.state.selectedOrganizationSizes.includes(
                    name
                )
                    ? this.state.selectedOrganizationSizes.filter(type => type !== name)
                    : [...this.state.selectedOrganizationSizes, name]
            },
            () => this.getArticles()
        );
    };

    handleOrganizationSubTypeSelect = e => {
        const {name} = e.target;
        this.clearSelectedCommitmentTypes();
        let categoryIds = [this.state.currentPageType];
        if( this.state.selectedOrganizationSubTypes.includes(name) && this.state.selectedOrganizationSubTypes.length === 1) {
            allTypes.forEach(type => {
                categoryIds.push(type)
            });
            this.setState({ selectedCommitmentTypes: categoryIds })
        }
        this.setState(
            {
                selectedOrganizationSubTypes: this.state.selectedOrganizationSubTypes.includes(
                    name
                )
                    ? this.state.selectedOrganizationSubTypes.filter(
                        type => type !== name
                    )
                    : [...this.state.selectedOrganizationSubTypes, name]
            },
            () => this.getArticles()
        );
    };

    handleCommitmentTypeSelect = e => {
        const {name} = e.target;
        this.setState(
            {
                selectedCommitmentTypes: this.state.selectedCommitmentTypes.includes(
                    name
                )
                    ? this.state.selectedCommitmentTypes.filter(type => type !== name)
                    : [...this.state.selectedCommitmentTypes, name]
            },
            () => this.getArticles()
        );
    };

    clearFilters = () => {
        let categoryIds = [this.state.currentPageType];
        if (this.state.currentPageTypeName === "organizations")
            allTypes.forEach(type => {
                categoryIds.push(type);
            });
        if (this.state.currentPageTypeName === "henkilo")
            categoryIds.push(catPerson);
        this.setState(
            {
                searchTerm: "",
                selectedMainCategories: [],
                selectedOrganizationTypes: [],
                selectedOrganizationSubTypes: [],
                selectedOrganizationSizes: [],
                selectedCommitmentTypes: categoryIds,
                sortCriteria: {
                    value: "latest",
                    name: "Uusin"
                },
            },
            () => this.getArticles()
        );
    };

    parseGreenDealCategories = () => {
        let parsedGreenDeals = [];
        const unparsedGreenDeals =
            this.props.greenDealCategories && this.props.greenDealCategories.children
                ? this.props.greenDealCategories.children
                : [];
        unparsedGreenDeals.forEach(deal => {
            parsedGreenDeals.push({
                titleCurrentValue: deal.titleMap[this.props.locale],
                categoryId: deal.categoryId
            });
        });
        return parsedGreenDeals;
    };

    getPageHeader(){
        if(this.state.currentPageType === "33555"){
            return translate({textKey: "sit.commitmentList.ravitsemusSitoumukset" });
        } else if(this.state.currentPageTypeName === "organizations"){
            return translate({textKey: "sit.commitment.actionCommitment" });
        } else if(this.state.currentPageTypeName === "henkilo"){
            return (
              <Fragment>
                  <GoalIcon icon={'kestavat-elamantavat'} borderStyle={'solid'} style={{display: 'inline', verticalAlign: 'middle'}}/>
                  {translate({textKey: "sit.commitmentList.kestavatElamantavat" })}
              </Fragment>
            );
        } else if(this.state.currentPageTypeName === "green-deal"){
            return translate({textKey: "sit.commitment.greenDealCommitment" });
        }
    }
    render() {
        const greenDealCategories = this.parseGreenDealCategories();
        return (
            <Fragment>
                <Route
                    exact
                    path="/"
                    render={listProps => (
                        <Container style={{ flexDirection: 'column'}}>
                            <AlignCenter>
                                <Row style={{ justifyContent: 'space-around', width: '100%', padding: "30px 30px 0px 30px"}}>
                                    <Header style={{ width: '100%', textAlign: "left", borderBottom: "1px solid #dcdcdc", padding: "0 0 15px 0" }}>
                                        {this.getPageHeader()}
                                    </Header>
                                </Row>
                                <div style={{width: '100%', padding: "30px 30px 30px 30px"}}>
                                    <Row style={{ justifyContent: 'space-around', width: '100%', padding: "0 0 30px 0", borderBottom: "1px solid #dcdcdc"}}>
                                        <Container style={{ flexDirection: 'row', width: 300, alignItems: 'center' }}>
                                            <SearchBox
                                                id="commitment_listing_search_field"
                                                style={{
                                                    maxWidth: 300,
                                                    borderTop: "0px solid #ccc"
                                                }}
                                                placeholder={translate({
                                                    textKey: "sit.commitmentList.searchCommitment"
                                                })}
                                                type="text"
                                                name="searchCommitment"
                                                onChange={this.handleFilterInput}
                                                value={this.state.searchTerm}
                                                onKeyUp={e => {
                                                    if (e.key === "Enter") this.getArticles();
                                                }}
                                            />
                                            <i className="fa fa-search" style={{zIndex: 100, marginLeft: -25, color: '#ccc'}} />
                                        </Container>
                                        <Container style={{ flexDirection: 'column'}}>
                                            {/* Used in all views */}
                                            <Dropdown style={{textTransform: 'uppercase'}}
                                                height="42px"
                                                label={translate({
                                                    textKey: "sit.commitmentList.sort"
                                                }) + ": " +this.state.sortCriteria.name}
                                                items={[
                                                    {
                                                        label: translate({
                                                            textKey: "sit.commitmentList.latest"
                                                        }),
                                                        name: translate({
                                                            textKey: "sit.commitmentList.latest"
                                                        }),
                                                        value: "latest",
                                                        id: "latest"
                                                    },
                                                    {
                                                        label: translate({
                                                            textKey: "sit.commitmentList.oldest"
                                                        }),
                                                        name: translate({
                                                            textKey: "sit.commitmentList.oldest"
                                                        }),
                                                        value: "oldest",
                                                        id: "oldest"
                                                    },
                                                    {
                                                        label: translate({
                                                            textKey: "sit.commitmentList.alphabetAscending"
                                                        }),
                                                        value: "alphabet_asc",
                                                        name: translate({
                                                            textKey: "sit.commitmentList.alphabetAscending"
                                                        }),
                                                        id: "alphabet_asc"
                                                    },
                                                    {
                                                        label: translate({
                                                            textKey: "sit.commitmentList.priority"
                                                        }),
                                                        value: "priority",
                                                        name: translate({
                                                            textKey: "sit.commitmentList.priority"
                                                        }),
                                                        id: "priority"
                                                    }
                                                ]}
                                                render={({items, value}) => (
                                                    <RadioButtonContainer
                                                        style={{
                                                            minWidth: "160px",
                                                            border: 0,
                                                            minHeight: 36
                                                        }}
                                                    >
                                                        {items.map(item => (
                                                            <RadioButton
                                                                id={item.id}
                                                                defaultChecked={
                                                                    get(this.state, "sortCriteria.value") ===
                                                                    item.value
                                                                }
                                                                key={item.id}
                                                                label={item.label}
                                                                name="filterType"
                                                                value={item.name}
                                                                onChange={() => {
                                                                    this.setState(
                                                                        {
                                                                            sortCriteria: {
                                                                                value: item.value,
                                                                                name: item.name
                                                                            }
                                                                        },
                                                                        () => this.getArticles()
                                                                    );
                                                                }}
                                                            />
                                                        ))}
                                                    </RadioButtonContainer>
                                                )}
                                            />
                                            {/* Used company toimenpidesit. view */}
                                            {this.state.currentPageTypeName === "organizations" ?
                                                /* Toimiala */
                                                 <Dropdown
                                                    showValue={true}
                                                    disabled={false}
                                                    id="commitment_listing_organization_subtype"
                                                    height="42px"
                                                    style={{
                                                        visibility:
                                                            !this.state.currentPageTypeName === "organizations" && "hidden",
                                                        textTransform: 'uppercase'
                                                    }}
                                                    items={this.getOrganizationSubTypes()}
                                                    label={translate({
                                                        textKey: "sit.profile.lineOfBusiness"
                                                    })}
                                                    internalPadding="0 0 0 0"
                                                    render={({items}) =>
                                                        items.map(item =>
                                                            (
                                                                <StyledCheckbox
                                                                    id={`commitment_listing_organization_subtype_${item.categoryId}`}
                                                                    name={item.categoryId}
                                                                    onChange={this.handleOrganizationSubTypeSelect}
                                                                    checked={this.state.selectedOrganizationSubTypes.includes(
                                                                        item.categoryId
                                                                    )}
                                                                    height="52px"
                                                                    style={{border: 0}}
                                                                    label={item.titleCurrentValue}
                                                                    key={item.uuid || item.id}
                                                                />
                                                            )
                                                        )
                                                    }
                                                />
                                                : ""
                                            }
                                        </Container>

                                        <Container style={{ flexDirection: 'column'}}>
                                            { this.state.currentPageTypeName === "organizations" ?
                                                /* Päätavoitteet */
                                                <Dropdown style={{textTransform: 'uppercase'}}
                                                    showValue={false}
                                                    id="commitment_listing_primary_goal_dropdown"
                                                    height="42px"
                                                    width="360px"
                                                    items={this.props.mainCategories}
                                                    label={translate({
                                                        textKey: "sit.commitment.mainGoals"
                                                    })}
                                                    internalPadding="0 0 0 0"
                                                    render={({items}) =>
                                                        items.map(item =>
                                                            (
                                                                <StyledCheckbox
                                                                    id={`commitment_listing_primary_goal_${item.categoryId}`}
                                                                    name={item.categoryId}
                                                                    onChange={this.handleCategorySelect}
                                                                    checked={this.state.selectedMainCategories.includes(
                                                                        item.categoryId
                                                                    )}
                                                                    height="52px"
                                                                    style={{border: 0}}
                                                                    label={item.titleCurrentValue}
                                                                    key={item.uuid || item.id}
                                                                />
                                                            ))
                                                    }
                                                />
                                                : ""
                                            }
                                            { this.state.currentPageTypeName === "organizations" || this.state.currentPageType === "33555" ?
                                                /* Tekijä */
                                                <Dropdown style={{textTransform: 'uppercase'}}
                                                    showValue={false}
                                                    id="commitment_listing_organization_type_dropdown"
                                                    height="42px"
                                                    items={get(
                                                        this.props.organizations,
                                                        "organizationTypes",
                                                        []
                                                    )}
                                                    label={translate({
                                                        textKey: "sit.commitmentList.creator"
                                                    })}
                                                    internalPadding="0 0 0 0"
                                                    render={({ items }) =>
                                                        items.map(item =>
                                                            item.categoryId !== catPerson.toString() ?
                                                            (
                                                                <StyledCheckbox
                                                                    id={`commitment_listing_organization_type_${item.categoryId}`}
                                                                    name={item.categoryId}
                                                                    onChange={this.handleOrganizationTypeSelect}
                                                                    checked={this.state.selectedOrganizationTypes.includes(item.categoryId)}
                                                                    height="52px"
                                                                    style={{ border: 0 }}
                                                                    label={item.titleCurrentValue}
                                                                    key={item.uuid || item.id}
                                                                />
                                                            )
                                                            :console.log(item.categoryId)
                                                        )
                                                    }
                                                />
                                                : ""
                                            }
                                            { this.state.currentPageTypeName === "green-deal" ?
                                                <Dropdown style={{textTransform: 'uppercase'}}
                                                    showValue={false}
                                                    id="commitment_listing_greendeal_type_dropdown"
                                                    height="54px"
                                                    style={{ width: "25%" }}
                                                    items={greenDealCategories}
                                                    label={translate({
                                                        textKey:
                                                            "sit.commitmentList.greenDeal.searchGreendealCommitments"
                                                    })}
                                                    internalPadding="0 0 0 0"
                                                    render={({ items }) =>
                                                        items.map(item => (
                                                            <StyledCheckbox
                                                                id={`commitment_listing_greendeal_type_${item.categoryId}`}
                                                                name={item.categoryId}
                                                                onChange={this.handleCommitmentTypeSelect}
                                                                checked={this.state.selectedCommitmentTypes.includes(
                                                                    item.categoryId
                                                                )}
                                                                height="52px"
                                                                style={{ border: 0 }}
                                                                label={item.titleCurrentValue}
                                                                key={item.categoryId}
                                                            />
                                                        ))
                                                    }
                                                />
                                                : ""
                                            }
                                        </Container>
                                        <Container style={{ flexDirection: 'column'}}>
                                            <JustifyRight>
                                                <StyledClearSelection
                                                    onClick={this.clearFilters}
                                                    id="commitment_listing_clear_filters"
                                                >
                                                    {translate({
                                                        textKey: "sit.commitmentList.clearFilters"
                                                    })}
                                                </StyledClearSelection>
                                            </JustifyRight>
                                        </Container>
                                    </Row>
                                </div>
                            </AlignCenter>


                            <AlignCenter style={{padding: 0, flex: "1 1 auto"}}>

                                <WhiteSpace height="40px"/>

                                <CommitmentList
                                    renderName={this.renderName}
                                    articles={this.props.articles}
                                    reportListing={this.props.reportListing}
                                    pageType={this.state.currentPageType}
                                    pageTypeName={this.state.currentPageTypeName}
                                    getMoreArticles={() => {
                                        this.getArticles({
                                            start: this.state.range.start,
                                            end: this.state.range.end + 50
                                        });
                                    }}
                                    stats={this.props.commitmentCountForAllTypes}
                                />
                            </AlignCenter>
                        </Container>
                    )}
                />

                <Route
                    path={this.props.match.path + "/details/:articleId"}
                    render={routeProps => (
                        <Commitment renderName={this.renderName} {...routeProps} />
                    )}
                />

                {/* <Route exact path="/" render={() => <Redirect to="/list" />} /> */}
            </Fragment>
        );
    }
}

export default withRouter(
    connect(
        (state: STORE_STATE) => ({
            articles: toArray(state.articles.articles),
            mainCategories: toArray(state.articles.mainCategories),
            organizations: state.organizations,
            locale: state.user.locale || "fi_FI",
            greenDealCategories: state.articles.fetchedCategoryHierarchy,
            commitmentsPerCity: state.articles.commitmentsPerCity,
            commitmentCountForAllTypes: state.articles.commitmentCountForAllTypes
        }),
        {
            ...pick(
                ArticleActions,
                "getArticles",
                "getMainCategories",
                "getCategoriesInHierarchy",
                "getCommitmentsPerCity",
                "getCommitmentCountForAllTypes"
            ),
            ...pick(
                OrganizationActions,
                "getOrganizationSizes",
                "getOrganizationTypes"
            )
        }
    )(CommitmentListContainer)
);
