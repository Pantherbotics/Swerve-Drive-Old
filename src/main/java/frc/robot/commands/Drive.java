/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.Util.DriveCommand;
import frc.robot.Util.ModuleLocation;

import static frc.robot.Robot.kDrivetrain;
import static frc.robot.Robot.oi;

public class Drive extends Command {
  double leftX, leftY, rightX, heading, temp, A, B, C, D, frontLeftSpeed, frontRightSpeed, backLeftSpeed, backRightSpeed, max;
  DriveCommand frontLeftCommand, frontRightCommand, backLeftCommand, backRightCommand;
  public Drive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.kDrivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    leftX = oi.getLeftXAxis();
    leftY = oi.getLeftYAxis();
    rightX = oi.getRightXAxis();
    heading = Math.toRadians(Robot.kDrivetrain.getGyro());

    temp = leftY*Math.cos(heading) + leftX*Math.sin(heading);
    leftX = -leftY*Math.sin(heading) + leftX*Math.cos(heading);
    leftY = temp;

    A = leftX - rightX * (Constants.kWheelbase/Constants.kTurnRadius);
    B = leftX + rightX * (Constants.kWheelbase/Constants.kTurnRadius);
    C = leftY - rightX * (Constants.kTrackwidth/Constants.kTurnRadius);
    D = leftY + rightX * (Constants.kTrackwidth/Constants.kTurnRadius);

    frontLeftSpeed = Math.hypot(B, D);
    frontRightSpeed = Math.hypot(B, C);
    backLeftSpeed = Math.hypot(A, D);
    backRightSpeed = Math.hypot(A, C);
    
    max = frontLeftSpeed;

    if(frontRightSpeed > max)
      max = frontRightSpeed;
    if(backLeftSpeed > max)
      max = backLeftSpeed;
    if(backRightSpeed > max)
      max = backRightSpeed;
    if(max > 1){
      frontLeftSpeed /= max;
      frontRightSpeed /= max;
      backLeftSpeed /= max;
      backRightSpeed /= max;
    }

    frontLeftCommand = new DriveCommand((Math.atan2(B, D) * 180.0/Math.PI), frontLeftSpeed);
    frontRightCommand = new DriveCommand((Math.atan2(B, C) * 180.0/Math.PI), frontRightSpeed);
    backLeftCommand = new DriveCommand((Math.atan2(A, D) * 180.0/Math.PI), backLeftSpeed);
    backRightCommand = new DriveCommand((Math.atan2(A, C) * 180.0/Math.PI), backRightSpeed);

    kDrivetrain.setModule(ModuleLocation.FRONT_LEFT, frontLeftCommand);
    kDrivetrain.setModule(ModuleLocation.FRONT_RIGHT, frontRightCommand);
    kDrivetrain.setModule(ModuleLocation.BACK_LEFT, backLeftCommand);
    kDrivetrain.setModule(ModuleLocation.BACK_RIGHT, backRightCommand);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
