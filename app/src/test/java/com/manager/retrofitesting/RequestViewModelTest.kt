package com.manager.retrofitesting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.manager.retrofitesting.model.MainData
import com.manager.retrofitesting.model.api.ApiHelperImpl
import com.manager.retrofitesting.model.api.RetrofitBuilder
import com.manager.retrofitesting.util.Resource
import com.manager.retrofitesting.viewmodel.RequestViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.json.JSONObject
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class RequestViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: RequestViewModel

    private lateinit var apiHelper: ApiHelperImpl

    @Mock
    private lateinit var apiEmployeeObserver: Observer<Resource<MainData>?>

    private lateinit var mockWebServer: MockWebServer


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = RequestViewModel()
        viewModel.getDetail().observeForever(apiEmployeeObserver)

        mockWebServer = MockWebServer()
        mockWebServer.start()
        apiHelper = ApiHelperImpl(RetrofitBuilder.apiInterface)
    }

    @Test
    fun `read sample success json file`(){
        val reader = MockResponseFileReader("success_response.json")
        assertNotNull(reader.content)
    }

    @Test
    fun `fetch details and check response Code 200 returned`(){
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("success_response.json").content)
        mockWebServer.enqueue(response)
        // Act
        val  actualResponse = apiHelper.getEmployeeDetails().execute()
        // Assert
        assertEquals(response.toString().contains("200"),actualResponse.code().toString().contains("200"))
    }

    @Test
    fun `fetch details and check response success returned`(){
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("success_response.json").content)
        mockWebServer.enqueue(response)
        val mockResponse = response.getBody()?.readUtf8()
        // Act
        val  actualResponse = apiHelper.getEmployeeDetails().execute()
        // Assert
        assertEquals(mockResponse?.let { `parse mocked JSON response`(it) }, actualResponse.body()?.status)
    }

    private fun `parse mocked JSON response`(mockResponse: String): String {
        val reader = JSONObject(mockResponse)
        return reader.getString("status")
    }

    @Test
    fun `fetch details for failed response 400 returned`(){
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
            .setBody(MockResponseFileReader("failed_response.json").content)
        mockWebServer.enqueue(response)
        // Act
        val  actualResponse = apiHelper.getEmployeeDetails().execute()
        // Assert
        assertEquals(response.toString().contains("400"),actualResponse.toString().contains("400"))
    }

    @After
    fun tearDown() {
        viewModel.getDetail().removeObserver(apiEmployeeObserver)
        mockWebServer.shutdown()
    }
}