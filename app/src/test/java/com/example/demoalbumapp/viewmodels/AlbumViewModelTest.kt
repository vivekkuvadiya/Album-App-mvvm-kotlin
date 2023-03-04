package com.example.demoalbumapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.demoalbumapp.remote.model.Photo
import com.example.demoalbumapp.remote.repository.MainRepository
import com.example.demoalbumapp.repository.FakeMainRepository
import com.example.demoalbumapp.ui.albums.AlbumsViewModel
import com.example.demoalbumapp.utils.Resource
import com.example.demoalbumapp.utils.getOrAwaitValue
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AlbumViewModelTest {

    private lateinit var repository: MainRepository
    private lateinit var viewModel: AlbumsViewModel
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = FakeMainRepository()
        viewModel = AlbumsViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getAlbum() with successful response`() = testDispatcher.runBlockingTest {
        val actual = viewModel.albums.getOrAwaitValue()
        Truth.assertThat(actual).isInstanceOf(Resource.Success::class.java)
    }

    @Test
    fun `getAlbum() should update albums LiveData with success response`() =
        testDispatcher.runBlockingTest {
            // Given
            val fakeAlbums = listOf(
                listOf(
                    Photo(
                        1,
                        1,
                        "accusamus beatae ad facilis cum similique qui sunt",
                        "https://via.placeholder.com/600/92c952",
                        "https://via.placeholder.com/150/92c952"
                    )
                )
            )

            val value = Resource.Success(fakeAlbums)

            Truth.assertThat(value).isInstanceOf(Resource.Success::class.java)

        }

    @Test
    fun `getAlbum() should update albums LiveData with error response`() = runBlockingTest {
        val value = Resource.Error<List<List<Photo>>>("Some error")
        Truth.assertThat(value).isInstanceOf(Resource.Error::class.java)
    }



}