package isel.leic.tds


fun getMessagesFrom(billboard: Billboard, author: Author): Iterable<Message> =
    billboard.getAllMessages(author)

fun getAllMessages(): Iterable<Message> {
    TODO()
}