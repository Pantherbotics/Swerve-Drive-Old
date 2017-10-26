package org.usfirst.frc.team3863.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3863.robot.RobotMap;
import org.usfirst.frc.team3863.robot.SwerveModule;
import org.usfirst.frc.team3863.robot.commands.Drive;

/**
 * Created by Aaron Fang on 10/7/2017.
 */
public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private SwerveModule topLeft, topRight, bottomLeft, bottomRight;

    private AnalogGyro gyro;

    private DoubleSolenoid shifter;

    private boolean inHighGear;


    public void initDefaultCommand() {
        setDefaultCommand(new Drive());
    }

    public Drivetrain(){
        topLeft = new SwerveModule(RobotMap.topLeft_Drive, RobotMap.topLeft_Steer, false, RobotMap.steer_P, RobotMap.steer_I, RobotMap.steer_D);
        topRight = new SwerveModule(RobotMap.topRight_Drive, RobotMap.topRight_Steer, false, RobotMap.steer_P, RobotMap.steer_I, RobotMap.steer_D);
        bottomRight = new SwerveModule(RobotMap.botRight_Drive, RobotMap.botRight_Steer, true, RobotMap.steer_P, RobotMap.steer_I, RobotMap.steer_D);
        bottomLeft = new SwerveModule(RobotMap.botLeft_Drive, RobotMap.botLeft_Steer, true, RobotMap.steer_P, RobotMap.steer_I, RobotMap.steer_D);

        gyro = new AnalogGyro(RobotMap.gyro);
        gyro.calibrate();
        

        shifter = new DoubleSolenoid(RobotMap.shift_Forward, RobotMap.shift_Reverse);
    }
    //set is an array
    public void setTopLeft(double[] set){
        topLeft.setWheelPower(set[0]);
        topLeft.setAngleRadians(set[1]);
    }

    public void setTopRight(double[] set){
        topRight.setWheelPower(set[0]);
        topRight.setAngleRadians(set[1]);
    }

    public void setBottomLeft(double[] set){
        bottomLeft.setWheelPower(set[0]);
        bottomLeft.setAngleRadians(set[1]);
    }

    public void setBottomRight(double[] set){
        bottomRight.setWheelPower(set[0]);
        bottomRight.setAngleRadians(set[1]);
    }

    public double getHeadingDegrees(){
        return gyro.getAngle();
    }

    public double getHeadingRadians(){
        return Math.toRadians(gyro.getAngle());
    }

    public void setInHighGear(){
        inHighGear = true;
        shifter.set(DoubleSolenoid.Value.kForward);
    }

    public void setInLowGear(){
        inHighGear = false;
        shifter.set(DoubleSolenoid.Value.kReverse);
    }

    public boolean isInHighGear(){
        return inHighGear;
    }
    
    public void enableAll() {
    	topLeft.enable();
        topRight.enable();
        bottomLeft.enable();
        bottomRight.enable();
    }
}

