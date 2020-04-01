// @flow

import type {
  CommitmentType,
  OperationType,
  CATEGORY_TYPE,
  PROFILE_TYPE,
  ORGANIZATION_TYPE
} from "../../constants/types";

import React, { Component } from "react";
import { get } from "lodash";

import { vocCommitmentType } from "../../constants/constants";
import LiferayClient from "../../utils/LiferayClient";

import SingleCommitment from "./SingleCommitment";

class JoinCommitment extends Component<
  {
    commitment: CommitmentType,
    match: any,
    mainCategories: Array<CATEGORY_TYPE>,
    mainCategoryProperties: Array<CATEGORY_TYPE>,
    renderName: string => string,
    secondaryCategories: Array<CATEGORY_TYPE>,
    secondaryCategoryProperties: Array<CATEGORY_TYPE>,
    addLikeToCommitment: () => void
  },
  {
    selectedOperations: Array<OperationType>,
    commitmentTypes: Array<CATEGORY_TYPE>,
    selectedCreator: ?PROFILE_TYPE | ?ORGANIZATION_TYPE,
    nutritionCommitmentContentAreas: Array<CATEGORY_TYPE>
  }
> {
  state = {
    selectedOperations: [],
    commitmentTypes: [],
    selectedCreator: null,
    nutritionCommitmentContentAreas: []
  };

  async componentDidMount() {
    const commitmentTypes = await LiferayClient(
      "/assetcategory/get-vocabulary-categories",
      {
        vocabularyId: vocCommitmentType,
        start: 0,
        end: 1000,
        obc: null
      }
    );

    const mainTypes = commitmentTypes.filter(t => t.parentCategoryId === "0");

    const contentAreas = [];
    if (this.props.commitment.commitmentType === "NUTRITION") {
      /* Nämä haetaan jotta voidaan tarkistaa mikä näistä on valittuna sitoumuksen kategoriaId:eissä */
      const contentAreasResponse = await LiferayClient(
        "/assetcategory/get-vocabulary-categories",
        {
          parentCategoryId: 33555,
          vocabularyId: 33552,
          start: 0,
          end: 1000,
          obc: null
        }
      );
      if (contentAreasResponse) contentAreas.push(...contentAreasResponse);
    }

    this.setState({
      commitmentTypes: mainTypes,
      nutritionCommitmentContentAreas: contentAreas
    });
  }

  selectOperation = (_op: OperationType) => {
    const op = {
      ..._op,
      meters: []
    };
    const isSelected = this.state.selectedOperations.some(oper => {
      return oper.operationId === op.operationId;
    });

    if (!isSelected) {
      this.setState({
        selectedOperations: this.state.selectedOperations.concat(op)
      });
    } else {
      this.setState({
        selectedOperations: this.state.selectedOperations.filter(
          oper => oper.operationId !== op.operationId
        )
      });
    }
  };

  render() {
    const creatorOrganizationTypeId = get(
      this.state,
      "selectedCreator.categories.organizationType.categoryId",
      ""
    );

    const creatorOrganizationId = get(
      this.state,
      "selectedCreator.organizationId",
      ""
    );

    const nutrionContentAreaIds = this.state.nutritionCommitmentContentAreas.map(
      area => Number(area.categoryId)
    );

    const contentArea = this.props.commitment.categoryIds.find(id => {
      return nutrionContentAreaIds.includes(id);
    });

    const commitmentData = {
      operations: this.state.selectedOperations,
      categoryIds: contentArea ? [contentArea] : []
    };

    const commitmentTypeIds = this.state.commitmentTypes.map(commType =>
      Number(commType.categoryId)
    );

    const commitmentType = this.props.commitment.categoryIds.find(id => {
      return commitmentTypeIds.includes(id);
    });

    return (
      <SingleCommitment
        {...this.props}
        selectOperation={this.selectOperation}
        selectedOperations={this.state.selectedOperations}
        selectedCreator={this.state.selectedCreator}
        selectCreator={val =>
          this.setState({ selectedCreator: val ? JSON.parse(val) : null })
        }
        joinCommitmentMode={true}
        creatorOrganizationId={creatorOrganizationId}
        creatorOrganizationTypeId={creatorOrganizationTypeId}
        commitmentData={commitmentData}
        commitmentType={commitmentType}
        // contentArea={contentArea}
      />
    );
  }
}

export default JoinCommitment;
