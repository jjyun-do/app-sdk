package healthstack.backend.integration.adapter

import healthstack.backend.integration.BackendFacade
import healthstack.healthdata.link.HealthData
import java.time.LocalDateTime

class HealthStackBackendAdapter private constructor(
    private val networkClient: HealthStackBackendAPI,
    private val projectId: String,
) : BackendFacade {

    override suspend fun sync(idToken: String, healthData: HealthData) {
        networkClient.sync(idToken, projectId, healthData)
    }

    override suspend fun registerUser(idToken: String, user: healthstack.backend.integration.registration.User) {
        networkClient.registerUser(idToken, projectId, user)
    }

    override suspend fun getTasks(
        idToken: String,
        lastSyncTime: LocalDateTime
    ): List<healthstack.backend.integration.task.TaskSpec> =
        networkClient.getTasks(idToken, projectId, lastSyncTime)

    override suspend fun uploadResult(idToken: String, result: healthstack.backend.integration.task.TaskResult) =
        networkClient.uploadTaskResult(idToken, projectId, listOf(result))

    companion object {
        private lateinit var INSTANCE: HealthStackBackendAdapter

        fun initialize(platformEndpoint: String, researchProjectId: String) {
            synchronized(this) {
                if (Companion::INSTANCE.isInitialized.not()) {
                    INSTANCE = HealthStackBackendAdapter(
                        RetrofitFactory.create(
                            platformEndpoint,
                            HealthStackBackendAPI::class.java
                        ),
                        researchProjectId
                    )
                }
            }
        }

        fun getInstance(): HealthStackBackendAdapter = INSTANCE
    }
}
