package healthstack.backend.integration.adapter

import healthstack.backend.integration.registration.User
import healthstack.backend.integration.task.TaskResult
import healthstack.backend.integration.task.TaskSpec
import healthstack.healthdata.link.HealthData
import java.time.LocalDateTime
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@DisplayName("Health Stack Backend Adapter Test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@OptIn(ExperimentalCoroutinesApi::class)
class HealthStackBackendAdapterTest {

    private lateinit var networkClientStub: HealthStackBackendAPI
    private lateinit var healthStackBackendAdapter: HealthStackBackendAdapter
    private val idToken: String = "idToken"
    private val projectId: String = "1"

    @BeforeAll
    fun beforeAll() {
        networkClientStub = mock(HealthStackBackendAPI::class.java)
        healthStackBackendAdapter = HealthStackBackendAdapter(networkClientStub, projectId)
    }

    @Test
    fun `test sync health data api`() {
        val healthData = HealthData("HeartRate", emptyList())

        runTest {
            healthStackBackendAdapter.sync(idToken, healthData)
            verify(networkClientStub).sync(idToken, projectId, healthData)
        }
    }

    @Test
    fun `test register user api`() {
        val user = User("id", emptyMap())

        runTest {
            healthStackBackendAdapter.registerUser(idToken, user)
            verify(networkClientStub).registerUser(idToken, projectId, user)
        }
    }

    @Test
    fun `test get task api`() {
        val lastSyncTime = LocalDateTime.now()
        val endTime = LocalDateTime.now()
        val taskSpec = TaskSpec(
            0, "taskId", "title", "description",
            "0 0 12 1/1 * ? *", "2022-09-12T12:00:00", "2022-09-12T12:00:00",
            1440, emptyList()
        )

        runTest {
            `when`(networkClientStub.getTasks(idToken, projectId, lastSyncTime, endTime))
                .thenReturn(listOf(taskSpec))

            val specs = healthStackBackendAdapter.getTasks(idToken, lastSyncTime, endTime)

            verify(networkClientStub).getTasks(idToken, projectId, lastSyncTime, endTime)
            assertEquals(listOf(taskSpec), specs)
        }
    }

    @Test
    fun `test upload task api`() {
        val taskResult = TaskResult(
            "userId", "taskId", 0,
            "2022-09-12T12:00:00", "2022-09-12T12:00:00", emptyList()
        )

        runTest {
            healthStackBackendAdapter.uploadTaskResult(idToken, taskResult)
            verify(networkClientStub).uploadTaskResult(idToken, projectId, listOf(taskResult))
        }
    }
}
