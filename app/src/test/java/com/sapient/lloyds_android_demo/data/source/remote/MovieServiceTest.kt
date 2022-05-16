package com.sapient.lloyds_android_demo.data.source.remote


import com.sapient.lloyds_android_demo.domain.model.Movies
import io.reactivex.rxjava3.observers.TestObserver
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create


@RunWith(JUnit4::class)
class MovieServiceTest {
private  lateinit var service: MovieService
private lateinit var mockWebServer: MockWebServer
    @Before
   fun init() {
  mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create()
    }

    @After
    fun cleanup(){
        mockWebServer.shutdown()
    }

    @Test
    fun getTrendingMovie(){
        enqueueResponse("trending_movies.json")

        val testObserver = TestObserver<Movies>()
        service.getTrendingMovie(1).subscribe(testObserver)

        //random test the values
        testObserver.await()
            .assertValue{
                return@assertValue it.page == 1L
            }
            .assertValue{
                return@assertValue it.totalPage == 1000L
            }
            .assertValue{
                return@assertValue it.results[0].title == "The Northman"
            }
            .assertValue{
                return@assertValue it.results[0].id == 639933L
            }
            .assertComplete()
            .assertNoErrors()

        //test the request path
        val takeRequest = mockWebServer.takeRequest()
        assertThat(takeRequest.path,`is`("/trending/all/day?page=1"))
    }

    private fun enqueueResponse(fileName:String,headers:Map<String,String> = emptyMap()){
        val inputStream =javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse =MockResponse()
        for((key,value)in headers){
            mockResponse.addHeader(key,value)
        }
        mockWebServer.enqueue(mockResponse.setBody(source.readString(Charsets.UTF_8)))
    }
}
