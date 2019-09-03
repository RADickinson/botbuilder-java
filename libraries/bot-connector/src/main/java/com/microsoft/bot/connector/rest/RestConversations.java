/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.bot.connector.rest;

import com.microsoft.bot.schema.*;
import retrofit2.Retrofit;
import com.microsoft.bot.connector.Conversations;
import com.google.common.reflect.TypeToken;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceFuture;
import com.microsoft.rest.ServiceResponse;
import com.microsoft.rest.Validator;
import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.HTTP;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.Response;
import rx.functions.Func1;
import rx.Observable;

/**
 * An instance of this class provides access to all the operations defined
 * in Conversations.
 */
public class RestConversations implements Conversations {
    /** The Retrofit service to perform REST calls. */
    private ConversationsService service;
    /** The service client containing this operation class. */
    private RestConnectorClient client;

    /**
     * Initializes an instance of ConversationsImpl.
     *
     * @param retrofit the Retrofit instance built from a Retrofit Builder.
     * @param client the instance of the service client containing this operation class.
     */
    RestConversations(Retrofit retrofit, RestConnectorClient client) {
        this.service = retrofit.create(ConversationsService.class);
        this.client = client;
    }

    /**
     * The interface defining all the services for Conversations to be
     * used by Retrofit to perform actually REST calls.
     */
    @SuppressWarnings("checkstyle:linelength")
    interface ConversationsService {
        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.bot.schema.Conversations getConversations" })
        @GET("v3/conversations")
        Observable<Response<ResponseBody>> getConversations(@Query("continuationToken") String continuationToken,
                                                            @Header("accept-language") String acceptLanguage,
                                                            @Header("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.bot.schema.Conversations createConversation" })
        @POST("v3/conversations")
        Observable<Response<ResponseBody>> createConversation(@Body ConversationParameters parameters,
                                                              @Header("accept-language") String acceptLanguage,
                                                              @Header("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.bot.schema.Conversations sendToConversation" })
        @POST("v3/conversations/{conversationId}/activities")
        Observable<Response<ResponseBody>> sendToConversation(@Path("conversationId") String conversationId,
                                                              @Body Activity activity,
                                                              @Header("accept-language") String acceptLanguage,
                                                              @Header("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.bot.schema.Conversations updateActivity" })
        @PUT("v3/conversations/{conversationId}/activities/{activityId}")
        Observable<Response<ResponseBody>> updateActivity(@Path("conversationId") String conversationId,
                                                          @Path("activityId") String activityId,
                                                          @Body Activity activity,
                                                          @Header("accept-language") String acceptLanguage,
                                                          @Header("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.bot.schema.Conversations replyToActivity" })
        @POST("v3/conversations/{conversationId}/activities/{activityId}")
        Observable<Response<ResponseBody>> replyToActivity(@Path("conversationId") String conversationId,
                                                           @Path("activityId") String activityId,
                                                           @Body Activity activity,
                                                           @Header("accept-language") String acceptLanguage,
                                                           @Header("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.bot.schema.Conversations deleteActivity" })
        @HTTP(path = "v3/conversations/{conversationId}/activities/{activityId}", method = "DELETE", hasBody = true)
        Observable<Response<ResponseBody>> deleteActivity(@Path("conversationId") String conversationId,
                                                          @Path("activityId") String activityId,
                                                          @Header("accept-language") String acceptLanguage,
                                                          @Header("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.bot.schema.Conversations getConversationMembers" })
        @GET("v3/conversations/{conversationId}/members")
        Observable<Response<ResponseBody>> getConversationMembers(@Path("conversationId") String conversationId,
                                                                  @Header("accept-language") String acceptLanguage,
                                                                  @Header("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.bot.schema.Conversations deleteConversationMember" })
        @HTTP(path = "v3/conversations/{conversationId}/members/{memberId}", method = "DELETE", hasBody = true)
        Observable<Response<ResponseBody>> deleteConversationMember(@Path("conversationId") String conversationId,
                                                                    @Path("memberId") String memberId,
                                                                    @Header("accept-language") String acceptLanguage,
                                                                    @Header("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.bot.schema.Conversations getActivityMembers" })
        @GET("v3/conversations/{conversationId}/activities/{activityId}/members")
        Observable<Response<ResponseBody>> getActivityMembers(@Path("conversationId") String conversationId,
                                                              @Path("activityId") String activityId,
                                                              @Header("accept-language") String acceptLanguage,
                                                              @Header("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.bot.schema.Conversations uploadAttachment" })
        @POST("v3/conversations/{conversationId}/attachments")
        Observable<Response<ResponseBody>> uploadAttachment(@Path("conversationId") String conversationId,
                                                            @Body AttachmentData attachmentUpload,
                                                            @Header("accept-language") String acceptLanguage,
                                                            @Header("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.bot.schema.Conversations sendConversationHistory" })
        @POST("v3/conversations/{conversationId}/activities/history")
        Observable<Response<ResponseBody>> sendConversationHistory(@Path("conversationId") String conversationId,
                                                                   @Body Transcript history,
                                                                   @Header("accept-language") String acceptLanguage,
                                                                   @Header("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.bot.schema.Conversations getConversationPagedMembers" })
        @GET("v3/conversations/{conversationId}/pagedmembers")
        Observable<Response<ResponseBody>> getConversationPagedMembers(@Path("conversationId") String conversationId,
                                                                       @Header("accept-language") String acceptLanguage,
                                                                       @Header("User-Agent") String userAgent);
    }

    /**
     * Implementation of getConversations.
     *
     * @see Conversations#getConversations
     */
    @Override
    public ConversationsResult getConversations() {
        return getConversationsWithServiceResponseAsync().toBlocking().single().body();
    }

    /**
     * Implementation of getConversationsAsync.
     *
     * @see Conversations#getConversationsAsync
     */
    @Override
    public ServiceFuture<ConversationsResult> getConversationsAsync(
        final ServiceCallback<ConversationsResult> serviceCallback) {

        return ServiceFuture.fromResponse(getConversationsWithServiceResponseAsync(), serviceCallback);
    }

    /**
     * Implementation of getConversationsAsync.
     *
     * @see Conversations#getConversationsAsync
     */
    @Override
    public Observable<ConversationsResult> getConversationsAsync() {
        return getConversationsWithServiceResponseAsync().map(response -> response.body());
    }

    /**
     * Implementation of getConversationsWithServiceResponseAsync.
     *
     * @see Conversations#getConversationsWithServiceResponseAsync
     */
    @Override
    public Observable<ServiceResponse<ConversationsResult>> getConversationsWithServiceResponseAsync() {
        final String continuationToken = null;
        return service.getConversations(continuationToken, this.client.getAcceptLanguage(), this.client.getUserAgent())
            .flatMap((Func1<Response<ResponseBody>, Observable<ServiceResponse<ConversationsResult>>>) response -> {
                try {
                    ServiceResponse<ConversationsResult> clientResponse = getConversationsDelegate(response);
                    return Observable.just(clientResponse);
                } catch (Throwable t) {
                    return Observable.error(t);
                }
            });
    }

    /**
     * Implementation of getConversations.
     *
     * @see Conversations#getConversations
     */
    @Override
    public ConversationsResult getConversations(String continuationToken) {
        return getConversationsWithServiceResponseAsync(continuationToken).toBlocking().single().body();
    }

    /**
     * Implementation of getConversationsAsync.
     *
     * @see Conversations#getConversationsAsync
     */
    @Override
    public ServiceFuture<ConversationsResult> getConversationsAsync(
        String continuationToken, final ServiceCallback<ConversationsResult> serviceCallback) {

        return ServiceFuture.fromResponse(getConversationsWithServiceResponseAsync(continuationToken), serviceCallback);
    }

    /**
     * Implementation of getConversationsAsync.
     *
     * @see Conversations#getConversationsAsync
     */
    @Override
    public Observable<ConversationsResult> getConversationsAsync(String continuationToken) {
        return getConversationsWithServiceResponseAsync(continuationToken).map(response -> response.body());
    }

    /**
     * Implementation of getConversationsWithServiceResponseAsync.
     *
     * @see Conversations#getConversationsWithServiceResponseAsync
     */
    @Override
    public Observable<ServiceResponse<ConversationsResult>> getConversationsWithServiceResponseAsync(
        String continuationToken) {

        return service.getConversations(continuationToken, this.client.getAcceptLanguage(), this.client.getUserAgent())
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<ConversationsResult>>>() {
                @Override
                public Observable<ServiceResponse<ConversationsResult>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<ConversationsResult> clientResponse = getConversationsDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<ConversationsResult> getConversationsDelegate(
        Response<ResponseBody> response) throws ErrorResponseException, IOException {

        return this.client.restClient().responseBuilderFactory().<ConversationsResult, ErrorResponseException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<ConversationsResult>() { }.getType())
                .registerError(ErrorResponseException.class)
                .build(response);
    }

    /**
     * Implementation of CreateConversation.
     *
     * @see Conversations#createConversation
     */
    @Override
    public ConversationResourceResponse createConversation(ConversationParameters parameters) {
        return createConversationWithServiceResponseAsync(parameters).toBlocking().single().body();
    }

    /**
     * Implementation of CreateConversation.
     *
     * @see Conversations#createConversationAsync
     */
    @Override
    public ServiceFuture<ConversationResourceResponse> createConversationAsync(
        ConversationParameters parameters, final ServiceCallback<ConversationResourceResponse> serviceCallback) {

        return ServiceFuture.fromResponse(createConversationWithServiceResponseAsync(parameters), serviceCallback);
    }

    /**
     * Implementation of CreateConversation.
     *
     * @see Conversations#createConversationAsync
     */
    @Override
    public Observable<ConversationResourceResponse> createConversationAsync(ConversationParameters parameters) {
        return createConversationWithServiceResponseAsync(parameters).map(response -> response.body());
    }

    /**
     * Implementation of createConversationWithServiceResponseAsync.
     *
     * @see Conversations#createConversationWithServiceResponseAsync
     */
    @Override
    public Observable<ServiceResponse<ConversationResourceResponse>> createConversationWithServiceResponseAsync(
        ConversationParameters parameters) {

        if (parameters == null) {
            throw new IllegalArgumentException("Parameter parameters is required and cannot be null.");
        }
        Validator.validate(parameters);
        return service.createConversation(parameters, this.client.getAcceptLanguage(), this.client.getUserAgent())
            .flatMap((Func1<Response<ResponseBody>, Observable<ServiceResponse<ConversationResourceResponse>>>) response -> {
                try {
                    ServiceResponse<ConversationResourceResponse> clientResponse = createConversationDelegate(response);
                    return Observable.just(clientResponse);
                } catch (Throwable t) {
                    return Observable.error(t);
                }
            });
    }

    private ServiceResponse<ConversationResourceResponse> createConversationDelegate(
        Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {

        return this.client.restClient().responseBuilderFactory().<ConversationResourceResponse, ErrorResponseException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<ConversationResourceResponse>() { }.getType())
                .register(201, new TypeToken<ConversationResourceResponse>() { }.getType())
                .register(202, new TypeToken<ConversationResourceResponse>() { }.getType())
                .registerError(ErrorResponseException.class)
                .build(response);
    }

    /**
     * Implementation of sendToConversation.
     *
     * @see Conversations#sendToConversation
     */
    @Override
    public ResourceResponse sendToConversation(String conversationId, Activity activity) {
        return sendToConversationWithServiceResponseAsync(conversationId, activity).toBlocking().single().body();
    }

    /**
     * Implementation of sendToConversationAsync.
     *
     * @see Conversations#sendToConversationAsync
     */
    @Override
    public ServiceFuture<ResourceResponse> sendToConversationAsync(
        String conversationId, Activity activity, final ServiceCallback<ResourceResponse> serviceCallback) {

        return ServiceFuture.fromResponse(sendToConversationWithServiceResponseAsync(conversationId, activity), serviceCallback);
    }

    /**
     * Implementation of sendToConversationAsync.
     *
     * @see Conversations#sendToConversationAsync
     */
    @Override
    public Observable<ResourceResponse> sendToConversationAsync(String conversationId, Activity activity) {
        return sendToConversationWithServiceResponseAsync(conversationId, activity).map(response -> response.body());
    }

    /**
     * Implementation of sendToConversationWithServiceResponseAsync.
     *
     * @see Conversations#sendToConversationWithServiceResponseAsync
     */
    @Override
    public Observable<ServiceResponse<ResourceResponse>> sendToConversationWithServiceResponseAsync(
        String conversationId, Activity activity) {

        if (conversationId == null) {
            throw new IllegalArgumentException("Parameter conversationId is required and cannot be null.");
        }
        if (activity == null) {
            throw new IllegalArgumentException("Parameter activity is required and cannot be null.");
        }
        Validator.validate(activity);
        return service.sendToConversation(conversationId, activity, this.client.getAcceptLanguage(), this.client.getUserAgent())
            .flatMap((Func1<Response<ResponseBody>, Observable<ServiceResponse<ResourceResponse>>>) response -> {
                try {
                    ServiceResponse<ResourceResponse> clientResponse = sendToConversationDelegate(response);
                    return Observable.just(clientResponse);
                } catch (Throwable t) {
                    return Observable.error(t);
                }
            });
    }

    private ServiceResponse<ResourceResponse> sendToConversationDelegate(
        Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {

        return this.client.restClient().responseBuilderFactory().<ResourceResponse, ErrorResponseException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<ResourceResponse>() { }.getType())
                .register(201, new TypeToken<ResourceResponse>() { }.getType())
                .register(202, new TypeToken<ResourceResponse>() { }.getType())
                .registerError(ErrorResponseException.class)
                .build(response);
    }

    /**
     * Implementation of updateActivity.
     *
     * @see Conversations#updateActivity
     */
    @Override
    public ResourceResponse updateActivity(String conversationId, String activityId, Activity activity) {
        return updateActivityWithServiceResponseAsync(conversationId, activityId, activity).toBlocking().single().body();
    }

    /**
     * Implementation of updateActivityAsync.
     *
     * @see Conversations#updateActivityAsync
     */
    @Override
    public ServiceFuture<ResourceResponse> updateActivityAsync(
        String conversationId,
        String activityId,
        Activity activity,
        final ServiceCallback<ResourceResponse> serviceCallback) {

        return ServiceFuture.fromResponse(updateActivityWithServiceResponseAsync(conversationId, activityId, activity), serviceCallback);
    }

    /**
     * Implementation of updateActivityAsync.
     *
     * @see Conversations#updateActivityAsync
     */
    @Override
    public Observable<ResourceResponse> updateActivityAsync(String conversationId, String activityId, Activity activity) {
        return updateActivityWithServiceResponseAsync(conversationId, activityId, activity).map(response -> response.body());
    }

    /**
     * Implementation of updateActivityWithServiceResponseAsync.
     *
     * @see Conversations#updateActivityWithServiceResponseAsync
     */
    @Override
    public Observable<ServiceResponse<ResourceResponse>> updateActivityWithServiceResponseAsync(
        String conversationId, String activityId, Activity activity) {
        if (conversationId == null) {
            throw new IllegalArgumentException("Parameter conversationId is required and cannot be null.");
        }
        if (activityId == null) {
            throw new IllegalArgumentException("Parameter activityId is required and cannot be null.");
        }
        if (activity == null) {
            throw new IllegalArgumentException("Parameter activity is required and cannot be null.");
        }
        Validator.validate(activity);
        return service.updateActivity(conversationId, activityId, activity, this.client.getAcceptLanguage(), this.client.getUserAgent())
            .flatMap((Func1<Response<ResponseBody>, Observable<ServiceResponse<ResourceResponse>>>) response -> {
                try {
                    ServiceResponse<ResourceResponse> clientResponse = updateActivityDelegate(response);
                    return Observable.just(clientResponse);
                } catch (Throwable t) {
                    return Observable.error(t);
                }
            });
    }

    private ServiceResponse<ResourceResponse> updateActivityDelegate(
        Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {

        return this.client.restClient().responseBuilderFactory().<ResourceResponse, ErrorResponseException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<ResourceResponse>() { }.getType())
                .register(201, new TypeToken<ResourceResponse>() { }.getType())
                .register(202, new TypeToken<ResourceResponse>() { }.getType())
                .registerError(ErrorResponseException.class)
                .build(response);
    }

    /**
     * Implementation of replyToActivity.
     *
     * @see Conversations#replyToActivity
     */
    @Override
    public ResourceResponse replyToActivity(String conversationId, String activityId, Activity activity) {
        return replyToActivityWithServiceResponseAsync(
            conversationId, activityId, activity).toBlocking().single().body();
    }

    /**
     * Implementation of replyToActivityAsync.
     *
     * @see Conversations#replyToActivityAsync
     */
    @Override
    public ServiceFuture<ResourceResponse> replyToActivityAsync(
        String conversationId,
        String activityId,
        Activity activity,
        final ServiceCallback<ResourceResponse> serviceCallback) {

        return ServiceFuture.fromResponse(replyToActivityWithServiceResponseAsync(
            conversationId, activityId, activity), serviceCallback);
    }

    /**
     * Implementation of replyToActivityAsync.
     *
     * @see Conversations#replyToActivityAsync
     */
    @Override
    public Observable<ResourceResponse> replyToActivityAsync(String conversationId,
                                                             String activityId,
                                                             Activity activity) {
        return replyToActivityWithServiceResponseAsync(conversationId, activityId, activity).map(response -> response.body());
    }

    /**
     * Implementation of replyToActivityWithServiceResponseAsync.
     *
     * @see Conversations#replyToActivityWithServiceResponseAsync
     */
    @Override
    public Observable<ServiceResponse<ResourceResponse>> replyToActivityWithServiceResponseAsync(
        String conversationId, String activityId, Activity activity) {

        if (conversationId == null) {
            throw new IllegalArgumentException("Parameter conversationId is required and cannot be null.");
        }
        if (activityId == null) {
            throw new IllegalArgumentException("Parameter activityId is required and cannot be null.");
        }
        if (activity == null) {
            throw new IllegalArgumentException("Parameter activity is required and cannot be null.");
        }
        Validator.validate(activity);
        return service.replyToActivity(conversationId, activityId, activity, this.client.getAcceptLanguage(), this.client.getUserAgent())
            .flatMap((Func1<Response<ResponseBody>, Observable<ServiceResponse<ResourceResponse>>>) response -> {
                try {
                    ServiceResponse<ResourceResponse> clientResponse = replyToActivityDelegate(response);
                    return Observable.just(clientResponse);
                } catch (Throwable t) {
                    return Observable.error(t);
                }
            });
    }

    private ServiceResponse<ResourceResponse> replyToActivityDelegate(
        Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {

        return this.client.restClient().responseBuilderFactory().<ResourceResponse, ErrorResponseException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<ResourceResponse>() { }.getType())
                .register(201, new TypeToken<ResourceResponse>() { }.getType())
                .register(202, new TypeToken<ResourceResponse>() { }.getType())
                .registerError(ErrorResponseException.class)
                .build(response);
    }

    /**
     * Implementation of deleteActivity.
     *
     * @see Conversations#deleteActivity
     */
    @Override
    public void deleteActivity(String conversationId, String activityId) {
        deleteActivityWithServiceResponseAsync(conversationId, activityId).toBlocking().single().body();
    }

    /**
     * Implementation of deleteActivityAsync.
     *
     * @see Conversations#deleteActivityAsync
     */
    @Override
    public ServiceFuture<Void> deleteActivityAsync(
        String conversationId, String activityId, final ServiceCallback<Void> serviceCallback) {

        return ServiceFuture.fromResponse(deleteActivityWithServiceResponseAsync(conversationId, activityId), serviceCallback);
    }

    /**
     * Implementation of deleteActivityAsync.
     *
     * @see Conversations#deleteActivityAsync
     */
    @Override
    public Observable<Void> deleteActivityAsync(String conversationId, String activityId) {
        return deleteActivityWithServiceResponseAsync(conversationId, activityId).map(response -> response.body());
    }

    /**
     * Implementation of deleteActivityWithServiceResponseAsync.
     *
     * @see Conversations#deleteActivityWithServiceResponseAsync
     */
    @Override
    public Observable<ServiceResponse<Void>> deleteActivityWithServiceResponseAsync(
        String conversationId, String activityId) {

        if (conversationId == null) {
            throw new IllegalArgumentException("Parameter conversationId is required and cannot be null.");
        }
        if (activityId == null) {
            throw new IllegalArgumentException("Parameter activityId is required and cannot be null.");
        }
        return service.deleteActivity(conversationId, activityId, this.client.getAcceptLanguage(), this.client.getUserAgent())
            .flatMap((Func1<Response<ResponseBody>, Observable<ServiceResponse<Void>>>) response -> {
                try {
                    ServiceResponse<Void> clientResponse = deleteActivityDelegate(response);
                    return Observable.just(clientResponse);
                } catch (Throwable t) {
                    return Observable.error(t);
                }
            });
    }

    private ServiceResponse<Void> deleteActivityDelegate(
        Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {

        return this.client.restClient().responseBuilderFactory().<Void, ErrorResponseException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<Void>() { }.getType())
                .register(202, new TypeToken<Void>() { }.getType())
                .registerError(ErrorResponseException.class)
                .build(response);
    }

    /**
     * Implementation of getConversationMembers.
     *
     * @see Conversations#getConversationMembers
     */
    @Override
    public List<ChannelAccount> getConversationMembers(String conversationId) {
        return getConversationMembersWithServiceResponseAsync(conversationId).toBlocking().single().body();
    }

    /**
     * Implementation of getConversationMembersAsync.
     *
     * @see Conversations#getConversationMembersAsync
     */
    @Override
    public ServiceFuture<List<ChannelAccount>> getConversationMembersAsync(
        String conversationId, final ServiceCallback<List<ChannelAccount>> serviceCallback) {

        return ServiceFuture.fromResponse(getConversationMembersWithServiceResponseAsync(conversationId), serviceCallback);
    }

    /**
     * Implementation of getConversationMembersAsync.
     *
     * @see Conversations#getConversationMembersAsync
     */
    @Override
    public Observable<List<ChannelAccount>> getConversationMembersAsync(String conversationId) {
        return getConversationMembersWithServiceResponseAsync(conversationId).map(response -> response.body());
    }

    /**
     * Implementation of getConversationMembersWithServiceResponseAsync.
     *
     * @see Conversations#getConversationMembersWithServiceResponseAsync
     */
    @Override
    public Observable<ServiceResponse<List<ChannelAccount>>> getConversationMembersWithServiceResponseAsync(
        String conversationId) {

        if (conversationId == null) {
            throw new IllegalArgumentException("Parameter conversationId is required and cannot be null.");
        }
        return service.getConversationMembers(conversationId, this.client.getAcceptLanguage(), this.client.getUserAgent())
            .flatMap((Func1<Response<ResponseBody>, Observable<ServiceResponse<List<ChannelAccount>>>>) response -> {
                try {
                    ServiceResponse<List<ChannelAccount>> clientResponse = getConversationMembersDelegate(response);
                    return Observable.just(clientResponse);
                } catch (Throwable t) {
                    return Observable.error(t);
                }
            });
    }

    private ServiceResponse<List<ChannelAccount>> getConversationMembersDelegate(
        Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {

        return this.client.restClient().responseBuilderFactory().<List<ChannelAccount>, ErrorResponseException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<List<ChannelAccount>>() { }.getType())
                .registerError(ErrorResponseException.class)
                .build(response);
    }

    /**
     * Implementation of deleteConversationMember.
     *
     * @see Conversations#deleteConversationMember
     */
    @Override
    public void deleteConversationMember(String conversationId, String memberId) {
        deleteConversationMemberWithServiceResponseAsync(conversationId, memberId).toBlocking().single().body();
    }

    /**
     * Implementation of deleteConversationMemberAsync.
     *
     * @see Conversations#deleteConversationMemberAsync
     */
    @Override
    public ServiceFuture<Void> deleteConversationMemberAsync(
        String conversationId, String memberId, final ServiceCallback<Void> serviceCallback) {

        return ServiceFuture.fromResponse(deleteConversationMemberWithServiceResponseAsync(
            conversationId, memberId), serviceCallback);
    }

    /**
     * Implementation of deleteConversationMemberAsync.
     *
     * @see Conversations#deleteConversationMemberAsync
     */
    @Override
    public Observable<Void> deleteConversationMemberAsync(String conversationId, String memberId) {
        return deleteConversationMemberWithServiceResponseAsync(
            conversationId, memberId).map(response -> response.body());
    }

    /**
     * Implementation of deleteConversationMemberWithServiceResponseAsync.
     *
     * @see Conversations#deleteConversationMemberWithServiceResponseAsync
     */
    @Override
    public Observable<ServiceResponse<Void>> deleteConversationMemberWithServiceResponseAsync(
        String conversationId, String memberId) {

        if (conversationId == null) {
            throw new IllegalArgumentException("Parameter conversationId is required and cannot be null.");
        }
        if (memberId == null) {
            throw new IllegalArgumentException("Parameter memberId is required and cannot be null.");
        }
        return service.deleteConversationMember(conversationId, memberId, this.client.getAcceptLanguage(), this.client.getUserAgent())
            .flatMap((Func1<Response<ResponseBody>, Observable<ServiceResponse<Void>>>) response -> {
                try {
                    ServiceResponse<Void> clientResponse = deleteConversationMemberDelegate(response);
                    return Observable.just(clientResponse);
                } catch (Throwable t) {
                    return Observable.error(t);
                }
            });
    }


    private ServiceResponse<Void> deleteConversationMemberDelegate(
        Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {

        return this.client.restClient().responseBuilderFactory().<Void, ErrorResponseException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<Void>() { }.getType())
                .register(204, new TypeToken<Void>() { }.getType())
                .registerError(ErrorResponseException.class)
                .build(response);
    }

    /**
     * Implementation of getActivityMembers.
     *
     * @see Conversations#getActivityMembers
     */
    @Override
    public List<ChannelAccount> getActivityMembers(String conversationId, String activityId) {
        return getActivityMembersWithServiceResponseAsync(conversationId, activityId).toBlocking().single().body();
    }

    /**
     * Implementation of getActivityMembersAsync.
     *
     * @see Conversations#getActivityMembersAsync
     */
    @Override
    public ServiceFuture<List<ChannelAccount>> getActivityMembersAsync(
        String conversationId, String activityId, final ServiceCallback<List<ChannelAccount>> serviceCallback) {

        return ServiceFuture.fromResponse(getActivityMembersWithServiceResponseAsync(conversationId, activityId), serviceCallback);
    }

    /**
     * Implementation of getActivityMembersAsync.
     *
     * @see Conversations#getActivityMembersAsync
     */
    @Override
    public Observable<List<ChannelAccount>> getActivityMembersAsync(String conversationId, String activityId) {
        return getActivityMembersWithServiceResponseAsync(conversationId, activityId).map(response -> response.body());
    }

    /**
     * Implementation of getActivityMembersWithServiceResponseAsync.
     *
     * @see Conversations#getActivityMembersWithServiceResponseAsync
     */
    @Override
    public Observable<ServiceResponse<List<ChannelAccount>>> getActivityMembersWithServiceResponseAsync(
        String conversationId, String activityId) {

        if (conversationId == null) {
            throw new IllegalArgumentException("Parameter conversationId is required and cannot be null.");
        }
        if (activityId == null) {
            throw new IllegalArgumentException("Parameter activityId is required and cannot be null.");
        }
        return service.getActivityMembers(conversationId, activityId, this.client.getAcceptLanguage(), this.client.getUserAgent())
            .flatMap((Func1<Response<ResponseBody>, Observable<ServiceResponse<List<ChannelAccount>>>>) response -> {
                try {
                    ServiceResponse<List<ChannelAccount>> clientResponse = getActivityMembersDelegate(response);
                    return Observable.just(clientResponse);
                } catch (Throwable t) {
                    return Observable.error(t);
                }
            });
    }

    private ServiceResponse<List<ChannelAccount>> getActivityMembersDelegate(
        Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {

        return this.client.restClient().responseBuilderFactory().<List<ChannelAccount>, ErrorResponseException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<List<ChannelAccount>>() { }.getType())
                .registerError(ErrorResponseException.class)
                .build(response);
    }

    /**
     * Implementation of uploadAttachment.
     *
     * @see Conversations#uploadAttachment
     */
    @Override
    public ResourceResponse uploadAttachment(String conversationId, AttachmentData attachmentUpload) {
        return uploadAttachmentWithServiceResponseAsync(conversationId, attachmentUpload).toBlocking().single().body();
    }

    /**
     * Implementation of uploadAttachmentAsync.
     *
     * @see Conversations#uploadAttachmentAsync
     */
    @Override
    public ServiceFuture<ResourceResponse> uploadAttachmentAsync(
        String conversationId, AttachmentData attachmentUpload, final ServiceCallback<ResourceResponse> serviceCallback) {

        return ServiceFuture.fromResponse(uploadAttachmentWithServiceResponseAsync(conversationId, attachmentUpload), serviceCallback);
    }

    /**
     * Implementation of uploadAttachmentAsync.
     *
     * @see Conversations#uploadAttachmentAsync
     */
    @Override
    public Observable<ResourceResponse> uploadAttachmentAsync(String conversationId, AttachmentData attachmentUpload) {
        return uploadAttachmentWithServiceResponseAsync(
            conversationId, attachmentUpload).map(response -> response.body());
    }

    /**
     * Implementation of uploadAttachmentWithServiceResponseAsync.
     *
     * @see Conversations#uploadAttachmentWithServiceResponseAsync
     */
    @Override
    public Observable<ServiceResponse<ResourceResponse>> uploadAttachmentWithServiceResponseAsync(
        String conversationId, AttachmentData attachmentUpload) {

        if (conversationId == null) {
            throw new IllegalArgumentException("Parameter conversationId is required and cannot be null.");
        }
        if (attachmentUpload == null) {
            throw new IllegalArgumentException("Parameter attachmentUpload is required and cannot be null.");
        }
        Validator.validate(attachmentUpload);
        return service.uploadAttachment(conversationId, attachmentUpload, this.client.getAcceptLanguage(), this.client.getUserAgent())
            .flatMap((Func1<Response<ResponseBody>, Observable<ServiceResponse<ResourceResponse>>>) response -> {
                try {
                    ServiceResponse<ResourceResponse> clientResponse = uploadAttachmentDelegate(response);
                    return Observable.just(clientResponse);
                } catch (Throwable t) {
                    return Observable.error(t);
                }
            });
    }

    private ServiceResponse<ResourceResponse> uploadAttachmentDelegate(
        Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {

        return this.client.restClient().responseBuilderFactory().<ResourceResponse, ErrorResponseException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<ResourceResponse>() { }.getType())
                .register(201, new TypeToken<ResourceResponse>() { }.getType())
                .register(202, new TypeToken<ResourceResponse>() { }.getType())
                .registerError(ErrorResponseException.class)
                .build(response);
    }


    /**
     * Implementation of sendConversationHistory.
     *
     * @see Conversations#sendConversationHistory
     */
    @Override
    public ResourceResponse sendConversationHistory(String conversationId, Transcript history) {
        return sendConversationHistoryWithServiceResponseAsync(conversationId, history).toBlocking().single().body();
    }

    /**
     * Implementation of sendConversationHistoryAsync.
     *
     * @see Conversations#sendConversationHistoryAsync
     */
    @Override
    public ServiceFuture<ResourceResponse> sendConversationHistoryAsync(
        String conversationId, Transcript history, final ServiceCallback<ResourceResponse> serviceCallback) {

        return ServiceFuture.fromResponse(sendConversationHistoryWithServiceResponseAsync(conversationId, history), serviceCallback);
    }

    /**
     * Implementation of sendConversationHistoryAsync.
     *
     * @see Conversations#sendConversationHistoryAsync
     */
    @Override
    public Observable<ResourceResponse> sendConversationHistoryAsync(String conversationId, Transcript history) {
        return sendConversationHistoryWithServiceResponseAsync(
            conversationId, history).map(response -> response.body());
    }

    /**
     * Implementation of sendConversationHistoryWithServiceResponseAsync.
     *
     * @see Conversations#sendConversationHistoryWithServiceResponseAsync
     */
    @Override
     public Observable<ServiceResponse<ResourceResponse>> sendConversationHistoryWithServiceResponseAsync(
         String conversationId, Transcript history) {

        if (conversationId == null) {
            throw new IllegalArgumentException("Parameter conversationId is required and cannot be null.");
        }
        if (history == null) {
            throw new IllegalArgumentException("Parameter history is required and cannot be null.");
        }
        Validator.validate(history);
        return service.sendConversationHistory(conversationId, history, this.client.getAcceptLanguage(), this.client.getUserAgent())
            .flatMap((Func1<Response<ResponseBody>, Observable<ServiceResponse<ResourceResponse>>>) response -> {
                try {
                    ServiceResponse<ResourceResponse> clientResponse = sendConversationHistoryDelegate(response);
                    return Observable.just(clientResponse);
                } catch (Throwable t) {
                    return Observable.error(t);
                }
            });
    }

    private ServiceResponse<ResourceResponse> sendConversationHistoryDelegate(
        Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {

        return this.client.restClient().responseBuilderFactory().<ResourceResponse, ErrorResponseException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<ResourceResponse>() { }.getType())
                .register(201, new TypeToken<ResourceResponse>() { }.getType())
                .register(202, new TypeToken<ResourceResponse>() { }.getType())
                .registerError(ErrorResponseException.class)
                .build(response);
    }


    /**
     * Implementation of getConversationPagedMembers.
     *
     * @see Conversations#getConversationPagedMembers
     */
    @Override
    public PagedMembersResult getConversationPagedMembers(String conversationId){
        return getConversationPagedMembersWithServiceResponseAsync(conversationId).toBlocking().single().body();
    }

    /**
     * Implementation of getConversationPagedMembersAsync.
     *
     * @see Conversations#getConversationPagedMembersAsync
     */
    @Override
    public ServiceFuture<PagedMembersResult> getConversationPagedMembersAsync(
        String conversationId, final ServiceCallback<PagedMembersResult> serviceCallback){

        return ServiceFuture.fromResponse(getConversationPagedMembersWithServiceResponseAsync(conversationId), serviceCallback);
    }

    /**
     * Implementation of getConversationPagedMembersAsync.
     *
     * @see Conversations#getConversationPagedMembersAsync
     */
    @Override
    public Observable<PagedMembersResult> getConversationPagedMembersAsync(String conversationId){
        return getConversationPagedMembersWithServiceResponseAsync(conversationId).map(response -> response.body());
    }

    /**
     * Implementation of getConversationPagedMembersWithServiceResponseAsync.
     *
     * @see Conversations#getConversationPagedMembersWithServiceResponseAsync
     */
    @Override
    public Observable<ServiceResponse<PagedMembersResult>> getConversationPagedMembersWithServiceResponseAsync(
        String conversationId){

        if (conversationId == null) {
            throw new IllegalArgumentException("Parameter conversationId is required and cannot be null.");
        }
        return service.getConversationPagedMembers(conversationId, this.client.getAcceptLanguage(), this.client.getUserAgent())
            .flatMap((Func1<Response<ResponseBody>, Observable<ServiceResponse<PagedMembersResult>>>) response -> {
                try {
                    ServiceResponse<PagedMembersResult> clientResponse = getConversationPagedMembersDelegate(response);
                    return Observable.just(clientResponse);
                } catch (Throwable t) {
                    return Observable.error(t);
                }
            });
    }

    private ServiceResponse<PagedMembersResult> getConversationPagedMembersDelegate(
        Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {

        return this.client.restClient().responseBuilderFactory().<PagedMembersResult, ErrorResponseException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<PagedMembersResult>() { }.getType())
                .registerError(ErrorResponseException.class)
                .build(response);
    }
}