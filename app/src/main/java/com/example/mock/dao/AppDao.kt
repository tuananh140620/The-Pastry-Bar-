package com.example.mock.dao

import androidx.room.*

@Dao
interface AppDao {
//    @Query("SELECT * FROM $PERSON_TABLE ORDER BY name")
//    fun getPersons(): LiveData<List<Person>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertPerson(person: Person)
//
//    @Query("SELECT * FROM $PERSON_TABLE WHERE phone = :phone ")
//    fun checkPersonLogin(phone : String): Person
//
//    @Query("SELECT * FROM $PERSON_TABLE WHERE phone LIKE :phone")
//    fun findPersonByPhone(phone: String): Person
//
//    @Query("SELECT * FROM $PERSON_TABLE WHERE name LIKE :key")
//    fun findPersonByName(key: String): Person
//
//    @Query("DELETE FROM $PERSON_TABLE WHERE $PERSON_ID == :id")
//    fun deletePerson(id: String)
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    fun updatePerson(person: Person)
//
//    @Query("SELECT EXISTS(SELECT * FROM $PERSON_TABLE WHERE phone = :phone AND password =:password)")
//    fun checkPhonePassword(phone: String, password: String): Boolean
//
//    @Query("SELECT * FROM $ORDER_TABLE")
//    fun getOrders(): LiveData<List<Order>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertOrder(order: Order)
//
//    @Query("SELECT * FROM $ORDER_TABLE WHERE $ORDER_ID = :id ")
//    fun findOrderByID(id: Int): Order
//
//    @Query("SELECT * FROM $ORDER_TABLE WHERE customerID = :id ")
//    fun findOrderByCustomer(id: Int): LiveData<List<Order>>
//
//    @Query("SELECT * FROM $ORDER_TABLE WHERE status = :status ")
//    fun findOrderByStatus(status: String): LiveData<List<Order>>
//
//    @Query("DELETE FROM $ORDER_TABLE WHERE $ORDER_ID == :id")
//    fun deleteOrder(id: Int)
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    fun updateOrder(order: Order)
//
//    @Query("UPDATE $ORDER_TABLE SET status=:status WHERE $ORDER_ID== :id")
//    fun updateOrderStatus(id: Int, status: String)
//
//    @Query("SELECT * FROM $PRODUCT_TABLE ORDER BY name")
//    fun getProducts(): LiveData<List<Product>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAll(products: List<Product>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertProduct(product: Product)
//
//    @Query("SELECT * FROM $PRODUCT_TABLE WHERE $PRODUCT_ID == :key")
//    fun findProductByID(key: String): Product
//
//    @Query("SELECT * FROM $PRODUCT_TABLE WHERE name LIKE :key")
//    fun findProductByName(key: String): Product
//
//    @Query("SELECT * FROM $PRODUCT_TABLE WHERE type LIKE :key")
//    fun findProductByType(key: String): LiveData<List<Product>>
//
//    @Query("SELECT * FROM $PRODUCT_TABLE WHERE price > :minPrice")
//    fun findProductsByMinPrice(minPrice: Int): LiveData<List<Product>>
//
//    @Query("SELECT * FROM $PRODUCT_TABLE WHERE price < :maxPrice")
//    fun findProductsByMaxPrice(maxPrice: Int): LiveData<List<Product>>
//
//    @Query("DELETE FROM $PRODUCT_TABLE WHERE $PRODUCT_ID == :id")
//    fun deleteProduct(id: Int)
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    fun updateProduct(product: Product)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun addToCart(cart: Cart)
//
//    @Query("SELECT * FROM $CART_TABLE WHERE $PERSON_ID == :id")
//    fun searchCart(id: Int): LiveData<List<Cart>>
//
//    @Query("DELETE FROM $CART_TABLE WHERE $PERSON_ID == :id")
//    fun checkoutCart(id: Int)
//
//    @Query("SELECT * FROM stores ORDER BY name")
//    fun getStores(): LiveData<List<Store>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertStores(stores: List<Store>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertStore(store: Store)
//
//    @Query("SELECT * FROM stores WHERE name LIKE :key")
//    fun findByName(key: String): Store
//
//    @Query("DELETE FROM stores WHERE id == :id")
//    fun deleteStore(id: Int)
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    fun updateStore(store: Store)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertStock(stock: Stock)
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    fun updateStock(stock: Stock)
//
//    @Query("SELECT * FROM $ORDER_ITEM_TABLE")
//    fun getOrderItems(): LiveData<List<OrderItem>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertOrderItems(orderItem: OrderItem)
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    fun updateOrderItem(orderItem: OrderItem)
//
//    @Query("SELECT * FROM $ORDER_ITEM_TABLE WHERE $ORDER_ID = :id ")
//    fun findOrderItemByOrder(id: String): LiveData<List<OrderItem>>
//
//    @Query("SELECT * FROM $ORDER_TABLE join $ORDER_ITEM_TABLE ON $ORDER_TABLE.$ORDER_ID = $ORDER_ITEM_TABLE.$ORDER_ID")
//    fun findOrderAndItems(): LiveData<List<OrderWithItems>>
//
//    @Query("SELECT * FROM $ORDER_TABLE join $ORDER_ITEM_TABLE ON $ORDER_TABLE.$ORDER_ID = $ORDER_ITEM_TABLE.$ORDER_ID WHERE $ORDER_TABLE.$ORDER_ID = :id ")
//    fun findOrderAndItemsByID(id: String): LiveData<List<OrderWithItems>>
}