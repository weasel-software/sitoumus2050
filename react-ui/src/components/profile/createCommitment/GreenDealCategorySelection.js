// @flow

import React, { Component } from "react";
import translate from "../../reusable/translate";
import Button from "../../reusable/Button";
import { ArticleActions } from "../../../redux/articles";
import { connect } from "react-redux";
import produce from "immer";
import { get, isEmpty } from "lodash";

type Props = {
  setGreendealCategorySelected: boolean => void,
  setGreenDealMetaData: (number, number, number) => void,
  getCategoriesInHierarchy: string => void,
  fetchedCategoryHierarchy: Object,
  locale: "fi_FI" | "en_US"
};

type State = {
  continueDisabled: boolean,
  subCategories: Array<any>,
  selectedMainCategory: any,
  selectedSubCategory: any,
  mainGoal: number
};

const mapStateToProps = state => {
  return {
    fetchedCategoryHierarchy: state.articles.fetchedCategoryHierarchy
  };
};

// $FlowFixMe
const mapDispatchToProps = dispatch => {
  return {
    getCategoriesInHierarchy: () =>
      dispatch(ArticleActions.getCategoriesInHierarchy("33554"))
  };
};

class GreenDealCategorySelection extends Component<Props, State> {
  constructor(props) {
    super(props);
    this.state = {
      mainGoal: 0,
      continueDisabled: true,
      subCategories: [],
      selectedMainCategory: "",
      selectedSubCategory: ""
    };
    this.props.getCategoriesInHierarchy("33554");
  }

  onMainCategoryChange = e => {
    const greendealCategory = this.props.fetchedCategoryHierarchy;
    const mainCategoryName = get(e, "target.value");
    const correspondingMainCategory = greendealCategory.children.find(
      mainCategory => {
        return (
          mainCategory.titleMap[this.props.locale] === String(mainCategoryName)
        );
      }
    );
    this.setState(
      // $FlowFixMe
      produce(draft => {
        draft.mainGoal =
          correspondingMainCategory && correspondingMainCategory.properties
            ? correspondingMainCategory.properties.mainGoal
            : null;
        draft.subCategories =
          correspondingMainCategory && correspondingMainCategory.children
            ? correspondingMainCategory.children
            : [];
        draft.selectedMainCategory = correspondingMainCategory
          ? correspondingMainCategory
          : {};
        draft.continueDisabled =
          correspondingMainCategory &&
          correspondingMainCategory.children &&
          correspondingMainCategory.children.length === 0
            ? false
            : true;
      })
    );
  };

  onSubCategoryChange = e => {
    const { selectedMainCategory, subCategories } = this.state;
    const subCategoryName = get(e, "target.value");
    const correspondingSubCategory = subCategories
      ? subCategories.find(subCategory => {
          return (
            subCategory.titleMap[this.props.locale] === String(subCategoryName)
          );
        })
      : null;

    this.setState(
      // $FlowFixMe
      produce(draft => {
        draft.selectedSubCategory = correspondingSubCategory
          ? correspondingSubCategory
          : {};
        draft.continueDisabled =
          !isEmpty(selectedMainCategory) && correspondingSubCategory
            ? false
            : true;
      })
    );
  };

  render() {
    const greendealCategory = this.props.fetchedCategoryHierarchy;
    const { locale } = this.props;
    return (
      <div>
        <div style={{ display: "flex", justifyContent: "space-between" }}>
          <Button
            style={{
              backgroundColor: "transparent",
              color: "#93be38",
              border: "1px solid #93be38",
              maxWidth: "20%",
              minWidth: "180px"
            }}
          >
            {translate({
              textKey: "sit.commitment.greenDeal.categorySelect.backButtonText"
            })}
          </Button>
          <h2>
            {translate({
              languageOverride: locale,
              textKey:
                "sit.commitment.greenDeal.categorySelect.commitmentTypeTitle"
            })}
          </h2>
        </div>
        <hr />
        <div style={{ paddingTop: "1%", maxWidth: "80%", margin: "auto" }}>
          <div>
            <h2>
              {translate({
                languageOverride: locale,
                textKey: "sit.commitment.greenDeal.categorySelect.titleText"
              })}
            </h2>
            <select
              style={{
                height: "48px",
                width: "100%",
                wordWrap: true,
                marginTop: "20px"
              }}
              onChange={e => {
                this.onMainCategoryChange(e);
              }}
            >
              <option>
                {translate({
                  languageOverride: locale,
                  textKey:
                    "sit.commitment.greenDeal.categorySelect.firstLevelDefaultText"
                })}
              </option>
              {greendealCategory &&
                greendealCategory.children.map(category => (
                  <option key={category.categoryId}>
                    {category.titleMap[locale]}
                  </option>
                ))}
            </select>
            <select
              style={{
                height: "48px",
                width: "100%",
                wordWrap: true,
                marginTop: "20px"
              }}
              onChange={e => {
                this.onSubCategoryChange(e);
              }}
            >
              <option>
                {translate({
                  languageOverride: locale,
                  textKey:
                    "sit.commitment.greenDeal.categorySelect.secondLevelDefaultText"
                })}
              </option>
              {this.state.subCategories.map(category => (
                <option key={category.categoryId}>
                  {category.titleMap[locale]}
                </option>
              ))}
            </select>
            <div style={{ float: "right", marginTop: "50px" }}>
              <Button
                onClick={() => {
                  this.props.setGreendealCategorySelected(true);
                  this.props.setGreenDealMetaData(
                    Number(this.state.mainGoal),
                    Number(
                      this.state.selectedMainCategory.properties
                        .operationTemplatesFolder
                    ),
                    Number(
                      this.state.selectedMainCategory.properties.ownCategoryId
                    )
                  );
                }}
                disabled={this.state.continueDisabled}
              >
                {translate({
                  textKey:
                    "sit.commitment.greenDeal.categorySelect.continueButtonText"
                })}
              </Button>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(GreenDealCategorySelection);
//export default GreenDealCategorySelection;
