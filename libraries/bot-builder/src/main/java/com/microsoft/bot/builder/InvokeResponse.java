// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.bot.builder;

/**
 * Tuple class containing an HTTP Status Code and a JSON Serializable
 * object. The HTTP Status code is, in the invoke activity scenario, what will
 * be set in the resulting POST. The Body of the resulting POST will be
 * the JSON Serialized content from the Body property.
 */
public class InvokeResponse {
    /**
     * The POST that is generated in response to the incoming Invoke Activity
     * will have the HTTP Status code specified by this field.
     */
    private int status;
    /**
     * The POST that is generated in response to the incoming Invoke Activity
     * will have a body generated by JSON serializing the object in the Body field.
     */
    private Object body;

    /**
     * Gets the HTTP status code for the response.
     * @return The HTTP status code.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the HTTP status code for the response.
     * @param withStatus The HTTP status code.
     */
    public void setStatus(int withStatus) {
        this.status = withStatus;
    }

    /**
     * Gets the body content for the response.
     * @return The body content.
     */
    public Object getBody() {
        return body;
    }

    /**
     * Sets the body content for the response.
     * @param withBody The body content.
     */
    public void setBody(Object withBody) {
        body = withBody;
    }
}
