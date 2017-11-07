package team3863.subsystems;

import com.ctre.CANTalon
import edu.wpi.first.wpilibj.AnalogGyro
import edu.wpi.first.wpilibj.DoubleSolenoid
import edu.wpi.first.wpilibj.command.Subsystem
import team3863.robot.RobotMap
import team3863.robot.SwerveModule
import team3863.robot.commands.Drive
import edu.wpi.first.wpilibj.ADXRS450_Gyro


/**
 * Created by Aaron Fang on 10/7/2017.
 */
class Drivetrain : Subsystem() {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    val topLeft: SwerveModule
    val topRight: SwerveModule
    val bottomLeft: SwerveModule
    val bottomRight: SwerveModule

    private val gyro: ADXRS450_Gyro

    private val shifter: DoubleSolenoid

    var isInHighGear: Boolean = false
        private set


    public override fun initDefaultCommand() {
        defaultCommand = Drive()
    }

    init {
        topLeft = SwerveModule(RobotMap.topLeft_Drive, RobotMap.topLeft_Steer, RobotMap.topLeft_Offset, false, RobotMap.steer_P, RobotMap.steer_I, RobotMap.steer_D)
        topRight = SwerveModule(RobotMap.topRight_Drive, RobotMap.topRight_Steer, RobotMap.topRight_Offset, false, RobotMap.steer_P, RobotMap.steer_I, RobotMap.steer_D)
        bottomRight = SwerveModule(RobotMap.botRight_Drive, RobotMap.botRight_Steer, RobotMap.botRight_Offset, true, RobotMap.steer_P, RobotMap.steer_I, RobotMap.steer_D)
        bottomLeft = SwerveModule(RobotMap.botLeft_Drive, RobotMap.botLeft_Steer, RobotMap.botLeft_Offset, true, RobotMap.steer_P, RobotMap.steer_I, RobotMap.steer_D)

        gyro = ADXRS450_Gyro()
        gyro.calibrate()


        shifter = DoubleSolenoid(RobotMap.shift_Forward, RobotMap.shift_Reverse)
    }

    //set is an array
    fun setTopLeft(set: DoubleArray) {
        topLeft.setWheelPower(set[0])
        //topLeft.setAngleRadians(set[1])
    }

    fun setTopRight(set: DoubleArray) {
        topRight.setWheelPower(set[0])
        //topRight.setAngleRadians(set[1])
    }

    fun setBottomLeft(set: DoubleArray) {
        bottomLeft.setWheelPower(set[0])
        //bottomLeft.setAngleRadians(set[1])
    }

    fun setBottomRight(set: DoubleArray) {
        bottomRight.setWheelPower(set[0])
        //bottomRight.setAngleRadians(set[1])
    }

    val headingDegrees: Double
        get() = gyro.angle

    val headingRadians: Double
        get() = Math.toRadians(gyro.angle)

    fun setInHighGear() {
        isInHighGear = true
        shifter.set(DoubleSolenoid.Value.kForward)
    }

    fun setInLowGear() {
        isInHighGear = false
        shifter.set(DoubleSolenoid.Value.kReverse)
    }

    fun enableAll() {
        topLeft.enable()
        topRight.enable()
        bottomLeft.enable()
        bottomRight.enable()
    }

    fun getModules() : Array<SwerveModule>{
        return arrayOf(topRight, topLeft, bottomRight, bottomLeft)
    }

    fun zeroAll(){
        topRight.zero()
        topLeft.zero()
        bottomRight.zero()
        bottomLeft.zero()
    }

}

