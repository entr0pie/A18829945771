package br.edu.up.a18829945771.data
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

data class Item(
    @DocumentId val id: String = "",
    val name: String,
    val description: String
) {
    constructor() : this("", "", "") {}
}

interface FirebaseItemsDataSource {
    fun list(): Flow<List<Item>>
    fun create(item: Item)
    fun update(item: Item)
    fun delete(item: Item)
}

class FirebaseItemsDataSourceImpl : FirebaseItemsDataSource {

    private val firestore = FirebaseFirestore.getInstance()
    private val collection = firestore.collection("items")
    private val idGenerator = idGenerator()

    override fun list(): Flow<List<Item>> {
        return callbackFlow {
            val listener = collection.addSnapshotListener { data, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                if (data != null) {
                    val items = data.documents.mapNotNull {
                        it.toObject(Item::class.java)
                    }

                    trySend(items).isSuccess
                }
            }
            awaitClose { listener.remove() }
        }

    }

    override fun create(item: Item) {
        val itemWithId = Item(idGenerator.generate(), item.name, item.description)
        val document = collection.document(itemWithId.id)
        document.set(itemWithId)
    }

    override fun update(item: Item) {
        val document = collection.document(item.id)

        val updates = mapOf(
            "name" to item.name,
            "description" to item.description
        )

        document.update(updates)
    }

    override fun delete(item: Item) {
        collection.document(item.id).delete()
    }

}