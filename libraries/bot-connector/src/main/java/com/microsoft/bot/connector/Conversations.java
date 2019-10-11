/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 * <p>
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.microsoft.bot.connector;

import com.microsoft.bot.schema.Activity;
import com.microsoft.bot.schema.AttachmentData;
import com.microsoft.bot.schema.ChannelAccount;
import com.microsoft.bot.schema.ConversationParameters;
import com.microsoft.bot.schema.ConversationResourceResponse;
import com.microsoft.bot.schema.ConversationsResult;
import com.microsoft.bot.schema.PagedMembersResult;
import com.microsoft.bot.schema.ResourceResponse;
import com.microsoft.bot.schema.Transcript;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * An instance of this class provides access to all the operations defined
 * in Conversations.
 */
public interface Conversations {
    /**
     * GetConversations.
     * List the Conversations in which this bot has participated.
     * GET from this method with a skip token
     * The return value is a ConversationsResult, which contains an array of ConversationMembers and a skip token.
     * If the skip token is not empty, then there are further values to be returned. Call this method again with the
     * returned token to get more values.
     *
     * Each ConversationMembers object contains the ID of the conversation and an array of ChannelAccounts that
     * describe the members of the conversation.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the ConversationsResult object
     */
    CompletableFuture<ConversationsResult> getConversations();

    /**
     * GetConversations.
     * List the Conversations in which this bot has participated.
     * GET from this method with a skip token
     * The return value is a ConversationsResult, which contains an array of ConversationMembers and a skip token.
     * If the skip token is not empty, then there are further values to be returned. Call this method again with the
     * returned token to get more values.
     * Each ConversationMembers object contains the ID of the conversation and an array of ChannelAccounts that
     * describe the members of the conversation.
     *
     * @param continuationToken skip or continuation token
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the ConversationsResult object
     */
    CompletableFuture<ConversationsResult> getConversations(String continuationToken);

    /**
     * CreateConversation.
     * Create a new Conversation.
     * POST to this method with a
     * Bot being the bot creating the conversation
     * IsGroup set to true if this is not a direct message (default is false)
     * Members array contining the members you want to have be in the conversation.
     * The return value is a ResourceResponse which contains a conversation id which is suitable for use
     * in the message payload and REST API uris.
     * Most channels only support the semantics of bots initiating a direct message conversation.  An example of how
     * to do that would be:
     * ```
     * var resource = await connector.conversations.CreateConversation(new ConversationParameters(){ Bot = bot,
     *     members = new ChannelAccount[] { new ChannelAccount("user1") } );
     * await connect.Conversations.SendToConversation(resource.Id, new Activity() ... ) ;
     * ```
     *
     * @param parameters Parameters to create the conversation from
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the ConversationResourceResponse object
     */
    CompletableFuture<ConversationResourceResponse> createConversation(ConversationParameters parameters);

    /**
     * SendToConversation.
     * This method allows you to send an activity to the end of a conversation.
     * This is slightly different from ReplyToActivity().
     * SendToConverstion(conversationId) - will append the activity to the end of the conversation according to the
     * timestamp or semantics of the channel.
     * ReplyToActivity(conversationId,ActivityId) - adds the activity as a reply to another activity, if the channel
     * supports it. If the channel does not support nested replies, ReplyToActivity falls back to SendToConversation.
     * Use ReplyToActivity when replying to a specific activity in the conversation.
     * Use SendToConversation in all other cases.
     *
     * @param conversationId Conversation ID
     * @param activity Activity to send
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the ResourceResponse object
     */
    CompletableFuture<ResourceResponse> sendToConversation(String conversationId, Activity activity);

    /**
     * SendToConversation.
     * This method allows you to send an activity to the end of a conversation.
     * This is slightly different from ReplyToActivity().
     * sendToConverstion(activity) - will append the activity to the end of the conversation according to the
     * timestamp or semantics of the channel, using the Activity.getConversation.getId for the conversation id.
     * replyToActivity(conversationId,ActivityId) - adds the activity as a reply to another activity, if the channel
     * supports it. If the channel does not support nested replies, ReplyToActivity falls back to SendToConversation.
     * Use ReplyToActivity when replying to a specific activity in the conversation.
     * Use SendToConversation in all other cases.
     *
     * @param activity Activity to send
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the ResourceResponse object
     */
    default CompletableFuture<ResourceResponse> sendToConversation(Activity activity) {
        return sendToConversation(activity.getConversation().getId(), activity);
    }

    /**
     * UpdateActivity.
     * Edit an existing activity.
     * Some channels allow you to edit an existing activity to reflect the new state of a bot conversation.
     * For example, you can remove buttons after someone has clicked "Approve" button.
     *
     * @param conversationId Conversation ID
     * @param activityId activityId to update
     * @param activity replacement Activity
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the ResourceResponse object
     */
    CompletableFuture<ResourceResponse> updateActivity(String conversationId, String activityId, Activity activity);

    /**
     * UpdateActivity.
     * Edit an existing activity.
     * Some channels allow you to edit an existing activity to reflect the new state of a bot conversation.
     * For example, you can remove buttons after someone has clicked "Approve" button.
     *
     * @param activity replacement Activity
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the ResourceResponse object
     */
    default CompletableFuture<ResourceResponse> updateActivity(Activity activity) {
        return updateActivity(activity.getConversation().getId(), activity.getId(), activity);
    }

    /**
     * ReplyToActivity.
     * This method allows you to reply to an activity.
     * This is slightly different from SendToConversation().
     * SendToConversation(conversationId) - will append the activity to the end of the conversation according to the
     * timestamp or semantics of the channel.
     * ReplyToActivity(conversationId,ActivityId) - adds the activity as a reply to another activity, if the channel
     * supports it. If the channel does not support nested replies, ReplyToActivity falls back to SendToConversation.
     * Use ReplyToActivity when replying to a specific activity in the conversation.
     * Use SendToConversation in all other cases.
     *
     * @param conversationId Conversation ID
     * @param activityId activityId the reply is to (OPTIONAL)
     * @param activity Activity to send
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the ResourceResponse object
     */
    CompletableFuture<ResourceResponse> replyToActivity(String conversationId, String activityId, Activity activity);

    /**
     * ReplyToActivity.
     * This method allows you to reply to an activity.
     * This is slightly different from SendToConversation().
     * SendToConversation(conversationId) - will append the activity to the end of the conversation according to the
     * timestamp or semantics of the channel.
     * ReplyToActivity(conversationId,ActivityId) - adds the activity as a reply to another activity, if the channel
     * supports it. If the channel does not support nested replies, ReplyToActivity falls back to SendToConversation.
     * Use ReplyToActivity when replying to a specific activity in the conversation.
     * Use SendToConversation in all other cases.
     *
     * @param activity Activity to send
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the ResourceResponse object
     */
    default CompletableFuture<ResourceResponse> replyToActivity(Activity activity) {
        if (StringUtils.isEmpty(activity.getReplyToId())) {
            throw new IllegalArgumentException("ReplyToId cannot be empty");
        }

        return replyToActivity(activity.getConversation().getId(), activity.getReplyToId(), activity);
    }

    /**
     * DeleteActivity.
     * Delete an existing activity.
     * Some channels allow you to delete an existing activity, and if successful this method will remove the specified
     * activity.
     *
     * @param conversationId Conversation ID
     * @param activityId activityId to delete
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link com.microsoft.bot.rest.ServiceResponse} object if successful.
     */
    CompletableFuture<Void> deleteActivity(String conversationId, String activityId);

    /**
     * GetConversationMembers.
     * Enumerate the members of a converstion.
     * This REST API takes a ConversationId and returns an array of ChannelAccount objects representing the members
     * of the conversation.
     *
     * @param conversationId Conversation ID
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the List&lt;ChannelAccount&gt; object
     */
    CompletableFuture<List<ChannelAccount>> getConversationMembers(String conversationId);

    /**
     * DeleteConversationMember.
     * Deletes a member from a conversation.
     * This REST API takes a ConversationId and a memberId (of type string) and removes that member from the
     * conversation. If that member was the last member of the conversation, the conversation will also be deleted.
     *
     * @param conversationId Conversation ID
     * @param memberId ID of the member to delete from this conversation
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link com.microsoft.bot.rest.ServiceResponse} object if successful.
     */
    CompletableFuture<Void> deleteConversationMember(String conversationId, String memberId);

    /**
     * GetActivityMembers.
     * Enumerate the members of an activity.
     * This REST API takes a ConversationId and a ActivityId, returning an array of ChannelAccount objects
     * representing the members of the particular activity in the conversation.
     *
     * @param conversationId Conversation ID
     * @param activityId Activity ID
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the List&lt;ChannelAccount&gt; object
     */
    CompletableFuture<List<ChannelAccount>> getActivityMembers(String conversationId, String activityId);

    /**
     * UploadAttachment.
     * Upload an attachment directly into a channel's blob storage.
     * This is useful because it allows you to store data in a compliant store when dealing with enterprises.
     * The response is a ResourceResponse which contains an AttachmentId which is suitable for using with the
     * attachments API.
     *
     * @param conversationId Conversation ID
     * @param attachmentUpload Attachment data
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the ResourceResponse object
     */
    CompletableFuture<ResourceResponse> uploadAttachment(String conversationId, AttachmentData attachmentUpload);

    /**
     * This method allows you to upload the historic activities to the conversation.
     *
     * Sender must ensure that the historic activities have unique ids and appropriate timestamps.
     * The ids are used by the client to deal with duplicate activities and the timestamps are used by
     * the client to render the activities in the right order.
     *
     * @param conversationId Conversation ID
     * @param history Historic activities
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the ResourceResponse object if successful.
     */
    CompletableFuture<ResourceResponse> sendConversationHistory(String conversationId, Transcript history);

    /**
     * Enumerate the members of a conversation one page at a time.
     *
     * This REST API takes a ConversationId. Optionally a pageSize and/or continuationToken can be provided.
     * It returns a PagedMembersResult, which contains an array of ChannelAccounts representing the members
     * of the conversation and a continuation token that can be used to get more values.
     *
     * One page of ChannelAccounts records are returned with each call. The number of records in a page may
     * vary between channels and calls. The pageSize parameter can be used as a suggestion. If there are no
     * additional results the response will not contain a continuation token. If there are no members in the
     * conversation the Members will be empty or not present in the response.
     *
     * A response to a request that has a continuation token from a prior request may rarely return members
     * from a previous request.
     *
     * @param conversationId Conversation ID
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the PagedMembersResult object if successful.
     */
    CompletableFuture<PagedMembersResult> getConversationPagedMembers(String conversationId);
}
