package isel.leic.tds.firestore

import com.google.cloud.firestore.*
import java.io.File

private const val ENVIRONMENT_VAR = "GOOGLE_APPLICATION_CREDENTIALS"

/**
 * Checks if the application execution environment is correctly specified.
 * @throws IllegalStateException when the environment is invalid
 */
fun checkEnvironment() {
    val credentialsFileName: String? = System.getenv(ENVIRONMENT_VAR)
    check(!credentialsFileName.isNullOrBlank()) {
        "Provide location of credentials file through $ENVIRONMENT_VAR environment variable"
    }
    check(File(credentialsFileName).canRead()) { "Can't read credentials file $credentialsFileName" }
}

/**
 * Helper function to obtain the Firestore service instance.
 * @return  the service instance
 */
fun getService(): Firestore = FirestoreOptions.getDefaultInstance().service

/**
 * Synchronously gets all root collections.
 * @return the sequence of corresponding collection references
 */
fun Firestore.getRootCollections() = listCollections()

/**
 * Synchronously gets all documents from the given collection.
 * @return the sequence of document snapshots contained in the collection
 */
fun CollectionReference.getAllDocuments(): Iterable<DocumentSnapshot> {
    return this.listDocuments().map { it.get().get() }
}

/**
 * Synchronously gets all documents from the given collection.
 * @param collectionId  the collection identifier
 * @return the sequence of document snapshots contained in the collection
 */
fun Firestore.getAllDocuments(collectionId: String): Iterable<DocumentSnapshot> {
    val query = this.collection(collectionId).get()
    return query.get().documents
}

/**
 * Synchronously gets a document from a given collection.
 * @param collectionId  the collection identifier
 * @param documentId    the document identifier
 * @return  the document snapshot instance. If the document does not exist, the returned snapshot instance is empty
 */
fun Firestore.getDocument(collectionId: String, documentId: String): DocumentSnapshot {
    val query = this.document("$collectionId/$documentId").get()
    return query.get()
}

/**
 * Synchronously gets the given document from this collection.
 * @return the document snapshot. If the document does not exist, the returned snapshot instance is empty.
 */
fun CollectionReference.getDocument(documentId: String): DocumentSnapshot {
    return this.getDocument(documentId)
}
