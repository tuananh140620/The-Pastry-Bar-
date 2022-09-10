//package com.example.mock
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.LiveData
//import androidx.room.Room
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import androidx.test.platform.app.InstrumentationRegistry
//import com.google.common.truth.Truth.assertThat
//import gst.trainingcourse.firebasedemo.dao.AppDao
//import gst.trainingcourse.firebasedemo.database.AppDatabase
//import gst.trainingcourse.firebasedemo.entity.*
//import kotlinx.coroutines.runBlocking
//import org.junit.After
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import java.util.concurrent.CountDownLatch
//import java.util.concurrent.TimeUnit
//
//@RunWith(AndroidJUnit4::class)
//class DaoTest {
//    private lateinit var database: AppDatabase
//    private lateinit var dao: AppDao
//    private val personA = Person("1", "A", "22A", "a@gmail.com", "0922", "aaa", "1", 0)
//    private val personB = Person("2", "B", "22B", "b@gmail.com", "2321", "sa", "0", 0)
//    private val personC = Person("3", "C", "22C", "c@gmail.com", "122", "121", "0", 0)
//    private val personD = Person("3", "D", "22F", "d@gmail.com", "888", "22", "1", 0)
//    private val persons = listOf(personA, personB, personC)
//    private val orderA = Order("1", "1", OrderStatus.Confirmed)
//    private val orderB = Order("2", "2", OrderStatus.Pending)
//    private val orderC = Order("3", "1", OrderStatus.Pending)
//    private val storeA = Store("1", "ABC", "222A", "0988")
//    private val productA = Product("1", "ABC", "222A", "https://imgur.com/ZigXHzX", 10000, 0.2, 100, "QA")
//    private val productB = Product("2", "ABC", "222A", "https://imgur.com/ZigXHzX", 10000, 0.2, 100, "QA")
//    private val stockA = Stock("1", "1", 20)
//    private val orderItemA1 = OrderItem("1", "1", 20, 10000, 0.2)
//    private val orderItemA2 = OrderItem("1", "2", 10, 30000, 0.1)
//    private val orderItemB1 = OrderItem("2", "1", 30, 10000, 0.2)
//    private val cartA = Cart("1", "1", 10)
//    private val cartB = Cart("1", "2", 50)
//    private val cartC = Cart("2", "1", 4)
//
//    @Throws(InterruptedException::class)
//    fun <T> getValue(liveData: LiveData<T>): T {
//        val data = arrayOfNulls<Any>(1)
//        val latch = CountDownLatch(1)
//        liveData.observeForever { o ->
//            data[0] = o
//            latch.countDown()
//        }
//        latch.await(2, TimeUnit.SECONDS)
//
//        @Suppress("UNCHECKED_CAST")
//        return data[0] as T
//    }
//
//    @Rule
//    @JvmField
//    val rule = InstantTaskExecutorRule()
//
//    @Before
//    fun createDb() = runBlocking {
//        val context = InstrumentationRegistry.getInstrumentation().targetContext
//        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
//        dao = database.dao
//        persons.forEach { person ->
//            dao.insertPerson(person)
//        }
//        dao.insertOrder(orderA)
//        dao.insertOrder(orderB)
//        dao.insertOrder(orderC)
//        dao.insertStore(storeA)
//        dao.insertProduct(productA)
//        dao.insertStock(stockA)
//        dao.insertOrderItems(orderItemA1)
//        dao.insertOrderItems(orderItemA2)
//        dao.insertOrderItems(orderItemB1)
//        dao.addToCart(cartA)
//        dao.addToCart(cartB)
//        dao.addToCart(cartC)
//    }
//
//    @After
//    fun closeDb() {
//        database.close()
//    }
//
//    @Test
//    fun testGetPersons() = runBlocking {
//        val personList = getValue(dao.getPersons())
//        assertThat(personList.size).isEqualTo(3)
//        assertThat(personList[0]).isEqualTo(personA)
//        assertThat(personList[1]).isEqualTo(personB)
//        assertThat(personList[2]).isEqualTo(personC)
//    }
//
//    @Test
//    fun testGetOrdersByPersonID() = runBlocking {
//        val orderList = getValue(dao.findOrderByCustomer(1))
//        assertThat(orderList.size).isEqualTo(2)
//        assertThat(orderList[0]).isEqualTo(orderA)
//        assertThat(orderList[1]).isEqualTo(orderC)
//    }
//
//    @Test
//    fun testGetOrdersByStatus() = runBlocking {
//        val orderList = getValue(dao.findOrderByStatus("Pending"))
//        assertThat(orderList.size).isEqualTo(2)
//        assertThat(orderList[0]).isEqualTo(orderB)
//        assertThat(orderList[1]).isEqualTo(orderC)
//    }
//
//    @Test
//    fun testDeletePersons() = runBlocking {
//        dao.deletePerson("2")
//        val personList = getValue(dao.getPersons())
//        assertThat(personList.size).isEqualTo(2)
//        assertThat(personList[0]).isEqualTo(personA)
//        assertThat(personList[1]).isEqualTo(personC)
//    }
//
//    @Test
//    fun testUpdatePersons() = runBlocking {
//        dao.updatePerson(personD)
//        val personList = getValue(dao.getPersons())
//        assertThat(personList[2]).isEqualTo(personD)
//    }
//
//    @Test
//    fun testOrderItems() = runBlocking {
//        val orderItemList = getValue(dao.findOrderItemByOrder("1"))
//        assertThat(orderItemList[0]).isEqualTo(orderItemA1)
//        assertThat(orderItemList[1]).isEqualTo(orderItemA2)
//    }
//
//    @Test
//    fun testOrderAndItems() = runBlocking {
//        val orderItemList = getValue(dao.findOrderAndItems())
//        val orderWithItemsA = OrderWithItems(orderA, listOf(orderItemA1, orderItemA2))
//        assertThat(orderItemList.size).isEqualTo(3)
//        assertThat(orderItemList[0]).isEqualTo(orderWithItemsA)
//    }
//
//    @Test
//    fun testOrderAndItemsByID() = runBlocking {
//        val orderItemList = getValue(dao.findOrderAndItemsByID("2"))
//        val orderWithItemsB = OrderWithItems(orderB, listOf(orderItemB1))
//        assertThat(orderItemList.size).isEqualTo(1)
//        assertThat(orderItemList[0]).isEqualTo(orderWithItemsB)
//    }
//
//    @Test
//    fun testCart() = runBlocking {
//        var cartList = getValue(dao.searchCart(1))
//        assertThat(cartList.size).isEqualTo(2)
//        assertThat(cartList[0]).isEqualTo(cartA)
//        assertThat(cartList[1]).isEqualTo(cartB)
//        dao.checkoutCart(1)
//        cartList = getValue(dao.searchCart(1))
//        assertThat(cartList.isEmpty()).isTrue()
//    }
//}