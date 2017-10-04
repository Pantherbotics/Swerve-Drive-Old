package org.usfirst.frc.team3863.robot;

import com.ctre.MotorControl.CANTalon;
import com.ctre.MotorControl.SmartMotorController;

/**
 * Created by Aaron Fang on 10/3/2017.
 */
public class PrintedSwerve implements SwerveModule {
    private boolean isReversed;
    private CANTalon driveMotor;
    private CANTalon steeringMotor;
    /*Motor Specs:
    MiniCIM
    NeveRest 40 - 28PPR and 1120CPR

     */

    public PrintedSwerve(int driveMotorID, int steeringMotorID, boolean isReversed, double p, double i, double d){
        this.isReversed = isReversed;
        driveMotor = new CANTalon(driveMotorID);
        steeringMotor = new CANTalon(steeringMotorID);

        steeringMotor.enableBrakeMode(true);

        if(steeringMotor.isSensorPresent(SmartMotorController.FeedbackDevice.QuadEncoder).equals(SmartMotorController.FeedbackDeviceStatus.FeedbackStatusPresent)) {
            steeringMotor.enableZeroSensorPositionOnIndex(true, true);      //encoder position is within [0, 3360]
            steeringMotor.changeControlMode(SmartMotorController.TalonControlMode.Position);
            steeringMotor.setPID(p,i,d);
        }
        else
            throw new Error("Encoder is not detected. Verify that all wires are plugged in securely.");
    }

    public void setAngleDegrees(double degrees){
        double set;
        if(isReversed){
            set = 3360 - (3360*degrees)/360;
            steeringMotor.set(set);
        }
        else{
            set = (3360*degrees)/360;
            steeringMotor.set(set);
        }

    }

    public void setWheelPower(double power){
        if(isReversed)
            driveMotor.set(-power);
        else
            driveMotor.set(power);
    }

    public void zeroRotation(){
        //TODO: Find a way for the Talon to go past the limit switch but still zero the encoder.
    }
}
