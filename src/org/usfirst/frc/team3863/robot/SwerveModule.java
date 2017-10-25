package org.usfirst.frc.team3863.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Created by Aaron Fang on 10/3/2017.
 */
public class SwerveModule {
    private boolean isReversed;
    private CANTalon driveMotor;
    private CANTalon steeringMotor;
    private boolean inHighGear;
    private double angle;
    private double speed;
    /*Motor Specs:
    MiniCIM
    NeveRest 40 - 28PPR and 1120CPR

     */

    public SwerveModule(int driveMotorID, int steeringMotorID, boolean isReversed,
                         double steerP, double steerI, double steerD){
        this.isReversed = isReversed;
        driveMotor = new CANTalon(driveMotorID);
        //shifter = new Solenoid(highGearID);
        //this.setInHighGear();
        steeringMotor = new CANTalon(steeringMotorID);
        steeringMotor.enableBrakeMode(true);
        if(steeringMotor.isSensorPresent(CANTalon.FeedbackDevice.QuadEncoder).equals(CANTalon.FeedbackDeviceStatus.FeedbackStatusPresent) || 
           steeringMotor.isSensorPresent(CANTalon.FeedbackDevice.QuadEncoder).equals(CANTalon.FeedbackDeviceStatus.FeedbackStatusUnknown)) {
        	
             steeringMotor.enableZeroSensorPositionOnIndex(true, true);      //encoder position is within [0, 3360]
             steeringMotor.changeControlMode(CANTalon.TalonControlMode.Position);
             steeringMotor.setPID(steerP,steerI,steerD);
             //TODO: Get this zero function working
             //zeroRotation();
             System.out.println("DEBUG: Encoder and PID settings for CANTalon: " + steeringMotorID + " have been applied");
        }
        else
             System.out.println("ERROR: Encoder on CANTalon: " + steeringMotorID + " is not detected. Verify that all wires are plugged in securely. ");

    }

    public void setAngleDegrees(double degrees){
        angle = degrees;
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

    public void setAngleRadians(double rad){
        setAngleDegrees(Math.toDegrees(rad));
    }

    public double getAngleDegrees(){
        return angle;
    }

    public double getAngleRad(){
        return Math.toRadians(angle);
    }

    public void setWheelPower(double power){
        if(isReversed)
            driveMotor.set(-power);
        else
            driveMotor.set(power);
    }

    public void zeroRotation(){
        //TODO: Find a way for the Talon to go past the limit switch but still zero the encoder.
        steeringMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        while(steeringMotor.getPosition()!=0){
            steeringMotor.set(1);
        }
        steeringMotor.changeControlMode(CANTalon.TalonControlMode.Position);
    }

    /*public void setInHighGear(){
        inHighGear = true;
        shifter.set(inHighGear);
    }

    public void setInLowGear(){
        inHighGear = false;
        shifter.set(inHighGear);
    }*/
}
