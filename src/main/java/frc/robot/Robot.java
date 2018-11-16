/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//import javax.swing.text.Position;
//import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drivetrain;
import frc.robot.commands.setMotorSpeed;
import edu.wpi.first.wpilibj.command.Command;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  public static final Drivetrain kDrivetrain = new Drivetrain();
  //public static final SwerveModule module = new SwerveModule(Constants.kSteeringID, Constants.kDriveID, false, Constants.kSwerveP, Constants.kSwerveI, Constants.kSwerveD);

  public TalonSRX mSteering = new TalonSRX(Constants.kSteeringID);
  public OI oi = new OI();
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.addDefault("Default Auto", kDefaultAuto);
    m_chooser.addObject("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    /*
    mSteering.configSelectedFeedbackSensor(FeedbackDevice.Analog, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    mSteering.configOpenloopRamp(0, Constants.kTimeoutMs);      //this is what we were missing!
    mSteering.configPeakCurrentDuration(Constants.kPeakCurrentDuration, Constants.kTimeoutMs);
    mSteering.configPeakCurrentLimit(Constants.kPeakCurrentLimit, Constants.kTimeoutMs);
    mSteering.configContinuousCurrentLimit(Constants.kSustainedCurrentLimit, Constants.kTimeoutMs);
    mSteering.setStatusFramePeriod(StatusFrame.Status_4_AinTempVbat, 1, 0);
    mSteering.setInverted(true);
    mSteering.setSensorPhase(true);

    mSteering.config_kP(0, 2.0, 0);
    mSteering.config_kI(0, 0 ,0);
    mSteering.config_kD(0, 0, 0);
    */
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
      SmartDashboard.putNumber("Raw Encoder", mSteering.getSelectedSensorPosition(0));

  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    /*
    int setpoint = 600;
    int encPos = mSteering.getSelectedSensorPosition(0);
    int correctedEncoderPosition = (int)Math.round(((Math.abs(encPos) % 1023) - 45) * Math.abs((1023.0/(870-45))));
    int error = setpoint - correctedEncoderPosition;

    SmartDashboard.putNumber("Setpoint", setpoint);
    SmartDashboard.putNumber("EncPos", correctedEncoderPosition);
    SmartDashboard.putNumber("Error", error);

    if(correctedEncoderPosition > 600)
      mSteering.set(ControlMode.PercentOutput, -1.0);
    if(correctedEncoderPosition < 600)
      mSteering.set(ControlMode.PercentOutput, 1.0);
*/

    mSteering.set(ControlMode.PercentOutput, oi.getLeftXAxis());
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.][\
   *
   */
  @Override
  public void testPeriodic() {
  }
}
