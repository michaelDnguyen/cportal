package com.dlvn.mcustomerportal.services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Streaming;

/**
 * @arthor nn.tai
 * @date Oct 20, 2016
 */
public interface ServicesRequest {

	// @GET("DLPadAuthLogin/{username};{password};{deviceid}")
	// Call<List<User>> getUser(@Path("username") String username,
	// @Path("password") String password,
	// @Path("deviceid") String deviceid);

//	@POST("ApprovalLogin")
//	Call<ApprovalLoginResponse> ApprovalLogin(@Body BaseRequest request);
//
//	@POST("GetInquiryMasterData")
//	Call<InquiryMasterDataReponse> getInquiryMasterData(@Body BaseRequest request);
//
//	@POST("GetInquirySummary")
//	Call<InquirySummaryResponse> getInquirySummary(@Body BaseRequest request);
//
//	@POST("GetDetailInquirySummary")
//	Call<DetailInquirySummaryResponse> getDetailInquirySummary(@Body BaseRequest request);
//
//	@POST("GetExpenseDetail")
//	Call<ExpenseDetailResponse> getExpenseDetail(@Body BaseRequest request);
//
//	@POST("GetInvoiceDetail")
//	Call<InvoiceDetailResponse> getInvoiceDetail(@Body BaseRequest request);
//
//	@POST("SavePostComment")
//	Call<SavePostCommentResponse> savePostComment(@Body BaseRequest request);
//
//	@POST("ApproveRequisition")
//	Call<ApproveRequisitionResponse> ApproveRequisition(@Body BaseRequest request);
//
//	// @Streaming
//	// @GET
//	// Call<ResponseBody> downloadFileAttach(@Url String fileUrl);
//	@Streaming
//	@POST("GetAttachFile")
//	Call<ResponseBody> downloadFileAttach(@Body BaseRequest request);
}
