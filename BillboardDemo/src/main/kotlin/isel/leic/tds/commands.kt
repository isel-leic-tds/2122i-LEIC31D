package isel.leic.tds

import isel.leic.tds.firestore.getAllDocuments
import isel.leic.tds.firestore.getService


fun getMessagesFrom(author: Author) =
    getService().getAllDocuments(author.id).map {
        Message(
            author = author,
            content = it.getString("content") ?: ""
        )
    }

fun getAllMessages(): Iterable<Message> {
    TODO()
}