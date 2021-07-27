/**
 * <p>These are just the POJOs that define entities to be be either passed into the service
 * calls or returned from the service calls. None of these classes need to define @JsonSerialize
 * since they aren't passing data from this client to a front-end framework, but if this
 * application were ever to be converted to a webservice of some sort, then JSON serialization
 * would need to be added to some of the model objects defined in this package.</p>
 * <p>Additionally, some of these model objects do describe how to deserialize JSON as they
 * are expected return values from webservice invocations. Other model objects do not describe
 * JSON deserialization since they are used only locally within the application for passing
 * data between methods and objects.</p>
 */
package home.safrin.models;