/*******************************************************************************
 * Copyright © 2015 Hoffmann-La Roche
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.emc.d2fs.schemas.models;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import com.emc.d2fs.models.taxonomy_operation.GetTaxonomieContentRequest;
import com.emc.d2fs.models.taxonomy_operation.GetTaxonomieContentResponse;
import com.emc.d2fs.services.acrobat_annotate_service.GetAcrobatAnnotateURLRequest;
import com.emc.d2fs.services.acrobat_annotate_service.GetAcrobatAnnotateURLResponse;
import com.emc.d2fs.services.action_service.HandlePluginActionRequest;
import com.emc.d2fs.services.action_service.HandlePluginActionResponse;
import com.emc.d2fs.services.annotation_service.GetContentIdAndNativeAnnotationIdsRequest;
import com.emc.d2fs.services.annotation_service.GetContentIdAndNativeAnnotationIdsResponse;
import com.emc.d2fs.services.annotation_service.GetNativeAnnotationRequest;
import com.emc.d2fs.services.annotation_service.GetNativeAnnotationResponse;
import com.emc.d2fs.services.annotation_service.GetUserNativeAnnotationCheckoutIdsRequest;
import com.emc.d2fs.services.annotation_service.GetUserNativeAnnotationCheckoutIdsResponse;
import com.emc.d2fs.services.annotation_service.TestComparisonDirectionRequest;
import com.emc.d2fs.services.annotation_service.TestComparisonDirectionResponse;
import com.emc.d2fs.services.browser_service.GetBrowserContentRequest;
import com.emc.d2fs.services.browser_service.GetBrowserContentResponse;
import com.emc.d2fs.services.browser_service.GetBrowserFacetContentRequest;
import com.emc.d2fs.services.browser_service.GetBrowserFacetContentResponse;
import com.emc.d2fs.services.centerstage_service.AddCenterstageCommentRequest;
import com.emc.d2fs.services.centerstage_service.AddCenterstageCommentResponse;
import com.emc.d2fs.services.centerstage_service.GetCenterstageCommentsRequest;
import com.emc.d2fs.services.centerstage_service.GetCenterstageCommentsResponse;
import com.emc.d2fs.services.checkin_service.CheckinRequest;
import com.emc.d2fs.services.checkin_service.CheckinResponse;
import com.emc.d2fs.services.checkin_service.GetCheckinConfigRequest;
import com.emc.d2fs.services.checkin_service.GetCheckinConfigResponse;
import com.emc.d2fs.services.checkout_service.CancelCheckoutAllRequest;
import com.emc.d2fs.services.checkout_service.CancelCheckoutAllResponse;
import com.emc.d2fs.services.checkout_service.CancelCheckoutRequest;
import com.emc.d2fs.services.checkout_service.CancelCheckoutResponse;
import com.emc.d2fs.services.checkout_service.CheckoutAsNewRequest;
import com.emc.d2fs.services.checkout_service.CheckoutAsNewResponse;
import com.emc.d2fs.services.checkout_service.CheckoutRequest;
import com.emc.d2fs.services.checkout_service.CheckoutResponse;
import com.emc.d2fs.services.checkout_service.GetCheckoutCountRequest;
import com.emc.d2fs.services.checkout_service.GetCheckoutCountResponse;
import com.emc.d2fs.services.checkout_service.TestCheckoutRequest;
import com.emc.d2fs.services.checkout_service.TestCheckoutResponse;
import com.emc.d2fs.services.checkout_service.TestControlledPrintRequest;
import com.emc.d2fs.services.checkout_service.TestControlledPrintResponse;
import com.emc.d2fs.services.command_service.IsCommandAllowedRequest;
import com.emc.d2fs.services.command_service.IsCommandAllowedResponse;
import com.emc.d2fs.services.content_service.GetContentRequest;
import com.emc.d2fs.services.content_service.GetContentResponse;
import com.emc.d2fs.services.content_service.GetDQLContentRequest;
import com.emc.d2fs.services.content_service.GetDQLContentResponse;
import com.emc.d2fs.services.content_service.GetDoclistFilterOptionsRequest;
import com.emc.d2fs.services.content_service.GetDoclistFilterOptionsResponse;
import com.emc.d2fs.services.content_service.GetFacetContentRequest;
import com.emc.d2fs.services.content_service.GetFacetContentResponse;
import com.emc.d2fs.services.content_service.GetFilteredContentRequest;
import com.emc.d2fs.services.content_service.GetFilteredContentResponse;
import com.emc.d2fs.services.content_service.GetMultiContentRequest;
import com.emc.d2fs.services.content_service.GetMultiContentResponse;
import com.emc.d2fs.services.creation_service.CreatePropertiesRequest;
import com.emc.d2fs.services.creation_service.CreatePropertiesResponse;
import com.emc.d2fs.services.creation_service.GetUIMaxSizeRequest;
import com.emc.d2fs.services.creation_service.GetUIMaxSizeResponse;
import com.emc.d2fs.services.creation_service.HasAttachmentsRequest;
import com.emc.d2fs.services.creation_service.HasAttachmentsResponse;
import com.emc.d2fs.services.creation_service.IsAFolderOrACabinetRequest;
import com.emc.d2fs.services.creation_service.IsAFolderOrACabinetResponse;
import com.emc.d2fs.services.creation_service.IsNoCreationProfileRequest;
import com.emc.d2fs.services.creation_service.IsNoCreationProfileResponse;
import com.emc.d2fs.services.creation_service.RemoveAttachmentsRequest;
import com.emc.d2fs.services.creation_service.RemoveAttachmentsResponse;
import com.emc.d2fs.services.d2_apply_config_service.ApplyConfigRequest;
import com.emc.d2fs.services.d2_apply_config_service.ApplyConfigResponse;
import com.emc.d2fs.services.d2_archive_service.CreateVersionRequest;
import com.emc.d2fs.services.d2_archive_service.CreateVersionResponse;
import com.emc.d2fs.services.d2_archive_service.ListArchivesRequest;
import com.emc.d2fs.services.d2_archive_service.ListArchivesResponse;
import com.emc.d2fs.services.d2_archive_service.TestExistsRequest;
import com.emc.d2fs.services.d2_archive_service.TestExistsResponse;
import com.emc.d2fs.services.d2_audit_service.ApplyAuditMessagesRequest;
import com.emc.d2fs.services.d2_audit_service.ApplyAuditMessagesResponse;
import com.emc.d2fs.services.d2_audit_service.IsEventAuditedRequest;
import com.emc.d2fs.services.d2_audit_service.IsEventAuditedResponse;
import com.emc.d2fs.services.d2_audit_service.IsPropertyAuditedRequest;
import com.emc.d2fs.services.d2_audit_service.IsPropertyAuditedResponse;
import com.emc.d2fs.services.d2_cache_service.RefreshDictionaryCacheRequest;
import com.emc.d2fs.services.d2_cache_service.RefreshDictionaryCacheResponse;
import com.emc.d2fs.services.d2_cache_service.RefreshTaxonomyCacheRequest;
import com.emc.d2fs.services.d2_cache_service.RefreshTaxonomyCacheResponse;
import com.emc.d2fs.services.d2_dictionary_service.DictionaryExportRequest;
import com.emc.d2fs.services.d2_dictionary_service.DictionaryExportResponse;
import com.emc.d2fs.services.d2_dictionary_service.DictionaryImportRequest;
import com.emc.d2fs.services.d2_dictionary_service.DictionaryImportResponse;
import com.emc.d2fs.services.d2_dictionary_service.DictionaryOperationRequest;
import com.emc.d2fs.services.d2_dictionary_service.DictionaryOperationResponse;
import com.emc.d2fs.services.d2_dictionary_service.GetDictionaryNumberValueRequest;
import com.emc.d2fs.services.d2_dictionary_service.GetDictionaryNumberValueResponse;
import com.emc.d2fs.services.d2_dictionary_service.GetExportDictionaryUrlRequest;
import com.emc.d2fs.services.d2_dictionary_service.GetExportDictionaryUrlResponse;
import com.emc.d2fs.services.d2_dictionary_service.GetImportDictionaryUrlRequest;
import com.emc.d2fs.services.d2_dictionary_service.GetImportDictionaryUrlResponse;
import com.emc.d2fs.services.d2_dictionary_service.ImportVersionDictionaryRequest;
import com.emc.d2fs.services.d2_dictionary_service.ImportVersionDictionaryResponse;
import com.emc.d2fs.services.d2_dictionary_service.RequestDocbaseUpdateRequest;
import com.emc.d2fs.services.d2_dictionary_service.RequestDocbaseUpdateResponse;
import com.emc.d2fs.services.d2_dictionary_service.SaveVersionDictionaryRequest;
import com.emc.d2fs.services.d2_dictionary_service.SaveVersionDictionaryResponse;
import com.emc.d2fs.services.d2_link_service.GetLinkStatesRequest;
import com.emc.d2fs.services.d2_link_service.GetLinkStatesResponse;
import com.emc.d2fs.services.d2_link_service.ModifyLinkRequest;
import com.emc.d2fs.services.d2_link_service.ModifyLinkResponse;
import com.emc.d2fs.services.d2_linked_document_service.GetLinkInfoRequest;
import com.emc.d2fs.services.d2_linked_document_service.GetLinkInfoResponse;
import com.emc.d2fs.services.d2_linked_document_service.GetLinksCountRequest;
import com.emc.d2fs.services.d2_linked_document_service.GetLinksCountResponse;
import com.emc.d2fs.services.d2_massupdate_service.ApplyConfigurationsRequest;
import com.emc.d2fs.services.d2_massupdate_service.ApplyConfigurationsResponse;
import com.emc.d2fs.services.d2_massupdate_service.CanDisplayModeDialogRequest;
import com.emc.d2fs.services.d2_massupdate_service.CanDisplayModeDialogResponse;
import com.emc.d2fs.services.d2_massupdate_service.GetMassUpdateInformationsRequest;
import com.emc.d2fs.services.d2_massupdate_service.GetMassUpdateInformationsResponse;
import com.emc.d2fs.services.d2_massupdate_service.ListConfigurationsRequest;
import com.emc.d2fs.services.d2_massupdate_service.ListConfigurationsResponse;
import com.emc.d2fs.services.d2_method_service.ExecuteMethodRequest;
import com.emc.d2fs.services.d2_method_service.ExecuteMethodResponse;
import com.emc.d2fs.services.d2_pdfrender_service.QueuePdfRenderRequest;
import com.emc.d2fs.services.d2_pdfrender_service.QueuePdfRenderResponse;
import com.emc.d2fs.services.d2_pdfrender_service.TestPdfRenderRequest;
import com.emc.d2fs.services.d2_pdfrender_service.TestPdfRenderResponse;
import com.emc.d2fs.services.d2_relation_service.ChildIdRequest;
import com.emc.d2fs.services.d2_relation_service.ChildIdResponse;
import com.emc.d2fs.services.d2_relation_service.ChildrenIdRequest;
import com.emc.d2fs.services.d2_relation_service.ChildrenIdResponse;
import com.emc.d2fs.services.d2_relation_service.ChildrenRelationRequest;
import com.emc.d2fs.services.d2_relation_service.ChildrenRelationResponse;
import com.emc.d2fs.services.d2_relation_service.CreateRelationRequest;
import com.emc.d2fs.services.d2_relation_service.CreateRelationResponse;
import com.emc.d2fs.services.d2_relation_service.DestroyRelationRequest;
import com.emc.d2fs.services.d2_relation_service.DestroyRelationResponse;
import com.emc.d2fs.services.d2_relation_service.ParentIdRequest;
import com.emc.d2fs.services.d2_relation_service.ParentIdResponse;
import com.emc.d2fs.services.d2_relation_service.ParentsRelationRequest;
import com.emc.d2fs.services.d2_relation_service.ParentsRelationResponse;
import com.emc.d2fs.services.d2_taxonomy_service.GetExportTaxonomyUrlRequest;
import com.emc.d2fs.services.d2_taxonomy_service.GetExportTaxonomyUrlResponse;
import com.emc.d2fs.services.d2_taxonomy_service.GetImportTaxonomyUrlRequest;
import com.emc.d2fs.services.d2_taxonomy_service.GetImportTaxonomyUrlResponse;
import com.emc.d2fs.services.d2_taxonomy_service.ImportVersionTaxonomyRequest;
import com.emc.d2fs.services.d2_taxonomy_service.ImportVersionTaxonomyResponse;
import com.emc.d2fs.services.d2_taxonomy_service.SaveTaxonomyRequest;
import com.emc.d2fs.services.d2_taxonomy_service.SaveTaxonomyResponse;
import com.emc.d2fs.services.d2_taxonomy_service.SaveVersionTaxonomyRequest;
import com.emc.d2fs.services.d2_taxonomy_service.SaveVersionTaxonomyResponse;
import com.emc.d2fs.services.d2_taxonomy_service.TaxonomyExportRequest;
import com.emc.d2fs.services.d2_taxonomy_service.TaxonomyExportResponse;
import com.emc.d2fs.services.d2_taxonomy_service.TaxonomyImportRequest;
import com.emc.d2fs.services.d2_taxonomy_service.TaxonomyImportResponse;
import com.emc.d2fs.services.d2_taxonomy_service.TaxonomyOperationRequest;
import com.emc.d2fs.services.d2_taxonomy_service.TaxonomyOperationResponse;
import com.emc.d2fs.services.d2_validation_service.CheckAttributesConstraintsRequest;
import com.emc.d2fs.services.d2_validation_service.CheckAttributesConstraintsResponse;
import com.emc.d2fs.services.d2_validation_service.CheckObjectConstraintsRequest;
import com.emc.d2fs.services.d2_validation_service.CheckObjectConstraintsResponse;
import com.emc.d2fs.services.d2_validation_service.CheckUniquenessRequest;
import com.emc.d2fs.services.d2_validation_service.CheckUniquenessResponse;
import com.emc.d2fs.services.d2_vd_template_service.GetVdTemplatesRequest;
import com.emc.d2fs.services.d2_vd_template_service.GetVdTemplatesResponse;
import com.emc.d2fs.services.d2_vd_template_service.SetVdTemplateRequest;
import com.emc.d2fs.services.d2_vd_template_service.SetVdTemplateResponse;
import com.emc.d2fs.services.d2lifecycle_service.ApplyRequest;
import com.emc.d2fs.services.d2lifecycle_service.ApplyResponse;
import com.emc.d2fs.services.d2lifecycle_service.ChangeStateOnEventRequest;
import com.emc.d2fs.services.d2lifecycle_service.ChangeStateOnEventResponse;
import com.emc.d2fs.services.d2lifecycle_service.ChangeStateRequest;
import com.emc.d2fs.services.d2lifecycle_service.ChangeStateResponse;
import com.emc.d2fs.services.d2lifecycle_service.NextStateOnEventRequest;
import com.emc.d2fs.services.d2lifecycle_service.NextStateOnEventResponse;
import com.emc.d2fs.services.d2lifecycle_service.NextStatesRequest;
import com.emc.d2fs.services.d2lifecycle_service.NextStatesResponse;
import com.emc.d2fs.services.d2lifecycle_service.TestChangeStateConditionsRequest;
import com.emc.d2fs.services.d2lifecycle_service.TestChangeStateConditionsResponse;
import com.emc.d2fs.services.d2lifecycle_service.TestNextStateRequest;
import com.emc.d2fs.services.d2lifecycle_service.TestNextStateResponse;
import com.emc.d2fs.services.d2lifecycle_service.TestTransitionRequest;
import com.emc.d2fs.services.d2lifecycle_service.TestTransitionResponse;
import com.emc.d2fs.services.dctmlifecycle_service.HandleOperationRequest;
import com.emc.d2fs.services.dctmlifecycle_service.HandleOperationResponse;
import com.emc.d2fs.services.destroy_service.DestroyAllRequest;
import com.emc.d2fs.services.destroy_service.DestroyAllResponse;
import com.emc.d2fs.services.destroy_service.DestroyRequest;
import com.emc.d2fs.services.destroy_service.DestroyResponse;
import com.emc.d2fs.services.destroy_service.IsDialogRequiredRequest;
import com.emc.d2fs.services.destroy_service.IsDialogRequiredResponse;
import com.emc.d2fs.services.details_service.GetDetailContentRequest;
import com.emc.d2fs.services.details_service.GetDetailContentResponse;
import com.emc.d2fs.services.dialog_service.CancelDialogRequest;
import com.emc.d2fs.services.dialog_service.CancelDialogResponse;
import com.emc.d2fs.services.dialog_service.GetDialogRequest;
import com.emc.d2fs.services.dialog_service.GetDialogResponse;
import com.emc.d2fs.services.dialog_service.GetExportValuesUrlRequest;
import com.emc.d2fs.services.dialog_service.GetExportValuesUrlResponse;
import com.emc.d2fs.services.dialog_service.GetImportValuesUrlRequest;
import com.emc.d2fs.services.dialog_service.GetImportValuesUrlResponse;
import com.emc.d2fs.services.dialog_service.GetLabelsRequest;
import com.emc.d2fs.services.dialog_service.GetLabelsResponse;
import com.emc.d2fs.services.dialog_service.GetOptionsRequest;
import com.emc.d2fs.services.dialog_service.GetOptionsResponse;
import com.emc.d2fs.services.dialog_service.GetTaxonomyRequest;
import com.emc.d2fs.services.dialog_service.GetTaxonomyResponse;
import com.emc.d2fs.services.dialog_service.IsMemberOfGroupRequest;
import com.emc.d2fs.services.dialog_service.IsMemberOfGroupResponse;
import com.emc.d2fs.services.dialog_service.ValidDialogRequest;
import com.emc.d2fs.services.dialog_service.ValidDialogResponse;
import com.emc.d2fs.services.distribution_service.GetDistributionContentRequest;
import com.emc.d2fs.services.distribution_service.GetDistributionContentResponse;
import com.emc.d2fs.services.distribution_service.GetReportFileNameRequest;
import com.emc.d2fs.services.distribution_service.GetReportFileNameResponse;
import com.emc.d2fs.services.distribution_service.HandleDistributionActionRequest;
import com.emc.d2fs.services.distribution_service.HandleDistributionActionResponse;
import com.emc.d2fs.services.distribution_service.LaunchDistributionRequest;
import com.emc.d2fs.services.distribution_service.LaunchDistributionResponse;
import com.emc.d2fs.services.distribution_service.StopDistributionRequest;
import com.emc.d2fs.services.distribution_service.StopDistributionResponse;
import com.emc.d2fs.services.download_service.CanPrintControlledViewRequest;
import com.emc.d2fs.services.download_service.CanPrintControlledViewResponse;
import com.emc.d2fs.services.download_service.GetCheckinUrlsRequest;
import com.emc.d2fs.services.download_service.GetCheckinUrlsResponse;
import com.emc.d2fs.services.download_service.GetDefaultDfsServerInfoRequest;
import com.emc.d2fs.services.download_service.GetDefaultDfsServerInfoResponse;
import com.emc.d2fs.services.download_service.GetDownloadUrlsRequest;
import com.emc.d2fs.services.download_service.GetDownloadUrlsResponse;
import com.emc.d2fs.services.download_service.GetExtractUrlsRequest;
import com.emc.d2fs.services.download_service.GetExtractUrlsResponse;
import com.emc.d2fs.services.download_service.GetFileInfoRequest;
import com.emc.d2fs.services.download_service.GetFileInfoResponse;
import com.emc.d2fs.services.download_service.GetUploadUrlsRequest;
import com.emc.d2fs.services.download_service.GetUploadUrlsResponse;
import com.emc.d2fs.services.export_service.GetExportFolderRequest;
import com.emc.d2fs.services.export_service.GetExportFolderResponse;
import com.emc.d2fs.services.export_service.GetExportUrlRequest;
import com.emc.d2fs.services.export_service.GetExportUrlResponse;
import com.emc.d2fs.services.favorites_service.AddToFavoritesRequest;
import com.emc.d2fs.services.favorites_service.AddToFavoritesResponse;
import com.emc.d2fs.services.favorites_service.RemoveFromFavoritesRequest;
import com.emc.d2fs.services.favorites_service.RemoveFromFavoritesResponse;
import com.emc.d2fs.services.locate_service.GetLocationByDqlRequest;
import com.emc.d2fs.services.locate_service.GetLocationByDqlResponse;
import com.emc.d2fs.services.locate_service.GetLocationByIdRequest;
import com.emc.d2fs.services.locate_service.GetLocationByIdResponse;
import com.emc.d2fs.services.locate_service.GetLocationByNameRequest;
import com.emc.d2fs.services.locate_service.GetLocationByNameResponse;
import com.emc.d2fs.services.locate_service.GetLocationByPathRequest;
import com.emc.d2fs.services.locate_service.GetLocationByPathResponse;
import com.emc.d2fs.services.locate_service.GetLocationByTypeRequest;
import com.emc.d2fs.services.locate_service.GetLocationByTypeResponse;
import com.emc.d2fs.services.locate_service.GetLocationForBreadCrumbRequest;
import com.emc.d2fs.services.locate_service.GetLocationForBreadCrumbResponse;
import com.emc.d2fs.services.locate_service.GetLocationRequest;
import com.emc.d2fs.services.locate_service.GetLocationResponse;
import com.emc.d2fs.services.menu_service.GetFullMenusTypeRequest;
import com.emc.d2fs.services.menu_service.GetFullMenusTypeResponse;
import com.emc.d2fs.services.menu_service.GetMenusTypeRequest;
import com.emc.d2fs.services.menu_service.GetMenusTypeResponse;
import com.emc.d2fs.services.move_service.CopyRequest;
import com.emc.d2fs.services.move_service.CopyResponse;
import com.emc.d2fs.services.move_service.LinkRequest;
import com.emc.d2fs.services.move_service.LinkResponse;
import com.emc.d2fs.services.move_service.MoveRequest;
import com.emc.d2fs.services.move_service.MoveResponse;
import com.emc.d2fs.services.preference_service.GetColumnPreferencesRequest;
import com.emc.d2fs.services.preference_service.GetColumnPreferencesResponse;
import com.emc.d2fs.services.preference_service.GetColumnSelectSettingRequest;
import com.emc.d2fs.services.preference_service.GetColumnSelectSettingResponse;
import com.emc.d2fs.services.preference_service.GetColumnSrcSettingRequest;
import com.emc.d2fs.services.preference_service.GetColumnSrcSettingResponse;
import com.emc.d2fs.services.preference_service.GetColumnTypeSettingRequest;
import com.emc.d2fs.services.preference_service.GetColumnTypeSettingResponse;
import com.emc.d2fs.services.preference_service.GetFiltersRequest;
import com.emc.d2fs.services.preference_service.GetFiltersResponse;
import com.emc.d2fs.services.preference_service.GetLanguageRequest;
import com.emc.d2fs.services.preference_service.GetLanguageResponse;
import com.emc.d2fs.services.preference_service.GetPreferenceRequest;
import com.emc.d2fs.services.preference_service.GetPreferenceResponse;
import com.emc.d2fs.services.preference_service.GetPreferenceWithColumnRequest;
import com.emc.d2fs.services.preference_service.GetPreferenceWithColumnResponse;
import com.emc.d2fs.services.preference_service.SetColumnPreferenceRequest;
import com.emc.d2fs.services.preference_service.SetColumnPreferenceResponse;
import com.emc.d2fs.services.preference_service.SetColumnSelectSettingRequest;
import com.emc.d2fs.services.preference_service.SetColumnSelectSettingResponse;
import com.emc.d2fs.services.preference_service.SetFilterActiveRequest;
import com.emc.d2fs.services.preference_service.SetFilterActiveResponse;
import com.emc.d2fs.services.property_service.DumpRequest;
import com.emc.d2fs.services.property_service.DumpResponse;
import com.emc.d2fs.services.property_service.GetPropertiesRequest;
import com.emc.d2fs.services.property_service.GetPropertiesResponse;
import com.emc.d2fs.services.property_service.SavePropertiesRequest;
import com.emc.d2fs.services.property_service.SavePropertiesResponse;
import com.emc.d2fs.services.repository_service.CheckLoginRequest;
import com.emc.d2fs.services.repository_service.CheckLoginResponse;
import com.emc.d2fs.services.repository_service.GenerateDMTicketRequest;
import com.emc.d2fs.services.repository_service.GenerateDMTicketResponse;
import com.emc.d2fs.services.repository_service.GetAllRepositoriesRequest;
import com.emc.d2fs.services.repository_service.GetAllRepositoriesResponse;
import com.emc.d2fs.services.repository_service.GetRepositoryRequest;
import com.emc.d2fs.services.repository_service.GetRepositoryResponse;
import com.emc.d2fs.services.repository_service.ReleaseRequest;
import com.emc.d2fs.services.repository_service.ReleaseResponse;
import com.emc.d2fs.services.search_service.CreateSearchCategoryRequest;
import com.emc.d2fs.services.search_service.CreateSearchCategoryResponse;
import com.emc.d2fs.services.search_service.GetLastSearchQueryTermRequest;
import com.emc.d2fs.services.search_service.GetLastSearchQueryTermResponse;
import com.emc.d2fs.services.search_service.GetQuickSearchContentRequest;
import com.emc.d2fs.services.search_service.GetQuickSearchContentResponse;
import com.emc.d2fs.services.search_service.GetQuickSearchMultiContentRequest;
import com.emc.d2fs.services.search_service.GetQuickSearchMultiContentResponse;
import com.emc.d2fs.services.search_service.GetSearchAssistanceValuesRequest;
import com.emc.d2fs.services.search_service.GetSearchAssistanceValuesResponse;
import com.emc.d2fs.services.search_service.GetSearchAttributesRequest;
import com.emc.d2fs.services.search_service.GetSearchAttributesResponse;
import com.emc.d2fs.services.search_service.GetSearchConditionsRequest;
import com.emc.d2fs.services.search_service.GetSearchConditionsResponse;
import com.emc.d2fs.services.search_service.LoadSearchAttributesRequest;
import com.emc.d2fs.services.search_service.LoadSearchAttributesResponse;
import com.emc.d2fs.services.search_service.RunQueryFormRequest;
import com.emc.d2fs.services.search_service.RunQueryFormResponse;
import com.emc.d2fs.services.search_service.SaveSearchRequest;
import com.emc.d2fs.services.search_service.SaveSearchResponse;
import com.emc.d2fs.services.sendmail_service.SendmailRequest;
import com.emc.d2fs.services.sendmail_service.SendmailResponse;
import com.emc.d2fs.services.subscriptions_service.SubscribeRequest;
import com.emc.d2fs.services.subscriptions_service.SubscribeResponse;
import com.emc.d2fs.services.subscriptions_service.UnsubscribeAllRequest;
import com.emc.d2fs.services.subscriptions_service.UnsubscribeAllResponse;
import com.emc.d2fs.services.thumbnails_service.GetThumbnailUrlRequest;
import com.emc.d2fs.services.thumbnails_service.GetThumbnailUrlResponse;
import com.emc.d2fs.services.thumbnails_service.GetThumbnailUrlsRequest;
import com.emc.d2fs.services.thumbnails_service.GetThumbnailUrlsResponse;
import com.emc.d2fs.services.vdcontent_service.AddVDChildRequest;
import com.emc.d2fs.services.vdcontent_service.AddVDChildResponse;
import com.emc.d2fs.services.vdcontent_service.GetVDContentRequest;
import com.emc.d2fs.services.vdcontent_service.GetVDContentResponse;
import com.emc.d2fs.services.vdcontent_service.InsertVDInheritedComponentRequest;
import com.emc.d2fs.services.vdcontent_service.InsertVDInheritedComponentResponse;
import com.emc.d2fs.services.vdcontent_service.MoveVDChildRequest;
import com.emc.d2fs.services.vdcontent_service.MoveVDChildResponse;
import com.emc.d2fs.services.vdcontent_service.RemoveVDChildRequest;
import com.emc.d2fs.services.vdcontent_service.RemoveVDChildResponse;
import com.emc.d2fs.services.vdcontent_service.SetVDChildBindingVersionsRequest;
import com.emc.d2fs.services.vdcontent_service.SetVDChildBindingVersionsResponse;
import com.emc.d2fs.services.vdconvert_service.ConvertToSimpleDocRequest;
import com.emc.d2fs.services.vdconvert_service.ConvertToSimpleDocResponse;
import com.emc.d2fs.services.vdconvert_service.ConvertToVDRequest;
import com.emc.d2fs.services.vdconvert_service.ConvertToVDResponse;
import com.emc.d2fs.services.vdsnapshot_service.VdSnapshotRequest;
import com.emc.d2fs.services.vdsnapshot_service.VdSnapshotResponse;
import com.emc.d2fs.services.workflow_service.AbortWorkflowRequest;
import com.emc.d2fs.services.workflow_service.AbortWorkflowResponse;
import com.emc.d2fs.services.workflow_service.AcquireTaskRequest;
import com.emc.d2fs.services.workflow_service.AcquireTaskResponse;
import com.emc.d2fs.services.workflow_service.AddNoteToTaskRequest;
import com.emc.d2fs.services.workflow_service.AddNoteToTaskResponse;
import com.emc.d2fs.services.workflow_service.CanAbortWorkflowRequest;
import com.emc.d2fs.services.workflow_service.CanAbortWorkflowResponse;
import com.emc.d2fs.services.workflow_service.CanAddTaskNoteRequest;
import com.emc.d2fs.services.workflow_service.CanAddTaskNoteResponse;
import com.emc.d2fs.services.workflow_service.CanDelegateTaskRequest;
import com.emc.d2fs.services.workflow_service.CanDelegateTaskResponse;
import com.emc.d2fs.services.workflow_service.CanForwardTaskRequest;
import com.emc.d2fs.services.workflow_service.CanForwardTaskResponse;
import com.emc.d2fs.services.workflow_service.CanRejectTaskRequest;
import com.emc.d2fs.services.workflow_service.CanRejectTaskResponse;
import com.emc.d2fs.services.workflow_service.CheckLifeCycleRequest;
import com.emc.d2fs.services.workflow_service.CheckLifeCycleResponse;
import com.emc.d2fs.services.workflow_service.CheckPropertyPageRequest;
import com.emc.d2fs.services.workflow_service.CheckPropertyPageResponse;
import com.emc.d2fs.services.workflow_service.CheckWorkflowAliasesRequest;
import com.emc.d2fs.services.workflow_service.CheckWorkflowAliasesResponse;
import com.emc.d2fs.services.workflow_service.DelegateTaskOnErrorRequest;
import com.emc.d2fs.services.workflow_service.DelegateTaskOnErrorResponse;
import com.emc.d2fs.services.workflow_service.DelegateTaskRequest;
import com.emc.d2fs.services.workflow_service.DelegateTaskResponse;
import com.emc.d2fs.services.workflow_service.GetConfigurationsNamesRequest;
import com.emc.d2fs.services.workflow_service.GetConfigurationsNamesResponse;
import com.emc.d2fs.services.workflow_service.GetTaskFolderLabelRequest;
import com.emc.d2fs.services.workflow_service.GetTaskFolderLabelResponse;
import com.emc.d2fs.services.workflow_service.GetTaskModeRequest;
import com.emc.d2fs.services.workflow_service.GetTaskModeResponse;
import com.emc.d2fs.services.workflow_service.IsManualAcquisitionTaskRequest;
import com.emc.d2fs.services.workflow_service.IsManualAcquisitionTaskResponse;
import com.emc.d2fs.services.workflow_service.IsTaskAcquiredRequest;
import com.emc.d2fs.services.workflow_service.IsTaskAcquiredResponse;
import com.emc.d2fs.services.workflow_service.LaunchScheduledWorkflowRequest;
import com.emc.d2fs.services.workflow_service.LaunchScheduledWorkflowResponse;
import com.emc.d2fs.services.workflow_service.LaunchWorkflowRequest;
import com.emc.d2fs.services.workflow_service.LaunchWorkflowResponse;
import com.emc.d2fs.services.workflow_service.ProcessTaskRequest;
import com.emc.d2fs.services.workflow_service.ProcessTaskResponse;
import com.emc.d2fs.services.workflow_service.SetTaskPriorityRequest;
import com.emc.d2fs.services.workflow_service.SetTaskPriorityResponse;
import com.emc.d2fs.services.workflow_service.SetTaskReadStateRequest;
import com.emc.d2fs.services.workflow_service.SetTaskReadStateResponse;
import com.emc.d2fs.services.workflow_service.VerifyEntryCriteriaRequest;
import com.emc.d2fs.services.workflow_service.VerifyEntryCriteriaResponse;
import com.emc.d2fs.services.workflowcontent_service.GetWorkflowContentRequest;
import com.emc.d2fs.services.workflowcontent_service.GetWorkflowContentResponse;
import com.emc.d2fs.services.x3config_service.FetchSpaceXmlContentRequest;
import com.emc.d2fs.services.x3config_service.FetchSpaceXmlContentResponse;
import com.emc.d2fs.services.x3config_service.GetAvailableSkinsRequest;
import com.emc.d2fs.services.x3config_service.GetAvailableSkinsResponse;
import com.emc.d2fs.services.x3config_service.GetAvailableSpacesRequest;
import com.emc.d2fs.services.x3config_service.GetAvailableSpacesResponse;
import com.emc.d2fs.services.x3config_service.GetAvailableWidgetsRequest;
import com.emc.d2fs.services.x3config_service.GetAvailableWidgetsResponse;
import com.emc.d2fs.services.x3config_service.GetDefaultOfflineSkinRequest;
import com.emc.d2fs.services.x3config_service.GetDefaultOfflineSkinResponse;
import com.emc.d2fs.services.x3config_service.GetDownloadLocationsRequest;
import com.emc.d2fs.services.x3config_service.GetDownloadLocationsResponse;
import com.emc.d2fs.services.x3config_service.GetIsWidgetInViewRequest;
import com.emc.d2fs.services.x3config_service.GetIsWidgetInViewResponse;
import com.emc.d2fs.services.x3config_service.GetPortalMenuPositionRequest;
import com.emc.d2fs.services.x3config_service.GetPortalMenuPositionResponse;
import com.emc.d2fs.services.x3config_service.GetPortalMenuWidthRequest;
import com.emc.d2fs.services.x3config_service.GetPortalMenuWidthResponse;
import com.emc.d2fs.services.x3config_service.GetPortalRowHeightRequest;
import com.emc.d2fs.services.x3config_service.GetPortalRowHeightResponse;
import com.emc.d2fs.services.x3config_service.GetQueryFormIdFromWidgetRequest;
import com.emc.d2fs.services.x3config_service.GetQueryFormIdFromWidgetResponse;
import com.emc.d2fs.services.x3config_service.GetSkinRequest;
import com.emc.d2fs.services.x3config_service.GetSkinResponse;
import com.emc.d2fs.services.x3config_service.GetSpaceRequest;
import com.emc.d2fs.services.x3config_service.GetSpaceResponse;
import com.emc.d2fs.services.x3config_service.GetUserLastSpacesRequest;
import com.emc.d2fs.services.x3config_service.GetUserLastSpacesResponse;
import com.emc.d2fs.services.x3config_service.GetWidgetUrlRequest;
import com.emc.d2fs.services.x3config_service.GetWidgetUrlResponse;
import com.emc.d2fs.services.x3config_service.SaveUserLastSpacesRequest;
import com.emc.d2fs.services.x3config_service.SaveUserLastSpacesResponse;
import com.emc.d2fs.services.x3config_service.SetDownloadLocationsRequest;
import com.emc.d2fs.services.x3config_service.SetDownloadLocationsResponse;
import com.emc.d2fs.services.x3config_service.SetPortalMenuPositionRequest;
import com.emc.d2fs.services.x3config_service.SetPortalMenuPositionResponse;
import com.emc.d2fs.services.x3config_service.SetPortalMenuWidthRequest;
import com.emc.d2fs.services.x3config_service.SetPortalMenuWidthResponse;
import com.emc.d2fs.services.x3config_service.SetPortalRowHeightRequest;
import com.emc.d2fs.services.x3config_service.SetPortalRowHeightResponse;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ModelPort", targetNamespace = "http://www.emc.com/d2fs/schemas/models")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    com.emc.d2fs.exceptions.ObjectFactory.class,
    com.emc.d2fs.models.archive_info.ObjectFactory.class,
    com.emc.d2fs.models.attribute.ObjectFactory.class,
    com.emc.d2fs.models.checkin_config.ObjectFactory.class,
    com.emc.d2fs.models.checkin_parameters.ObjectFactory.class,
    com.emc.d2fs.models.comment.ObjectFactory.class,
    com.emc.d2fs.models.common.ObjectFactory.class,
    com.emc.d2fs.models.context.ObjectFactory.class,
    com.emc.d2fs.models.destroyresult.ObjectFactory.class,
    com.emc.d2fs.models.dialog.ObjectFactory.class,
    com.emc.d2fs.models.dictionary_operation.ObjectFactory.class,
    com.emc.d2fs.models.dump.ObjectFactory.class,
    com.emc.d2fs.models.export_folder_entry.ObjectFactory.class,
    com.emc.d2fs.models.filecontent.ObjectFactory.class,
    com.emc.d2fs.models.getcomments_result.ObjectFactory.class,
    com.emc.d2fs.models.item.ObjectFactory.class,
    com.emc.d2fs.models.lifecycle.ObjectFactory.class,
    com.emc.d2fs.models.link_info.ObjectFactory.class,
    com.emc.d2fs.models.massupdateinformations.ObjectFactory.class,
    com.emc.d2fs.models.menu.ObjectFactory.class,
    com.emc.d2fs.models.method_result.ObjectFactory.class,
    com.emc.d2fs.models.node.ObjectFactory.class,
    com.emc.d2fs.models.option.ObjectFactory.class,
    com.emc.d2fs.models.pdfrender_result.ObjectFactory.class,
    com.emc.d2fs.models.preference.ObjectFactory.class,
    com.emc.d2fs.models.rendering_options.ObjectFactory.class,
    com.emc.d2fs.models.repository.ObjectFactory.class,
    com.emc.d2fs.models.search_query_term.ObjectFactory.class,
    com.emc.d2fs.models.server_info.ObjectFactory.class,
    com.emc.d2fs.models.taxonomy_operation.ObjectFactory.class,
    com.emc.d2fs.models.template.ObjectFactory.class,
    com.emc.d2fs.models.workflow_details.ObjectFactory.class,
    com.emc.d2fs.models.x3_skin.ObjectFactory.class,
    com.emc.d2fs.models.x3_space.ObjectFactory.class,
    com.emc.d2fs.models.x3_widget.ObjectFactory.class,
    com.emc.d2fs.services.acrobat_annotate_service.ObjectFactory.class,
    com.emc.d2fs.services.action_service.ObjectFactory.class,
    com.emc.d2fs.services.annotation_service.ObjectFactory.class,
    com.emc.d2fs.services.browser_service.ObjectFactory.class,
    com.emc.d2fs.services.centerstage_service.ObjectFactory.class,
    com.emc.d2fs.services.checkin_service.ObjectFactory.class,
    com.emc.d2fs.services.checkout_service.ObjectFactory.class,
    com.emc.d2fs.services.command_service.ObjectFactory.class,
    com.emc.d2fs.services.content_service.ObjectFactory.class,
    com.emc.d2fs.services.creation_service.ObjectFactory.class,
    com.emc.d2fs.services.d2_apply_config_service.ObjectFactory.class,
    com.emc.d2fs.services.d2_archive_service.ObjectFactory.class,
    com.emc.d2fs.services.d2_audit_service.ObjectFactory.class,
    com.emc.d2fs.services.d2_cache_service.ObjectFactory.class,
    com.emc.d2fs.services.d2_dictionary_service.ObjectFactory.class,
    com.emc.d2fs.services.d2_link_service.ObjectFactory.class,
    com.emc.d2fs.services.d2_linked_document_service.ObjectFactory.class,
    com.emc.d2fs.services.d2_massupdate_service.ObjectFactory.class,
    com.emc.d2fs.services.d2_method_service.ObjectFactory.class,
    com.emc.d2fs.services.d2_pdfrender_service.ObjectFactory.class,
    com.emc.d2fs.services.d2_relation_service.ObjectFactory.class,
    com.emc.d2fs.services.d2_taxonomy_service.ObjectFactory.class,
    com.emc.d2fs.services.d2_template_service.ObjectFactory.class,
    com.emc.d2fs.services.d2_validation_service.ObjectFactory.class,
    com.emc.d2fs.services.d2_vd_template_service.ObjectFactory.class,
    com.emc.d2fs.services.d2lifecycle_service.ObjectFactory.class,
    com.emc.d2fs.services.dctmlifecycle_service.ObjectFactory.class,
    com.emc.d2fs.services.destroy_service.ObjectFactory.class,
    com.emc.d2fs.services.details_service.ObjectFactory.class,
    com.emc.d2fs.services.dialog_service.ObjectFactory.class,
    com.emc.d2fs.services.distribution_service.ObjectFactory.class,
    com.emc.d2fs.services.download_service.ObjectFactory.class,
    com.emc.d2fs.services.export_service.ObjectFactory.class,
    com.emc.d2fs.services.favorites_service.ObjectFactory.class,
    com.emc.d2fs.services.locate_service.ObjectFactory.class,
    com.emc.d2fs.services.menu_service.ObjectFactory.class,
    com.emc.d2fs.services.move_service.ObjectFactory.class,
    com.emc.d2fs.services.preference_service.ObjectFactory.class,
    com.emc.d2fs.services.property_service.ObjectFactory.class,
    com.emc.d2fs.services.repository_service.ObjectFactory.class,
    com.emc.d2fs.services.search_service.ObjectFactory.class,
    com.emc.d2fs.services.sendmail_service.ObjectFactory.class,
    com.emc.d2fs.services.subscriptions_service.ObjectFactory.class,
    com.emc.d2fs.services.thumbnails_service.ObjectFactory.class,
    com.emc.d2fs.services.vdcontent_service.ObjectFactory.class,
    com.emc.d2fs.services.vdconvert_service.ObjectFactory.class,
    com.emc.d2fs.services.vdsnapshot_service.ObjectFactory.class,
    com.emc.d2fs.services.workflow_service.ObjectFactory.class,
    com.emc.d2fs.services.workflowcontent_service.ObjectFactory.class,
    com.emc.d2fs.services.x3config_service.ObjectFactory.class,
    org.w3._2005._05.xmlmime.ObjectFactory.class
})
public interface ModelPort {


    /**
     * 
     * @param getDetailContentRequest
     * @return
     *     returns com.emc.d2fs.services.details_service.GetDetailContentResponse
     */
    @WebMethod
    @WebResult(name = "getDetailContentResponse", targetNamespace = "http://www.emc.com/d2fs/services/details_service", partName = "getDetailContentResponse")
    public GetDetailContentResponse getDetailContent(
        @WebParam(name = "getDetailContentRequest", targetNamespace = "http://www.emc.com/d2fs/services/details_service", partName = "getDetailContentRequest")
        GetDetailContentRequest getDetailContentRequest);

    /**
     * 
     * @param getPreferenceWithColumnRequest
     * @return
     *     returns com.emc.d2fs.services.preference_service.GetPreferenceWithColumnResponse
     */
    @WebMethod
    @WebResult(name = "getPreferenceWithColumnResponse", targetNamespace = "http://www.emc.com/d2fs/services/preference_service", partName = "getPreferenceWithColumnResponse")
    public GetPreferenceWithColumnResponse getPreferenceWithColumn(
        @WebParam(name = "getPreferenceWithColumnRequest", targetNamespace = "http://www.emc.com/d2fs/services/preference_service", partName = "getPreferenceWithColumnRequest")
        GetPreferenceWithColumnRequest getPreferenceWithColumnRequest);

    /**
     * 
     * @param cancelCheckoutRequest
     * @return
     *     returns com.emc.d2fs.services.checkout_service.CancelCheckoutResponse
     */
    @WebMethod
    @WebResult(name = "cancelCheckoutResponse", targetNamespace = "http://www.emc.com/d2fs/services/checkout_service", partName = "cancelCheckoutResponse")
    public CancelCheckoutResponse cancelCheckout(
        @WebParam(name = "cancelCheckoutRequest", targetNamespace = "http://www.emc.com/d2fs/services/checkout_service", partName = "cancelCheckoutRequest")
        CancelCheckoutRequest cancelCheckoutRequest);

    /**
     * 
     * @param getDQLContentRequest
     * @return
     *     returns com.emc.d2fs.services.content_service.GetDQLContentResponse
     */
    @WebMethod
    @WebResult(name = "getDQLContentResponse", targetNamespace = "http://www.emc.com/d2fs/services/content_service", partName = "getDQLContentResponse")
    public GetDQLContentResponse getDQLContent(
        @WebParam(name = "getDQLContentRequest", targetNamespace = "http://www.emc.com/d2fs/services/content_service", partName = "getDQLContentRequest")
        GetDQLContentRequest getDQLContentRequest);

    /**
     * 
     * @param getLinksCountRequest
     * @return
     *     returns com.emc.d2fs.services.d2_linked_document_service.GetLinksCountResponse
     */
    @WebMethod
    @WebResult(name = "getLinksCountResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_linked_document_service", partName = "getLinksCountResponse")
    public GetLinksCountResponse getLinksCount(
        @WebParam(name = "getLinksCountRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_linked_document_service", partName = "getLinksCountRequest")
        GetLinksCountRequest getLinksCountRequest);

    /**
     * 
     * @param removeAttachmentsRequest
     * @return
     *     returns com.emc.d2fs.services.creation_service.RemoveAttachmentsResponse
     */
    @WebMethod
    @WebResult(name = "removeAttachmentsResponse", targetNamespace = "http://www.emc.com/d2fs/services/creation_service", partName = "removeAttachmentsResponse")
    public RemoveAttachmentsResponse removeAttachments(
        @WebParam(name = "removeAttachmentsRequest", targetNamespace = "http://www.emc.com/d2fs/services/creation_service", partName = "removeAttachmentsRequest")
        RemoveAttachmentsRequest removeAttachmentsRequest);

    /**
     * 
     * @param canForwardTaskRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.CanForwardTaskResponse
     */
    @WebMethod
    @WebResult(name = "canForwardTaskResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "canForwardTaskResponse")
    public CanForwardTaskResponse canForwardTask(
        @WebParam(name = "canForwardTaskRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "canForwardTaskRequest")
        CanForwardTaskRequest canForwardTaskRequest);

    /**
     * 
     * @param getMassUpdateInformationsRequest
     * @return
     *     returns com.emc.d2fs.services.d2_massupdate_service.GetMassUpdateInformationsResponse
     */
    @WebMethod
    @WebResult(name = "getMassUpdateInformationsResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_massupdate_service", partName = "getMassUpdateInformationsResponse")
    public GetMassUpdateInformationsResponse getMassUpdateInformations(
        @WebParam(name = "getMassUpdateInformationsRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_massupdate_service", partName = "getMassUpdateInformationsRequest")
        GetMassUpdateInformationsRequest getMassUpdateInformationsRequest);

    /**
     * 
     * @param handlePluginActionRequest
     * @return
     *     returns com.emc.d2fs.services.action_service.HandlePluginActionResponse
     */
    @WebMethod
    @WebResult(name = "handlePluginActionResponse", targetNamespace = "http://www.emc.com/d2fs/services/action_service", partName = "handlePluginActionResponse")
    public HandlePluginActionResponse handlePluginAction(
        @WebParam(name = "handlePluginActionRequest", targetNamespace = "http://www.emc.com/d2fs/services/action_service", partName = "handlePluginActionRequest")
        HandlePluginActionRequest handlePluginActionRequest);

    /**
     * 
     * @param getWidgetUrlRequest
     * @return
     *     returns com.emc.d2fs.services.x3config_service.GetWidgetUrlResponse
     */
    @WebMethod
    @WebResult(name = "getWidgetUrlResponse", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getWidgetUrlResponse")
    public GetWidgetUrlResponse getWidgetUrl(
        @WebParam(name = "getWidgetUrlRequest", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getWidgetUrlRequest")
        GetWidgetUrlRequest getWidgetUrlRequest);

    /**
     * 
     * @param getDownloadLocationsRequest
     * @return
     *     returns com.emc.d2fs.services.x3config_service.GetDownloadLocationsResponse
     */
    @WebMethod
    @WebResult(name = "getDownloadLocationsResponse", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getDownloadLocationsResponse")
    public GetDownloadLocationsResponse getDownloadLocations(
        @WebParam(name = "getDownloadLocationsRequest", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getDownloadLocationsRequest")
        GetDownloadLocationsRequest getDownloadLocationsRequest);

    /**
     * 
     * @param getFacetContentRequest
     * @return
     *     returns com.emc.d2fs.services.content_service.GetFacetContentResponse
     */
    @WebMethod
    @WebResult(name = "getFacetContentResponse", targetNamespace = "http://www.emc.com/d2fs/services/content_service", partName = "getFacetContentResponse")
    public GetFacetContentResponse getFacetContent(
        @WebParam(name = "getFacetContentRequest", targetNamespace = "http://www.emc.com/d2fs/services/content_service", partName = "getFacetContentRequest")
        GetFacetContentRequest getFacetContentRequest);

    /**
     * 
     * @param isNoContentAuthorizedRequest
     * @return
     *     returns com.emc.d2fs.services.d2_template_service.IsNoContentAuthorizedResponse
     */
    @WebMethod
    @WebResult(name = "isNoContentAuthorizedResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_template_service", partName = "isNoContentAuthorizedResponse")
    public com.emc.d2fs.services.d2_template_service.IsNoContentAuthorizedResponse isNoContentAuthorized2(
        @WebParam(name = "isNoContentAuthorizedRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_template_service", partName = "isNoContentAuthorizedRequest")
        com.emc.d2fs.services.d2_template_service.IsNoContentAuthorizedRequest isNoContentAuthorizedRequest);

    /**
     * 
     * @param fetchSpaceXmlContentRequest
     * @return
     *     returns com.emc.d2fs.services.x3config_service.FetchSpaceXmlContentResponse
     */
    @WebMethod
    @WebResult(name = "fetchSpaceXmlContentResponse", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "fetchSpaceXmlContentResponse")
    public FetchSpaceXmlContentResponse fetchSpaceXmlContent(
        @WebParam(name = "fetchSpaceXmlContentRequest", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "fetchSpaceXmlContentRequest")
        FetchSpaceXmlContentRequest fetchSpaceXmlContentRequest);

    /**
     * 
     * @param getSignoffParametersRequest
     * @return
     *     returns com.emc.d2fs.services.d2lifecycle_service.GetSignoffParametersResponse
     */
    @WebMethod
    @WebResult(name = "getSignoffParametersResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2lifecycle_service", partName = "getSignoffParametersResponse")
    public com.emc.d2fs.services.d2lifecycle_service.GetSignoffParametersResponse getSignoffParameters(
        @WebParam(name = "getSignoffParametersRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2lifecycle_service", partName = "getSignoffParametersRequest")
        com.emc.d2fs.services.d2lifecycle_service.GetSignoffParametersRequest getSignoffParametersRequest);

    /**
     * 
     * @param childrenRelationRequest
     * @return
     *     returns com.emc.d2fs.services.d2_relation_service.ChildrenRelationResponse
     */
    @WebMethod
    @WebResult(name = "childrenRelationResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_relation_service", partName = "childrenRelationResponse")
    public ChildrenRelationResponse childrenRelation(
        @WebParam(name = "childrenRelationRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_relation_service", partName = "childrenRelationRequest")
        ChildrenRelationRequest childrenRelationRequest);

    /**
     * 
     * @param destroyAllRequest
     * @return
     *     returns com.emc.d2fs.services.destroy_service.DestroyAllResponse
     */
    @WebMethod
    @WebResult(name = "destroyAllResponse", targetNamespace = "http://www.emc.com/d2fs/services/destroy_service", partName = "destroyAllResponse")
    public DestroyAllResponse destroyAll(
        @WebParam(name = "destroyAllRequest", targetNamespace = "http://www.emc.com/d2fs/services/destroy_service", partName = "destroyAllRequest")
        DestroyAllRequest destroyAllRequest);

    /**
     * 
     * @param getQuickSearchMultiContentRequest
     * @return
     *     returns com.emc.d2fs.services.search_service.GetQuickSearchMultiContentResponse
     */
    @WebMethod
    @WebResult(name = "getQuickSearchMultiContentResponse", targetNamespace = "http://www.emc.com/d2fs/services/search_service", partName = "getQuickSearchMultiContentResponse")
    public GetQuickSearchMultiContentResponse getQuickSearchMultiContent(
        @WebParam(name = "getQuickSearchMultiContentRequest", targetNamespace = "http://www.emc.com/d2fs/services/search_service", partName = "getQuickSearchMultiContentRequest")
        GetQuickSearchMultiContentRequest getQuickSearchMultiContentRequest);

    /**
     * 
     * @param linkRequest
     * @return
     *     returns com.emc.d2fs.services.move_service.LinkResponse
     */
    @WebMethod
    @WebResult(name = "linkResponse", targetNamespace = "http://www.emc.com/d2fs/services/move_service", partName = "linkResponse")
    public LinkResponse link(
        @WebParam(name = "linkRequest", targetNamespace = "http://www.emc.com/d2fs/services/move_service", partName = "linkRequest")
        LinkRequest linkRequest);

    /**
     * 
     * @param getVDContentRequest
     * @return
     *     returns com.emc.d2fs.services.vdcontent_service.GetVDContentResponse
     */
    @WebMethod
    @WebResult(name = "getVDContentResponse", targetNamespace = "http://www.emc.com/d2fs/services/vdcontent_service", partName = "getVDContentResponse")
    public GetVDContentResponse getVDContent(
        @WebParam(name = "getVDContentRequest", targetNamespace = "http://www.emc.com/d2fs/services/vdcontent_service", partName = "getVDContentRequest")
        GetVDContentRequest getVDContentRequest);

    /**
     * 
     * @param getMenusTypeRequest
     * @return
     *     returns com.emc.d2fs.services.menu_service.GetMenusTypeResponse
     */
    @WebMethod
    @WebResult(name = "getMenusTypeResponse", targetNamespace = "http://www.emc.com/d2fs/services/menu_service", partName = "getMenusTypeResponse")
    public GetMenusTypeResponse getMenusType(
        @WebParam(name = "getMenusTypeRequest", targetNamespace = "http://www.emc.com/d2fs/services/menu_service", partName = "getMenusTypeRequest")
        GetMenusTypeRequest getMenusTypeRequest);

    /**
     * 
     * @param getQueryFormIdFromWidgetRequest
     * @return
     *     returns com.emc.d2fs.services.x3config_service.GetQueryFormIdFromWidgetResponse
     */
    @WebMethod
    @WebResult(name = "getQueryFormIdFromWidgetResponse", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getQueryFormIdFromWidgetResponse")
    public GetQueryFormIdFromWidgetResponse getQueryFormIdFromWidget(
        @WebParam(name = "getQueryFormIdFromWidgetRequest", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getQueryFormIdFromWidgetRequest")
        GetQueryFormIdFromWidgetRequest getQueryFormIdFromWidgetRequest);

    /**
     * 
     * @param childIdRequest
     * @return
     *     returns com.emc.d2fs.services.d2_relation_service.ChildIdResponse
     */
    @WebMethod
    @WebResult(name = "childIdResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_relation_service", partName = "childIdResponse")
    public ChildIdResponse childId(
        @WebParam(name = "childIdRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_relation_service", partName = "childIdRequest")
        ChildIdRequest childIdRequest);

    /**
     * 
     * @param getExtractUrlsRequest
     * @return
     *     returns com.emc.d2fs.services.download_service.GetExtractUrlsResponse
     */
    @WebMethod
    @WebResult(name = "getExtractUrlsResponse", targetNamespace = "http://www.emc.com/d2fs/services/download_service", partName = "getExtractUrlsResponse")
    public GetExtractUrlsResponse getExtractUrls(
        @WebParam(name = "getExtractUrlsRequest", targetNamespace = "http://www.emc.com/d2fs/services/download_service", partName = "getExtractUrlsRequest")
        GetExtractUrlsRequest getExtractUrlsRequest);

    /**
     * 
     * @param destroyRelationRequest
     * @return
     *     returns com.emc.d2fs.services.d2_relation_service.DestroyRelationResponse
     */
    @WebMethod
    @WebResult(name = "destroyRelationResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_relation_service", partName = "destroyRelationResponse")
    public DestroyRelationResponse destroyRelation(
        @WebParam(name = "destroyRelationRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_relation_service", partName = "destroyRelationRequest")
        DestroyRelationRequest destroyRelationRequest);

    /**
     * 
     * @param checkLoginRequest
     * @return
     *     returns com.emc.d2fs.services.repository_service.CheckLoginResponse
     */
    @WebMethod
    @WebResult(name = "checkLoginResponse", targetNamespace = "http://www.emc.com/d2fs/services/repository_service", partName = "checkLoginResponse")
    public CheckLoginResponse checkLogin(
        @WebParam(name = "checkLoginRequest", targetNamespace = "http://www.emc.com/d2fs/services/repository_service", partName = "checkLoginRequest")
        CheckLoginRequest checkLoginRequest);

    /**
     * 
     * @param launchWorkflowRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.LaunchWorkflowResponse
     */
    @WebMethod
    @WebResult(name = "launchWorkflowResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "launchWorkflowResponse")
    public LaunchWorkflowResponse launchWorkflow(
        @WebParam(name = "launchWorkflowRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "launchWorkflowRequest")
        LaunchWorkflowRequest launchWorkflowRequest);

    /**
     * 
     * @param getExportDictionaryUrlRequest
     * @return
     *     returns com.emc.d2fs.services.d2_dictionary_service.GetExportDictionaryUrlResponse
     */
    @WebMethod
    @WebResult(name = "getExportDictionaryUrlResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_dictionary_service", partName = "getExportDictionaryUrlResponse")
    public GetExportDictionaryUrlResponse getExportDictionaryUrl(
        @WebParam(name = "getExportDictionaryUrlRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_dictionary_service", partName = "getExportDictionaryUrlRequest")
        GetExportDictionaryUrlRequest getExportDictionaryUrlRequest);

    /**
     * 
     * @param getLocationByDqlRequest
     * @return
     *     returns com.emc.d2fs.services.locate_service.GetLocationByDqlResponse
     */
    @WebMethod
    @WebResult(name = "getLocationByDqlResponse", targetNamespace = "http://www.emc.com/d2fs/services/locate_service", partName = "getLocationByDqlResponse")
    public GetLocationByDqlResponse getLocationByDql(
        @WebParam(name = "getLocationByDqlRequest", targetNamespace = "http://www.emc.com/d2fs/services/locate_service", partName = "getLocationByDqlRequest")
        GetLocationByDqlRequest getLocationByDqlRequest);

    /**
     * 
     * @param saveVersionTaxonomyRequest
     * @return
     *     returns com.emc.d2fs.services.d2_taxonomy_service.SaveVersionTaxonomyResponse
     */
    @WebMethod
    @WebResult(name = "saveVersionTaxonomyResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_taxonomy_service", partName = "saveVersionTaxonomyResponse")
    public SaveVersionTaxonomyResponse saveVersionTaxonomy(
        @WebParam(name = "saveVersionTaxonomyRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_taxonomy_service", partName = "saveVersionTaxonomyRequest")
        SaveVersionTaxonomyRequest saveVersionTaxonomyRequest);

    /**
     * 
     * @param addNoteToTaskRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.AddNoteToTaskResponse
     */
    @WebMethod
    @WebResult(name = "addNoteToTaskResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "addNoteToTaskResponse")
    public AddNoteToTaskResponse addNoteToTask(
        @WebParam(name = "addNoteToTaskRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "addNoteToTaskRequest")
        AddNoteToTaskRequest addNoteToTaskRequest);

    /**
     * 
     * @param parentIdRequest
     * @return
     *     returns com.emc.d2fs.services.d2_relation_service.ParentIdResponse
     */
    @WebMethod
    @WebResult(name = "parentIdResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_relation_service", partName = "parentIdResponse")
    public ParentIdResponse parentId(
        @WebParam(name = "parentIdRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_relation_service", partName = "parentIdRequest")
        ParentIdRequest parentIdRequest);

    /**
     * 
     * @param getUserLastSpacesRequest
     * @return
     *     returns com.emc.d2fs.services.x3config_service.GetUserLastSpacesResponse
     */
    @WebMethod
    @WebResult(name = "getUserLastSpacesResponse", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getUserLastSpacesResponse")
    public GetUserLastSpacesResponse getUserLastSpaces(
        @WebParam(name = "getUserLastSpacesRequest", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getUserLastSpacesRequest")
        GetUserLastSpacesRequest getUserLastSpacesRequest);

    /**
     * 
     * @param convertToVDRequest
     * @return
     *     returns com.emc.d2fs.services.vdconvert_service.ConvertToVDResponse
     */
    @WebMethod
    @WebResult(name = "convertToVDResponse", targetNamespace = "http://www.emc.com/d2fs/services/vdconvert_service", partName = "convertToVDResponse")
    public ConvertToVDResponse convertToVD(
        @WebParam(name = "convertToVDRequest", targetNamespace = "http://www.emc.com/d2fs/services/vdconvert_service", partName = "convertToVDRequest")
        ConvertToVDRequest convertToVDRequest);

    /**
     * 
     * @param getPropertiesRequest
     * @return
     *     returns com.emc.d2fs.services.property_service.GetPropertiesResponse
     */
    @WebMethod
    @WebResult(name = "getPropertiesResponse", targetNamespace = "http://www.emc.com/d2fs/services/property_service", partName = "getPropertiesResponse")
    public GetPropertiesResponse getProperties(
        @WebParam(name = "getPropertiesRequest", targetNamespace = "http://www.emc.com/d2fs/services/property_service", partName = "getPropertiesRequest")
        GetPropertiesRequest getPropertiesRequest);

    /**
     * 
     * @param validDialogRequest
     * @return
     *     returns com.emc.d2fs.services.dialog_service.ValidDialogResponse
     */
    @WebMethod
    @WebResult(name = "validDialogResponse", targetNamespace = "http://www.emc.com/d2fs/services/dialog_service", partName = "validDialogResponse")
    public ValidDialogResponse validDialog(
        @WebParam(name = "validDialogRequest", targetNamespace = "http://www.emc.com/d2fs/services/dialog_service", partName = "validDialogRequest")
        ValidDialogRequest validDialogRequest);

    /**
     * 
     * @param getThumbnailUrlRequest
     * @return
     *     returns com.emc.d2fs.services.thumbnails_service.GetThumbnailUrlResponse
     */
    @WebMethod
    @WebResult(name = "getThumbnailUrlResponse", targetNamespace = "http://www.emc.com/d2fs/services/thumbnails_service", partName = "getThumbnailUrlResponse")
    public GetThumbnailUrlResponse getThumbnailUrl(
        @WebParam(name = "getThumbnailUrlRequest", targetNamespace = "http://www.emc.com/d2fs/services/thumbnails_service", partName = "getThumbnailUrlRequest")
        GetThumbnailUrlRequest getThumbnailUrlRequest);

    /**
     * 
     * @param getImportValuesUrlRequest
     * @return
     *     returns com.emc.d2fs.services.dialog_service.GetImportValuesUrlResponse
     */
    @WebMethod
    @WebResult(name = "getImportValuesUrlResponse", targetNamespace = "http://www.emc.com/d2fs/services/dialog_service", partName = "getImportValuesUrlResponse")
    public GetImportValuesUrlResponse getImportValuesUrl(
        @WebParam(name = "getImportValuesUrlRequest", targetNamespace = "http://www.emc.com/d2fs/services/dialog_service", partName = "getImportValuesUrlRequest")
        GetImportValuesUrlRequest getImportValuesUrlRequest);

    /**
     * 
     * @param testCheckoutRequest
     * @return
     *     returns com.emc.d2fs.services.checkout_service.TestCheckoutResponse
     */
    @WebMethod
    @WebResult(name = "testCheckoutResponse", targetNamespace = "http://www.emc.com/d2fs/services/checkout_service", partName = "testCheckoutResponse")
    public TestCheckoutResponse testCheckout(
        @WebParam(name = "testCheckoutRequest", targetNamespace = "http://www.emc.com/d2fs/services/checkout_service", partName = "testCheckoutRequest")
        TestCheckoutRequest testCheckoutRequest);

    /**
     * 
     * @param getMultiContentRequest
     * @return
     *     returns com.emc.d2fs.services.content_service.GetMultiContentResponse
     */
    @WebMethod
    @WebResult(name = "getMultiContentResponse", targetNamespace = "http://www.emc.com/d2fs/services/content_service", partName = "getMultiContentResponse")
    public GetMultiContentResponse getMultiContent(
        @WebParam(name = "getMultiContentRequest", targetNamespace = "http://www.emc.com/d2fs/services/content_service", partName = "getMultiContentRequest")
        GetMultiContentRequest getMultiContentRequest);

    /**
     * 
     * @param getPreferenceRequest
     * @return
     *     returns com.emc.d2fs.services.preference_service.GetPreferenceResponse
     */
    @WebMethod
    @WebResult(name = "getPreferenceResponse", targetNamespace = "http://www.emc.com/d2fs/services/preference_service", partName = "getPreferenceResponse")
    public GetPreferenceResponse getPreference(
        @WebParam(name = "getPreferenceRequest", targetNamespace = "http://www.emc.com/d2fs/services/preference_service", partName = "getPreferenceRequest")
        GetPreferenceRequest getPreferenceRequest);

    /**
     * 
     * @param stopDistributionRequest
     * @return
     *     returns com.emc.d2fs.services.distribution_service.StopDistributionResponse
     */
    @WebMethod
    @WebResult(name = "stopDistributionResponse", targetNamespace = "http://www.emc.com/d2fs/services/distribution_service", partName = "stopDistributionResponse")
    public StopDistributionResponse stopDistribution(
        @WebParam(name = "stopDistributionRequest", targetNamespace = "http://www.emc.com/d2fs/services/distribution_service", partName = "stopDistributionRequest")
        StopDistributionRequest stopDistributionRequest);

    /**
     * 
     * @param saveTaxonomyRequest
     * @return
     *     returns com.emc.d2fs.services.d2_taxonomy_service.SaveTaxonomyResponse
     */
    @WebMethod
    @WebResult(name = "saveTaxonomyResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_taxonomy_service", partName = "saveTaxonomyResponse")
    public SaveTaxonomyResponse saveTaxonomy(
        @WebParam(name = "saveTaxonomyRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_taxonomy_service", partName = "saveTaxonomyRequest")
        SaveTaxonomyRequest saveTaxonomyRequest);

    /**
     * 
     * @param canDisplayModeDialogRequest
     * @return
     *     returns com.emc.d2fs.services.d2_massupdate_service.CanDisplayModeDialogResponse
     */
    @WebMethod
    @WebResult(name = "canDisplayModeDialogResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_massupdate_service", partName = "canDisplayModeDialogResponse")
    public CanDisplayModeDialogResponse canDisplayModeDialog(
        @WebParam(name = "canDisplayModeDialogRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_massupdate_service", partName = "canDisplayModeDialogRequest")
        CanDisplayModeDialogRequest canDisplayModeDialogRequest);

    /**
     * 
     * @param getDefaultDfsServerInfoRequest
     * @return
     *     returns com.emc.d2fs.services.download_service.GetDefaultDfsServerInfoResponse
     */
    @WebMethod
    @WebResult(name = "getDefaultDfsServerInfoResponse", targetNamespace = "http://www.emc.com/d2fs/services/download_service", partName = "getDefaultDfsServerInfoResponse")
    public GetDefaultDfsServerInfoResponse getDefaultDfsServerInfo(
        @WebParam(name = "getDefaultDfsServerInfoRequest", targetNamespace = "http://www.emc.com/d2fs/services/download_service", partName = "getDefaultDfsServerInfoRequest")
        GetDefaultDfsServerInfoRequest getDefaultDfsServerInfoRequest);

    /**
     * 
     * @param getDoclistFilterOptionsRequest
     * @return
     *     returns com.emc.d2fs.services.content_service.GetDoclistFilterOptionsResponse
     */
    @WebMethod
    @WebResult(name = "getDoclistFilterOptionsResponse", targetNamespace = "http://www.emc.com/d2fs/services/content_service", partName = "getDoclistFilterOptionsResponse")
    public GetDoclistFilterOptionsResponse getDoclistFilterOptions(
        @WebParam(name = "getDoclistFilterOptionsRequest", targetNamespace = "http://www.emc.com/d2fs/services/content_service", partName = "getDoclistFilterOptionsRequest")
        GetDoclistFilterOptionsRequest getDoclistFilterOptionsRequest);

    /**
     * 
     * @param requestDocbaseUpdateRequest
     * @return
     *     returns com.emc.d2fs.services.d2_dictionary_service.RequestDocbaseUpdateResponse
     */
    @WebMethod
    @WebResult(name = "requestDocbaseUpdateResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_dictionary_service", partName = "requestDocbaseUpdateResponse")
    public RequestDocbaseUpdateResponse requestDocbaseUpdate(
        @WebParam(name = "requestDocbaseUpdateRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_dictionary_service", partName = "requestDocbaseUpdateRequest")
        RequestDocbaseUpdateRequest requestDocbaseUpdateRequest);

    /**
     * 
     * @param getLanguageRequest
     * @return
     *     returns com.emc.d2fs.services.preference_service.GetLanguageResponse
     */
    @WebMethod
    @WebResult(name = "getLanguageResponse", targetNamespace = "http://www.emc.com/d2fs/services/preference_service", partName = "getLanguageResponse")
    public GetLanguageResponse getLanguage(
        @WebParam(name = "getLanguageRequest", targetNamespace = "http://www.emc.com/d2fs/services/preference_service", partName = "getLanguageRequest")
        GetLanguageRequest getLanguageRequest);

    /**
     * 
     * @param getLocationByPathRequest
     * @return
     *     returns com.emc.d2fs.services.locate_service.GetLocationByPathResponse
     */
    @WebMethod
    @WebResult(name = "getLocationByPathResponse", targetNamespace = "http://www.emc.com/d2fs/services/locate_service", partName = "getLocationByPathResponse")
    public GetLocationByPathResponse getLocationByPath(
        @WebParam(name = "getLocationByPathRequest", targetNamespace = "http://www.emc.com/d2fs/services/locate_service", partName = "getLocationByPathRequest")
        GetLocationByPathRequest getLocationByPathRequest);

    /**
     * 
     * @param testTransitionRequest
     * @return
     *     returns com.emc.d2fs.services.d2lifecycle_service.TestTransitionResponse
     */
    @WebMethod
    @WebResult(name = "testTransitionResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2lifecycle_service", partName = "testTransitionResponse")
    public TestTransitionResponse testTransition(
        @WebParam(name = "testTransitionRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2lifecycle_service", partName = "testTransitionRequest")
        TestTransitionRequest testTransitionRequest);

    /**
     * 
     * @param listArchivesRequest
     * @return
     *     returns com.emc.d2fs.services.d2_archive_service.ListArchivesResponse
     */
    @WebMethod
    @WebResult(name = "listArchivesResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_archive_service", partName = "listArchivesResponse")
    public ListArchivesResponse listArchives(
        @WebParam(name = "listArchivesRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_archive_service", partName = "listArchivesRequest")
        ListArchivesRequest listArchivesRequest);

    /**
     * 
     * @param convertToSimpleDocRequest
     * @return
     *     returns com.emc.d2fs.services.vdconvert_service.ConvertToSimpleDocResponse
     */
    @WebMethod
    @WebResult(name = "convertToSimpleDocResponse", targetNamespace = "http://www.emc.com/d2fs/services/vdconvert_service", partName = "convertToSimpleDocResponse")
    public ConvertToSimpleDocResponse convertToSimpleDoc(
        @WebParam(name = "convertToSimpleDocRequest", targetNamespace = "http://www.emc.com/d2fs/services/vdconvert_service", partName = "convertToSimpleDocRequest")
        ConvertToSimpleDocRequest convertToSimpleDocRequest);

    /**
     * 
     * @param testControlledPrintRequest
     * @return
     *     returns com.emc.d2fs.services.checkout_service.TestControlledPrintResponse
     */
    @WebMethod
    @WebResult(name = "testControlledPrintResponse", targetNamespace = "http://www.emc.com/d2fs/services/checkout_service", partName = "testControlledPrintResponse")
    public TestControlledPrintResponse testControlledPrint(
        @WebParam(name = "testControlledPrintRequest", targetNamespace = "http://www.emc.com/d2fs/services/checkout_service", partName = "testControlledPrintRequest")
        TestControlledPrintRequest testControlledPrintRequest);

    /**
     * 
     * @param taxonomyImportRequest
     * @return
     *     returns com.emc.d2fs.services.d2_taxonomy_service.TaxonomyImportResponse
     */
    @WebMethod
    @WebResult(name = "taxonomyImportResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_taxonomy_service", partName = "taxonomyImportResponse")
    public TaxonomyImportResponse taxonomyImport(
        @WebParam(name = "taxonomyImportRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_taxonomy_service", partName = "taxonomyImportRequest")
        TaxonomyImportRequest taxonomyImportRequest);

    /**
     * 
     * @param getTemplatesRequest
     * @return
     *     returns com.emc.d2fs.services.d2_template_service.GetTemplatesResponse
     */
    @WebMethod
    @WebResult(name = "getTemplatesResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_template_service", partName = "getTemplatesResponse")
    public com.emc.d2fs.services.d2_template_service.GetTemplatesResponse getTemplates2(
        @WebParam(name = "getTemplatesRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_template_service", partName = "getTemplatesRequest")
        com.emc.d2fs.services.d2_template_service.GetTemplatesRequest getTemplatesRequest);

    /**
     * 
     * @param setFilterActiveRequest
     * @return
     *     returns com.emc.d2fs.services.preference_service.SetFilterActiveResponse
     */
    @WebMethod
    @WebResult(name = "setFilterActiveResponse", targetNamespace = "http://www.emc.com/d2fs/services/preference_service", partName = "setFilterActiveResponse")
    public SetFilterActiveResponse setFilterActive(
        @WebParam(name = "setFilterActiveRequest", targetNamespace = "http://www.emc.com/d2fs/services/preference_service", partName = "setFilterActiveRequest")
        SetFilterActiveRequest setFilterActiveRequest);

    /**
     * 
     * @param getColumnPreferencesRequest
     * @return
     *     returns com.emc.d2fs.services.preference_service.GetColumnPreferencesResponse
     */
    @WebMethod
    @WebResult(name = "getColumnPreferencesResponse", targetNamespace = "http://www.emc.com/d2fs/services/preference_service", partName = "getColumnPreferencesResponse")
    public GetColumnPreferencesResponse getColumnPreferences(
        @WebParam(name = "getColumnPreferencesRequest", targetNamespace = "http://www.emc.com/d2fs/services/preference_service", partName = "getColumnPreferencesRequest")
        GetColumnPreferencesRequest getColumnPreferencesRequest);

    /**
     * 
     * @param checkObjectConstraintsRequest
     * @return
     *     returns com.emc.d2fs.services.d2_validation_service.CheckObjectConstraintsResponse
     */
    @WebMethod
    @WebResult(name = "checkObjectConstraintsResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_validation_service", partName = "checkObjectConstraintsResponse")
    public CheckObjectConstraintsResponse checkObjectConstraints(
        @WebParam(name = "checkObjectConstraintsRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_validation_service", partName = "checkObjectConstraintsRequest")
        CheckObjectConstraintsRequest checkObjectConstraintsRequest);

    /**
     * 
     * @param isMemberOfGroupRequest
     * @return
     *     returns com.emc.d2fs.services.dialog_service.IsMemberOfGroupResponse
     */
    @WebMethod
    @WebResult(name = "isMemberOfGroupResponse", targetNamespace = "http://www.emc.com/d2fs/services/dialog_service", partName = "isMemberOfGroupResponse")
    public IsMemberOfGroupResponse isMemberOfGroup(
        @WebParam(name = "isMemberOfGroupRequest", targetNamespace = "http://www.emc.com/d2fs/services/dialog_service", partName = "isMemberOfGroupRequest")
        IsMemberOfGroupRequest isMemberOfGroupRequest);

    /**
     * 
     * @param getUIMaxSizeRequest
     * @return
     *     returns com.emc.d2fs.services.creation_service.GetUIMaxSizeResponse
     */
    @WebMethod
    @WebResult(name = "getUIMaxSizeResponse", targetNamespace = "http://www.emc.com/d2fs/services/creation_service", partName = "getUIMaxSizeResponse")
    public GetUIMaxSizeResponse getUIMaxSize(
        @WebParam(name = "getUIMaxSizeRequest", targetNamespace = "http://www.emc.com/d2fs/services/creation_service", partName = "getUIMaxSizeRequest")
        GetUIMaxSizeRequest getUIMaxSizeRequest);

    /**
     * 
     * @param launchDistributionRequest
     * @return
     *     returns com.emc.d2fs.services.distribution_service.LaunchDistributionResponse
     */
    @WebMethod
    @WebResult(name = "launchDistributionResponse", targetNamespace = "http://www.emc.com/d2fs/services/distribution_service", partName = "launchDistributionResponse")
    public LaunchDistributionResponse launchDistribution(
        @WebParam(name = "launchDistributionRequest", targetNamespace = "http://www.emc.com/d2fs/services/distribution_service", partName = "launchDistributionRequest")
        LaunchDistributionRequest launchDistributionRequest);

    /**
     * 
     * @param canRejectTaskRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.CanRejectTaskResponse
     */
    @WebMethod
    @WebResult(name = "canRejectTaskResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "canRejectTaskResponse")
    public CanRejectTaskResponse canRejectTask(
        @WebParam(name = "canRejectTaskRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "canRejectTaskRequest")
        CanRejectTaskRequest canRejectTaskRequest);

    /**
     * 
     * @param setColumnPreferenceRequest
     * @return
     *     returns com.emc.d2fs.services.preference_service.SetColumnPreferenceResponse
     */
    @WebMethod
    @WebResult(name = "setColumnPreferenceResponse", targetNamespace = "http://www.emc.com/d2fs/services/preference_service", partName = "setColumnPreferenceResponse")
    public SetColumnPreferenceResponse setColumnPreference(
        @WebParam(name = "setColumnPreferenceRequest", targetNamespace = "http://www.emc.com/d2fs/services/preference_service", partName = "setColumnPreferenceRequest")
        SetColumnPreferenceRequest setColumnPreferenceRequest);

    /**
     * 
     * @param testNextStateRequest
     * @return
     *     returns com.emc.d2fs.services.d2lifecycle_service.TestNextStateResponse
     */
    @WebMethod
    @WebResult(name = "testNextStateResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2lifecycle_service", partName = "testNextStateResponse")
    public TestNextStateResponse testNextState(
        @WebParam(name = "testNextStateRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2lifecycle_service", partName = "testNextStateRequest")
        TestNextStateRequest testNextStateRequest);

    /**
     * 
     * @param getLocationRequest
     * @return
     *     returns com.emc.d2fs.services.locate_service.GetLocationResponse
     */
    @WebMethod
    @WebResult(name = "getLocationResponse", targetNamespace = "http://www.emc.com/d2fs/services/locate_service", partName = "getLocationResponse")
    public GetLocationResponse getLocation(
        @WebParam(name = "getLocationRequest", targetNamespace = "http://www.emc.com/d2fs/services/locate_service", partName = "getLocationRequest")
        GetLocationRequest getLocationRequest);

    /**
     * 
     * @param checkAttributesConstraintsRequest
     * @return
     *     returns com.emc.d2fs.services.d2_validation_service.CheckAttributesConstraintsResponse
     */
    @WebMethod
    @WebResult(name = "checkAttributesConstraintsResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_validation_service", partName = "checkAttributesConstraintsResponse")
    public CheckAttributesConstraintsResponse checkAttributesConstraints(
        @WebParam(name = "checkAttributesConstraintsRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_validation_service", partName = "checkAttributesConstraintsRequest")
        CheckAttributesConstraintsRequest checkAttributesConstraintsRequest);

    /**
     * 
     * @param refreshDictionaryCacheRequest
     * @return
     *     returns com.emc.d2fs.services.d2_cache_service.RefreshDictionaryCacheResponse
     */
    @WebMethod
    @WebResult(name = "refreshDictionaryCacheResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_cache_service", partName = "refreshDictionaryCacheResponse")
    public RefreshDictionaryCacheResponse refreshDictionaryCache(
        @WebParam(name = "refreshDictionaryCacheRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_cache_service", partName = "refreshDictionaryCacheRequest")
        RefreshDictionaryCacheRequest refreshDictionaryCacheRequest);

    /**
     * 
     * @param getColumnTypeSettingRequest
     * @return
     *     returns com.emc.d2fs.services.preference_service.GetColumnTypeSettingResponse
     */
    @WebMethod
    @WebResult(name = "getColumnTypeSettingResponse", targetNamespace = "http://www.emc.com/d2fs/services/preference_service", partName = "getColumnTypeSettingResponse")
    public GetColumnTypeSettingResponse getColumnTypeSetting(
        @WebParam(name = "getColumnTypeSettingRequest", targetNamespace = "http://www.emc.com/d2fs/services/preference_service", partName = "getColumnTypeSettingRequest")
        GetColumnTypeSettingRequest getColumnTypeSettingRequest);

    /**
     * 
     * @param savePropertiesRequest
     * @return
     *     returns com.emc.d2fs.services.property_service.SavePropertiesResponse
     */
    @WebMethod
    @WebResult(name = "savePropertiesResponse", targetNamespace = "http://www.emc.com/d2fs/services/property_service", partName = "savePropertiesResponse")
    public SavePropertiesResponse saveProperties(
        @WebParam(name = "savePropertiesRequest", targetNamespace = "http://www.emc.com/d2fs/services/property_service", partName = "savePropertiesRequest")
        SavePropertiesRequest savePropertiesRequest);

    /**
     * 
     * @param getCheckoutCountRequest
     * @return
     *     returns com.emc.d2fs.services.checkout_service.GetCheckoutCountResponse
     */
    @WebMethod
    @WebResult(name = "getCheckoutCountResponse", targetNamespace = "http://www.emc.com/d2fs/services/checkout_service", partName = "getCheckoutCountResponse")
    public GetCheckoutCountResponse getCheckoutCount(
        @WebParam(name = "getCheckoutCountRequest", targetNamespace = "http://www.emc.com/d2fs/services/checkout_service", partName = "getCheckoutCountRequest")
        GetCheckoutCountRequest getCheckoutCountRequest);

    /**
     * 
     * @param executeMethodRequest
     * @return
     *     returns com.emc.d2fs.services.d2_method_service.ExecuteMethodResponse
     */
    @WebMethod
    @WebResult(name = "executeMethodResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_method_service", partName = "executeMethodResponse")
    public ExecuteMethodResponse executeMethod(
        @WebParam(name = "executeMethodRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_method_service", partName = "executeMethodRequest")
        ExecuteMethodRequest executeMethodRequest);

    /**
     * 
     * @param launchScheduledWorkflowRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.LaunchScheduledWorkflowResponse
     */
    @WebMethod
    @WebResult(name = "launchScheduledWorkflowResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "launchScheduledWorkflowResponse")
    public LaunchScheduledWorkflowResponse launchScheduledWorkflow(
        @WebParam(name = "launchScheduledWorkflowRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "launchScheduledWorkflowRequest")
        LaunchScheduledWorkflowRequest launchScheduledWorkflowRequest);

    /**
     * 
     * @param getColumnSelectSettingRequest
     * @return
     *     returns com.emc.d2fs.services.preference_service.GetColumnSelectSettingResponse
     */
    @WebMethod
    @WebResult(name = "getColumnSelectSettingResponse", targetNamespace = "http://www.emc.com/d2fs/services/preference_service", partName = "getColumnSelectSettingResponse")
    public GetColumnSelectSettingResponse getColumnSelectSetting(
        @WebParam(name = "getColumnSelectSettingRequest", targetNamespace = "http://www.emc.com/d2fs/services/preference_service", partName = "getColumnSelectSettingRequest")
        GetColumnSelectSettingRequest getColumnSelectSettingRequest);

    /**
     * 
     * @param checkinRequest
     * @return
     *     returns com.emc.d2fs.services.checkin_service.CheckinResponse
     */
    @WebMethod
    @WebResult(name = "checkinResponse", targetNamespace = "http://www.emc.com/d2fs/services/checkin_service", partName = "checkinResponse")
    public CheckinResponse checkin(
        @WebParam(name = "checkinRequest", targetNamespace = "http://www.emc.com/d2fs/services/checkin_service", partName = "checkinRequest")
        CheckinRequest checkinRequest);

    /**
     * 
     * @param setTemplateRequest
     * @return
     *     returns com.emc.d2fs.services.creation_service.SetTemplateResponse
     */
    @WebMethod
    @WebResult(name = "setTemplateResponse", targetNamespace = "http://www.emc.com/d2fs/services/creation_service", partName = "setTemplateResponse")
    public com.emc.d2fs.services.creation_service.SetTemplateResponse setTemplate(
        @WebParam(name = "setTemplateRequest", targetNamespace = "http://www.emc.com/d2fs/services/creation_service", partName = "setTemplateRequest")
        com.emc.d2fs.services.creation_service.SetTemplateRequest setTemplateRequest);

    /**
     * 
     * @param getSearchConditionsRequest
     * @return
     *     returns com.emc.d2fs.services.search_service.GetSearchConditionsResponse
     */
    @WebMethod
    @WebResult(name = "getSearchConditionsResponse", targetNamespace = "http://www.emc.com/d2fs/services/search_service", partName = "getSearchConditionsResponse")
    public GetSearchConditionsResponse getSearchConditions(
        @WebParam(name = "getSearchConditionsRequest", targetNamespace = "http://www.emc.com/d2fs/services/search_service", partName = "getSearchConditionsRequest")
        GetSearchConditionsRequest getSearchConditionsRequest);

    /**
     * 
     * @param getPortalMenuWidthRequest
     * @return
     *     returns com.emc.d2fs.services.x3config_service.GetPortalMenuWidthResponse
     */
    @WebMethod
    @WebResult(name = "getPortalMenuWidthResponse", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getPortalMenuWidthResponse")
    public GetPortalMenuWidthResponse getPortalMenuWidth(
        @WebParam(name = "getPortalMenuWidthRequest", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getPortalMenuWidthRequest")
        GetPortalMenuWidthRequest getPortalMenuWidthRequest);

    /**
     * 
     * @param getReportFileNameRequest
     * @return
     *     returns com.emc.d2fs.services.distribution_service.GetReportFileNameResponse
     */
    @WebMethod
    @WebResult(name = "getReportFileNameResponse", targetNamespace = "http://www.emc.com/d2fs/services/distribution_service", partName = "getReportFileNameResponse")
    public GetReportFileNameResponse getReportFileName(
        @WebParam(name = "getReportFileNameRequest", targetNamespace = "http://www.emc.com/d2fs/services/distribution_service", partName = "getReportFileNameRequest")
        GetReportFileNameRequest getReportFileNameRequest);

    /**
     * 
     * @param changeStateOnEventRequest
     * @return
     *     returns com.emc.d2fs.services.d2lifecycle_service.ChangeStateOnEventResponse
     */
    @WebMethod
    @WebResult(name = "changeStateOnEventResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2lifecycle_service", partName = "changeStateOnEventResponse")
    public ChangeStateOnEventResponse changeStateOnEvent(
        @WebParam(name = "changeStateOnEventRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2lifecycle_service", partName = "changeStateOnEventRequest")
        ChangeStateOnEventRequest changeStateOnEventRequest);

    /**
     * 
     * @param getLinkInfoRequest
     * @return
     *     returns com.emc.d2fs.services.d2_linked_document_service.GetLinkInfoResponse
     */
    @WebMethod
    @WebResult(name = "getLinkInfoResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_linked_document_service", partName = "getLinkInfoResponse")
    public GetLinkInfoResponse getLinkInfo(
        @WebParam(name = "getLinkInfoRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_linked_document_service", partName = "getLinkInfoRequest")
        GetLinkInfoRequest getLinkInfoRequest);

    /**
     * 
     * @param moveVDChildRequest
     * @return
     *     returns com.emc.d2fs.services.vdcontent_service.MoveVDChildResponse
     */
    @WebMethod
    @WebResult(name = "moveVDChildResponse", targetNamespace = "http://www.emc.com/d2fs/services/vdcontent_service", partName = "moveVDChildResponse")
    public MoveVDChildResponse moveVDChild(
        @WebParam(name = "moveVDChildRequest", targetNamespace = "http://www.emc.com/d2fs/services/vdcontent_service", partName = "moveVDChildRequest")
        MoveVDChildRequest moveVDChildRequest);

    /**
     * 
     * @param changeStateRequest
     * @return
     *     returns com.emc.d2fs.services.d2lifecycle_service.ChangeStateResponse
     */
    @WebMethod
    @WebResult(name = "changeStateResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2lifecycle_service", partName = "changeStateResponse")
    public ChangeStateResponse changeState(
        @WebParam(name = "changeStateRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2lifecycle_service", partName = "changeStateRequest")
        ChangeStateRequest changeStateRequest);

    /**
     * 
     * @param getExportFolderRequest
     * @return
     *     returns com.emc.d2fs.services.export_service.GetExportFolderResponse
     */
    @WebMethod
    @WebResult(name = "getExportFolderResponse", targetNamespace = "http://www.emc.com/d2fs/services/export_service", partName = "getExportFolderResponse")
    public GetExportFolderResponse getExportFolder(
        @WebParam(name = "getExportFolderRequest", targetNamespace = "http://www.emc.com/d2fs/services/export_service", partName = "getExportFolderRequest")
        GetExportFolderRequest getExportFolderRequest);

    /**
     * 
     * @param createSearchCategoryRequest
     * @return
     *     returns com.emc.d2fs.services.search_service.CreateSearchCategoryResponse
     */
    @WebMethod
    @WebResult(name = "createSearchCategoryResponse", targetNamespace = "http://www.emc.com/d2fs/services/search_service", partName = "createSearchCategoryResponse")
    public CreateSearchCategoryResponse createSearchCategory(
        @WebParam(name = "createSearchCategoryRequest", targetNamespace = "http://www.emc.com/d2fs/services/search_service", partName = "createSearchCategoryRequest")
        CreateSearchCategoryRequest createSearchCategoryRequest);

    /**
     * 
     * @param testComparisonDirectionRequest
     * @return
     *     returns com.emc.d2fs.services.annotation_service.TestComparisonDirectionResponse
     */
    @WebMethod
    @WebResult(name = "testComparisonDirectionResponse", targetNamespace = "http://www.emc.com/d2fs/services/annotation_service", partName = "testComparisonDirectionResponse")
    public TestComparisonDirectionResponse testComparisonDirection(
        @WebParam(name = "testComparisonDirectionRequest", targetNamespace = "http://www.emc.com/d2fs/services/annotation_service", partName = "testComparisonDirectionRequest")
        TestComparisonDirectionRequest testComparisonDirectionRequest);

    /**
     * 
     * @param queuePdfRenderRequest
     * @return
     *     returns com.emc.d2fs.services.d2_pdfrender_service.QueuePdfRenderResponse
     */
    @WebMethod
    @WebResult(name = "queuePdfRenderResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_pdfrender_service", partName = "queuePdfRenderResponse")
    public QueuePdfRenderResponse queuePdfRender(
        @WebParam(name = "queuePdfRenderRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_pdfrender_service", partName = "queuePdfRenderRequest")
        QueuePdfRenderRequest queuePdfRenderRequest);

    /**
     * 
     * @param vdSnapshotRequest
     * @return
     *     returns com.emc.d2fs.services.vdsnapshot_service.VdSnapshotResponse
     */
    @WebMethod
    @WebResult(name = "vdSnapshotResponse", targetNamespace = "http://www.emc.com/d2fs/services/vdsnapshot_service", partName = "vdSnapshotResponse")
    public VdSnapshotResponse vdSnapshot(
        @WebParam(name = "vdSnapshotRequest", targetNamespace = "http://www.emc.com/d2fs/services/vdsnapshot_service", partName = "vdSnapshotRequest")
        VdSnapshotRequest vdSnapshotRequest);

    /**
     * 
     * @param getAvailableWidgetsRequest
     * @return
     *     returns com.emc.d2fs.services.x3config_service.GetAvailableWidgetsResponse
     */
    @WebMethod
    @WebResult(name = "getAvailableWidgetsResponse", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getAvailableWidgetsResponse")
    public GetAvailableWidgetsResponse getAvailableWidgets(
        @WebParam(name = "getAvailableWidgetsRequest", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getAvailableWidgetsRequest")
        GetAvailableWidgetsRequest getAvailableWidgetsRequest);

    /**
     * 
     * @param isTaskAcquiredRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.IsTaskAcquiredResponse
     */
    @WebMethod
    @WebResult(name = "isTaskAcquiredResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "isTaskAcquiredResponse")
    public IsTaskAcquiredResponse isTaskAcquired(
        @WebParam(name = "isTaskAcquiredRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "isTaskAcquiredRequest")
        IsTaskAcquiredRequest isTaskAcquiredRequest);

    /**
     * 
     * @param isNoCreationProfileRequest
     * @return
     *     returns com.emc.d2fs.services.creation_service.IsNoCreationProfileResponse
     */
    @WebMethod
    @WebResult(name = "isNoCreationProfileResponse", targetNamespace = "http://www.emc.com/d2fs/services/creation_service", partName = "isNoCreationProfileResponse")
    public IsNoCreationProfileResponse isNoCreationProfile(
        @WebParam(name = "isNoCreationProfileRequest", targetNamespace = "http://www.emc.com/d2fs/services/creation_service", partName = "isNoCreationProfileRequest")
        IsNoCreationProfileRequest isNoCreationProfileRequest);

    /**
     * 
     * @param saveVersionDictionaryRequest
     * @return
     *     returns com.emc.d2fs.services.d2_dictionary_service.SaveVersionDictionaryResponse
     */
    @WebMethod
    @WebResult(name = "saveVersionDictionaryResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_dictionary_service", partName = "saveVersionDictionaryResponse")
    public SaveVersionDictionaryResponse saveVersionDictionary(
        @WebParam(name = "saveVersionDictionaryRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_dictionary_service", partName = "saveVersionDictionaryRequest")
        SaveVersionDictionaryRequest saveVersionDictionaryRequest);

    /**
     * 
     * @param setTemplateRequest
     * @return
     *     returns com.emc.d2fs.services.d2_template_service.SetTemplateResponse
     */
    @WebMethod
    @WebResult(name = "setTemplateResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_template_service", partName = "setTemplateResponse")
    public com.emc.d2fs.services.d2_template_service.SetTemplateResponse setTemplate2(
        @WebParam(name = "setTemplateRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_template_service", partName = "setTemplateRequest")
        com.emc.d2fs.services.d2_template_service.SetTemplateRequest setTemplateRequest);

    /**
     * 
     * @param testPdfRenderRequest
     * @return
     *     returns com.emc.d2fs.services.d2_pdfrender_service.TestPdfRenderResponse
     */
    @WebMethod
    @WebResult(name = "testPdfRenderResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_pdfrender_service", partName = "testPdfRenderResponse")
    public TestPdfRenderResponse testPdfRender(
        @WebParam(name = "testPdfRenderRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_pdfrender_service", partName = "testPdfRenderRequest")
        TestPdfRenderRequest testPdfRenderRequest);

    /**
     * 
     * @param getNativeAnnotationRequest
     * @return
     *     returns com.emc.d2fs.services.annotation_service.GetNativeAnnotationResponse
     */
    @WebMethod
    @WebResult(name = "getNativeAnnotationResponse", targetNamespace = "http://www.emc.com/d2fs/services/annotation_service", partName = "getNativeAnnotationResponse")
    public GetNativeAnnotationResponse getNativeAnnotation(
        @WebParam(name = "getNativeAnnotationRequest", targetNamespace = "http://www.emc.com/d2fs/services/annotation_service", partName = "getNativeAnnotationRequest")
        GetNativeAnnotationRequest getNativeAnnotationRequest);

    /**
     * 
     * @param getExportUrlRequest
     * @return
     *     returns com.emc.d2fs.services.export_service.GetExportUrlResponse
     */
    @WebMethod
    @WebResult(name = "getExportUrlResponse", targetNamespace = "http://www.emc.com/d2fs/services/export_service", partName = "getExportUrlResponse")
    public GetExportUrlResponse getExportUrl(
        @WebParam(name = "getExportUrlRequest", targetNamespace = "http://www.emc.com/d2fs/services/export_service", partName = "getExportUrlRequest")
        GetExportUrlRequest getExportUrlRequest);

    /**
     * 
     * @param setColumnSelectSettingRequest
     * @return
     *     returns com.emc.d2fs.services.preference_service.SetColumnSelectSettingResponse
     */
    @WebMethod
    @WebResult(name = "setColumnSelectSettingResponse", targetNamespace = "http://www.emc.com/d2fs/services/preference_service", partName = "setColumnSelectSettingResponse")
    public SetColumnSelectSettingResponse setColumnSelectSetting(
        @WebParam(name = "setColumnSelectSettingRequest", targetNamespace = "http://www.emc.com/d2fs/services/preference_service", partName = "setColumnSelectSettingRequest")
        SetColumnSelectSettingRequest setColumnSelectSettingRequest);

    /**
     * 
     * @param isNoContentAuthorizedRequest
     * @return
     *     returns com.emc.d2fs.services.creation_service.IsNoContentAuthorizedResponse
     */
    @WebMethod
    @WebResult(name = "isNoContentAuthorizedResponse", targetNamespace = "http://www.emc.com/d2fs/services/creation_service", partName = "isNoContentAuthorizedResponse")
    public com.emc.d2fs.services.creation_service.IsNoContentAuthorizedResponse isNoContentAuthorized(
        @WebParam(name = "isNoContentAuthorizedRequest", targetNamespace = "http://www.emc.com/d2fs/services/creation_service", partName = "isNoContentAuthorizedRequest")
        com.emc.d2fs.services.creation_service.IsNoContentAuthorizedRequest isNoContentAuthorizedRequest);

    /**
     * 
     * @param createRelationRequest
     * @return
     *     returns com.emc.d2fs.services.d2_relation_service.CreateRelationResponse
     */
    @WebMethod
    @WebResult(name = "createRelationResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_relation_service", partName = "createRelationResponse")
    public CreateRelationResponse createRelation(
        @WebParam(name = "createRelationRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_relation_service", partName = "createRelationRequest")
        CreateRelationRequest createRelationRequest);

    /**
     * 
     * @param saveSearchRequest
     * @return
     *     returns com.emc.d2fs.services.search_service.SaveSearchResponse
     */
    @WebMethod
    @WebResult(name = "saveSearchResponse", targetNamespace = "http://www.emc.com/d2fs/services/search_service", partName = "saveSearchResponse")
    public SaveSearchResponse saveSearch(
        @WebParam(name = "saveSearchRequest", targetNamespace = "http://www.emc.com/d2fs/services/search_service", partName = "saveSearchRequest")
        SaveSearchRequest saveSearchRequest);

    /**
     * 
     * @param getRepositoryRequest
     * @return
     *     returns com.emc.d2fs.services.repository_service.GetRepositoryResponse
     */
    @WebMethod
    @WebResult(name = "getRepositoryResponse", targetNamespace = "http://www.emc.com/d2fs/services/repository_service", partName = "getRepositoryResponse")
    public GetRepositoryResponse getRepository(
        @WebParam(name = "getRepositoryRequest", targetNamespace = "http://www.emc.com/d2fs/services/repository_service", partName = "getRepositoryRequest")
        GetRepositoryRequest getRepositoryRequest);

    /**
     * 
     * @param getDefaultOfflineSkinRequest
     * @return
     *     returns com.emc.d2fs.services.x3config_service.GetDefaultOfflineSkinResponse
     */
    @WebMethod
    @WebResult(name = "getDefaultOfflineSkinResponse", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getDefaultOfflineSkinResponse")
    public GetDefaultOfflineSkinResponse getDefaultOfflineSkin(
        @WebParam(name = "getDefaultOfflineSkinRequest", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getDefaultOfflineSkinRequest")
        GetDefaultOfflineSkinRequest getDefaultOfflineSkinRequest);

    /**
     * 
     * @param childrenIdRequest
     * @return
     *     returns com.emc.d2fs.services.d2_relation_service.ChildrenIdResponse
     */
    @WebMethod
    @WebResult(name = "childrenIdResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_relation_service", partName = "childrenIdResponse")
    public ChildrenIdResponse childrenId(
        @WebParam(name = "childrenIdRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_relation_service", partName = "childrenIdRequest")
        ChildrenIdRequest childrenIdRequest);

    /**
     * 
     * @param getImportTaxonomyUrlRequest
     * @return
     *     returns com.emc.d2fs.services.d2_taxonomy_service.GetImportTaxonomyUrlResponse
     */
    @WebMethod
    @WebResult(name = "getImportTaxonomyUrlResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_taxonomy_service", partName = "getImportTaxonomyUrlResponse")
    public GetImportTaxonomyUrlResponse getImportTaxonomyUrl(
        @WebParam(name = "getImportTaxonomyUrlRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_taxonomy_service", partName = "getImportTaxonomyUrlRequest")
        GetImportTaxonomyUrlRequest getImportTaxonomyUrlRequest);

    /**
     * 
     * @param getAvailableSpacesRequest
     * @return
     *     returns com.emc.d2fs.services.x3config_service.GetAvailableSpacesResponse
     */
    @WebMethod
    @WebResult(name = "getAvailableSpacesResponse", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getAvailableSpacesResponse")
    public GetAvailableSpacesResponse getAvailableSpaces(
        @WebParam(name = "getAvailableSpacesRequest", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getAvailableSpacesRequest")
        GetAvailableSpacesRequest getAvailableSpacesRequest);

    /**
     * 
     * @param getLastSearchQueryTermRequest
     * @return
     *     returns com.emc.d2fs.services.search_service.GetLastSearchQueryTermResponse
     */
    @WebMethod
    @WebResult(name = "getLastSearchQueryTermResponse", targetNamespace = "http://www.emc.com/d2fs/services/search_service", partName = "getLastSearchQueryTermResponse")
    public GetLastSearchQueryTermResponse getLastSearchQueryTerm(
        @WebParam(name = "getLastSearchQueryTermRequest", targetNamespace = "http://www.emc.com/d2fs/services/search_service", partName = "getLastSearchQueryTermRequest")
        GetLastSearchQueryTermRequest getLastSearchQueryTermRequest);

    /**
     * 
     * @param getAcrobatAnnotateURLRequest
     * @return
     *     returns com.emc.d2fs.services.acrobat_annotate_service.GetAcrobatAnnotateURLResponse
     */
    @WebMethod
    @WebResult(name = "getAcrobatAnnotateURLResponse", targetNamespace = "http://www.emc.com/d2fs/services/acrobat_annotate_service", partName = "getAcrobatAnnotateURLResponse")
    public GetAcrobatAnnotateURLResponse getAcrobatAnnotateURL(
        @WebParam(name = "getAcrobatAnnotateURLRequest", targetNamespace = "http://www.emc.com/d2fs/services/acrobat_annotate_service", partName = "getAcrobatAnnotateURLRequest")
        GetAcrobatAnnotateURLRequest getAcrobatAnnotateURLRequest);

    /**
     * 
     * @param canAddTaskNoteRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.CanAddTaskNoteResponse
     */
    @WebMethod
    @WebResult(name = "canAddTaskNoteResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "canAddTaskNoteResponse")
    public CanAddTaskNoteResponse canAddTaskNote(
        @WebParam(name = "canAddTaskNoteRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "canAddTaskNoteRequest")
        CanAddTaskNoteRequest canAddTaskNoteRequest);

    /**
     * 
     * @param getFilteredContentRequest
     * @return
     *     returns com.emc.d2fs.services.content_service.GetFilteredContentResponse
     */
    @WebMethod
    @WebResult(name = "getFilteredContentResponse", targetNamespace = "http://www.emc.com/d2fs/services/content_service", partName = "getFilteredContentResponse")
    public GetFilteredContentResponse getFilteredContent(
        @WebParam(name = "getFilteredContentRequest", targetNamespace = "http://www.emc.com/d2fs/services/content_service", partName = "getFilteredContentRequest")
        GetFilteredContentRequest getFilteredContentRequest);

    /**
     * 
     * @param setVDChildBindingVersionsRequest
     * @return
     *     returns com.emc.d2fs.services.vdcontent_service.SetVDChildBindingVersionsResponse
     */
    @WebMethod
    @WebResult(name = "setVDChildBindingVersionsResponse", targetNamespace = "http://www.emc.com/d2fs/services/vdcontent_service", partName = "setVDChildBindingVersionsResponse")
    public SetVDChildBindingVersionsResponse setVDChildBindingVersions(
        @WebParam(name = "setVDChildBindingVersionsRequest", targetNamespace = "http://www.emc.com/d2fs/services/vdcontent_service", partName = "setVDChildBindingVersionsRequest")
        SetVDChildBindingVersionsRequest setVDChildBindingVersionsRequest);

    /**
     * 
     * @param getCheckinConfigRequest
     * @return
     *     returns com.emc.d2fs.services.checkin_service.GetCheckinConfigResponse
     */
    @WebMethod
    @WebResult(name = "getCheckinConfigResponse", targetNamespace = "http://www.emc.com/d2fs/services/checkin_service", partName = "getCheckinConfigResponse")
    public GetCheckinConfigResponse getCheckinConfig(
        @WebParam(name = "getCheckinConfigRequest", targetNamespace = "http://www.emc.com/d2fs/services/checkin_service", partName = "getCheckinConfigRequest")
        GetCheckinConfigRequest getCheckinConfigRequest);

    /**
     * 
     * @param getContentRequest
     * @return
     *     returns com.emc.d2fs.services.content_service.GetContentResponse
     */
    @WebMethod
    @WebResult(name = "getContentResponse", targetNamespace = "http://www.emc.com/d2fs/services/content_service", partName = "getContentResponse")
    public GetContentResponse getContent(
        @WebParam(name = "getContentRequest", targetNamespace = "http://www.emc.com/d2fs/services/content_service", partName = "getContentRequest")
        GetContentRequest getContentRequest);

    /**
     * 
     * @param cancelCheckoutAllRequest
     * @return
     *     returns com.emc.d2fs.services.checkout_service.CancelCheckoutAllResponse
     */
    @WebMethod
    @WebResult(name = "cancelCheckoutAllResponse", targetNamespace = "http://www.emc.com/d2fs/services/checkout_service", partName = "cancelCheckoutAllResponse")
    public CancelCheckoutAllResponse cancelCheckoutAll(
        @WebParam(name = "cancelCheckoutAllRequest", targetNamespace = "http://www.emc.com/d2fs/services/checkout_service", partName = "cancelCheckoutAllRequest")
        CancelCheckoutAllRequest cancelCheckoutAllRequest);

    /**
     * 
     * @param getLabelsRequest
     * @return
     *     returns com.emc.d2fs.services.dialog_service.GetLabelsResponse
     */
    @WebMethod
    @WebResult(name = "getLabelsResponse", targetNamespace = "http://www.emc.com/d2fs/services/dialog_service", partName = "getLabelsResponse")
    public GetLabelsResponse getLabels(
        @WebParam(name = "getLabelsRequest", targetNamespace = "http://www.emc.com/d2fs/services/dialog_service", partName = "getLabelsRequest")
        GetLabelsRequest getLabelsRequest);

    /**
     * 
     * @param getBrowserContentRequest
     * @return
     *     returns com.emc.d2fs.services.browser_service.GetBrowserContentResponse
     */
    @WebMethod
    @WebResult(name = "getBrowserContentResponse", targetNamespace = "http://www.emc.com/d2fs/services/browser_service", partName = "getBrowserContentResponse")
    public GetBrowserContentResponse getBrowserContent(
        @WebParam(name = "getBrowserContentRequest", targetNamespace = "http://www.emc.com/d2fs/services/browser_service", partName = "getBrowserContentRequest")
        GetBrowserContentRequest getBrowserContentRequest);

    /**
     * 
     * @param canAbortWorkflowRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.CanAbortWorkflowResponse
     */
    @WebMethod
    @WebResult(name = "canAbortWorkflowResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "canAbortWorkflowResponse")
    public CanAbortWorkflowResponse canAbortWorkflow(
        @WebParam(name = "canAbortWorkflowRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "canAbortWorkflowRequest")
        CanAbortWorkflowRequest canAbortWorkflowRequest);

    /**
     * 
     * @param getLocationByIdRequest
     * @return
     *     returns com.emc.d2fs.services.locate_service.GetLocationByIdResponse
     */
    @WebMethod
    @WebResult(name = "getLocationByIdResponse", targetNamespace = "http://www.emc.com/d2fs/services/locate_service", partName = "getLocationByIdResponse")
    public GetLocationByIdResponse getLocationById(
        @WebParam(name = "getLocationByIdRequest", targetNamespace = "http://www.emc.com/d2fs/services/locate_service", partName = "getLocationByIdRequest")
        GetLocationByIdRequest getLocationByIdRequest);

    /**
     * 
     * @param subscribeRequest
     * @return
     *     returns com.emc.d2fs.services.subscriptions_service.SubscribeResponse
     */
    @WebMethod
    @WebResult(name = "subscribeResponse", targetNamespace = "http://www.emc.com/d2fs/services/subscriptions_service", partName = "subscribeResponse")
    public SubscribeResponse subscribe(
        @WebParam(name = "subscribeRequest", targetNamespace = "http://www.emc.com/d2fs/services/subscriptions_service", partName = "subscribeRequest")
        SubscribeRequest subscribeRequest);

    /**
     * 
     * @param checkUniquenessRequest
     * @return
     *     returns com.emc.d2fs.services.d2_validation_service.CheckUniquenessResponse
     */
    @WebMethod
    @WebResult(name = "checkUniquenessResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_validation_service", partName = "checkUniquenessResponse")
    public CheckUniquenessResponse checkUniqueness(
        @WebParam(name = "checkUniquenessRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_validation_service", partName = "checkUniquenessRequest")
        CheckUniquenessRequest checkUniquenessRequest);

    /**
     * 
     * @param isAFolderOrACabinetRequest
     * @return
     *     returns com.emc.d2fs.services.creation_service.IsAFolderOrACabinetResponse
     */
    @WebMethod
    @WebResult(name = "isAFolderOrACabinetResponse", targetNamespace = "http://www.emc.com/d2fs/services/creation_service", partName = "isAFolderOrACabinetResponse")
    public IsAFolderOrACabinetResponse isAFolderOrACabinet(
        @WebParam(name = "isAFolderOrACabinetRequest", targetNamespace = "http://www.emc.com/d2fs/services/creation_service", partName = "isAFolderOrACabinetRequest")
        IsAFolderOrACabinetRequest isAFolderOrACabinetRequest);

    /**
     * 
     * @param acquireTaskRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.AcquireTaskResponse
     */
    @WebMethod
    @WebResult(name = "acquireTaskResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "acquireTaskResponse")
    public AcquireTaskResponse acquireTask(
        @WebParam(name = "acquireTaskRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "acquireTaskRequest")
        AcquireTaskRequest acquireTaskRequest);

    /**
     * 
     * @param dictionaryOperationRequest
     * @return
     *     returns com.emc.d2fs.services.d2_dictionary_service.DictionaryOperationResponse
     */
    @WebMethod
    @WebResult(name = "dictionaryOperationResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_dictionary_service", partName = "dictionaryOperationResponse")
    public DictionaryOperationResponse dictionaryOperation(
        @WebParam(name = "dictionaryOperationRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_dictionary_service", partName = "dictionaryOperationRequest")
        DictionaryOperationRequest dictionaryOperationRequest);

    /**
     * 
     * @param processTaskRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.ProcessTaskResponse
     */
    @WebMethod
    @WebResult(name = "processTaskResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "processTaskResponse")
    public ProcessTaskResponse processTask(
        @WebParam(name = "processTaskRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "processTaskRequest")
        ProcessTaskRequest processTaskRequest);

    /**
     * 
     * @param getPortalRowHeightRequest
     * @return
     *     returns com.emc.d2fs.services.x3config_service.GetPortalRowHeightResponse
     */
    @WebMethod
    @WebResult(name = "getPortalRowHeightResponse", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getPortalRowHeightResponse")
    public GetPortalRowHeightResponse getPortalRowHeight(
        @WebParam(name = "getPortalRowHeightRequest", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getPortalRowHeightRequest")
        GetPortalRowHeightRequest getPortalRowHeightRequest);

    /**
     * 
     * @param getExportValuesUrlRequest
     * @return
     *     returns com.emc.d2fs.services.dialog_service.GetExportValuesUrlResponse
     */
    @WebMethod
    @WebResult(name = "getExportValuesUrlResponse", targetNamespace = "http://www.emc.com/d2fs/services/dialog_service", partName = "getExportValuesUrlResponse")
    public GetExportValuesUrlResponse getExportValuesUrl(
        @WebParam(name = "getExportValuesUrlRequest", targetNamespace = "http://www.emc.com/d2fs/services/dialog_service", partName = "getExportValuesUrlRequest")
        GetExportValuesUrlRequest getExportValuesUrlRequest);

    /**
     * 
     * @param isManualAcquisitionTaskRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.IsManualAcquisitionTaskResponse
     */
    @WebMethod
    @WebResult(name = "isManualAcquisitionTaskResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "isManualAcquisitionTaskResponse")
    public IsManualAcquisitionTaskResponse isManualAcquisitionTask(
        @WebParam(name = "isManualAcquisitionTaskRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "isManualAcquisitionTaskRequest")
        IsManualAcquisitionTaskRequest isManualAcquisitionTaskRequest);

    /**
     * 
     * @param getIsWidgetInViewRequest
     * @return
     *     returns com.emc.d2fs.services.x3config_service.GetIsWidgetInViewResponse
     */
    @WebMethod
    @WebResult(name = "getIsWidgetInViewResponse", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getIsWidgetInViewResponse")
    public GetIsWidgetInViewResponse getIsWidgetInView(
        @WebParam(name = "getIsWidgetInViewRequest", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getIsWidgetInViewRequest")
        GetIsWidgetInViewRequest getIsWidgetInViewRequest);

    /**
     * 
     * @param getAvailableSkinsRequest
     * @return
     *     returns com.emc.d2fs.services.x3config_service.GetAvailableSkinsResponse
     */
    @WebMethod
    @WebResult(name = "getAvailableSkinsResponse", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getAvailableSkinsResponse")
    public GetAvailableSkinsResponse getAvailableSkins(
        @WebParam(name = "getAvailableSkinsRequest", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getAvailableSkinsRequest")
        GetAvailableSkinsRequest getAvailableSkinsRequest);

    /**
     * 
     * @param setVdTemplateRequest
     * @return
     *     returns com.emc.d2fs.services.d2_vd_template_service.SetVdTemplateResponse
     */
    @WebMethod
    @WebResult(name = "setVdTemplateResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_vd_template_service", partName = "setVdTemplateResponse")
    public SetVdTemplateResponse setVdTemplate(
        @WebParam(name = "setVdTemplateRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_vd_template_service", partName = "setVdTemplateRequest")
        SetVdTemplateRequest setVdTemplateRequest);

    /**
     * 
     * @param getFiltersRequest
     * @return
     *     returns com.emc.d2fs.services.preference_service.GetFiltersResponse
     */
    @WebMethod
    @WebResult(name = "getFiltersResponse", targetNamespace = "http://www.emc.com/d2fs/services/preference_service", partName = "getFiltersResponse")
    public GetFiltersResponse getFilters(
        @WebParam(name = "getFiltersRequest", targetNamespace = "http://www.emc.com/d2fs/services/preference_service", partName = "getFiltersRequest")
        GetFiltersRequest getFiltersRequest);

    /**
     * 
     * @param testChangeStateConditionsRequest
     * @return
     *     returns com.emc.d2fs.services.d2lifecycle_service.TestChangeStateConditionsResponse
     */
    @WebMethod
    @WebResult(name = "testChangeStateConditionsResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2lifecycle_service", partName = "testChangeStateConditionsResponse")
    public TestChangeStateConditionsResponse testChangeStateConditions(
        @WebParam(name = "testChangeStateConditionsRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2lifecycle_service", partName = "testChangeStateConditionsRequest")
        TestChangeStateConditionsRequest testChangeStateConditionsRequest);

    /**
     * 
     * @param taxonomyExportRequest
     * @return
     *     returns com.emc.d2fs.services.d2_taxonomy_service.TaxonomyExportResponse
     */
    @WebMethod
    @WebResult(name = "taxonomyExportResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_taxonomy_service", partName = "taxonomyExportResponse")
    public TaxonomyExportResponse taxonomyExport(
        @WebParam(name = "taxonomyExportRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_taxonomy_service", partName = "taxonomyExportRequest")
        TaxonomyExportRequest taxonomyExportRequest);

    /**
     * 
     * @param loadSearchAttributesRequest
     * @return
     *     returns com.emc.d2fs.services.search_service.LoadSearchAttributesResponse
     */
    @WebMethod
    @WebResult(name = "loadSearchAttributesResponse", targetNamespace = "http://www.emc.com/d2fs/services/search_service", partName = "loadSearchAttributesResponse")
    public LoadSearchAttributesResponse loadSearchAttributes(
        @WebParam(name = "loadSearchAttributesRequest", targetNamespace = "http://www.emc.com/d2fs/services/search_service", partName = "loadSearchAttributesRequest")
        LoadSearchAttributesRequest loadSearchAttributesRequest);

    /**
     * 
     * @param getUserNativeAnnotationCheckoutIdsRequest
     * @return
     *     returns com.emc.d2fs.services.annotation_service.GetUserNativeAnnotationCheckoutIdsResponse
     */
    @WebMethod
    @WebResult(name = "getUserNativeAnnotationCheckoutIdsResponse", targetNamespace = "http://www.emc.com/d2fs/services/annotation_service", partName = "getUserNativeAnnotationCheckoutIdsResponse")
    public GetUserNativeAnnotationCheckoutIdsResponse getUserNativeAnnotationCheckoutIds(
        @WebParam(name = "getUserNativeAnnotationCheckoutIdsRequest", targetNamespace = "http://www.emc.com/d2fs/services/annotation_service", partName = "getUserNativeAnnotationCheckoutIdsRequest")
        GetUserNativeAnnotationCheckoutIdsRequest getUserNativeAnnotationCheckoutIdsRequest);

    /**
     * 
     * @param addCenterstageCommentRequest
     * @return
     *     returns com.emc.d2fs.services.centerstage_service.AddCenterstageCommentResponse
     */
    @WebMethod
    @WebResult(name = "addCenterstageCommentResponse", targetNamespace = "http://www.emc.com/d2fs/services/centerstage_service", partName = "addCenterstageCommentResponse")
    public AddCenterstageCommentResponse addCenterstageComment(
        @WebParam(name = "addCenterstageCommentRequest", targetNamespace = "http://www.emc.com/d2fs/services/centerstage_service", partName = "addCenterstageCommentRequest")
        AddCenterstageCommentRequest addCenterstageCommentRequest);

    /**
     * 
     * @param isDialogRequiredRequest
     * @return
     *     returns com.emc.d2fs.services.destroy_service.IsDialogRequiredResponse
     */
    @WebMethod
    @WebResult(name = "isDialogRequiredResponse", targetNamespace = "http://www.emc.com/d2fs/services/destroy_service", partName = "isDialogRequiredResponse")
    public IsDialogRequiredResponse isDialogRequired(
        @WebParam(name = "isDialogRequiredRequest", targetNamespace = "http://www.emc.com/d2fs/services/destroy_service", partName = "isDialogRequiredRequest")
        IsDialogRequiredRequest isDialogRequiredRequest);

    /**
     * 
     * @param getSearchAssistanceValuesRequest
     * @return
     *     returns com.emc.d2fs.services.search_service.GetSearchAssistanceValuesResponse
     */
    @WebMethod
    @WebResult(name = "getSearchAssistanceValuesResponse", targetNamespace = "http://www.emc.com/d2fs/services/search_service", partName = "getSearchAssistanceValuesResponse")
    public GetSearchAssistanceValuesResponse getSearchAssistanceValues(
        @WebParam(name = "getSearchAssistanceValuesRequest", targetNamespace = "http://www.emc.com/d2fs/services/search_service", partName = "getSearchAssistanceValuesRequest")
        GetSearchAssistanceValuesRequest getSearchAssistanceValuesRequest);

    /**
     * 
     * @param getTaxonomyRequest
     * @return
     *     returns com.emc.d2fs.services.dialog_service.GetTaxonomyResponse
     */
    @WebMethod
    @WebResult(name = "getTaxonomyResponse", targetNamespace = "http://www.emc.com/d2fs/services/dialog_service", partName = "getTaxonomyResponse")
    public GetTaxonomyResponse getTaxonomy(
        @WebParam(name = "getTaxonomyRequest", targetNamespace = "http://www.emc.com/d2fs/services/dialog_service", partName = "getTaxonomyRequest")
        GetTaxonomyRequest getTaxonomyRequest);

    /**
     * 
     * @param addVDChildRequest
     * @return
     *     returns com.emc.d2fs.services.vdcontent_service.AddVDChildResponse
     */
    @WebMethod
    @WebResult(name = "addVDChildResponse", targetNamespace = "http://www.emc.com/d2fs/services/vdcontent_service", partName = "addVDChildResponse")
    public AddVDChildResponse addVDChild(
        @WebParam(name = "addVDChildRequest", targetNamespace = "http://www.emc.com/d2fs/services/vdcontent_service", partName = "addVDChildRequest")
        AddVDChildRequest addVDChildRequest);

    /**
     * 
     * @param isProtectedInControlledViewRequest
     * @return
     *     returns com.emc.d2fs.services.thumbnails_service.IsProtectedInControlledViewResponse
     */
    @WebMethod
    @WebResult(name = "isProtectedInControlledViewResponse", targetNamespace = "http://www.emc.com/d2fs/services/thumbnails_service", partName = "isProtectedInControlledViewResponse")
    public com.emc.d2fs.services.thumbnails_service.IsProtectedInControlledViewResponse isProtectedInControlledView(
        @WebParam(name = "isProtectedInControlledViewRequest", targetNamespace = "http://www.emc.com/d2fs/services/thumbnails_service", partName = "isProtectedInControlledViewRequest")
        com.emc.d2fs.services.thumbnails_service.IsProtectedInControlledViewRequest isProtectedInControlledViewRequest);

    /**
     * 
     * @param checkLifeCycleRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.CheckLifeCycleResponse
     */
    @WebMethod
    @WebResult(name = "checkLifeCycleResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "checkLifeCycleResponse")
    public CheckLifeCycleResponse checkLifeCycle(
        @WebParam(name = "checkLifeCycleRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "checkLifeCycleRequest")
        CheckLifeCycleRequest checkLifeCycleRequest);

    /**
     * 
     * @param getContentIdAndNativeAnnotationIdsRequest
     * @return
     *     returns com.emc.d2fs.services.annotation_service.GetContentIdAndNativeAnnotationIdsResponse
     */
    @WebMethod
    @WebResult(name = "getContentIdAndNativeAnnotationIdsResponse", targetNamespace = "http://www.emc.com/d2fs/services/annotation_service", partName = "getContentIdAndNativeAnnotationIdsResponse")
    public GetContentIdAndNativeAnnotationIdsResponse getContentIdAndNativeAnnotationIds(
        @WebParam(name = "getContentIdAndNativeAnnotationIdsRequest", targetNamespace = "http://www.emc.com/d2fs/services/annotation_service", partName = "getContentIdAndNativeAnnotationIdsRequest")
        GetContentIdAndNativeAnnotationIdsRequest getContentIdAndNativeAnnotationIdsRequest);

    /**
     * 
     * @param getTaskFolderLabelRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.GetTaskFolderLabelResponse
     */
    @WebMethod
    @WebResult(name = "getTaskFolderLabelResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "getTaskFolderLabelResponse")
    public GetTaskFolderLabelResponse getTaskFolderLabel(
        @WebParam(name = "getTaskFolderLabelRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "getTaskFolderLabelRequest")
        GetTaskFolderLabelRequest getTaskFolderLabelRequest);

    /**
     * 
     * @param runQueryFormRequest
     * @return
     *     returns com.emc.d2fs.services.search_service.RunQueryFormResponse
     */
    @WebMethod
    @WebResult(name = "runQueryFormResponse", targetNamespace = "http://www.emc.com/d2fs/services/search_service", partName = "runQueryFormResponse")
    public RunQueryFormResponse runQueryForm(
        @WebParam(name = "runQueryFormRequest", targetNamespace = "http://www.emc.com/d2fs/services/search_service", partName = "runQueryFormRequest")
        RunQueryFormRequest runQueryFormRequest);

    /**
     * 
     * @param getDownloadUrlsRequest
     * @return
     *     returns com.emc.d2fs.services.download_service.GetDownloadUrlsResponse
     */
    @WebMethod
    @WebResult(name = "getDownloadUrlsResponse", targetNamespace = "http://www.emc.com/d2fs/services/download_service", partName = "getDownloadUrlsResponse")
    public GetDownloadUrlsResponse getDownloadUrls(
        @WebParam(name = "getDownloadUrlsRequest", targetNamespace = "http://www.emc.com/d2fs/services/download_service", partName = "getDownloadUrlsRequest")
        GetDownloadUrlsRequest getDownloadUrlsRequest);

    /**
     * 
     * @param checkPropertyPageRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.CheckPropertyPageResponse
     */
    @WebMethod
    @WebResult(name = "checkPropertyPageResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "checkPropertyPageResponse")
    public CheckPropertyPageResponse checkPropertyPage(
        @WebParam(name = "checkPropertyPageRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "checkPropertyPageRequest")
        CheckPropertyPageRequest checkPropertyPageRequest);

    /**
     * 
     * @param getSkinRequest
     * @return
     *     returns com.emc.d2fs.services.x3config_service.GetSkinResponse
     */
    @WebMethod
    @WebResult(name = "getSkinResponse", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getSkinResponse")
    public GetSkinResponse getSkin(
        @WebParam(name = "getSkinRequest", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getSkinRequest")
        GetSkinRequest getSkinRequest);

    /**
     * 
     * @param getAllRepositoriesRequest
     * @return
     *     returns com.emc.d2fs.services.repository_service.GetAllRepositoriesResponse
     */
    @WebMethod
    @WebResult(name = "getAllRepositoriesResponse", targetNamespace = "http://www.emc.com/d2fs/services/repository_service", partName = "getAllRepositoriesResponse")
    public GetAllRepositoriesResponse getAllRepositories(
        @WebParam(name = "getAllRepositoriesRequest", targetNamespace = "http://www.emc.com/d2fs/services/repository_service", partName = "getAllRepositoriesRequest")
        GetAllRepositoriesRequest getAllRepositoriesRequest);

    /**
     * 
     * @param dictionaryImportRequest
     * @return
     *     returns com.emc.d2fs.services.d2_dictionary_service.DictionaryImportResponse
     */
    @WebMethod
    @WebResult(name = "dictionaryImportResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_dictionary_service", partName = "dictionaryImportResponse")
    public DictionaryImportResponse dictionaryImport(
        @WebParam(name = "dictionaryImportRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_dictionary_service", partName = "dictionaryImportRequest")
        DictionaryImportRequest dictionaryImportRequest);

    /**
     * 
     * @param getCheckinUrlsRequest
     * @return
     *     returns com.emc.d2fs.services.download_service.GetCheckinUrlsResponse
     */
    @WebMethod
    @WebResult(name = "getCheckinUrlsResponse", targetNamespace = "http://www.emc.com/d2fs/services/download_service", partName = "getCheckinUrlsResponse")
    public GetCheckinUrlsResponse getCheckinUrls(
        @WebParam(name = "getCheckinUrlsRequest", targetNamespace = "http://www.emc.com/d2fs/services/download_service", partName = "getCheckinUrlsRequest")
        GetCheckinUrlsRequest getCheckinUrlsRequest);

    /**
     * 
     * @param generateDMTicketRequest
     * @return
     *     returns com.emc.d2fs.services.repository_service.GenerateDMTicketResponse
     */
    @WebMethod
    @WebResult(name = "generateDMTicketResponse", targetNamespace = "http://www.emc.com/d2fs/services/repository_service", partName = "generateDMTicketResponse")
    public GenerateDMTicketResponse generateDMTicket(
        @WebParam(name = "generateDMTicketRequest", targetNamespace = "http://www.emc.com/d2fs/services/repository_service", partName = "generateDMTicketRequest")
        GenerateDMTicketRequest generateDMTicketRequest);

    /**
     * 
     * @param setDownloadLocationsRequest
     * @return
     *     returns com.emc.d2fs.services.x3config_service.SetDownloadLocationsResponse
     */
    @WebMethod
    @WebResult(name = "setDownloadLocationsResponse", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "setDownloadLocationsResponse")
    public SetDownloadLocationsResponse setDownloadLocations(
        @WebParam(name = "setDownloadLocationsRequest", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "setDownloadLocationsRequest")
        SetDownloadLocationsRequest setDownloadLocationsRequest);

    /**
     * 
     * @param getConfigurationsNamesRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.GetConfigurationsNamesResponse
     */
    @WebMethod
    @WebResult(name = "getConfigurationsNamesResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "getConfigurationsNamesResponse")
    public GetConfigurationsNamesResponse getConfigurationsNames(
        @WebParam(name = "getConfigurationsNamesRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "getConfigurationsNamesRequest")
        GetConfigurationsNamesRequest getConfigurationsNamesRequest);

    /**
     * 
     * @param insertVDInheritedComponentRequest
     * @return
     *     returns com.emc.d2fs.services.vdcontent_service.InsertVDInheritedComponentResponse
     */
    @WebMethod
    @WebResult(name = "insertVDInheritedComponentResponse", targetNamespace = "http://www.emc.com/d2fs/services/vdcontent_service", partName = "insertVDInheritedComponentResponse")
    public InsertVDInheritedComponentResponse insertVDInheritedComponent(
        @WebParam(name = "insertVDInheritedComponentRequest", targetNamespace = "http://www.emc.com/d2fs/services/vdcontent_service", partName = "insertVDInheritedComponentRequest")
        InsertVDInheritedComponentRequest insertVDInheritedComponentRequest);

    /**
     * 
     * @param saveUserLastSpacesRequest
     * @return
     *     returns com.emc.d2fs.services.x3config_service.SaveUserLastSpacesResponse
     */
    @WebMethod
    @WebResult(name = "saveUserLastSpacesResponse", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "saveUserLastSpacesResponse")
    public SaveUserLastSpacesResponse saveUserLastSpaces(
        @WebParam(name = "saveUserLastSpacesRequest", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "saveUserLastSpacesRequest")
        SaveUserLastSpacesRequest saveUserLastSpacesRequest);

    /**
     * 
     * @param canPrintControlledViewRequest
     * @return
     *     returns com.emc.d2fs.services.download_service.CanPrintControlledViewResponse
     */
    @WebMethod
    @WebResult(name = "canPrintControlledViewResponse", targetNamespace = "http://www.emc.com/d2fs/services/download_service", partName = "canPrintControlledViewResponse")
    public CanPrintControlledViewResponse canPrintControlledView(
        @WebParam(name = "canPrintControlledViewRequest", targetNamespace = "http://www.emc.com/d2fs/services/download_service", partName = "canPrintControlledViewRequest")
        CanPrintControlledViewRequest canPrintControlledViewRequest);

    /**
     * 
     * @param applyConfigurationsRequest
     * @return
     *     returns com.emc.d2fs.services.d2_massupdate_service.ApplyConfigurationsResponse
     */
    @WebMethod
    @WebResult(name = "applyConfigurationsResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_massupdate_service", partName = "applyConfigurationsResponse")
    public ApplyConfigurationsResponse applyConfigurations(
        @WebParam(name = "applyConfigurationsRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_massupdate_service", partName = "applyConfigurationsRequest")
        ApplyConfigurationsRequest applyConfigurationsRequest);

    /**
     * 
     * @param getTemplatesRequest
     * @return
     *     returns com.emc.d2fs.services.creation_service.GetTemplatesResponse
     */
    @WebMethod
    @WebResult(name = "getTemplatesResponse", targetNamespace = "http://www.emc.com/d2fs/services/creation_service", partName = "getTemplatesResponse")
    public com.emc.d2fs.services.creation_service.GetTemplatesResponse getTemplates(
        @WebParam(name = "getTemplatesRequest", targetNamespace = "http://www.emc.com/d2fs/services/creation_service", partName = "getTemplatesRequest")
        com.emc.d2fs.services.creation_service.GetTemplatesRequest getTemplatesRequest);

    /**
     * 
     * @param destroyRequest
     * @return
     *     returns com.emc.d2fs.services.destroy_service.DestroyResponse
     */
    @WebMethod
    @WebResult(name = "destroyResponse", targetNamespace = "http://www.emc.com/d2fs/services/destroy_service", partName = "destroyResponse")
    public DestroyResponse destroy(
        @WebParam(name = "destroyRequest", targetNamespace = "http://www.emc.com/d2fs/services/destroy_service", partName = "destroyRequest")
        DestroyRequest destroyRequest);

    /**
     * 
     * @param dictionaryExportRequest
     * @return
     *     returns com.emc.d2fs.services.d2_dictionary_service.DictionaryExportResponse
     */
    @WebMethod
    @WebResult(name = "dictionaryExportResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_dictionary_service", partName = "dictionaryExportResponse")
    public DictionaryExportResponse dictionaryExport(
        @WebParam(name = "dictionaryExportRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_dictionary_service", partName = "dictionaryExportRequest")
        DictionaryExportRequest dictionaryExportRequest);

    /**
     * 
     * @param getLinkStatesRequest
     * @return
     *     returns com.emc.d2fs.services.d2_link_service.GetLinkStatesResponse
     */
    @WebMethod
    @WebResult(name = "getLinkStatesResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_link_service", partName = "getLinkStatesResponse")
    public GetLinkStatesResponse getLinkStates(
        @WebParam(name = "getLinkStatesRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_link_service", partName = "getLinkStatesRequest")
        GetLinkStatesRequest getLinkStatesRequest);

    /**
     * 
     * @param refreshTaxonomyCacheRequest
     * @return
     *     returns com.emc.d2fs.services.d2_cache_service.RefreshTaxonomyCacheResponse
     */
    @WebMethod
    @WebResult(name = "refreshTaxonomyCacheResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_cache_service", partName = "refreshTaxonomyCacheResponse")
    public RefreshTaxonomyCacheResponse refreshTaxonomyCache(
        @WebParam(name = "refreshTaxonomyCacheRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_cache_service", partName = "refreshTaxonomyCacheRequest")
        RefreshTaxonomyCacheRequest refreshTaxonomyCacheRequest);

    /**
     * 
     * @param handleDistributionActionRequest
     * @return
     *     returns com.emc.d2fs.services.distribution_service.HandleDistributionActionResponse
     */
    @WebMethod
    @WebResult(name = "handleDistributionActionResponse", targetNamespace = "http://www.emc.com/d2fs/services/distribution_service", partName = "handleDistributionActionResponse")
    public HandleDistributionActionResponse handleDistributionAction(
        @WebParam(name = "handleDistributionActionRequest", targetNamespace = "http://www.emc.com/d2fs/services/distribution_service", partName = "handleDistributionActionRequest")
        HandleDistributionActionRequest handleDistributionActionRequest);

    /**
     * 
     * @param checkoutAsNewRequest
     * @return
     *     returns com.emc.d2fs.services.checkout_service.CheckoutAsNewResponse
     */
    @WebMethod
    @WebResult(name = "checkoutAsNewResponse", targetNamespace = "http://www.emc.com/d2fs/services/checkout_service", partName = "checkoutAsNewResponse")
    public CheckoutAsNewResponse checkoutAsNew(
        @WebParam(name = "checkoutAsNewRequest", targetNamespace = "http://www.emc.com/d2fs/services/checkout_service", partName = "checkoutAsNewRequest")
        CheckoutAsNewRequest checkoutAsNewRequest);

    /**
     * 
     * @param getDialogRequest
     * @return
     *     returns com.emc.d2fs.services.dialog_service.GetDialogResponse
     */
    @WebMethod
    @WebResult(name = "getDialogResponse", targetNamespace = "http://www.emc.com/d2fs/services/dialog_service", partName = "getDialogResponse")
    public GetDialogResponse getDialog(
        @WebParam(name = "getDialogRequest", targetNamespace = "http://www.emc.com/d2fs/services/dialog_service", partName = "getDialogRequest")
        GetDialogRequest getDialogRequest);

    /**
     * 
     * @param getLocationByTypeRequest
     * @return
     *     returns com.emc.d2fs.services.locate_service.GetLocationByTypeResponse
     */
    @WebMethod
    @WebResult(name = "getLocationByTypeResponse", targetNamespace = "http://www.emc.com/d2fs/services/locate_service", partName = "getLocationByTypeResponse")
    public GetLocationByTypeResponse getLocationByType(
        @WebParam(name = "getLocationByTypeRequest", targetNamespace = "http://www.emc.com/d2fs/services/locate_service", partName = "getLocationByTypeRequest")
        GetLocationByTypeRequest getLocationByTypeRequest);

    /**
     * 
     * @param getQuickSearchContentRequest
     * @return
     *     returns com.emc.d2fs.services.search_service.GetQuickSearchContentResponse
     */
    @WebMethod
    @WebResult(name = "getQuickSearchContentResponse", targetNamespace = "http://www.emc.com/d2fs/services/search_service", partName = "getQuickSearchContentResponse")
    public GetQuickSearchContentResponse getQuickSearchContent(
        @WebParam(name = "getQuickSearchContentRequest", targetNamespace = "http://www.emc.com/d2fs/services/search_service", partName = "getQuickSearchContentRequest")
        GetQuickSearchContentRequest getQuickSearchContentRequest);

    /**
     * 
     * @param setTaskReadStateRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.SetTaskReadStateResponse
     */
    @WebMethod
    @WebResult(name = "setTaskReadStateResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "setTaskReadStateResponse")
    public SetTaskReadStateResponse setTaskReadState(
        @WebParam(name = "setTaskReadStateRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "setTaskReadStateRequest")
        SetTaskReadStateRequest setTaskReadStateRequest);

    /**
     * 
     * @param copyRequest
     * @return
     *     returns com.emc.d2fs.services.move_service.CopyResponse
     */
    @WebMethod
    @WebResult(name = "copyResponse", targetNamespace = "http://www.emc.com/d2fs/services/move_service", partName = "copyResponse")
    public CopyResponse copy(
        @WebParam(name = "copyRequest", targetNamespace = "http://www.emc.com/d2fs/services/move_service", partName = "copyRequest")
        CopyRequest copyRequest);

    /**
     * 
     * @param parentsRelationRequest
     * @return
     *     returns com.emc.d2fs.services.d2_relation_service.ParentsRelationResponse
     */
    @WebMethod
    @WebResult(name = "parentsRelationResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_relation_service", partName = "parentsRelationResponse")
    public ParentsRelationResponse parentsRelation(
        @WebParam(name = "parentsRelationRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_relation_service", partName = "parentsRelationRequest")
        ParentsRelationRequest parentsRelationRequest);

    /**
     * 
     * @param getSearchAttributesRequest
     * @return
     *     returns com.emc.d2fs.services.search_service.GetSearchAttributesResponse
     */
    @WebMethod
    @WebResult(name = "getSearchAttributesResponse", targetNamespace = "http://www.emc.com/d2fs/services/search_service", partName = "getSearchAttributesResponse")
    public GetSearchAttributesResponse getSearchAttributes(
        @WebParam(name = "getSearchAttributesRequest", targetNamespace = "http://www.emc.com/d2fs/services/search_service", partName = "getSearchAttributesRequest")
        GetSearchAttributesRequest getSearchAttributesRequest);

    /**
     * 
     * @param isPropertyAuditedRequest
     * @return
     *     returns com.emc.d2fs.services.d2_audit_service.IsPropertyAuditedResponse
     */
    @WebMethod
    @WebResult(name = "isPropertyAuditedResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_audit_service", partName = "isPropertyAuditedResponse")
    public IsPropertyAuditedResponse isPropertyAudited(
        @WebParam(name = "isPropertyAuditedRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_audit_service", partName = "isPropertyAuditedRequest")
        IsPropertyAuditedRequest isPropertyAuditedRequest);

    /**
     * 
     * @param isEventAuditedRequest
     * @return
     *     returns com.emc.d2fs.services.d2_audit_service.IsEventAuditedResponse
     */
    @WebMethod
    @WebResult(name = "isEventAuditedResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_audit_service", partName = "isEventAuditedResponse")
    public IsEventAuditedResponse isEventAudited(
        @WebParam(name = "isEventAuditedRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_audit_service", partName = "isEventAuditedRequest")
        IsEventAuditedRequest isEventAuditedRequest);

    /**
     * 
     * @param applyRequest
     * @return
     *     returns com.emc.d2fs.services.d2lifecycle_service.ApplyResponse
     */
    @WebMethod
    @WebResult(name = "applyResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2lifecycle_service", partName = "applyResponse")
    public ApplyResponse apply(
        @WebParam(name = "applyRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2lifecycle_service", partName = "applyRequest")
        ApplyRequest applyRequest);

    /**
     * 
     * @param cancelDialogRequest
     * @return
     *     returns com.emc.d2fs.services.dialog_service.CancelDialogResponse
     */
    @WebMethod
    @WebResult(name = "cancelDialogResponse", targetNamespace = "http://www.emc.com/d2fs/services/dialog_service", partName = "cancelDialogResponse")
    public CancelDialogResponse cancelDialog(
        @WebParam(name = "cancelDialogRequest", targetNamespace = "http://www.emc.com/d2fs/services/dialog_service", partName = "cancelDialogRequest")
        CancelDialogRequest cancelDialogRequest);

    /**
     * 
     * @param handleOperationRequest
     * @return
     *     returns com.emc.d2fs.services.dctmlifecycle_service.HandleOperationResponse
     */
    @WebMethod
    @WebResult(name = "handleOperationResponse", targetNamespace = "http://www.emc.com/d2fs/services/dctmlifecycle_service", partName = "handleOperationResponse")
    public HandleOperationResponse handleOperation(
        @WebParam(name = "handleOperationRequest", targetNamespace = "http://www.emc.com/d2fs/services/dctmlifecycle_service", partName = "handleOperationRequest")
        HandleOperationRequest handleOperationRequest);

    /**
     * 
     * @param sendmailRequest
     * @return
     *     returns com.emc.d2fs.services.sendmail_service.SendmailResponse
     */
    @WebMethod(operationName = "Sendmail")
    @WebResult(name = "SendmailResponse", targetNamespace = "http://www.emc.com/d2fs/services/sendmail_service", partName = "SendmailResponse")
    public SendmailResponse sendmail(
        @WebParam(name = "SendmailRequest", targetNamespace = "http://www.emc.com/d2fs/services/sendmail_service", partName = "SendmailRequest")
        SendmailRequest sendmailRequest);

    /**
     * 
     * @param hasAttachmentsRequest
     * @return
     *     returns com.emc.d2fs.services.creation_service.HasAttachmentsResponse
     */
    @WebMethod
    @WebResult(name = "hasAttachmentsResponse", targetNamespace = "http://www.emc.com/d2fs/services/creation_service", partName = "hasAttachmentsResponse")
    public HasAttachmentsResponse hasAttachments(
        @WebParam(name = "hasAttachmentsRequest", targetNamespace = "http://www.emc.com/d2fs/services/creation_service", partName = "hasAttachmentsRequest")
        HasAttachmentsRequest hasAttachmentsRequest);

    /**
     * 
     * @param applyAuditMessagesRequest
     * @return
     *     returns com.emc.d2fs.services.d2_audit_service.ApplyAuditMessagesResponse
     */
    @WebMethod
    @WebResult(name = "applyAuditMessagesResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_audit_service", partName = "applyAuditMessagesResponse")
    public ApplyAuditMessagesResponse applyAuditMessages(
        @WebParam(name = "applyAuditMessagesRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_audit_service", partName = "applyAuditMessagesRequest")
        ApplyAuditMessagesRequest applyAuditMessagesRequest);

    /**
     * 
     * @param applyConfigRequest
     * @return
     *     returns com.emc.d2fs.services.d2_apply_config_service.ApplyConfigResponse
     */
    @WebMethod
    @WebResult(name = "applyConfigResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_apply_config_service", partName = "applyConfigResponse")
    public ApplyConfigResponse applyConfig(
        @WebParam(name = "applyConfigRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_apply_config_service", partName = "applyConfigRequest")
        ApplyConfigRequest applyConfigRequest);

    /**
     * 
     * @param getCenterstageCommentsRequest
     * @return
     *     returns com.emc.d2fs.services.centerstage_service.GetCenterstageCommentsResponse
     */
    @WebMethod
    @WebResult(name = "getCenterstageCommentsResponse", targetNamespace = "http://www.emc.com/d2fs/services/centerstage_service", partName = "getCenterstageCommentsResponse")
    public GetCenterstageCommentsResponse getCenterstageComments(
        @WebParam(name = "getCenterstageCommentsRequest", targetNamespace = "http://www.emc.com/d2fs/services/centerstage_service", partName = "getCenterstageCommentsRequest")
        GetCenterstageCommentsRequest getCenterstageCommentsRequest);

    /**
     * 
     * @param getDistributionContentRequest
     * @return
     *     returns com.emc.d2fs.services.distribution_service.GetDistributionContentResponse
     */
    @WebMethod
    @WebResult(name = "getDistributionContentResponse", targetNamespace = "http://www.emc.com/d2fs/services/distribution_service", partName = "getDistributionContentResponse")
    public GetDistributionContentResponse getDistributionContent(
        @WebParam(name = "getDistributionContentRequest", targetNamespace = "http://www.emc.com/d2fs/services/distribution_service", partName = "getDistributionContentRequest")
        GetDistributionContentRequest getDistributionContentRequest);

    /**
     * 
     * @param checkoutRequest
     * @return
     *     returns com.emc.d2fs.services.checkout_service.CheckoutResponse
     */
    @WebMethod
    @WebResult(name = "checkoutResponse", targetNamespace = "http://www.emc.com/d2fs/services/checkout_service", partName = "checkoutResponse")
    public CheckoutResponse checkout(
        @WebParam(name = "checkoutRequest", targetNamespace = "http://www.emc.com/d2fs/services/checkout_service", partName = "checkoutRequest")
        CheckoutRequest checkoutRequest);

    /**
     * 
     * @param getDictionaryNumberValueRequest
     * @return
     *     returns com.emc.d2fs.services.d2_dictionary_service.GetDictionaryNumberValueResponse
     */
    @WebMethod
    @WebResult(name = "getDictionaryNumberValueResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_dictionary_service", partName = "getDictionaryNumberValueResponse")
    public GetDictionaryNumberValueResponse getDictionaryNumberValue(
        @WebParam(name = "getDictionaryNumberValueRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_dictionary_service", partName = "getDictionaryNumberValueRequest")
        GetDictionaryNumberValueRequest getDictionaryNumberValueRequest);

    /**
     * 
     * @param getVdTemplatesRequest
     * @return
     *     returns com.emc.d2fs.services.d2_vd_template_service.GetVdTemplatesResponse
     */
    @WebMethod
    @WebResult(name = "getVdTemplatesResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_vd_template_service", partName = "getVdTemplatesResponse")
    public GetVdTemplatesResponse getVdTemplates(
        @WebParam(name = "getVdTemplatesRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_vd_template_service", partName = "getVdTemplatesRequest")
        GetVdTemplatesRequest getVdTemplatesRequest);

    /**
     * 
     * @param getFileInfoRequest
     * @return
     *     returns com.emc.d2fs.services.download_service.GetFileInfoResponse
     */
    @WebMethod
    @WebResult(name = "getFileInfoResponse", targetNamespace = "http://www.emc.com/d2fs/services/download_service", partName = "getFileInfoResponse")
    public GetFileInfoResponse getFileInfo(
        @WebParam(name = "getFileInfoRequest", targetNamespace = "http://www.emc.com/d2fs/services/download_service", partName = "getFileInfoRequest")
        GetFileInfoRequest getFileInfoRequest);

    /**
     * 
     * @param getLocationByNameRequest
     * @return
     *     returns com.emc.d2fs.services.locate_service.GetLocationByNameResponse
     */
    @WebMethod
    @WebResult(name = "getLocationByNameResponse", targetNamespace = "http://www.emc.com/d2fs/services/locate_service", partName = "getLocationByNameResponse")
    public GetLocationByNameResponse getLocationByName(
        @WebParam(name = "getLocationByNameRequest", targetNamespace = "http://www.emc.com/d2fs/services/locate_service", partName = "getLocationByNameRequest")
        GetLocationByNameRequest getLocationByNameRequest);

    /**
     * 
     * @param getLocationForBreadCrumbRequest
     * @return
     *     returns com.emc.d2fs.services.locate_service.GetLocationForBreadCrumbResponse
     */
    @WebMethod
    @WebResult(name = "getLocationForBreadCrumbResponse", targetNamespace = "http://www.emc.com/d2fs/services/locate_service", partName = "getLocationForBreadCrumbResponse")
    public GetLocationForBreadCrumbResponse getLocationForBreadCrumb(
        @WebParam(name = "getLocationForBreadCrumbRequest", targetNamespace = "http://www.emc.com/d2fs/services/locate_service", partName = "getLocationForBreadCrumbRequest")
        GetLocationForBreadCrumbRequest getLocationForBreadCrumbRequest);

    /**
     * 
     * @param setPortalMenuPositionRequest
     * @return
     *     returns com.emc.d2fs.services.x3config_service.SetPortalMenuPositionResponse
     */
    @WebMethod
    @WebResult(name = "setPortalMenuPositionResponse", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "setPortalMenuPositionResponse")
    public SetPortalMenuPositionResponse setPortalMenuPosition(
        @WebParam(name = "setPortalMenuPositionRequest", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "setPortalMenuPositionRequest")
        SetPortalMenuPositionRequest setPortalMenuPositionRequest);

    /**
     * 
     * @param setTaskPriorityRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.SetTaskPriorityResponse
     */
    @WebMethod
    @WebResult(name = "setTaskPriorityResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "setTaskPriorityResponse")
    public SetTaskPriorityResponse setTaskPriority(
        @WebParam(name = "setTaskPriorityRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "setTaskPriorityRequest")
        SetTaskPriorityRequest setTaskPriorityRequest);

    /**
     * 
     * @param taxonomyOperationRequest
     * @return
     *     returns com.emc.d2fs.services.d2_taxonomy_service.TaxonomyOperationResponse
     */
    @WebMethod
    @WebResult(name = "taxonomyOperationResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_taxonomy_service", partName = "taxonomyOperationResponse")
    public TaxonomyOperationResponse taxonomyOperation(
        @WebParam(name = "taxonomyOperationRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_taxonomy_service", partName = "taxonomyOperationRequest")
        TaxonomyOperationRequest taxonomyOperationRequest);

    /**
     * 
     * @param setPortalRowHeightRequest
     * @return
     *     returns com.emc.d2fs.services.x3config_service.SetPortalRowHeightResponse
     */
    @WebMethod
    @WebResult(name = "setPortalRowHeightResponse", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "setPortalRowHeightResponse")
    public SetPortalRowHeightResponse setPortalRowHeight(
        @WebParam(name = "setPortalRowHeightRequest", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "setPortalRowHeightRequest")
        SetPortalRowHeightRequest setPortalRowHeightRequest);

    /**
     * 
     * @param isCommandAllowedRequest
     * @return
     *     returns com.emc.d2fs.services.command_service.IsCommandAllowedResponse
     */
    @WebMethod
    @WebResult(name = "isCommandAllowedResponse", targetNamespace = "http://www.emc.com/d2fs/services/command_service", partName = "isCommandAllowedResponse")
    public IsCommandAllowedResponse isCommandAllowed(
        @WebParam(name = "isCommandAllowedRequest", targetNamespace = "http://www.emc.com/d2fs/services/command_service", partName = "isCommandAllowedRequest")
        IsCommandAllowedRequest isCommandAllowedRequest);

    /**
     * 
     * @param nextStatesRequest
     * @return
     *     returns com.emc.d2fs.services.d2lifecycle_service.NextStatesResponse
     */
    @WebMethod
    @WebResult(name = "nextStatesResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2lifecycle_service", partName = "nextStatesResponse")
    public NextStatesResponse nextStates(
        @WebParam(name = "nextStatesRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2lifecycle_service", partName = "nextStatesRequest")
        NextStatesRequest nextStatesRequest);

    /**
     * 
     * @param getTaskModeRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.GetTaskModeResponse
     */
    @WebMethod
    @WebResult(name = "getTaskModeResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "getTaskModeResponse")
    public GetTaskModeResponse getTaskMode(
        @WebParam(name = "getTaskModeRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "getTaskModeRequest")
        GetTaskModeRequest getTaskModeRequest);

    /**
     * 
     * @param isProtectedInControlledViewRequest
     * @return
     *     returns com.emc.d2fs.services.download_service.IsProtectedInControlledViewResponse
     */
    @WebMethod
    @WebResult(name = "isProtectedInControlledViewResponse", targetNamespace = "http://www.emc.com/d2fs/services/download_service", partName = "isProtectedInControlledViewResponse")
    public com.emc.d2fs.services.download_service.IsProtectedInControlledViewResponse isProtectedInControlledView2(
        @WebParam(name = "isProtectedInControlledViewRequest", targetNamespace = "http://www.emc.com/d2fs/services/download_service", partName = "isProtectedInControlledViewRequest")
        com.emc.d2fs.services.download_service.IsProtectedInControlledViewRequest isProtectedInControlledViewRequest);

    /**
     * 
     * @param getTaxonomieContentRequest
     * @return
     *     returns com.emc.d2fs.models.taxonomy_operation.GetTaxonomieContentResponse
     */
    @WebMethod
    @WebResult(name = "getTaxonomieContentResponse", targetNamespace = "http://www.emc.com/d2fs/models/taxonomy_operation", partName = "getTaxonomieContentResponse")
    public GetTaxonomieContentResponse getTaxonomieContent(
        @WebParam(name = "getTaxonomieContentRequest", targetNamespace = "http://www.emc.com/d2fs/models/taxonomy_operation", partName = "getTaxonomieContentRequest")
        GetTaxonomieContentRequest getTaxonomieContentRequest);

    /**
     * 
     * @param importVersionTaxonomyRequest
     * @return
     *     returns com.emc.d2fs.services.d2_taxonomy_service.ImportVersionTaxonomyResponse
     */
    @WebMethod
    @WebResult(name = "importVersionTaxonomyResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_taxonomy_service", partName = "importVersionTaxonomyResponse")
    public ImportVersionTaxonomyResponse importVersionTaxonomy(
        @WebParam(name = "importVersionTaxonomyRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_taxonomy_service", partName = "importVersionTaxonomyRequest")
        ImportVersionTaxonomyRequest importVersionTaxonomyRequest);

    /**
     * 
     * @param getBrowserFacetContentRequest
     * @return
     *     returns com.emc.d2fs.services.browser_service.GetBrowserFacetContentResponse
     */
    @WebMethod
    @WebResult(name = "getBrowserFacetContentResponse", targetNamespace = "http://www.emc.com/d2fs/services/browser_service", partName = "getBrowserFacetContentResponse")
    public GetBrowserFacetContentResponse getBrowserFacetContent(
        @WebParam(name = "getBrowserFacetContentRequest", targetNamespace = "http://www.emc.com/d2fs/services/browser_service", partName = "getBrowserFacetContentRequest")
        GetBrowserFacetContentRequest getBrowserFacetContentRequest);

    /**
     * 
     * @param getSpaceRequest
     * @return
     *     returns com.emc.d2fs.services.x3config_service.GetSpaceResponse
     */
    @WebMethod
    @WebResult(name = "getSpaceResponse", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getSpaceResponse")
    public GetSpaceResponse getSpace(
        @WebParam(name = "getSpaceRequest", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getSpaceRequest")
        GetSpaceRequest getSpaceRequest);

    /**
     * 
     * @param canDelegateTaskRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.CanDelegateTaskResponse
     */
    @WebMethod
    @WebResult(name = "canDelegateTaskResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "canDelegateTaskResponse")
    public CanDelegateTaskResponse canDelegateTask(
        @WebParam(name = "canDelegateTaskRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "canDelegateTaskRequest")
        CanDelegateTaskRequest canDelegateTaskRequest);

    /**
     * 
     * @param testExistsRequest
     * @return
     *     returns com.emc.d2fs.services.d2_archive_service.TestExistsResponse
     */
    @WebMethod
    @WebResult(name = "testExistsResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_archive_service", partName = "testExistsResponse")
    public TestExistsResponse testExists(
        @WebParam(name = "testExistsRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_archive_service", partName = "testExistsRequest")
        TestExistsRequest testExistsRequest);

    /**
     * 
     * @param verifyEntryCriteriaRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.VerifyEntryCriteriaResponse
     */
    @WebMethod
    @WebResult(name = "verifyEntryCriteriaResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "verifyEntryCriteriaResponse")
    public VerifyEntryCriteriaResponse verifyEntryCriteria(
        @WebParam(name = "verifyEntryCriteriaRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "verifyEntryCriteriaRequest")
        VerifyEntryCriteriaRequest verifyEntryCriteriaRequest);

    /**
     * 
     * @param addToFavoritesRequest
     * @return
     *     returns com.emc.d2fs.services.favorites_service.AddToFavoritesResponse
     */
    @WebMethod
    @WebResult(name = "addToFavoritesResponse", targetNamespace = "http://www.emc.com/d2fs/services/favorites_service", partName = "addToFavoritesResponse")
    public AddToFavoritesResponse addToFavorites(
        @WebParam(name = "addToFavoritesRequest", targetNamespace = "http://www.emc.com/d2fs/services/favorites_service", partName = "addToFavoritesRequest")
        AddToFavoritesRequest addToFavoritesRequest);

    /**
     * 
     * @param modifyLinkRequest
     * @return
     *     returns com.emc.d2fs.services.d2_link_service.ModifyLinkResponse
     */
    @WebMethod
    @WebResult(name = "modifyLinkResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_link_service", partName = "modifyLinkResponse")
    public ModifyLinkResponse modifyLink(
        @WebParam(name = "modifyLinkRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_link_service", partName = "modifyLinkRequest")
        ModifyLinkRequest modifyLinkRequest);

    /**
     * 
     * @param moveRequest
     * @return
     *     returns com.emc.d2fs.services.move_service.MoveResponse
     */
    @WebMethod
    @WebResult(name = "moveResponse", targetNamespace = "http://www.emc.com/d2fs/services/move_service", partName = "moveResponse")
    public MoveResponse move(
        @WebParam(name = "moveRequest", targetNamespace = "http://www.emc.com/d2fs/services/move_service", partName = "moveRequest")
        MoveRequest moveRequest);

    /**
     * 
     * @param getColumnSrcSettingRequest
     * @return
     *     returns com.emc.d2fs.services.preference_service.GetColumnSrcSettingResponse
     */
    @WebMethod
    @WebResult(name = "getColumnSrcSettingResponse", targetNamespace = "http://www.emc.com/d2fs/services/preference_service", partName = "getColumnSrcSettingResponse")
    public GetColumnSrcSettingResponse getColumnSrcSetting(
        @WebParam(name = "getColumnSrcSettingRequest", targetNamespace = "http://www.emc.com/d2fs/services/preference_service", partName = "getColumnSrcSettingRequest")
        GetColumnSrcSettingRequest getColumnSrcSettingRequest);

    /**
     * 
     * @param getThumbnailUrlsRequest
     * @return
     *     returns com.emc.d2fs.services.thumbnails_service.GetThumbnailUrlsResponse
     */
    @WebMethod
    @WebResult(name = "getThumbnailUrlsResponse", targetNamespace = "http://www.emc.com/d2fs/services/thumbnails_service", partName = "getThumbnailUrlsResponse")
    public GetThumbnailUrlsResponse getThumbnailUrls(
        @WebParam(name = "getThumbnailUrlsRequest", targetNamespace = "http://www.emc.com/d2fs/services/thumbnails_service", partName = "getThumbnailUrlsRequest")
        GetThumbnailUrlsRequest getThumbnailUrlsRequest);

    /**
     * 
     * @param nextStateOnEventRequest
     * @return
     *     returns com.emc.d2fs.services.d2lifecycle_service.NextStateOnEventResponse
     */
    @WebMethod
    @WebResult(name = "nextStateOnEventResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2lifecycle_service", partName = "nextStateOnEventResponse")
    public NextStateOnEventResponse nextStateOnEvent(
        @WebParam(name = "nextStateOnEventRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2lifecycle_service", partName = "nextStateOnEventRequest")
        NextStateOnEventRequest nextStateOnEventRequest);

    /**
     * 
     * @param getWorkflowContentRequest
     * @return
     *     returns com.emc.d2fs.services.workflowcontent_service.GetWorkflowContentResponse
     */
    @WebMethod
    @WebResult(name = "getWorkflowContentResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflowcontent_service", partName = "getWorkflowContentResponse")
    public GetWorkflowContentResponse getWorkflowContent(
        @WebParam(name = "getWorkflowContentRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflowcontent_service", partName = "getWorkflowContentRequest")
        GetWorkflowContentRequest getWorkflowContentRequest);

    /**
     * 
     * @param dumpRequest
     * @return
     *     returns com.emc.d2fs.services.property_service.DumpResponse
     */
    @WebMethod
    @WebResult(name = "dumpResponse", targetNamespace = "http://www.emc.com/d2fs/services/property_service", partName = "dumpResponse")
    public DumpResponse dump(
        @WebParam(name = "dumpRequest", targetNamespace = "http://www.emc.com/d2fs/services/property_service", partName = "dumpRequest")
        DumpRequest dumpRequest);

    /**
     * 
     * @param delegateTaskOnErrorRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.DelegateTaskOnErrorResponse
     */
    @WebMethod
    @WebResult(name = "delegateTaskOnErrorResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "delegateTaskOnErrorResponse")
    public DelegateTaskOnErrorResponse delegateTaskOnError(
        @WebParam(name = "delegateTaskOnErrorRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "delegateTaskOnErrorRequest")
        DelegateTaskOnErrorRequest delegateTaskOnErrorRequest);

    /**
     * 
     * @param createPropertiesRequest
     * @return
     *     returns com.emc.d2fs.services.creation_service.CreatePropertiesResponse
     */
    @WebMethod
    @WebResult(name = "createPropertiesResponse", targetNamespace = "http://www.emc.com/d2fs/services/creation_service", partName = "createPropertiesResponse")
    public CreatePropertiesResponse createProperties(
        @WebParam(name = "createPropertiesRequest", targetNamespace = "http://www.emc.com/d2fs/services/creation_service", partName = "createPropertiesRequest")
        CreatePropertiesRequest createPropertiesRequest);

    /**
     * 
     * @param createVersionRequest
     * @return
     *     returns com.emc.d2fs.services.d2_archive_service.CreateVersionResponse
     */
    @WebMethod
    @WebResult(name = "createVersionResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_archive_service", partName = "createVersionResponse")
    public CreateVersionResponse createVersion(
        @WebParam(name = "createVersionRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_archive_service", partName = "createVersionRequest")
        CreateVersionRequest createVersionRequest);

    /**
     * 
     * @param getSignoffParametersRequest
     * @return
     *     returns com.emc.d2fs.services.distribution_service.GetSignoffParametersResponse
     */
    @WebMethod
    @WebResult(name = "getSignoffParametersResponse", targetNamespace = "http://www.emc.com/d2fs/services/distribution_service", partName = "getSignoffParametersResponse")
    public com.emc.d2fs.services.distribution_service.GetSignoffParametersResponse getSignoffParameters2(
        @WebParam(name = "getSignoffParametersRequest", targetNamespace = "http://www.emc.com/d2fs/services/distribution_service", partName = "getSignoffParametersRequest")
        com.emc.d2fs.services.distribution_service.GetSignoffParametersRequest getSignoffParametersRequest);

    /**
     * 
     * @param delegateTaskRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.DelegateTaskResponse
     */
    @WebMethod
    @WebResult(name = "delegateTaskResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "delegateTaskResponse")
    public DelegateTaskResponse delegateTask(
        @WebParam(name = "delegateTaskRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "delegateTaskRequest")
        DelegateTaskRequest delegateTaskRequest);

    /**
     * 
     * @param getExportTaxonomyUrlRequest
     * @return
     *     returns com.emc.d2fs.services.d2_taxonomy_service.GetExportTaxonomyUrlResponse
     */
    @WebMethod
    @WebResult(name = "getExportTaxonomyUrlResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_taxonomy_service", partName = "getExportTaxonomyUrlResponse")
    public GetExportTaxonomyUrlResponse getExportTaxonomyUrl(
        @WebParam(name = "getExportTaxonomyUrlRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_taxonomy_service", partName = "getExportTaxonomyUrlRequest")
        GetExportTaxonomyUrlRequest getExportTaxonomyUrlRequest);

    /**
     * 
     * @param importVersionDictionaryRequest
     * @return
     *     returns com.emc.d2fs.services.d2_dictionary_service.ImportVersionDictionaryResponse
     */
    @WebMethod
    @WebResult(name = "importVersionDictionaryResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_dictionary_service", partName = "importVersionDictionaryResponse")
    public ImportVersionDictionaryResponse importVersionDictionary(
        @WebParam(name = "importVersionDictionaryRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_dictionary_service", partName = "importVersionDictionaryRequest")
        ImportVersionDictionaryRequest importVersionDictionaryRequest);

    /**
     * 
     * @param getOptionsRequest
     * @return
     *     returns com.emc.d2fs.services.dialog_service.GetOptionsResponse
     */
    @WebMethod
    @WebResult(name = "getOptionsResponse", targetNamespace = "http://www.emc.com/d2fs/services/dialog_service", partName = "getOptionsResponse")
    public GetOptionsResponse getOptions(
        @WebParam(name = "getOptionsRequest", targetNamespace = "http://www.emc.com/d2fs/services/dialog_service", partName = "getOptionsRequest")
        GetOptionsRequest getOptionsRequest);

    /**
     * 
     * @param getPortalMenuPositionRequest
     * @return
     *     returns com.emc.d2fs.services.x3config_service.GetPortalMenuPositionResponse
     */
    @WebMethod
    @WebResult(name = "getPortalMenuPositionResponse", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getPortalMenuPositionResponse")
    public GetPortalMenuPositionResponse getPortalMenuPosition(
        @WebParam(name = "getPortalMenuPositionRequest", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "getPortalMenuPositionRequest")
        GetPortalMenuPositionRequest getPortalMenuPositionRequest);

    /**
     * 
     * @param checkWorkflowAliasesRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.CheckWorkflowAliasesResponse
     */
    @WebMethod
    @WebResult(name = "checkWorkflowAliasesResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "checkWorkflowAliasesResponse")
    public CheckWorkflowAliasesResponse checkWorkflowAliases(
        @WebParam(name = "checkWorkflowAliasesRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "checkWorkflowAliasesRequest")
        CheckWorkflowAliasesRequest checkWorkflowAliasesRequest);

    /**
     * 
     * @param getUploadUrlsRequest
     * @return
     *     returns com.emc.d2fs.services.download_service.GetUploadUrlsResponse
     */
    @WebMethod
    @WebResult(name = "getUploadUrlsResponse", targetNamespace = "http://www.emc.com/d2fs/services/download_service", partName = "getUploadUrlsResponse")
    public GetUploadUrlsResponse getUploadUrls(
        @WebParam(name = "getUploadUrlsRequest", targetNamespace = "http://www.emc.com/d2fs/services/download_service", partName = "getUploadUrlsRequest")
        GetUploadUrlsRequest getUploadUrlsRequest);

    /**
     * 
     * @param unsubscribeAllRequest
     * @return
     *     returns com.emc.d2fs.services.subscriptions_service.UnsubscribeAllResponse
     */
    @WebMethod
    @WebResult(name = "unsubscribeAllResponse", targetNamespace = "http://www.emc.com/d2fs/services/subscriptions_service", partName = "unsubscribeAllResponse")
    public UnsubscribeAllResponse unsubscribeAll(
        @WebParam(name = "unsubscribeAllRequest", targetNamespace = "http://www.emc.com/d2fs/services/subscriptions_service", partName = "unsubscribeAllRequest")
        UnsubscribeAllRequest unsubscribeAllRequest);

    /**
     * 
     * @param removeVDChildRequest
     * @return
     *     returns com.emc.d2fs.services.vdcontent_service.RemoveVDChildResponse
     */
    @WebMethod
    @WebResult(name = "removeVDChildResponse", targetNamespace = "http://www.emc.com/d2fs/services/vdcontent_service", partName = "removeVDChildResponse")
    public RemoveVDChildResponse removeVDChild(
        @WebParam(name = "removeVDChildRequest", targetNamespace = "http://www.emc.com/d2fs/services/vdcontent_service", partName = "removeVDChildRequest")
        RemoveVDChildRequest removeVDChildRequest);

    /**
     * 
     * @param abortWorkflowRequest
     * @return
     *     returns com.emc.d2fs.services.workflow_service.AbortWorkflowResponse
     */
    @WebMethod
    @WebResult(name = "abortWorkflowResponse", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "abortWorkflowResponse")
    public AbortWorkflowResponse abortWorkflow(
        @WebParam(name = "abortWorkflowRequest", targetNamespace = "http://www.emc.com/d2fs/services/workflow_service", partName = "abortWorkflowRequest")
        AbortWorkflowRequest abortWorkflowRequest);

    /**
     * 
     * @param removeFromFavoritesRequest
     * @return
     *     returns com.emc.d2fs.services.favorites_service.RemoveFromFavoritesResponse
     */
    @WebMethod
    @WebResult(name = "removeFromFavoritesResponse", targetNamespace = "http://www.emc.com/d2fs/services/favorites_service", partName = "removeFromFavoritesResponse")
    public RemoveFromFavoritesResponse removeFromFavorites(
        @WebParam(name = "removeFromFavoritesRequest", targetNamespace = "http://www.emc.com/d2fs/services/favorites_service", partName = "removeFromFavoritesRequest")
        RemoveFromFavoritesRequest removeFromFavoritesRequest);

    /**
     * 
     * @param listConfigurationsRequest
     * @return
     *     returns com.emc.d2fs.services.d2_massupdate_service.ListConfigurationsResponse
     */
    @WebMethod
    @WebResult(name = "listConfigurationsResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_massupdate_service", partName = "listConfigurationsResponse")
    public ListConfigurationsResponse listConfigurations(
        @WebParam(name = "listConfigurationsRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_massupdate_service", partName = "listConfigurationsRequest")
        ListConfigurationsRequest listConfigurationsRequest);

    /**
     * 
     * @param releaseRequest
     * @return
     *     returns com.emc.d2fs.services.repository_service.ReleaseResponse
     */
    @WebMethod
    @WebResult(name = "releaseResponse", targetNamespace = "http://www.emc.com/d2fs/services/repository_service", partName = "releaseResponse")
    public ReleaseResponse release(
        @WebParam(name = "releaseRequest", targetNamespace = "http://www.emc.com/d2fs/services/repository_service", partName = "releaseRequest")
        ReleaseRequest releaseRequest);

    /**
     * 
     * @param setPortalMenuWidthRequest
     * @return
     *     returns com.emc.d2fs.services.x3config_service.SetPortalMenuWidthResponse
     */
    @WebMethod
    @WebResult(name = "setPortalMenuWidthResponse", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "setPortalMenuWidthResponse")
    public SetPortalMenuWidthResponse setPortalMenuWidth(
        @WebParam(name = "setPortalMenuWidthRequest", targetNamespace = "http://www.emc.com/d2fs/services/x3config_service", partName = "setPortalMenuWidthRequest")
        SetPortalMenuWidthRequest setPortalMenuWidthRequest);

    /**
     * 
     * @param getImportDictionaryUrlRequest
     * @return
     *     returns com.emc.d2fs.services.d2_dictionary_service.GetImportDictionaryUrlResponse
     */
    @WebMethod
    @WebResult(name = "getImportDictionaryUrlResponse", targetNamespace = "http://www.emc.com/d2fs/services/d2_dictionary_service", partName = "getImportDictionaryUrlResponse")
    public GetImportDictionaryUrlResponse getImportDictionaryUrl(
        @WebParam(name = "getImportDictionaryUrlRequest", targetNamespace = "http://www.emc.com/d2fs/services/d2_dictionary_service", partName = "getImportDictionaryUrlRequest")
        GetImportDictionaryUrlRequest getImportDictionaryUrlRequest);

    /**
     * 
     * @param getFullMenusTypeRequest
     * @return
     *     returns com.emc.d2fs.services.menu_service.GetFullMenusTypeResponse
     */
    @WebMethod
    @WebResult(name = "getFullMenusTypeResponse", targetNamespace = "http://www.emc.com/d2fs/services/menu_service", partName = "getFullMenusTypeResponse")
    public GetFullMenusTypeResponse getFullMenusType(
        @WebParam(name = "getFullMenusTypeRequest", targetNamespace = "http://www.emc.com/d2fs/services/menu_service", partName = "getFullMenusTypeRequest")
        GetFullMenusTypeRequest getFullMenusTypeRequest);

}
