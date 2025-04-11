package edu.iesam.meceiot.features.pantallasensor.data

import edu.iesam.meceiot.features.grafana.data.remote.GrafanaRemoteDataSource
import edu.iesam.meceiot.features.pantallasensor.data.local.db.SensorDbDataSource
import edu.iesam.meceiot.features.pantallasensor.domain.GraphSensor
import edu.iesam.meceiot.features.pantallasensor.domain.GraphSensorRepository
import org.koin.core.annotation.Single

@Single
class GraphGraphSensorDataRepository(
    private val remoteDataSource: GrafanaRemoteDataSource,
    private val localDataSource: SensorDbDataSource
) : GraphSensorRepository {
    override suspend fun getSensorDataById(
        id: Int,
        query: String,
        from: Long,
        to: Long
    ): Result<GraphSensor> {
        // LocalDataSource doesn't support time ranges, so we always go to remote if local fails or isn't applicable?
        // Or should we check if the requested range is within the locally cached data? (More complex)
        // For now, let's bypass local check when specific range is requested, needs refinement.
        // TODO: Refine local cache strategy for time ranges.

        // Simplification: Directly fetch from remote when a specific range is requested.
        // If we want to use local cache, we'd need to store timestamps with the local data.

        /* Original logic:
        val localResult = localDataSource.getById(id)
        return if (localResult.isSuccess) {
            localResult
        } else {
            val remoteResult = remoteDataSource.getSensorData(query, from, to)
            if (remoteResult.isSuccess) {
                // We should ideally save with timestamp info if caching range-based data.
                // localDataSource.save(remoteResult.getOrThrow())
            }
            remoteResult
        }
        */

        // Always fetch from remote for now when time range is involved
        return remoteDataSource.getSensorData(query, from, to)
        // TODO: Implement local caching strategy for time-range queries later if needed.
    }
}