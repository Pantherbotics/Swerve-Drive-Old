package org.usfirst.frc.team3863.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3863.robot.RobotMap;
import org.usfirst.frc.team3863.robot.SwerveModule;

/**
 * Created by Aaron Fang on 10/7/2017.
 */
public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private SwerveModule topLeft, topRight, bottomLeft, bottomRight;

    public void initDefaultCommand() {
        topLeft = new SwerveModule(RobotMap.topLeft_Drive, RobotMap.topLeft_Steer, false, RobotMap.topLeft_Solenoid, RobotMap.steer_P, RobotMap.steer_I, RobotMap.steer_D);
        topRight = new SwerveModule(RobotMap.topRight_Drive, RobotMap.topRight_Steer, false, RobotMap.topRight_Solenoid, RobotMap.steer_P, RobotMap.steer_I, RobotMap.steer_D);
        bottomRight = new SwerveModule(RobotMap.botRight_Drive, RobotMap.botRight_Steer, false, RobotMap.botRight_Solenoid, RobotMap.steer_P, RobotMap.steer_I, RobotMap.steer_D);
        bottomLeft = new SwerveModule(RobotMap.botLeft_Drive, RobotMap.botLeft_Steer, false, RobotMap.botLeft_Solenoid, RobotMap.steer_P, RobotMap.steer_I, RobotMap.steer_D);
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
}

