package org.usfirst.frc.team3863.robot

import com.ctre.CANTalon
import edu.wpi.first.wpilibj.DoubleSolenoid
import edu.wpi.first.wpilibj.Solenoid



//import edu.wpi.first.wpilibj.livewindow.LiveWindow;


/**
 * Created by Aaron Fang on 10/3/2017.
 */
class SwerveModule
/*Motor Specs:
    MiniCIM
    NeveRest 40 - 280PPR and 1120CPR

     */
(driveMotorID: Int, steeringMotorID: Int, private val isReversed: Boolean,
 steerP: Double, steerI: Double, steerD: Double) {
    private val driveMotor: CANTalon
    private val steeringMotor: CANTalon
    private val inHighGear: Boolean = false
    var angleDegrees: Double = 0.0
        set(degrees) {
            field = degrees
            val set: Double
            if (isReversed) {
                set = 3360 - 3360 * degrees / 360
                steeringMotor.set(set)
            } else {
                set = 3360 * degrees / 360
                steeringMotor.set(set)
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
        angleDegrees = Math.toDegrees(rad)
    }

    val angleRad: Double
        get() = Math.toRadians(angleDegrees)

    fun setWheelPower(power: Double) {
        if (isReversed)
            driveMotor.set(-power)
        else
            driveMotor.set(power)
    }

    fun zero(){
        launch{

        }
    }

}
