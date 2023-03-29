package healthstack.kit.task.activity.model

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import healthstack.kit.sensor.SensorUtils
import healthstack.kit.task.base.StepModel
import healthstack.kit.ui.TextType

class RangeOfMotionMeasureModel(
    id: String,
    title: String,
    val header: String = "Right Arm Circumduction",
    val body: List<String>? = listOf(
        "Place phone in your right hand.",
        "Straighten your right arm and move it in a full circle for 20 sec",
    ),
    val buttonText: String = "Continue",
    val textType: TextType = TextType.NUMBER,
    val timeSeconds: Long = 20,
    isRightHand: Boolean = true,
) : StepModel(id, title, null) {
    fun init() {
        dataManager.init()
    }

    fun close() {
        dataManager.cancel()
    }

    val handType: String = when (isRightHand) {
        true -> "right"
        else -> "left"
    }

    private val dataManager = SensorDataManager()

    val timeMillis: List<Long>
        get() {
            return dataManager.times
        }

    val accelerometer: List<List<Float>>
        get() {
            return dataManager.accelerometer
        }

    val gyroscope: List<List<Float>>
        get() {
            return dataManager.gyroscope
        }

    class SensorDataManager() : SensorEventListener {
        var times = mutableListOf<Long>()
        var accelerometer = listOf(mutableListOf<Float>(), mutableListOf<Float>(), mutableListOf<Float>())
        var gyroscope = listOf(mutableListOf<Float>(), mutableListOf<Float>(), mutableListOf<Float>())

        private var currentAccel: FloatArray? = null
        private var currentGyro: FloatArray? = null

        fun init() {
            SensorUtils.sensorManager.registerListener(
                this,
                SensorUtils.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL
            )
            SensorUtils.sensorManager.registerListener(
                this,
                SensorUtils.sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }

        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_ACCELEROMETER)
                currentAccel = event.values

            if (event.sensor.type == Sensor.TYPE_GYROSCOPE)
                currentGyro = event.values

            if (currentAccel != null && currentGyro != null) {
                times.add(System.currentTimeMillis())
                accelerometer[0].add(currentAccel!![0])
                accelerometer[1].add(currentAccel!![1])
                accelerometer[2].add(currentAccel!![2])
                gyroscope[0].add(currentGyro!![0])
                gyroscope[1].add(currentGyro!![1])
                gyroscope[2].add(currentGyro!![2])
            }
        }

        fun cancel() {
            SensorUtils.sensorManager.unregisterListener(this)
        }

        override fun onAccuracyChanged(p0: Sensor, p1: Int) {}
    }
}
