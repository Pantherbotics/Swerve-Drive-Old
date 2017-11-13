package team3863.robot

import com.ctre.CANTalon
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import team3863.robot.RobotMap


//import edu.wpi.first.wpilibj.livewindow.LiveWindow;


/**
 * Created by Aaron Fang on 10/3/2017.
 */
class SwerveModule
/*Motor Specs:
    MiniCIM
    NeveRest 40 - 280PPR and 1120CPR

     */
(driveMotorID: Int, steeringMotorID: Int, private val offset: Int, private val isReversed: Boolean,
 steerP: Double, steerI: Double, steerD: Double) {
    private val driveMotor: CANTalon
    private val steeringMotor: CANTalon
    var angleDegrees: Double = 0.0
        set(degrees) {
            field = degrees
            field += offset
            val set: Double
            if (isReversed) {
                set = 2940 - 2940 * degrees / 360
                steeringMotor.set(300.0)
            } else {
                set = 2940 * degrees / 360
                steeringMotor.set(300.0)
            }

        }
    private val speed: Double = 0.toDouble()

    init {
        driveMotor = CANTalon(driveMotorID)
        steeringMotor = CANTalon(steeringMotorID)
        steeringMotor.enableBrakeMode(true)
        if (steeringMotor.isSensorPresent(CANTalon.FeedbackDevice.QuadEncoder) == CANTalon.FeedbackDeviceStatus.FeedbackStatusPresent || steeringMotor.isSensorPresent(CANTalon.FeedbackDevice.QuadEncoder) == CANTalon.FeedbackDeviceStatus.FeedbackStatusUnknown) {

            steeringMotor.enableZeroSensorPositionOnIndex(true, true)      //encoder position is within [0, 3360]
            steeringMotor.changeControlMode(CANTalon.TalonControlMode.Position)
            steeringMotor.setPID(steerP, steerI, steerD)
            //LiveWindow.addActuator("SwerveDrive", "steeringMotor-", steeringMotor);
            //steeringMotor.startLiveWindowMode();
            steeringMotor.encPosition = 5000
            steeringMotor.inverted = isReversed
            println("DEBUG: Encoder and PID settings for CANTalon: $steeringMotorID have been applied")
        } else
            println("ERROR: Encoder on CANTalon: $steeringMotorID is not detected. Verify that all wires are plugged in securely. ")

    }

    fun enable() {
        println(" en: " + steeringMotor.isEnabled + " cn: " + steeringMotor.isControlEnabled + " sf: " + steeringMotor.isSafetyEnabled)
        steeringMotor.enable()
        steeringMotor.enableControl()
    }

    fun setAngleRadians(rad: Double) {
        var rad2 : Double = rad;
        if(Math.signum(rad) == -1.0){
             rad2 = 2*Math.PI - rad
        }
        angleDegrees = Math.toDegrees(rad2)
    }

    val angleRad: Double
        get() = Math.toRadians(angleDegrees)

    fun setWheelPower(power: Double) {
        if (isReversed)
            driveMotor.set(-power)
        else
            driveMotor.set(power)
    }

    fun setOpenLoop(){
        steeringMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus)
    }

    fun setClosedLoop(){
        steeringMotor.changeControlMode(CANTalon.TalonControlMode.Position)
    }

    fun setSteerMotor(s : Double){
        steeringMotor.set(s)
    }

    val encPosition: Int
        get() = steeringMotor.encPosition

    val name: String
        get(){
            when(steeringMotor.deviceID){
                12 -> return "Top Right"
                15 -> return "Top Left"
                3 -> return "Bot Right"
                5 -> return "Bot Left"
                else -> return "idk lol"
            }
        }

    fun setEncPosition(x: Int){
        steeringMotor.encPosition = x
    }

    fun zero(){
        launch {
            setOpenLoop()
            while (encPosition !=0){
                setSteerMotor(0.5)
            }
        }
    }

    val error: Int
        get() = steeringMotor.closedLoopError
}
